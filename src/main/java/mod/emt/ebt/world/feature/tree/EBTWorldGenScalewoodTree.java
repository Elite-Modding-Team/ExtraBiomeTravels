
package mod.emt.ebt.world.feature.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import mod.emt.ebt.init.EBTBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockVine;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.jetbrains.annotations.NotNull;

public class EBTWorldGenScalewoodTree extends WorldGenAbstractTree {
    private static final int BASE_HEIGHT = 26;
    private static final int HEIGHT_VARIANCE = 23;
    private static final int TRUNK_HEIGHT = 4;
    private static final int BRANCH_START_PERCENT = 50;
    private static final int BRANCH_END_OFFSET = 6;
    private static final int BRANCH_LENGTH = 9;
    private static final int BRANCH_CHANCE = 4;
    private static final int BRANCH_DIRECTIONS = 8;
    private static final int LEAF_RADIUS = 3;

    private final boolean generateLeavesVines;
    private final boolean generateTrunkVines;
    private final IBlockState stateWood;
    private final IBlockState stateLeaves;

    public EBTWorldGenScalewoodTree(boolean isWorldGen, boolean generateTrunkVines, boolean generateLeavesVines) {
        super(!isWorldGen);
        this.generateLeavesVines = generateLeavesVines;
        this.generateTrunkVines = generateTrunkVines;
        this.stateWood = EBTBlocks.scalewood_log.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
        this.stateLeaves = EBTBlocks.scalewood_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);
    }

    @Override
    public boolean generate(World world, @NotNull Random rand, @NotNull BlockPos pos) {
        TreeGen gen = createTreeGen(pos, rand);

        if (!world.isAreaLoaded(pos.add(-gen.width - 2, 0, -gen.width - 2), pos.add(gen.width + 2, gen.height + BRANCH_LENGTH + 2, gen.width + 2))) {
            return false;
        }

        if (pos.getY() < 1 || pos.getY() + gen.height + BRANCH_LENGTH >= 256) {
            return false;
        }

        if (!canPlaceTree(world, gen)) {
            return false;
        }

        BlockPos soilPos = pos.down();
        BlockPos eastSoilPos = soilPos.east();
        BlockPos southSoilPos = soilPos.south();
        BlockPos southEastSoilPos = soilPos.south().east();

        if (!canSustainPlant(world, soilPos) || !canSustainPlant(world, eastSoilPos) || !canSustainPlant(world, southSoilPos) || !canSustainPlant(world, southEastSoilPos)) {
            return false;
        }

        onPlantGrow(world, soilPos, pos);
        onPlantGrow(world, eastSoilPos, pos);
        onPlantGrow(world, southSoilPos, pos);
        onPlantGrow(world, southEastSoilPos, pos);

        for (BlockPos woodPos : gen.wood) {
            setBlockAndNotifyAdequately(world, woodPos, stateWood);
        }

        if (generateTrunkVines) {
            generateTrunkVines(world, rand, pos, gen);
        }

        for (BlockPos leafPos : gen.leaves) {
            if (!gen.woodSet.contains(leafPos)) {
                setBlockAndNotifyAdequately(world, leafPos, stateLeaves);
            }
        }

        if (generateLeavesVines) {
            generateVines(world, rand, gen);
        }

        return true;
    }

    private boolean canSustainPlant(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().canSustainPlant(state, world, pos, EnumFacing.UP, (BlockSapling) Blocks.SAPLING);
    }

    private void onPlantGrow(World world, BlockPos pos, BlockPos source) {
        IBlockState state = world.getBlockState(pos);
        state.getBlock().onPlantGrow(state, world, pos, source);
    }

    private TreeGen createTreeGen(BlockPos pos, Random rand) {
        int height = BASE_HEIGHT + rand.nextInt(HEIGHT_VARIANCE);
        TreeGen gen = new TreeGen(height, BRANCH_LENGTH + LEAF_RADIUS + 1);
        int trunkHeight = Math.max(height - TRUNK_HEIGHT, 1);

        for (int y = 0; y < trunkHeight; y++) {
            addWood(gen, pos.add(0, y, 0));
            addWood(gen, pos.add(1, y, 0));
            addWood(gen, pos.add(0, y, 1));
            addWood(gen, pos.add(1, y, 1));
        }

        int branchStart = height * BRANCH_START_PERCENT / 100;
        int branchEnd = height - BRANCH_END_OFFSET;

        for (int y = branchStart; y <= branchEnd; y++) {
            BlockPos layerPos = pos.add(0, y, 0);

            for (int direction = 0; direction < BRANCH_DIRECTIONS; direction++) {
                if (rand.nextInt(BRANCH_CHANCE) != 0) {
                    continue;
                }

                generateBranch(gen, layerPos, direction, rand);
            }
        }

        pruneLeaves(gen);
        return gen;
    }

    private void generateBranch(TreeGen gen, BlockPos start, int direction, Random rand) {
        int x = start.getX();
        int y = start.getY();
        int z = start.getZ();
        int xDirection = 0;
        int zDirection = 0;

        switch (direction) {
            case 0:
                xDirection = -1;
                break;
            case 1:
                xDirection = 1;
                break;
            case 2:
                zDirection = -1;
                break;
            case 3:
                zDirection = 1;
                break;
            case 4:
                xDirection = -1;
                zDirection = 1;
                break;
            case 5:
                xDirection = -1;
                zDirection = -1;
                break;
            case 6:
                xDirection = 1;
                zDirection = 1;
                break;
            case 7:
                xDirection = 1;
                zDirection = -1;
                break;
            default:
                break;
        }

        for (int length = 0; length < BRANCH_LENGTH; length++) {
            if (xDirection < 0 && rand.nextInt(3) == 0) {
                x--;
            } else if (xDirection > 0 && rand.nextInt(3) == 0) {
                x++;
            }

            if (zDirection < 0 && rand.nextInt(3) == 0) {
                z--;
            } else if (zDirection > 0 && rand.nextInt(3) == 0) {
                z++;
            }

            BlockPos branchPos = new BlockPos(x, y, z);
            addWood(gen, branchPos);

            if (length == BRANCH_LENGTH - 1 || rand.nextInt(6) == 0) {
                generateLeaves(gen, branchPos);
            }

            y++;
        }
    }

    private void generateLeaves(TreeGen gen, BlockPos center) {
        for (int dx = -LEAF_RADIUS; dx <= LEAF_RADIUS; dx++) {
            for (int dz = -LEAF_RADIUS; dz <= LEAF_RADIUS; dz++) {
                if ((Math.abs(dx) == LEAF_RADIUS && Math.abs(dz) == LEAF_RADIUS) || (Math.abs(dx) == LEAF_RADIUS && Math.abs(dz) == LEAF_RADIUS - 1) || (Math.abs(dx) == LEAF_RADIUS - 1 && Math.abs(dz) == LEAF_RADIUS)) {
                    continue;
                }
                addLeafIfSupported(gen, center.add(dx, 0, dz));
            }
        }

        for (int dx = -LEAF_RADIUS + 1; dx <= LEAF_RADIUS - 1; dx++) {
            for (int dz = -LEAF_RADIUS + 1; dz <= LEAF_RADIUS - 1; dz++) {
                if (Math.abs(dx) == LEAF_RADIUS - 1 && Math.abs(dz) == LEAF_RADIUS - 1) {
                    continue;
                }
                addLeafIfSupported(gen, center.add(dx, 1, dz));
                addLeafIfSupported(gen, center.add(dx, -1, dz));
            }
        }
    }

    private void addLeafIfSupported(TreeGen gen, BlockPos pos) {
        if (!gen.woodSet.contains(pos) && hasNearbyWood(gen, pos, 4)) {
            addLeaves(gen, pos);
        }
    }

    private void pruneLeaves(TreeGen gen) {
        gen.leaves.removeIf(leafPos -> !hasNearbyWood(gen, leafPos, 4));
        gen.leafSet.retainAll(gen.leaves);
    }

    private boolean hasNearbyWood(TreeGen gen, BlockPos leaf, int range) {
        for (int dx = -range; dx <= range; dx++) {
            for (int dy = -range; dy <= range; dy++) {
                for (int dz = -range; dz <= range; dz++) {
                    if (Math.max(Math.abs(dx), Math.max(Math.abs(dy), Math.abs(dz))) > range) {
                        continue;
                    }
                    if (gen.woodSet.contains(leaf.add(dx, dy, dz))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void addLeaves(TreeGen gen, BlockPos pos) {
        if (gen.woodSet.contains(pos)) {
            return;
        }

        if (gen.leafSet.add(pos)) {
            gen.leaves.add(pos);
        }
    }

    private void addWood(TreeGen gen, BlockPos pos) {
        if (gen.woodSet.add(pos)) {
            gen.wood.add(pos);
        }
    }

    private void generateTrunkVines(World world, Random rand, BlockPos basePos, TreeGen gen) {
        int trunkHeight = Math.max(gen.height - TRUNK_HEIGHT, 1);

        for (int y = 1; y < trunkHeight; y++) {
            BlockPos vinePos = basePos.add(-1, y, 0);
            if (rand.nextInt(3) != 0 && world.isAirBlock(vinePos)) {
                placeVine(world, vinePos, BlockVine.EAST);
            }

            vinePos = basePos.add(-1, y, 1);
            if (rand.nextInt(3) != 0 && world.isAirBlock(vinePos)) {
                placeVine(world, vinePos, BlockVine.EAST);
            }

            vinePos = basePos.add(0, y, -1);
            if (rand.nextInt(3) != 0 && world.isAirBlock(vinePos)) {
                placeVine(world, vinePos, BlockVine.SOUTH);
            }

            vinePos = basePos.add(1, y, -1);
            if (rand.nextInt(3) != 0 && world.isAirBlock(vinePos)) {
                placeVine(world, vinePos, BlockVine.SOUTH);
            }

            vinePos = basePos.add(2, y, 0);
            if (rand.nextInt(3) != 0 && world.isAirBlock(vinePos)) {
                placeVine(world, vinePos, BlockVine.WEST);
            }

            vinePos = basePos.add(2, y, 1);
            if (rand.nextInt(3) != 0 && world.isAirBlock(vinePos)) {
                placeVine(world, vinePos, BlockVine.WEST);
            }

            vinePos = basePos.add(0, y, 2);
            if (rand.nextInt(3) != 0 && world.isAirBlock(vinePos)) {
                placeVine(world, vinePos, BlockVine.NORTH);
            }

            vinePos = basePos.add(1, y, 2);
            if (rand.nextInt(3) != 0 && world.isAirBlock(vinePos)) {
                placeVine(world, vinePos, BlockVine.NORTH);
            }
        }
    }

    private void generateVines(World world, Random rand, TreeGen gen) {
        for (BlockPos leafPos : gen.leaves) {
            BlockPos vinePos = leafPos.west();

            if (rand.nextInt(4) == 0 && world.isAirBlock(vinePos)) {
                growVines(world, vinePos, BlockVine.EAST);
            }

            vinePos = leafPos.east();

            if (rand.nextInt(4) == 0 && world.isAirBlock(vinePos)) {
                growVines(world, vinePos, BlockVine.WEST);
            }

            vinePos = leafPos.north();

            if (rand.nextInt(4) == 0 && world.isAirBlock(vinePos)) {
                growVines(world, vinePos, BlockVine.SOUTH);
            }

            vinePos = leafPos.south();

            if (rand.nextInt(4) == 0 && world.isAirBlock(vinePos)) {
                growVines(world, vinePos, BlockVine.NORTH);
            }
        }
    }

    private void growVines(World world, BlockPos pos, PropertyBool facing) {
        IBlockState vineState = Blocks.VINE.getDefaultState().withProperty(facing, true);
        setBlockAndNotifyAdequately(world, pos, vineState);

        int remaining = 4;
        BlockPos below = pos.down();

        while (remaining > 0 && world.isAirBlock(below)) {
            setBlockAndNotifyAdequately(world, below, vineState);
            below = below.down();
            remaining--;
        }
    }

    private void placeVine(World world, BlockPos pos, PropertyBool facing) {
        IBlockState vineState = Blocks.VINE.getDefaultState().withProperty(facing, true);
        setBlockAndNotifyAdequately(world, pos, vineState);
    }

    private boolean canPlaceTree(World world, TreeGen gen) {
        for (BlockPos pos : gen.wood) {
            IBlockState state = world.getBlockState(pos);

            if (state.getBlock() instanceof BlockLog || !isReplaceable(world, pos)) {
                return false;
            }
        }

        for (BlockPos pos : gen.leaves) {
            IBlockState state = world.getBlockState(pos);

            if (state.getBlock() instanceof BlockLog || !isReplaceable(world, pos)) {
                return false;
            }
        }

        return true;
    }

    private static class TreeGen {
        private final int height;
        private final int width;
        private final List<BlockPos> wood = new ArrayList<>();
        private final List<BlockPos> leaves = new ArrayList<>();
        private final Set<BlockPos> woodSet = new HashSet<>();
        private final Set<BlockPos> leafSet = new HashSet<>();

        private TreeGen(int height, int width) {
            this.height = height;
            this.width = width;
        }
    }
}