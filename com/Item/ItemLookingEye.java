package com.Item;

import com.TeleporterMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemLookingEye extends Item
{

	public ItemLookingEye(int id) 
	{
		super(id);
		this.setUnlocalizedName("Watching_Eye");
		this.setCreativeTab(TeleporterMod.TpTab);


	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{		
		itemIcon = iconRegister.registerIcon("tpmod:Watching_Eye");
	}

}
