package mod.emt.traverse.world.biome;

import mod.emt.traverse.world.feature.tree.TBXLWorldGenWattleTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TBXLBiomePrairie extends Biome {
    private static final TBXLWorldGenWattleTree WATTLE_TREE = new TBXLWorldGenWattleTree();

    public static BiomeProperties properties = new BiomeProperties("Prairie");

    static {
        properties.setTemperature(0.8F);
        properties.setRainfall(0.7F);
        properties.setBaseHeight(0.01F);
        properties.setHeightVariation(0.05F);
    }

    public TBXLBiomePrairie() {
        super(properties);
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 1, 2, 6));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityDonkey.class, 1, 1, 1));

        if (this.getBaseHeight() > 1.1F) {
            this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityLlama.class, 8, 4, 4));
        }

        this.decorator.treesPerChunk = 0;
        this.decorator.flowersPerChunk = 4;
        this.decorator.grassPerChunk = 35;
    }

    public @NotNull WorldGenAbstractTree getRandomTreeFeature(@NotNull Random rand) {
        return WATTLE_TREE;
    }

    public void decorate(@NotNull World world, @NotNull Random rand, @NotNull BlockPos pos) {
        DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

        if (TerrainGen.decorate(world, rand, new ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
            for (int i = 0; i < 12; ++i) {
                int j = rand.nextInt(16) + 8;
                int k = rand.nextInt(16) + 8;
                int l = rand.nextInt(world.getHeight(pos.add(j, 0, k)).getY() + 32);
                DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(j, l, k));
            }

        super.decorate(world, rand, pos);
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