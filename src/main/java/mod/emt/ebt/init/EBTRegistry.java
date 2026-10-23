package mod.emt.ebt.init;

import javax.annotation.Nonnull;

import mod.emt.ebt.ExtraBiomeTravels;
import mod.emt.ebt.block.*;
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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = ExtraBiomeTravels.MOD_ID)
public class EBTRegistry {
    public static final SoundType HARDENED_SAND = new SoundType(1.0F, 1.0F, EBTSoundEvents.hardenedSandBreak.getSoundEvent(), EBTSoundEvents.hardenedSandStep.getSoundEvent(),
            EBTSoundEvents.hardenedSandPlace.getSoundEvent(), EBTSoundEvents.hardenedSandHit.getSoundEvent(), EBTSoundEvents.hardenedSandFall.getSoundEvent());

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        EBTBlocks.initBlocks();
        EBTBlocks.MOD_BLOCKS.forEach(registry::register);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        EBTItems.initItems();
        EBTItems.MOD_ITEMS.forEach(registry::register);
        EBTBlocks.MOD_BLOCKS.stream()
                .filter(block -> !(block instanceof BlockDoor))
                .filter(block -> !(block instanceof BlockSlab))
                .forEach(block -> registry.register(new ItemBlock(block) {
                            @Override
                            public int getItemBurnTime(@NotNull ItemStack itemStack) {
                                if (block instanceof IBurnableFuel) {
                                    return ((IBurnableFuel) block).getBlockBurnTime(itemStack);
                                } else {
                                    return super.getItemBurnTime(itemStack);
                                }
                            }
                        }
                                .setRegistryName(Objects.requireNonNull(block.getRegistryName()))
                                .setTranslationKey(block.getTranslationKey())
                                .setCreativeTab(block.getCreativeTab())
                ));
        registerOreDictionaries();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        EBTBlocks.MOD_BLOCKS.stream()
                .filter(block -> !(block instanceof BlockDoor))
                .filter(block -> !(block instanceof BlockSlab))
                .forEach(block -> {
                    String variant = (block instanceof EBTBlockTallGrass || block instanceof EBTBlockFlower) ? "inventory" : "normal";
                    ModelResourceLocation loc = new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), variant);
                    ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, loc);
                });

        EBTItems.MOD_ITEMS.forEach(item -> {
            ModelResourceLocation loc = new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "normal");
            ModelLoader.setCustomModelResourceLocation(item, 0, loc);
        });

        ModelLoader.setCustomStateMapper(EBTBlocks.autumnal_door, (new StateMap.Builder()).ignore(EBTBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.bald_cypress_door, (new StateMap.Builder()).ignore(EBTBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.cypress_door, (new StateMap.Builder()).ignore(EBTBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.douglas_fir_door, (new StateMap.Builder()).ignore(EBTBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.japanese_maple_door, (new StateMap.Builder()).ignore(EBTBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.peach_sakura_door, (new StateMap.Builder()).ignore(EBTBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.rainbow_eucalyptus_door, (new StateMap.Builder()).ignore(EBTBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.scalewood_door, (new StateMap.Builder()).ignore(EBTBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.wattle_door, (new StateMap.Builder()).ignore(EBTBlockDoorWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.white_fir_door, (new StateMap.Builder()).ignore(EBTBlockDoorWood.POWERED).build());

        ModelLoader.setCustomStateMapper(EBTBlocks.autumnal_fence_gate, (new StateMap.Builder()).ignore(EBTBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.bald_cypress_fence_gate, (new StateMap.Builder()).ignore(EBTBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.cypress_fence_gate, (new StateMap.Builder()).ignore(EBTBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.douglas_fir_fence_gate, (new StateMap.Builder()).ignore(EBTBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.japanese_maple_fence_gate, (new StateMap.Builder()).ignore(EBTBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.peach_sakura_fence_gate, (new StateMap.Builder()).ignore(EBTBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.rainbow_eucalyptus_fence_gate, (new StateMap.Builder()).ignore(EBTBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.scalewood_fence_gate, (new StateMap.Builder()).ignore(EBTBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.wattle_fence_gate, (new StateMap.Builder()).ignore(EBTBlockFenceGateWood.POWERED).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.white_fir_fence_gate, (new StateMap.Builder()).ignore(EBTBlockFenceGateWood.POWERED).build());

        ModelLoader.setCustomStateMapper(EBTBlocks.crag_rock_bricks_wall, (new StateMap.Builder()).ignore(EBTBlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.red_rock_bricks_wall, (new StateMap.Builder()).ignore(EBTBlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.crag_rock_cobblestone_wall, (new StateMap.Builder()).ignore(EBTBlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.red_rock_cobblestone_wall, (new StateMap.Builder()).ignore(EBTBlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.crag_rock_wall, (new StateMap.Builder()).ignore(EBTBlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(EBTBlocks.red_rock_wall, (new StateMap.Builder()).ignore(EBTBlockWall.VARIANT).build());
    }

    public static void registerOreDictionaries() {
        ExtraBiomeTravels.LOGGER.info("Registering ore dictionary entries...");

        OreDictionary.registerOre("cobblestone", EBTBlocks.crag_rock_cobblestone);
        OreDictionary.registerOre("cobblestone", EBTBlocks.red_rock_cobblestone);

        OreDictionary.registerOre("doorWood", EBTItems.autumnal_door);
        OreDictionary.registerOre("doorWood", EBTItems.bald_cypress_door);
        OreDictionary.registerOre("doorWood", EBTItems.cypress_door);
        OreDictionary.registerOre("doorWood", EBTItems.douglas_fir_door);
        OreDictionary.registerOre("doorWood", EBTItems.japanese_maple_door);
        OreDictionary.registerOre("doorWood", EBTItems.peach_sakura_door);
        OreDictionary.registerOre("doorWood", EBTItems.rainbow_eucalyptus_door);
        OreDictionary.registerOre("doorWood", EBTItems.scalewood_door);
        OreDictionary.registerOre("doorWood", EBTItems.wattle_door);
        OreDictionary.registerOre("doorWood", EBTItems.white_fir_door);

        OreDictionary.registerOre("fenceGateWood", EBTBlocks.autumnal_fence_gate);
        OreDictionary.registerOre("fenceGateWood", EBTBlocks.bald_cypress_fence_gate);
        OreDictionary.registerOre("fenceGateWood", EBTBlocks.cypress_fence_gate);
        OreDictionary.registerOre("fenceGateWood", EBTBlocks.douglas_fir_fence_gate);
        OreDictionary.registerOre("fenceGateWood", EBTBlocks.japanese_maple_fence_gate);
        OreDictionary.registerOre("fenceGateWood", EBTBlocks.peach_sakura_fence_gate);
        OreDictionary.registerOre("fenceGateWood", EBTBlocks.rainbow_eucalyptus_fence_gate);
        OreDictionary.registerOre("fenceGateWood", EBTBlocks.scalewood_fence_gate);
        OreDictionary.registerOre("fenceGateWood", EBTBlocks.wattle_fence_gate);
        OreDictionary.registerOre("fenceGateWood", EBTBlocks.white_fir_fence_gate);

        OreDictionary.registerOre("fenceWood", EBTBlocks.autumnal_fence);
        OreDictionary.registerOre("fenceWood", EBTBlocks.bald_cypress_fence);
        OreDictionary.registerOre("fenceWood", EBTBlocks.cypress_fence);
        OreDictionary.registerOre("fenceWood", EBTBlocks.douglas_fir_fence);
        OreDictionary.registerOre("fenceWood", EBTBlocks.japanese_maple_fence);
        OreDictionary.registerOre("fenceWood", EBTBlocks.peach_sakura_fence);
        OreDictionary.registerOre("fenceWood", EBTBlocks.rainbow_eucalyptus_fence);
        OreDictionary.registerOre("fenceWood", EBTBlocks.scalewood_fence);
        OreDictionary.registerOre("fenceWood", EBTBlocks.wattle_fence);
        OreDictionary.registerOre("fenceWood", EBTBlocks.white_fir_fence);

        OreDictionary.registerOre("logWood", EBTBlocks.autumnal_log);
        OreDictionary.registerOre("logWood", EBTBlocks.bald_cypress_log);
        OreDictionary.registerOre("logWood", EBTBlocks.cypress_log);
        OreDictionary.registerOre("logWood", EBTBlocks.douglas_fir_log);
        OreDictionary.registerOre("logWood", EBTBlocks.japanese_maple_log);
        OreDictionary.registerOre("logWood", EBTBlocks.peach_sakura_log);
        OreDictionary.registerOre("logWood", EBTBlocks.rainbow_eucalyptus_log);
        OreDictionary.registerOre("logWood", EBTBlocks.scalewood_log);
        OreDictionary.registerOre("logWood", EBTBlocks.wattle_log);
        OreDictionary.registerOre("logWood", EBTBlocks.white_fir_log);

        OreDictionary.registerOre("plankWood", EBTBlocks.autumnal_planks);
        OreDictionary.registerOre("plankWood", EBTBlocks.bald_cypress_planks);
        OreDictionary.registerOre("plankWood", EBTBlocks.cypress_planks);
        OreDictionary.registerOre("plankWood", EBTBlocks.douglas_fir_planks);
        OreDictionary.registerOre("plankWood", EBTBlocks.japanese_maple_planks);
        OreDictionary.registerOre("plankWood", EBTBlocks.peach_sakura_planks);
        OreDictionary.registerOre("plankWood", EBTBlocks.rainbow_eucalyptus_planks);
        OreDictionary.registerOre("plankWood", EBTBlocks.scalewood_planks);
        OreDictionary.registerOre("plankWood", EBTBlocks.wattle_planks);
        OreDictionary.registerOre("plankWood", EBTBlocks.white_fir_planks);

        OreDictionary.registerOre("sand", EBTBlocks.cracked_sand);

        OreDictionary.registerOre("slabWood", EBTItems.autumnal_slab);
        OreDictionary.registerOre("slabWood", EBTItems.bald_cypress_slab);
        OreDictionary.registerOre("slabWood", EBTItems.cypress_slab);
        OreDictionary.registerOre("slabWood", EBTItems.douglas_fir_slab);
        OreDictionary.registerOre("slabWood", EBTItems.japanese_maple_slab);
        OreDictionary.registerOre("slabWood", EBTItems.peach_sakura_slab);
        OreDictionary.registerOre("slabWood", EBTItems.rainbow_eucalyptus_slab);
        OreDictionary.registerOre("slabWood", EBTItems.scalewood_slab);
        OreDictionary.registerOre("slabWood", EBTItems.wattle_slab);
        OreDictionary.registerOre("slabWood", EBTItems.white_fir_slab);

        OreDictionary.registerOre("stairWood", EBTBlocks.autumnal_stairs);
        OreDictionary.registerOre("stairWood", EBTBlocks.bald_cypress_stairs);
        OreDictionary.registerOre("stairWood", EBTBlocks.cypress_stairs);
        OreDictionary.registerOre("stairWood", EBTBlocks.douglas_fir_stairs);
        OreDictionary.registerOre("stairWood", EBTBlocks.japanese_maple_stairs);
        OreDictionary.registerOre("stairWood", EBTBlocks.peach_sakura_stairs);
        OreDictionary.registerOre("stairWood", EBTBlocks.rainbow_eucalyptus_stairs);
        OreDictionary.registerOre("stairWood", EBTBlocks.scalewood_stairs);
        OreDictionary.registerOre("stairWood", EBTBlocks.wattle_stairs);
        OreDictionary.registerOre("stairWood", EBTBlocks.white_fir_stairs);

        //OreDictionary.registerOre("trapdoorWood", EBTBlocks.fir_trapdoor);

        OreDictionary.registerOre("stone", EBTBlocks.red_rock);
        OreDictionary.registerOre("stone", EBTBlocks.crag_rock);

        OreDictionary.registerOre("treeLeaves", EBTBlocks.bald_cypress_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.brown_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.citrine_autumnal_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.cypress_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.douglas_fir_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.goldenrod_autumnal_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.green_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.japanese_maple_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.japanese_maple_shrub_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.orange_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.peach_sakura_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.purple_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.red_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.scalewood_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.umber_autumnal_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.vermillion_autumnal_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.wattle_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.white_fir_leaves);
        OreDictionary.registerOre("treeLeaves", EBTBlocks.yellow_autumnal_oak_leaves);

        OreDictionary.registerOre("treeSapling", EBTBlocks.bald_cypress_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.brown_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.citrine_autumnal_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.cypress_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.douglas_fir_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.goldenrod_autumnal_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.green_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.japanese_maple_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.japanese_maple_shrub_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.orange_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.peach_sakura_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.purple_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.rainbow_eucalyptus_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.red_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.scalewood_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.umber_autumnal_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.vermillion_autumnal_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.wattle_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.white_fir_sapling);
        OreDictionary.registerOre("treeSapling", EBTBlocks.yellow_autumnal_oak_sapling);
    }

    @SubscribeEvent
    public static void registerRecipes(@Nonnull final RegistryEvent.Register<IRecipe> event) {
        ExtraBiomeTravels.LOGGER.info("Registering recipes...");

        // Furnace Recipes
        GameRegistry.addSmelting(new ItemStack(EBTBlocks.cracked_sand), new ItemStack(Blocks.GLASS), 0.1F);
        GameRegistry.addSmelting(new ItemStack(EBTBlocks.crag_rock_cobblestone), new ItemStack(EBTBlocks.crag_rock), 0.1F);
        GameRegistry.addSmelting(new ItemStack(EBTBlocks.red_rock_cobblestone), new ItemStack(EBTBlocks.red_rock), 0.1F);
    }
}
