package org.example.MODID.init;

import com.hugman.dawn.api.creator.BlockCreator;
import org.example.MODID.object.block.MODCLASSBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class MODCLASSBlockBundle extends MODCLASSBundle
{
	public static final Block TEMPLATE_BLOCK = add(new BlockCreator.Builder("template_block", MODCLASSBlock::new, FabricBlockSettings.copyOf(Blocks.STONE)).build());
}
