package fr.hugman.ancient_tech.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class EcheaScreen extends AbstractInventoryScreen<EcheaScreenHandler> {
    static final SimpleInventory INVENTORY = new SimpleInventory(45);

    public EcheaScreen(EcheaScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {

    }
}
