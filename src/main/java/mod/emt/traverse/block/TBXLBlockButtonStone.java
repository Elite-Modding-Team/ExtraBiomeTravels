package mod.emt.traverse.block;

import mod.emt.traverse.TraverseBiomesXL;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.SoundType;

import java.util.Objects;

public class TBXLBlockButtonStone extends BlockButtonStone {
    public TBXLBlockButtonStone(String unlocName) {
        super();
        this.setRegistryName(TraverseBiomesXL.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(TraverseBiomesXL.TAB);
        this.setSoundType(SoundType.STONE);
        this.setHardness(0.5F);
        this.setHarvestLevel("pickaxe", 0);
    }
}
