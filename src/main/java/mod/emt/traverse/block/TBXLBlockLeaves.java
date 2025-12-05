package mod.emt.traverse.block;

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

import javax.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TBXLBlockLeaves extends BlockLeaves {
    private final MapColor mapColor;
    public boolean flammable;
    public int saplingDropChance;

    public TBXLBlockLeaves(MapColor mapColor, boolean flammable, int saplingDropChance) {
        this.mapColor = mapColor;
        this.flammable = flammable;
        this.saplingDropChance = saplingDropChance;
        setSoundType(SoundType.PLANT);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return Blocks.LEAVES.getDefaultState().isOpaqueCube();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return Blocks.LEAVES.getRenderLayer();
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if (!Minecraft.getMinecraft().gameSettings.fancyGraphics) {
            return !(blockAccess.getBlockState(pos.offset(side)).getBlock() instanceof BlockLeaves);
        }
        return true;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
        return mapColor;
    }

    public boolean isFlammable() {
        return flammable;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        if (isFlammable()) {
            return Blocks.LEAVES.getFlammability(world, pos, face);
        } else {
            return 0;
        }
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        if (isFlammable()) {
            return Blocks.LEAVES.getFireSpreadSpeed(world, pos, face);
        } else {
            return 0;
        }
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS) {
            player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(worldIn, pos, new ItemStack(this));
        } else {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(this, 1, 0));
        return list;
    }

    @Override
    public EnumType getWoodType(int meta) {
        return null;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
    }

    @Override
    public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
        if (this == TBXLBlocks.fir_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.fir_sapling);
        } else {
            return Item.getItemFromBlock(Blocks.SAPLING);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
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
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(DECAYABLE, false);
    }

    @Override
    protected int getSaplingDropChance(IBlockState state) {
        return saplingDropChance;
    }

    @Override
    protected void dropApple(final World world, final BlockPos pos, final IBlockState state, final int chance) {
        /*if (world.rand.nextInt(chance) == 0) {
            if (this == TBXLBlocks.fir_leaves) {
                spawnAsEntity(world, pos, new ItemStack(TBXLItems.fir_door));
            }
        }*/
    }
}
