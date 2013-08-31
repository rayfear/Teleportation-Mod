package com.block;

import java.util.Random;

import com.TeleporterMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTeleportingGrass extends Block
{
	@SideOnly(Side.CLIENT)
	private Icon iconGrassTop;

	public BlockTeleportingGrass(int par1)
	{
		super(par1, Material.grass);
		this.setTickRandomly(true);
		this.setHardness(0.99F);
		this.setResistance(0F);
		this.setUnlocalizedName("TeleportingGrass");
		this.setStepSound(soundGrassFootstep);
	}

	
	@SideOnly(Side.CLIENT)

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister par1IconRegister)
	{	
			this.blockIcon = par1IconRegister.registerIcon("tpmod:Teleport_Grass_Side");
			this.iconGrassTop = par1IconRegister.registerIcon("tpmod:Teleport_Grass_Top");
	}

	@SideOnly(Side.CLIENT)

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public Icon getIcon(int par1, int par2)
	{
		return par1 == 1 ? this.iconGrassTop : (par1 == 0 ? TeleporterMod.TeleportingDirt.getBlockTextureFromSide(par1) : this.blockIcon);
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote)
		{
			if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && par1World.getBlockLightOpacity(par2, par3 + 1, par4) > 2)
			{
				par1World.setBlock(par2, par3, par4, TeleporterMod.TeleportingDirt.blockID);
			}
			else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
			{
				for (int l = 0; l < 4; ++l)
				{
					int i1 = par2 + par5Random.nextInt(3) - 1;
					int j1 = par3 + par5Random.nextInt(5) - 3;
					int k1 = par4 + par5Random.nextInt(3) - 1;
					int l1 = par1World.getBlockId(i1, j1 + 1, k1);

					if (par1World.getBlockId(i1, j1, k1) == TeleporterMod.TeleportingDirt.blockID && par1World.getBlockLightValue(i1, j1 + 1, k1) >= 4 && par1World.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
					{
						par1World.setBlock(i1, j1, k1, this.blockID);
					}
				}
			}
		}
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return TeleporterMod.TeleportingDirt.idDropped(0, par2Random, par3);
	}

	@SideOnly(Side.CLIENT)

	/**
	 * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
	 */
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		if (par5 == 1)
		{
			return this.iconGrassTop;
		}
		else if (par5 == 0)
		{
			return TeleporterMod.TeleportingDirt.getBlockTextureFromSide(par5);
		}
		else
		{
			return this.blockIcon;
		}

	}







}
