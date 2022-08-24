package fr.hugman.ancient_tech.echea;

import dev.onyxstudios.cca.api.v3.component.Component;
import fr.hugman.ancient_tech.AncientTechComponents;
import fr.hugman.ancient_tech.util.UncappedStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;
import net.minecraft.world.WorldProperties;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IncorporealEchea implements Component, Iterable<ItemStack> {
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

	public boolean add(ItemStack stack) {
		// Add to another stack if the item is the same
		var opt = this.content.stream().filter(itemStack -> ItemStack.canCombine(stack, itemStack)).findFirst();
		if(opt.isPresent()) {
			opt.get().increment(stack.getCount());
			return true;
		}

		return this.content.add(stack);
	}

	public ItemStack fullPick(int i) {
		var stack = UncappedStackHelper.getMaxed(this.content.get(i));
		this.clean();
		return stack;
	}

	public void clean() {
		this.content.removeIf(ItemStack::isEmpty);
	}

	public boolean isEmpty() {
		return this.content.isEmpty();
	}

	@Override
	public void readFromNbt(NbtCompound nbt) {
		var nbtList = nbt.getList(CONTENT_TAG, NbtElement.COMPOUND_TYPE);
		this.content.clear();
		for(int i = 0; i < nbtList.size(); ++i) {
			ItemStack stack = UncappedStackHelper.fromNbt(nbtList.getCompound(i));
			if(!stack.isEmpty()) {
				this.content.add(stack);
			}
		}
	}

	@Override
	public void writeToNbt(@NotNull NbtCompound nbt) {
		NbtList nbtList = new NbtList();
		for(var stack : this.content) {
			nbtList.add(UncappedStackHelper.toNbt(stack));
		}
		nbt.put(CONTENT_TAG, nbtList);
	}

	@NotNull
	@Override
	public Iterator<ItemStack> iterator() {
		return this.content.listIterator();
	}

	public static IncorporealEchea get(World world) {
		return AncientTechComponents.INCORPOREAL_ECHEA.get(world.getLevelProperties());
	}
}
