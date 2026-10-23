
package mod.emt.ebt.world.biome;

import mod.emt.ebt.world.feature.EBTWorldGenPatch;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EBTBiomePlainsPlateau extends Biome {
    protected static final EBTWorldGenPatch STONE_PATCH_FEATURE = new EBTWorldGenPatch(Blocks.STONE.getDefaultState(), 15);

    public static BiomeProperties properties = new BiomeProperties("Plains Plateau");

    static {
        properties.setTemperature(0.8F);
        properties.setRainfall(0.2F);
        properties.setBaseHeight(1.6F);
        properties.setHeightVariation(0.0F);
    }

    public EBTBiomePlainsPlateau() {
        super(properties);
        spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityLlama.class, 5, 1, 5));
    }

    @Override
    public void decorate(@NotNull World world, @NotNull Random rand, @NotNull BlockPos pos) {
        double d0 = GRASS_COLOR_NOISE.getValue((double) (pos.getX() + 8) / 200.0D, (double) (pos.getZ() + 8) / 200.0D);
        if (d0 < -0.8D) {
            this.decorator.flowersPerChunk = 15;
            this.decorator.grassPerChunk = 5;
        } else {
            this.decorator.flowersPerChunk = 4;
            this.decorator.grassPerChunk = 10;
            DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);
            if (TerrainGen.decorate(world, rand, new ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS)) {
                for (int i = 0; i < 7; ++i) {
                    int j = rand.nextInt(16) + 8;
                    int k = rand.nextInt(16) + 8;
                    int l = rand.nextInt(world.getHeight(pos.add(j, 0, k)).getY() + 32);
                    DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(j, l, k));
                }
            }
        }

        int stoneChance = rand.nextInt(4);
        if (stoneChance == 0) {
            int k6 = rand.nextInt(16) + 8;
            int l = rand.nextInt(16) + 8;
            BlockPos blockpos = world.getHeight(pos.add(k6, 0, l));
            STONE_PATCH_FEATURE.generate(world, rand, blockpos);
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public void genTerrainBlocks(@NotNull World world, @NotNull Random rand, @NotNull ChunkPrimer primer, int x, int z, double noiseVal) {
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();
        this.generateBiomeTerrain(world, rand, primer, x, z, noiseVal);

        int localX = x & 15;
        int localZ = z & 15;
        int height = 0;

        for (int y = 255; y >= 0; --y) {
            if (primer.getBlockState(localX, y, localZ).getMaterial().isSolid()) {
                height = y;
                break;
            }
        }

        int north = getSurfaceHeight(primer, localX, localZ - 1);
        int south = getSurfaceHeight(primer, localX, localZ + 1);
        int west = getSurfaceHeight(primer, localX - 1, localZ);
        int east = getSurfaceHeight(primer, localX + 1, localZ);
        int slope = Math.max(Math.max(Math.abs(height - north), Math.abs(height - south)), Math.max(Math.abs(height - west), Math.abs(height - east)));

        if (slope >= 1) {
            int stoneDepth = 5 + rand.nextInt(5);
            for (int y = height; y >= Math.max(1, height - stoneDepth); --y) {
                if (primer.getBlockState(localX, y, localZ).getMaterial().isSolid()) {
                    primer.setBlockState(localX, y, localZ, Blocks.STONE.getDefaultState());
                }
            }
        }
    }

    private int getSurfaceHeight(ChunkPrimer primer, int x, int z) {
        x = Math.max(0, Math.min(15, x));
        z = Math.max(0, Math.min(15, z));

        for (int y = 255; y >= 0; --y) {
            if (primer.getBlockState(x, y, z).getMaterial().isSolid()) {
                return y;
            }
        }

        return 0;
    }

    @Override
    public @NotNull BiomeDecorator createBiomeDecorator() {
        return new DecoratorRockyPlateau();
    }

    public static class DecoratorRockyPlateau extends BiomeDecorator {
        public DecoratorRockyPlateau() {
            treesPerChunk = 0;
            extraTreeChance = 0.05F;
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