package tpmod.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import tpmod.block.TeleportationBlocks;

public class WorldGenTeleportTrees extends WorldGenAbstractTree
{
    private boolean allowTallTrees;

    public WorldGenTeleportTrees(boolean doBlockNotify, boolean allowTallTrees)
    {
        super(doBlockNotify);
        this.allowTallTrees = allowTallTrees;
    }

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        int genHeight = rand.nextInt(3) + 5;

        if (this.allowTallTrees)
        {
            genHeight += rand.nextInt(7);
        }

        boolean flag = true;

        if (y >= 1 && y + genHeight + 1 <= 256)
        {
            int j1;
            int k1;

            for (int i1 = y; i1 <= y + 1 + genHeight; ++i1)
            {
                byte b0 = 1;

                if (i1 == y)
                {
                    b0 = 0;
                }

                if (i1 >= y + 1 + genHeight - 2)
                {
                    b0 = 2;
                }

                for (j1 = x - b0; j1 <= x + b0 && flag; ++j1)
                {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {

                            if (!this.isReplaceable(world, j1, i1, k1))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block block2 = world.getBlock(x, y - 1, z);

                boolean isSoil = block2 == TeleportationBlocks.teleportationDirt || block2 == TeleportationBlocks.teleportationGrass;
                if (isSoil && y < 256 - genHeight - 1)
                {
                    int k2;

                    for (k2 = y - 3 + genHeight; k2 <= y + genHeight; ++k2)
                    {
                        j1 = k2 - (y + genHeight);
                        k1 = 1 - j1 / 2;

                        for (int l2 = x - k1; l2 <= x + k1; ++l2)
                        {
                            int l1 = l2 - x;

                            for (int i2 = z - k1; i2 <= z + k1; ++i2)
                            {
                                int j2 = i2 - z;

                                if (Math.abs(l1) != k1 || Math.abs(j2) != k1 || rand.nextInt(2) != 0 && j1 != 0)
                                {
                                    Block block1 = world.getBlock(l2, k2, i2);

                                    if (block1.isAir(world, l2, k2, i2) || block1.isLeaves(world, l2, k2, i2))
                                    {
                                        this.setBlockAndNotifyAdequately(world, l2, k2, i2, Blocks.obsidian, 2);
                                    }
                                }
                            }
                        }
                    }

                    for (k2 = 0; k2 < genHeight; ++k2)
                    {
                        Block block3 = world.getBlock(x, y + k2, z);

                        if (block3.isAir(world, x, y + k2, z) || block3.isLeaves(world, x, y + k2, z))
                        {
                            this.setBlockAndNotifyAdequately(world, x, y + k2, z, Blocks.cobblestone, 2);
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
}