package mod.emt.traverse.world.biome;

import mod.emt.traverse.init.TBXLBlocks;
import mod.emt.traverse.world.feature.tree.TBXLWorldGenAutumnalTree;
import mod.emt.traverse.world.feature.tree.TBXLWorldGenJapaneseMapleTree;
import mod.emt.traverse.world.feature.tree.TBXLWorldGenTallTree;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TBXLBiomeAutumnalGrove extends Biome {
    public static BiomeProperties properties = new BiomeProperties("Autumnal Grove");

    static {
        properties.setTemperature(Biomes.FOREST.getDefaultTemperature());
        properties.setRainfall(Biomes.FOREST.getRainfall());
        properties.setBaseHeight(0.8F);
        properties.setHeightVariation(0.2F);
    }

    public TBXLBiomeAutumnalGrove() {
        super(properties);
        decorator.treesPerChunk = 6;
        decorator.flowersPerChunk = 4;
        decorator.mushroomsPerChunk = 3;
        decorator.grassPerChunk = 6;
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        int num = rand.nextInt(6);
        if (num == 0) {
            return new TBXLWorldGenAutumnalTree(true, TBXLBlocks.autumnal_log.getDefaultState(), TBXLBlocks.citrine_autumnal_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
        } else if (num == 1) {
            return new TBXLWorldGenAutumnalTree(true, TBXLBlocks.autumnal_log.getDefaultState(), TBXLBlocks.goldenrod_autumnal_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
        } else if (num == 2) {
            return new TBXLWorldGenAutumnalTree(true, TBXLBlocks.autumnal_log.getDefaultState(), TBXLBlocks.umber_autumnal_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
        } else if (num == 3) {
            return new TBXLWorldGenAutumnalTree(true, TBXLBlocks.autumnal_log.getDefaultState(), TBXLBlocks.vermillion_autumnal_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
        } else if (num == 4) {
            return new TBXLWorldGenJapaneseMapleTree(true);
        } else {
            int num2 = rand.nextInt(6);
            if (num2 == 0) {
                return new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.brown_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
            } else if (num2 == 1) {
                return new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.orange_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
            } else if (num2 == 2) {
                return new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.purple_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
            } else if (num2 == 3) {
                return new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.red_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
            } else if (num2 == 4) {
                return new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.yellow_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
            } else {
                return new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.green_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
            }
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