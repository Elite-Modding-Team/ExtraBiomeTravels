package mod.emt.traverse.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

import mod.emt.traverse.init.TBXLBlocks;

public class TBXLBlockRock extends TBXLBlockMaterial {
    public TBXLBlockRock(MapColor mapColor) {
        super(Material.ROCK, mapColor, 1.5F, 10.0F, SoundType.STONE);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (this == TBXLBlocks.red_rock) {
            return TBXLBlocks.red_rock_cobblestone.getItemDropped(TBXLBlocks.red_rock_cobblestone.getDefaultState(), rand, fortune);
        } else {
            return Item.getItemFromBlock(this);
        }
    }
}
