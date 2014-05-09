package tpmod.world;

import java.util.Random;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import tpmod.TeleportationMod;
import tpmod.block.TeleportationBlocks;
import tpmod.entity.EntityWatcher;

public class BiomeGenTeleport extends BiomeGenBase
{
    @SuppressWarnings("unchecked")
	public BiomeGenTeleport(int par1)
    {
        super(par1);
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.theBiomeDecorator.treesPerChunk = 2;
        this.fillerBlock = TeleportationBlocks.teleportationDirt;
        this.topBlock = TeleportationBlocks.teleportationGrass;
        setColor(244);
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 30, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityWatcher.class, 50, 2, 4));
    }
    
    public WorldGenAbstractTree func_150567_a(Random random)
    {
        return (WorldGenAbstractTree)(random.nextInt(10) == 0 ? TeleportationMod.worldGeneratorTeleTree : (random.nextInt(2) == 0 ? new WorldGenTeleportTrees(true, true) : (random.nextInt(3) == 0 ? new WorldGenTeleportTrees(true, false) : new WorldGenTeleportTrees(true, true))));
    }
}
