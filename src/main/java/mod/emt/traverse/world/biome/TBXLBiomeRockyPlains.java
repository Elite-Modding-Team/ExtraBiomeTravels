package mod.emt.traverse.world.biome;

import mod.emt.traverse.world.feature.TBXLWorldGenPatch;
import mod.emt.traverse.world.feature.tree.TBXLWorldGenCypressTree;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@SuppressWarnings("deprecation")
public class TBXLBiomeRockyPlains extends BiomePlains {
    protected static final WorldGenShrub BIRCH_SHRUB = new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH),
            Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH).withProperty(BlockLeaves.CHECK_DECAY, false));
    protected static final WorldGenBlockBlob COBBLESTONE_BOULDER_FEATURE = new WorldGenBlockBlob(Blocks.COBBLESTONE, 1);
    protected static final TBXLWorldGenPatch STONE_PATCH_FEATURE = new TBXLWorldGenPatch(Blocks.STONE.getDefaultState(), 5);

    public static BiomeProperties properties = new BiomeProperties("Rocky Plains");

    static {
        properties.setTemperature(0.9F);
        properties.setRainfall(0.7F);
        properties.setBaseHeight(0.125F);
        properties.setHeightVariation(0.15F);
    }

    public TBXLBiomeRockyPlains() {
        super(false, properties);
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 4, 1, 5));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityDonkey.class, 1, 1, 2));
    }
    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(@NotNull Random rand) {
        return new TBXLWorldGenCypressTree(true);
    }

    @Override
    public void decorate(@NotNull World world, Random rand, @NotNull BlockPos pos) {
        int stoneChance = rand.nextInt(4);
        if (stoneChance == 0) {
            int k6 = rand.nextInt(16) + 8;
            int l = rand.nextInt(16) + 8;
            BlockPos blockpos = world.getHeight(pos.add(k6, 0, l));
            STONE_PATCH_FEATURE.generate(world, rand, blockpos);
        }

        if (TerrainGen.decorate(world, rand, pos, DecorateBiomeEvent.Decorate.EventType.ROCK)) {
            int genChance = rand.nextInt(4);
            if (genChance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = world.getHeight(pos.add(k6, 0, l));
                COBBLESTONE_BOULDER_FEATURE.generate(world, rand, blockpos);
            }
        }
        if (TerrainGen.decorate(world, rand, pos, DecorateBiomeEvent.Decorate.EventType.TREE)) {
            int genChance = rand.nextInt(3);
            if (genChance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = world.getHeight(pos.add(k6, 0, l));
                BIRCH_SHRUB.generate(world, rand, blockpos);
            }
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public void genTerrainBlocks(@NotNull World world, @NotNull Random rand, @NotNull ChunkPrimer chunkPrimer, int x, int z, double noiseVal) {
        if (noiseVal > 1.6D) {
            this.topBlock = Blocks.STONE.getDefaultState();
            this.fillerBlock = Blocks.STONE.getDefaultState();
        } else {
            this.topBlock = Blocks.GRASS.getDefaultState();
            this.fillerBlock = Blocks.DIRT.getDefaultState();
        }
        this.generateBiomeTerrain(world, rand, chunkPrimer, x, z, noiseVal);
    }

    @Override
    public @NotNull BiomeDecorator createBiomeDecorator() {
        return new DecoratorRockyPlains();
    }

    public static class DecoratorRockyPlains extends BiomeDecorator {
        public DecoratorRockyPlains() {
            treesPerChunk = 1;
            extraTreeChance = 0.15F;
            flowersPerChunk = 4;
            grassPerChunk = 10;
        }

        @Override
        protected void genStandardOre2(@NotNull World world, @NotNull Random random, int blockCount, @NotNull WorldGenerator generator, int centerHeight, int spread) {
            for (int i = 0; i < blockCount; ++i) {
                BlockPos blockpos = this.chunkPos.add(random.nextInt(16), random.nextInt(spread) + random.nextInt(spread) + centerHeight - spread, random.nextInt(16));
                generator.generate(world, random, blockpos);
            }
        }
    }
}