package fr.hugman.ancient_tech;

import fr.hugman.ancient_tech.echea.IncorporealEcheaBrowserBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AncientTech implements ModInitializer {
	public static final String MOD_ID = "ancient_tech";
	public static final Logger LOGGER = LogManager.getLogger();

	public static final Block INCORPOREAL_ECHEA_BROWSER = AncientTech.block("incorporeal_echea_browser", new IncorporealEcheaBrowserBlock(FabricBlockSettings.copy(Blocks.BEDROCK)), ItemGroup.MISC);

	@Override
	public void onInitialize() {

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