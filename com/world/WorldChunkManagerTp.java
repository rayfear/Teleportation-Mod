package com.world;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

public class WorldChunkManagerTp extends WorldChunkManager
{
    /** The biome generator object. */
    private BiomeGenBase biomeGenerator;


    public WorldChunkManagerTp(BiomeGenBase par1BiomeGenBase, float par2, float par3)
    {
        this.biomeGenerator = par1BiomeGenBase;
        
    }

    /**
     * Returns the BiomeGenBase related to the x, z position on the world.
     */
    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
        return this.biomeGenerator;
    }

    /**
     * Returns an array of biomes for the location input.
     */
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        Arrays.fill(par1ArrayOfBiomeGenBase, 0, par4 * par5, this.biomeGenerator);
        return par1ArrayOfBiomeGenBase;
    }

 
   

    /**
     * Returns biomes to use for the blocks and loads the other data like temperature and humidity onto the
     * WorldChunkManager Args: oldBiomeList, x, z, width, depth
     */
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        Arrays.fill(par1ArrayOfBiomeGenBase, 0, par4 * par5, this.biomeGenerator);
        return par1ArrayOfBiomeGenBase;
    }

    /**
     * Return a list of biomes for the specified blocks. Args: listToReuse, x, y, width, length, cacheFlag (if false,
     * don't check biomeCache to avoid infinite loop in BiomeCacheBlock)
     */
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
    {
        return this.loadBlockGeneratorData(par1ArrayOfBiomeGenBase, par2, par3, par4, par5);
    }

    /**
     * Finds a valid position within a range, that is in one of the listed biomes. Searches {par1,par2} +-par3 blocks.
     * Strongly favors positive y positions.
     */
    public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random)
    {
        return par4List.contains(this.biomeGenerator) ? new ChunkPosition(par1 - par3 + par5Random.nextInt(par3 * 2 + 1), 0, par2 - par3 + par5Random.nextInt(par3 * 2 + 1)) : null;
    }

    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
    {
        return par4List.contains(this.biomeGenerator);
    }
}
