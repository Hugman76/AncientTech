package fr.hugman.ancient_tech.screen;

import fr.hugman.ancient_tech.AncientTech;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class EcheaScreenHandler extends ScreenHandler {
	public EcheaScreenHandler(int syncId, PlayerInventory playerInventory) {
		this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
	}

	public EcheaScreenHandler(int syncId, PlayerInventory playerInventory, final ScreenHandlerContext context) {
		super(AncientTech.IEB_SCREEN_HANDLER_TYPE, syncId);
	}

	@Override
	public ItemStack transferSlot(PlayerEntity player, int index) {
		return null;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return true;
	}
}
