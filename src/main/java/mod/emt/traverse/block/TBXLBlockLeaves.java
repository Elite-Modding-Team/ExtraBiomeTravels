package mod.emt.traverse.block;

import mod.emt.traverse.TraverseBiomesXL;
import mod.emt.traverse.init.TBXLBlocks;

import net.minecraft.block.Block;
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
    public int radius;
    private int[] surroundings;

    public TBXLBlockLeaves(String unlocName, MapColor mapColor, boolean flammable, int saplingDropChance, int radius) {
        this.setRegistryName(TraverseBiomesXL.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(TraverseBiomesXL.TAB);
        this.mapColor = mapColor;
        this.flammable = flammable;
        this.saplingDropChance = saplingDropChance;
        this.radius = radius;
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
        } else if (this == TBXLBlocks.citrine_autumnal_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.citrine_autumnal_sapling);
        } else if (this == TBXLBlocks.cypress_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.cypress_sapling);
        } else if (this == TBXLBlocks.douglas_fir_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.douglas_fir_sapling);
        } else if (this == TBXLBlocks.goldenrod_autumnal_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.goldenrod_autumnal_sapling);
        } else if (this == TBXLBlocks.green_autumnal_oak_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.green_autumnal_oak_sapling);
        } else if (this == TBXLBlocks.japanese_maple_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.japanese_maple_sapling);
        } else if (this == TBXLBlocks.japanese_maple_shrub_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.japanese_maple_shrub_sapling);
        } else if (this == TBXLBlocks.orange_autumnal_oak_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.orange_autumnal_oak_sapling);
        } else if (this == TBXLBlocks.purple_autumnal_oak_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.purple_autumnal_oak_sapling);
        } else if (this == TBXLBlocks.red_autumnal_oak_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.red_autumnal_oak_sapling);
        } else if (this == TBXLBlocks.umber_autumnal_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.umber_autumnal_sapling);
        } else if (this == TBXLBlocks.vermillion_autumnal_leaves) {
            return Item.getItemFromBlock(TBXLBlocks.vermillion_autumnal_sapling);
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
        return this.getDefaultState().withProperty(DECAYABLE, (meta & 1) == 0).withProperty(CHECK_DECAY, (meta & 2) > 0);
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

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (world.isRemote || !state.getValue(CHECK_DECAY) || !state.getValue(DECAYABLE)) return;
        if (!world.isAreaLoaded(pos, 1) || !world.isAreaLoaded(pos, radius + 2)) return;

        final int size = radius > 15 ? 64 : 32;
        final int sizeH = size / 2;
        final int sizeSq = size * size;

        if (surroundings == null) surroundings = new int[size * size * size];

        final int x = pos.getX();
        final int y = pos.getY();
        final int z = pos.getZ();

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    IBlockState s = world.getBlockState(mutable.setPos(x + dx, y + dy, z + dz));
                    Block b = s.getBlock();
                    int idx = (dx + sizeH) * sizeSq + (dy + sizeH) * size + (dz + sizeH);
                    if (b.canSustainLeaves(s, world, mutable)) surroundings[idx] = 0;
                    else if (b.isLeaves(s, world, mutable)) surroundings[idx] = -2;
                    else surroundings[idx] = -1;
                }
            }
        }

        for (int dist = 1; dist <= radius; dist++) {
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dz = -radius; dz <= radius; dz++) {
                        int idx = (dx + sizeH) * sizeSq + (dy + sizeH) * size + (dz + sizeH);
                        if (surroundings[idx] != dist - 1) continue;
                        if (dx > -radius && surroundings[idx - sizeSq] == -2) surroundings[idx - sizeSq] = dist;
                        if (dx < radius && surroundings[idx + sizeSq] == -2) surroundings[idx + sizeSq] = dist;
                        if (dy > -radius && surroundings[idx - size] == -2) surroundings[idx - size] = dist;
                        if (dy < radius && surroundings[idx + size] == -2) surroundings[idx + size] = dist;
                        if (dz > -radius && surroundings[idx - 1] == -2) surroundings[idx - 1] = dist;
                        if (dz < radius && surroundings[idx + 1] == -2) surroundings[idx + 1] = dist;
                    }
                }
            }
        }

        int centerIdx = sizeH * sizeSq + sizeH * size + sizeH;
        int distance = surroundings[centerIdx];

        if (distance >= 0) {
            world.setBlockState(pos, state.withProperty(CHECK_DECAY, false), 4);
        } else {
            dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
            world.setBlockToAir(pos);
        }
    }
}
