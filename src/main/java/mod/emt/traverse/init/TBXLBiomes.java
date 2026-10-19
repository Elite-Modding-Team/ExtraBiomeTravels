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
    public static Biome AUTUMNAL_GROVE = new TBXLBiomeAutumnalGrove();
    public static Biome AUTUMNAL_OAK_FOREST = new TBXLBiomeAutumnalOakForest();
    public static Biome AUTUMNAL_OAK_FOREST_HILLS = new TBXLBiomeAutumnalOakForestHills();
    public static Biome CLIFFS = new TBXLBiomeCliffs();
    public static Biome CONIFEROUS_FOREST = new TBXLBiomeConiferousForest(false);
    public static Biome CYPRESS_SWAMP = new TBXLBiomeCypressSwamp();
    public static Biome FLATLANDS = new TBXLBiomeFlatlands();
    public static Biome GLACIER = new TBXLBiomeGlacier(false);
    public static Biome GLACIER_SPIKES = new TBXLBiomeGlacier(true);
    public static Biome LUSH_JUNGLE = new TBXLBiomeLushJungle();
    public static Biome MAPLE_SHRUBLANDS = new TBXLBiomeMapleShrublands();
    public static Biome MOUNTAINOUS_CRAG = new TBXLBiomeMountainousCrag();
    public static Biome PRAIRIE = new TBXLBiomePrairie();
    public static Biome PRAIRIE_SHRUBLANDS = new TBXLBiomePrairieShrublands();
    public static Biome RAINFOREST = new TBXLBiomeRainforest();
    public static Biome RED_DESERT = new TBXLBiomeRedDesert();
    public static Biome RED_ROCK_DRYLANDS = new TBXLBiomeRedRockDrylands();
    public static Biome RED_ROCK_RIDGE = new TBXLBiomeRedRockRidge();
    public static Biome ROCKY_PLAINS = new TBXLBiomeRockyPlains();
    public static Biome ROLLING_HILLS = new TBXLBiomeRollingHills();
    public static Biome SNOWY_CONIFEROUS_FOREST = new TBXLBiomeConiferousForest(true);
    public static Biome THICKET = new TBXLBiomeThicket();
    public static Biome WASTELAND = new TBXLBiomeWasteland();
    public static Biome WOODED_RED_DESERT = new TBXLBiomeWoodedRedDesert();
    public static Biome WOODLANDS = new TBXLBiomeWoodlands();

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        // Biomes
        registerBiome(AUTUMNAL_GROVE, "autumnal_grove", false, Type.DENSE, Type.FOREST);
        registerBiome(AUTUMNAL_OAK_FOREST, "autumnal_oak_forest", false, Type.DENSE, Type.FOREST);
        registerBiome(CLIFFS, "cliffs", false, Type.COLD, Type.HILLS, Type.MOUNTAIN, Type.SNOWY);
        registerBiome(CONIFEROUS_FOREST, "coniferous_forest", false, Type.COLD, Type.CONIFEROUS, Type.FOREST);
        registerBiome(CYPRESS_SWAMP, "cypress_swamp", false, Type.LUSH, Type.WET, Type.SWAMP);
        registerBiome(FLATLANDS, "flatlands", true, Type.LUSH, Type.PLAINS, Type.WET);
        registerBiome(GLACIER, "glacier", false, Type.COLD, Type.HILLS, Type.MOUNTAIN, Type.SNOWY);
        registerBiome(LUSH_JUNGLE, "lush_jungle", false, Type.DENSE, Type.HOT, Type.JUNGLE, Type.WET);
        registerBiome(MAPLE_SHRUBLANDS, "maple_shrublands", true, Type.PLAINS, Type.SPARSE);
        registerBiome(MOUNTAINOUS_CRAG, "mountainous_crag", false, Type.COLD, Type.HILLS, Type.MOUNTAIN);
        registerBiome(PRAIRIE, "prairie", true, Type.DRY, Type.HOT, Type.PLAINS, Type.SAVANNA);
        registerBiome(RAINFOREST, "rainforest", false, Type.DENSE, Type.JUNGLE, Type.LUSH, Type.WET);
        registerBiome(RED_DESERT, "red_desert", true, Type.DEAD, Type.DRY, Type.HOT, Type.SANDY);
        registerBiome(RED_ROCK_DRYLANDS, "red_rock_drylands", false, Type.DRY, Type.HOT, Type.HILLS, Type.SPARSE);
        registerBiome(RED_ROCK_RIDGE, "red_rock_ridge", false, Type.DRY, Type.HOT, Type.MESA, Type.MOUNTAIN);
        registerBiome(ROCKY_PLAINS, "rocky_plains", true, Type.HILLS, Type.PLAINS, Type.SPARSE);
        registerBiome(ROLLING_HILLS, "rolling_hills", true, Type.LUSH, Type.HILLS, Type.SPARSE);
        registerBiome(SNOWY_CONIFEROUS_FOREST, "snowy_coniferous_forest", false, Type.COLD, Type.CONIFEROUS, Type.FOREST, Type.SNOWY);
        registerBiome(THICKET, "thicket", false, Type.DENSE, Type.FOREST);
        registerBiome(WASTELAND, "wasteland", false, Type.DEAD, Type.DRY, Type.HOT, Type.SPARSE, Type.WASTELAND);
        registerBiome(WOODLANDS, "woodlands", true, Type.DRY, Type.FOREST, Type.PLAINS);

        // Sub-Biomes: These are never registered to biome spawns!
        registerSubBiome(AUTUMNAL_OAK_FOREST_HILLS, AUTUMNAL_OAK_FOREST, "autumnal_oak_forest_hills", false, Type.DENSE, Type.FOREST, Type.HILLS);
        registerSubBiome(GLACIER_SPIKES, GLACIER, "glacier_spikes", false, Type.COLD, Type.HILLS, Type.MOUNTAIN, Type.SNOWY);
        registerSubBiome(PRAIRIE_SHRUBLANDS, PRAIRIE, "prairie_shrublands", true, Type.DRY, Type.HOT, Type.PLAINS, Type.SAVANNA, Type.SPARSE);
        registerSubBiome(WOODED_RED_DESERT, RED_DESERT, "wooded_red_desert", true, Type.DRY, Type.HILLS, Type.HOT, Type.SANDY, Type.SPARSE);

        // Biome Spawns: Makes them spawn on the world, apply multiple biome types if they are meant to spawn more commonly
        registerBiomeSpawn(AUTUMNAL_GROVE, 4, BiomeType.COOL);
        registerBiomeSpawn(AUTUMNAL_GROVE, 4, BiomeType.WARM);
        registerBiomeSpawn(AUTUMNAL_OAK_FOREST, 5, BiomeType.COOL);
        registerBiomeSpawn(AUTUMNAL_OAK_FOREST, 5, BiomeType.WARM);
        registerBiomeSpawn(CLIFFS, 3, BiomeType.COOL);
        registerBiomeSpawn(CLIFFS, 3, BiomeType.WARM);
        registerBiomeSpawn(CONIFEROUS_FOREST, 4, BiomeType.COOL);
        registerBiomeSpawn(CYPRESS_SWAMP, 4, BiomeType.WARM);
        registerBiomeSpawn(FLATLANDS, 5, BiomeType.COOL);
        registerBiomeSpawn(FLATLANDS, 5, BiomeType.WARM);
        registerBiomeSpawn(GLACIER, 3, BiomeType.ICY);
        registerBiomeSpawn(LUSH_JUNGLE, 4, BiomeType.WARM);
        registerBiomeSpawn(MAPLE_SHRUBLANDS, 4, BiomeType.COOL);
        registerBiomeSpawn(MAPLE_SHRUBLANDS, 4, BiomeType.WARM);
        registerBiomeSpawn(MOUNTAINOUS_CRAG, 3, BiomeType.COOL);
        registerBiomeSpawn(MOUNTAINOUS_CRAG, 3, BiomeType.WARM);
        registerBiomeSpawn(PRAIRIE, 5, BiomeType.DESERT);
        registerBiomeSpawn(PRAIRIE, 5, BiomeType.WARM);
        registerBiomeSpawn(RAINFOREST, 4, BiomeType.WARM);
        registerBiomeSpawn(RED_DESERT, 5, BiomeType.DESERT);
        registerBiomeSpawn(RED_ROCK_DRYLANDS, 4, BiomeType.DESERT);
        registerBiomeSpawn(RED_ROCK_RIDGE, 5, BiomeType.DESERT);
        registerBiomeSpawn(ROCKY_PLAINS, 5, BiomeType.COOL);
        registerBiomeSpawn(ROCKY_PLAINS, 5, BiomeType.WARM);
        registerBiomeSpawn(ROLLING_HILLS, 5, BiomeType.COOL);
        registerBiomeSpawn(SNOWY_CONIFEROUS_FOREST, 5, BiomeType.ICY);
        registerBiomeSpawn(THICKET, 3, BiomeType.WARM);
        registerBiomeSpawn(WASTELAND, 4, BiomeType.DESERT);
        registerBiomeSpawn(WOODLANDS, 5, BiomeType.COOL);
        registerBiomeSpawn(WOODLANDS, 5, BiomeType.WARM);
    }

    private static void registerBiome(Biome biome, String name, boolean hasVillages, BiomeDictionary.Type... types) {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        if (hasVillages) {
            BiomeManager.addVillageBiome(biome, true);
        }
    }

    private static void registerSubBiome(Biome biome, Biome baseBiome, String name, boolean hasVillages, BiomeDictionary.Type... types) {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        if (hasVillages) {
            BiomeManager.addVillageBiome(biome, true);
        }
        Biome.MUTATION_TO_BASE_ID_MAP.put(biome, Biome.getIdForBiome(ForgeRegistries.BIOMES.getValue(baseBiome.getRegistryName())));
    }

    private static void registerBiomeSpawn(Biome biome, int biomeWeight, BiomeManager.BiomeType biomeType) {
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, biomeWeight));
    }
}
