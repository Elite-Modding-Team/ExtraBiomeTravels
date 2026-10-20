package mod.emt.ebt.block;

import mod.emt.ebt.ExtraBiomeTravels;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

// NOTE: Wooden pressure plates are not flammable in vanilla
@SuppressWarnings("deprecation")
public class EBTBlockPressurePlateStone extends BlockPressurePlate {
    private final MapColor mapColor;

    public EBTBlockPressurePlateStone(String unlocName, MapColor mapColor) {
        super(Material.ROCK, Sensitivity.MOBS);
        this.setRegistryName(ExtraBiomeTravels.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(ExtraBiomeTravels.TAB);
        this.setSoundType(SoundType.STONE);
        this.setHardness(0.5F);
        this.setHarvestLevel("pickaxe", 0);
        this.mapColor = mapColor;
    }

    @Override
    public @NotNull MapColor getMapColor(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos) {
        return mapColor;
    }
}
