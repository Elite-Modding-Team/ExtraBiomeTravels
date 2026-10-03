package mod.emt.traverse.block;

import mod.emt.traverse.TraverseBiomesXL;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("deprecation")
public class TBXLBlockMiniLog extends BlockLog {
    private final MapColor mapColor;
    public boolean flammable;

    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");

    public TBXLBlockMiniLog(String unlocName, MapColor mapColor, float hardness, boolean flammable) {
        this.setRegistryName(TraverseBiomesXL.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(TraverseBiomesXL.TAB);
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(LOG_AXIS, EnumAxis.Y)
                .withProperty(NORTH, false)
                .withProperty(SOUTH, false)
                .withProperty(EAST, false)
                .withProperty(WEST, false)
                .withProperty(UP, false)
                .withProperty(DOWN, false));
        this.setHardness(hardness);
        this.mapColor = mapColor;
        this.flammable = flammable;
    }

    @Override
    protected @NotNull BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS, NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(LOG_AXIS).ordinal();
    }

    @Override
    public boolean isOpaqueCube(@NotNull IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(@NotNull IBlockState state) {
        return false;
    }

    @Override
    public boolean isPassable(@NotNull IBlockAccess world, @NotNull BlockPos pos) {
        return false;
    }

    @Override
    public @NotNull BlockFaceShape getBlockFaceShape(@NotNull IBlockAccess world, @NotNull IBlockState state, @NotNull BlockPos pos, @NotNull EnumFacing face) {
        return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.CENTER;
    }

    @Override
    public @NotNull IBlockState getStateFromMeta(int meta) {
        for (EnumAxis axis : EnumAxis.values()) {
            if (axis.ordinal() == meta) {
                return getDefaultState().withProperty(LOG_AXIS, axis);
            }
        }
        return getDefaultState();
    }

    @Override
    public @NotNull IBlockState getActualState(IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos) {
        EnumAxis axis = state.getValue(LOG_AXIS);
        boolean north = false;
        boolean south = false;
        boolean east = false;
        boolean west = false;
        boolean up = false;
        boolean down = false;

        switch (axis) {
            case X:
                north = canConnect(world, pos.north(), EnumFacing.NORTH);
                south = canConnect(world, pos.south(), EnumFacing.SOUTH);
                up = canConnect(world, pos.up(), EnumFacing.UP);
                down = canConnect(world, pos.down(), EnumFacing.DOWN);
                break;
            case Z:
                east = canConnect(world, pos.east(), EnumFacing.EAST);
                west = canConnect(world, pos.west(), EnumFacing.WEST);
                up = canConnect(world, pos.up(), EnumFacing.UP);
                down = canConnect(world, pos.down(), EnumFacing.DOWN);
                break;
            case NONE:
            default:
                north = canConnect(world, pos.north(), EnumFacing.NORTH);
                south = canConnect(world, pos.south(), EnumFacing.SOUTH);
                east = canConnect(world, pos.east(), EnumFacing.EAST);
                west = canConnect(world, pos.west(), EnumFacing.WEST);
                break;
        }

        return state.withProperty(NORTH, north)
                .withProperty(SOUTH, south)
                .withProperty(EAST, east)
                .withProperty(WEST, west)
                .withProperty(UP, up)
                .withProperty(DOWN, down);
    }

    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos) {
        state = getActualState(state, world, pos);
        EnumAxis axis = state.getValue(LOG_AXIS);

        switch (axis) {
            case X: {
                double minY = 3.0D / 16.0D;
                double maxY = 13.0D / 16.0D;
                double minZ = 3.0D / 16.0D;
                double maxZ = 13.0D / 16.0D;

                if (state.getValue(DOWN)) {
                    minY = 0.0D;
                }

                if (state.getValue(UP)) {
                    maxY = 1.0D;
                }

                if (state.getValue(NORTH)) {
                    minZ = 0.0D;
                }

                if (state.getValue(SOUTH)) {
                    maxZ = 1.0D;
                }

                return new AxisAlignedBB(0.0D, minY, minZ, 1.0D, maxY, maxZ);
            }

            case Z: {
                double minX = 3.0D / 16.0D;
                double maxX = 13.0D / 16.0D;
                double minY = 3.0D / 16.0D;
                double maxY = 13.0D / 16.0D;

                if (state.getValue(WEST)) {
                    minX = 0.0D;
                }

                if (state.getValue(EAST)) {
                    maxX = 1.0D;
                }

                if (state.getValue(DOWN)) {
                    minY = 0.0D;
                }

                if (state.getValue(UP)) {
                    maxY = 1.0D;
                }

                return new AxisAlignedBB(minX, minY, 0.0D, maxX, maxY, 1.0D);
            }

            case Y:
            case NONE:
            default: {
                double minX = 3.0D / 16.0D;
                double maxX = 13.0D / 16.0D;
                double minZ = 3.0D / 16.0D;
                double maxZ = 13.0D / 16.0D;

                if (state.getValue(NORTH)) {
                    minZ = 0.0D;
                }

                if (state.getValue(SOUTH)) {
                    maxZ = 1.0D;
                }

                if (state.getValue(WEST)) {
                    minX = 0.0D;
                }

                if (state.getValue(EAST)) {
                    maxX = 1.0D;
                }

                return new AxisAlignedBB(minX, 0.0D, minZ, maxX, 1.0D, maxZ);
            }
        }
    }

    private boolean canConnect(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock().canBeConnectedTo(world, pos, facing.getOpposite())) {
            return true;
        }

        if (state.getBlock() == this) {
            return true;
        }

        return state.getMaterial() == Material.LEAVES;
    }

    @Override
    public @NotNull MapColor getMapColor(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos) {
        return mapColor;
    }

    public boolean isFlammable() {
        return flammable;
    }

    @Override
    public int getFlammability(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EnumFacing face) {
        if (isFlammable()) {
            return Blocks.LOG.getFlammability(world, pos, face);
        } else {
            return 0;
        }
    }

    @Override
    public int getFireSpreadSpeed(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EnumFacing face) {
        if (isFlammable()) {
            return Blocks.LOG.getFireSpreadSpeed(world, pos, face);
        } else {
            return 0;
        }
    }
}
