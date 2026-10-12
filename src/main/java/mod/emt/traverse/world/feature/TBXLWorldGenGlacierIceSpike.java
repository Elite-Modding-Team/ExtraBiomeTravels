package mod.emt.traverse.world.feature;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

// Needed so it can spawn on Packed Ice
public class TBXLWorldGenGlacierIceSpike extends WorldGenerator {
    public boolean generate(World world, @NotNull Random rand, @NotNull BlockPos position) {
        while (world.isAirBlock(position) && position.getY() > 2) {
            position = position.down();
        }

        if (world.getBlockState(position).getBlock() != Blocks.SNOW && world.getBlockState(position).getBlock() != Blocks.PACKED_ICE) {
            return false;
        }

        if (!hasSolidBase(world, position, 2)) {
            return false;
        }

        position = position.up(rand.nextInt(4));
        int i = rand.nextInt(4) + 7;
        int j = i / 4 + rand.nextInt(2);

        if (j > 1 && rand.nextInt(60) == 0) {
            position = position.up(10 + rand.nextInt(30));
        }

        for (int k = 0; k < i; ++k) {
            float f = (1.0F - (float) k / (float) i) * (float) j;
            int l = MathHelper.ceil(f);

            for (int i1 = -l; i1 <= l; ++i1) {
                float f1 = (float) MathHelper.abs(i1) - 0.25F;

                for (int j1 = -l; j1 <= l; ++j1) {
                    float f2 = (float) MathHelper.abs(j1) - 0.25F;

                    if ((i1 == 0 && j1 == 0 || f1 * f1 + f2 * f2 <= f * f) && (i1 != -l && i1 != l && j1 != -l && j1 != l || rand.nextFloat() <= 0.75F)) {
                        IBlockState iblockstate = world.getBlockState(position.add(i1, k, j1));
                        Block block = iblockstate.getBlock();

                        if (iblockstate.getBlock().isAir(iblockstate, world, position.add(i1, k, j1)) || block == Blocks.DIRT || block == Blocks.SNOW || block == Blocks.ICE || block == Blocks.PACKED_ICE) {
                            this.setBlockAndNotifyAdequately(world, position.add(i1, k, j1), Blocks.PACKED_ICE.getDefaultState());
                        }

                        if (k != 0 && l > 1) {
                            iblockstate = world.getBlockState(position.add(i1, -k, j1));
                            block = iblockstate.getBlock();

                            if (iblockstate.getBlock().isAir(iblockstate, world, position.add(i1, -k, j1)) || block == Blocks.DIRT || block == Blocks.SNOW || block == Blocks.ICE || block == Blocks.PACKED_ICE) {
                                this.setBlockAndNotifyAdequately(world, position.add(i1, -k, j1), Blocks.PACKED_ICE.getDefaultState());
                            }
                        }
                    }
                }
            }
        }

        int k1 = j - 1;

        if (k1 > 1) {
            k1 = 1;
        }

        for (int l1 = -k1; l1 <= k1; ++l1) {
            for (int i2 = -k1; i2 <= k1; ++i2) {
                BlockPos blockpos = position.add(l1, -1, i2);
                int j2 = 50;

                if (MathHelper.abs(l1) == 1 && MathHelper.abs(i2) == 1) {
                    j2 = rand.nextInt(5);
                }

                while (blockpos.getY() > 50) {
                    IBlockState iblockstate1 = world.getBlockState(blockpos);
                    Block block1 = iblockstate1.getBlock();

                    if (!iblockstate1.getBlock().isAir(iblockstate1, world, blockpos) && block1 != Blocks.DIRT && block1 != Blocks.SNOW && block1 != Blocks.ICE && block1 != Blocks.PACKED_ICE) {
                        break;
                    }

                    this.setBlockAndNotifyAdequately(world, blockpos, Blocks.PACKED_ICE.getDefaultState());
                    blockpos = blockpos.down();
                    --j2;

                    if (j2 <= 0) {
                        blockpos = blockpos.down(rand.nextInt(5) + 1);
                        j2 = rand.nextInt(5);
                    }
                }
            }
        }

        return true;
    }

    private boolean hasSolidBase(World world, BlockPos center, int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos checkPos = center.add(x, 0, z);
                Block block = world.getBlockState(checkPos).getBlock();

                if (block != Blocks.PACKED_ICE && block != Blocks.SNOW) {
                    return false;
                }

                Block belowBlock = world.getBlockState(checkPos.down()).getBlock();
                if (belowBlock == Blocks.AIR) {
                    return false;
                }
            }
        }

        return true;
    }
}