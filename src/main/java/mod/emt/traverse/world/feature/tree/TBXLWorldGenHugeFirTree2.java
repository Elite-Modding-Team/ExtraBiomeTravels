package mod.emt.traverse.world.feature.tree;

import mod.emt.traverse.init.TBXLBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TBXLWorldGenHugeFirTree2 extends WorldGenHugeTrees {
    public TBXLWorldGenHugeFirTree2(boolean notify) {
        super(notify, 32, 16, TBXLBlocks.douglas_fir_log.getDefaultState(), TBXLBlocks.douglas_fir_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
    }

    @Override
    protected int getHeight(@NotNull Random rand) {
        return this.baseHeight + rand.nextInt(this.extraRandomHeight);
    }

    @Override
    public boolean generate(@NotNull World world, @NotNull Random rand, @NotNull BlockPos position) {
        final int height = this.getHeight(rand);
        final int nakedLogHeight = 1 + rand.nextInt(12);
        final int crownHeight = height - nakedLogHeight;
        final int crownRadius = Math.min(2 + rand.nextInt(9), 4);

        if (position.getY() < 1 || position.getY() + height + 1 > world.getHeight()) {
            return false;
        }

        for (int y = position.getY(); y <= position.getY() + height + 1; y++) {
            int radius;

            if (y - position.getY() < nakedLogHeight) {
                radius = 0;
            } else {
                radius = crownRadius;
            }

            for (int x = position.getX() - radius; x <= position.getX() + 1 + radius; x++) {
                for (int z = position.getZ() - radius; z <= position.getZ() + 1 + radius; z++) {
                    BlockPos checkPos = new BlockPos(x, y, z);
                    IBlockState state = world.getBlockState(checkPos);
                    if (!state.getBlock().isLeaves(state, world, checkPos) && !state.getBlock().isReplaceable(world, checkPos)) {
                        return false;
                    }
                }
            }
        }

        if (!this.ensureGrowable(world, rand, position, height)) {
            return false;
        }

        int leafRadius = rand.nextInt(2);
        int previousRadius = 1;
        boolean flag = false;

        for (int layer = 0; layer <= crownHeight; layer++) {
            int y = position.getY() + height - layer;
            int centerX = position.getX() + 1;
            int centerZ = position.getZ() + 1;
            for (int x = centerX - leafRadius; x <= centerX + leafRadius; x++) {
                int xOffset = x - centerX;
                for (int z = centerZ - leafRadius; z <= centerZ + leafRadius; z++) {
                    int zOffset = z - centerZ;
                    if (Math.abs(xOffset) != leafRadius || Math.abs(zOffset) != leafRadius || leafRadius <= 0) {
                        placeLeafIfReplaceable(world, new BlockPos(x, y, z));
                        placeLeafIfReplaceable(world, new BlockPos(x - 1, y, z));
                        placeLeafIfReplaceable(world, new BlockPos(x, y, z - 1));
                        placeLeafIfReplaceable(world, new BlockPos(x - 1, y, z - 1));
                    }
                }
            }

            if (leafRadius >= previousRadius) {
                leafRadius = flag ? 1 : 0;
                flag = true;
                if (++previousRadius > crownRadius) {
                    previousRadius = crownRadius;
                }
            } else {
                leafRadius++;
            }
        }

        final int trunkVariation = rand.nextInt(3);

        for (int y = 0; y < height - trunkVariation; y++) {
            BlockPos p1 = position.add(0, y, 0);
            BlockPos p2 = position.add(1, y, 0);
            BlockPos p3 = position.add(1, y, 1);
            BlockPos p4 = position.add(0, y, 1);
            placeTrunkIfReplaceable(world, p1);
            placeTrunkIfReplaceable(world, p2);
            placeTrunkIfReplaceable(world, p3);
            placeTrunkIfReplaceable(world, p4);
        }
        return true;
    }

    private void placeLeafIfReplaceable(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock().isAir(state, world, pos) || state.getBlock().canBeReplacedByLeaves(state, world, pos)) {
            this.setBlockAndNotifyAdequately(world, pos, this.leavesMetadata);
        }
    }

    private void placeTrunkIfReplaceable(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos) || state.getMaterial() == Material.VINE || state.getBlock().isReplaceable(world, pos)) {
            this.setBlockAndNotifyAdequately(world, pos, this.woodMetadata);
        }
    }
}