package mod.emt.traverse.world.biome.sub;

import java.util.Random;

import mod.emt.traverse.init.TBXLBiomes;
import mod.emt.traverse.init.TBXLBlocks;
import mod.emt.traverse.world.feature.tree.TBXLWorldGenTallTree;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.jetbrains.annotations.NotNull;

public class TBXLBiomeAutumnalOakForestHills extends Biome {
    public static Biome.BiomeProperties properties = new Biome.BiomeProperties("Autumnal Wooded Hills");

    static {
        properties.setTemperature(Biomes.FOREST_HILLS.getDefaultTemperature());
        properties.setRainfall(Biomes.FOREST_HILLS.getRainfall());
        properties.setBaseHeight(Biomes.FOREST_HILLS.getBaseHeight());
        properties.setHeightVariation(Biomes.FOREST_HILLS.getHeightVariation());

        // Important to properly set this as a sub-biome!
        properties.setBaseBiome(TBXLBiomes.AUTUMNAL_OAK_FOREST.getBiomeName());
    }

    public TBXLBiomeAutumnalOakForestHills() {
        super(properties);
        decorator.treesPerChunk = 10;
        decorator.flowersPerChunk = 4;
        decorator.grassPerChunk = 6;

        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        int num = rand.nextInt(6);

        if (num == 0) {
            return new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.brown_autumnal_oak_leaves.getDefaultState());
        } else if (num == 1) {
            return new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.orange_autumnal_oak_leaves.getDefaultState());
        } else if (num == 2) {
            return new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.purple_autumnal_oak_leaves.getDefaultState());
        } else if (num == 3) {
            return new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.red_autumnal_oak_leaves.getDefaultState());
        } else if (num == 4) {
            return new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.yellow_autumnal_oak_leaves.getDefaultState());
        } else {
            return new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.green_autumnal_oak_leaves.getDefaultState());
        }
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