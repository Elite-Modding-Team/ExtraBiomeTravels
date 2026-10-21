package mod.emt.ebt.util;

import mod.emt.ebt.init.EBTBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

public class EBTCreativeTab extends CreativeTabs {
    public EBTCreativeTab(int length, String name) {
        super(length, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public @NotNull ItemStack createIcon() {
        return new ItemStack(EBTBlocks.cypress_sapling);
    }
}
