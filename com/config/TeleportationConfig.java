package com.config;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class TeleportationConfig
{	
	public static int TeleporterID;
	public static int LookingWandID;
	public static int ObsidianSticksID;
	public static int TeleportationShardsID;
	public static int TeleporterRememberID;
	public static int TeleportationBallID;
	public static int MiniPortalID;
	public static int FlintAndDiamondID;
	public static int LookingEyeID;
	public static int tpBowID;
	public static int tpArrowID;
	public static int TpSwordID;
	
	public static int TeleportationDirtID;
	public static int TeleportationGrassID;
	public static int TeleportationOreID;
	public static int FireID;
	public static int portalID;
	public static int TPBlockID;

	
	public static Property enable3D;

	public static void loadConfig(Configuration config)
	{
		config.load();

		//Blocks
		TeleportationGrassID = config.getTerrainBlock(Configuration.CATEGORY_BLOCK,
				"Tp Grass ID (ID Must be below 255)", 253, "TeleportationGrassID").getInt();
		TeleportationDirtID = config.getTerrainBlock(Configuration.CATEGORY_BLOCK,
				"Tp Dirt (ID Must be below 255)", 254, "TeleportationDirtID").getInt();
		TeleportationOreID = config.getTerrainBlock(Configuration.CATEGORY_BLOCK,
				"Tp Ore", 3055, "TeleportationOreID").getInt();
		FireID = config.getTerrainBlock(Configuration.CATEGORY_BLOCK,
				"Fire Block ID", 1550, "TeleportationOreID").getInt();
		TPBlockID = config.getTerrainBlock(Configuration.CATEGORY_BLOCK,
				"Tp Block ID", 2001, "TPBlockID").getInt();
		portalID = config.getTerrainBlock(Configuration.CATEGORY_BLOCK,
				"Portal ID", 3645, "portalID").getInt();

		//Items
		TeleporterID = config.get(Configuration.CATEGORY_ITEM,
				"Portal Teleportation Wand ID", 5002).getInt();
		TeleportationBallID = config.get(Configuration.CATEGORY_ITEM,
				"Teleportation Ball ID", 5006).getInt();
		FlintAndDiamondID = config.get(Configuration.CATEGORY_ITEM,
				"Flint And Diamond ID", 5008).getInt();
		LookingEyeID = config.get(Configuration.CATEGORY_ITEM,
				"Watching Eye ID", 5009).getInt();
		LookingWandID = config.get(Configuration.CATEGORY_ITEM,
				"Looking Wand ID", 5003).getInt();
		MiniPortalID = config.get(Configuration.CATEGORY_ITEM,
				"Portal Wand Top ID", 5007).getInt();
		ObsidianSticksID = config.get(Configuration.CATEGORY_ITEM,
				"Obsidian Sticks ID", 5004).getInt();
		TeleportationShardsID = config.get(Configuration.CATEGORY_ITEM,
				"Teleportation Shards ID", 5005).getInt();
		TeleporterRememberID = config.get(Configuration.CATEGORY_ITEM,
				"Teleporter Remember ID", 5001).getInt();
		
		tpBowID = config.get(Configuration.CATEGORY_ITEM,
				"Teleportation Bow ID", 5010).getInt();
		
		tpArrowID = config.get(Configuration.CATEGORY_ITEM,
				"Teleportation Arrow ID", 5011).getInt();
		
		
		TpSwordID = config.get(Configuration.CATEGORY_ITEM,
				"Teleportation Sword ID", 5012).getInt();

		//General
		enable3D = config.get(Configuration.CATEGORY_GENERAL, "Should Enable 3D Items", true);

		config.save();
	}

}
