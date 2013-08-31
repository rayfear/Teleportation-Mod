package com.tab;

import com.TeleporterMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;



public class TabTeleportation extends CreativeTabs
{
	
	public TabTeleportation(String label) {
	    super(label);
	}
	@Override
	public ItemStack getIconItemStack() {
	    return new ItemStack(TeleporterMod.LookingEye);
	}
      
    }
