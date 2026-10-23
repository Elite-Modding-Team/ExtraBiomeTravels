package mod.emt.ebt.block;

import net.minecraft.item.ItemStack;

public interface IBurnableFuel {
    int getBlockBurnTime(ItemStack itemStack);
}
