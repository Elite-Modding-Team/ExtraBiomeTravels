package mod.emt.traverse.init;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import mod.emt.traverse.TraverseBiomesXL;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = TraverseBiomesXL.MOD_ID)
public class TBXLRegistry {
    public static final SoundType HARDENED_SAND = new SoundType(1.0F, 1.0F, TBXLSoundEvents.hardenedSandBreak.getSoundEvent(), TBXLSoundEvents.hardenedSandStep.getSoundEvent(),
            TBXLSoundEvents.hardenedSandPlace.getSoundEvent(), TBXLSoundEvents.hardenedSandHit.getSoundEvent(), TBXLSoundEvents.hardenedSandFall.getSoundEvent());

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
    public static void registerFuels(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.brown_autumnal_oak_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.green_autumnal_oak_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.orange_autumnal_oak_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.purple_autumnal_oak_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.red_autumnal_oak_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.yellow_autumnal_oak_sapling)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.fir_button)) {
            event.setBurnTime(100); // 0.5 items
        } else if (event.getItemStack().getItem() == Item.getItemFromBlock(TBXLBlocks.fir_sapling)) {
            event.setBurnTime(100); // 0.5 items
        }
    }

    @SubscribeEvent
    public static void registerRecipes(@Nonnull final RegistryEvent.Register<IRecipe> event) {
        TraverseBiomesXL.LOGGER.info("Registering ore dictionary entries...");

        OreDictionary.registerOre("cobblestone", TBXLBlocks.red_rock_cobblestone);
        OreDictionary.registerOre("doorWood", TBXLItems.fir_door);
        OreDictionary.registerOre("fenceGateWood", TBXLBlocks.fir_fence_gate);
        OreDictionary.registerOre("fenceWood", TBXLBlocks.fir_fence);
        OreDictionary.registerOre("logWood", TBXLBlocks.fir_log);
        OreDictionary.registerOre("plankWood", TBXLBlocks.fir_planks);
        OreDictionary.registerOre("sand", TBXLBlocks.cracked_sand);
        OreDictionary.registerOre("slabWood", TBXLBlocks.fir_slab);
        OreDictionary.registerOre("stairWood", TBXLBlocks.fir_stairs);
        //OreDictionary.registerOre("trapdoorWood", TBXLBlocks.fir_trapdoor);
        OreDictionary.registerOre("stone", TBXLBlocks.red_rock);

        OreDictionary.registerOre("treeLeaves", TBXLBlocks.brown_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.fir_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.green_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.orange_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.purple_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.red_autumnal_oak_leaves);
        OreDictionary.registerOre("treeLeaves", TBXLBlocks.yellow_autumnal_oak_leaves);

        OreDictionary.registerOre("treeSapling", TBXLBlocks.brown_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.fir_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.green_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.orange_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.purple_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.red_autumnal_oak_sapling);
        OreDictionary.registerOre("treeSapling", TBXLBlocks.yellow_autumnal_oak_sapling);

        //TraverseBiomesXL.LOGGER.info("Registering recipes...");

        // Furnace Recipes
        GameRegistry.addSmelting(new ItemStack(TBXLBlocks.cracked_sand), new ItemStack(Blocks.GLASS), 0.1F);
        GameRegistry.addSmelting(new ItemStack(TBXLBlocks.red_rock_cobblestone), new ItemStack(TBXLBlocks.red_rock), 0.1F);
    }
}
