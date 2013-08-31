package com.Item;


import com.TeleporterClient;
import com.model.ModelWand;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

public class ItemTeleporter extends Item
{
     public ItemTeleporter(int id) {
              super(id);
              this.setUnlocalizedName("Portal_Wand");
              this.setCreativeTab(CreativeTabs.tabMisc);
              this.maxStackSize = 1;
     	      this.setMaxDamage(100);
     	      this.isFull3D();
               

           
        }
     
     @Override
 	public void registerIcons(IconRegister iconRegister)
 	{		
 		itemIcon = iconRegister.registerIcon("tpmod:Portal_Wand");
 	}
     
        public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par2EntityPlayer)
        {
        	TeleporterClient teleporter = new TeleporterClient(par2World);
        	teleporter.placeInPortal(par2EntityPlayer, 0, 0, 0, 0);
        	par1ItemStack.damageItem(1, par2EntityPlayer);
            return par1ItemStack;
        
           
        }
         
       
        public boolean hasEffect(ItemStack par1ItemStack)
        {
            return true;
        }
        public EnumRarity getRarity(ItemStack par1ItemStack)
        {
            return par1ItemStack.getItemDamage() == 0 ? EnumRarity.rare : EnumRarity.epic;
        }
        
      

    }
