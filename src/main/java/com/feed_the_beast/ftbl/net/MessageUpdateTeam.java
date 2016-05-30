package com.feed_the_beast.ftbl.net;

import com.feed_the_beast.ftbl.api.ForgeTeam;
import com.feed_the_beast.ftbl.api.ForgeWorldSP;
import com.feed_the_beast.ftbl.api.net.LMNetworkWrapper;
import com.feed_the_beast.ftbl.api.net.MessageToClient;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by LatvianModder on 30.05.2016.
 */
public class MessageUpdateTeam extends MessageToClient<MessageUpdateTeam>
{
    public int teamID;
    public NBTTagCompound data;

    public MessageUpdateTeam()
    {
    }

    public MessageUpdateTeam(ForgeTeam team)
    {
        teamID = team.teamID;
        data = team.serializeNBTForNet();
    }

    public MessageUpdateTeam(int id)
    {
        teamID = id;
        data = null;
    }

    @Override
    public LMNetworkWrapper getWrapper()
    {
        return FTBLibNetHandler.NET;
    }

    @Override
    public void toBytes(ByteBuf io)
    {
        io.writeInt(teamID);
        writeTag(io, data);
    }

    @Override
    public void fromBytes(ByteBuf io)
    {
        teamID = io.readInt();
        data = readTag(io);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onMessage(MessageUpdateTeam m, Minecraft mc)
    {
        if(m.data == null)
        {
            ForgeWorldSP.inst.teams.remove(m.teamID);
        }
        else
        {
            ForgeTeam team = ForgeWorldSP.inst.teams.get(m.teamID);

            if(team == null)
            {
                team = new ForgeTeam(ForgeWorldSP.inst, m.teamID);
                ForgeWorldSP.inst.teams.put(m.teamID, team);
            }

            team.deserializeNBTFromNet(m.data);
        }
    }
}