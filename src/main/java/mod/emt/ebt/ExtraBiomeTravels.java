package mod.emt.ebt;

import static mod.emt.ebt.ExtraBiomeTravels.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.emt.ebt.proxy.CommonProxy;
import mod.emt.ebt.util.EBTCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MOD_ID, name = NAME, version = VERSION, acceptedMinecraftVersions = ACCEPTED_VERSIONS)
public class ExtraBiomeTravels {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String MOD_PREFIX = MOD_ID + ":";
    public static final String NAME = Tags.MOD_NAME;
    public static final String VERSION = Tags.VERSION;
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final Logger LOGGER = LogManager.getLogger(NAME);
    public static final CreativeTabs TAB = new EBTCreativeTab(CreativeTabs.CREATIVE_TAB_ARRAY.length, MOD_ID + ".tab");

    public static final String CLIENT_PROXY = "mod.emt.ebt.proxy.ClientProxy";
    public static final String COMMON_PROXY = "mod.emt.ebt.proxy.CommonProxy";

    @Mod.Instance
    public static ExtraBiomeTravels instance;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info(NAME + " pre-initialized");
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info(NAME + " initialized");
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info(NAME + " post-initialized");
        proxy.postInit();
    }
}
