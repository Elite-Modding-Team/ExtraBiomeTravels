package mod.emt.ebt.world.feature;

import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EBTWorldGenPlant extends WorldGenerator {
    private final IBlockState state;

    public EBTWorldGenPlant(IBlockState state) {
        this.state = state;
    }

    public boolean generate(World world, @NotNull Random rand, @NotNull BlockPos position) {
        for (IBlockState iblockstate = world.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, world, position) || iblockstate.getBlock().isLeaves(iblockstate, world, position)) && position.getY() > 0; iblockstate = world.getBlockState(position)) {
            position = position.down();
        }

        for (int i = 0; i < 128; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            boolean canStay = !(state.getBlock() instanceof BlockBush) || ((BlockBush) state.getBlock()).canBlockStay(world, blockpos, this.state);

            if (world.isAirBlock(blockpos) && canStay) {
                world.setBlockState(blockpos, this.state, 2);
            }
        }

        return true;
    }
}
