package fr.hugman.ancient_tech.screen;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class IEBBlockScreen extends CottonInventoryScreen<IEBDescription> {
	public IEBBlockScreen(IEBDescription gui, PlayerEntity player, Text title) {
		super(gui, player, title);
	}
}