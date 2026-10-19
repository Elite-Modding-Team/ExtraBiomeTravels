package mod.emt.traverse.world.biome.sub;

import java.util.Random;

import mod.emt.traverse.init.TBXLBiomes;
import mod.emt.traverse.world.feature.tree.TBXLWorldGenWattleTree;
import net.minecraft.block.*;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import org.jetbrains.annotations.NotNull;

public class TBXLBiomeWoodedRedDesert extends BiomeDesert {
    protected static final WorldGenSavannaTree ACACIA_TREE_FEATURE = new WorldGenSavannaTree(false);

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
        this.topBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
        this.fillerBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
        decorator.generateFalls = false;
        decorator.treesPerChunk = 1;
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(2) == 0) {
            return rand.nextBoolean() ? new WorldGenShrub(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA),
                    Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLeaves.CHECK_DECAY, false))
                    : new TBXLWorldGenWattleTree();
        }

        return ACACIA_TREE_FEATURE;
    }

    @Override
    public void genTerrainBlocks(@NotNull World world, @NotNull Random rand, @NotNull ChunkPrimer chunkPrimer, int x, int z, double noiseVal) {
        if (noiseVal > 1.0D) {
            this.topBlock = Blocks.GRASS.getDefaultState();
            this.fillerBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
        } else {
            this.topBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
            this.fillerBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
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