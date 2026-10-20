package mod.emt.ebt.init;

import mod.emt.ebt.ExtraBiomeTravels;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public enum EBTSoundEvents {
    // Blocks
    hardenedSandBreak("blocks.hardened_sand.break"),
    hardenedSandFall("blocks.hardened_sand.fall"),
    hardenedSandHit("blocks.hardened_sand.hit"),
    hardenedSandPlace("blocks.hardened_sand.place"),
    hardenedSandStep("blocks.hardened_sand.step");


    private final SoundEvent soundEvent;

    EBTSoundEvents(String path) {
        ResourceLocation resourceLocation = new ResourceLocation(ExtraBiomeTravels.MOD_ID, path);
        this.soundEvent = new SoundEvent(resourceLocation);
        this.soundEvent.setRegistryName(resourceLocation);
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }
}
