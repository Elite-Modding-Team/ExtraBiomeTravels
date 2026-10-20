package mod.emt.ebt.proxy;

import mod.emt.ebt.event.EBTEventGlacierLavaPools;
import mod.emt.ebt.event.EBTEventVillageBlocks;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    public void preInit() {
    }

    public void init() {
        MinecraftForge.TERRAIN_GEN_BUS.register(new EBTEventVillageBlocks());
        MinecraftForge.TERRAIN_GEN_BUS.register(new EBTEventGlacierLavaPools());
    }

    public void postInit() {
    }
}
