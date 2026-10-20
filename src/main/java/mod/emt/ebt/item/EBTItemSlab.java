package mod.emt.ebt.item;

import mod.emt.ebt.ExtraBiomeTravels;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;

import java.util.Objects;

public class EBTItemSlab extends ItemSlab {
    public EBTItemSlab(String unlocName, Block block, BlockSlab singleSlab, BlockSlab doubleSlab) {
        super(block, singleSlab, doubleSlab);
        this.setRegistryName(ExtraBiomeTravels.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
    }
}
