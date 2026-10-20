package mod.emt.ebt.event;

import mod.emt.ebt.init.EBTBiomes;
import mod.emt.ebt.init.EBTBlocks;
import net.minecraft.block.BlockRedSandstone;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// Certain village blocks are hardcoded and will not change, Universal Tweaks partially fixes this at least
// Blocks Affected: Cobblestone, Doors, Pressure Plates, Slabs
public class EBTEventVillageBlocks {
    private static IBlockState getMapleForestVillageBlocks(IBlockState original) {
        if (original.getBlock() == Blocks.LOG || original.getBlock() == Blocks.LOG2) {
            return EBTBlocks.japanese_maple_log.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_DOOR) {
            return EBTBlocks.japanese_maple_door.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_FENCE) {
            return EBTBlocks.japanese_maple_fence.getDefaultState();
        }

        if (original.getBlock() == Blocks.WOODEN_PRESSURE_PLATE) {
            return EBTBlocks.japanese_maple_pressure_plate.getDefaultState();
        }

        if (original.getBlock() == Blocks.PLANKS) {
            return EBTBlocks.japanese_maple_planks.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_STAIRS) {
            return EBTBlocks.japanese_maple_stairs.getDefaultState().withProperty(BlockStairs.FACING, original.getValue(BlockStairs.FACING));
        }

        return original;
    }

    private static IBlockState getPrairieVillageBlocks(IBlockState original) {
        if (original.getBlock() == Blocks.LOG || original.getBlock() == Blocks.LOG2) {
            return EBTBlocks.wattle_log.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_DOOR) {
            return EBTBlocks.wattle_door.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_FENCE) {
            return EBTBlocks.wattle_fence.getDefaultState();
        }

        if (original.getBlock() == Blocks.WOODEN_PRESSURE_PLATE) {
            return EBTBlocks.wattle_pressure_plate.getDefaultState();
        }

        if (original.getBlock() == Blocks.PLANKS) {
            return EBTBlocks.wattle_planks.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_STAIRS) {
            return EBTBlocks.wattle_stairs.getDefaultState().withProperty(BlockStairs.FACING, original.getValue(BlockStairs.FACING));
        }

        return original;
    }

    private static IBlockState getRedDesertVillageBlocks(IBlockState original) {
        if (original.getBlock() == Blocks.LOG || original.getBlock() == Blocks.LOG2 || original.getBlock() == Blocks.GRAVEL) {
            return Blocks.RED_SANDSTONE.getDefaultState();
        }

        if (original.getBlock() == Blocks.COBBLESTONE) {
            return EBTBlocks.red_rock_cobblestone.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_DOOR) {
            return Blocks.ACACIA_DOOR.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_FENCE) {
            return Blocks.ACACIA_FENCE.getDefaultState();
        }

        if (original.getBlock() == Blocks.WOODEN_PRESSURE_PLATE) {
            return EBTBlocks.red_rock_pressure_plate.getDefaultState();
        }

        if (original.getBlock() == Blocks.PLANKS) {
            return Blocks.RED_SANDSTONE.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.SMOOTH);
        }

        if (original.getBlock() == Blocks.STONE_SLAB) {
            return EBTBlocks.red_rock_slab.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_STAIRS || original.getBlock() == Blocks.STONE_STAIRS) {
            return Blocks.RED_SANDSTONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, original.getValue(BlockStairs.FACING));
        }

        return original;
    }

    private static IBlockState getRockyPlainsVillageBlocks(IBlockState original) {
        if (original.getBlock() == Blocks.LOG || original.getBlock() == Blocks.LOG2) {
            return EBTBlocks.cypress_log.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_DOOR) {
            return EBTBlocks.cypress_door.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_FENCE) {
            return EBTBlocks.cypress_fence.getDefaultState();
        }

        if (original.getBlock() == Blocks.WOODEN_PRESSURE_PLATE) {
            return EBTBlocks.cypress_pressure_plate.getDefaultState();
        }

        if (original.getBlock() == Blocks.PLANKS) {
            return EBTBlocks.cypress_planks.getDefaultState();
        }

        if (original.getBlock() == Blocks.OAK_STAIRS) {
            return EBTBlocks.cypress_stairs.getDefaultState().withProperty(BlockStairs.FACING, original.getValue(BlockStairs.FACING));
        }

        return original;
    }

    @SubscribeEvent
    public void replaceVillageBlocks(BiomeEvent.GetVillageBlockID event) {
        if (event.getBiome() == EBTBiomes.MAPLE_SHRUBLANDS) {
            event.setReplacement(getMapleForestVillageBlocks(event.getOriginal()));
            event.setResult(Event.Result.DENY);
        }

        if (event.getBiome() == EBTBiomes.PRAIRIE || event.getBiome() == EBTBiomes.PRAIRIE_SHRUBLANDS) {
            event.setReplacement(getPrairieVillageBlocks(event.getOriginal()));
            event.setResult(Event.Result.DENY);
        }

        if (event.getBiome() == EBTBiomes.RED_DESERT || event.getBiome() == EBTBiomes.WOODED_RED_DESERT) {
            event.setReplacement(getRedDesertVillageBlocks(event.getOriginal()));
            event.setResult(Event.Result.DENY);
        }

        if (event.getBiome() == EBTBiomes.ROCKY_PLAINS) {
            event.setReplacement(getRockyPlainsVillageBlocks(event.getOriginal()));
            event.setResult(Event.Result.DENY);
        }
    }
}
