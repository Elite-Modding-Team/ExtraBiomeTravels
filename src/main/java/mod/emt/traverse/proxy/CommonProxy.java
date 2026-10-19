package mod.emt.traverse.proxy;

import mod.emt.traverse.event.TBXLEventGlacierLavaPools;
import mod.emt.traverse.event.TBXLEventVillageBlocks;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    public void preInit() {
    }

    public void init() {
        MinecraftForge.TERRAIN_GEN_BUS.register(new TBXLEventVillageBlocks());
        MinecraftForge.TERRAIN_GEN_BUS.register(new TBXLEventGlacierLavaPools());
    }

    public void postInit() {
    }
}
