package mod.emt.traverse.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

public class TBXLBlockPlanks extends TBXLBlockMaterial {
    public boolean flammable;

    public TBXLBlockPlanks(String unlocName, MapColor mapColor, float hardness, float resistance, boolean flammable) {
        super(unlocName, Material.WOOD, mapColor, hardness, resistance, SoundType.WOOD);
        this.setSoundType(SoundType.WOOD);
        this.flammable = flammable;
    }

    public TBXLBlockPlanks(String unlocName, Material material, MapColor mapColor, float hardness, float resistance) {
        super(unlocName, material, mapColor, hardness, resistance, SoundType.WOOD);
    }

    public boolean isFlammable() {
        return flammable;
    }

    @Override
    public int getFlammability(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EnumFacing face) {
        if (isFlammable()) {
            return Blocks.PLANKS.getFlammability(world, pos, face);
        } else {
            return 0;
        }
    }

    @Override
    public int getFireSpreadSpeed(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EnumFacing face) {
        if (isFlammable()) {
            return Blocks.PLANKS.getFireSpreadSpeed(world, pos, face);
        } else {
            return 0;
        }
    }
}
