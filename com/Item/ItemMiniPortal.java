package com.Item;

import com.TeleporterMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMiniPortal extends Item 
{
	
        public ItemMiniPortal(int id) 
        {
                super(id);
                this.setUnlocalizedName("Portal_Top");
                this.setCreativeTab(TeleporterMod.TpTab);	
        }
        
        
        @Override
    	public void registerIcons(IconRegister iconRegister)
    	{		
    		itemIcon = iconRegister.registerIcon("tpmod:Portal_Top");
    	}
        
    }
