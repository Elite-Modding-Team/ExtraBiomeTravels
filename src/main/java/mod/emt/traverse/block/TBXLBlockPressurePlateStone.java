package mod.emt.traverse.block;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

// NOTE: Wooden pressure plates are not flammable in vanilla
public class TBXLBlockPressurePlateStone extends BlockPressurePlate {
    private final MapColor mapColor;

    public TBXLBlockPressurePlateStone(MapColor mapColor) {
        super(Material.ROCK, Sensitivity.MOBS);
        this.setSoundType(SoundType.STONE);
        this.setHardness(0.5F);
        this.setHarvestLevel("pickaxe", 0);
        this.mapColor = mapColor;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
        return mapColor;
    }
}
