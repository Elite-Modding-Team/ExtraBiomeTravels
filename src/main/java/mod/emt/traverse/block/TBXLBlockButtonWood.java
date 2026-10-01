package mod.emt.traverse.block;

import mod.emt.traverse.TraverseBiomesXL;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.SoundType;

import java.util.Objects;

// NOTE: Wooden buttons are not flammable in vanilla
public class TBXLBlockButtonWood extends BlockButtonWood {
    public TBXLBlockButtonWood(String unlocName) {
        super();
        this.setRegistryName(TraverseBiomesXL.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(TraverseBiomesXL.TAB);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(0.5F);
        this.setHarvestLevel("axe", 0);
    }
}
