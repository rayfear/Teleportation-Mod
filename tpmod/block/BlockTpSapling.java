package tpmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.terraingen.TerrainGen;
import tpmod.TeleportationMod;
import tpmod.world.WorldGenTeleportTrees;

public class BlockTpSapling extends BlockBush
{
    public BlockTpSapling()
    {
        super(Material.plants);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setBlockName("TeleportationSapling").setStepSound(Block.soundTypeGrass).setCreativeTab(TeleportationMod.teleportationTab).setBlockTextureName(TeleportationBlocks.modID("Teleportation Sapling"));
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World world, int x, int y, int z)
    {
    	if(world.getBlock(x, y - 1, z) != Blocks.air && world.getBlock(x, y - 1, z).isSideSolid(world, x, y, z, ForgeDirection.UP))
    	{
    		return true;
    	}
        return false;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
            {
                this.markOrGrowMarked(par1World, par2, par3, par4, par5Random);
            }
        }
    }

    public void markOrGrowMarked(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        this.growTree(par1World, par2, par3, par4, par5Random);
    }

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World world, int x, int y, int z, Random rand)
    {
        if (!TerrainGen.saplingGrowTree(world, rand, x, y, z))
        {
            return;
        }

        int metadata = world.getBlockMetadata(x, y, z) & 3;
        Object object = null;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;
        object = new WorldGenTeleportTrees(false, false);

        if (flag)
        {
            world.setBlock(x + i1, y, z + j1, Blocks.air, 0, 4);
            world.setBlock(x + i1 + 1, y, z + j1, Blocks.air, 0, 4);
            world.setBlock(x + i1, y, z + j1 + 1, Blocks.air, 0, 4);
            world.setBlock(x + i1 + 1, y, z + j1 + 1, Blocks.air, 0, 4);
        }
        else
        {
            world.setBlock(x, y, z, Blocks.air, 0, 4);
        }

        if (!((WorldGenerator)object).generate(world, rand, x + i1, y, z + j1))
        {
            if (flag)
            {
                world.setBlock(x + i1, y, z + j1, this, metadata, 4);
                world.setBlock(x + i1 + 1, y, z + j1, this, metadata, 4);
                world.setBlock(x + i1, y, z + j1 + 1, this, metadata, 4);
                world.setBlock(x + i1 + 1, y, z + j1 + 1, this, metadata, 4);
            }
            else
            {
                world.setBlock(x, y, z, this, metadata, 4);
            }
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 3;
    }
    

}
