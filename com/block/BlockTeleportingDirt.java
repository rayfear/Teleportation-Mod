package com.block;

import wings.WingsMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;



public class BlockTeleportingDirt extends Block
{

	public BlockTeleportingDirt(int par1, int par2) 
	{
		super(par1,Material.ground);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundGravelFootstep);
	
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{	
			blockIcon = iconRegister.registerIcon("tpmod:Teleport_Dirt" );
	}

}
