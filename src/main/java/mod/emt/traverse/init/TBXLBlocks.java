package mod.emt.traverse.init;

import mod.emt.traverse.block.*;
import mod.emt.traverse.block.TBXLBlockSapling.EnumWoodType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class TBXLBlocks {
    public static TBXLBlockButtonWood fir_button = null;
    public static TBXLBlockDoorWood fir_door = null;
    public static TBXLBlockFenceGateWood fir_fence_gate = null;
    public static TBXLBlockFenceWood fir_fence = null;

    public static TBXLBlockLeaves brown_autumnal_oak_leaves = null;
    public static TBXLBlockLeaves green_autumnal_oak_leaves = null;
    public static TBXLBlockLeaves orange_autumnal_oak_leaves = null;
    public static TBXLBlockLeaves purple_autumnal_oak_leaves = null;
    public static TBXLBlockLeaves red_autumnal_oak_leaves = null;
    public static TBXLBlockLeaves yellow_autumnal_oak_leaves = null;
    public static TBXLBlockLeaves fir_leaves = null;

    public static TBXLBlockLog fir_log = null;
    public static TBXLBlockPlanks fir_planks = null;
    public static TBXLBlockPressurePlateWood fir_pressure_plate = null;

    public static TBXLBlockSapling brown_autumnal_oak_sapling = null;
    public static TBXLBlockSapling green_autumnal_oak_sapling = null;
    public static TBXLBlockSapling orange_autumnal_oak_sapling = null;
    public static TBXLBlockSapling purple_autumnal_oak_sapling = null;
    public static TBXLBlockSapling red_autumnal_oak_sapling = null;
    public static TBXLBlockSapling yellow_autumnal_oak_sapling = null;
    public static TBXLBlockSapling fir_sapling = null;

    public static TBXLBlockSlab.Double fir_slab_double = null;
    public static TBXLBlockSlab.Half fir_slab = null;
    public static TBXLBlockStairs fir_stairs = null;
    public static TBXLBlockTrapdoorWood fir_trapdoor = null;

    public static TBXLBlockMaterial cracked_sand = null;
    public static TBXLBlockMaterial red_rock = null;
    public static TBXLBlockButtonStone red_rock_button = null;
    public static TBXLBlockSlab.Double red_rock_slab_double = null;
    public static TBXLBlockSlab.Half red_rock_slab = null;
    public static TBXLBlockStairs red_rock_stairs = null;
    public static TBXLBlockWall red_rock_wall = null;
    public static TBXLBlockMaterial red_rock_bricks = null;
    public static TBXLBlockPressurePlateStone red_rock_pressure_plate = null;
    public static TBXLBlockSlab.Double red_rock_bricks_slab_double = null;
    public static TBXLBlockSlab.Half red_rock_bricks_slab = null;
    public static TBXLBlockStairs red_rock_bricks_stairs = null;
    public static TBXLBlockWall red_rock_bricks_wall = null;
    public static TBXLBlockMaterial red_rock_cobblestone = null;
    public static TBXLBlockSlab.Double red_rock_cobblestone_slab_double = null;
    public static TBXLBlockSlab.Half red_rock_cobblestone_slab = null;
    public static TBXLBlockStairs red_rock_cobblestone_stairs = null;
    public static TBXLBlockWall red_rock_cobblestone_wall = null;

    public static final List<Block> MOD_BLOCKS = new ArrayList<>();

    public static void initBlocks() {
        MOD_BLOCKS.add(fir_button = new TBXLBlockButtonWood("fir_button"));
        MOD_BLOCKS.add(fir_door = new TBXLBlockDoorWood("fir_door", MapColor.ADOBE));
        MOD_BLOCKS.add(fir_fence_gate = new TBXLBlockFenceGateWood("fir_fence_gate", MapColor.ADOBE, true));
        MOD_BLOCKS.add(fir_fence = new TBXLBlockFenceWood("fir_fence", MapColor.ADOBE, true));
        MOD_BLOCKS.add(brown_autumnal_oak_leaves = new TBXLBlockLeaves("brown_autumnal_oak_leaves", MapColor.BROWN_STAINED_HARDENED_CLAY, true, 20));
        MOD_BLOCKS.add(green_autumnal_oak_leaves = new TBXLBlockLeaves("green_autumnal_oak_leaves", MapColor.LIME_STAINED_HARDENED_CLAY, true, 20));
        MOD_BLOCKS.add(orange_autumnal_oak_leaves = new TBXLBlockLeaves("orange_autumnal_oak_leaves", MapColor.ORANGE_STAINED_HARDENED_CLAY, true, 20));
        MOD_BLOCKS.add(purple_autumnal_oak_leaves = new TBXLBlockLeaves("purple_autumnal_oak_leaves", MapColor.PURPLE_STAINED_HARDENED_CLAY, true, 20));
        MOD_BLOCKS.add(red_autumnal_oak_leaves = new TBXLBlockLeaves("red_autumnal_oak_leaves", MapColor.RED_STAINED_HARDENED_CLAY, true, 20));
        MOD_BLOCKS.add(yellow_autumnal_oak_leaves = new TBXLBlockLeaves("yellow_autumnal_oak_leaves", MapColor.YELLOW_STAINED_HARDENED_CLAY, true, 20));
        MOD_BLOCKS.add(fir_leaves = new TBXLBlockLeaves("fir_leaves", MapColor.FOLIAGE, true, 20));

        MOD_BLOCKS.add(fir_log = new TBXLBlockLog("fir_log", MapColor.WOOD, 2.0F, true));
        MOD_BLOCKS.add(fir_planks = new TBXLBlockPlanks("fir_planks", MapColor.ADOBE, 2.0F, 5.0F, true));
        MOD_BLOCKS.add(fir_pressure_plate = new TBXLBlockPressurePlateWood("fir_pressure_plate", MapColor.ADOBE));

        MOD_BLOCKS.add(brown_autumnal_oak_sapling = new TBXLBlockSapling("brown_autumnal_oak_sapling", EnumWoodType.AUTUMNAL_OAK_BROWN, MapColor.FOLIAGE, true));
        MOD_BLOCKS.add(green_autumnal_oak_sapling = new TBXLBlockSapling("green_autumnal_oak_sapling", EnumWoodType.AUTUMNAL_OAK_GREEN, MapColor.FOLIAGE, true));
        MOD_BLOCKS.add(orange_autumnal_oak_sapling = new TBXLBlockSapling("orange_autumnal_oak_sapling", EnumWoodType.AUTUMNAL_OAK_ORANGE, MapColor.FOLIAGE, true));
        MOD_BLOCKS.add(purple_autumnal_oak_sapling = new TBXLBlockSapling("purple_autumnal_oak_sapling", EnumWoodType.AUTUMNAL_OAK_PURPLE, MapColor.FOLIAGE, true));
        MOD_BLOCKS.add(red_autumnal_oak_sapling = new TBXLBlockSapling("red_autumnal_oak_sapling", EnumWoodType.AUTUMNAL_OAK_RED, MapColor.FOLIAGE, true));
        MOD_BLOCKS.add(yellow_autumnal_oak_sapling = new TBXLBlockSapling("yellow_autumnal_oak_sapling", EnumWoodType.AUTUMNAL_OAK_YELLOW, MapColor.FOLIAGE, true));
        MOD_BLOCKS.add(fir_sapling = new TBXLBlockSapling("fir_sapling", EnumWoodType.FIR, MapColor.FOLIAGE, true));

        MOD_BLOCKS.add(fir_slab = new TBXLBlockSlab.Half("fir_slab", Material.WOOD, MapColor.ADOBE, 2.0F, 5.0F, true));
        MOD_BLOCKS.add(fir_slab_double = new TBXLBlockSlab.Double("fir_slab", Material.WOOD, MapColor.ADOBE, 2.0F, 5.0F, true));
        MOD_BLOCKS.add(fir_stairs = new TBXLBlockStairs("fir_stairs", new TBXLBlockPlanks("fir_planks_base", MapColor.ADOBE, 2.0F, 5.0F, true).getDefaultState(), true));

        MOD_BLOCKS.add(cracked_sand = new TBXLBlockHardenedSand("cracked_sand", MapColor.SILVER_STAINED_HARDENED_CLAY));
        MOD_BLOCKS.add(red_rock_button = new TBXLBlockButtonStone("red_rock_button"));
        MOD_BLOCKS.add(red_rock = new TBXLBlockRock("red_rock", MapColor.ORANGE_STAINED_HARDENED_CLAY));
        MOD_BLOCKS.add(red_rock_cobblestone = new TBXLBlockMaterial("red_rock_cobblestone", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, SoundType.STONE));
        MOD_BLOCKS.add(red_rock_bricks = new TBXLBlockMaterial("red_rock_bricks", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, SoundType.STONE));
        MOD_BLOCKS.add(red_rock_pressure_plate = new TBXLBlockPressurePlateStone("red_rock_pressure_plate", MapColor.ORANGE_STAINED_HARDENED_CLAY));

        MOD_BLOCKS.add(red_rock_slab_double = new TBXLBlockSlab.Double("red_rock_slab", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, false));
        MOD_BLOCKS.add(red_rock_slab = new TBXLBlockSlab.Half("red_rock_slab", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, false));
        MOD_BLOCKS.add(red_rock_cobblestone_slab_double = new TBXLBlockSlab.Double("red_rock_cobblestone_slab", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, false));
        MOD_BLOCKS.add(red_rock_cobblestone_slab = new TBXLBlockSlab.Half("red_rock_cobblestone_slab", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, false));
        MOD_BLOCKS.add(red_rock_bricks_slab_double = new TBXLBlockSlab.Double("red_rock_bricks_slab", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, false));
        MOD_BLOCKS.add(red_rock_bricks_slab = new TBXLBlockSlab.Half("red_rock_bricks_slab", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, false));

        MOD_BLOCKS.add(red_rock_stairs = new TBXLBlockStairs("red_rock_stairs", new TBXLBlockMaterial("red_rock_stairs_base", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, SoundType.STONE).getDefaultState(), false));
        MOD_BLOCKS.add(red_rock_cobblestone_stairs = new TBXLBlockStairs("red_rock_cobblestone_stairs", new TBXLBlockMaterial("red_rock_cobblestone_stairs_base", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, SoundType.STONE).getDefaultState(), false));
        MOD_BLOCKS.add(red_rock_bricks_stairs = new TBXLBlockStairs("red_rock_bricks_stairs", new TBXLBlockMaterial("red_rock_bricks_stairs_base", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, SoundType.STONE).getDefaultState(), false));

        MOD_BLOCKS.add(red_rock_wall = new TBXLBlockWall("red_rock_wall", new TBXLBlockMaterial("red_rock_wall_base", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, SoundType.STONE), false));
        MOD_BLOCKS.add(red_rock_cobblestone_wall = new TBXLBlockWall("red_rock_cobblestone_wall", new TBXLBlockMaterial("red_rock_cobblestone_wall_base", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, SoundType.STONE), false));
        MOD_BLOCKS.add(red_rock_bricks_wall = new TBXLBlockWall("red_rock_bricks_wall", new TBXLBlockMaterial("red_rock_bricks_wall_base", Material.ROCK, MapColor.ORANGE_STAINED_HARDENED_CLAY, 2.0F, 10.0F, SoundType.STONE), false));
    }
}
