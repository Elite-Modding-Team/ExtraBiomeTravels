package mod.emt.ebt.world.biome;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EBTBiomeMarsh extends BiomeSwamp {
    public static BiomeProperties properties = new BiomeProperties("Marsh");

    static {
        properties.setTemperature(Biomes.SWAMPLAND.getDefaultTemperature());
        properties.setRainfall(Biomes.SWAMPLAND.getRainfall());
        properties.setBaseHeight(-0.1F);
        properties.setHeightVariation(0.0F);
        properties.setWaterColor(0xFF7A7560);
    }

    public EBTBiomeMarsh() {
        super(properties);
        this.decorator.flowersPerChunk = 3;
        this.decorator.grassPerChunk = 10;
        this.decorator.treesPerChunk = 1;
        //this.addFlower(EBTBlocks.toadstools.getDefaultState(), 10);
    }

    @Override
    public void decorate(@NotNull World world, @NotNull Random rand, @NotNull BlockPos pos) {
        super.decorate(world, rand, pos);
        if (rand.nextInt(8) == 0) {
            int x = pos.getX() + rand.nextInt(16) + 8;
            int z = pos.getZ() + rand.nextInt(16) + 8;
            int y = rand.nextInt(30) + 50;
            BlockPos pondPos = new BlockPos(x, y, z);
            WorldGenLakes pond = new WorldGenLakes(Blocks.WATER);
            pond.generate(world, rand, pondPos);
        }
    }

    @Override
    public void genTerrainBlocks(@NotNull World world, @NotNull Random rand, @NotNull ChunkPrimer primer, int x, int z, double noiseVal) {
        int i = x & 15;
        int j = z & 15;
        double puddleNoise = GRASS_COLOR_NOISE.getValue((double) x * 0.25D, (double) z * 0.25D);
        int surfaceY = 62;

        for (int y = 255; y > surfaceY; y--) {
            primer.setBlockState(j, y, i, Blocks.AIR.getDefaultState());
        }

        primer.setBlockState(j, surfaceY, i, Blocks.GRASS.getDefaultState());

        for (int y = surfaceY - 1; y >= surfaceY - 3; y--) {
            primer.setBlockState(j, y, i, Blocks.DIRT.getDefaultState());
        }

        if (puddleNoise > 0.0D) {
            primer.setBlockState(j, surfaceY, i, Blocks.WATER.getDefaultState());
            if (puddleNoise < 0.12D) {
                primer.setBlockState(j, surfaceY + 1, i, Blocks.WATERLILY.getDefaultState());
            }
        }
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(@NotNull Random rand) {
        return SWAMP_FEATURE;
    }
}