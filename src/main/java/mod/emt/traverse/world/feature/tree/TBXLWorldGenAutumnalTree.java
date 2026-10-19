package mod.emt.traverse.world.feature.tree;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.jetbrains.annotations.NotNull;

// Majority of code is based on the large oak tree
public class TBXLWorldGenAutumnalTree extends WorldGenAbstractTree {
    public final boolean isWorldGen;
    private Random rand;
    private World world;
    private BlockPos basePos = BlockPos.ORIGIN;
    int heightLimit;
    int height;
    double heightAttenuation = 0.62D;
    double branchSlope = 0.20D;
    double scaleWidth = 1.0D;
    double leafDensity = 1.0D;
    int trunkSize = 1;
    int heightLimitLimit = 16;
    int leafDistanceLimit = 5;
    List<FoliageCoordinates> foliageCoords;

    private final IBlockState woodState;
    private final IBlockState leavesState;

    public TBXLWorldGenAutumnalTree(boolean isWorldGen, IBlockState woodState, IBlockState leavesState) {
        super(!isWorldGen);
        this.isWorldGen = isWorldGen;
        this.woodState = woodState;
        this.leavesState = leavesState;
    }

    void generateLeafNodeList() {
        this.height = (int) ((double) this.heightLimit * this.heightAttenuation);

        if (this.height >= this.heightLimit) {
            this.height = this.heightLimit - 1;
        }

        int nodeCount = (int) (1.382D + Math.pow(this.leafDensity * (double) this.heightLimit / 13.0D, 2.0D));

        if (nodeCount < 1) {
            nodeCount = 1;
        }

        int topY = this.basePos.getY() + this.height;
        int k = this.heightLimit - this.leafDistanceLimit;
        this.foliageCoords = Lists.newArrayList();
        this.foliageCoords.add(new FoliageCoordinates(this.basePos.up(k), topY));

        for (; k >= 0; --k) {
            float layerRadius = this.layerSize(k);

            if (layerRadius >= 0.0F) {
                for (int l = 0; l < nodeCount; ++l) {
                    double distance = this.scaleWidth * (double) layerRadius * ((double) this.rand.nextFloat() + 0.328D);
                    double angle = (double) (this.rand.nextFloat() * 2.0F) * Math.PI;
                    double offsetX = distance * Math.sin(angle) + 0.5D;
                    double offsetZ = distance * Math.cos(angle) + 0.5D;
                    BlockPos foliagePos = this.basePos.add(offsetX, k - 1, offsetZ);
                    BlockPos foliagePosTop = foliagePos.up(this.leafDistanceLimit);

                    if (this.checkBlockLine(foliagePos, foliagePosTop) == -1) {
                        BlockPos branchBase = getBlockPos(foliagePos, topY);

                        if (this.checkBlockLine(branchBase, foliagePos) == -1) {
                            this.foliageCoords.add(new FoliageCoordinates(foliagePos, branchBase.getY()));
                        }
                    }
                }
            }
        }
    }

    private @NotNull BlockPos getBlockPos(BlockPos foliagePos, int topY) {
        int dx = this.basePos.getX() - foliagePos.getX();
        int dz = this.basePos.getZ() - foliagePos.getZ();
        double branchY = (double) foliagePos.getY() - Math.sqrt(dx * dx + dz * dz) * this.branchSlope;
        int branchBaseY = branchY > (double) topY ? topY : (int) branchY;
        return new BlockPos(this.basePos.getX(), branchBaseY, this.basePos.getZ());
    }

    void crossSection(BlockPos pos, float radius, IBlockState blockState) {
        int i = (int) ((double) radius + 0.618D);
        for (int j = -i; j <= i; ++j) {
            for (int k = -i; k <= i; ++k) {
                if (Math.pow((double) Math.abs(j) + 0.5D, 2.0D) + Math.pow((double) Math.abs(k) + 0.5D, 2.0D) <= (double) (radius * radius)) {
                    BlockPos blockpos = pos.add(j, 0, k);
                    IBlockState state = this.world.getBlockState(blockpos);
                    if (state.getBlock().isAir(state, world, blockpos) || state.getBlock().isLeaves(state, world, blockpos)) {
                        this.setBlockAndNotifyAdequately(this.world, blockpos, blockState);
                    }
                }
            }
        }
    }

    float layerSize(int y) {
        if ((float) y < (float) this.heightLimit * 0.32F) {
            return -1.0F;
        }

        float f = (float) this.heightLimit / 2.0F;
        float f1 = f - (float) y;
        float f2 = MathHelper.sqrt(f * f - f1 * f1);

        if (f1 == 0.0F) {
            f2 = f;
        } else if (Math.abs(f1) >= f) {
            return 0.0F;
        }
        return f2 * 0.5F;
    }

    float leafSize(int y) {
        if (y >= 0 && y < this.leafDistanceLimit) {
            return y != 0 && y != this.leafDistanceLimit - 1 ? 3.0F : 2.0F;
        }
        return -1.0F;
    }

    void generateLeafNode(BlockPos pos) {
        for (int i = 0; i < this.leafDistanceLimit; ++i) {
            this.crossSection(pos.up(i), this.leafSize(i), this.leavesState);
        }
    }

    void limb(BlockPos pos, BlockPos pos2) {
        BlockPos delta = pos2.add(-pos.getX(), -pos.getY(), -pos.getZ());
        int distance = this.getGreatestDistance(delta);
        if (distance == 0) {
            return;
        }
        float xStep = (float) delta.getX() / (float) distance;
        float yStep = (float) delta.getY() / (float) distance;
        float zStep = (float) delta.getZ() / (float) distance;

        for (int j = 0; j <= distance; ++j) {
            BlockPos blockpos = pos.add(0.5F + (float) j * xStep, 0.5F + (float) j * yStep, 0.5F + (float) j * zStep);
            IBlockState current = this.world.getBlockState(blockpos);
            if (current.getBlock().isAir(current, this.world, blockpos) || current.getBlock().isLeaves(current, this.world, blockpos)) {
                BlockLog.EnumAxis axis = this.getLogAxis(pos, blockpos);
                this.setBlockAndNotifyAdequately(this.world, blockpos, this.woodState.withProperty(BlockLog.LOG_AXIS, axis));
            }
        }
    }

    private int getGreatestDistance(BlockPos pos) {
        int x = MathHelper.abs(pos.getX());
        int y = MathHelper.abs(pos.getY());
        int z = MathHelper.abs(pos.getZ());
        if (z > x && z > y) {
            return z;
        }
        return Math.max(y, x);
    }

    private BlockLog.EnumAxis getLogAxis(BlockPos pos, BlockPos pos2) {
        BlockLog.EnumAxis axis = BlockLog.EnumAxis.Y;
        int x = Math.abs(pos2.getX() - pos.getX());
        int z = Math.abs(pos2.getZ() - pos.getZ());
        int horizontalDistance = Math.max(x, z);
        if (horizontalDistance > 0) {
            if (x == horizontalDistance) {
                axis = BlockLog.EnumAxis.X;
            } else {
                axis = BlockLog.EnumAxis.Z;
            }
        }
        return axis;
    }

    void generateLeaves() {
        for (FoliageCoordinates foliage : this.foliageCoords) {
            this.generateLeafNode(foliage);
        }
    }

    boolean leafNodeNeedsBase(int node) {
        return (double) node >= (double) this.heightLimit * 0.20D;
    }

    void generateTrunk() {
        BlockPos trunkBase = this.basePos;
        BlockPos trunkTop = this.basePos.up(this.height);
        this.limb(trunkBase, trunkTop);
        if (this.trunkSize == 2) {
            this.limb(trunkBase.east(), trunkTop.east());
            this.limb(trunkBase.east().south(), trunkTop.east().south());
            this.limb(trunkBase.south(), trunkTop.south());
        }
    }

    void generateLeafNodeBases() {
        for (FoliageCoordinates foliage : this.foliageCoords) {
            int branchBaseY = foliage.getBranchBase();
            BlockPos branchBase = new BlockPos(this.basePos.getX(), branchBaseY, this.basePos.getZ());
            if (!branchBase.equals(foliage) && this.leafNodeNeedsBase(branchBaseY - this.basePos.getY())) {
                this.limb(branchBase, foliage);
            }
        }
    }

    int checkBlockLine(BlockPos posOne, BlockPos posTwo) {
        BlockPos delta = posTwo.add(-posOne.getX(), -posOne.getY(), -posOne.getZ());
        int distance = this.getGreatestDistance(delta);

        if (distance == 0) {
            return -1;
        }

        float xStep = (float) delta.getX() / (float) distance;
        float yStep = (float) delta.getY() / (float) distance;
        float zStep = (float) delta.getZ() / (float) distance;

        for (int j = 0; j <= distance; ++j) {
            BlockPos blockpos = posOne.add(0.5F + (float) j * xStep, 0.5F + (float) j * yStep, 0.5F + (float) j * zStep);
            IBlockState state = world.getBlockState(blockpos);
            if (!state.getBlock().isAir(state, world, blockpos) && !state.getBlock().isLeaves(state, world, blockpos)) {
                return j;
            }
        }

        return -1;
    }

    public void setDecorationDefaults() {
        this.leafDistanceLimit = 5;
    }

    public boolean generate(@NotNull World world, Random rand, @NotNull BlockPos position) {
        this.world = world;
        this.basePos = position;
        this.rand = new Random(rand.nextLong());
        this.heightLimit = 9 + this.rand.nextInt(this.heightLimitLimit);
        if (!this.validTreeLocation()) {
            this.world = null;
            return false;
        } else {
            BlockPos soilPos = this.basePos.down();
            IBlockState soilState = this.world.getBlockState(soilPos);
            soilState.getBlock().onPlantGrow(soilState, this.world, soilPos, this.basePos);
            this.generateLeafNodeList();
            this.generateLeaves();
            this.generateTrunk();
            this.generateLeafNodeBases();
            this.world = null;
            return true;
        }
    }

    private boolean validTreeLocation() {
        BlockPos down = this.basePos.down();
        IBlockState state = this.world.getBlockState(down);
        boolean isSoil = state.getBlock().canSustainPlant(state, this.world, down, EnumFacing.UP, ((BlockSapling) Blocks.SAPLING));
        if (!isSoil) {
            return false;
        } else {
            int radius = this.heightLimit;
            BlockPos min = this.basePos.add(-radius, 0, -radius);
            BlockPos max = this.basePos.add(radius, radius, radius);
            if (!this.world.isAreaLoaded(min, max)) {
                return false;
            }
            int i = this.checkBlockLine(this.basePos, this.basePos.up(this.heightLimit - 1));
            if (i == -1) {
                return true;
            } else if (i < 6) {
                return false;
            } else {
                this.heightLimit = i;
                return true;
            }
        }
    }

    static class FoliageCoordinates extends BlockPos {
        private final int branchBase;

        public FoliageCoordinates(BlockPos pos, int branch) {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.branchBase = branch;
        }

        public int getBranchBase() {
            return this.branchBase;
        }
    }
}