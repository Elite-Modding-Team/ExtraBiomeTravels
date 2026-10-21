package mod.emt.ebt.world.biome;

import java.util.Random;

import mod.emt.ebt.init.EBTBlocks;
import mod.emt.ebt.world.feature.EBTWorldGenPlant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.jetbrains.annotations.NotNull;

public class EBTBiomeRedRockRidge extends Biome {
    public static BiomeProperties properties = new BiomeProperties("Red Rock Ridge");

    public static final WorldGenerator DRY_GRASS_FEATURE = new EBTWorldGenPlant(EBTBlocks.dry_grass.getDefaultState());

    static {
        properties.setTemperature(1.0F);
        properties.setRainfall(0.0F);
        properties.setBaseHeight(2.8F);
        properties.setHeightVariation(0.4F);
    }

    public EBTBiomeRedRockRidge() {
        super(properties);
        this.topBlock = EBTBlocks.red_rock.getDefaultState();
        this.fillerBlock = EBTBlocks.red_rock.getDefaultState();
        this.spawnableCreatureList.clear();

        // Remove Zombies from most spawns and replace them with Husks
        this.spawnableMonsterList.removeIf(list -> list.entityClass == EntityZombie.class || list.entityClass == EntityZombieVillager.class);
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombie.class, 19, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombieVillager.class, 1, 1, 1));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityHusk.class, 80, 4, 4));

        /*if (this.getBaseHeight() > 4.4F)
        {
            this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityLlama.class, 5, 4, 6));
        }*/

        decorator.treesPerChunk = -999;
        decorator.extraTreeChance = -999;
        decorator.flowersPerChunk = -999;
        decorator.grassPerChunk = 1;
        decorator.generateFalls = false;
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

                        if (j == 0 && state1.getBlock() == Blocks.SAND && k > 1) {
                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
                            state1 = state1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
                        }

                        if (j == 0 && state == EBTBlocks.red_rock.getDefaultState() && k > 1) {
                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
                            state = EBTBlocks.red_rock.getDefaultState();
                        }

                        if (j == 0 && state1 == EBTBlocks.red_rock.getDefaultState() && k > 1) {
                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
                            state1 = EBTBlocks.red_rock.getDefaultState();
                        }
                    }
                }
            }
        }

        for (int x1 = 0; x1 < 16; x1++) {
            for (int z1 = 0; z1 < 16; z1++) {
                int surfaceY = -1;

                for (int y = 255; y >= 0; y--) {
                    if (chunkPrimer.getBlockState(x1, y, z1).getMaterial() != Material.AIR) {
                        surfaceY = y;
                        break;
                    }
                }

                if (surfaceY < 1) {
                    continue;
                }

                int worldX = (x & ~15) + x1;
                int worldZ = (z & ~15) + z1;
                double variation = smoothNoise(worldX, worldZ, 24.0);

                int depth = 11 + (int) Math.round(variation * 3.0);
                depth = Math.max(8, Math.min(14, depth));

                for (int y = surfaceY - 1; y >= Math.max(1, surfaceY - depth); y--) {
                    Block block = chunkPrimer.getBlockState(x1, y, z1).getBlock();
                    if (block == Blocks.STONE) {
                        chunkPrimer.setBlockState(x1, y, z1, EBTBlocks.red_rock.getDefaultState());
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
    public @NotNull WorldGenerator getRandomWorldGenForGrass(@NotNull Random rand) {
        return DRY_GRASS_FEATURE;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFFFFE5DD;
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