package mod.emt.traverse.world.biome;

import java.util.Iterator;

import mod.emt.traverse.world.WorldGenConstants;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class TBXLBiomeWasteland extends Biome implements WorldGenConstants {
    public static BiomeProperties properties = new BiomeProperties("Wasteland");

    static {
        properties.setTemperature(Biomes.DESERT.getDefaultTemperature());
        properties.setRainfall(Biomes.DESERT.getRainfall());
        properties.setRainDisabled();
        properties.setBaseHeight(0.1F);
        properties.setHeightVariation(0.0F);
        properties.setWaterColor(0xF08000);
    }

    public TBXLBiomeWasteland() {
        super(properties);
        this.spawnableCreatureList.clear();

        // Remove Zombies from most spawns and replace them with Husks
        Iterator<Biome.SpawnListEntry> iterator = this.spawnableMonsterList.iterator();
        while (iterator.hasNext()) {
            Biome.SpawnListEntry list = iterator.next();

            if (list.entityClass == EntityZombie.class || list.entityClass == EntityZombieVillager.class) {
                iterator.remove();
            }
        }

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombie.class, 19, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombieVillager.class, 1, 1, 1));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityHusk.class, 80, 4, 4));

        this.topBlock = CRACKED_SAND;
        this.fillerBlock = CRACKED_SAND;

        decorator.extraTreeChance = 0.01F;
        decorator.flowersPerChunk = 0;
        decorator.deadBushPerChunk = 5;
        decorator.generateFalls = false;
        decorator.grassPerChunk = 5;
        decorator.treesPerChunk = 0;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 0xBBC4C3;
    }

    @Override
    public int getModdedBiomeGrassColor(int original) {
        return super.getModdedBiomeGrassColor(0x7B7A68);
    }

    @Override
    public int getModdedBiomeFoliageColor(int original) {
        return super.getModdedBiomeFoliageColor(0x7B7A68);
    }
}