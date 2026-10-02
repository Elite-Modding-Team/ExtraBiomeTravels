package mod.emt.traverse.item;

import mod.emt.traverse.TraverseBiomesXL;
import net.minecraft.block.Block;
import net.minecraft.item.ItemDoor;

import java.util.Objects;

public class TBXLItemDoor extends ItemDoor {
    public TBXLItemDoor(String unlocName, Block block) {
        super(block);
        this.setRegistryName(TraverseBiomesXL.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(TraverseBiomesXL.TAB);
    }
}
