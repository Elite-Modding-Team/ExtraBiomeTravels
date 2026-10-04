package mod.emt.traverse.world.feature;

import mod.emt.traverse.init.TBXLBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TBXLWorldGenCypressTree extends WorldGenAbstractTree {
    private static final int BASE_HEIGHT = 12;
    private static final int BASE_HEIGHT_VARIANCE = 6;
    private static final int CANOPY_START_HEIGHT = 0;
    private static final int CANOPY_START_VARIANCE = 4;
    private static final double CANOPY_RADIUS = 1.5D;
    private static final double CANOPY_RADIUS_VARIANCE = 1.0D;

    public final boolean isWorldGen;
    private final IBlockState stateWood;
    private final IBlockState stateLeaves;

    public TBXLWorldGenCypressTree(boolean isWorldGen) {
        this(isWorldGen, TBXLBlocks.cypress_log.getDefaultState(), TBXLBlocks.cypress_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
    }

    public TBXLWorldGenCypressTree(boolean isWorldGen, IBlockState stateWood, IBlockState stateLeaves) {
        super(!isWorldGen);
        this.isWorldGen = isWorldGen;
        this.stateWood = stateWood;
        this.stateLeaves = stateLeaves;
    }

    @Override
    public boolean generate(@NotNull World world, Random rand, @NotNull BlockPos position) {
        long seed = rand.nextLong();
        Random checkRand = new Random(seed);
        Random generateRand = new Random(seed);

        if (!checkTree(world, checkRand, position)) {
            return false;
        }

        return generateTree(world, generateRand, position);
    }

    private boolean checkTree(World world, Random rand, BlockPos position) {
        int height = rand.nextInt(BASE_HEIGHT_VARIANCE) + BASE_HEIGHT;
        int start = CANOPY_START_HEIGHT + (int) ((rand.nextDouble() * CANOPY_START_VARIANCE) - ((double) CANOPY_START_VARIANCE / 2));
        double radius = CANOPY_RADIUS + ((rand.nextDouble() * CANOPY_RADIUS_VARIANCE) + (CANOPY_RADIUS_VARIANCE / 2));
        double factor = 16.0D / (2 + height - start);
        int chunkCheck = (int) Math.ceil(radius) + 1;
        BlockPos down = position.down();
        IBlockState soil = world.getBlockState(down);

        if (!soil.getBlock().canSustainPlant(soil, world, down, EnumFacing.UP, (BlockSapling) Blocks.SAPLING)) {
            return false;
        }

        if (position.getY() >= world.getHeight() - height - 4) {
            return false;
        }

        if (position.getY() < 1 || position.getY() + height + 4 > world.getHeight()) {
            return false;
        }

        if (!world.isAreaLoaded(position.add(-chunkCheck, -chunkCheck, -chunkCheck), position.add(chunkCheck, chunkCheck, chunkCheck))) {
            return false;
        }

        if (!checkTrunk(world, position, height)) {
            return false;
        }

        for (int layer = 0; layer < 4 + height - start; ++layer) {
            double offset = factor * layer;
            double offset2 = offset * offset;
            double offset3 = offset2 * offset;
            double r1 = radius * ((0.00142D * offset3) - (0.0517D * offset2) + (0.5085D * offset) - 0.4611D);
            if (!checkLeavesCircle(world, position.getX(), position.getY() + start + layer, position.getZ(), r1)) {
                return false;
            }
        }
        return true;
    }

    private boolean generateTree(World world, Random rand, BlockPos position) {
        int height = rand.nextInt(BASE_HEIGHT_VARIANCE) + BASE_HEIGHT;
        int start = CANOPY_START_HEIGHT + (int) ((rand.nextDouble() * CANOPY_START_VARIANCE) - ((double) CANOPY_START_VARIANCE / 2));
        double radius = CANOPY_RADIUS + ((rand.nextDouble() * CANOPY_RADIUS_VARIANCE) + (CANOPY_RADIUS_VARIANCE / 2));
        double factor = 16.0D / (2 + height - start);

        if (!placeTrunk(world, position, height)) {
            return false;
        }

        for (int layer = 0; layer < 4 + height - start; ++layer) {
            double offset = factor * layer;
            double offset2 = offset * offset;
            double offset3 = offset2 * offset;
            double r1 = radius * ((0.00142D * offset3) - (0.0517D * offset2) + (0.5085D * offset) - 0.4611D);
            placeLeavesCircle(world, position.getX(), position.getY() + start + layer, position.getZ(), r1);
        }

        return true;
    }

    private boolean checkTrunk(World world, BlockPos position, int height) {
        for (int y = 0; y < height; ++y) {
            BlockPos pos = position.up(y);
            IBlockState state = world.getBlockState(pos);
            if (!state.getBlock().isAir(state, world, pos) && !state.getBlock().isLeaves(state, world, pos)) {
                return false;
            }
        }
        return true;
    }

    private boolean placeTrunk(World world, BlockPos position, int height) {
        for (int y = 0; y < height; ++y) {
            BlockPos pos = position.up(y);
            IBlockState state = world.getBlockState(pos);
            if (state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos)) {
                this.setBlockAndNotifyAdequately(world, pos, stateWood);
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean checkLeavesCircle(World world, int x, int y, int z, double radius) {
        int minX = (int) Math.floor(x - radius);
        int maxX = (int) Math.ceil(x + radius);
        int minZ = (int) Math.floor(z - radius);
        int maxZ = (int) Math.ceil(z + radius);
        for (int leafX = minX; leafX <= maxX; ++leafX) {
            for (int leafZ = minZ; leafZ <= maxZ; ++leafZ) {
                double dx = leafX - x;
                double dz = leafZ - z;
                if (dx * dx + dz * dz <= radius * radius) {
                    BlockPos pos = new BlockPos(leafX, y, leafZ);
                    IBlockState state = world.getBlockState(pos);
                    if (!state.getBlock().isAir(state, world, pos) && !state.getBlock().isLeaves(state, world, pos)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void placeLeavesCircle(World world, int x, int y, int z, double radius) {
        int minX = (int) Math.floor(x - radius);
        int maxX = (int) Math.ceil(x + radius);
        int minZ = (int) Math.floor(z - radius);
        int maxZ = (int) Math.ceil(z + radius);
        for (int leafX = minX; leafX <= maxX; ++leafX) {
            for (int leafZ = minZ; leafZ <= maxZ; ++leafZ) {
                double dx = leafX - x;
                double dz = leafZ - z;
                if (dx * dx + dz * dz <= radius * radius) {
                    BlockPos pos = new BlockPos(leafX, y, leafZ);
                    IBlockState state = world.getBlockState(pos);
                    if (state.getBlock().canBeReplacedByLeaves(state, world, pos)) {
                        this.setBlockAndNotifyAdequately(world, pos, stateLeaves);
                    }
                }
            }
        }
    }
}