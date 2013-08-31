package com.Item;

import com.TeleporterMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTeleportationBall extends Item 
{
	
        public ItemTeleportationBall(int id) {
                super(id);
                this.setUnlocalizedName("Teleport_Ball");
                this.setCreativeTab(TeleporterMod.TpTab);	
          
        }
        
        @Override
    	public void registerIcons(IconRegister iconRegister)
    	{		
    		itemIcon = iconRegister.registerIcon("tpmod:Teleport_Ball");
    	}
        
      
    }
