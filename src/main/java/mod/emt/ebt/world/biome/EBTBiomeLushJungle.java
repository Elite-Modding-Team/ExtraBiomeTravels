package mod.emt.ebt.world.biome;

import java.util.Random;

import net.minecraft.block.*;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class EBTBiomeLushJungle extends Biome {
    public static BiomeProperties properties = new BiomeProperties("Lush Jungle");

    static {
        properties.setTemperature(Biomes.JUNGLE.getDefaultTemperature());
        properties.setRainfall(Biomes.JUNGLE.getRainfall());
        properties.setBaseHeight(-0.1F);
        properties.setHeightVariation(0.45F);
        properties.setWaterColor(0xFF24B01C);
    }

    public EBTBiomeLushJungle() {
        super(properties);
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityOcelot.class, 10, 1, 1));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 4, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityParrot.class, 40, 1, 2));

        this.decorator.treesPerChunk = 30;
        this.decorator.flowersPerChunk = 5;
        this.decorator.grassPerChunk = 25;
        this.decorator.reedsPerChunk = 2;
        this.decorator.clayPerChunk = 3;
        this.decorator.waterlilyPerChunk = 12;
    }

    @Override
    public @NotNull WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
    }

    @Override
    public @NotNull WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return rand.nextInt(10) == 0 ? BIG_TREE_FEATURE : new WorldGenTrees(false, 2 + rand.nextInt(3), Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE),
                Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, false), true);
    }

    @Override
    public void decorate(@NotNull World world, @NotNull Random rand, @NotNull BlockPos pos) {
        super.decorate(world, rand, pos);
        int i = rand.nextInt(16) + 8;
        int j = rand.nextInt(16) + 8;
        int height = world.getHeight(pos.add(i, 0, j)).getY() * 2; // Could == 0, which crashes nextInt
        if (height < 1) height = 1;
        int k = rand.nextInt(height);

        if (TerrainGen.decorate(world, rand, pos, DecorateBiomeEvent.Decorate.EventType.PUMPKIN)) {
            new WorldGenMelon().generate(world, rand, pos.add(i, k, j));
        }

        WorldGenVines vines = new WorldGenVines();
        if (TerrainGen.decorate(world, rand, pos, DecorateBiomeEvent.Decorate.EventType.GRASS)) {
            for (j = 0; j < 50; ++j) {
                k = rand.nextInt(16) + 8;
                int i1 = rand.nextInt(16) + 8;
                vines.generate(world, rand, pos.add(k, 128, i1));
            }
        }
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFFC2FFEB;
    }
}