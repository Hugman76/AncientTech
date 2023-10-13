package fr.hugman.ancient_tech.echea;

import dev.onyxstudios.cca.api.v3.component.Component;
import fr.hugman.ancient_tech.AncientTechComponents;
import fr.hugman.ancient_tech.util.UncappedStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class IncorporealEchea implements Component {
	private static final String CONTENT_TAG = "Content";

	private final List<ItemStack> stacks;

	//TODO: add modules (crafting, brewing, etc...)
	//TODO: add owners
	public IncorporealEchea() {
		this.stacks = new ArrayList<>();
	}

	public List<ItemStack> content() {
		return stacks;
	}

	public static IncorporealEchea get(World world) {
		return AncientTechComponents.INCORPOREAL_ECHEA.get(world.getLevelProperties());
	}

	@Override
	public void readFromNbt(NbtCompound nbt) {
		var nbtList = nbt.getList(CONTENT_TAG, NbtElement.COMPOUND_TYPE);
		this.stacks.clear();
		for(int i = 0; i < nbtList.size(); ++i) {
			ItemStack stack = UncappedStackHelper.fromNbt(nbtList.getCompound(i));
			if(!stack.isEmpty()) {
				this.stacks.add(stack);
			}
		}
	}

	@Override
	public void writeToNbt(@NotNull NbtCompound nbt) {
		NbtList nbtList = new NbtList();
		for(var stack : this.stacks) {
			nbtList.add(UncappedStackHelper.toNbt(stack));
		}
		nbt.put(CONTENT_TAG, nbtList);
	}

	public boolean add(ItemStack stack) {
		// Add to another stack if the item+nbt is the same
		var opt = this.stacks.stream().filter(itemStack -> ItemStack.canCombine(stack, itemStack)).findFirst();
		if(opt.isPresent()) {
			opt.get().increment(stack.getCount());
			return true;
		}

		return this.stacks.add(stack);
	}

	public ItemStack pickMax(int i) {
		var stack = UncappedStackHelper.getMaxed(this.stacks.get(i));
		this.refresh();
		return stack;
	}

	public void refresh() {
		this.stacks.removeIf(ItemStack::isEmpty);
	}

	public int maxSize() {
		// starts at 16
		// TODO: add size modules (2 slots with x2, x4, x8, x16, x32, x64) for a total of 65536 slots
		return 16;
	}

	public int size() {
		return this.stacks.size();
	}

	public boolean isEmpty() {
		return this.stacks.isEmpty();
	}
}
