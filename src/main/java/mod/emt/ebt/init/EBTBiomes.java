package mod.emt.ebt.init;

import mod.emt.ebt.ExtraBiomeTravels;
import mod.emt.ebt.config.EBTConfig;
import mod.emt.ebt.world.biome.*;
import mod.emt.ebt.world.biome.sub.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
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
        if (EBTConfig.BIOMES.enableAutumnalGrove) {
            registerBiome(AUTUMNAL_GROVE, "autumnal_grove", EBTConfig.BIOMES.villageGenAutumnalGrove, Type.DENSE, Type.FOREST);
            registerBiomeSpawn(AUTUMNAL_GROVE, EBTConfig.BIOMES.weightAutumnalGrove, BiomeType.COOL);
            registerBiomeSpawn(AUTUMNAL_GROVE, EBTConfig.BIOMES.weightAutumnalGrove, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enableAutumnalOakForest) {
            registerBiome(AUTUMNAL_OAK_FOREST, "autumnal_oak_forest", EBTConfig.BIOMES.villageGenAutumnalOakForest, Type.DENSE, Type.FOREST);
            registerSubBiome(AUTUMNAL_OAK_FOREST_HILLS, AUTUMNAL_OAK_FOREST, "autumnal_oak_forest_hills", EBTConfig.BIOMES.villageGenAutumnalOakForest, Type.DENSE, Type.FOREST, Type.HILLS);
            registerBiomeSpawn(AUTUMNAL_OAK_FOREST, EBTConfig.BIOMES.weightAutumnalOakForest, BiomeType.COOL);
            registerBiomeSpawn(AUTUMNAL_OAK_FOREST, EBTConfig.BIOMES.weightAutumnalOakForest, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enableCliffs) {
            registerBiome(CLIFFS, "cliffs", EBTConfig.BIOMES.villageGenCliffs, Type.COLD, Type.HILLS, Type.MOUNTAIN, Type.SNOWY);
            registerBiomeSpawn(CLIFFS, EBTConfig.BIOMES.weightCliffs, BiomeType.COOL);
            registerBiomeSpawn(CLIFFS, EBTConfig.BIOMES.weightCliffs, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enableConiferousForest) {
            registerBiome(CONIFEROUS_FOREST, "coniferous_forest", EBTConfig.BIOMES.villageGenConiferousForest, Type.COLD, Type.CONIFEROUS, Type.FOREST);
            registerBiomeSpawn(CONIFEROUS_FOREST, EBTConfig.BIOMES.weightConiferousForest, BiomeType.COOL);
        }
        if (EBTConfig.BIOMES.enableCypressForest) {
            registerBiome(CYPRESS_FOREST, "cypress_forest", EBTConfig.BIOMES.villageGenCypressForest, Type.DENSE, Type.FOREST);
            registerSubBiome(CYPRESS_FOREST_HILLS, CYPRESS_FOREST, "cypress_forest_hills", EBTConfig.BIOMES.villageGenCypressForest, Type.DENSE, Type.FOREST, Type.HILLS);
            registerBiomeSpawn(CYPRESS_FOREST, EBTConfig.BIOMES.weightCypressForest, BiomeType.COOL);
            registerBiomeSpawn(CYPRESS_FOREST, EBTConfig.BIOMES.weightCypressForest, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enableCypressSwamp) {
            registerBiome(CYPRESS_SWAMP, "cypress_swamp", EBTConfig.BIOMES.villageGenCypressSwamp, Type.LUSH, Type.SWAMP, Type.WET);
            registerBiomeSpawn(CYPRESS_SWAMP, EBTConfig.BIOMES.weightCypressSwamp, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enableFlatlands) {
            registerBiome(FLATLANDS, "flatlands", EBTConfig.BIOMES.villageGenFlatlands, Type.LUSH, Type.PLAINS, Type.WET);
            registerBiomeSpawn(FLATLANDS, EBTConfig.BIOMES.weightFlatlands, BiomeType.COOL);
            registerBiomeSpawn(FLATLANDS, EBTConfig.BIOMES.weightFlatlands, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enableGlacier) {
            registerBiome(GLACIER, "glacier", EBTConfig.BIOMES.villageGenGlacier, Type.COLD, Type.HILLS, Type.MOUNTAIN, Type.SNOWY);
            registerSubBiome(GLACIER_SPIKES, GLACIER, "glacier_spikes", EBTConfig.BIOMES.villageGenGlacier, Type.COLD, Type.HILLS, Type.MOUNTAIN, Type.SNOWY);
            registerBiomeSpawn(GLACIER, EBTConfig.BIOMES.weightGlacier, BiomeType.ICY);
        }
        if (EBTConfig.BIOMES.enableLushJungle) {
            registerBiome(LUSH_JUNGLE, "lush_jungle", EBTConfig.BIOMES.villageGenLushJungle, Type.DENSE, Type.HOT, Type.JUNGLE, Type.WET);
            registerBiomeSpawn(LUSH_JUNGLE, EBTConfig.BIOMES.weightLushJungle, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enableMapleShrublands) {
            registerBiome(MAPLE_SHRUBLANDS, "maple_shrublands", EBTConfig.BIOMES.villageGenMapleShrublands, Type.PLAINS, Type.SPARSE);
            registerBiomeSpawn(MAPLE_SHRUBLANDS, EBTConfig.BIOMES.weightMapleShrublands, BiomeType.COOL);
            registerBiomeSpawn(MAPLE_SHRUBLANDS, EBTConfig.BIOMES.weightMapleShrublands, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enableMountainousCrag) {
            registerBiome(MOUNTAINOUS_CRAG, "mountainous_crag", EBTConfig.BIOMES.villageGenMountainousCrag, Type.COLD, Type.HILLS, Type.MOUNTAIN);
            registerBiomeSpawn(MOUNTAINOUS_CRAG, EBTConfig.BIOMES.weightMountainousCrag, BiomeType.COOL);
            registerBiomeSpawn(MOUNTAINOUS_CRAG, EBTConfig.BIOMES.weightMountainousCrag, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enablePrairie) {
            registerBiome(PRAIRIE, "prairie", EBTConfig.BIOMES.villageGenPrairie, Type.DRY, Type.HOT, Type.PLAINS, Type.SAVANNA);
            registerSubBiome(PRAIRIE_SHRUBLANDS, PRAIRIE, "prairie_shrublands", EBTConfig.BIOMES.villageGenPrairie, Type.DRY, Type.HOT, Type.PLAINS, Type.SAVANNA, Type.SPARSE);
            registerBiomeSpawn(PRAIRIE, EBTConfig.BIOMES.weightPrairie, BiomeType.DESERT);
            registerBiomeSpawn(PRAIRIE, EBTConfig.BIOMES.weightPrairie, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enableRainforest) {
            registerBiome(RAINFOREST, "rainforest", EBTConfig.BIOMES.villageGenRainforest, Type.DENSE, Type.JUNGLE, Type.LUSH, Type.WET);
            registerBiomeSpawn(RAINFOREST, EBTConfig.BIOMES.weightRainforest, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enableRedDesert) {
            registerBiome(RED_DESERT, "red_desert", EBTConfig.BIOMES.villageGenRedDesert, Type.DEAD, Type.DRY, Type.HOT, Type.SANDY);
            registerSubBiome(WOODED_RED_DESERT, RED_DESERT, "wooded_red_desert", EBTConfig.BIOMES.villageGenRedDesert, Type.DRY, Type.HILLS, Type.HOT, Type.SANDY, Type.SPARSE);
            registerBiomeSpawn(RED_DESERT, EBTConfig.BIOMES.weightRedDesert, BiomeType.DESERT);
        }
        if (EBTConfig.BIOMES.enableRedRockDrylands) {
            registerBiome(RED_ROCK_DRYLANDS, "red_rock_drylands", EBTConfig.BIOMES.villageGenRedRockDrylands, Type.DRY, Type.HOT, Type.HILLS, Type.SPARSE);
            registerBiomeSpawn(RED_ROCK_DRYLANDS, EBTConfig.BIOMES.weightRedRockDrylands, BiomeType.DESERT);
        }
        if (EBTConfig.BIOMES.enableRedRockRidge) {
            registerBiome(RED_ROCK_RIDGE, "red_rock_ridge", EBTConfig.BIOMES.villageGenRedRockRidge, Type.DRY, Type.HOT, Type.MESA, Type.MOUNTAIN);
            registerBiomeSpawn(RED_ROCK_RIDGE, EBTConfig.BIOMES.weightRedRockRidge, BiomeType.DESERT);
        }
        if (EBTConfig.BIOMES.enableRockyPlains) {
            registerBiome(ROCKY_PLAINS, "rocky_plains", EBTConfig.BIOMES.villageGenRockyPlains, Type.HILLS, Type.PLAINS, Type.SPARSE);
            registerBiomeSpawn(ROCKY_PLAINS, EBTConfig.BIOMES.weightRockyPlains, BiomeType.COOL);
            registerBiomeSpawn(ROCKY_PLAINS, EBTConfig.BIOMES.weightRockyPlains, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enableRollingHills) {
            registerBiome(ROLLING_HILLS, "rolling_hills", EBTConfig.BIOMES.villageGenRollingHills, Type.HILLS, Type.LUSH, Type.SPARSE);
            registerBiomeSpawn(ROLLING_HILLS, EBTConfig.BIOMES.weightRollingHills, BiomeType.COOL);
        }
        if (EBTConfig.BIOMES.enableSnowyConiferousForest) {
            registerBiome(SNOWY_CONIFEROUS_FOREST, "snowy_coniferous_forest", EBTConfig.BIOMES.villageGenSnowyConiferousForest, Type.COLD, Type.CONIFEROUS, Type.FOREST, Type.SNOWY);
            registerBiomeSpawn(SNOWY_CONIFEROUS_FOREST, EBTConfig.BIOMES.weightSnowyConiferousForest, BiomeType.ICY);
        }
        if (EBTConfig.BIOMES.enableThicket) {
            registerBiome(THICKET, "thicket", EBTConfig.BIOMES.villageGenThicket, Type.DENSE, Type.FOREST);
            registerBiomeSpawn(THICKET, EBTConfig.BIOMES.weightThicket, BiomeType.WARM);
        }
        if (EBTConfig.BIOMES.enabletWasteland) {
            registerBiome(WASTELAND, "wasteland", EBTConfig.BIOMES.villageGenWasteland, Type.DEAD, Type.DRY, Type.HOT, Type.SPARSE, Type.WASTELAND);
            registerBiomeSpawn(WASTELAND, EBTConfig.BIOMES.weightWasteland, BiomeType.DESERT);
        }
        if (EBTConfig.BIOMES.enableWoodlands) {
            registerBiome(WOODLANDS, "woodlands", EBTConfig.BIOMES.villageGenWoodlands, Type.DRY, Type.FOREST, Type.PLAINS);
            registerBiomeSpawn(WOODLANDS, EBTConfig.BIOMES.weightWoodlands, BiomeType.COOL);
            registerBiomeSpawn(WOODLANDS, EBTConfig.BIOMES.weightWoodlands, BiomeType.WARM);
        }
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
