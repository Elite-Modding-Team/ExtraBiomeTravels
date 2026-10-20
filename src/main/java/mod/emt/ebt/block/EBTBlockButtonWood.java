package mod.emt.ebt.block;

import mod.emt.ebt.ExtraBiomeTravels;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.SoundType;

import java.util.Objects;

// NOTE: Wooden buttons are not flammable in vanilla
public class EBTBlockButtonWood extends BlockButtonWood {
    public EBTBlockButtonWood(String unlocName) {
        super();
        this.setRegistryName(ExtraBiomeTravels.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(ExtraBiomeTravels.TAB);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(0.5F);
        this.setHarvestLevel("axe", 0);
    }
}
