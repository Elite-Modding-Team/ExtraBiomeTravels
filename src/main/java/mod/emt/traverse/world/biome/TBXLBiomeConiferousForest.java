package mod.emt.traverse.world.biome;

import java.util.Random;

import mod.emt.traverse.world.feature.tree.TBXLWorldGenFirTree;
import mod.emt.traverse.world.feature.tree.TBXLWorldGenHugeFirTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import org.jetbrains.annotations.NotNull;

public class TBXLBiomeConiferousForest extends Biome {
    protected static final TBXLWorldGenFirTree FIR_TREE_FEATURE = new TBXLWorldGenFirTree(true);
    protected static final TBXLWorldGenHugeFirTree HUGE_FIR_TREE_FEATURE = new TBXLWorldGenHugeFirTree(true, true);

    public final boolean isSnowy;

    public TBXLBiomeConiferousForest(boolean isSnowy) {
        super(getProperties(isSnowy));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 5, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 3, 2, 3));

        this.decorator.treesPerChunk = 7;
        this.decorator.grassPerChunk = 9;
        this.decorator.mushroomsPerChunk = 2;
        this.isSnowy = isSnowy;
    }

    public static BiomeProperties getProperties(boolean isSnowy) {
        BiomeProperties properties;

        if (isSnowy) {
            properties = new BiomeProperties("Snowy Coniferous Forest");
            properties.setTemperature(-0.25F);
            properties.setRainfall(0.5F);
            properties.setSnowEnabled();
        } else {
            properties = new BiomeProperties("Coniferous Forest");
            properties.setTemperature(0.45F);
            properties.setRainfall(0.5F);
        }

        properties.setBaseHeight(0.3F);
        properties.setHeightVariation(0.2F);
        return properties;
    }

    @Override
    public @NotNull WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(2) > 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
    }

    @Override
    public void decorate(@NotNull World world, @NotNull Random rand, @NotNull BlockPos pos) {
        DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.FERN);

        if (TerrainGen.decorate(world, rand, new ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.FLOWERS)) {
            for (int i1 = 0; i1 < 7; ++i1) {
                int j1 = rand.nextInt(16) + 8;
                int k1 = rand.nextInt(16) + 8;
                int l1 = rand.nextInt(world.getHeight(pos.add(j1, 0, k1)).getY() + 32);
                DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(j1, l1, k1));
            }
        }

        super.decorate(world, rand, pos);
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(16) == 0) {
            return HUGE_FIR_TREE_FEATURE;
        }

        return FIR_TREE_FEATURE;
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return super.getModdedBiomeGrassColor(0x528F60);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0x528F60);
    }
}