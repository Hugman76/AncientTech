package fr.hugman.ancient_tech.block;

import fr.hugman.ancient_tech.echea.IncorporealEchea;
import fr.hugman.ancient_tech.screen.EcheaScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The incorporeal <a href="https://en.wikipedia.org/wiki/Echea">echea</a>is a block that allows the player to store a very large amount of items no matter where they are, similarly to the ender chest.
 * This block is obviously very difficult to obtain.
 */
public class IEBBlock extends Block {
	private static final Text TITLE = Text.translatable("container.grindstone_title");

	public IEBBlock(Settings settings) {
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
				echea.add(player.getStackInHand(hand));
				player.setStackInHand(hand, ItemStack.EMPTY);
			}

			// Empty hand interaction
			else {
				if(!echea.content().isEmpty()) {
					player.setStackInHand(hand, echea.pickMax(echea.content().size() - 1));
				}
			}
		}

		if(world.isClient) {
			return ActionResult.SUCCESS;
		}
		else {
			//player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
			//player.incrementStat(Stats.INTERACT_WITH_GRINDSTONE);
			return ActionResult.CONSUME;
		}
	}

	@Override
	public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
		return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new EcheaScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), TITLE);
	}
}
