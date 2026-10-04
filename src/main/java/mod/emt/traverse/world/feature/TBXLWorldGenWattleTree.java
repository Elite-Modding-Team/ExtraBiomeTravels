package mod.emt.traverse.world.feature;

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

public class TBXLWorldGenWattleTree extends WorldGenAbstractTree {
    private final IBlockState stateWood;
    private final IBlockState stateLeaves;

    public TBXLWorldGenWattleTree() {
        this(TBXLBlocks.wattle_log.getDefaultState(), TBXLBlocks.wattle_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
    }

    public TBXLWorldGenWattleTree(IBlockState stateWood, IBlockState stateLeaves) {
        super(false);
        this.stateWood = stateWood;
        this.stateLeaves = stateLeaves;
    }

    @Override
    public boolean generate(@NotNull World world, Random rand, @NotNull BlockPos position) {
        final int height = rand.nextInt(4) + 6;

        if (position.getY() < 1 || position.getY() + height + 1 > world.getHeight()) {
            return false;
        }

        for (int y = position.getY(); y <= position.getY() + height + 1; y++) {
            int clearance = 1;

            if (y == position.getY()) {
                clearance = 0;
            }

            if (y >= position.getY() + 1 + height - 2) {
                clearance = 2;
            }

            for (int x = position.getX() - clearance; x <= position.getX() + clearance; x++) {
                for (int z = position.getZ() - clearance; z <= position.getZ() + clearance; z++) {
                    BlockPos checkPos = new BlockPos(x, y, z);
                    IBlockState state = world.getBlockState(checkPos);
                    if (!state.getBlock().isLeaves(state, world, checkPos) && state.getBlock() != Blocks.GRASS && state.getBlock() != Blocks.DIRT && !state.getBlock().isWood(world, checkPos) && !state.getBlock().isReplaceable(world, checkPos)) {
                        return false;
                    }
                }
            }
        }

        BlockPos soilPos = position.down();
        IBlockState soilState = world.getBlockState(soilPos);

        if (!soilState.getBlock().canSustainPlant(soilState, world, soilPos, EnumFacing.UP, (BlockSapling) Blocks.SAPLING)) {
            return false;
        }

        soilState.getBlock().onPlantGrow(soilState, world, soilPos, position);

        final int canopyHeight = 2;
        final int minCanopyRadius = 0;

        for (int y = position.getY() - canopyHeight + height; y <= position.getY() + height; y++) {
            final int distanceFromTop = y - (position.getY() + height);
            final int canopyRadius = minCanopyRadius + 1 - distanceFromTop;
            for (int x = position.getX() - canopyRadius; x <= position.getX() + canopyRadius; x++) {
                final int xOnRadius = x - position.getX();
                for (int z = position.getZ() - canopyRadius; z <= position.getZ() + canopyRadius; z++) {
                    final int zOnRadius = z - position.getZ();
                    if ((Math.abs(xOnRadius) != canopyRadius || Math.abs(zOnRadius) != canopyRadius || rand.nextInt(2) != 0 || distanceFromTop == 0)) {
                        BlockPos leafPos = new BlockPos(x, y, z);
                        IBlockState state = world.getBlockState(leafPos);
                        if (state.getBlock().canBeReplacedByLeaves(state, world, leafPos)) {
                            setBlockAndNotifyAdequately(world, leafPos, stateLeaves);
                        }
                    }
                }
            }
        }

        for (int y = 0; y < height; y++) {
            BlockPos trunkPos = position.up(y);
            IBlockState state = world.getBlockState(trunkPos);
            if (state.getBlock().isLeaves(state, world, trunkPos) || state.getBlock().isAir(state, world, trunkPos)) {
                setBlockAndNotifyAdequately(world, trunkPos, stateWood);
            }
        }
        return true;
    }
}