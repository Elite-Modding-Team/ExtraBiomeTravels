package mod.emt.traverse.world.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import mod.emt.traverse.init.TBXLBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.jetbrains.annotations.NotNull;

// Majority of code is based on the large oak tree
public class TBXLWorldGenRainbowEucalyptusTree extends WorldGenAbstractTree {
    private static final int BASE_HEIGHT = 19;
    private static final int BASE_HEIGHT_VARIANCE = 8;
    private static final double TRUNK_HEIGHT_PERCENT = 0.50D;
    private static final double TRUNK_BRANCHES_START = 0.18D;
    private static final int BRANCHES_BASE_NUMBER = 10;
    private static final int BRANCHES_EXTRA = 10;
    private static final int CANOPY_WIDTH = 12;
    private static final int CANOPY_WIDTH_VARIANCE = 3;
    private static final int CLUSTER_DIAMETER = 2;
    private static final int CLUSTER_DIAMETER_VARIANCE = 2;
    private static final int CLUSTER_HEIGHT = 2;
    private static final int CLUSTER_HEIGHT_VARIANCE = 1;

    private final IBlockState stateWood;
    private final IBlockState stateLeaves;

    public TBXLWorldGenRainbowEucalyptusTree(boolean isWorldGen) {
        super(!isWorldGen);
        this.stateWood = TBXLBlocks.rainbow_eucalyptus_log.getDefaultState();
        this.stateLeaves = TBXLBlocks.rainbow_eucalyptus_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);
    }

    @Override
    public boolean generate(World world, @NotNull Random rand, @NotNull BlockPos pos) {
        TreeGen gen = createTreeGen(pos, rand);

        if (!world.isAreaLoaded(pos.add(-gen.width - 2, 0, -gen.width - 2), pos.add(gen.width + 2, gen.height + 2, gen.width + 2))) {
            return false;
        }

        if (pos.getY() < 1 || pos.getY() + gen.height >= 256) {
            return false;
        }

        if (!canPlaceTree(world, gen)) {
            return false;
        }

        for (BlockPos woodPos : gen.wood) {
            IBlockState woodState = stateWood;

            if (gen.woodAxis.containsKey(woodPos)) {
                woodState = stateWood.withProperty(BlockLog.LOG_AXIS, gen.woodAxis.get(woodPos));
            }

            setBlockAndNotifyAdequately(world, woodPos, woodState);
        }

        for (BlockPos leafPos : gen.leaves) {
            if (!gen.woodSet.contains(leafPos)) {
                setBlockAndNotifyAdequately(world, leafPos, stateLeaves);
            }
        }

        return true;
    }

    private TreeGen createTreeGen(BlockPos pos, Random rand) {
        int height = BASE_HEIGHT + rand.nextInt(BASE_HEIGHT_VARIANCE);
        int width = CANOPY_WIDTH + rand.nextInt(CANOPY_WIDTH_VARIANCE);

        if (width % 2 != 0) {
            width++;
        }

        TreeGen gen = new TreeGen(height, width);
        int trunkHeight = (int) (height * TRUNK_HEIGHT_PERCENT);
        int trunkTop = trunkHeight - 1;

        for (int y = 0; y <= trunkTop; y++) {
            addWood(gen, pos.add(0, y, 0));
            addWood(gen, pos.add(1, y, 0));
            addWood(gen, pos.add(0, y, 1));
            addWood(gen, pos.add(1, y, 1));
        }

        generateKnees(gen, pos, rand);

        int branchStart = (int) (height * TRUNK_BRANCHES_START);
        int branchCount = BRANCHES_BASE_NUMBER + rand.nextInt(BRANCHES_EXTRA);
        int maxBranchHeight = height - branchStart - 3;
        int offset = width / 2;

        for (int branch = 0; branch < branchCount; branch++) {
            int endX = rand.nextInt(width + 1) - offset + pos.getX();
            int endZ = rand.nextInt(width + 1) - offset + pos.getZ();
            int endY = rand.nextInt(Math.max(maxBranchHeight, 1)) + trunkHeight + pos.getY();
            int startX;
            int startZ;

            if (endX > pos.getX() && endZ > pos.getZ()) {
                startX = pos.getX() + 1;
                startZ = pos.getZ() + 1;
            } else if (endX > pos.getX()) {
                startX = pos.getX() + 1;
                startZ = pos.getZ();
            } else if (endZ > pos.getZ()) {
                startX = pos.getX();
                startZ = pos.getZ() + 1;
            } else {
                startX = pos.getX();
                startZ = pos.getZ();
            }

            int minimumStartY = pos.getY() + branchStart;
            int maximumStartY = Math.min(pos.getY() + trunkTop, endY);
            int startY = minimumStartY;

            if (maximumStartY > minimumStartY) {
                startY += rand.nextInt(maximumStartY - minimumStartY + 1);
            }

            BlockPos start = new BlockPos(startX, startY, startZ);
            BlockPos end = new BlockPos(endX, endY, endZ);

            addWoodLine(gen, start, end);

            int clusterHeight = CLUSTER_HEIGHT + rand.nextInt(CLUSTER_HEIGHT_VARIANCE + 1);
            int clusterRadius = CLUSTER_DIAMETER + rand.nextInt(CLUSTER_DIAMETER_VARIANCE + 1);

            generateLeafCluster(gen, end, clusterRadius, clusterHeight);
        }

        BlockPos crown = pos.add(0, trunkHeight, 0);
        generateLeafCluster(gen, crown, 4, 3);
        pruneLeaves(gen);
        return gen;
    }

    private void generateLeafCluster(TreeGen gen, BlockPos center, int radius, int height) {
        for (int layer = -height; layer <= height; layer++) {
            double layerFactor = Math.cos(layer / (height / 1.3D));
            int layerRadius = Math.max(1, (int) Math.round(radius * layerFactor));

            for (int dx = -layerRadius; dx <= layerRadius; dx++) {
                for (int dz = -layerRadius; dz <= layerRadius; dz++) {
                    int distance = dx * dx + dz * dz;

                    if (distance > layerRadius * layerRadius) {
                        continue;
                    }

                    BlockPos leafPos = center.add(dx, layer, dz);

                    if (!gen.woodSet.contains(leafPos) && hasNearbyWood(gen, leafPos, 4)) {
                        addLeaves(gen, leafPos);
                    }
                }
            }
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

    private void generateKnees(TreeGen gen, BlockPos pos, Random rand) {
        switch (rand.nextInt(11)) {
            case 0:
            case 1:
            case 2:
            case 3:
                addKnee(gen, pos.getX() - 1, pos.getY(), pos.getZ(), rand);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                addKnee(gen, pos.getX() - 1, pos.getY(), pos.getZ() + 1, rand);
                break;
            case 8:
                addKnee(gen, pos.getX() - 1, pos.getY(), pos.getZ(), rand);
                addKnee(gen, pos.getX() - 1, pos.getY(), pos.getZ() + 1, rand);
                break;
            default:
                break;
        }

        switch (rand.nextInt(11)) {
            case 0:
            case 1:
            case 2:
            case 3:
                addKnee(gen, pos.getX(), pos.getY(), pos.getZ() - 1, rand);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                addKnee(gen, pos.getX() + 1, pos.getY(), pos.getZ() - 1, rand);
                break;
            case 8:
                addKnee(gen, pos.getX(), pos.getY(), pos.getZ() - 1, rand);
                addKnee(gen, pos.getX() + 1, pos.getY(), pos.getZ() - 1, rand);
                break;
            default:
                break;
        }

        switch (rand.nextInt(11)) {
            case 0:
            case 1:
            case 2:
            case 3:
                addKnee(gen, pos.getX() + 2, pos.getY(), pos.getZ(), rand);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                addKnee(gen, pos.getX() + 2, pos.getY(), pos.getZ() + 1, rand);
                break;
            case 8:
                addKnee(gen, pos.getX() + 2, pos.getY(), pos.getZ(), rand);
                addKnee(gen, pos.getX() + 2, pos.getY(), pos.getZ() + 1, rand);
                break;
            default:
                break;
        }

        switch (rand.nextInt(11)) {
            case 0:
            case 1:
            case 2:
            case 3:
                addKnee(gen, pos.getX(), pos.getY(), pos.getZ() + 2, rand);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                addKnee(gen, pos.getX() + 1, pos.getY(), pos.getZ() + 2, rand);
                break;
            case 8:
                addKnee(gen, pos.getX(), pos.getY(), pos.getZ() + 2, rand);
                addKnee(gen, pos.getX() + 1, pos.getY(), pos.getZ() + 2, rand);
                break;
            default:
                break;
        }
    }

    private void addKnee(TreeGen gen, int x, int y, int z, Random rand) {
        int height = rand.nextInt(3) != 0 ? 1 : 2;
        for (int i = 0; i < height; i++) {
            addWood(gen, new BlockPos(x, y + i, z));
        }
    }

    private void addWoodLine(TreeGen gen, BlockPos start, BlockPos end) {
        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();
        int dz = end.getZ() - start.getZ();
        int length = Math.max(Math.abs(dx), Math.max(Math.abs(dy), Math.abs(dz)));

        if (length == 0) {
            addWood(gen, start);
            return;
        }

        BlockLog.EnumAxis axis;

        if (Math.abs(dy) >= Math.max(Math.abs(dx), Math.abs(dz))) {
            axis = BlockLog.EnumAxis.Y;
        } else if (Math.abs(dx) >= Math.abs(dz)) {
            axis = BlockLog.EnumAxis.X;
        } else {
            axis = BlockLog.EnumAxis.Z;
        }

        for (int i = 0; i <= length; i++) {
            int x = start.getX() + Math.round(dx * i / (float) length);
            int y = start.getY() + Math.round(dy * i / (float) length);
            int z = start.getZ() + Math.round(dz * i / (float) length);

            BlockPos woodPos = new BlockPos(x, y, z);

            if (gen.woodSet.add(woodPos)) {
                gen.wood.add(woodPos);
                gen.woodAxis.put(woodPos, axis);
            }
        }
    }

    private void addWood(TreeGen gen, BlockPos pos) {
        if (gen.woodSet.add(pos)) {
            gen.wood.add(pos);
        }
    }

    private boolean canPlaceTree(World world, TreeGen gen) {
        for (BlockPos pos : gen.wood) {
            if (!isReplaceable(world, pos)) {
                return false;
            }
        }

        for (BlockPos pos : gen.leaves) {
            if (!isReplaceable(world, pos)) {
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
        private final Map<BlockPos, BlockLog.EnumAxis> woodAxis = new HashMap<>();

        private TreeGen(int height, int width) {
            this.height = height;
            this.width = width;
        }
    }
}