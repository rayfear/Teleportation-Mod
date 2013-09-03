package com.Item;

import net.minecraft.item.Item;

import com.TeleporterMod;
import com.config.TeleportationConfig;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class TeleportationItems
{
	public final static Item Teleporter = new ItemTeleporter(TeleportationConfig.TeleporterID);
	public final static Item LookingWand = new ItemLookWand(TeleportationConfig.LookingWandID);
	public final static Item ObsidianSticks = new ItemObsidianStick(TeleportationConfig.ObsidianSticksID);
	public final static Item TeleportationShards = new ItemTeleportationShards(TeleportationConfig.TeleportationShardsID);
	public final static Item TeleporterRemember = new ItemTeleportingWand(TeleportationConfig.TeleporterRememberID);
	public final static Item TeleportationBall = new ItemTeleportationBall(TeleportationConfig.TeleportationBallID);
	public final static Item MiniPortal = new ItemMiniPortal(TeleportationConfig.MiniPortalID);
	public final static Item FlintAndDiamond = new ItemFlintAndDiamond(TeleportationConfig.FlintAndDiamondID);
	public final static Item LookingEye = new ItemLookingEye(TeleportationConfig.LookingEyeID);
	
	public static void registerItems()
	{
		LanguageRegistry.addName(LookingWand, "Teleportation Wand");
		LanguageRegistry.addName(ObsidianSticks, "Obsidian Sticks");
		LanguageRegistry.addName(TeleportationShards, "Teleportation Shards");
		LanguageRegistry.addName(FlintAndDiamond, "Flint and Diamond");
		LanguageRegistry.addName(Teleporter, "Portal Teleportation Wand");
		LanguageRegistry.addName(TeleporterRemember, "Teleportation Wand");
		LanguageRegistry.addName(TeleportationBall, "Teleportation Orb");
		LanguageRegistry.addName(MiniPortal, "Portal Wand Top");
		LanguageRegistry.addName(LookingEye, "Watching Eye");
		
		TeleportationShards.setCreativeTab(TeleporterMod.TpTab);
		ObsidianSticks.setCreativeTab(TeleporterMod.TpTab);
		FlintAndDiamond.setCreativeTab(TeleporterMod.TpTab);
		Teleporter.setCreativeTab(TeleporterMod.TpTab);
		TeleporterRemember.setCreativeTab(TeleporterMod.TpTab);
	}
}
