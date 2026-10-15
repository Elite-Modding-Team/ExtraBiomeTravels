package mod.emt.traverse.world.biome;

import mod.emt.traverse.world.feature.tree.TBXLWorldGenBaldCypressTree;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TBXLBiomeCypressSwamp extends BiomeSwamp {
    public static BiomeProperties properties = new BiomeProperties("Cypress Swampland");

    static {
        properties.setTemperature(Biomes.SWAMPLAND.getDefaultTemperature());
        properties.setRainfall(Biomes.SWAMPLAND.getRainfall());
        properties.setBaseHeight(Biomes.SWAMPLAND.getBaseHeight());
        properties.setHeightVariation(Biomes.SWAMPLAND.getHeightVariation());
    }

    public TBXLBiomeCypressSwamp() {
        super(properties);
        this.decorator.flowersPerChunk = 6;
        this.decorator.deadBushPerChunk = 0;
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(@NotNull Random rand) {
        return rand.nextInt(5) == 0 ? SWAMP_FEATURE : new TBXLWorldGenBaldCypressTree(true);
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return super.getModdedBiomeGrassColor(0x4DD838);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0x4DD838);
    }
}