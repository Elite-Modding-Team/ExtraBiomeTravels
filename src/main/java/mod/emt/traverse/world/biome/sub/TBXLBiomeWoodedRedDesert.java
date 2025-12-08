package mod.emt.traverse.world.biome.sub;

import java.util.Random;

import mod.emt.traverse.init.TBXLBiomes;
import mod.emt.traverse.world.WorldGenConstants;
import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;

public class TBXLBiomeWoodedRedDesert extends BiomeDesert implements WorldGenConstants {
    public static BiomeProperties properties = new BiomeProperties("Wooded Red Desert");

    static {
        properties.setTemperature(Biomes.DESERT_HILLS.getDefaultTemperature());
        properties.setRainfall(Biomes.DESERT_HILLS.getRainfall());
        properties.setRainDisabled();
        properties.setBaseHeight(Biomes.DESERT_HILLS.getBaseHeight());
        properties.setHeightVariation(Biomes.DESERT_HILLS.getHeightVariation());

        // Important to properly set this as a sub-biome!
        properties.setBaseBiome(TBXLBiomes.RED_DESERT.getBiomeName());
    }

    public TBXLBiomeWoodedRedDesert() {
        super(properties);
        this.topBlock = RED_SAND;
        this.fillerBlock = RED_SAND;
        decorator.generateFalls = false;
        decorator.treesPerChunk = 1;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(2) == 0) {
            return rand.nextBoolean() ? new WorldGenShrub(ACACIA_LOG, ACACIA_LEAVES) : TREE_FEATURE;
        }

        return ACACIA_TREE_FEATURE;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int x, int z, double noiseVal) {
        if (noiseVal > 1.0D) {
            this.topBlock = GRASS;
            this.fillerBlock = RED_SAND;
        } else {
            this.topBlock = RED_SAND;
            this.fillerBlock = RED_SAND;
        }

        this.generateBiomeTerrain(world, rand, chunkPrimer, x, z, noiseVal);
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return super.getModdedBiomeGrassColor(0xFFDBC77C);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0xFFC2C168);
    }
}