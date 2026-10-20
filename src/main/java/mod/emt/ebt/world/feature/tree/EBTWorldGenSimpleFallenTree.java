package mod.emt.ebt.world.feature.tree;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EBTWorldGenSimpleFallenTree extends WorldGenAbstractTree {
    private final int minTreeLength;
    private IBlockState stateWood;

    public EBTWorldGenSimpleFallenTree(boolean isWorldGen) {
        this(isWorldGen, 3, Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK));
    }

    public EBTWorldGenSimpleFallenTree(boolean isWorldGen, int minTreeLength, IBlockState stateWood) {
        super(!isWorldGen);
        this.minTreeLength = minTreeLength;
        this.stateWood = stateWood;
    }

    public boolean generate(@NotNull World world, Random rand, @NotNull BlockPos position) {
        int num = rand.nextInt(5);
        EnumFacing orientation;

        if (num == 0) {
            orientation = EnumFacing.EAST;
            stateWood = stateWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X);
        } else if (num == 1) {
            orientation = EnumFacing.WEST;
            stateWood = stateWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X);
        } else if (num == 1) {
            orientation = EnumFacing.SOUTH;
            stateWood = stateWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z);
        } else {
            orientation = EnumFacing.NORTH;
            stateWood = stateWood.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z);
        }

        int i = rand.nextInt(2) + this.minTreeLength;
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 <= world.getHeight()) {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
                int k = 1;

                if (j == position.getY()) {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                        if (j >= 0 && j < world.getHeight()) {
                            if (!this.isReplaceable(world, mutablePos.setPos(l, j, i1))) {
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
                IBlockState state = world.getBlockState(position.down());

                if (state.getBlock().canSustainPlant(state, world, position.down(), EnumFacing.UP, (BlockSapling) Blocks.SAPLING) && position.getY() < world.getHeight() - i - 1) {
                    state.getBlock().onPlantGrow(state, world, position.down(), position);

                    for (int j3 = 0; j3 < i; ++j3) {
                        BlockPos offsetPos = position.offset(orientation, j3);
                        state = world.getBlockState(offsetPos);

                        if (state.getBlock().isAir(state, world, offsetPos) || state.getBlock().isLeaves(state, world, offsetPos) || state.getMaterial() == Material.VINE) {
                            this.setBlockAndNotifyAdequately(world, position.offset(orientation, j3), this.stateWood);
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