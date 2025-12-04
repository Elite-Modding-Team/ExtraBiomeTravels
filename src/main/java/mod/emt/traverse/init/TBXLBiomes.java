package mod.emt.traverse.init;

import mod.emt.traverse.TraverseBiomesXL;
import mod.emt.traverse.world.biome.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = TraverseBiomesXL.MOD_ID)
@GameRegistry.ObjectHolder(TraverseBiomesXL.MOD_ID)
public class TBXLBiomes {
    public static Biome FLATLANDS = new TBXLBiomeFlatlands();
    public static Biome LUSH_JUNGLE = new TBXLBiomeLushJungle();
    public static Biome ROLLING_HILLS = new TBXLBiomeRollingHills();
    public static Biome WOODLANDS = new TBXLBiomeWoodlands();

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        registerBiome(FLATLANDS, "flatlands", true, Type.LUSH, Type.PLAINS, Type.WET);
        registerBiome(LUSH_JUNGLE, "lush_jungle", false, Type.DENSE, Type.HOT, Type.JUNGLE, Type.WET);
        registerBiome(ROLLING_HILLS, "rolling_hills", false, Type.LUSH, Type.HILLS, Type.SPARSE);
        registerBiome(WOODLANDS, "woodlands", true, Type.DRY, Type.FOREST, Type.PLAINS);

        registerBiomeSpawn(FLATLANDS, 7, BiomeType.COOL);
        registerBiomeSpawn(FLATLANDS, 7, BiomeType.WARM);
        registerBiomeSpawn(LUSH_JUNGLE, 4, BiomeType.WARM);
        registerBiomeSpawn(ROLLING_HILLS, 6, BiomeType.COOL);
        registerBiomeSpawn(WOODLANDS, 9, BiomeType.COOL);
        registerBiomeSpawn(WOODLANDS, 9, BiomeType.WARM);
    }

    private static void registerBiome(Biome biome, String name, boolean hasVillages, BiomeDictionary.Type... types) {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addVillageBiome(biome, hasVillages);
    }

    private static void registerBiomeSpawn(Biome biome, int biomeWeight, BiomeManager.BiomeType biomeType) {
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, biomeWeight));
    }
}
