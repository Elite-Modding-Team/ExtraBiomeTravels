package mod.emt.traverse.block;

import mod.emt.traverse.TraverseBiomesXL;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

// NOTE: Wooden trapdoors are not flammable in vanilla
@SuppressWarnings("deprecation")
public class TBXLBlockTrapdoorWood extends BlockTrapDoor {
    private final MapColor mapColor;

    public TBXLBlockTrapdoorWood(String unlocName, MapColor mapColor) {
        super(Material.WOOD);
        this.setRegistryName(TraverseBiomesXL.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(TraverseBiomesXL.TAB);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(3.0F);
        this.setHarvestLevel("axe", 0);
        this.mapColor = mapColor;
        this.enableStats = false;
    }

    @Override
    public @NotNull MapColor getMapColor(@NotNull IBlockState state, @NotNull IBlockAccess world, @NotNull BlockPos pos) {
        return mapColor;
    }
}
