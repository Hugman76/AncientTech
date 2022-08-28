package fr.hugman.ancient_tech.screen;

import fr.hugman.ancient_tech.AncientTech;
import fr.hugman.ancient_tech.echea.IncorporealEchea;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import org.jetbrains.annotations.Nullable;

public class IEBDescription extends SyncedGuiDescription {
	public IEBDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
		super(AncientTech.IEB_SCREEN_HANDLER_TYPE, syncId, playerInventory);

		@Nullable var echea = context.get((world, pos) -> IncorporealEchea.get(world), null);

		WGridPanel root = new WGridPanel();
		setRootPanel(root);
		root.setSize(300, 200);
		root.setInsets(Insets.ROOT_PANEL);

		WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
		root.add(itemSlot, 4, 1);

		root.add(this.createPlayerInventoryPanel(), 0, 3);

		root.validate(this);
	}
}