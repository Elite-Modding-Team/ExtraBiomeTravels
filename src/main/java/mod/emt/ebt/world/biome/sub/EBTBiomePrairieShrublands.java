package mod.emt.ebt.world.biome.sub;

import mod.emt.ebt.init.EBTBiomes;
import mod.emt.ebt.init.EBTBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EBTBiomePrairieShrublands extends Biome {
    private static final WorldGenShrub BROWN_AUTUMNAL_OAK_SHRUB = new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK),
            EBTBlocks.brown_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
    private static final WorldGenShrub ORANGE_AUTUMNAL_OAK_SHRUB = new WorldGenShrub(EBTBlocks.japanese_maple_log.getDefaultState(),
            EBTBlocks.orange_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
    private static final WorldGenShrub WATTLE_SHRUB = new WorldGenShrub(EBTBlocks.wattle_log.getDefaultState(),
            EBTBlocks.wattle_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
    private static final WorldGenShrub YELLOW_AUTUMNAL_OAK_SHRUB = new WorldGenShrub(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK),
            EBTBlocks.yellow_autumnal_oak_leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));

    public static BiomeProperties properties = new BiomeProperties("Prairie Shrublands");

    static {
        properties.setTemperature(0.8F);
        properties.setRainfall(0.7F);
        properties.setBaseHeight(0.1F);
        properties.setHeightVariation(0.05F);

        // Important to properly set this as a sub-biome!
        properties.setBaseBiome(EBTBiomes.PRAIRIE.getBiomeName());
    }

    public EBTBiomePrairieShrublands() {
        super(properties);
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 1, 2, 6));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityDonkey.class, 1, 1, 1));

        if (this.getBaseHeight() > 1.1F) {
            this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityLlama.class, 8, 4, 4));
        }

        decorator.treesPerChunk = 1;
        decorator.flowersPerChunk = 4;
        decorator.grassPerChunk = 4;
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        int num = rand.nextInt(5);
        if (num == 0) {
            return BROWN_AUTUMNAL_OAK_SHRUB;
        } else if (num == 1) {
            return ORANGE_AUTUMNAL_OAK_SHRUB;
        } else if (num == 2) {
            return YELLOW_AUTUMNAL_OAK_SHRUB;
        }
        return WATTLE_SHRUB;
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return super.getModdedBiomeGrassColor(0xFFD8C16A);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0xFFB5AD58);
    }
}