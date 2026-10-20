package mod.emt.ebt.block;

import mod.emt.ebt.ExtraBiomeTravels;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class EBTBlockFlower extends BlockBush {
    public EBTBlockFlower(String unlocName) {
        super(Material.PLANTS);
        this.setRegistryName(ExtraBiomeTravels.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(ExtraBiomeTravels.TAB);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
    }

    @Override
    public int getFlammability(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EnumFacing face) {
        return Blocks.RED_FLOWER.getFlammability(world, pos, face);
    }

    @Override
    public int getFireSpreadSpeed(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EnumFacing face) {
        return Blocks.RED_FLOWER.getFireSpreadSpeed(world, pos, face);
    }

    @Override
    public @NotNull EnumOffsetType getOffsetType() {
        return EnumOffsetType.XZ;
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return BUSH_AABB.offset(state.getOffset(world, pos));
    }

    @Override
    public @NotNull EnumPlantType getPlantType(@NotNull IBlockAccess world, @NotNull BlockPos pos) {
        return EnumPlantType.Plains;
    }
}
