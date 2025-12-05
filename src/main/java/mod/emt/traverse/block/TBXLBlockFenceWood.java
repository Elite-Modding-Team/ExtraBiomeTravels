package mod.emt.traverse.block;

import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

// NOTE: Wooden buttons are not flammable in vanilla
public class TBXLBlockFenceWood extends BlockFence {
    public boolean flammable;

    public TBXLBlockFenceWood(MapColor mapColor, boolean flammable) {
        super(Material.WOOD, mapColor);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setHarvestLevel("axe", 0);
        this.flammable = flammable;
    }

    public boolean isFlammable() {
        return flammable;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        if (isFlammable()) {
            return Blocks.PLANKS.getFlammability(world, pos, face);
        } else {
            return 0;
        }
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        if (isFlammable()) {
            return Blocks.PLANKS.getFireSpreadSpeed(world, pos, face);
        } else {
            return 0;
        }
    }
}
