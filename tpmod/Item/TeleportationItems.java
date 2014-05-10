package tpmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemFood;
import tpmod.TeleportationMod;
import cpw.mods.fml.common.registry.GameRegistry;

public class TeleportationItems
{
	public final static Item warpWand = new ItemWarpWand().setCreativeTab(TeleportationMod.teleportationTab).setUnlocalizedName("WarpWand");
	public final static Item portalTeleportationWand = new ItemPortalTeleportationWand().setCreativeTab(TeleportationMod.teleportationTab).setUnlocalizedName("PortalWand");
	public final static Item jumpWand = new ItemJumpWand().setCreativeTab(TeleportationMod.teleportationTab).setUnlocalizedName("JumpWand");
	public final static Item obsidianSticks = new ItemObsidianStick().setCreativeTab(TeleportationMod.teleportationTab).setUnlocalizedName("ObsidianSticks");
	public final static Item teleportationShards = new ItemTeleportationShards().setCreativeTab(TeleportationMod.teleportationTab).setUnlocalizedName("TeleportationShards");
	public final static Item teleportationOrb = new ItemTeleportationOrb().setCreativeTab(TeleportationMod.teleportationTab).setUnlocalizedName("TeleportationOrb");
	public final static Item portalWandTop = new ItemPortalWandTop().setCreativeTab(TeleportationMod.teleportationTab).setUnlocalizedName("PortalWandTop");
	public final static Item flintAndDiamond = new ItemFlintAndDiamond().setCreativeTab(TeleportationMod.teleportationTab).setUnlocalizedName("FlintAndDiamond");
	public final static Item watchingEye = new ItemWatchingEye().setCreativeTab(TeleportationMod.teleportationTab).setUnlocalizedName("WatchingEye");
	public static final Item tpSword = new ItemTpSword(ToolMaterial.WOOD).setCreativeTab(TeleportationMod.teleportationTab).setUnlocalizedName("TeleportationSword");
	public final static Item tpBow = new ItemTeleportationBow().setCreativeTab(TeleportationMod.teleportationTab).setUnlocalizedName("TeleportationBow");
	public final static Item tpArrow = new ItemTeleportationArrow().setCreativeTab(TeleportationMod.teleportationTab).setUnlocalizedName("TeleportationArrow");
	public static final Item playerTeleporter = new ItemPlayerTeleporter();
	
	public static final ItemFood rawEnderMeat = (ItemFood) new ItemFood(4, true).setPotionEffect(9, 15, 6, 100).setUnlocalizedName("RawEnderMeat").setTextureName("tpmod:rawEnderMeat");
	public static final ItemFood cookedEnderMeat = (ItemFood) new ItemFood(6, true).setPotionEffect(1, 60, 1, 60).setUnlocalizedName("CookedEnderMeat").setTextureName("tpmod:cookedEnderMeat");

	public static final Item teleportationDoor = new ItemTeleportationDoor().setUnlocalizedName("TeleportationDoor");
	
	public static void registerItems()
	{
		GameRegistry.registerItem(warpWand, "warp_wand");
		GameRegistry.registerItem(portalTeleportationWand, "portal_wand");
		GameRegistry.registerItem(jumpWand, "jump_wand");
		GameRegistry.registerItem(obsidianSticks, "obsidian_sticks");
		GameRegistry.registerItem(teleportationShards, "teleportation_shards");
		GameRegistry.registerItem(teleportationOrb, "teleportation_orb");
		GameRegistry.registerItem(portalWandTop, "portal_wand_top");
		GameRegistry.registerItem(flintAndDiamond, "flint_and_diamond");
		GameRegistry.registerItem(watchingEye, "watching_eye");
		GameRegistry.registerItem(tpSword, "teleportation_sword");
		GameRegistry.registerItem(tpBow, "teleportation_bow");
		GameRegistry.registerItem(tpArrow, "teleportation_arrow");
		GameRegistry.registerItem(rawEnderMeat, "raw_ender_meat");
		GameRegistry.registerItem(cookedEnderMeat, "cooked_ender_meat");
		//GameRegistry.registerItem(playerTeleporter, "player_teleporter");
		
		//GameRegistry.registerItem(teleportationDoor, "teleportation_door_item");
	}
}
