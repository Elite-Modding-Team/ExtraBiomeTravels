package mod.emt.traverse.block;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class TBXLBlockSlab extends BlockSlab {
    public static final PropertyEnum<Variant> VARIANT = PropertyEnum.create("variant", Variant.class);

    public boolean flammable;

    public TBXLBlockSlab(Material material, MapColor mapColor, boolean flammable) {
        super(material, mapColor);

        IBlockState iblockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

        if (!this.isDouble()) {
            iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(iblockstate);
        this.useNeighborBrightness = !this.isDouble();
        this.setSoundType(material == Material.WOOD ? SoundType.WOOD : SoundType.STONE);
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

    @Override
    public String getTranslationKey(int meta) {
        return super.getTranslationKey();
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return Variant.DEFAULT;
    }

    @Override
    public final IBlockState getStateFromMeta(final int meta) {
        IBlockState blockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

        if (!this.isDouble()) {
            blockstate = blockstate.withProperty(HALF, ((meta & 8) != 0) ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
        }

        return blockstate;
    }

    @Override
    public final int getMetaFromState(final IBlockState state) {
        int meta = 0;

        if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) {
            meta |= 8;
        }

        return meta;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        if (!this.isDouble()) {
            return new BlockStateContainer(this, VARIANT, HALF);
        }
        return new BlockStateContainer(this, VARIANT);
    }

    public enum Variant implements IStringSerializable {
        DEFAULT;

        @Override
        public String getName() {
            return "default";
        }
    }

    public static class Double extends TBXLBlockSlab {

        public Double(Material material, MapColor mapColor, boolean flammable) {
            super(material, mapColor, flammable);
        }

        @Override
        public boolean isDouble() {
            return true;
        }

    }

    public static class Half extends TBXLBlockSlab {

        public Half(Material material, MapColor mapColor, boolean flammable) {
            super(material, mapColor, flammable);
        }

        @Override
        public boolean isDouble() {
            return false;
        }
    }
}
