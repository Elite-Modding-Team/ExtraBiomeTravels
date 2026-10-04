package mod.emt.traverse.block;

import mod.emt.traverse.TraverseBiomesXL;
import mod.emt.traverse.init.TBXLBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings("deprecation")
public class TBXLBlockLeaves extends BlockLeaves {
    private final MapColor mapColor;
    public boolean flammable;
    public int saplingDropChance;

    public TBXLBlockLeaves(String unlocName, MapColor mapColor, boolean flammable, int saplingDropChance) {
        this.setRegistryName(TraverseBiomesXL.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(TraverseBiomesXL.TAB);
        this.mapColor = mapColor;
        this.flammable = flammable;
        this.saplingDropChance = saplingDropChance;
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setSoundType(SoundType.PLANT);
    }

    @Override
    public boolean isOpaqueCube(@NotNull IBlockState state) {
        return Blocks.LEAVES.getDefaultState().isOpaqueCube();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public @NotNull BlockRenderLayer getRenderLayer() {
        return Blocks.LEAVES.getRenderLayer();
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(@NotNull IBlockState blockState, @NotNull IBlockAccess blockAccess, @NotNull BlockPos pos, @NotNull EnumFacing side) {
        if (!Minecraft.getMinecraft().gameSettings.fancyGraphics) {
            return !(blockAccess.getBlockState(pos.offset(side)).getBlock() instanceof BlockLeaves);
        }
        return true;
    }

    @Override
    public @NotNull MapColor getMapColor(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos) {
        return mapColor;
    }

    public boolean isFlammable() {
        return flammable;
    }

    @Override
    public int getFlammability(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EnumFacing face) {
        if (isFlammable()) {
            return Blocks.LEAVES.getFlammability(world, pos, face);
        } else {
            return 0;
        }
    }

    @Override
    public int getFireSpreadSpeed(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EnumFacing face) {
        if (isFlammable()) {
            return Blocks.LEAVES.getFireSpreadSpeed(world, pos, face);
        } else {
            return 0;
        }
    }

    @Override
    public void harvestBlock(World worldIn, @NotNull EntityPlayer player, @NotNull BlockPos pos, @NotNull IBlockState state, @Nullable TileEntity te, @NotNull ItemStack stack) {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS) {
            player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(worldIn, pos, new ItemStack(this));
        } else {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    public @NotNull List<ItemStack> onSheared(@NotNull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(this, 1, 0));
        return list;
    }

    @Override
    public @NotNull EnumType getWoodType(int meta) {
        return null;
    }

    @Override
    protected @NotNull BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    public @NotNull Item getItemDropped(final @NotNull IBlockState state, final @NotNull Random rand, final int fortune) {
        if (this == TBXLBlocks.brown_autumnal_oak_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.brown_autumnal_oak_sapling);
        } else if (this == TBXLBlocks.cypress_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.cypress_sapling);
        } else if (this == TBXLBlocks.douglas_fir_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.douglas_fir_sapling);
        } else if (this == TBXLBlocks.green_autumnal_oak_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.green_autumnal_oak_sapling);
        } else if (this == TBXLBlocks.orange_autumnal_oak_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.orange_autumnal_oak_sapling);
        } else if (this == TBXLBlocks.purple_autumnal_oak_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.purple_autumnal_oak_sapling);
        } else if (this == TBXLBlocks.red_autumnal_oak_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.red_autumnal_oak_sapling);
        } else if (this == TBXLBlocks.wattle_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.wattle_sapling);
        } else if (this == TBXLBlocks.white_fir_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.white_fir_sapling);
        } else if (this == TBXLBlocks.yellow_autumnal_oak_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.yellow_autumnal_oak_sapling);
        } else {
            return Item.getItemFromBlock(Blocks.SAPLING);
        }
    }

    @Override
    public @NotNull IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(DECAYABLE, (meta & 1) == 0)
                .withProperty(CHECK_DECAY, (meta & 2) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;

        if (!state.getValue(DECAYABLE)) {
            meta |= 1;
        }
        if (state.getValue(CHECK_DECAY)) {
            meta |= 2;
        }

        return meta;
    }

    @Override
    public @NotNull IBlockState getStateForPlacement(@NotNull World world, @NotNull BlockPos pos, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ, int meta, @NotNull EntityLivingBase placer, @NotNull EnumHand hand) {
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(DECAYABLE, false);
    }

    @Override
    protected int getSaplingDropChance(@NotNull IBlockState state) {
        return saplingDropChance;
    }

    @Override
    protected void dropApple(final @NotNull World world, final @NotNull BlockPos pos, final @NotNull IBlockState state, final int chance) {
        /*if (world.rand.nextInt(chance) == 0) {
            if (this == TBXLBlocks.fir_leaves) {
                spawnAsEntity(world, pos, new ItemStack(TBXLItems.fir_door));
            }
        }*/
    }
}
