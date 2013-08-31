package com.world;

import java.util.Random;

import com.TeleporterMod;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;


public class BiomeGenTeleport extends BiomeGenBase
{
   
	public BiomeGenTeleport(int par1)
    {
		
        super(par1);
      
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.theBiomeDecorator.treesPerChunk = 2;
        this.fillerBlock = (byte)TeleporterMod.TeleportingDirt.blockID;
        this.topBlock = (byte)TeleporterMod.TeleportingGrass.blockID;
        setColor(244);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityEnderman.class, 800, 4, 8));

    }
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)(par1Random.nextInt(10) == 0 ? TeleporterMod.worldGeneratorTeleTree : (par1Random.nextInt(2) == 0 ? new WorldGenTeleportTrees(false, 3, 0, waterColorMultiplier, false) : (par1Random.nextInt(3) == 0 ? new WorldGenTeleportTrees(false, 10 + par1Random.nextInt(20), 3, 3, false) : new WorldGenTeleportTrees(false, 4 + par1Random.nextInt(7), 3, 3, true))));
    }
}
