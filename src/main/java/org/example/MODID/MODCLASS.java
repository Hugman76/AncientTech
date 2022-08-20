package org.example.MODID;

import com.google.common.reflect.Reflection;
import com.hugman.dawn.api.object.ModData;
import org.example.MODID.config.MODCLASSConfig;
import org.example.MODID.init.MODCLASSBlockBundle;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MODCLASS implements ModInitializer
{
	public static final ModData MOD_DATA = new ModData("MODID");
	public static final Logger LOGGER = LogManager.getLogger();
	public static final MODCLASSConfig CONFIG = AutoConfig.register(MODCLASSConfig.class, GsonConfigSerializer::new).getConfig();

	@Override
	public void onInitialize() {
		initBundles();
		MOD_DATA.registerCreators();
	}

	public static void initBundle(Class<?> clazz) {
		Reflection.initialize(clazz);
		for(Class<?> clazz2 : clazz.getClasses()) {
			initBundle(clazz2);
		}
	}

	public static void initBundles() {
		initBundle(MODCLASSBlockBundle.class);
	}
}