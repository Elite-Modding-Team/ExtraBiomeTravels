package mod.emt.ebt.block;

import java.util.Objects;
import java.util.Random;

import mod.emt.ebt.ExtraBiomeTravels;
import mod.emt.ebt.init.EBTBlocks;
import mod.emt.ebt.init.EBTItems;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

// NOTE: Wooden doors are not flammable in vanilla
@SuppressWarnings("deprecation")
public class EBTBlockDoorWood extends BlockDoor {
    private final MapColor mapColor;

    public EBTBlockDoorWood(String unlocName, MapColor mapColor) {
        super(Material.WOOD);
        this.setRegistryName(ExtraBiomeTravels.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(ExtraBiomeTravels.TAB);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(3.0F);
        this.setHarvestLevel("axe", 0);
        this.mapColor = mapColor;
        this.enableStats = false;
    }

    @Override
    public @NotNull ItemStack getItem(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state) {
        return new ItemStack(getItemBlock());
    }

    protected Item getItemBlock() {
        if (this == EBTBlocks.douglas_fir_door) {
            return new ItemStack(EBTItems.douglas_fir_door, 1).getItem();
        }

        // If door is not listed here give an oak one instead to prevent issues
        else {
            return Items.OAK_DOOR;
        }
    }

    @Override
    public @NotNull Item getItemDropped(IBlockState state, @NotNull Random rand, int fortune) {
        return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : getItemBlock();
    }

    @Override
    public @NotNull MapColor getMapColor(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos) {
        return mapColor;
    }
}
