package mod.emt.ebt.world.biome;

import mod.emt.ebt.init.EBTBlocks;
import mod.emt.ebt.world.feature.EBTWorldGenPlant;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EBTBiomeMountainousCrag extends Biome {
    public static BiomeProperties properties = new BiomeProperties("Mountainous Crag");

    public static final WorldGenerator DRY_GRASS_FEATURE = new EBTWorldGenPlant(EBTBlocks.dry_grass.getDefaultState());

    static {
        properties.setTemperature(0.8F);
        properties.setRainfall(0.2F);
        properties.setBaseHeight(2.0F);
        properties.setHeightVariation(0.8F);
    }

    public EBTBiomeMountainousCrag() {
        super(properties);
        this.topBlock = EBTBlocks.crag_rock.getDefaultState();
        this.fillerBlock = EBTBlocks.crag_rock.getDefaultState();
        decorator.treesPerChunk = -999;
        decorator.extraTreeChance = -999;
        decorator.flowersPerChunk = -999;
        decorator.grassPerChunk = 1;
        spawnableCreatureList.clear();
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, @NotNull ChunkPrimer chunkPrimer, int x, int z, double noiseVal) {
        int i = world.getSeaLevel();
        IBlockState state = this.topBlock;
        IBlockState state1 = this.fillerBlock;
        int j = -1;
        int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int l = x & 15;
        int i1 = z & 15;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int j1 = 255; j1 >= 0; --j1) {
            if (j1 <= rand.nextInt(5)) {
                chunkPrimer.setBlockState(i1, j1, l, BEDROCK);
            } else {
                IBlockState state2 = chunkPrimer.getBlockState(i1, j1, l);

                if (state2.getMaterial() == Material.AIR) {
                    j = -1;
                } else if (state2.getBlock() == Blocks.STONE) {
                    if (j == -1) {
                        if (k <= 0) {
                            state = AIR;
                            state1 = STONE;
                        } else if (j1 >= i - 4 && j1 <= i + 1) {
                            state = this.topBlock;
                            state1 = this.fillerBlock;
                        }

                        if (j1 < i && (state == null || state.getMaterial() == Material.AIR)) {
                            if (this.getTemperature(pos.setPos(x, j1, z)) < 0.15F) {
                                state = ICE;
                            } else {
                                state = WATER;
                            }
                        }

                        j = k;

                        if (j1 >= i - 1) {
                            chunkPrimer.setBlockState(i1, j1, l, state);
                        } else if (j1 < i - 7 - k) {
                            state = AIR;
                            state1 = STONE;
                            chunkPrimer.setBlockState(i1, j1, l, GRAVEL);
                        } else {
                            chunkPrimer.setBlockState(i1, j1, l, state1);
                        }
                    } else if (j > 0) {
                        --j;
                        chunkPrimer.setBlockState(i1, j1, l, state1);

                        if (j == 0 && state == fillerBlock && k > 0) {
                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
                            state = fillerBlock;
                        }

                        if (j == 0 && state1 == fillerBlock && k > 0) {
                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
                            state1 = fillerBlock;
                        }
                    }
                }
            }
        }
    }

    @Override
    public @NotNull WorldGenerator getRandomWorldGenForGrass(@NotNull Random rand) {
        return DRY_GRASS_FEATURE;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFF97C6C0;
    }
}