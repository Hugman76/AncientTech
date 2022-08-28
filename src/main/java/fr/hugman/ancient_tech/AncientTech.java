package fr.hugman.ancient_tech;

import fr.hugman.ancient_tech.block.IEBBlock;
import fr.hugman.ancient_tech.command.EcheaCommand;
import fr.hugman.ancient_tech.screen.IEBDescription;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AncientTech implements ModInitializer {
	public static final String MOD_ID = "ancient_tech";
	public static final Logger LOGGER = LogManager.getLogger();

	public static final Block INCORPOREAL_ECHEA_BROWSER = AncientTech.block("incorporeal_echea_browser", new IEBBlock(FabricBlockSettings.copy(Blocks.BEDROCK)), ItemGroup.MISC);
	public static final ScreenHandlerType<IEBDescription> IEB_SCREEN_HANDLER_TYPE = Registry.register(Registry.SCREEN_HANDLER, AncientTech.id("incorporeal_echea_browser"), new ScreenHandlerType<>((syncId, inventory) -> new IEBDescription(syncId, inventory, ScreenHandlerContext.EMPTY)));

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> EcheaCommand.register(dispatcher));
	}

	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}

	public static Block block(String name, Block block, ItemGroup group) {
		block = Registry.register(Registry.BLOCK, AncientTech.id(name), block);
		Registry.register(Registry.ITEM, AncientTech.id(name), new BlockItem(block, new FabricItemSettings().group(group)));
		return block;
	}
}