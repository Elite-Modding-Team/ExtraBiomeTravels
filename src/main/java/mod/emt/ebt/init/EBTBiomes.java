package mod.emt.ebt.init;

import mod.emt.ebt.ExtraBiomeTravels;
import mod.emt.ebt.config.EBTConfig;
import mod.emt.ebt.world.biome.*;
import mod.emt.ebt.world.biome.sub.*;
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

@Mod.EventBusSubscriber(modid = ExtraBiomeTravels.MOD_ID)
@GameRegistry.ObjectHolder(ExtraBiomeTravels.MOD_ID)
public class EBTBiomes {
    public static Biome AUTUMNAL_GROVE = new EBTBiomeAutumnalGrove();
    public static Biome AUTUMNAL_OAK_FOREST = new EBTBiomeAutumnalOakForest();
    public static Biome AUTUMNAL_OAK_FOREST_HILLS = new EBTBiomeAutumnalOakForestHills();
    public static Biome CLIFFS = new EBTBiomeCliffs();
    public static Biome CONIFEROUS_FOREST = new EBTBiomeConiferousForest(false);
    public static Biome CYPRESS_FOREST = new EBTBiomeCypressForest();
    public static Biome CYPRESS_FOREST_HILLS = new EBTBiomeCypressForestHills();
    public static Biome CYPRESS_SWAMP = new EBTBiomeCypressSwamp();
    public static Biome FLATLANDS = new EBTBiomeFlatlands();
    public static Biome GLACIER = new EBTBiomeGlacier(false);
    public static Biome GLACIER_SPIKES = new EBTBiomeGlacier(true);
    public static Biome LUSH_JUNGLE = new EBTBiomeLushJungle();
    public static Biome MAPLE_SHRUBLANDS = new EBTBiomeMapleShrublands();
    public static Biome MOUNTAINOUS_CRAG = new EBTBiomeMountainousCrag();
    public static Biome PRAIRIE = new EBTBiomePrairie();
    public static Biome PRAIRIE_SHRUBLANDS = new EBTBiomePrairieShrublands();
    public static Biome RAINFOREST = new EBTBiomeRainforest();
    public static Biome RED_DESERT = new EBTBiomeRedDesert();
    public static Biome RED_ROCK_DRYLANDS = new EBTBiomeRedRockDrylands();
    public static Biome RED_ROCK_RIDGE = new EBTBiomeRedRockRidge();
    public static Biome ROCKY_PLAINS = new EBTBiomeRockyPlains();
    public static Biome ROLLING_HILLS = new EBTBiomeRollingHills();
    public static Biome SNOWY_CONIFEROUS_FOREST = new EBTBiomeConiferousForest(true);
    public static Biome THICKET = new EBTBiomeThicket();
    public static Biome WASTELAND = new EBTBiomeWasteland();
    public static Biome WOODED_RED_DESERT = new EBTBiomeWoodedRedDesert();
    public static Biome WOODLANDS = new EBTBiomeWoodlands();

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        // Biomes
        registerBiome(AUTUMNAL_GROVE, "autumnal_grove", false, Type.DENSE, Type.FOREST);
        registerBiome(AUTUMNAL_OAK_FOREST, "autumnal_oak_forest", false, Type.DENSE, Type.FOREST);
        registerBiome(CLIFFS, "cliffs", false, Type.COLD, Type.HILLS, Type.MOUNTAIN, Type.SNOWY);
        registerBiome(CONIFEROUS_FOREST, "coniferous_forest", false, Type.COLD, Type.CONIFEROUS, Type.FOREST);
        registerBiome(CYPRESS_FOREST, "cypress_forest", false, Type.DENSE, Type.FOREST);
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
        registerSubBiome(CYPRESS_FOREST_HILLS, CYPRESS_FOREST, "cypress_forest_hills", false, Type.DENSE, Type.FOREST, Type.HILLS);
        registerSubBiome(GLACIER_SPIKES, GLACIER, "glacier_spikes", false, Type.COLD, Type.HILLS, Type.MOUNTAIN, Type.SNOWY);
        registerSubBiome(PRAIRIE_SHRUBLANDS, PRAIRIE, "prairie_shrublands", true, Type.DRY, Type.HOT, Type.PLAINS, Type.SAVANNA, Type.SPARSE);
        registerSubBiome(WOODED_RED_DESERT, RED_DESERT, "wooded_red_desert", true, Type.DRY, Type.HILLS, Type.HOT, Type.SANDY, Type.SPARSE);

        // Biome Spawns: Makes them spawn on the world, apply multiple biome types if they are meant to spawn more commonly
        registerBiomeSpawn(AUTUMNAL_GROVE, EBTConfig.BIOMES.weightAutumnalGrove, BiomeType.COOL);
        registerBiomeSpawn(AUTUMNAL_GROVE, EBTConfig.BIOMES.weightAutumnalGrove, BiomeType.WARM);
        registerBiomeSpawn(AUTUMNAL_OAK_FOREST, EBTConfig.BIOMES.weightAutumnalOakForest, BiomeType.COOL);
        registerBiomeSpawn(AUTUMNAL_OAK_FOREST, EBTConfig.BIOMES.weightAutumnalOakForest, BiomeType.WARM);
        registerBiomeSpawn(CLIFFS, EBTConfig.BIOMES.weightCliffs, BiomeType.COOL);
        registerBiomeSpawn(CLIFFS, EBTConfig.BIOMES.weightCliffs, BiomeType.WARM);
        registerBiomeSpawn(CONIFEROUS_FOREST, EBTConfig.BIOMES.weightConiferousForest, BiomeType.COOL);
        registerBiomeSpawn(CYPRESS_FOREST, EBTConfig.BIOMES.weightCypressForest, BiomeType.COOL);
        registerBiomeSpawn(CYPRESS_FOREST, EBTConfig.BIOMES.weightCypressForest, BiomeType.WARM);
        registerBiomeSpawn(CYPRESS_SWAMP, EBTConfig.BIOMES.weightCypressSwamp, BiomeType.WARM);
        registerBiomeSpawn(FLATLANDS, EBTConfig.BIOMES.weightFlatlands, BiomeType.COOL);
        registerBiomeSpawn(FLATLANDS, EBTConfig.BIOMES.weightFlatlands, BiomeType.WARM);
        registerBiomeSpawn(GLACIER, EBTConfig.BIOMES.weightGlacier, BiomeType.ICY);
        registerBiomeSpawn(LUSH_JUNGLE, EBTConfig.BIOMES.weightLushJungle, BiomeType.WARM);
        registerBiomeSpawn(MAPLE_SHRUBLANDS, EBTConfig.BIOMES.weightMapleShrublands, BiomeType.COOL);
        registerBiomeSpawn(MAPLE_SHRUBLANDS, EBTConfig.BIOMES.weightMapleShrublands, BiomeType.WARM);
        registerBiomeSpawn(MOUNTAINOUS_CRAG, EBTConfig.BIOMES.weightMountainousCrag, BiomeType.COOL);
        registerBiomeSpawn(MOUNTAINOUS_CRAG, EBTConfig.BIOMES.weightMountainousCrag, BiomeType.WARM);
        registerBiomeSpawn(PRAIRIE, EBTConfig.BIOMES.weightPrairie, BiomeType.DESERT);
        registerBiomeSpawn(PRAIRIE, EBTConfig.BIOMES.weightPrairie, BiomeType.WARM);
        registerBiomeSpawn(RAINFOREST, EBTConfig.BIOMES.weightRainforest, BiomeType.WARM);
        registerBiomeSpawn(RED_DESERT, EBTConfig.BIOMES.weightRedDesert, BiomeType.DESERT);
        registerBiomeSpawn(RED_ROCK_DRYLANDS, EBTConfig.BIOMES.weightRedRockDrylands, BiomeType.DESERT);
        registerBiomeSpawn(RED_ROCK_RIDGE, EBTConfig.BIOMES.weightRedRockRidge, BiomeType.DESERT);
        registerBiomeSpawn(ROCKY_PLAINS, EBTConfig.BIOMES.weightRockyPlains, BiomeType.COOL);
        registerBiomeSpawn(ROCKY_PLAINS, EBTConfig.BIOMES.weightRockyPlains, BiomeType.WARM);
        registerBiomeSpawn(ROLLING_HILLS, EBTConfig.BIOMES.weightRollingHills, BiomeType.COOL);
        registerBiomeSpawn(SNOWY_CONIFEROUS_FOREST, EBTConfig.BIOMES.weightSnowyConiferousForest, BiomeType.ICY);
        registerBiomeSpawn(THICKET, EBTConfig.BIOMES.weightThicket, BiomeType.WARM);
        registerBiomeSpawn(WASTELAND, EBTConfig.BIOMES.weightWasteland, BiomeType.DESERT);
        registerBiomeSpawn(WOODLANDS, EBTConfig.BIOMES.weightWoodlands, BiomeType.COOL);
        registerBiomeSpawn(WOODLANDS, EBTConfig.BIOMES.weightWoodlands, BiomeType.WARM);
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
