package mod.emt.traverse.world.feature.tree;

import mod.emt.traverse.init.TBXLBlocks;
import net.minecraft.block.BlockLeaves;
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

public class TBXLWorldGenFirTree2 extends WorldGenAbstractTree {
    public final boolean isWorldGen;
    private final IBlockState stateWood;
    private final IBlockState stateLeaves;

    public TBXLWorldGenFirTree2(boolean isWorldGen) {
        this(isWorldGen, TBXLBlocks.douglas_fir_log.getDefaultState(), TBXLBlocks.douglas_fir_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
    }

    public TBXLWorldGenFirTree2(boolean isWorldGen, IBlockState stateWood, IBlockState stateLeaves) {
        super(!isWorldGen);
        this.isWorldGen = isWorldGen;
        this.stateWood = stateWood;
        this.stateLeaves = stateLeaves;
    }

    @Override
    public boolean generate(@NotNull World world, Random rand, @NotNull BlockPos position) {
        final int height = rand.nextInt(8) + 24;
        final int nakedLogHeight = 1 + rand.nextInt(12);
        final int maxLeafRadius = Math.min(2 + rand.nextInt(6), 4);

        if (position.getY() < 1 || position.getY() + height + 1 > world.getHeight()) {
            return false;
        }

        BlockPos soilPos = position.down();
        IBlockState soilState = world.getBlockState(soilPos);

        if (!soilState.getBlock().canSustainPlant(soilState, world, soilPos, EnumFacing.UP, (BlockSapling) Blocks.SAPLING)) {
            return false;
        }

        for (int y = position.getY(); y <= position.getY() + height + 1; y++) {
            int radius;

            if (y - position.getY() < nakedLogHeight) {
                radius = 0;
            } else {
                radius = maxLeafRadius;
            }

            for (int x = position.getX() - radius; x <= position.getX() + radius; x++) {
                for (int z = position.getZ() - radius; z <= position.getZ() + radius; z++) {
                    BlockPos checkPos = new BlockPos(x, y, z);
                    IBlockState state = world.getBlockState(checkPos);
                    if (!state.getBlock().isReplaceable(world, checkPos)) {
                        return false;
                    }
                }
            }
        }

        soilState.getBlock().onPlantGrow(soilState, world, soilPos, position);

        int leafRadius = rand.nextInt(2);
        int previousRadius = 1;
        boolean flag = false;

        for (int layer = 0; layer <= height - nakedLogHeight; layer++) {
            int y = position.getY() + height - layer;
            for (int x = position.getX() - leafRadius; x <= position.getX() + leafRadius; x++) {
                int xOffset = x - position.getX();
                for (int z = position.getZ() - leafRadius; z <= position.getZ() + leafRadius; z++) {
                    int zOffset = z - position.getZ();
                    if ((Math.abs(xOffset) != leafRadius || Math.abs(zOffset) != leafRadius || leafRadius <= 0)) {
                        BlockPos leafPos = new BlockPos(x, y, z);
                        IBlockState state = world.getBlockState(leafPos);
                        if (state.getBlock().canBeReplacedByLeaves(state, world, leafPos)) {
                            setBlockAndNotifyAdequately(world, leafPos, stateLeaves);
                        }
                    }
                }
            }

            if (leafRadius >= previousRadius) {
                leafRadius = flag ? 1 : 0;
                flag = true;
                if (++previousRadius > maxLeafRadius) {
                    previousRadius = maxLeafRadius;
                }
            } else {
                leafRadius++;
            }
        }

        final int trunkVariation = rand.nextInt(3);

        for (int y = 0; y < height - trunkVariation; y++) {
            BlockPos trunkPos = position.up(y);
            IBlockState state = world.getBlockState(trunkPos);
            if (state.getBlock().isAir(state, world, trunkPos) || state.getBlock().isLeaves(state, world, trunkPos) || state.getMaterial() == Material.VINE || state.getBlock().isReplaceable(world, trunkPos)) {
                setBlockAndNotifyAdequately(world, trunkPos, stateWood);
            }
        }
        return true;
    }
}