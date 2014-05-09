package tpmod.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import tpmod.block.TeleportationBlocks;
import cpw.mods.fml.common.IWorldGenerator;

public class OreWorldGenerator implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.dimensionId)
        {
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;

            case 1:
                generateEnd();
                break;
        }
    }

    public void generateSurface(World world, Random rand, int chunkX, int chunkZ)
    {
        for (int i = 0; i < 10; i++)
        {
            int randPosX = chunkX + rand.nextInt(16);
            int randPosY = rand.nextInt(10);
            int randPosZ = chunkZ + rand.nextInt(16);
            (new WorldGenMinable(TeleportationBlocks.teleportationOre, 4)).generate(world, rand, randPosX, randPosY, randPosZ);
        }
    }

    public void generateEnd()
    {
        //we're not going to generate in the end either
    }
}