package mod.emt.traverse.block;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

// NOTE: Wooden trapdoors are not flammable in vanilla
public class TBXLBlockTrapdoorWood extends BlockTrapDoor {
    private final MapColor mapColor;

    public TBXLBlockTrapdoorWood(MapColor mapColor) {
        super(Material.WOOD);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(3.0F);
        this.setHarvestLevel("axe", 0);
        this.mapColor = mapColor;
        this.enableStats = false;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
        return mapColor;
    }
}
