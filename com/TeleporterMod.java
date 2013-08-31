package com;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import com.Item.*;
import com.world.*;
import com.block.*;
import com.tab.*;
import com.entity.*;
import com.model.*;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;





@Mod(modid="tpmod", name="Teleportation Mod", version="2.4.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)

public class TeleporterMod 
{

	public static CreativeTabs TpTab = new TabTeleportation("TpTab");
	public static Minecraft mc;
	public final static Item Teleporter = new ItemTeleporter(5002 - 256);
	public final static Item LookingWand = new ItemLookWand(5003 - 256);
	public final static Item ObsidianSticks = new ItemObsidianStick(5004);
	public final static Item TeleportationShards = new ItemTeleportationShards(5005);
	public final static Item TeleporterRemember = new ItemTeleportingWand(5001 - 256);
	public final static Item TeleportationBall = new ItemTeleportationBall(5006);
	public final static Item MiniPortal = new ItemMiniPortal(5007);
	public final static Item FlintAndDiamond = new ItemFlintAndDiamond(5008);
	public final static Item LookingEye = new ItemLookingEye(5009);
	
	

	public static final Block TeleportingDirt = new BlockTeleportingDirt(254,2).setUnlocalizedName("Teleport_Dirt").setHardness(1F);
	public static final Block TeleportingGrass = new BlockTeleportingGrass(253).setUnlocalizedName("TeleportingGrass").setHardness(1F);

	public static final Block TeleportingOre = new BlockTeleportingOre(3055,1).setUnlocalizedName("Teleport_Ore").setHardness(5F);
	public static final BlockTpFire FireBlock = (BlockTpFire)(new BlockTpFire(1550)).setHardness(0.0F).setLightValue(1.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("fire");
	public static final BlockTpPortal portal = (BlockTpPortal) new BlockTpPortal(3645).setUnlocalizedName("Tp_Portal").setBlockUnbreakable().setLightValue(2F).setHardness(1F);
	public static final Block TpBlock = new BlockTpBlock(2001,6).setUnlocalizedName("Tp_Block").setHardness(5F);


	public static int dimension = 20;
	public static BiomeGenBase teleportbiome = new BiomeGenTeleport(50).setColor(747097).setBiomeName("Tp").setMinMaxHeight(0.1F, 0.4F);
	public static WorldGenTeleportTrees worldGeneratorTeleTree = new WorldGenTeleportTrees(false, 4, 0, 0, false);

	

	@SidedProxy(clientSide = "com.ClientProxyTp", serverSide = "com.CommonProxyTp")
	public static ClientProxyTp proxy;
	static int startEntityId = 542;

	
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		//proxy
		proxy.registerRenderThings();

		//Mob
		EntityRegistry.registerModEntity(EntityTeleportationMob.class, "TpMob", 1, this, 80, 1, true);
		EntityRegistry.addSpawn(EntityTeleportationMob.class, 20, 2, 4, EnumCreatureType.monster,  this.teleportbiome);
		LanguageRegistry.instance().addStringLocalization("TpMob", "TpMob");
		registerEntityEgg(EntityTeleportationMob.class, 0x505B09, 0x161616);
		LanguageRegistry.instance().addStringLocalization("entity.Teleport.TpMob.name", "Tp Mob");
		RenderingRegistry.instance().registerEntityRenderingHandler(EntityTeleportationMob.class, new RenderTpMob(new ModelTeleportationMob(), 0.3F));

		//Language Registry
		LanguageRegistry.instance().addStringLocalization("itemGroup.TpTab", "en_US", "Teleportation Mod");

		LanguageRegistry.addName(LookingWand, "Teleportation Wand");
		LanguageRegistry.addName(ObsidianSticks, "Obsidian Sticks");
		LanguageRegistry.addName(TeleportationShards, "Teleportation Shards");
		LanguageRegistry.addName(TpBlock, "Tp Block");
		LanguageRegistry.addName(portal, "Portal");
		LanguageRegistry.addName(TeleportingGrass, "Teleporting Grass");
		LanguageRegistry.addName(FireBlock, "Fire");
		LanguageRegistry.addName(FlintAndDiamond, "Flint and Diamond");
		LanguageRegistry.addName(TeleportingDirt, "Teleporting Dirt");
		LanguageRegistry.addName(Teleporter, "Portal Teleportation Wand");
		LanguageRegistry.addName(TeleporterRemember, "Teleportation Wand");
		LanguageRegistry.addName(TeleportingOre, "Teleportation Ore");
		LanguageRegistry.addName(TeleportationBall, "Teleportation Orb");
		LanguageRegistry.addName(MiniPortal, "Portal Wand Top");
		LanguageRegistry.addName(LookingEye, "Watching Eye");


		//Creative Tabs
		TpBlock.setCreativeTab(this.TpTab);
		TeleportingDirt.setCreativeTab(this.TpTab);
		TeleportingGrass.setCreativeTab(this.TpTab);
		TeleportingOre.setCreativeTab(this.TpTab);
		TeleportationShards.setCreativeTab(this.TpTab);
		ObsidianSticks.setCreativeTab(this.TpTab);
		FlintAndDiamond.setCreativeTab(this.TpTab);
		Teleporter.setCreativeTab(this.TpTab);
		TeleporterRemember.setCreativeTab(this.TpTab);

		//GameRegistry
		GameRegistry.registerBlock(TeleportingDirt,"Teleporting Dirt");
		GameRegistry.registerBlock(TeleportingGrass, "Teleporting Grass");
		GameRegistry.registerBlock(portal, "Portal");
		GameRegistry.registerBlock(FireBlock, "Fire");
		GameRegistry.registerBlock(TpBlock, "Tp Block");
		GameRegistry.registerBlock(TeleportingOre, "Teleportation Ore");

		//Dimension
		DimensionManager.registerProviderType(dimension, WorldProviderTp.class, true);
		DimensionManager.registerDimension(dimension, dimension);

		//Crafting
		ItemStack ObsidianStack = new ItemStack(Block.obsidian);
		ItemStack ObsidianStickStack = new ItemStack(ObsidianSticks);
		ItemStack TeleportationShardsStack = new ItemStack(TeleportationShards);
		ItemStack FlintStack = new ItemStack(Item.flint);
		ItemStack DiamondStack = new ItemStack(Item.diamond);
		GameRegistry.addRecipe(new ItemStack(ObsidianSticks, 4), "x", "x", 
				'x', ObsidianStack);
		GameRegistry.addRecipe(new ItemStack(TeleporterRemember, 1), "y", "x","x", 
				'x', ObsidianStickStack, 'y', TeleportationBall);

		GameRegistry.addRecipe(new ItemStack(Teleporter, 1), "y", "x","x", 
				'x', ObsidianStickStack, 'y', MiniPortal);

		GameRegistry.addRecipe(new ItemStack(TpBlock, 1), "tt", "tt", 
				't', TeleportationShards);

		GameRegistry.addRecipe(new ItemStack(FlintAndDiamond, 1), "d ", " f", 
				'f', FlintStack,'d', DiamondStack);
		GameRegistry.addRecipe(new ItemStack(Teleporter, 1), "t", 
				't', Teleporter);
		GameRegistry.addRecipe(new ItemStack(TeleporterRemember, 1), "t", 
				't', TeleporterRemember);
		GameRegistry.addRecipe(new ItemStack(TeleportationBall, 1), "ggg","gtg","ggg", 
				't', TeleportationShards, 'g',Block.glass);

		GameRegistry.addRecipe(new ItemStack(MiniPortal, 1), "ggg","gtg","ggg", 
				't', Item.flintAndSteel, 'g',Block.obsidian);

		GameRegistry.addRecipe(new ItemStack(LookingWand, 1), "e","s","s", 
				'e', LookingEye, 's', ObsidianSticks);




		//Word Generation
		GameRegistry.registerWorldGenerator(new OreWorldGenerator());

	}

	public static boolean someConfigFlag;


	public static int getUniqueEntityId() 
	{

		do 
		{
			startEntityId++;
		} 
		while (EntityList.getStringFromID(startEntityId) != null);

		return startEntityId;
	}

	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) 
	{
		int id = getUniqueEntityId();


		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	}
}