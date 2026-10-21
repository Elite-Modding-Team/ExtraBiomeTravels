package mod.emt.ebt.world.biome.sub;

import java.util.Random;

import mod.emt.ebt.init.EBTBlocks;
import mod.emt.ebt.world.feature.EBTWorldGenPlant;
import mod.emt.ebt.world.feature.tree.EBTWorldGenTallTree;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.jetbrains.annotations.NotNull;

public class EBTBiomeAutumnalOakForestHills extends Biome {
    public static Biome.BiomeProperties properties = new Biome.BiomeProperties("Autumnal Oak Forest Hills");

    public static final WorldGenerator AUTUMNAL_SHRUB_FEATURE = new EBTWorldGenPlant(EBTBlocks.autumnal_shrub.getDefaultState());

    static {
        properties.setTemperature(Biomes.FOREST_HILLS.getDefaultTemperature());
        properties.setRainfall(Biomes.FOREST_HILLS.getRainfall());
        properties.setBaseHeight(Biomes.FOREST_HILLS.getBaseHeight());
        properties.setHeightVariation(Biomes.FOREST_HILLS.getHeightVariation());

        // Important to properly set this as a sub-biome!
        properties.setBaseBiome("Autumnal Oak Forest");
    }

    public EBTBiomeAutumnalOakForestHills() {
        super(properties);
        decorator.treesPerChunk = 10;
        decorator.flowersPerChunk = 4;
        decorator.grassPerChunk = 6;
        this.addFlower(EBTBlocks.autumnal_shrub.getDefaultState(), 10);
        //this.addFlower(EBTBlocks.toadstools.getDefaultState(), 10);
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        int num = rand.nextInt(6);

        if (num == 0) {
            return new EBTWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), EBTBlocks.brown_autumnal_oak_leaves.getDefaultState());
        } else if (num == 1) {
            return new EBTWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), EBTBlocks.orange_autumnal_oak_leaves.getDefaultState());
        } else if (num == 2) {
            return new EBTWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), EBTBlocks.purple_autumnal_oak_leaves.getDefaultState());
        } else if (num == 3) {
            return new EBTWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), EBTBlocks.red_autumnal_oak_leaves.getDefaultState());
        } else if (num == 4) {
            return new EBTWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), EBTBlocks.yellow_autumnal_oak_leaves.getDefaultState());
        } else {
            return new EBTWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), EBTBlocks.green_autumnal_oak_leaves.getDefaultState());
        }
    }

    @Override
    public @NotNull WorldGenerator getRandomWorldGenForGrass(@NotNull Random rand) {
        return rand.nextInt(5) > 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS) : AUTUMNAL_SHRUB_FEATURE;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFFEFECD9;
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return super.getModdedBiomeGrassColor(0xFFD6C23D);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0xFFD2D31F);
    }
}