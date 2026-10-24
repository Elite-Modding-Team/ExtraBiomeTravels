package mod.emt.ebt.world.biome;

import java.util.Random;

import mod.emt.ebt.init.EBTBlocks;
import mod.emt.ebt.world.feature.EBTWorldGenPlant;
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

                        if (j == 0 && state1.getBlock() == Blocks.SAND && k > 0) {
                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
                            state1 = state1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
                        }

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