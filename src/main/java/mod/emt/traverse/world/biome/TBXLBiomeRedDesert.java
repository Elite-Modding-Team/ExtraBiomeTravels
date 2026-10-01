package mod.emt.traverse.world.biome;

import net.minecraft.block.BlockSand;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDesert;

// It's the vanilla desert but with red sand
public class TBXLBiomeRedDesert extends BiomeDesert {
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
        this.topBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
        this.fillerBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
        decorator.generateFalls = false;
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