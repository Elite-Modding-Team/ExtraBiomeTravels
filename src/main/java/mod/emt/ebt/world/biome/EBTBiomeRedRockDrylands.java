package mod.emt.ebt.world.biome;

import java.util.Random;

import mod.emt.ebt.init.EBTBlocks;
import mod.emt.ebt.world.feature.tree.EBTWorldGenWattleTree;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class EBTBiomeRedRockDrylands extends Biome {
    protected static final WorldGenSavannaTree ACACIA_TREE_FEATURE = new WorldGenSavannaTree(false);
    protected static final WorldGenLakes LAVA_LAKE_FEATURE = new WorldGenLakes(Blocks.LAVA);

    public static BiomeProperties properties = new BiomeProperties("Red Rock Drylands");

    static {
        properties.setTemperature(0.6F);
        properties.setRainfall(0.1F);
        properties.setBaseHeight(0.6F);
        properties.setHeightVariation(0.5F);
    }

    public EBTBiomeRedRockDrylands() {
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
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(2) == 0) {
            return rand.nextBoolean() ? new WorldGenShrub(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA),
                    Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLeaves.CHECK_DECAY, false))
                    : new EBTWorldGenWattleTree();
        }

        return ACACIA_TREE_FEATURE;
    }

    public void genTerrainBlocks(@NotNull World world, @NotNull Random rand, @NotNull ChunkPrimer chunkPrimer, int x, int z, double noiseVal) {
        IBlockState redRock = EBTBlocks.red_rock.getDefaultState();
        IBlockState redSand = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
        IBlockState redSandstone = Blocks.RED_SANDSTONE.getDefaultState();

        if (noiseVal > 2.0D) {
            this.topBlock = redRock;
            this.fillerBlock = redRock;
        } else if (noiseVal > 1.0D) {
            this.topBlock = redSand;
            this.fillerBlock = redSandstone;
        } else {
            this.topBlock = Blocks.GRASS.getDefaultState();
            this.fillerBlock = Blocks.DIRT.getDefaultState();
        }

        this.generateBiomeTerrain(world, rand, chunkPrimer, x, z, noiseVal);
    }

    @Override
    public void decorate(@NotNull World world, @NotNull Random rand, @NotNull BlockPos pos) {
        if (TerrainGen.decorate(world, rand, pos, DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA)) {
            int chance = rand.nextInt(12);

            if (chance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = world.getHeight(pos.add(k6, 0, l));
                LAVA_LAKE_FEATURE.generate(world, rand, blockpos);
            }
        }

        if (TerrainGen.decorate(world, rand, pos, DecorateBiomeEvent.Decorate.EventType.GRASS)) {
            DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

            for (int i = 0; i < 7; ++i) {
                int j = rand.nextInt(16) + 8;
                int k = rand.nextInt(16) + 8;
                int l = rand.nextInt(world.getHeight(pos.add(j, 0, k)).getY() + 32);
                DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(j, l, k));
            }
        }

        super.decorate(world, rand, pos);
    }
}
