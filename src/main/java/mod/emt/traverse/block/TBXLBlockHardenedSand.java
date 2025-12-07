package mod.emt.traverse.block;

import java.util.Random;

import mod.emt.traverse.init.TBXLRegistry;
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

public class TBXLBlockHardenedSand extends TBXLBlockMaterial implements IGrowable {
    public TBXLBlockHardenedSand(MapColor mapColor) {
        super(Material.SAND, mapColor, 1.2F, TBXLRegistry.HARDENED_SAND);
    }

    // These sand blocks can either be mined or dug to destroy them faster
    @Override
    public boolean isToolEffective(String type, IBlockState state) {
        if (type.equals("pickaxe") || type.equals("shovel")) {
            return true;
        }

        return super.isToolEffective(type, state);
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        return plantable.getPlantType(world, pos.offset(direction)) == EnumPlantType.Desert ||
                plantable.getPlantType(world, pos.offset(direction)) == EnumPlantType.Beach;
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    // Renewable dead bushes, WOW!
    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        BlockPos blockpos = pos.up();

        for (int i = 0; i < 32; ++i) {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true) {
                if (j >= i / 8) {
                    if (world.isAirBlock(blockpos1)) {
                        if (rand.nextInt(2) == 0) {
                            world.getBiome(blockpos1).plantFlower(world, rand, blockpos1);
                        } else {
                            IBlockState iblockstate1 = Blocks.DEADBUSH.getDefaultState();

                            if ((Blocks.DEADBUSH).canBlockStay(world, blockpos1, iblockstate1)) {
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
