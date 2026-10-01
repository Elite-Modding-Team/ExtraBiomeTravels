package mod.emt.traverse.block;

import mod.emt.traverse.TraverseBiomesXL;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import java.util.Objects;

public class TBXLBlockMaterial extends Block {
    public TBXLBlockMaterial(String unlocName, Material material, MapColor mapColor, float hardness, float resistance, SoundType soundType) {
        super(material, mapColor);
        this.setRegistryName(TraverseBiomesXL.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(TraverseBiomesXL.TAB);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setSoundType(soundType);
    }

    public TBXLBlockMaterial(String unlocName, Material material, MapColor mapColor, float hardness, SoundType soundType) {
        super(material, mapColor);
        this.setRegistryName(TraverseBiomesXL.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(TraverseBiomesXL.TAB);
        this.setHardness(hardness);
        this.setSoundType(soundType);
    }
}
