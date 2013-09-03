package com;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import com.Item.TeleportationItems;
import com.ThreeDee.RenderWand;
import com.ThreeDee.RenderWand2;
import com.ThreeDee.RenderWand3;
import com.block.TeleportationBlocks;
import com.config.TeleportationConfig;
import com.crafting.TeleportationCrafting;
import com.entity.TeleportationEntities;
import com.world.TeleportationBiomes;
import com.world.TeleportationDimensions;

public class ClientProxyTp extends CommonProxyTp
{

	@Override
	public void register()
	{
		//Render
		if(TeleportationConfig.enable3D.getBoolean(true))
		{
			MinecraftForgeClient.registerItemRenderer(TeleportationItems.TeleporterRemember.itemID, (IItemRenderer)new RenderWand());
			MinecraftForgeClient.registerItemRenderer(TeleportationItems.Teleporter.itemID, (IItemRenderer)new RenderWand2());
			MinecraftForgeClient.registerItemRenderer(TeleportationItems.LookingWand.itemID, (IItemRenderer)new RenderWand3());
		}
		
		TeleportationBlocks.registerBlocks();
		TeleportationItems.registerItems();
		TeleportationCrafting.registerCrafting();
		TeleportationEntities.registerEntities();
		TeleportationBiomes.registerBiomes();
		TeleportationDimensions.registerDimensions();
		
		
	}
}
