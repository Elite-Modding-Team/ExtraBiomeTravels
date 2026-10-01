package mod.emt.traverse.util;

import mod.emt.traverse.init.TBXLItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

public class TBXLCreativeTab extends CreativeTabs {
    public TBXLCreativeTab(int length, String name) {
        super(length, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public @NotNull ItemStack createIcon() {
        return new ItemStack(TBXLItems.fir_door);
    }
}
