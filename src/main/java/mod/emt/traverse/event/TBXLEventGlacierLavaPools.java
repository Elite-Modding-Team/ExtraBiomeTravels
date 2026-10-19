package mod.emt.traverse.event;

import mod.emt.traverse.init.TBXLBiomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// Disables lava pools from spawning on Glaciers to prevent immersion breaking
public class TBXLEventGlacierLavaPools {
    @SubscribeEvent
    public void onPopulate(PopulateChunkEvent.Populate event) {
        BlockPos biomePos = new BlockPos(event.getChunkX() << 4, 0, event.getChunkZ() << 4);
        Biome biome = event.getWorld().getBiome(biomePos);
        if (biome == TBXLBiomes.GLACIER || biome == TBXLBiomes.GLACIER_SPIKES) {
            if (event.getType() == PopulateChunkEvent.Populate.EventType.LAVA) {
                event.setResult(Event.Result.DENY);
            }
        }
    }
}
