package ftb.lib.api.players;

import net.minecraft.world.WorldServer;

/**
 * Created by LatvianModder on 16.02.2016.
 */
public interface IWorldTick
{
	void onTick(WorldServer w, long now);
}