package mod.emt.traverse.world.biome;

import java.util.Random;

import mod.emt.traverse.init.TBXLBiomes;
import mod.emt.traverse.world.feature.TBXLWorldGenGlacierIceSpike;
import net.minecraft.block.Block;
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
public class TBXLBiomeGlacier extends Biome {
    public static final TBXLWorldGenGlacierIceSpike ICE_SPIKE_FEATURE = new TBXLWorldGenGlacierIceSpike();

    public final boolean isSpikes;

    public TBXLBiomeGlacier(boolean isSpikes) {
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
            properties.setBaseBiome(TBXLBiomes.GLACIER.getBiomeName());
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
    public void genTerrainBlocks(@NotNull World world, @NotNull Random rand, @NotNull ChunkPrimer primer, int chunkX, int chunkZ, double noiseVal) {
        super.genTerrainBlocks(world, rand, primer, chunkX, chunkZ, noiseVal);
        int[][] surface = new int[16][16];

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                surface[x][z] = -1;
                for (int y = 255; y >= 0; y--) {
                    if (primer.getBlockState(x, y, z).getBlock() != Blocks.AIR) {
                        surface[x][z] = y;
                        break;
                    }
                }
            }
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int surfaceY = surface[x][z];

                if (surfaceY < 1) {
                    continue;
                }

                int worldX = chunkX * 16 + x;
                int worldZ = chunkZ * 16 + z;
                double variation = smoothNoise(worldX, worldZ, 24.0);

                int depth = 11 + (int) Math.round(variation * 3.0);
                depth = Math.max(8, Math.min(14, depth));

                for (int y = surfaceY - 1; y >= Math.max(1, surfaceY - depth); y--) {
                    Block block = primer.getBlockState(x, y, z).getBlock();
                    if (block == Blocks.STONE || block == Blocks.GRASS || block == Blocks.DIRT) {
                        primer.setBlockState(x, y, z, Blocks.PACKED_ICE.getDefaultState());
                    }
                }
            }
        }
    }

    private static double smoothNoise(int x, int z, double scale) {
        int gx = (int) Math.floor(x / scale);
        int gz = (int) Math.floor(z / scale);
        double fx = x / scale - gx;
        double fz = z / scale - gz;
        fx = fx * fx * (3.0 - 2.0 * fx);
        fz = fz * fz * (3.0 - 2.0 * fz);
        double a = lattice(gx, gz);
        double b = lattice(gx + 1, gz);
        double c = lattice(gx, gz + 1);
        double d = lattice(gx + 1, gz + 1);
        double ab = a + (b - a) * fx;
        double cd = c + (d - c) * fx;
        return ab + (cd - ab) * fz;
    }

    private static double lattice(int x, int z) {
        long n = x * 341873128712L + z * 132897987541L;
        n = (n ^ (n >> 13)) * 1274126177L;
        n = n ^ (n >> 16);
        return ((n & 0x7fffffffL) / (double) 0x3fffffffL) - 1.0;
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
}