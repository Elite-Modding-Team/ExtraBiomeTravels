package mod.emt.traverse.world.feature;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;

import java.util.Random;

import mod.emt.traverse.world.WorldGenConstants;

public class TBXLWorldGenHugeFirTree extends WorldGenHugeTrees implements WorldGenConstants {
    private final boolean useBaseHeight;

    // useBaseHeight being set to false gives it the alternate tree variant
    public TBXLWorldGenHugeFirTree(boolean notify, boolean useBaseHeight) {
        super(notify, 22, 18, FIR_LOG, FIR_LEAVES);
        this.useBaseHeight = useBaseHeight;
    }

    @Override
    protected int getHeight(Random rand) {
        int i = this.baseHeight;

        if (this.extraRandomHeight > 1) {
            i += rand.nextInt(this.extraRandomHeight);
        }

        return i;
    }

    public boolean generate(World world, Random rand, BlockPos position) {
        int i = this.getHeight(rand);

        if (!this.ensureGrowable(world, rand, position, i)) {
            return false;
        } else {
            this.createCrown(world, position.getX(), position.getZ(), position.getY() + 2 + i, 0, rand);

            for (int j = 0; j < i; ++j) {
                if (j < i - 1) {

                    if (isAirLeaves(world, position.add(0, j, 0))) {
                        this.setBlockAndNotifyAdequately(world, position.add(0, j, 0), this.woodMetadata);
                    }

                    if (isAirLeaves(world, position.add(1, j, 0))) {
                        this.setBlockAndNotifyAdequately(world, position.add(1, j, 0), this.woodMetadata);
                    }

                    if (isAirLeaves(world, position.add(1, j, 1))) {
                        this.setBlockAndNotifyAdequately(world, position.add(1, j, 1), this.woodMetadata);
                    }


                    if (isAirLeaves(world, position.add(0, j, 1))) {
                        this.setBlockAndNotifyAdequately(world, position.add(0, j, 1), this.woodMetadata);
                    }
                }
            }

            return true;
        }
    }

    private void createCrown(World world, int x, int z, int y, int w, Random rand) {
        int i = 2 + rand.nextInt(3) + (this.useBaseHeight ? this.extraRandomHeight : 3);
        int j = 0;

        for (int k = y - i; k <= y; ++k) {
            int l = y - k;
            int i1 = w + MathHelper.floor((float) l / (float) i * 3.5F);
            this.growLeavesLayerStrict(world, new BlockPos(x, k, z), i1 + (l > 0 && i1 == j && (k & 1) == 0 ? 1 : 0));
            j = i1;
        }
    }


    private boolean isAirLeaves(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos);
    }
}