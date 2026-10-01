package mod.emt.traverse.block;

import mod.emt.traverse.TraverseBiomesXL;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

@SuppressWarnings("deprecation")
public abstract class TBXLBlockSlab extends BlockSlab {
    public static final PropertyEnum<Variant> VARIANT = PropertyEnum.create("variant", Variant.class);

    public boolean flammable;

    public TBXLBlockSlab(String name, Material material, MapColor mapColor, boolean flammable, float hardness, float resistance) {
        super(material, mapColor);
        this.setRegistryName(TraverseBiomesXL.MOD_ID, name);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(TraverseBiomesXL.TAB);
        IBlockState iblockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

        if (!this.isDouble()) {
            iblockstate = iblockstate.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(iblockstate);
        this.useNeighborBrightness = !this.isDouble();
        this.setSoundType(material == Material.WOOD ? SoundType.WOOD : SoundType.STONE);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.flammable = flammable;
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

    @Override
    public @NotNull String getTranslationKey(int meta) {
        return super.getTranslationKey();
    }

    @Override
    public @NotNull IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public @NotNull Comparable<?> getTypeForItem(@NotNull ItemStack stack) {
        return Variant.DEFAULT;
    }

    @Override
    public final @NotNull IBlockState getStateFromMeta(final int meta) {
        IBlockState blockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);

        if (!this.isDouble()) {
            blockstate = blockstate.withProperty(HALF, ((meta & 8) != 0) ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
        }

        return blockstate;
    }

    @Override
    public final int getMetaFromState(final @NotNull IBlockState state) {
        int meta = 0;

        if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) {
            meta |= 8;
        }

        return meta;
    }

    @Override
    protected @NotNull BlockStateContainer createBlockState() {
        if (!this.isDouble()) {
            return new BlockStateContainer(this, VARIANT, HALF);
        }
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public @NotNull Item getItemDropped(@NotNull IBlockState state, @NotNull Random rand, int fortune) {
        if(this.isDouble()) {
            Block singleBlock = Block.getBlockFromName(Objects.requireNonNull(this.getRegistryName()).toString().replace("_double", ""));
            return singleBlock != null ? Item.getItemFromBlock(singleBlock) : Items.AIR;
        }

        return Item.getItemFromBlock(this);
    }

    public enum Variant implements IStringSerializable {
        DEFAULT;

        @Override
        public @NotNull String getName() {
            return "default";
        }
    }

    public static class Double extends TBXLBlockSlab {
        public Double(String name, Material material, MapColor mapColor, float hardness, float resistance, boolean flammable) {
            super(name + "_double", material, mapColor, flammable, hardness, resistance);
            this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString().replace("_double", ""));
        }

        @Override
        public boolean isDouble() {
            return true;
        }
    }

    public static class Half extends TBXLBlockSlab {
        public Half(String name, Material material, MapColor mapColor, float hardness, float resistance, boolean flammable) {
            super(name, material, mapColor, flammable, hardness, resistance);
        }

        @Override
        public boolean isDouble() {
            return false;
        }
    }
}
