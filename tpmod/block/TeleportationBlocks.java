package tpmod.block;

import net.minecraft.block.Block;
import tpmod.TeleporterMod;
import tpmod.config.TeleportationConfig;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class TeleportationBlocks
{
    public static final Block TeleportingDirt = new BlockTeleportingDirt(TeleportationConfig.TeleportationDirtID, 2).setUnlocalizedName("Teleport_Dirt").setHardness(1F);
    public static final Block TeleportingGrass = new BlockTeleportingGrass(TeleportationConfig.TeleportationGrassID).setUnlocalizedName("TeleportingGrass").setHardness(1F);
    public static final Block TeleportingOre = new BlockTeleportingOre(TeleportationConfig.TeleportationOreID, 1).setUnlocalizedName("Teleport_Ore").setHardness(5F);
    public static final BlockTpFire FireBlock = (BlockTpFire)(new BlockTpFire(TeleportationConfig.FireID)).setBlockUnbreakable().setLightValue(1.0F).setUnlocalizedName("tpFire").setTextureName("tpmod:tpFire");
    public static final BlockTpPortal portal = (BlockTpPortal) new BlockTpPortal(TeleportationConfig.portalID).setUnlocalizedName("Tp_Portal").setBlockUnbreakable().setLightValue(2F).setHardness(1F);
    public static final Block TpBlock = new BlockTpBlock(TeleportationConfig.TPBlockID, 6).setUnlocalizedName("Tp_Block").setHardness(5F);
    public static final Block tpSapling = new BlockTpSapling(TeleportationConfig.tpSaplingID).setUnlocalizedName("Tp_Sapling").setStepSound(Block.soundGrassFootstep).setCreativeTab(TeleporterMod.TpTab);

    public static void registerBlocks()
    {
        TpBlock.setCreativeTab(TeleporterMod.TpTab);
        TeleportingDirt.setCreativeTab(TeleporterMod.TpTab);
        TeleportingGrass.setCreativeTab(TeleporterMod.TpTab);
        TeleportingOre.setCreativeTab(TeleporterMod.TpTab);
        GameRegistry.registerBlock(TeleportingDirt, "Teleporting Dirt");
        GameRegistry.registerBlock(TeleportingGrass, "Teleporting Grass");
        GameRegistry.registerBlock(portal, "Portal");
        GameRegistry.registerBlock(FireBlock, "Fire");
        GameRegistry.registerBlock(TpBlock, "Tp Block");
        GameRegistry.registerBlock(TeleportingOre, "Teleportation Ore");
        GameRegistry.registerBlock(tpSapling, "Teleportation Sapling");
        LanguageRegistry.addName(TeleportingOre, "Teleportation Ore");
        LanguageRegistry.addName(TeleportingDirt, "Teleporting Dirt");
        LanguageRegistry.addName(TpBlock, "Tp Block");
        LanguageRegistry.addName(portal, "Portal");
        LanguageRegistry.addName(TeleportingGrass, "Teleporting Grass");
        LanguageRegistry.addName(FireBlock, "Fire");
        LanguageRegistry.addName(tpSapling, "Teleportation Sapling");
    }
}
