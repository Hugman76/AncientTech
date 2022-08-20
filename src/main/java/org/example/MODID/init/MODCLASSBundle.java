package org.example.MODID.init;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.SimpleCreator;
import org.example.MODID.MODCLASS;

public abstract class MODCLASSBundle
{
	protected static <O, V extends SimpleCreator<O>> O add(V creator) {
		MODCLASS.MOD_DATA.addCreator(creator);
		return creator.getValue();
	}

	protected static <V extends Creator> V creator(V creator) {
		MODCLASS.MOD_DATA.addCreator(creator);
		return creator;
	}
}