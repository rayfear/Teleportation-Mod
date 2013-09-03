package com.world;

import net.minecraftforge.common.DimensionManager;

public class TeleportationDimensions
{
	public static int dimension = 20;
	
	public static void registerDimensions()
	{
		DimensionManager.registerProviderType(dimension, WorldProviderTp.class, true);
		DimensionManager.registerDimension(dimension, dimension);
	}
}
