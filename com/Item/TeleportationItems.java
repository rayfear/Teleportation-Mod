package com.Item;

import net.minecraft.item.EnumToolMaterial;
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
	
	public final static Item tpArrow = new ItemTeleportationArrow(TeleportationConfig.tpArrowID).setCreativeTab(TeleporterMod.TpTab);
	public final static Item tpBow = new ItemTeleportationBow(TeleportationConfig.tpBowID).setCreativeTab(TeleporterMod.TpTab);

	public static final Item tpSword = new ItemTpSword(TeleportationConfig.TpSwordID, EnumToolMaterial.WOOD).setCreativeTab(TeleporterMod.TpTab);
	
	public static void registerItems()
	{
		LanguageRegistry.addName(LookingWand, "Jump Wand");
		LanguageRegistry.addName(ObsidianSticks, "Obsidian Sticks");
		LanguageRegistry.addName(TeleportationShards, "Teleportation Shards");
		LanguageRegistry.addName(FlintAndDiamond, "Flint and Diamond");
		LanguageRegistry.addName(Teleporter, "Portal Teleportation Wand");
		LanguageRegistry.addName(TeleporterRemember, "Warp Wand");
		LanguageRegistry.addName(TeleportationBall, "Teleportation Orb");
		LanguageRegistry.addName(MiniPortal, "Portal Wand Top");
		LanguageRegistry.addName(LookingEye, "Watching Eye");
		LanguageRegistry.addName(tpArrow, "Teleportation Arrow");
		LanguageRegistry.addName(tpBow, "Teleportation Bow");
		LanguageRegistry.addName(tpSword, "Teleportation Sword");
		
		TeleportationShards.setCreativeTab(TeleporterMod.TpTab);
		ObsidianSticks.setCreativeTab(TeleporterMod.TpTab);
		FlintAndDiamond.setCreativeTab(TeleporterMod.TpTab);
		Teleporter.setCreativeTab(TeleporterMod.TpTab);
		TeleporterRemember.setCreativeTab(TeleporterMod.TpTab);
	}
}
