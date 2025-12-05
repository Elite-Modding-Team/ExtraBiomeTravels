package mod.emt.traverse.world.biome;

import mod.emt.traverse.world.WorldGenConstants;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeDesert;

// It's the vanilla desert but with red sand
public class TBXLBiomeRedDesert extends BiomeDesert implements WorldGenConstants {
    public static BiomeProperties properties = new BiomeProperties("Red Desert");

    static {
        properties.setTemperature(Biomes.DESERT.getDefaultTemperature());
        properties.setRainfall(Biomes.DESERT.getRainfall());
        properties.setRainDisabled();
        properties.setBaseHeight(Biomes.DESERT.getBaseHeight());
        properties.setHeightVariation(Biomes.DESERT.getHeightVariation());
    }

    public TBXLBiomeRedDesert() {
        super(properties);
        this.topBlock = RED_SAND;
        this.fillerBlock = RED_SAND;
        decorator.generateFalls = false;
    }
}