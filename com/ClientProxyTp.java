package com;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import com.ThreeDee.RenderWand;
import com.ThreeDee.RenderWand2;
import com.ThreeDee.RenderWand3;

public class ClientProxyTp extends CommonProxyTp
{

	@Override
	public void registerRenderThings()
	{
		//render
		MinecraftForgeClient.registerItemRenderer(5001, (IItemRenderer)new RenderWand());
		MinecraftForgeClient.registerItemRenderer(5002, (IItemRenderer)new RenderWand2());
		MinecraftForgeClient.registerItemRenderer(5003, (IItemRenderer)new RenderWand3());
	}
}
