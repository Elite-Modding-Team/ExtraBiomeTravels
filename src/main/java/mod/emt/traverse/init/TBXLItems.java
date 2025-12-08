package mod.emt.traverse.init;

import javax.annotation.Nonnull;

import mod.emt.traverse.TraverseBiomesXL;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemSlab;
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
public class TBXLItems {
    @GameRegistry.ObjectHolder("fir_door")
    public static final ItemDoor fir_door = null;

    @SubscribeEvent
    public static void onRegisterItemsEvent(@Nonnull final RegistryEvent.Register<Item> event) {
        TraverseBiomesXL.LOGGER.info("Registering items...");

        final IForgeRegistry<Item> registry = event.getRegistry();

        // ITEMS
        registry.registerAll
                (
                        TBXLRegistry.setup(new ItemDoor(TBXLBlocks.fir_door), "fir_door").setCreativeTab(TraverseBiomesXL.TAB)
                );

        // ITEM BLOCKS
        ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> block.getRegistryName().getNamespace().equals(TraverseBiomesXL.MOD_ID))
                .filter(block -> !(block instanceof BlockDoor)) // Doors should not have an item block registered
                .filter(block -> !(block instanceof BlockSlab)) // Slabs should not have an item block registered
                .forEach(block -> registry.register(TBXLRegistry.setup(new ItemBlock(block), block.getRegistryName())));

        registry.register(TBXLRegistry.setup(new ItemSlab(TBXLBlocks.fir_slab, TBXLBlocks.fir_slab, TBXLBlocks.fir_slab_double), TBXLBlocks.fir_slab.getRegistryName()));
        registry.register(TBXLRegistry.setup(new ItemSlab(TBXLBlocks.red_rock_bricks_slab, TBXLBlocks.red_rock_bricks_slab, TBXLBlocks.red_rock_bricks_slab_double), TBXLBlocks.red_rock_bricks_slab.getRegistryName()));
        registry.register(TBXLRegistry.setup(new ItemSlab(TBXLBlocks.red_rock_cobblestone_slab, TBXLBlocks.red_rock_cobblestone_slab, TBXLBlocks.red_rock_cobblestone_slab_double), TBXLBlocks.red_rock_cobblestone_slab.getRegistryName()));
        registry.register(TBXLRegistry.setup(new ItemSlab(TBXLBlocks.red_rock_slab, TBXLBlocks.red_rock_slab, TBXLBlocks.red_rock_slab_double), TBXLBlocks.red_rock_slab.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRegisterModelsEvent(@Nonnull final ModelRegistryEvent event) {
        TraverseBiomesXL.LOGGER.info("Registering item models...");

        // ITEM MODELS
        for (final Item item : ForgeRegistries.ITEMS.getValues()) {
            if (item.getRegistryName().getNamespace().equals(TraverseBiomesXL.MOD_ID)) {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
            }
        }
    }
}
