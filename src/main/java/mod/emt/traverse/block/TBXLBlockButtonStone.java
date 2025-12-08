package mod.emt.traverse.block;

import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.SoundType;

public class TBXLBlockButtonStone extends BlockButtonStone {
    public TBXLBlockButtonStone() {
        super();
        this.setSoundType(SoundType.STONE);
        this.setHardness(0.5F);
        this.setHarvestLevel("pickaxe", 0);
    }
}
