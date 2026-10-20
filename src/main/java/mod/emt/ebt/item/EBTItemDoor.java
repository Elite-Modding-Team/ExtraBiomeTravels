package mod.emt.ebt.item;

import mod.emt.ebt.ExtraBiomeTravels;
import net.minecraft.block.Block;
import net.minecraft.item.ItemDoor;

import java.util.Objects;

public class EBTItemDoor extends ItemDoor {
    public EBTItemDoor(String unlocName, Block block) {
        super(block);
        this.setRegistryName(ExtraBiomeTravels.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(ExtraBiomeTravels.TAB);
    }
}
