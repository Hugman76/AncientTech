package org.example.MODID.init.data;

import org.example.MODID.MODCLASS;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class MODCLASSTags
{
	public static final TagKey<Block> TEMPLATE_BLOCKS = blocks("template_blocks");

	private static TagKey<Block> blocks(String name) {
		return TagKey.of(Registry.BLOCK_KEY, MODCLASS.MOD_DATA.id(name));
	}
}
