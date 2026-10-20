package mod.emt.ebt.world.biome;

import java.util.Random;

import mod.emt.ebt.world.feature.tree.EBTWorldGenFirTree;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import org.jetbrains.annotations.NotNull;

public class EBTBiomeCliffs extends Biome {
    protected static final EBTWorldGenFirTree FIR_TREE_FEATURE = new EBTWorldGenFirTree(true);
    WorldGenTaiga2 SPRUCE_TREE_FEATURE2 = new WorldGenTaiga2(false);

    public static BiomeProperties properties = new BiomeProperties("Cliffs");

    static {
        properties.setTemperature(0.22F);
        properties.setRainfall(0.4F);
        properties.setBaseHeight(4.5F);
        properties.setHeightVariation(0.2F);
    }

    public EBTBiomeCliffs() {
        super(properties);
        this.topBlock = Blocks.STONE.getDefaultState();
        this.fillerBlock = Blocks.STONE.getDefaultState();
        this.spawnableCreatureList.clear();

        /*if (this.getBaseHeight() > 4.4F)
        {
            this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityLlama.class, 5, 4, 6));
        }*/

        this.decorator.treesPerChunk = 2;
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(2) == 0) {
            return SPRUCE_TREE_FEATURE2;
        }

        return FIR_TREE_FEATURE;
    }
}