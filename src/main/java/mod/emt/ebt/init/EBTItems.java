package mod.emt.ebt.init;

import mod.emt.ebt.item.EBTItemDoor;
import mod.emt.ebt.item.EBTItemSlab;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class EBTItems {
    public static EBTItemDoor autumnal_door;
    public static EBTItemSlab autumnal_slab;
    public static EBTItemDoor bald_cypress_door;
    public static EBTItemSlab bald_cypress_slab;
    public static EBTItemSlab crag_rock_bricks_slab;
    public static EBTItemSlab crag_rock_cobblestone_slab;
    public static EBTItemSlab crag_rock_slab;
    public static EBTItemDoor cypress_door;
    public static EBTItemSlab cypress_slab;
    public static EBTItemDoor douglas_fir_door;
    public static EBTItemSlab douglas_fir_slab;
    public static EBTItemDoor japanese_maple_door;
    public static EBTItemSlab japanese_maple_slab;
    public static EBTItemDoor peach_sakura_door;
    public static EBTItemSlab peach_sakura_slab;
    public static EBTItemDoor rainbow_eucalyptus_door;
    public static EBTItemSlab rainbow_eucalyptus_slab;
    public static EBTItemSlab red_rock_bricks_slab;
    public static EBTItemSlab red_rock_cobblestone_slab;
    public static EBTItemSlab red_rock_slab;
    public static EBTItemDoor scalewood_door;
    public static EBTItemSlab scalewood_slab;
    public static EBTItemDoor wattle_door;
    public static EBTItemSlab wattle_slab;
    public static EBTItemDoor white_fir_door;
    public static EBTItemSlab white_fir_slab;

    public static final List<Item> MOD_ITEMS = new ArrayList<>();

    public static void initItems() {
        MOD_ITEMS.add(autumnal_door = new EBTItemDoor("autumnal_door", EBTBlocks.autumnal_door));
        MOD_ITEMS.add(autumnal_slab = new EBTItemSlab("autumnal_slab", EBTBlocks.autumnal_slab, EBTBlocks.autumnal_slab, EBTBlocks.autumnal_slab_double));
        MOD_ITEMS.add(bald_cypress_door = new EBTItemDoor("bald_cypress_door", EBTBlocks.bald_cypress_door));
        MOD_ITEMS.add(bald_cypress_slab = new EBTItemSlab("bald_cypress_slab", EBTBlocks.bald_cypress_slab, EBTBlocks.bald_cypress_slab, EBTBlocks.bald_cypress_slab_double));
        MOD_ITEMS.add(crag_rock_bricks_slab = new EBTItemSlab("crag_rock_bricks_slab", EBTBlocks.crag_rock_bricks_slab, EBTBlocks.crag_rock_bricks_slab, EBTBlocks.crag_rock_bricks_slab_double));
        MOD_ITEMS.add(crag_rock_cobblestone_slab = new EBTItemSlab("crag_rock_cobblestone_slab", EBTBlocks.crag_rock_cobblestone_slab, EBTBlocks.crag_rock_cobblestone_slab, EBTBlocks.crag_rock_cobblestone_slab_double));
        MOD_ITEMS.add(crag_rock_slab = new EBTItemSlab("crag_rock_slab", EBTBlocks.crag_rock_slab, EBTBlocks.crag_rock_slab, EBTBlocks.crag_rock_slab_double));
        MOD_ITEMS.add(cypress_door = new EBTItemDoor("cypress_door", EBTBlocks.cypress_door));
        MOD_ITEMS.add(cypress_slab = new EBTItemSlab("cypress_slab", EBTBlocks.cypress_slab, EBTBlocks.cypress_slab, EBTBlocks.cypress_slab_double));
        MOD_ITEMS.add(douglas_fir_door = new EBTItemDoor("douglas_fir_door", EBTBlocks.douglas_fir_door));
        MOD_ITEMS.add(douglas_fir_slab = new EBTItemSlab("douglas_fir_slab", EBTBlocks.douglas_fir_slab, EBTBlocks.douglas_fir_slab, EBTBlocks.douglas_fir_slab_double));
        MOD_ITEMS.add(japanese_maple_door = new EBTItemDoor("japanese_maple_door", EBTBlocks.japanese_maple_door));
        MOD_ITEMS.add(japanese_maple_slab = new EBTItemSlab("japanese_maple_slab", EBTBlocks.japanese_maple_slab, EBTBlocks.japanese_maple_slab, EBTBlocks.japanese_maple_slab_double));
        MOD_ITEMS.add(peach_sakura_door = new EBTItemDoor("peach_sakura_door", EBTBlocks.peach_sakura_door));
        MOD_ITEMS.add(peach_sakura_slab = new EBTItemSlab("peach_sakura_slab", EBTBlocks.peach_sakura_slab, EBTBlocks.peach_sakura_slab, EBTBlocks.peach_sakura_slab_double));
        MOD_ITEMS.add(rainbow_eucalyptus_door = new EBTItemDoor("rainbow_eucalyptus_door", EBTBlocks.rainbow_eucalyptus_door));
        MOD_ITEMS.add(rainbow_eucalyptus_slab = new EBTItemSlab("rainbow_eucalyptus_slab", EBTBlocks.rainbow_eucalyptus_slab, EBTBlocks.rainbow_eucalyptus_slab, EBTBlocks.rainbow_eucalyptus_slab_double));
        MOD_ITEMS.add(red_rock_bricks_slab = new EBTItemSlab("red_rock_bricks_slab", EBTBlocks.red_rock_bricks_slab, EBTBlocks.red_rock_bricks_slab, EBTBlocks.red_rock_bricks_slab_double));
        MOD_ITEMS.add(red_rock_cobblestone_slab = new EBTItemSlab("red_rock_cobblestone_slab", EBTBlocks.red_rock_cobblestone_slab, EBTBlocks.red_rock_cobblestone_slab, EBTBlocks.red_rock_cobblestone_slab_double));
        MOD_ITEMS.add(red_rock_slab = new EBTItemSlab("red_rock_slab", EBTBlocks.red_rock_slab, EBTBlocks.red_rock_slab, EBTBlocks.red_rock_slab_double));
        MOD_ITEMS.add(scalewood_door = new EBTItemDoor("scalewood_door", EBTBlocks.scalewood_door));
        MOD_ITEMS.add(scalewood_slab = new EBTItemSlab("scalewood_slab", EBTBlocks.scalewood_slab, EBTBlocks.scalewood_slab, EBTBlocks.scalewood_slab_double));
        MOD_ITEMS.add(wattle_door = new EBTItemDoor("wattle_door", EBTBlocks.wattle_door));
        MOD_ITEMS.add(wattle_slab = new EBTItemSlab("wattle_slab", EBTBlocks.wattle_slab, EBTBlocks.wattle_slab, EBTBlocks.wattle_slab_double));
        MOD_ITEMS.add(white_fir_door = new EBTItemDoor("white_fir_door", EBTBlocks.white_fir_door));
        MOD_ITEMS.add(white_fir_slab = new EBTItemSlab("white_fir_slab", EBTBlocks.white_fir_slab, EBTBlocks.white_fir_slab, EBTBlocks.white_fir_slab_double));
    }
}
