package tpmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import tpmod.TeleportationMod;
import tpmod.item.TeleportationItems;

public class BlockTeleportingOre extends Block
{
    public BlockTeleportingOre()
    {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHarvestLevel("pickaxe", 2);
        this.setBlockName("TeleportationOre");
        this.setHardness(5F);
        this.setBlockTextureName(TeleportationBlocks.modID("Teleportation Ore"));
        this.setCreativeTab(TeleportationMod.teleportationTab);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
    	int min = 2;
        return par1Random.nextInt(5 - min) + min;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public Item getItemDropped(int par1, Random random, int par3)
    {
        return TeleportationItems.teleportationShards;
    }
    
}
