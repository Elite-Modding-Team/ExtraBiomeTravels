package mod.emt.traverse.world.biome;

import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

import mod.emt.traverse.world.WorldGenConstants;

@SuppressWarnings("deprecation")
public class TBXLBiomeWoodlands extends Biome implements WorldGenConstants {
    public static BiomeProperties properties = new BiomeProperties("Woodlands");

    static {
        properties.setTemperature(0.8F);
        properties.setRainfall(0.4F);
        properties.setBaseHeight(0.15F);
        properties.setHeightVariation(0.05F);
    }

    public TBXLBiomeWoodlands() {
        super(properties);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 4, 4, 4));

        this.decorator.treesPerChunk = 4;
        this.decorator.flowersPerChunk = 6;
        this.decorator.grassPerChunk = 16;

        this.flowers.clear();
        for (BlockFlower.EnumFlowerType type : BlockFlower.EnumFlowerType.values()) {
            if (type.getBlockType() == BlockFlower.EnumFlowerColor.YELLOW) {
                continue;
            }

            if (type == BlockFlower.EnumFlowerType.BLUE_ORCHID) {
                type = BlockFlower.EnumFlowerType.POPPY;
            }

            addFlower(Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), type), 10);
        }
    }

    @Override
    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
        double d0 = GRASS_COLOR_NOISE.getValue((double) pos.getX() / 200.0D, (double) pos.getZ() / 200.0D);

        if (d0 < -0.8D) {
            int j = rand.nextInt(4);

            switch (j) {
                case 0:
                    return BlockFlower.EnumFlowerType.ORANGE_TULIP;
                case 1:
                    return BlockFlower.EnumFlowerType.RED_TULIP;
                case 2:
                    return BlockFlower.EnumFlowerType.PINK_TULIP;
                case 3:
                default:
                    return BlockFlower.EnumFlowerType.WHITE_TULIP;
            }
        } else if (rand.nextInt(3) > 0) {
            int i = rand.nextInt(3);
            return i == 0 ? BlockFlower.EnumFlowerType.POPPY : (i == 1 ? BlockFlower.EnumFlowerType.HOUSTONIA : BlockFlower.EnumFlowerType.OXEYE_DAISY);
        } else {
            return BlockFlower.EnumFlowerType.DANDELION;
        }
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        int num = rand.nextInt(3);

        if (num == 0) {
            return new WorldGenShrub(OAK_LOG, OAK_LEAVES);
        }

        return TREE_FEATURE;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {
        if (TerrainGen.decorate(world, rand, pos, DecorateBiomeEvent.Decorate.EventType.TREE)) {
            int genChance = rand.nextInt(5);

            if (genChance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = world.getHeight(pos.add(k6, 0, l));
                SIMPLE_FALLEN_TREE_FEATURE.generate(world, rand, blockpos);
            }
        }

        super.decorate(world, rand, pos);
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xFF88B4E1;
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return super.getModdedBiomeGrassColor(0xFF99A955);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0xFF849E4A);
    }
}