package mod.emt.ebt.world.biome;

import java.util.Random;

import mod.emt.ebt.world.feature.EBTWorldGenGlacierIceSpike;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class EBTBiomeGlacier extends Biome {
    public static final EBTWorldGenGlacierIceSpike ICE_SPIKE_FEATURE = new EBTWorldGenGlacierIceSpike();

    public final boolean isSpikes;

    public EBTBiomeGlacier(boolean isSpikes) {
        super(getProperties(isSpikes));

        this.topBlock = Blocks.PACKED_ICE.getDefaultState();
        this.fillerBlock = Blocks.PACKED_ICE.getDefaultState();

        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPolarBear.class, 8, 1, 2));
        this.spawnableMonsterList.removeIf(spawnEntry -> spawnEntry.entityClass == EntitySkeleton.class);
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySkeleton.class, 20, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityStray.class, 80, 4, 4));

        this.decorator.grassPerChunk = -999;
        this.decorator.extraTreeChance = -999;
        this.decorator.flowersPerChunk = -999;
        this.decorator.mushroomsPerChunk = -999;
        this.decorator.reedsPerChunk = -999;
        this.decorator.treesPerChunk = -999;
        this.isSpikes = isSpikes;
    }

    public static BiomeProperties getProperties(boolean isSpikes) {
        BiomeProperties properties;

        if (isSpikes) {
            properties = new BiomeProperties("Glacier Spikes");

            // Important to properly set this as a sub-biome!
            properties.setBaseBiome("Glacier");
        } else {
            properties = new BiomeProperties("Glacier");
        }

        properties.setTemperature(-0.25F);
        properties.setRainfall(0.4F);
        properties.setSnowEnabled();
        properties.setBaseHeight(4.5F);
        properties.setHeightVariation(0.1F);
        return properties;
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
    public void decorate(@NotNull World world, @NotNull Random rand, @NotNull BlockPos pos) {
        if (this.isSpikes && TerrainGen.decorate(world, rand, pos, DecorateBiomeEvent.Decorate.EventType.ICE)) {
            int genChance = rand.nextInt(2);
            if (genChance == 0) {
                int j = rand.nextInt(16) + 8;
                int k = rand.nextInt(16) + 8;
                ICE_SPIKE_FEATURE.generate(world, rand, world.getHeight(pos.add(j, 0, k)));
            }
        }
        super.decorate(world, rand, pos);
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFFBFC5CC;
    }
}