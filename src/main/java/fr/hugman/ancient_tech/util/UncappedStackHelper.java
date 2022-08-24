package fr.hugman.ancient_tech.util;

import fr.hugman.ancient_tech.AncientTech;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class UncappedStackHelper {
	private static final String ID_KEY = "id";
	private static final String COUNT_KEY = "count";
	private static final String TAG_KEY = "tag";

	public static ItemStack fromNbt(NbtCompound nbt) {
		try {
			var item = (Item) Registry.ITEM.get(new Identifier(nbt.getString(ID_KEY)));
			var count = nbt.getInt(COUNT_KEY);
			var stack = new ItemStack(item, count);
			if(nbt.contains("tag", NbtElement.COMPOUND_TYPE)) {
				stack.setNbt(nbt.getCompound("tag"));
			}
			return stack;
		} catch(RuntimeException var2) {
			AncientTech.LOGGER.debug("Tried to load invalid item: {}", nbt, var2);
			return ItemStack.EMPTY;
		}
	}

	public static NbtCompound toNbt(ItemStack stack) {
		var nbt = new NbtCompound();
		Identifier identifier = Registry.ITEM.getId(stack.getItem());
		nbt.putString(ID_KEY, identifier.toString());
		nbt.putInt(COUNT_KEY, stack.getCount());
		if(stack.getNbt() != null) {
			nbt.put(TAG_KEY, stack.getNbt().copy());
		}

		return nbt;
	}

	public static ItemStack getMaxed(ItemStack source) {
		int i = Math.min(source.getCount(), source.getMaxCount());
		var stack = source.copy();
		source.decrement(i);
		stack.setCount(i);
		return stack;
	}
}
