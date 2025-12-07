package mod.emt.traverse.world;

import mod.emt.traverse.init.TBXLBlocks;
import mod.emt.traverse.world.feature.TBXLWorldGenFirTree;
import mod.emt.traverse.world.feature.TBXLWorldGenHugeFirTree;
import mod.emt.traverse.world.feature.TBXLWorldGenSimpleFallenTree;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenBirchTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;

public interface WorldGenConstants {
    // Vanilla Logs
    IBlockState ACACIA_LOG = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA);
    IBlockState BIRCH_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
    IBlockState DARK_OAK_LOG = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
    IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    IBlockState OAK_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);
    IBlockState SPRUCE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);

    // Vanilla Leaves
    IBlockState ACACIA_LEAVES = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState BIRCH_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH).withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState DARK_OAK_LEAVES = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState JUNGLE_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState OAK_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState SPRUCE_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLeaves.CHECK_DECAY, false);

    // Vanilla Misc
    IBlockState GRASS = Blocks.GRASS.getDefaultState();
    IBlockState SAND = Blocks.SAND.getDefaultState();
    IBlockState RED_SAND = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);

    // Vanilla Features
    WorldGenSavannaTree ACACIA_TREE_FEATURE = new WorldGenSavannaTree(false);
    WorldGenBirchTree BIRCH_TREE_FEATURE = new WorldGenBirchTree(false, false);
    WorldGenShrub OAK_SHRUB_FEATURE = new WorldGenShrub(OAK_LOG, OAK_LEAVES);
    WorldGenTaiga2 SPRUCE_TREE_FEATURE2 = new WorldGenTaiga2(false);
    WorldGenBirchTree TALLER_BIRCH_TREE_FEATURE = new WorldGenBirchTree(false, true);
    WorldGenTrees TALLER_OAK_TREE_FEATURE = new WorldGenTrees(false, 7, OAK_LOG, OAK_LEAVES, false);

    // TraverseBiomesXL Logs
    IBlockState FIR_LOG = TBXLBlocks.fir_log.getDefaultState();

    // TraverseBiomesXL Leaves
    IBlockState BROWN_AUTUMNAL_OAK_LEAVES = TBXLBlocks.brown_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState ORANGE_AUTUMNAL_LEAVES = TBXLBlocks.orange_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState RED_AUTUMNAL_LEAVES = TBXLBlocks.red_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState YELLOW_AUTUMNAL_LEAVES = TBXLBlocks.yellow_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);
    IBlockState FIR_LEAVES = TBXLBlocks.fir_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false);

    // TraverseBiomesXL Misc
    IBlockState CRACKED_SAND = TBXLBlocks.cracked_sand.getDefaultState();

    // TraverseBiomesXL Features
    TBXLWorldGenFirTree FIR_TREE_FEATURE = new TBXLWorldGenFirTree(false);
    TBXLWorldGenHugeFirTree HUGE_FIR_TREE_FEATURE = new TBXLWorldGenHugeFirTree(false, true);
    TBXLWorldGenSimpleFallenTree SIMPLE_OAK_FALLEN_TREE_FEATURE = new TBXLWorldGenSimpleFallenTree(false);
}