package tpmod.world;

import net.minecraft.world.biome.BiomeGenBase;

public class TeleportationBiomes
{
    public static BiomeGenBase teleportbiome = new BiomeGenTeleport(50).setColor(747097).setBiomeName("Tp").setMinMaxHeight(0.2F, 0.5F);

    public static void registerBiomes()
    {
    }
}
