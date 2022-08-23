package fr.hugman.ancient_tech.echea;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The intangible <a href="https://en.wikipedia.org/wiki/Echea">echea</a>is a block that allows the player to store a very large amount of items no matter where they are, similarly to the ender chest.
 * This block is obviously very difficult to obtain.
 */
public class IncorporealEcheaBrowserBlock extends Block {
	public IncorporealEcheaBrowserBlock(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		// TODO: open GUI

		// TEMPORARY BEHAVIOUR
		if(!world.isClient()) {
			var echea = IncorporealEchea.get(world);
			var stack = player.getStackInHand(hand);

			// Full hand interaction
			if(!stack.isEmpty()) {
				echea.content().add(player.getStackInHand(hand));
				player.setStackInHand(hand, ItemStack.EMPTY);
			}

			// Empty hand interaction
			else {
				if(!echea.content().isEmpty()) {
					var firstStack = echea.content().get(echea.content().size() - 1);
					echea.content().remove(firstStack);
					player.setStackInHand(hand, firstStack);
				}
			}
		}
		return ActionResult.SUCCESS;
	}
}
