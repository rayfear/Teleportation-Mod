package com;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;

import com.command.CommandJump;
import com.config.TeleportationConfig;
import com.tab.TabTeleportation;
import com.world.OreWorldGenerator;
import com.world.WorldGenTeleportTrees;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="tpmod", name="Teleportation Mod", version="2.4.1", useMetadata = true)
@NetworkMod(clientSideRequired=true, serverSideRequired=false)

public class TeleporterMod 
{

	@Instance ("tpmod")
	public static TeleporterMod instance;
	
	public static CreativeTabs TpTab = new TabTeleportation("TpTab");
	
	public static WorldGenTeleportTrees worldGeneratorTeleTree = new WorldGenTeleportTrees(false, 4, 0, 0, false);

	@SidedProxy(clientSide = "com.ClientProxyTp", serverSide = "com.CommonProxyTp")
	public static ClientProxyTp proxy;

	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		//Proxy
		proxy.register();
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.TpTab", "en_US", "Teleportation Mod");
		GameRegistry.registerWorldGenerator(new OreWorldGenerator());

	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		TeleportationConfig.loadConfig(new Configuration(e.getSuggestedConfigurationFile()));
	}
	
	  @EventHandler
	  public void serverLoad(FMLServerStartingEvent event)
	  {
	    event.registerServerCommand(new CommandJump());
	  }

	
}