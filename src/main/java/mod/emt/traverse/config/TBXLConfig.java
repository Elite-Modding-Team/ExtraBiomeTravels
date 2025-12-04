package mod.emt.traverse.config;

import mod.emt.traverse.TraverseBiomesXL;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = TraverseBiomesXL.MOD_ID, name = "TraverseBiomesXL")
public class TBXLConfig {
    @Config.LangKey("cfg.traversebiomesxl.general")
    @Config.Name("General")
    public static final GeneralSettings GENERAL = new GeneralSettings();

    public static class GeneralSettings {
        /*@Config.Name("Debug Mode")
        @Config.Comment("Prints debug values to console")
        public boolean debugMode = false;*/
    }

    @Mod.EventBusSubscriber(modid = TraverseBiomesXL.MOD_ID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(TraverseBiomesXL.MOD_ID)) {
                ConfigManager.sync(TraverseBiomesXL.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
