package mod.emt.traverse.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class TBXLBlockMaterial extends Block {
    public TBXLBlockMaterial(Material material, MapColor mapColor, float hardness, float resistance, SoundType soundType) {
        super(material, mapColor);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setSoundType(soundType);
    }

    public TBXLBlockMaterial(Material material, MapColor mapColor, float hardness, SoundType soundType) {
        super(material, mapColor);
        this.setHardness(hardness);
        this.setSoundType(soundType);
    }
}
