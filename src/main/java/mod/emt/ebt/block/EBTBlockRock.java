package mod.emt.ebt.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

import mod.emt.ebt.init.EBTBlocks;
import org.jetbrains.annotations.NotNull;

public class EBTBlockRock extends EBTBlockMaterial {
    public EBTBlockRock(String unlocName, MapColor mapColor) {
        super(unlocName, Material.ROCK, mapColor, 1.5F, 10.0F, SoundType.STONE);
    }

    @Override
    public @NotNull Item getItemDropped(@NotNull IBlockState state, @NotNull Random rand, int fortune) {
        if (this == EBTBlocks.crag_rock) {
            return EBTBlocks.crag_rock_cobblestone.getItemDropped(EBTBlocks.crag_rock_cobblestone.getDefaultState(), rand, fortune);
        } if (this == EBTBlocks.red_rock) {
            return EBTBlocks.red_rock_cobblestone.getItemDropped(EBTBlocks.red_rock_cobblestone.getDefaultState(), rand, fortune);
        } else {
            return Item.getItemFromBlock(this);
        }
    }
}
