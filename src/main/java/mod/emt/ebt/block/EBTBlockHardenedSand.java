package mod.emt.ebt.block;

import java.util.Random;

import mod.emt.ebt.init.EBTBlocks;
import mod.emt.ebt.init.EBTRegistry;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import org.jetbrains.annotations.NotNull;

public class EBTBlockHardenedSand extends EBTBlockMaterial implements IGrowable {
    public EBTBlockHardenedSand(String unlocName, MapColor mapColor) {
        super(unlocName, Material.SAND, mapColor, 1.2F, EBTRegistry.HARDENED_SAND);
    }

    // These sand blocks can either be mined or dug to destroy them faster
    @Override
    public boolean isToolEffective(String type, @NotNull IBlockState state) {
        if (type.equals("pickaxe") || type.equals("shovel")) {
            return true;
        }

        return super.isToolEffective(type, state);
    }

    @Override
    public boolean canSustainPlant(@NotNull IBlockState state, @NotNull IBlockAccess world, BlockPos pos, @NotNull EnumFacing direction, IPlantable plantable) {
        return plantable.getPlantType(world, pos.offset(direction)) == EnumPlantType.Desert ||
                plantable.getPlantType(world, pos.offset(direction)) == EnumPlantType.Beach;
    }

    @Override
    public boolean canGrow(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(@NotNull World world, @NotNull Random rand, @NotNull BlockPos pos, @NotNull IBlockState state) {
        return true;
    }

    // Renewable dead bushes, WOW!
    @Override
    public void grow(@NotNull World world, @NotNull Random rand, BlockPos pos, @NotNull IBlockState state) {
        BlockPos blockpos = pos.up();

        for (int i = 0; i < 32; ++i) {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true) {
                if (j >= i / 8) {
                    if (world.isAirBlock(blockpos1)) {
                        if (rand.nextInt(4) == 0) {
                            IBlockState iblockstate1 = Blocks.DEADBUSH.getDefaultState();

                            if ((Blocks.DEADBUSH).canBlockStay(world, blockpos1, iblockstate1)) {
                                world.setBlockState(blockpos1, iblockstate1, 3);
                            }
                        } else {
                            IBlockState iblockstate1 = EBTBlocks.dead_grass.getDefaultState();

                            if ((EBTBlocks.dead_grass).canBlockStay(world, blockpos1, iblockstate1)) {
                                world.setBlockState(blockpos1, iblockstate1, 3);
                            }
                        }
                    }

                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (world.getBlockState(blockpos1).isNormalCube()) {
                    break;
                }

                ++j;
            }
        }
    }
}
