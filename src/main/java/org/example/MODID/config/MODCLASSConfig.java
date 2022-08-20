package org.example.MODID.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "MODID")
@Config.Gui.Background("minecraft:textures/block/orange_concrete.png")
public class MODCLASSConfig implements ConfigData
{
	@ConfigEntry.BoundedDiscrete(min = 1, max = 64)
	public int items_per_click = 3;
}