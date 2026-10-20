package mod.emt.ebt.world.biome;

import mod.emt.ebt.world.feature.tree.EBTWorldGenRainbowEucalyptusTree;
import mod.emt.ebt.world.feature.tree.EBTWorldGenScalewoodTree;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EBTBiomeRainforest extends Biome {
    private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState JUNGLE_LEAVES = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);

    public static BiomeProperties properties = new BiomeProperties("Rainforest");

    static {
        properties.setTemperature(0.95F);
        properties.setRainfall(0.9F);
        properties.setBaseHeight(0.4F);
        properties.setHeightVariation(0.4F);
        properties.setWaterColor(0xFF24B01C);
    }

    public EBTBiomeRainforest() {
        super(properties);
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityOcelot.class, 10, 1, 1));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 4, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityParrot.class, 40, 1, 2));
        this.decorator.treesPerChunk = 22;
        this.decorator.flowersPerChunk = 6;
        this.decorator.grassPerChunk = 4;
    }

    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(15) == 0) {
            return new WorldGenMegaJungle(false, 10, 20, JUNGLE_LOG, JUNGLE_LEAVES);
        } else if (rand.nextInt(15) == 0) {
            return new EBTWorldGenScalewoodTree(true, true, false);
        } else {
            return new EBTWorldGenRainbowEucalyptusTree(true, false);
        }
    }

    @Override
    public @NotNull WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFFC2FFEB;
    }
}