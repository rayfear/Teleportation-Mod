package tpmod.block;

import net.minecraft.block.Block;
import tpmod.TeleportationMod;
import cpw.mods.fml.common.registry.GameRegistry;

public class TeleportationBlocks
{
    public static final Block teleportationGrass = new BlockTeleportingGrass();
    public static final Block teleportationDirt = new BlockTeleportingDirt();
    public static final Block teleportationOre = new BlockTeleportingOre();
    public static final BlockTpFire teleportationFire = (BlockTpFire)(new BlockTpFire());
    public static final BlockTpPortal teleportationPortal = (BlockTpPortal) new BlockTpPortal();
    public static final Block teleportationBlock = new BlockTpBlock();
    public static final Block teleportationSapling = new BlockTpSapling();

    public static final Block teleportationDoor = new BlockTeleportationDoor();
    
    public static void registerBlocks()
    {
    	GameRegistry.registerBlock(teleportationGrass, "teleportation_grass");
    	GameRegistry.registerBlock(teleportationDirt, "teleportation_dirt");
    	GameRegistry.registerBlock(teleportationOre, "teleportation_ore");
    	GameRegistry.registerBlock(teleportationFire, "teleportation_fire");
    	GameRegistry.registerBlock(teleportationPortal, "teleportation_portal");
    	GameRegistry.registerBlock(teleportationBlock, "teleportation_block");
    	GameRegistry.registerBlock(teleportationSapling, "teleportation_sapling");
    	//GameRegistry.registerBlock(teleportationDoor, "teleportation_door");
    }
    
    public static String modID(String block)
    {
    	return TeleportationMod.MODID + ":" + block;
    }
}