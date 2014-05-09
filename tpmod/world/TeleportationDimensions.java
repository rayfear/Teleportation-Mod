package tpmod.world;

import tpmod.config.TeleportationConfig;
import net.minecraftforge.common.DimensionManager;

public class TeleportationDimensions
{
    public static int dimension = TeleportationConfig.dimensionID;

    public static void registerDimensions()
    {
        DimensionManager.registerProviderType(dimension, WorldProviderTp.class, true);
        DimensionManager.registerDimension(dimension, dimension);
    }
}
