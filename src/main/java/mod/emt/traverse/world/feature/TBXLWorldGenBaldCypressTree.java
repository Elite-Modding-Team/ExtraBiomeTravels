package mod.emt.traverse.world.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import mod.emt.traverse.init.TBXLBlocks;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.jetbrains.annotations.NotNull;

public class TBXLWorldGenBaldCypressTree extends WorldGenAbstractTree {
    private static final int BASE_HEIGHT = 24;
    private static final int BASE_HEIGHT_VARIANCE = 10;
    private static final double TRUNK_HEIGHT_PERCENT = 0.75D;
    private static final double TRUNK_BRANCHES_START = 0.25D;
    private static final int BRANCHES_BASE_NUMBER = 15;
    private static final int BRANCHES_EXTRA = 10;
    private static final int CANOPY_WIDTH = 15;
    private static final int CANOPY_WIDTH_VARIANCE = 5;

    private final IBlockState stateWood;
    private final IBlockState stateLeaves;

    public TBXLWorldGenBaldCypressTree(boolean isWorldGen) {
        super(!isWorldGen);
        this.stateWood = TBXLBlocks.bald_cypress_log.getDefaultState();
        this.stateLeaves = TBXLBlocks.bald_cypress_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);
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
        int branchTrunkHeight = (int) (height * TRUNK_HEIGHT_PERCENT);
        int trunkTop = branchTrunkHeight - 1;

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

        for (int branch = 0; branch < branchCount; branch++) {
            double angle;

            if (branch < 8) {
                angle = (Math.PI * 2.0D / 8.0D) * branch;
                angle += (rand.nextDouble() - 0.5D) * (Math.PI / 5.0D);
            } else {
                angle = rand.nextDouble() * Math.PI * 2.0D;
            }

            double distance = 6.0D + rand.nextDouble() * (width / 2.0D - 6.0D);
            int endX = pos.getX() + (int) Math.round(Math.cos(angle) * distance);
            int endZ = pos.getZ() + (int) Math.round(Math.sin(angle) * distance);
            int endY = rand.nextInt(Math.max(maxBranchHeight, 1)) + branchStart + pos.getY();
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
            int startY = minimumStartY;

            if (endY > minimumStartY) {
                startY += rand.nextInt(Math.max(endY - minimumStartY, 1));
            }

            BlockPos start = new BlockPos(startX, startY, startZ);
            BlockPos end = new BlockPos(endX, endY, endZ);

            addWoodLine(gen, start, end);

            int clusterHeight = 4 + rand.nextInt(2);
            int clusterDiameter = 5 + rand.nextInt(2);
            generateLeafCluster(gen, end, clusterHeight, clusterDiameter, rand, false);
        }

        BlockPos crown = pos.add(0, branchTrunkHeight, 0);
        generateLeafCluster(gen, crown, 5 + rand.nextInt(2), 5 + rand.nextInt(2), rand, true);
        return gen;
    }

    private void generateLeafCluster(TreeGen gen, BlockPos center, int clusterHeight, int clusterDiameter, Random rand, boolean crown) {
        if (crown) {
            addWood(gen, center.down());
            addWood(gen, center.down().east());
            addWood(gen, center.down().south());
            addWood(gen, center.down().east().south());
            addWood(gen, center.down(2));
            addWood(gen, center.down(2).east());
            addWood(gen, center.down(2).south());
            addWood(gen, center.down(2).east().south());
        } else {
            addWood(gen, center);
            addWood(gen, center.east());
            addWood(gen, center.west());
            addWood(gen, center.north());
            addWood(gen, center.south());
            addWood(gen, center.east().north());
            addWood(gen, center.east().south());
            addWood(gen, center.west().north());
            addWood(gen, center.west().south());
        }

        int radius = Math.max(2, clusterDiameter / 2);

        if (!crown) {
            radius = Math.max(radius, 3);
        }

        if (!crown) {
            clusterHeight = Math.max(clusterHeight, 4);
        }

        int bottom = -(clusterHeight / 2);
        int top = clusterHeight - (clusterHeight / 2) - 1;

        for (int dy = bottom; dy <= top; dy++) {
            int layerRadius = radius;

            if (dy == bottom || dy == top) {
                layerRadius = Math.max(2, radius - 1);
            }

            for (int dx = -layerRadius; dx <= layerRadius; dx++) {
                for (int dz = -layerRadius; dz <= layerRadius; dz++) {
                    int distance = dx * dx + dz * dz;

                    if (distance > layerRadius * layerRadius + 1) {
                        continue;
                    }

                    if (distance >= layerRadius * layerRadius && rand.nextInt(6) == 0) {
                        continue;
                    }

                    BlockPos leafPos = center.add(dx, dy, dz);
                    if (!gen.woodSet.contains(leafPos)) {
                        addLeaves(gen, leafPos);
                    }
                }
            }
        }
    }

    private void addLeaves(TreeGen gen, BlockPos pos) {
        if (gen.woodSet.contains(pos)) {
            return;
        }

        if (!hasNearbyWood(gen, pos, 4)) {
            return;
        }

        if (gen.leafSet.add(pos)) {
            gen.leaves.add(pos);
        }
    }

    private boolean hasNearbyWood(TreeGen gen, BlockPos leaf, int range) {
        for (int dx = -range; dx <= range; dx++) {
            for (int dy = -range; dy <= range; dy++) {
                for (int dz = -range; dz <= range; dz++) {
                    if (Math.abs(dx) + Math.abs(dy) + Math.abs(dz) > range) {
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

    private void generateKnees(TreeGen gen, BlockPos pos, Random rand) {
        if (rand.nextInt(11) < 8) {
            int height = 1 + rand.nextInt(2);
            int x = pos.getX() - 1;
            int z = pos.getZ() + rand.nextInt(2);
            for (int y = 0; y < height; y++) {
                addWood(gen, new BlockPos(x, pos.getY() + y, z));
            }
        }

        if (rand.nextInt(11) < 8) {
            int height = 1 + rand.nextInt(2);
            int x = pos.getX() + rand.nextInt(2);
            int z = pos.getZ() - 1;
            for (int y = 0; y < height; y++) {
                addWood(gen, new BlockPos(x, pos.getY() + y, z));
            }
        }

        if (rand.nextInt(11) < 8) {
            int height = 1 + rand.nextInt(2);
            int x = pos.getX() + 2;
            int z = pos.getZ() + rand.nextInt(2);
            for (int y = 0; y < height; y++) {
                addWood(gen, new BlockPos(x, pos.getY() + y, z));
            }
        }

        if (rand.nextInt(11) < 8) {
            int height = 1 + rand.nextInt(2);
            int x = pos.getX() + rand.nextInt(2);
            int z = pos.getZ() + 2;
            for (int y = 0; y < height; y++) {
                addWood(gen, new BlockPos(x, pos.getY() + y, z));
            }
        }
    }

    private void addWoodLine(TreeGen gen, BlockPos start, BlockPos end) {
        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();
        int dz = end.getZ() - start.getZ();
        int length = Math.max(Math.abs(dx), Math.max(Math.abs(dy), Math.abs(dz)));

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

            BlockPos pos = new BlockPos(x, y, z);

            if (gen.woodSet.add(pos)) {
                gen.wood.add(pos);
                gen.woodAxis.put(pos, axis);
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