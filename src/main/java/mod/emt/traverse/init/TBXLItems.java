package mod.emt.traverse.init;

import mod.emt.traverse.item.TBXLItemDoor;
import mod.emt.traverse.item.TBXLItemSlab;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class TBXLItems {
    public static TBXLItemDoor fir_door;
    public static TBXLItemSlab fir_slab;
    public static TBXLItemSlab red_rock_bricks_slab;
    public static TBXLItemSlab red_rock_cobblestone_slab;
    public static TBXLItemSlab red_rock_slab;

    public static final List<Item> MOD_ITEMS = new ArrayList<>();

    public static void initItems() {
        MOD_ITEMS.add(fir_door = new TBXLItemDoor("fir_door", TBXLBlocks.fir_door));
        MOD_ITEMS.add(fir_slab = new TBXLItemSlab("fir_slab", TBXLBlocks.fir_slab, TBXLBlocks.fir_slab, TBXLBlocks.fir_slab_double));
        MOD_ITEMS.add(red_rock_bricks_slab = new TBXLItemSlab("red_rock_bricks_slab", TBXLBlocks.red_rock_bricks_slab, TBXLBlocks.red_rock_bricks_slab, TBXLBlocks.red_rock_bricks_slab_double));
        MOD_ITEMS.add(red_rock_cobblestone_slab = new TBXLItemSlab("red_rock_cobblestone_slab", TBXLBlocks.red_rock_cobblestone_slab, TBXLBlocks.red_rock_cobblestone_slab, TBXLBlocks.red_rock_cobblestone_slab_double));
        MOD_ITEMS.add(red_rock_slab = new TBXLItemSlab("red_rock_slab", TBXLBlocks.red_rock_slab, TBXLBlocks.red_rock_slab, TBXLBlocks.red_rock_slab_double));
    }
}
