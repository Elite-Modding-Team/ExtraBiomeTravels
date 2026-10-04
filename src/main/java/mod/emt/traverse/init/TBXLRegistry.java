package mod.emt.traverse.init;

import javax.annotation.Nonnull;

import mod.emt.traverse.TraverseBiomesXL;
import mod.emt.traverse.block.TBXLBlockDoorWood;
import mod.emt.traverse.block.TBXLBlockFenceGateWood;
import mod.emt.traverse.block.TBXLBlockWall;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = TraverseBiomesXL.MOD_ID)
public class TBXLRegistry {
    public static final SoundType HARDENED_SAND = new SoundType(1.0F, 1.0F, TBXLSoundEvents.hardenedSandBreak.getSoundEvent(), TBXLSoundEvents.hardenedSandStep.getSoundEvent(),
            TBXLSoundEvents.hardenedSandPlace.getSoundEvent(), TBXLSoundEvents.hardenedSandHit.getSoundEvent(), TBXLSoundEvents.hardenedSandFall.getSoundEvent());

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        TBXLBlocks.initBlocks();
        TBXLBlocks.MOD_BLOCKS.forEach(registry::register);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        TBXLItems.initItems();
        TBXLItems.MOD_ITEMS.forEach(registry::register);

        TBXLBlocks.MOD_BLOCKS.stream()
                .filter(block -> !(block instanceof BlockDoor))
                .filter(block -> !(block instanceof BlockSlab))
                .forEach(block -> registry.register(new ItemBlock(block)
                        .setRegistryName(Objects.requireNonNull(block.getRegistryName()))
                        .setTranslationKey(block.getTranslationKey())
                        .setCreativeTab(block.getCreativeTab())
                ));

        registerOreDictionaries();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        TBXLBlocks.MOD_BLOCKS.stream()
                .filter(block -> !(block instanceof BlockDoor))
                .filter(block -> !(block instanceof BlockSlab))
                .forEach(block -> {
                    ModelResourceLocation loc = new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), "normal");
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, loc);
                });

        TBXLItems.MOD_ITEMS.forEach(item -> {
            ModelResourceLocation loc = new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "normal");
            ModelLoader.setCustomModelResourceLocation(item, 0, loc);
        });

        ModelLoader.setCustomStateMapper(TBXLBlocks.autumnal_door, (new StateMap.Builder()).ignore(TBXLBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.bald_cypress_door, (new StateMap.Builder()).ignore(TBXLBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.cypress_door, (new StateMap.Builder()).ignore(TBXLBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.douglas_fir_door, (new StateMap.Builder()).ignore(TBXLBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.japanese_maple_door, (new StateMap.Builder()).ignore(TBXLBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.peach_sakura_door, (new StateMap.Builder()).ignore(TBXLBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.rainbow_eucalyptus_door, (new StateMap.Builder()).ignore(TBXLBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.scalewood_door, (new StateMap.Builder()).ignore(TBXLBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.wattle_door, (new StateMap.Builder()).ignore(TBXLBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.white_fir_door, (new StateMap.Builder()).ignore(TBXLBlockDoorWood.POWERED).build());

        ModelLoader.setCustomStateMapper(TBXLBlocks.autumnal_fence_gate, (new StateMap.Builder()).ignore(TBXLBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.bald_cypress_fence_gate, (new StateMap.Builder()).ignore(TBXLBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.cypress_fence_gate, (new StateMap.Builder()).ignore(TBXLBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.douglas_fir_fence_gate, (new StateMap.Builder()).ignore(TBXLBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.japanese_maple_fence_gate, (new StateMap.Builder()).ignore(TBXLBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.peach_sakura_fence_gate, (new StateMap.Builder()).ignore(TBXLBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.rainbow_eucalyptus_fence_gate, (new StateMap.Builder()).ignore(TBXLBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.scalewood_fence_gate, (new StateMap.Builder()).ignore(TBXLBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.wattle_fence_gate, (new StateMap.Builder()).ignore(TBXLBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.white_fir_fence_gate, (new StateMap.Builder()).ignore(TBXLBlockFenceGateWood.POWERED).build());

        ModelLoader.setCustomStateMapper(TBXLBlocks.red_rock_bricks_wall, (new StateMap.Builder()).ignore(TBXLBlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.red_rock_cobblestone_wall, (new StateMap.Builder()).ignore(TBXLBlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(TBXLBlocks.red_rock_wall, (new StateMap.Builder()).ignore(TBXLBlockWall.VARIANT).build());
    }

    @SubscribeEvent
    public static void registerFuels(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.bald_cypress_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.brown_autumnal_oak_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.citrine_autumnal_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.cypress_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.douglas_fir_button)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.goldenrod_autumnal_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.green_autumnal_oak_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.japanese_maple_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.japanese_maple_shrub_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.orange_autumnal_oak_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.peach_sakura_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.purple_autumnal_oak_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.rainbow_eucalyptus_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.red_autumnal_oak_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.scalewood_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.umber_autumnal_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.vermillion_autumnal_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.wattle_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.white_fir_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.yellow_autumnal_oak_sapling)) {
            event.setBurnTime(100); // 0.5 items
        }
    }

    public static void registerOreDictionaries() {
        TraverseBiomesXL.LOGGER.info("Registering ore dictionary entries...");
        OreDictionary.registerOre("cobblestone", TBXLBlocks.red_rock_cobblestone);

        OreDictionary.registerOre("doorWood", TBXLItems.autumnal_door);
        OreDictionary.registerOre("doorWood", TBXLItems.bald_cypress_door);
        OreDictionary.registerOre("doorWood", TBXLItems.cypress_door);
        OreDictionary.registerOre("doorWood", TBXLItems.douglas_fir_door);
        OreDictionary.registerOre("doorWood", TBXLItems.japanese_maple_door);
        OreDictionary.registerOre("doorWood", TBXLBlocks.peach_sakura_door);
        OreDictionary.registerOre("doorWood", TBXLItems.rainbow_eucalyptus_door);
        OreDictionary.registerOre("doorWood", TBXLBlocks.scalewood_door);
        OreDictionary.registerOre("doorWood", TBXLBlocks.wattle_door);
        OreDictionary.registerOre("doorWood", TBXLBlocks.white_fir_door);

        OreDictionary.registerOre("fenceGateWood", TBXLBlocks.autumnal_fence_gate);
        OreDictionary.registerOre("fenceGateWood", TBXLBlocks.bald_cypress_fence_gate);
        OreDictionary.registerOre("fenceGateWood", TBXLBlocks.cypress_fence_gate);
        OreDictionary.registerOre("fenceGateWood", TBXLBlocks.douglas_fir_fence_gate);
        OreDictionary.registerOre("fenceGateWood", TBXLBlocks.japanese_maple_fence_gate);
        OreDictionary.registerOre("fenceGateWood", TBXLBlocks.peach_sakura_fence_gate);
        OreDictionary.registerOre("fenceGateWood", TBXLBlocks.rainbow_eucalyptus_fence_gate);
        OreDictionary.registerOre("fenceGateWood", TBXLBlocks.scalewood_fence_gate);
        OreDictionary.registerOre("fenceGateWood", TBXLBlocks.wattle_fence_gate);
        OreDictionary.registerOre("fenceGateWood", TBXLBlocks.white_fir_fence_gate);

        OreDictionary.registerOre("fenceWood", TBXLBlocks.autumnal_fence);
        OreDictionary.registerOre("fenceWood", TBXLBlocks.bald_cypress_fence);
        OreDictionary.registerOre("fenceWood", TBXLBlocks.cypress_fence);
        OreDictionary.registerOre("fenceWood", TBXLBlocks.douglas_fir_fence);
        OreDictionary.registerOre("fenceWood", TBXLBlocks.japanese_maple_fence);
        OreDictionary.registerOre("fenceWood", TBXLBlocks.peach_sakura_fence);
        OreDictionary.registerOre("fenceWood", TBXLBlocks.rainbow_eucalyptus_fence);
        OreDictionary.registerOre("fenceWood", TBXLBlocks.scalewood_fence);
        OreDictionary.registerOre("fenceWood", TBXLBlocks.wattle_fence);
        OreDictionary.registerOre("fenceWood", TBXLBlocks.white_fir_fence);

        OreDictionary.registerOre("logWood", TBXLBlocks.autumnal_log);
        OreDictionary.registerOre("logWood", TBXLBlocks.bald_cypress_log);
        OreDictionary.registerOre("logWood", TBXLBlocks.cypress_log);
        OreDictionary.registerOre("logWood", TBXLBlocks.douglas_fir_log);
        OreDictionary.registerOre("logWood", TBXLBlocks.japanese_maple_log);
        OreDictionary.registerOre("logWood", TBXLBlocks.peach_sakura_log);
        OreDictionary.registerOre("logWood", TBXLBlocks.rainbow_eucalyptus_log);
        OreDictionary.registerOre("logWood", TBXLBlocks.scalewood_log);
        OreDictionary.registerOre("logWood", TBXLBlocks.wattle_log);
        OreDictionary.registerOre("logWood", TBXLBlocks.white_fir_log);

        OreDictionary.registerOre("plankWood", TBXLBlocks.autumnal_planks);
        OreDictionary.registerOre("plankWood", TBXLBlocks.bald_cypress_planks);
        OreDictionary.registerOre("plankWood", TBXLBlocks.cypress_planks);
        OreDictionary.registerOre("plankWood", TBXLBlocks.douglas_fir_planks);
        OreDictionary.registerOre("plankWood", TBXLBlocks.japanese_maple_planks);
        OreDictionary.registerOre("plankWood", TBXLBlocks.peach_sakura_planks);
        OreDictionary.registerOre("plankWood", TBXLBlocks.rainbow_eucalyptus_planks);
        OreDictionary.registerOre("plankWood", TBXLBlocks.scalewood_planks);
        OreDictionary.registerOre("plankWood", TBXLBlocks.wattle_planks);
        OreDictionary.registerOre("plankWood", TBXLBlocks.white_fir_planks);

        OreDictionary.registerOre("sand", TBXLBlocks.cracked_sand);

        OreDictionary.registerOre("slabWood", TBXLItems.autumnal_slab);
        OreDictionary.registerOre("slabWood", TBXLItems.bald_cypress_slab);
        OreDictionary.registerOre("slabWood", TBXLItems.cypress_slab);
        OreDictionary.registerOre("slabWood", TBXLItems.douglas_fir_slab);
        OreDictionary.registerOre("slabWood", TBXLItems.japanese_maple_slab);
        OreDictionary.registerOre("slabWood", TBXLItems.peach_sakura_slab);
        OreDictionary.registerOre("slabWood", TBXLItems.rainbow_eucalyptus_slab);
        OreDictionary.registerOre("slabWood", TBXLItems.scalewood_slab);
        OreDictionary.registerOre("slabWood", TBXLItems.wattle_slab);
        OreDictionary.registerOre("slabWood", TBXLItems.white_fir_slab);

        OreDictionary.registerOre("stairWood", TBXLBlocks.autumnal_stairs);
        OreDictionary.registerOre("stairWood", TBXLBlocks.bald_cypress_stairs);
        OreDictionary.registerOre("stairWood", TBXLBlocks.cypress_stairs);
        OreDictionary.registerOre("stairWood", TBXLBlocks.douglas_fir_stairs);
        OreDictionary.registerOre("stairWood", TBXLBlocks.japanese_maple_stairs);
        OreDictionary.registerOre("stairWood", TBXLBlocks.peach_sakura_stairs);
        OreDictionary.registerOre("stairWood", TBXLBlocks.rainbow_eucalyptus_stairs);
        OreDictionary.registerOre("stairWood", TBXLBlocks.scalewood_stairs);
        OreDictionary.registerOre("stairWood", TBXLBlocks.wattle_stairs);
        OreDictionary.registerOre("stairWood", TBXLBlocks.white_fir_stairs);

        //OreDictionary.registerOre("trapdoorWood", TBXLBlocks.fir_trapdoor);

        OreDictionary.registerOre("stone", TBXLBlocks.red_rock);

        OreDictionary.registerOre("treeLeaves", TBXLBlocks.bald_cypress_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.brown_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.citrine_autumnal_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.cypress_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.douglas_fir_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.goldenrod_autumnal_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.green_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.japanese_maple_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.japanese_maple_shrub_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.orange_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.peach_sakura_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.purple_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.red_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.scalewood_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.umber_autumnal_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.vermillion_autumnal_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.wattle_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.white_fir_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.yellow_autumnal_oak_leaves);

        OreDictionary.registerOre("treeSapling", TBXLBlocks.bald_cypress_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.brown_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.citrine_autumnal_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.cypress_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.douglas_fir_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.goldenrod_autumnal_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.green_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.japanese_maple_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.japanese_maple_shrub_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.orange_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.peach_sakura_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.purple_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.rainbow_eucalyptus_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.red_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.scalewood_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.umber_autumnal_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.vermillion_autumnal_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.wattle_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.white_fir_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.yellow_autumnal_oak_sapling);
    }

    @SubscribeEvent
    public static void registerRecipes(@Nonnull final RegistryEvent.Register<IRecipe> event) {
        TraverseBiomesXL.LOGGER.info("Registering recipes...");

        // Furnace Recipes
        GameRegistry.addSmelting(new ItemStack(TBXLBlocks.cracked_sand), new ItemStack(Blocks.GLASS), 0.1F);
        GameRegistry.addSmelting(new ItemStack(TBXLBlocks.red_rock_cobblestone), new ItemStack(TBXLBlocks.red_rock), 0.1F);
    }
}
