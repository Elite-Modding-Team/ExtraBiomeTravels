package mod.emt.traverse.world.feature.tree;

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

public class TBXLWorldGenFirTree extends WorldGenAbstractTree {
    public final boolean isWorldGen;
    private final int minTreeHeight;
    private final IBlockState stateWood;
    private final IBlockState stateLeaves;

    public TBXLWorldGenFirTree(boolean isWorldGen) {
        this(isWorldGen, 12);
    }

    public TBXLWorldGenFirTree(boolean isWorldGen, int minTreeHeight) {
        this(isWorldGen, minTreeHeight, TBXLBlocks.white_fir_log.getDefaultState(), TBXLBlocks.white_fir_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
    }

    public TBXLWorldGenFirTree(boolean isWorldGen, int minTreeHeight, IBlockState stateWood, IBlockState stateLeaves) {
        super(!isWorldGen);
        this.isWorldGen = isWorldGen;
        this.minTreeHeight = minTreeHeight;
        this.stateWood = stateWood;
        this.stateLeaves = stateLeaves;
    }

    public boolean generate(@NotNull World world, Random rand, BlockPos position) {
        int treeHeight = rand.nextInt(15) + minTreeHeight;
        int nakedLogHeight = 3 + rand.nextInt(3);
        int leavesHeight = treeHeight - nakedLogHeight;
        int l = 2 + rand.nextInt(2);
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + treeHeight + 1 <= world.getHeight()) {
            for (int i1 = position.getY(); i1 <= position.getY() + 1 + treeHeight && flag; ++i1) {

                int j1;

                if (i1 - position.getY() < nakedLogHeight) {
                    j1 = 0;
                } else {
                    j1 = l;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int k1 = position.getX() - j1; k1 <= position.getX() + j1 && flag; ++k1) {
                    for (int l1 = position.getZ() - j1; l1 <= position.getZ() + j1 && flag; ++l1) {

                        if (i1 >= 0 && i1 < world.getHeight()) {
                            IBlockState state = world.getBlockState(blockpos$mutableblockpos.setPos(k1, i1, l1));

                            if (!state.getBlock().isAir(state, world, blockpos$mutableblockpos.setPos(k1, i1, l1)) && !state.getBlock().isLeaves(state, world, blockpos$mutableblockpos.setPos(k1, i1, l1))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                BlockPos down = position.down();
                IBlockState state = world.getBlockState(down);

                if (state.getBlock().canSustainPlant(state, world, down, EnumFacing.UP, (BlockSapling) Blocks.SAPLING) && position.getY() < world.getHeight() - treeHeight - 1) {
                    state.getBlock().onPlantGrow(state, world, down, position);
                    int i3 = rand.nextInt(2);
                    int j3 = 1;
                    int k3 = 0;

                    for (int l3 = 0; l3 <= leavesHeight; ++l3) {
                        int j4 = position.getY() + treeHeight - l3;

                        for (int i2 = position.getX() - i3; i2 <= position.getX() + i3; ++i2) {
                            int j2 = i2 - position.getX();

                            for (int k2 = position.getZ() - i3; k2 <= position.getZ() + i3; ++k2) {
                                int l2 = k2 - position.getZ();

                                if (Math.abs(j2) != i3 || Math.abs(l2) != i3 || i3 <= 0) {
                                    BlockPos blockpos = new BlockPos(i2, j4, k2);
                                    state = world.getBlockState(blockpos);

                                    if (state.getBlock().canBeReplacedByLeaves(state, world, blockpos)) {
                                        this.setBlockAndNotifyAdequately(world, blockpos, stateLeaves);
                                    }
                                }
                            }
                        }

                        if (i3 >= j3) {
                            i3 = k3;
                            k3 = 1;
                            ++j3;

                            if (j3 > l) {
                                j3 = l;
                            }
                        } else {
                            ++i3;
                        }
                    }

                    int i4 = rand.nextInt(3);

                    for (int k4 = 0; k4 < treeHeight - i4; ++k4) {
                        BlockPos upN = position.up(k4);
                        state = world.getBlockState(upN);

                        if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN)) {
                            this.setBlockAndNotifyAdequately(world, position.up(k4), stateWood);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}