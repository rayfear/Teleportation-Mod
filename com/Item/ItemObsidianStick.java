package com.Item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemObsidianStick extends Item 
{
	
        public ItemObsidianStick(int id) {
                super(id);
                this.setUnlocalizedName("Obsidian_Sticks");
                this.setCreativeTab(CreativeTabs.tabMaterials);	
          
        }
        
        @Override
    	public void registerIcons(IconRegister iconRegister)
    	{		
    		itemIcon = iconRegister.registerIcon("tpmod:Obsidian_Sticks");
    	}
        
        
    }
