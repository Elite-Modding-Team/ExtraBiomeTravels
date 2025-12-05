package mod.emt.traverse.block;

import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.SoundType;

// NOTE: Wooden buttons are not flammable in vanilla
public class TBXLBlockButtonWood extends BlockButtonWood {
    public TBXLBlockButtonWood() {
        super();
        this.setSoundType(SoundType.WOOD);
        this.setHardness(0.5F);
        this.setHarvestLevel("axe", 0);
    }
}
