package mod.emt.traverse.world.biome;

import java.util.Random;

import mod.emt.traverse.world.WorldGenConstants;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

@SuppressWarnings("deprecation")
public class TBXLBiomeRedRockDrylands extends Biome implements WorldGenConstants {
    protected static final WorldGenLakes LAVA_LAKE_FEATURE = new WorldGenLakes(Blocks.LAVA);

    public static BiomeProperties properties = new BiomeProperties("Red Rock Drylands");

    static {
        properties.setTemperature(0.6F);
        properties.setRainfall(0.1F);
        properties.setBaseHeight(0.6F);
        properties.setHeightVariation(0.5F);
    }

    public TBXLBiomeRedRockDrylands() {
        super(properties);
        decorator.treesPerChunk = 0;
        decorator.deadBushPerChunk = 2;
        decorator.extraTreeChance = 0.0F;
        decorator.flowersPerChunk = -999;
        decorator.grassPerChunk = 4;

        spawnableCreatureList.clear();
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 4, 2, 4));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 3, 2, 4));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 3, 2, 4));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCow.class, 2, 2, 4));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 1, 2, 7));
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFFFFCE96;
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return super.getModdedBiomeGrassColor(0xFFDBC77C);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0xFFC2C168);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(2) == 0) {
            return rand.nextBoolean() ? new WorldGenShrub(ACACIA_LOG, ACACIA_LEAVES) : TREE_FEATURE;
        }

        return ACACIA_TREE_FEATURE;
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        if (noiseVal > 2.25D) {
            this.topBlock = RED_ROCK;
            this.fillerBlock = RED_ROCK;
        } else if (noiseVal > 1.8D) {
            this.topBlock = RED_SAND;
            this.fillerBlock = RED_SAND;
        } else {
            this.topBlock = Blocks.GRASS.getDefaultState();
            this.fillerBlock = Blocks.DIRT.getDefaultState();
        }

        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        if (TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA)) {
            int chance = rand.nextInt(12);

            if (chance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
                LAVA_LAKE_FEATURE.generate(worldIn, rand, blockpos);
            }
        }

        if (TerrainGen.decorate(worldIn, rand, pos, DecorateBiomeEvent.Decorate.EventType.GRASS)) {
            DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

            for (int i = 0; i < 7; ++i) {
                int j = rand.nextInt(16) + 8;
                int k = rand.nextInt(16) + 8;
                int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
                DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
            }
        }

        super.decorate(worldIn, rand, pos);
    }
}
