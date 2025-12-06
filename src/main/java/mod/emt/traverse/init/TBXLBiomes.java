package mod.emt.traverse.init;

import mod.emt.traverse.TraverseBiomesXL;
import mod.emt.traverse.world.biome.*;
import mod.emt.traverse.world.biome.sub.*;
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
    public static Biome AUTUMNAL_WOODS = new TBXLBiomeAutumnalWoods();
    public static Biome AUTUMNAL_WOODED_HILLS = new TBXLBiomeAutumnalWoodedHills();
    public static Biome CLIFFS = new TBXLBiomeCliffs();
    public static Biome CONIFEROUS_FOREST = new TBXLBiomeConiferousForest(false);
    public static Biome FLATLANDS = new TBXLBiomeFlatlands();
    public static Biome LUSH_JUNGLE = new TBXLBiomeLushJungle();
    public static Biome RED_DESERT = new TBXLBiomeRedDesert();
    public static Biome ROLLING_HILLS = new TBXLBiomeRollingHills();
    public static Biome SNOWY_CONIFEROUS_FOREST = new TBXLBiomeConiferousForest(true);
    public static Biome WOODLANDS = new TBXLBiomeWoodlands();

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        // Biomes
        registerBiome(AUTUMNAL_WOODS, "autumnal_woods", false, Type.DENSE, Type.FOREST);
        registerBiome(CLIFFS, "cliffs", false, Type.COLD, Type.HILLS, Type.MOUNTAIN, Type.SNOWY);
        registerBiome(CONIFEROUS_FOREST, "coniferous_forest", false, Type.COLD, Type.CONIFEROUS, Type.FOREST);
        registerBiome(FLATLANDS, "flatlands", true, Type.LUSH, Type.PLAINS, Type.WET);
        registerBiome(LUSH_JUNGLE, "lush_jungle", false, Type.DENSE, Type.HOT, Type.JUNGLE, Type.WET);
        registerBiome(RED_DESERT, "red_desert", true, Type.DEAD, Type.DRY, Type.HOT, Type.SANDY);
        registerBiome(ROLLING_HILLS, "rolling_hills", false, Type.LUSH, Type.HILLS, Type.SPARSE);
        registerBiome(SNOWY_CONIFEROUS_FOREST, "snowy_coniferous_forest", false, Type.COLD, Type.CONIFEROUS, Type.FOREST, Type.SNOWY);
        registerBiome(WOODLANDS, "woodlands", true, Type.DRY, Type.FOREST, Type.PLAINS);

        // Sub-Biomes: These are never registered to biome spawns!
        registerSubBiome(AUTUMNAL_WOODED_HILLS, AUTUMNAL_WOODS, "autumnal_wooded_hills", false, Type.DENSE, Type.FOREST, Type.HILLS);

        // Biome Spawn: Makes them spawn on the world, apply multiple biome types if they are meant to spawn more commonly
        registerBiomeSpawn(AUTUMNAL_WOODS, 6, BiomeType.COOL);
        registerBiomeSpawn(AUTUMNAL_WOODS, 6, BiomeType.WARM);
        registerBiomeSpawn(CLIFFS, 3, BiomeType.COOL);
        registerBiomeSpawn(CLIFFS, 3, BiomeType.ICY);
        registerBiomeSpawn(CLIFFS, 3, BiomeType.WARM);
        registerBiomeSpawn(CONIFEROUS_FOREST, 4, BiomeType.COOL);
        registerBiomeSpawn(FLATLANDS, 7, BiomeType.COOL);
        registerBiomeSpawn(FLATLANDS, 7, BiomeType.WARM);
        registerBiomeSpawn(LUSH_JUNGLE, 4, BiomeType.WARM);
        registerBiomeSpawn(RED_DESERT, 6, BiomeType.DESERT);
        registerBiomeSpawn(ROLLING_HILLS, 6, BiomeType.COOL);
        registerBiomeSpawn(SNOWY_CONIFEROUS_FOREST, 5, BiomeType.ICY);
        registerBiomeSpawn(WOODLANDS, 9, BiomeType.COOL);
        registerBiomeSpawn(WOODLANDS, 9, BiomeType.WARM);
    }

    private static void registerBiome(Biome biome, String name, boolean hasVillages, BiomeDictionary.Type... types) {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addVillageBiome(biome, hasVillages);
    }

    private static void registerSubBiome(Biome biome, Biome baseBiome, String name, boolean hasVillages, BiomeDictionary.Type... types) {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addVillageBiome(biome, hasVillages);
        Biome.MUTATION_TO_BASE_ID_MAP.put(biome, Biome.getIdForBiome(ForgeRegistries.BIOMES.getValue(baseBiome.getRegistryName())));
    }

    private static void registerBiomeSpawn(Biome biome, int biomeWeight, BiomeManager.BiomeType biomeType) {
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, biomeWeight));
    }
}
