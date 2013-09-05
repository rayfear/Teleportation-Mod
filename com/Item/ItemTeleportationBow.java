package com.Item;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import com.SavedLocation;
import com.entity.EntityTpArrow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTeleportationBow extends Item
{

	public ItemTeleportationBow(int par1)
	{
		super(par1);
		this.setUnlocalizedName("tpBow");
		this.maxStackSize = 1;
	    this.setMaxDamage(384);
	}

	@SideOnly(Side.CLIENT)
	private Icon [] bowIcons = new Icon [4];

	private SavedLocation location = null;
	public static double x;
	public static double y;
	public static double z;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons ( IconRegister iconreg )
	{
		this.itemIcon = iconreg.registerIcon ("tpmod:" + this.getUnlocalizedName().substring ( 5 ) );

		for ( int i = 1; i < this.bowIcons.length; i++ )
			this.bowIcons[i] = iconreg.registerIcon ("tpmod:" + this.getUnlocalizedName().substring ( 5 ) + "_pull_" + ( i - 1 ) );
	}


	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		if(location != null)
		{
			if (par1ItemStack.stackTagCompound == null)
			{
				par1ItemStack.setTagCompound(new NBTTagCompound());
			}

			if (!par1ItemStack.stackTagCompound.hasKey("ench"))
			{
				par1ItemStack.stackTagCompound.setTag("gegy", new NBTTagList("gegy"));
			}

			NBTTagList var3 = (NBTTagList)par1ItemStack.stackTagCompound.getTag("gegy");
			NBTTagCompound var4 = new NBTTagCompound();
			var4.setDouble("tpX", location._x);
			var4.setDouble("tpY", location._y);
			var4.setDouble("tpZ", location._z);

			var3.appendTag(var4);
		}

	}


	private void savetpPosition(ItemStack par1ItemStack,
			EntityPlayer par3EntityPlayer) {
		if(par1ItemStack.stackTagCompound == null)
		{
			par1ItemStack.setTagCompound(new NBTTagCompound());
		}


		if (!par1ItemStack.stackTagCompound.hasKey("tpPosition"))
		{
			par1ItemStack.stackTagCompound.setTag("tpPosition", new NBTTagList("tpPosition"));
		}

		NBTTagList tpPositionTagList = (NBTTagList)par1ItemStack.stackTagCompound.getTag("tpPosition");
		NBTTagCompound tpPositionTag = new NBTTagCompound();
		
		tpPositionTag.setDouble("x", par3EntityPlayer.posX);
		tpPositionTag.setDouble("y", par3EntityPlayer.posY);
		tpPositionTag.setDouble("z", par3EntityPlayer.posZ);
		



		tpPositionTagList.appendTag(tpPositionTag);
	}

	private boolean hasSavedPosition(ItemStack par1ItemStack) 
	{
		return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("tpPosition");
	}


	public static void readtpPosition(ItemStack par1ItemStack, Entity par3Entity) 
	{
		NBTTagList tpPositionTagList = (NBTTagList)par1ItemStack.stackTagCompound.getTag("tpPosition");
		NBTTagCompound tpPositionTag = (NBTTagCompound) tpPositionTagList.tagAt(0);

		x = tpPositionTag.getDouble("x");
		y = tpPositionTag.getDouble("y");
		z = tpPositionTag.getDouble("z");
	} 

	@Override
	public Icon getIcon ( ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining ) {
		if ( player.getItemInUse() == null )
			return this.itemIcon;
		int time = stack.getMaxItemUseDuration () - useRemaining;
		if ( time >= 18 )
			return this.bowIcons [3];
		else if ( time > 13 )
			return this.bowIcons [2];
		else if ( time > 0 )
			return this.bowIcons [1];

		return this.bowIcons [0];
	}

	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		if(!par3EntityPlayer.isSneaking())
		{
			if(this.hasSavedPosition(par1ItemStack))
			{
				readtpPosition(par1ItemStack, par3EntityPlayer);
			}
			else
			{
				par3EntityPlayer.addChatMessage("You haven't got any co-ords set. Register your co-ords by sneaking and right clicking!");
			}

			if(x != 0 && y != 0 && z!= 0)
			{
				int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

				ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
				MinecraftForge.EVENT_BUS.post(event);
				if (event.isCanceled())
				{
					return;
				}
				j = event.charge;

				boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

				if (flag || par3EntityPlayer.inventory.hasItem (TeleportationItems.tpArrow.itemID ) )
				{
					float f = (float)j / 20.0F;
					f = (f * f + f * 2.0F) / 3.0F;

					if ((double)f < 0.1D)
					{
						return;
					}

					if (f > 1.0F)
					{
						f = 1.0F;
					}

					if(this.hasSavedPosition(par1ItemStack))
					{
						readtpPosition(par1ItemStack, par3EntityPlayer);
					}
					else
					{
						par3EntityPlayer.addChatMessage("You haven't got any co-ords set. Register your co-ords by sneaking and right clicking!");
					}
					EntityTpArrow tpArrow = new EntityTpArrow(par2World, par3EntityPlayer, f * 2.0F);

					if (f == 1.0F)
					{
						tpArrow.setIsCritical(true);
					}

					int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

					if (k > 0)
					{
						tpArrow.setDamage(tpArrow.getDamage() + (double)k * 0.5D + 0.5D);
					}

					int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

					if (l > 0)
					{
						tpArrow.setKnockbackStrength(l);
					}

					if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
					{
						tpArrow.setFire(100);
					}

					par1ItemStack.damageItem(1, par3EntityPlayer);
					par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

					if (flag)
					{
						tpArrow.canBePickedUp = 2;
					}
					else
					{
						par3EntityPlayer.inventory.consumeInventoryItem(TeleportationItems.tpArrow.itemID);
					}

					if (!par2World.isRemote)
					{
						par2World.spawnEntityInWorld(tpArrow);
					}
				}
				else
				{
					par3EntityPlayer.addChatMessage("You haven't got any co-ords set. Register your co-ords by sneaking and right clicking!");
				}
			}
		}	
		else
		{	
			if(par3EntityPlayer.getClass()!= EntityClientPlayerMP.class)
			{
				if(!this.hasSavedPosition(par1ItemStack))
				{
					this.savetpPosition(par1ItemStack, par3EntityPlayer);
					par3EntityPlayer.addChatMessage("Succsesfully Set Co-ords");
				}
				else
				{
					par1ItemStack.stackTagCompound.removeTag("tpPosition");
					this.savetpPosition(par1ItemStack, par3EntityPlayer);
					par3EntityPlayer.addChatMessage("Succsesfully Set Co-ords");
				}
				
			}

		}

	}

	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		return par1ItemStack;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 72000;
	}

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.bow;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{

		if(!par3EntityPlayer.isSneaking())
		{
			if(this.hasSavedPosition(par1ItemStack))
			{
				readtpPosition(par1ItemStack, par3EntityPlayer);
			}
			else
			{
				par3EntityPlayer.addChatMessage("You haven't got any co-ords set. Register your co-ords by sneaking and right clicking!");
			}
			if(x != 0  && y != 0 && z != 0)
			{
				ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
				MinecraftForge.EVENT_BUS.post(event);

				if (event.isCanceled())
				{
					return event.result;
				}

				if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(TeleportationItems.tpArrow.itemID))
				{
					par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
				}
			}
			else
			{
				par3EntityPlayer.addChatMessage("You haven't got any co-ords set. Register your co-ords by sneaking and right clicking!");
			}


		}
		else
		{	
			if(par3EntityPlayer.getClass()!= EntityClientPlayerMP.class)
			{
				if(!this.hasSavedPosition(par1ItemStack))
				{
					this.savetpPosition(par1ItemStack, par3EntityPlayer);
					par3EntityPlayer.addChatMessage("Succsesfully Set Co-ords");
				}
				else
				{
					par1ItemStack.stackTagCompound.removeTag("tpPosition");
					this.savetpPosition(par1ItemStack, par3EntityPlayer);
					par3EntityPlayer.addChatMessage("Succsesfully Set Co-ords");
				}
				
			}

		}


		return par1ItemStack;
	}
}