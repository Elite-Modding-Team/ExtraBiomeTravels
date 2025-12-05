package mod.emt.traverse.init;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import mod.emt.traverse.TraverseBiomesXL;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = TraverseBiomesXL.MOD_ID)
public class TBXLRegistry {
    @Nonnull
    public static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) {
        return setup(entry, new ResourceLocation(TraverseBiomesXL.MOD_ID, name));
    }

    @Nonnull
    public static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
        Preconditions.checkNotNull(entry, "Entry to setup must not be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign must not be null!");

        entry.setRegistryName(registryName);
        if (entry instanceof Block)
            ((Block) entry).setTranslationKey(registryName.getNamespace() + "." + registryName.getPath());
        if (entry instanceof Item)
            ((Item) entry).setTranslationKey(registryName.getNamespace() + "." + registryName.getPath());
        return entry;
    }

    @SubscribeEvent
    public static void registerRecipes(@Nonnull final RegistryEvent.Register<IRecipe> event) {
        TraverseBiomesXL.LOGGER.info("Registering ore dictionary entries...");

        OreDictionary.registerOre("doorWood", TBXLItems.fir_door);
        OreDictionary.registerOre("fenceGateWood", TBXLBlocks.fir_fence_gate);
        OreDictionary.registerOre("fenceWood", TBXLBlocks.fir_fence);
        OreDictionary.registerOre("logWood", TBXLBlocks.fir_log);
        OreDictionary.registerOre("plankWood", TBXLBlocks.fir_planks);
        OreDictionary.registerOre("slabWood", TBXLBlocks.fir_slab);
        OreDictionary.registerOre("stairWood", TBXLBlocks.fir_stairs);
        //OreDictionary.registerOre("trapdoorWood", TBXLBlocks.fir_trapdoor);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.fir_leaves);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.fir_sapling);

        //TraverseBiomesXL.LOGGER.info("Registering recipes...");

        // Furnace Recipes
        //GameRegistry.addSmelting(new ItemStack(Items.GOLD_INGOT), new ItemStack(Items.GOLD_INGOT), 0.1F);
    }
}
