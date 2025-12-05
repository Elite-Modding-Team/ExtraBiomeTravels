package mod.emt.traverse;

import static mod.emt.traverse.TraverseBiomesXL.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.emt.traverse.proxy.CommonProxy;
import mod.emt.traverse.util.TBXLCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MOD_ID, name = NAME, version = VERSION, acceptedMinecraftVersions = ACCEPTED_VERSIONS)
public class TraverseBiomesXL {
    public static final String MOD_ID = "traverse";
    public static final String MOD_PREFIX = MOD_ID + ":";
    public static final String NAME = "TraverseBiomesXL";
    public static final String VERSION = "1.0.0";
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final Logger LOGGER = LogManager.getLogger(NAME);
    public static final CreativeTabs TAB = new TBXLCreativeTab(CreativeTabs.CREATIVE_TAB_ARRAY.length, MOD_ID + ".tab");

    public static final String CLIENT_PROXY = "mod.emt.traverse.proxy.ClientProxy";
    public static final String COMMON_PROXY = "mod.emt.traverse.proxy.CommonProxy";

    @Mod.Instance
    public static TraverseBiomesXL instance;

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
