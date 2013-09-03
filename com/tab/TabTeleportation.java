package com.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.Item.TeleportationItems;



public class TabTeleportation extends CreativeTabs
{
	
	public TabTeleportation(String label) {
	    super(label);
	}
	@Override
	public ItemStack getIconItemStack() {
	    return new ItemStack(TeleportationItems.LookingEye);
	}
      
    }
