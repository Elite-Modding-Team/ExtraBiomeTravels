package mod.emt.ebt.world.biome;

import mod.emt.ebt.world.feature.EBTWorldGenPatch;
import mod.emt.ebt.world.feature.EBTWorldGenSurfacePatch;
import mod.emt.ebt.world.feature.tree.EBTWorldGenSimpleFallenTree;
import net.minecraft.block.*;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@SuppressWarnings("deprecation")
public class EBTBiomeThicket extends Biome {
    protected static final EBTWorldGenSurfacePatch PODZOL_PATCH_FEATURE = new EBTWorldGenSurfacePatch(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL), 6);
    protected static final EBTWorldGenPatch GRAVEL_PATCH_FEATURE = new EBTWorldGenPatch(Blocks.GRAVEL.getDefaultState(), 4);
    protected static final EBTWorldGenSimpleFallenTree OAK_FALLEN_TREE_FEATURE = new EBTWorldGenSimpleFallenTree(true);
    protected static final WorldGenShrub OAK_SHRUB_FEATURE = new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK),
            Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, false));

    public static BiomeProperties properties = new BiomeProperties("Thicket");

    static {
        properties.setTemperature(0.8F);
        properties.setRainfall(0.3F);
        properties.setBaseHeight(0.2F);
        properties.setHeightVariation(0.3F);
    }

    public EBTBiomeThicket() {
        super(properties);
        decorator.treesPerChunk = 20;
        decorator.extraTreeChance = 0.05F;
        decorator.flowersPerChunk = 0;
        decorator.grassPerChunk = 5;

        spawnableCreatureList.clear();
        spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 4, 2, 4));
        spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 3, 2, 4));
        spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 3, 2, 4));
        spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 2, 2, 4));
        spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 1, 2, 7));
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(10) == 0) {
            return new WorldGenCanopyTree(false);
        }

        return TREE_FEATURE;
    }

    @Override
    public void genTerrainBlocks(@NotNull World world, @NotNull Random rand, @NotNull ChunkPrimer chunkPrimer, int x, int z, double noiseVal) {
        if (noiseVal > 1.5D) {
            this.topBlock = Blocks.GRASS.getDefaultState();
        } else {
            this.topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
        }
        this.fillerBlock = Blocks.DIRT.getDefaultState();
        this.generateBiomeTerrain(world, rand, chunkPrimer, x, z, noiseVal);
    }

    @Override
    public void decorate(@NotNull World world, Random rand, @NotNull BlockPos pos) {
        int podzolChance = rand.nextInt(4);
        if (podzolChance == 0) {
            int k6 = rand.nextInt(16) + 8;
            int l = rand.nextInt(16) + 8;
            BlockPos blockpos = world.getHeight(pos.add(k6, 0, l));
            PODZOL_PATCH_FEATURE.generate(world, rand, blockpos);
        }

        int gravelChance = rand.nextInt(5);
        if (gravelChance == 0) {
            int k6 = rand.nextInt(16) + 8;
            int l = rand.nextInt(16) + 8;
            BlockPos blockpos = world.getHeight(pos.add(k6, 0, l));
            GRAVEL_PATCH_FEATURE.generate(world, rand, blockpos);
        }

        if (TerrainGen.decorate(world, rand, pos, DecorateBiomeEvent.Decorate.EventType.TREE)) {
            int fallenTreeChance = rand.nextInt(2);
            if (fallenTreeChance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = world.getHeight(pos.add(k6, 0, l));
                OAK_FALLEN_TREE_FEATURE.generate(world, rand, blockpos);
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

        if (TerrainGen.decorate(world, rand, pos, DecorateBiomeEvent.Decorate.EventType.TREE)) {
            if (rand.nextInt(2) == 0) {
                int x = rand.nextInt(16) + 8;
                int z = rand.nextInt(16) + 8;
                BlockPos shrubPos = world.getHeight(pos.add(x, 0, z));
                OAK_SHRUB_FEATURE.generate(world, rand, shrubPos);
            }
        }

        super.decorate(world, rand, pos);
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFF88B4E1;
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return super.getModdedBiomeGrassColor(0xFF74CD61);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0xFF6DAF61);
    }
}