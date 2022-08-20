package org.example.MODID.object.block;

import org.example.MODID.MODCLASS;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MODCLASSBlock extends Block
{
	public MODCLASSBlock(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if(!world.isClient()) {
			BlockPos pos1 = pos.offset(hit.getSide());
			world.spawnEntity(new ItemEntity(world, pos1.getX() + 0.5, pos1.getY() + 0.5, pos1.getZ() + 0.5, new ItemStack(Items.DIAMOND, MODCLASS.CONFIG.items_per_click)));
		}
		return ActionResult.SUCCESS;
	}
}
