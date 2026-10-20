package mod.emt.ebt.block;

import mod.emt.ebt.ExtraBiomeTravels;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import java.util.Objects;

public class EBTBlockMaterial extends Block {
    public EBTBlockMaterial(String unlocName, Material material, MapColor mapColor, float hardness, float resistance, SoundType soundType) {
        super(material, mapColor);
        this.setRegistryName(ExtraBiomeTravels.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(ExtraBiomeTravels.TAB);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setSoundType(soundType);
    }

    public EBTBlockMaterial(String unlocName, Material material, MapColor mapColor, float hardness, SoundType soundType) {
        super(material, mapColor);
        this.setRegistryName(ExtraBiomeTravels.MOD_ID, unlocName);
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(ExtraBiomeTravels.TAB);
        this.setHardness(hardness);
        this.setSoundType(soundType);
    }
}
