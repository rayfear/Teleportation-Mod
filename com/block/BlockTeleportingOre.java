package com.block;

import java.util.Random;

import com.TeleporterMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;



public class BlockTeleportingOre extends Block
{

	public BlockTeleportingOre(int par1, int par2) 
	{
		super(par1,Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);

	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{		
		blockIcon = iconRegister.registerIcon("tpmod:Teleport_Ore");
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random)
	{
		return par1Random.nextInt(3);
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return TeleporterMod.TeleportationShards.itemID;
	}
}
