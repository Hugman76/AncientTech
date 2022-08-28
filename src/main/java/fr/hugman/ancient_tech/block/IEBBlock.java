package fr.hugman.ancient_tech.block;

import fr.hugman.ancient_tech.screen.IEBDescription;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The intangible <a href="https://en.wikipedia.org/wiki/Echea">echea</a>is a block that allows the player to store a very large amount of items no matter where they are, similarly to the ender chest.
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

		if(world.isClient) {
			return ActionResult.SUCCESS;
		}
		else {
			player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
			//player.incrementStat(Stats.INTERACT_WITH_GRINDSTONE);
			return ActionResult.CONSUME;
		}
	}

	@Override
	public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
		return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new IEBDescription(syncId, inventory, ScreenHandlerContext.create(world, pos)), TITLE);
	}
}
