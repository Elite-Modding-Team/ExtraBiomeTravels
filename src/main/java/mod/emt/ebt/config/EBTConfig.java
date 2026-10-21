package mod.emt.ebt.config;

import mod.emt.ebt.ExtraBiomeTravels;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ExtraBiomeTravels.MOD_ID, name = "TraverseBiomesXL")
public class EBTConfig {
    @Config.LangKey("cfg.traversebiomesxl.general")
    @Config.Name("General")
    public static final GeneralSettings GENERAL = new GeneralSettings();

    @Config.LangKey("cfg.traversebiomesxl.biomes")
    @Config.Name("Biomes")
    public static final BiomeSettings BIOMES = new BiomeSettings();

    public static class GeneralSettings {
        /*@Config.Name("Debug Mode")
        @Config.Comment("Prints debug values to console")
        public boolean debugMode = false;*/
    }

    public static class BiomeSettings {
        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Autumnal Grove")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightAutumnalGrove = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Autumnal Oak Forest")
        @Config.Comment("Set to 0 to unregister this biome including sub-variants")
        @Config.RangeInt(min = 0)
        public int weightAutumnalOakForest = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Cliffs")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightCliffs = 3;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Coniferous Forest")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightConiferousForest = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Cypress Forest")
        @Config.Comment("Set to 0 to unregister this biome including sub-variants")
        @Config.RangeInt(min = 0)
        public int weightCypressForest = 3;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Cypress Swamp")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightCypressSwamp = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Flatlands")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightFlatlands = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Glacier")
        @Config.Comment("Set to 0 to unregister this biome including sub-variants")
        @Config.RangeInt(min = 0)
        public int weightGlacier = 3;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Lush Jungle")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightLushJungle = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Maple Shrublands")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightMapleShrublands = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Mountainous Crag")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightMountainousCrag = 3;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Prairie")
        @Config.Comment("Set to 0 to unregister this biome including sub-variants")
        @Config.RangeInt(min = 0)
        public int weightPrairie = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Rainforest")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightRainforest = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Red Desert")
        @Config.Comment("Set to 0 to unregister this biome including sub-variants")
        @Config.RangeInt(min = 0)
        public int weightRedDesert = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Red Rock Drylands")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightRedRockDrylands = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Red Rock Ridge")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightRedRockRidge = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Rocky Plains")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightRockyPlains = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Rolling Hills")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightRollingHills = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Snowy Coniferous Forest")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightSnowyConiferousForest = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Thicket")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightThicket = 3;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Wasteland")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightWasteland = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Woodlands")
        @Config.Comment("Set to 0 to unregister this biome")
        @Config.RangeInt(min = 0)
        public int weightWoodlands = 5;
    }

    @Mod.EventBusSubscriber(modid = ExtraBiomeTravels.MOD_ID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(ExtraBiomeTravels.MOD_ID)) {
                ConfigManager.sync(ExtraBiomeTravels.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
