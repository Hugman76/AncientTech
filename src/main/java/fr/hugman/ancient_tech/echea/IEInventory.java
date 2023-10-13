package fr.hugman.ancient_tech.echea;

import dev.onyxstudios.cca.api.v3.component.Component;
import fr.hugman.ancient_tech.util.UncappedStackHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.collection.DefaultedList;

public class IEInventory implements Component {
	private static final String CONTENT_TAG = "Content";

	public final DefaultedList<ItemStack> stacks;

	public IEInventory(ItemStack... items) {
		this.stacks = DefaultedList.copyOf(ItemStack.EMPTY, items);
	}

	@Override
	public void readFromNbt(NbtCompound nbt) {
		NbtList nbtList = nbt.getList(CONTENT_TAG, NbtElement.COMPOUND_TYPE);
		this.stacks.clear();
		for(int i = 0; i < nbtList.size(); ++i) {
			ItemStack stack = UncappedStackHelper.fromNbt(nbtList.getCompound(i));
			if(!stack.isEmpty()) {
				this.stacks.add(stack);
			}
		}
	}

	@Override
	public void writeToNbt(NbtCompound nbt) {
		NbtList nbtList = new NbtList();
		for(var stack : this.stacks) {
			nbtList.add(UncappedStackHelper.toNbt(stack));
		}
		nbt.put(CONTENT_TAG, nbtList);
	}

	public int size() {
		return 0;
	}

	public boolean isEmpty() {
		return this.stacks.stream().allMatch(ItemStack::isEmpty);
	}
}
