package fr.hugman.ancient_tech.echea;

import dev.onyxstudios.cca.api.v3.component.Component;
import fr.hugman.ancient_tech.AncientTechComponents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;
import net.minecraft.world.WorldProperties;

import java.util.ArrayList;
import java.util.List;

public class IncorporealEchea implements Component {
	private static final String CONTENT_TAG = "Content";

	public final WorldProperties worldProperties;
	public final List<ItemStack> content;

	//TODO: add modules (crafting, brewing, etc...)
	//TODO: add owners
	public IncorporealEchea(WorldProperties worldProperties) {
		this.worldProperties = worldProperties;
		this.content = new ArrayList<>();
	}

	public List<ItemStack> content() {
		return content;
	}

	public static IncorporealEchea get(World world) {
		return AncientTechComponents.INCORPOREAL_ECHEA.get(world.getLevelProperties());
	}

	@Override
	public void readFromNbt(NbtCompound nbt) {
		var nbtList = nbt.getList(CONTENT_TAG, NbtElement.COMPOUND_TYPE);
		this.content.clear();
		for(int i = 0; i < nbtList.size(); ++i) {
			ItemStack stack = ItemStack.fromNbt(nbtList.getCompound(i));
			if(!stack.isEmpty()) {
				this.content.add(stack);
			}
		}
	}

	@Override
	public void writeToNbt(NbtCompound nbt) {
		NbtList nbtList = new NbtList();
		for(var stack : this.content) {
			nbtList.add(stack.writeNbt(new NbtCompound()));
		}
		nbt.put(CONTENT_TAG, nbtList);
	}
}
