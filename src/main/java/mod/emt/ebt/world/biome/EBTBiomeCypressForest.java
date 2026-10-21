package mod.emt.ebt.world.biome;

import mod.emt.ebt.init.EBTBlocks;
import mod.emt.ebt.world.feature.tree.EBTWorldGenCypressTree;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBirchTree;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EBTBiomeCypressForest extends Biome {
    public static BiomeProperties properties = new BiomeProperties("Cypress Forest");

    static {
        properties.setTemperature(Biomes.FOREST.getDefaultTemperature());
        properties.setRainfall(Biomes.FOREST.getRainfall());
        properties.setBaseHeight(Biomes.FOREST.getBaseHeight());
        properties.setHeightVariation(Biomes.FOREST.getHeightVariation());
    }

    public EBTBiomeCypressForest() {
        super(properties);
        decorator.treesPerChunk = 5;
        decorator.flowersPerChunk = 4;
        decorator.grassPerChunk = 4;
        this.addFlower(EBTBlocks.toadstools.getDefaultState(), 10);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        int num = rand.nextInt(3);

        if (num < 1) {
            return new WorldGenBirchTree(false, true);
        }

        return new EBTWorldGenCypressTree(true);
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFFAAFFFF;
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return super.getModdedBiomeGrassColor(0xFF88C78C);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0xFF88C78C);
    }
}