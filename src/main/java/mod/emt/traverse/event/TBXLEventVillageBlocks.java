package mod.emt.traverse.event;

import mod.emt.traverse.init.TBXLBiomes;
import mod.emt.traverse.init.TBXLBlocks;
import net.minecraft.block.BlockRedSandstone;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TBXLEventVillageBlocks {
    private static IBlockState getMapleForestVillageBlocks(IBlockState original) {
        if (original.getBlock() == Blocks.LOG || original.getBlock() == Blocks.LOG2) {
            return TBXLBlocks.japanese_maple_log.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_DOOR) {
            return TBXLBlocks.japanese_maple_door.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_FENCE) {
            return TBXLBlocks.japanese_maple_fence.getDefaultState();
        }

        if (original.getBlock() == Blocks.WOODEN_PRESSURE_PLATE) {
            return TBXLBlocks.japanese_maple_pressure_plate.getDefaultState();
        }

        if (original.getBlock() == Blocks.PLANKS) {
            return TBXLBlocks.japanese_maple_planks.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_STAIRS) {
            return TBXLBlocks.japanese_maple_stairs.getDefaultState().withProperty(BlockStairs.FACING, original.getValue(BlockStairs.FACING));
        }

        return original;
    }

    private static IBlockState getPrairieVillageBlocks(IBlockState original) {
        if (original.getBlock() == Blocks.LOG || original.getBlock() == Blocks.LOG2) {
            return TBXLBlocks.wattle_log.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_DOOR) {
            return TBXLBlocks.wattle_door.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_FENCE) {
            return TBXLBlocks.wattle_fence.getDefaultState();
        }

        if (original.getBlock() == Blocks.WOODEN_PRESSURE_PLATE) {
            return TBXLBlocks.wattle_pressure_plate.getDefaultState();
        }

        if (original.getBlock() == Blocks.PLANKS) {
            return TBXLBlocks.wattle_planks.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_STAIRS) {
            return TBXLBlocks.wattle_stairs.getDefaultState().withProperty(BlockStairs.FACING, original.getValue(BlockStairs.FACING));
        }

        return original;
    }

    private static IBlockState getRedDesertVillageBlocks(IBlockState original) {
        if (original.getBlock() == Blocks.LOG || original.getBlock() == Blocks.LOG2 || original.getBlock() == Blocks.GRAVEL) {
            return Blocks.RED_SANDSTONE.getDefaultState();
        }

        if (original.getBlock() == Blocks.COBBLESTONE) {
            return TBXLBlocks.red_rock_cobblestone.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_DOOR) {
            return Blocks.ACACIA_DOOR.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_FENCE) {
            return Blocks.ACACIA_FENCE.getDefaultState();
        }

        if (original.getBlock() == Blocks.WOODEN_PRESSURE_PLATE) {
            return TBXLBlocks.red_rock_pressure_plate.getDefaultState();
        }

        if (original.getBlock() == Blocks.PLANKS) {
            return Blocks.RED_SANDSTONE.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.SMOOTH);
        }

        if (original.getBlock() == Blocks.STONE_SLAB) {
            return TBXLBlocks.red_rock_slab.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_STAIRS || original.getBlock() == Blocks.STONE_STAIRS) {
            return Blocks.RED_SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, original.getValue(BlockStairs.FACING));
        }

        return original;
    }

    private static IBlockState getRockyPlainsVillageBlocks(IBlockState original) {
        if (original.getBlock() == Blocks.LOG || original.getBlock() == Blocks.LOG2) {
            return TBXLBlocks.cypress_log.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_DOOR) {
            return TBXLBlocks.cypress_door.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_FENCE) {
            return TBXLBlocks.cypress_fence.getDefaultState();
        }

        if (original.getBlock() == Blocks.WOODEN_PRESSURE_PLATE) {
            return TBXLBlocks.cypress_pressure_plate.getDefaultState();
        }

        if (original.getBlock() == Blocks.PLANKS) {
            return TBXLBlocks.cypress_planks.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_STAIRS) {
            return TBXLBlocks.cypress_stairs.getDefaultState().withProperty(BlockStairs.FACING, original.getValue(BlockStairs.FACING));
        }

        return original;
    }

    @SubscribeEvent
    public void replaceVillageBlocks(BiomeEvent.GetVillageBlockID event) {
        if (event.getBiome() == TBXLBiomes.MAPLE_SHRUBLANDS) {
            event.setReplacement(getMapleForestVillageBlocks(event.getOriginal()));
            event.setResult(Event.Result.DENY);
        }

        if (event.getBiome() == TBXLBiomes.PRAIRIE || event.getBiome() == TBXLBiomes.PRAIRIE_SHRUBLANDS) {
            event.setReplacement(getPrairieVillageBlocks(event.getOriginal()));
            event.setResult(Event.Result.DENY);
        }

        if (event.getBiome() == TBXLBiomes.RED_DESERT || event.getBiome() == TBXLBiomes.WOODED_RED_DESERT) {
            event.setReplacement(getRedDesertVillageBlocks(event.getOriginal()));
            event.setResult(Event.Result.DENY);
        }

        if (event.getBiome() == TBXLBiomes.ROCKY_PLAINS) {
            event.setReplacement(getRockyPlainsVillageBlocks(event.getOriginal()));
            event.setResult(Event.Result.DENY);
        }
    }
}
