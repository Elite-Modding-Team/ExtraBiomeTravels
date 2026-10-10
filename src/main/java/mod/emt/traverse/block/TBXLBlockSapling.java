package mod.emt.traverse.block;

import java.util.Objects;
import java.util.Random;

import mod.emt.traverse.TraverseBiomesXL;
import mod.emt.traverse.init.TBXLBlocks;
import mod.emt.traverse.world.feature.*;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class TBXLBlockSapling extends BlockBush implements IGrowable {
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);
    public boolean flammable;
    private EnumWoodType woodType;

    public TBXLBlockSapling(String unlocName, EnumWoodType woodType, MapColor mapColor, boolean flammable) {
        super(Material.PLANTS, mapColor);
        this.setRegistryName(TraverseBiomesXL.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(TraverseBiomesXL.TAB);
        this.flammable = flammable;
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0));
        this.woodType = woodType;
    }

    public TBXLBlockSapling(Material material, MapColor mapColor) {
        super(material, mapColor);
    }

    public boolean isFlammable() {
        return flammable;
    }

    @Override
    public int getFlammability(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EnumFacing face) {
        if (isFlammable()) {
            return Blocks.SAPLING.getFlammability(world, pos, face);
        } else {
            return 0;
        }
    }

    @Override
    public int getFireSpreadSpeed(@NotNull IBlockAccess world, @NotNull BlockPos pos, @NotNull EnumFacing face) {
        if (isFlammable()) {
            return Blocks.SAPLING.getFireSpreadSpeed(world, pos, face);
        } else {
            return 0;
        }
    }

    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return AABB;
    }

    @Override
    public void updateTick(World world, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, pos, state, rand);

            if (!world.isAreaLoaded(pos, 1)) return;
            if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(world, pos, state, rand);
            }
        }
    }

    public void grow(World world, BlockPos pos, IBlockState state, Random rand) {
        if (state.getValue(STAGE) == 0) {
            world.setBlockState(pos, state.cycleProperty(STAGE), 4);
        } else {
            this.generateTree(world, pos, state, rand);
        }
    }

    public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!TerrainGen.saplingGrowTree(world, rand, pos)) return;
        WorldGenerator generator = null;
        int i = 0;
        int j = 0;
        boolean flag = false;

        switch (this.woodType) {
            case AUTUMNAL_CITRINE:
                generator = new TBXLWorldGenAutumnalTree(true, TBXLBlocks.autumnal_log.getDefaultState(), TBXLBlocks.citrine_autumnal_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE));
                break;
            case AUTUMNAL_GOLDENROD:
                generator = new TBXLWorldGenAutumnalTree(true, TBXLBlocks.autumnal_log.getDefaultState(), TBXLBlocks.goldenrod_autumnal_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE));
                break;
            case AUTUMNAL_UMBER:
                generator = new TBXLWorldGenAutumnalTree(true, TBXLBlocks.autumnal_log.getDefaultState(), TBXLBlocks.umber_autumnal_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE));
                break;
            case AUTUMNAL_VERMILLION:
                generator = new TBXLWorldGenAutumnalTree(true, TBXLBlocks.autumnal_log.getDefaultState(), TBXLBlocks.vermillion_autumnal_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE));
                break;
            case AUTUMNAL_OAK_BROWN:
                generator = new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.brown_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE));
                break;
            case AUTUMNAL_OAK_GREEN:
                generator = new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.green_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE));
                break;
            case AUTUMNAL_OAK_ORANGE:
                generator = new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.orange_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE));
                break;
            case AUTUMNAL_OAK_PURPLE:
                generator = new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.purple_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE));
                break;
            case AUTUMNAL_OAK_RED:
                generator = new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.red_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE));
                break;
            case AUTUMNAL_OAK_YELLOW:
                generator = new TBXLWorldGenTallTree(false, rand.nextBoolean(), Blocks.LOG.getDefaultState(), TBXLBlocks.yellow_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE));
                break;
            case BALD_CYPRESS:
                check:
                for (i = 0; i >= -1; --i) {
                    for (j = 0; j >= -1; --j) {
                        if (this.isTwoByTwoOfType(world, pos, i, j, EnumWoodType.BALD_CYPRESS)) {
                            generator = new TBXLWorldGenBaldCypressTree(true);
                            flag = true;
                            break check;
                        }
                    }
                }

                if (!flag) {
                    return;
                }

                break;
            case CYPRESS:
                generator = new TBXLWorldGenCypressTree(true);
                break;
            case DOUGLAS_FIR:
                check:
                for (i = 0; i >= -1; --i) {
                    for (j = 0; j >= -1; --j) {
                        if (this.isTwoByTwoOfType(world, pos, i, j, EnumWoodType.DOUGLAS_FIR)) {
                            generator = new TBXLWorldGenHugeFirTree2(false);
                            flag = true;
                            break check;
                        }
                    }
                }

                if (!flag) {
                    i = 0;
                    j = 0;
                    generator = new TBXLWorldGenFirTree2(true);
                }
                break;
            case JAPANESE_MAPLE:
                generator = new TBXLWorldGenJapaneseMapleTree(true);
                break;
            case JAPANESE_MAPLE_SHRUB:
                generator = new WorldGenShrub(TBXLBlocks.japanese_maple_log.getDefaultState(), TBXLBlocks.japanese_maple_shrub_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE));
                break;
            case PEACH_SAKURA:
                generator = new TBXLWorldGenPeachSakuraTree(true);
                break;
            case RAINBOW_EUCALYPTUS:
                check:
                for (i = 0; i >= -1; --i) {
                    for (j = 0; j >= -1; --j) {
                        if (this.isTwoByTwoOfType(world, pos, i, j, EnumWoodType.RAINBOW_EUCALYPTUS)) {
                            generator = new TBXLWorldGenRainbowEucalyptusTree(true);
                            flag = true;
                            break check;
                        }
                    }
                }

                if (!flag) {
                    return;
                }

                break;
            case SCALEWOOD:
                check:
                for (i = 0; i >= -1; --i) {
                    for (j = 0; j >= -1; --j) {
                        if (this.isTwoByTwoOfType(world, pos, i, j, EnumWoodType.SCALEWOOD)) {
                            generator = new TBXLWorldGenScalewoodTree(true);
                            flag = true;
                            break check;
                        }
                    }
                }

                if (!flag) {
                    return;
                }

                break;
            case WATTLE:
                generator = new TBXLWorldGenWattleTree();
                break;
            case WHITE_FIR:
                check:
                for (i = 0; i >= -1; --i) {
                    for (j = 0; j >= -1; --j) {
                        if (this.isTwoByTwoOfType(world, pos, i, j, EnumWoodType.WHITE_FIR)) {
                            generator = new TBXLWorldGenHugeFirTree(false, true);
                            flag = true;
                            break check;
                        }
                    }
                }

                if (!flag) {
                    i = 0;
                    j = 0;
                    generator = new TBXLWorldGenFirTree(true);
                }
                break;
            default:
                break;
        }

        IBlockState air = Blocks.AIR.getDefaultState();

        if (flag) {
            world.setBlockState(pos.add(i, 0, j), air, 4);
            world.setBlockState(pos.add(i + 1, 0, j), air, 4);
            world.setBlockState(pos.add(i, 0, j + 1), air, 4);
            world.setBlockState(pos.add(i + 1, 0, j + 1), air, 4);
        } else {
            world.setBlockState(pos, air, 4);
        }

        if (!generator.generate(world, rand, pos.add(i, 0, j))) {
            if (flag) {
                world.setBlockState(pos.add(i, 0, j), state, 4);
                world.setBlockState(pos.add(i + 1, 0, j), state, 4);
                world.setBlockState(pos.add(i, 0, j + 1), state, 4);
                world.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
            } else {
                world.setBlockState(pos, state, 4);
            }
        }
    }


    // Trees that utilize 2x2 Saplings
    private boolean isTwoByTwoOfType(World world, BlockPos pos, int xOffset, int zOffset, EnumWoodType type) {
        return this.isTypeAt(world, pos.add(xOffset, 0, zOffset), type) && this.isTypeAt(world, pos.add(xOffset + 1, 0, zOffset), type) && this.isTypeAt(world, pos.add(xOffset, 0, zOffset + 1), type) && this.isTypeAt(world, pos.add(xOffset + 1, 0, zOffset + 1), type);
    }

    public EnumWoodType getWoodType() {
        return woodType;
    }

    public boolean isTypeAt(final World world, final BlockPos pos, final EnumWoodType type) {
        final IBlockState state = world.getBlockState(pos);

        // Necessary to ensure it checks correctly
        if (state.getBlock() instanceof TBXLBlockSapling) {
            return ((TBXLBlockSapling) state.getBlock()).getWoodType() == type;
        } else {
            return false;
        }
    }

    public boolean canGrow(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state, boolean isClient) {
        return true;
    }

    public boolean canUseBonemeal(World world, @NotNull Random rand, @NotNull BlockPos pos, @NotNull IBlockState state) {
        return world.rand.nextFloat() < 0.45D;
    }

    public void grow(@NotNull World world, @NotNull Random rand, @NotNull BlockPos pos, @NotNull IBlockState state) {
        this.grow(world, pos, state, rand);
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(STAGE, meta);
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(STAGE);
    }

    protected @NotNull BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STAGE);
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, @NotNull IBlockState state) {
        Block soil = world.getBlockState(pos.down()).getBlock();
        return soil instanceof BlockGrass || soil instanceof BlockDirt || soil instanceof BlockFarmland;
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        IBlockState soil = world.getBlockState(pos.down());
        return super.canPlaceBlockAt(world, pos) && soil.getBlock().canSustainPlant(soil, world, pos.down(), EnumFacing.UP, this);
    }

    public enum EnumWoodType implements IStringSerializable {
        DOUGLAS_FIR(0, "douglas_fir"),
        AUTUMNAL_OAK_BROWN(1, "autumnal_oak_brown"),
        AUTUMNAL_OAK_GREEN(2, "autumnal_oak_green"),
        AUTUMNAL_OAK_ORANGE(3, "autumnal_oak_orange"),
        AUTUMNAL_OAK_PURPLE(4, "autumnal_oak_purple"),
        AUTUMNAL_OAK_RED(5, "autumnal_oak_red"),
        AUTUMNAL_OAK_YELLOW(6, "autumnal_oak_yellow"),
        AUTUMNAL_CITRINE(7, "autumnal_citrine"),
        AUTUMNAL_GOLDENROD(8, "autumnal_goldenrod"),
        AUTUMNAL_UMBER(9, "autumnal_umber"),
        AUTUMNAL_VERMILLION(10, "autumnal_vermillion"),
        BALD_CYPRESS(11, "bald_cypress"),
        CYPRESS(12, "cypress"),
        JAPANESE_MAPLE(13, "japanese_maple"),
        JAPANESE_MAPLE_SHRUB(14, "japanese_maple_shrub"),
        PEACH_SAKURA(15, "peach_sakura"),
        RAINBOW_EUCALYPTUS(16, "rainbow_eucalyptus"),
        SCALEWOOD(17, "scalewood"),
        WATTLE (18, "wattle"),
        WHITE_FIR(19, "white_fir");

        private final String name;
        private final int meta;

        EnumWoodType(int meta, String name) {
            this.meta = meta;
            this.name = name;
        }

        public static EnumWoodType byMeta(int meta) {
            for (EnumWoodType woodType : EnumWoodType.values()) {
                if (woodType.meta == meta) {
                    return woodType;
                }
            }
            return EnumWoodType.DOUGLAS_FIR;
        }

        public int getMetadata() {
            return this.meta;
        }

        public String toString() {
            return this.name;
        }

        public @NotNull String getName() {
            return this.name;
        }
    }
}
