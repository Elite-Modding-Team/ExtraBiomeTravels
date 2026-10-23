package mod.emt.ebt.config;

import mod.emt.ebt.ExtraBiomeTravels;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ExtraBiomeTravels.MOD_ID, name = "Extra Biome Travels")
public class EBTConfig {
    @Config.LangKey("cfg.ebt.general")
    @Config.Name("General")
    public static final GeneralSettings GENERAL = new GeneralSettings();

    @Config.LangKey("cfg.ebt.biomes")
    @Config.Name("Biomes")
    public static final BiomeSettings BIOMES = new BiomeSettings();

    public static class GeneralSettings {
        /*@Config.Name("Debug Mode")
        @Config.Comment("Prints debug values to console")
        public boolean debugMode = false;*/
    }

    public static class BiomeSettings {
        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Autumnal Grove")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableAutumnalGrove = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Autumnal Oak Forest")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableAutumnalOakForest = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Cliffs")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableCliffs = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Coniferous Forest")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableConiferousForest = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Cypress Forest")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableCypressForest = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Cypress Swamp")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableCypressSwamp = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Flatlands")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableFlatlands = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Glacier")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableGlacier = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Lush Jungle")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableLushJungle = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Maple Shrublands")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableMapleShrublands = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Mountainous Crag")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableMountainousCrag = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Plains Plateau")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enablePlainsPlateau = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Prairie")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enablePrairie = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Rainforest")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableRainforest = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Red Desert")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableRedDesert = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Red Rock Drylands")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableRedRockDrylands = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Red Rock Ridge")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableRedRockRidge = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Rocky Plains")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableRockyPlains = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Rolling Hills")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableRollingHills = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Snowy Coniferous Forest")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableSnowyConiferousForest = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Thicket")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableThicket = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Wasteland")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enabletWasteland = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Enable: Woodlands")
        @Config.Comment("Set to false to unregister this biome")
        public boolean enableWoodlands = true;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Autumnal Grove")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightAutumnalGrove = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Autumnal Oak Forest")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightAutumnalOakForest = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Cliffs")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightCliffs = 3;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Coniferous Forest")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightConiferousForest = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Cypress Forest")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightCypressForest = 3;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Cypress Swamp")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightCypressSwamp = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Flatlands")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightFlatlands = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Glacier")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightGlacier = 3;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Lush Jungle")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightLushJungle = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Maple Shrublands")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightMapleShrublands = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Mountainous Crag")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightMountainousCrag = 3;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Plains Plateau")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightPlainsPlateau = 3;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Prairie")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightPrairie = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Rainforest")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightRainforest = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Red Desert")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightRedDesert = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Red Rock Drylands")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightRedRockDrylands = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Red Rock Ridge")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightRedRockRidge = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Rocky Plains")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightRockyPlains = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Rolling Hills")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightRollingHills = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Snowy Coniferous Forest")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightSnowyConiferousForest = 5;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Thicket")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightThicket = 3;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Wasteland")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightWasteland = 4;

        @Config.RequiresMcRestart
        @Config.Name("Biome Weight: Woodlands")
        @Config.Comment("Adjusts the spawn weight, higher numbers make it more common, while 0 completely prevents it from generating")
        @Config.RangeInt(min = 0)
        public int weightWoodlands = 5;

        @Config.RequiresMcRestart
        @Config.Name("Sub-Biome Enable: Autumnal Oak Forest Hills")
        @Config.Comment({"[Requires Autumnal Oak Forest] Set to false to unregister this sub-biome", "Note that sub-biomes cannot have their spawn weight adjusted!"})
        public boolean enableAutumnalOakForestHills = true;

        @Config.RequiresMcRestart
        @Config.Name("Sub-Biome Enable: Cypress Forest Hills")
        @Config.Comment({"[Requires Cypress Forest] Set to false to unregister this sub-biome", "Note that sub-biomes cannot have their spawn weight adjusted!"})
        public boolean enableCypressForestHills = true;

        @Config.RequiresMcRestart
        @Config.Name("Sub-Biome Enable: Glacier Spikes")
        @Config.Comment({"[Requires Glacier] Set to false to unregister this sub-biome", "Note that sub-biomes cannot have their spawn weight adjusted!"})
        public boolean enableGlacierSpikes = true;

        @Config.RequiresMcRestart
        @Config.Name("Sub-Biome Enable: Prairie Shrublands")
        @Config.Comment({"[Requires Prairie] Set to false to unregister this sub-biome", "Note that sub-biomes cannot have their spawn weight adjusted!"})
        public boolean enablePrairieShrublands = true;

        @Config.RequiresMcRestart
        @Config.Name("Sub-Biome Enable: Wooded Red Desert")
        @Config.Comment({"[Requires Red Desert] Set to false to unregister this sub-biome", "Note that sub-biomes cannot have their spawn weight adjusted!"})
        public boolean enableWoodedRedDesert = true;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Autumnal Grove")
        public boolean villageGenAutumnalGrove = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Autumnal Oak Forest")
        public boolean villageGenAutumnalOakForest = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Cliffs")
        public boolean villageGenCliffs = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Coniferous Forest")
        public boolean villageGenConiferousForest = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Cypress Forest")
        public boolean villageGenCypressForest = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Cypress Swamp")
        public boolean villageGenCypressSwamp = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Flatlands")
        public boolean villageGenFlatlands = true;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Glacier")
        public boolean villageGenGlacier = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Lush Jungle")
        public boolean villageGenLushJungle = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Maple Shrublands")
        public boolean villageGenMapleShrublands = true;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Mountainous Crag")
        public boolean villageGenMountainousCrag = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Plains Plateau")
        public boolean villageGenPlainsPlateau = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Prairie")
        public boolean villageGenPrairie = true;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Rainforest")
        public boolean villageGenRainforest = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Red Desert")
        public boolean villageGenRedDesert = true;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Red Rock Drylands")
        public boolean villageGenRedRockDrylands = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Red Rock Ridge")
        public boolean villageGenRedRockRidge = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Rocky Plains")
        public boolean villageGenRockyPlains = true;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Rolling Hills")
        public boolean villageGenRollingHills = true;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Snowy Coniferous Forest")
        public boolean villageGenSnowyConiferousForest = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Thicket")
        public boolean villageGenThicket = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Wasteland")
        public boolean villageGenWasteland = false;

        @Config.RequiresMcRestart
        @Config.Name("Village Generation: Woodlands")
        public boolean villageGenWoodlands = true;
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
