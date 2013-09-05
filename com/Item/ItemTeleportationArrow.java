package com.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemTeleportationArrow extends Item
{

	public ItemTeleportationArrow(int par1) 
	{
		super(par1);
		this.setUnlocalizedName("tpArrow");
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("tpmod:tpArrow");
    }

}
