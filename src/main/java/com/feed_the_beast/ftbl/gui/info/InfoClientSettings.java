package com.feed_the_beast.ftbl.gui.info;

import com.feed_the_beast.ftbl.api.config.ConfigEntryBool;
import com.feed_the_beast.ftbl.api.config.ConfigEntryInt;
import latmod.lib.annotations.Flags;
import latmod.lib.annotations.NumberBounds;

/**
 * Created by LatvianModder on 22.03.2016.
 */
public class InfoClientSettings
{
	public static final ConfigEntryBool unicode = new ConfigEntryBool("unicode", true);
	
	@Flags(Flags.USE_SLIDER)
	@NumberBounds(min = 100, max = 255)
	public static final ConfigEntryInt transparency = new ConfigEntryInt("transparency", 255);
	
	@Flags(Flags.USE_SLIDER)
	@NumberBounds(min = 0, max = 200)
	public static final ConfigEntryInt border_width = new ConfigEntryInt("border_width", 15);
	
	@Flags(Flags.USE_SLIDER)
	@NumberBounds(min = 0, max = 50)
	public static final ConfigEntryInt border_height = new ConfigEntryInt("border_height", 15);
}