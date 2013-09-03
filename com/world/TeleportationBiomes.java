package com.world;

import net.minecraft.world.biome.BiomeGenBase;

public class TeleportationBiomes 
{
	public static BiomeGenBase teleportbiome = new BiomeGenTeleport(50).setColor(747097).setBiomeName("Tp").setMinMaxHeight(0.1F, 0.4F);
	
	public static void registerBiomes()
	{
		
	}
}
