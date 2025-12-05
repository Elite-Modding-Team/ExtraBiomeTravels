package mod.emt.traverse.world.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class TBXLWorldGenTallTree extends WorldGenAbstractTree {
    private IBlockState stateWood;
    private IBlockState stateLeaves;
    private final boolean useExtraRandomHeight;

    public TBXLWorldGenTallTree(boolean notify, boolean useExtraRandomHeight, IBlockState stateWood, IBlockState stateLeaves) {
        super(notify);
        this.useExtraRandomHeight = useExtraRandomHeight;
        this.stateLeaves = stateLeaves;
        this.stateWood = stateWood;
    }

    public boolean generate(World world, Random rand, BlockPos position) {
        int i = rand.nextInt(3) + 5;

        if (this.useExtraRandomHeight) {
            i += rand.nextInt(7);
        }

        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 <= 256) {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
                int k = 1;

                if (j == position.getY()) {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {

                        if (j >= 0 && j < world.getHeight()) {
                            if (!this.isReplaceable(world, pos.setPos(l, j, i1))) {
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
                boolean isSoil = state.getBlock().canSustainPlant(state, world, down, net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling) Blocks.SAPLING);

                if (isSoil && position.getY() < world.getHeight() - i - 1) {
                    state.getBlock().onPlantGrow(state, world, down, position);

                    for (int i2 = position.getY() - 3 + i; i2 <= position.getY() + i; ++i2) {
                        int k2 = i2 - (position.getY() + i);
                        int l2 = 1 - k2 / 2;

                        for (int i3 = position.getX() - l2; i3 <= position.getX() + l2; ++i3) {
                            int j1 = i3 - position.getX();

                            for (int k1 = position.getZ() - l2; k1 <= position.getZ() + l2; ++k1) {
                                int l1 = k1 - position.getZ();

                                if (Math.abs(j1) != l2 || Math.abs(l1) != l2 || rand.nextInt(2) != 0 && k2 != 0) {
                                    BlockPos blockpos = new BlockPos(i3, i2, k1);
                                    IBlockState state2 = world.getBlockState(blockpos);

                                    if (state2.getBlock().isAir(state2, world, blockpos) || state2.getBlock().isAir(state2, world, blockpos)) {
                                        this.setBlockAndNotifyAdequately(world, blockpos, stateLeaves);
                                    }
                                }
                            }
                        }
                    }

                    for (int j2 = 0; j2 < i; ++j2) {
                        BlockPos upN = position.up(j2);
                        IBlockState state2 = world.getBlockState(upN);

                        if (state2.getBlock().isAir(state2, world, upN) || state2.getBlock().isLeaves(state2, world, upN)) {
                            this.setBlockAndNotifyAdequately(world, position.up(j2), stateWood);
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