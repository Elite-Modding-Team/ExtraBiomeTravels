package mod.emt.traverse.init;

import javax.annotation.Nonnull;

import mod.emt.traverse.TraverseBiomesXL;
import mod.emt.traverse.block.*;
import mod.emt.traverse.block.TBXLBlockSapling.EnumWoodType;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = TraverseBiomesXL.MOD_ID)
@GameRegistry.ObjectHolder(TraverseBiomesXL.MOD_ID)
public class TBXLBlocks {
    @GameRegistry.ObjectHolder("fir_button")
    public static final TBXLBlockButtonWood fir_button = null;
    @GameRegistry.ObjectHolder("fir_door")
    public static final TBXLBlockDoorWood fir_door = null;
    @GameRegistry.ObjectHolder("fir_fence_gate")
    public static final TBXLBlockFenceGateWood fir_fence_gate = null;
    @GameRegistry.ObjectHolder("fir_fence")
    public static final TBXLBlockFenceWood fir_fence = null;
    @GameRegistry.ObjectHolder("fir_leaves")
    public static final TBXLBlockLeaves fir_leaves = null;
    @GameRegistry.ObjectHolder("fir_log")
    public static final TBXLBlockLog fir_log = null;
    @GameRegistry.ObjectHolder("fir_planks")
    public static final TBXLBlockPlanks fir_planks = null;
    @GameRegistry.ObjectHolder("fir_pressure_plate")
    public static final TBXLBlockPressurePlateWood fir_pressure_plate = null;
    @GameRegistry.ObjectHolder("fir_sapling")
    public static final TBXLBlockSapling fir_sapling = null;
    @GameRegistry.ObjectHolder("fir_slab_double")
    public static final TBXLBlockSlab.Double fir_slab_double = null;
    @GameRegistry.ObjectHolder("fir_slab")
    public static final TBXLBlockSlab.Half fir_slab = null;
    @GameRegistry.ObjectHolder("fir_stairs")
    public static final TBXLBlockStairs fir_stairs = null;
    @GameRegistry.ObjectHolder("fir_trapdoor")
    public static final TBXLBlockTrapdoorWood fir_trapdoor = null;

    @SubscribeEvent
    public static void onRegisterBlocksEvent(@Nonnull final RegistryEvent.Register<Block> event) {
        TraverseBiomesXL.LOGGER.info("Registering blocks...");

        final IForgeRegistry<Block> registry = event.getRegistry();

        // BLOCKS
        registry.registerAll
                (
                        TBXLRegistry.setup(new TBXLBlockButtonWood(), "fir_button").setCreativeTab(TraverseBiomesXL.TAB),
                        TBXLRegistry.setup(new TBXLBlockDoorWood(MapColor.ADOBE), "fir_door"),
                        TBXLRegistry.setup(new TBXLBlockFenceGateWood(MapColor.ADOBE, true), "fir_fence_gate").setCreativeTab(TraverseBiomesXL.TAB),
                        TBXLRegistry.setup(new TBXLBlockFenceWood(MapColor.ADOBE, true), "fir_fence").setCreativeTab(TraverseBiomesXL.TAB),
                        TBXLRegistry.setup(new TBXLBlockLeaves(MapColor.FOLIAGE, true, 20), "fir_leaves").setCreativeTab(TraverseBiomesXL.TAB).setHardness(0.2F).setLightOpacity(1),
                        TBXLRegistry.setup(new TBXLBlockLog(MapColor.WOOD, true), "fir_log").setCreativeTab(TraverseBiomesXL.TAB).setHardness(2.0F),
                        TBXLRegistry.setup(new TBXLBlockPlanks(MapColor.ADOBE, true), "fir_planks").setCreativeTab(TraverseBiomesXL.TAB).setHardness(2.0F).setResistance(5.0F),
                        TBXLRegistry.setup(new TBXLBlockPressurePlateWood(MapColor.ADOBE), "fir_pressure_plate").setCreativeTab(TraverseBiomesXL.TAB),
                        TBXLRegistry.setup(new TBXLBlockSapling(EnumWoodType.FIR, MapColor.FOLIAGE, true), "fir_sapling").setCreativeTab(TraverseBiomesXL.TAB).setHardness(0.0F),
                        TBXLRegistry.setup(new TBXLBlockSlab.Double(Material.WOOD, MapColor.ADOBE, true), "fir_slab_double").setHardness(2.0F).setResistance(5.0F),
                        TBXLRegistry.setup(new TBXLBlockSlab.Half(Material.WOOD, MapColor.ADOBE, true), "fir_slab").setCreativeTab(TraverseBiomesXL.TAB).setHardness(2.0F).setResistance(5.0F),
                        TBXLRegistry.setup(new TBXLBlockStairs(new TBXLBlockPlanks(MapColor.ADOBE, true).getDefaultState(), true), "fir_stairs").setCreativeTab(TraverseBiomesXL.TAB).setHardness(2.0F).setResistance(5.0F)
                        //TBXLRegistry.setup(new TBXLBlockTrapdoorWood(MapColor.ADOBE), "fir_trapdoor")
                );
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (Item item : ForgeRegistries.ITEMS.getValues()) {
            if (item.getRegistryName().getNamespace().equals(TraverseBiomesXL.MOD_ID)) {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "normal"));
            }
        }

        // All doors, fence gates, and walls go here
        ModelLoader.setCustomStateMapper(fir_door, (new StateMap.Builder()).ignore(TBXLBlockDoorWood.POWERED).build());

        ModelLoader.setCustomStateMapper(fir_fence_gate, (new StateMap.Builder()).ignore(TBXLBlockFenceGateWood.POWERED).build());
    }
}
