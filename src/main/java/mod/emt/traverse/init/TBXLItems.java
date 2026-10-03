package mod.emt.traverse.init;

import mod.emt.traverse.item.TBXLItemDoor;
import mod.emt.traverse.item.TBXLItemSlab;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class TBXLItems {
    public static TBXLItemDoor autumnal_door;
    public static TBXLItemSlab autumnal_slab;
    public static TBXLItemDoor bald_cypress_door;
    public static TBXLItemSlab bald_cypress_slab;
    public static TBXLItemDoor cypress_door;
    public static TBXLItemSlab cypress_slab;
    public static TBXLItemDoor douglas_fir_door;
    public static TBXLItemSlab douglas_fir_slab;
    public static TBXLItemDoor japanese_maple_door;
    public static TBXLItemSlab japanese_maple_slab;
    public static TBXLItemDoor peach_sakura_door;
    public static TBXLItemSlab peach_sakura_slab;
    public static TBXLItemDoor rainbow_eucalyptus_door;
    public static TBXLItemSlab rainbow_eucalyptus_slab;
    public static TBXLItemSlab red_rock_bricks_slab;
    public static TBXLItemSlab red_rock_cobblestone_slab;
    public static TBXLItemSlab red_rock_slab;
    public static TBXLItemDoor scalewood_door;
    public static TBXLItemSlab scalewood_slab;
    public static TBXLItemDoor wattle_door;
    public static TBXLItemSlab wattle_slab;
    public static TBXLItemDoor white_fir_door;
    public static TBXLItemSlab white_fir_slab;

    public static final List<Item> MOD_ITEMS = new ArrayList<>();

    public static void initItems() {
        MOD_ITEMS.add(autumnal_door = new TBXLItemDoor("autumnal_door", TBXLBlocks.autumnal_door));
        MOD_ITEMS.add(autumnal_slab = new TBXLItemSlab("autumnal_slab", TBXLBlocks.autumnal_slab, TBXLBlocks.autumnal_slab, TBXLBlocks.autumnal_slab_double));
        MOD_ITEMS.add(bald_cypress_door = new TBXLItemDoor("bald_cypress_door", TBXLBlocks.bald_cypress_door));
        MOD_ITEMS.add(bald_cypress_slab = new TBXLItemSlab("bald_cypress_slab", TBXLBlocks.bald_cypress_slab, TBXLBlocks.bald_cypress_slab, TBXLBlocks.bald_cypress_slab_double));
        MOD_ITEMS.add(cypress_door = new TBXLItemDoor("cypress_door", TBXLBlocks.cypress_door));
        MOD_ITEMS.add(cypress_slab = new TBXLItemSlab("cypress_slab", TBXLBlocks.cypress_slab, TBXLBlocks.cypress_slab, TBXLBlocks.cypress_slab_double));
        MOD_ITEMS.add(douglas_fir_door = new TBXLItemDoor("douglas_fir_door", TBXLBlocks.douglas_fir_door));
        MOD_ITEMS.add(douglas_fir_slab = new TBXLItemSlab("douglas_fir_slab", TBXLBlocks.douglas_fir_slab, TBXLBlocks.douglas_fir_slab, TBXLBlocks.douglas_fir_slab_double));
        MOD_ITEMS.add(japanese_maple_door = new TBXLItemDoor("japanese_maple_door", TBXLBlocks.japanese_maple_door));
        MOD_ITEMS.add(japanese_maple_slab = new TBXLItemSlab("japanese_maple_slab", TBXLBlocks.japanese_maple_slab, TBXLBlocks.japanese_maple_slab, TBXLBlocks.japanese_maple_slab_double));
        MOD_ITEMS.add(peach_sakura_door = new TBXLItemDoor("peach_sakura_door", TBXLBlocks.peach_sakura_door));
        MOD_ITEMS.add(peach_sakura_slab = new TBXLItemSlab("peach_sakura_slab", TBXLBlocks.peach_sakura_slab, TBXLBlocks.peach_sakura_slab, TBXLBlocks.peach_sakura_slab_double));
        MOD_ITEMS.add(rainbow_eucalyptus_door = new TBXLItemDoor("rainbow_eucalyptus_door", TBXLBlocks.rainbow_eucalyptus_door));
        MOD_ITEMS.add(rainbow_eucalyptus_slab = new TBXLItemSlab("rainbow_eucalyptus_slab", TBXLBlocks.rainbow_eucalyptus_slab, TBXLBlocks.rainbow_eucalyptus_slab, TBXLBlocks.rainbow_eucalyptus_slab_double));
        MOD_ITEMS.add(red_rock_bricks_slab = new TBXLItemSlab("red_rock_bricks_slab", TBXLBlocks.red_rock_bricks_slab, TBXLBlocks.red_rock_bricks_slab, TBXLBlocks.red_rock_bricks_slab_double));
        MOD_ITEMS.add(red_rock_cobblestone_slab = new TBXLItemSlab("red_rock_cobblestone_slab", TBXLBlocks.red_rock_cobblestone_slab, TBXLBlocks.red_rock_cobblestone_slab, TBXLBlocks.red_rock_cobblestone_slab_double));
        MOD_ITEMS.add(red_rock_slab = new TBXLItemSlab("red_rock_slab", TBXLBlocks.red_rock_slab, TBXLBlocks.red_rock_slab, TBXLBlocks.red_rock_slab_double));
        MOD_ITEMS.add(scalewood_door = new TBXLItemDoor("scalewood_door", TBXLBlocks.scalewood_door));
        MOD_ITEMS.add(scalewood_slab = new TBXLItemSlab("scalewood_slab", TBXLBlocks.scalewood_slab, TBXLBlocks.scalewood_slab, TBXLBlocks.scalewood_slab_double));
        MOD_ITEMS.add(wattle_door = new TBXLItemDoor("wattle_door", TBXLBlocks.wattle_door));
        MOD_ITEMS.add(wattle_slab = new TBXLItemSlab("wattle_slab", TBXLBlocks.wattle_slab, TBXLBlocks.wattle_slab, TBXLBlocks.wattle_slab_double));
        MOD_ITEMS.add(white_fir_door = new TBXLItemDoor("white_fir_door", TBXLBlocks.white_fir_door));
        MOD_ITEMS.add(white_fir_slab = new TBXLItemSlab("white_fir_slab", TBXLBlocks.white_fir_slab, TBXLBlocks.white_fir_slab, TBXLBlocks.white_fir_slab_double));
    }
}
