package mod.emt.traverse.init;

import javax.annotation.Nonnull;

import mod.emt.traverse.TraverseBiomesXL;
import mod.emt.traverse.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings({"unused"})
@Mod.EventBusSubscriber(modid = TraverseBiomesXL.MOD_ID)
@GameRegistry.ObjectHolder(TraverseBiomesXL.MOD_ID)
public class TBXLBlocks {
    public static final TBXLBlockMaterial test_block = null;

    @SubscribeEvent
    public static void onRegisterBlocksEvent(@Nonnull final RegistryEvent.Register<Block> event) {
        TraverseBiomesXL.LOGGER.info("Registering blocks...");

        final IForgeRegistry<Block> registry = event.getRegistry();

        // BLOCKS
        registry.registerAll
                (
                        //HSRegistry.setup(new TBXLBlockMaterial(Material.ROCK, MapColor.NETHERRACK, 5.0F, 5.0F, SoundType.STONE), "test_block").setCreativeTab(TraverseBiomesXL.TAB)
                );
    }
}
