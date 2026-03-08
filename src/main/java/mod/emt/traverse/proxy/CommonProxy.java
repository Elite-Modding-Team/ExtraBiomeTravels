package mod.emt.traverse.proxy;

import mod.emt.traverse.init.TBXLRegistry;

public class CommonProxy {
    public void preInit() {
    }

    public void init() {
        TBXLRegistry.registerOreDictionaries();
    }

    public void postInit() {
    }
}
