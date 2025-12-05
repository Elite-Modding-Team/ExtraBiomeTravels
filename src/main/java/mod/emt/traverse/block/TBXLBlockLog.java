package mod.emt.traverse.block;

import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class TBXLBlockLog extends BlockLog {
    private final MapColor mapColor;
    public boolean flammable;

    public TBXLBlockLog(MapColor mapColor, boolean flammable) {
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        this.mapColor = mapColor;
        this.flammable = flammable;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(LOG_AXIS).ordinal();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        for (EnumAxis axis : EnumAxis.values()) {
            if (axis.ordinal() == meta) {
                return getDefaultState().withProperty(LOG_AXIS, axis);
            }
        }

        return getDefaultState();
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
        return mapColor;
    }

    public boolean isFlammable() {
        return flammable;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        if (isFlammable()) {
            return Blocks.LOG.getFlammability(world, pos, face);
        } else {
            return 0;
        }
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        if (isFlammable()) {
            return Blocks.LOG.getFireSpreadSpeed(world, pos, face);
        } else {
            return 0;
        }
    }
}
