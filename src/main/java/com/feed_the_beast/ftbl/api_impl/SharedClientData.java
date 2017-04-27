package com.feed_the_beast.ftbl.api_impl;

import com.feed_the_beast.ftbl.api.ISharedClientData;

/**
 * Created by LatvianModder on 12.11.2016.
 */
public class SharedClientData extends SharedData implements ISharedClientData
{
    public static final SharedClientData INSTANCE = new SharedClientData();
    public boolean isClientPlayerOP;

    private SharedClientData()
    {
    }

    @Override
    public void reset()
    {
        super.reset();
        optionalServerMods.clear();
        notifications.clear();
        isClientPlayerOP = false;
    }

    @Override
    public boolean isClientOP()
    {
        return isClientPlayerOP;
    }
}