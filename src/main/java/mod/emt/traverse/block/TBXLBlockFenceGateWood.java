package mod.emt.traverse.block;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class TBXLBlockFenceGateWood extends BlockFenceGate {
    private final MapColor mapColor;
    public boolean flammable;

    public TBXLBlockFenceGateWood(MapColor mapColor, boolean flammable) {
        super(EnumType.OAK);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setHarvestLevel("axe", 0);
        this.mapColor = mapColor;
        this.flammable = flammable;
    }

    public boolean isFlammable() {
        return flammable;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        if (isFlammable()) {
            return Blocks.PLANKS.getFlammability(world, pos, face);
        } else {
            return 0;
        }
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        if (isFlammable()) {
            return Blocks.PLANKS.getFireSpreadSpeed(world, pos, face);
        } else {
            return 0;
        }
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
        return mapColor;
    }
}
