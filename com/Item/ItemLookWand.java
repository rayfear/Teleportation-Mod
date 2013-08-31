package com.Item;


import com.TeleporterMod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemLookWand extends Item 
{
	
        public ItemLookWand(int id) {
                super(id);
                this.setUnlocalizedName("Looking_Vec_Wand");
                this.setCreativeTab(TeleporterMod.TpTab);
                this.maxStackSize = 1;
     	        this.setMaxDamage(100);
          
        }
        
        @Override
    	public void registerIcons(IconRegister iconRegister)
    	{		
    		itemIcon = iconRegister.registerIcon("tpmod:Looking_Vec_Wand");
    	}
        
        public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par2EntityPlayer)
        {
        	 if (!par2World.isRemote)
             {
                 Vec3 var5 = par2EntityPlayer.getPosition(1.0F);
                 ++var5.yCoord;
                 Vec3 var6 = par2EntityPlayer.getLook(1.0F);
                 Vec3 var7 = var5.addVector(var6.xCoord * 1000.0D, var6.yCoord * 1000.0D, var6.zCoord * 1000.0D);
                 MovingObjectPosition var4 = par2World.rayTraceBlocks_do_do(var5, var7, true, true);

                 if (var4 != null && var4.typeOfHit == EnumMovingObjectType.TILE)
                 {
                     int var8 = var4.blockX;
                     int var9 = var4.blockY;
                     int var10 = var4.blockZ;
                     EntityPlayerMP var11 = (EntityPlayerMP)par2EntityPlayer;

                     if (!var11.playerNetServerHandler.connectionClosed)
                     {
                         var11.setPositionAndUpdate((double)var8, (double)((float)var9 + 1.0F), (double)var10);
                         var11.fallDistance = 0.0F;
                     }
                 }
             }
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
