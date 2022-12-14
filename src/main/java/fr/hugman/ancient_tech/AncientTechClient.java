package fr.hugman.ancient_tech;

import fr.hugman.ancient_tech.screen.IEBBlockScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class AncientTechClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HandledScreens.register(AncientTech.IEB_SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new IEBBlockScreen(gui, inventory.player, title));
	}
}
