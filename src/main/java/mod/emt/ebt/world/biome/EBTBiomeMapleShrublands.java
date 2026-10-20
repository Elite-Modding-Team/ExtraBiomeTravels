package mod.emt.ebt.world.biome;

import mod.emt.ebt.init.EBTBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EBTBiomeMapleShrublands extends Biome {
    private static final WorldGenShrub GREEN_AUTUMNAL_OAK_SHRUB = new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK),
            EBTBlocks.green_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
    private static final WorldGenShrub JAPANESE_MAPLE_SHRUB = new WorldGenShrub(EBTBlocks.japanese_maple_log.getDefaultState(),
            EBTBlocks.japanese_maple_shrub_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
    private static final WorldGenShrub PURPLE_AUTUMNAL_OAK_SHRUB = new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK),
            EBTBlocks.purple_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
    private static final WorldGenShrub RED_AUTUMNAL_OAK_SHRUB = new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK),
            EBTBlocks.red_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));

    public static BiomeProperties properties = new BiomeProperties("Maple Shrublands");

    static {
        properties.setTemperature(0.4F);
        properties.setRainfall(0.6F);
        properties.setBaseHeight(0.1F);
        properties.setHeightVariation(0.05F);
    }

    public EBTBiomeMapleShrublands() {
        super(properties);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 1, 2));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityDonkey.class, 1, 1, 2));

        decorator.treesPerChunk = 1;
        decorator.flowersPerChunk = 4;
        decorator.grassPerChunk = 4;
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        int num = rand.nextInt(5);
        if (num == 0) {
            return GREEN_AUTUMNAL_OAK_SHRUB;
        } else if (num == 1) {
            return PURPLE_AUTUMNAL_OAK_SHRUB;
        } else if (num == 2) {
            return RED_AUTUMNAL_OAK_SHRUB;
        }
        return JAPANESE_MAPLE_SHRUB;
    }
}