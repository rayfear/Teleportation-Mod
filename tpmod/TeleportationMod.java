package tpmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import tpmod.block.TeleportationBlocks;
import tpmod.block.tileentity.TileEntityTeleportationDoor;
import tpmod.command.CommandJump;
import tpmod.config.TeleportationConfig;
import tpmod.crafting.TeleportationCrafting;
import tpmod.entity.TeleportationEntities;
import tpmod.event.TpModEvents;
import tpmod.gui.TeleportationModGUIHandler;
import tpmod.item.TeleportationItems;
import tpmod.proxies.CommonProxyTp;
import tpmod.tab.TabTeleportation;
import tpmod.vip.VIPData;
import tpmod.world.OreWorldGenerator;
import tpmod.world.TeleportationBiomes;
import tpmod.world.TeleportationDimensions;
import tpmod.world.WorldGenTeleportTrees;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "tpmod", name = "Teleportation Mod", version = "2.0", useMetadata = true)

public class TeleportationMod
{
    @Instance("tpmod")
    public static TeleportationMod INSTANCE;
    
    public static String VERSION = "2.0";
    public static String MODID = "tpmod";

    public static CreativeTabs teleportationTab = new TabTeleportation("TpTab");

    public static WorldGenTeleportTrees worldGeneratorTeleTree = new WorldGenTeleportTrees(true, false);
    
    @SidedProxy(clientSide = "tpmod.proxies.ClientProxyTp", serverSide = "tpmod.proxies.CommonProxyTp")
    public static CommonProxyTp proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        TeleportationConfig.loadConfig(new Configuration(e.getSuggestedConfigurationFile()));
    	
        proxy.register();
        
        TeleportationBlocks.registerBlocks();
        TeleportationItems.registerItems();
        TeleportationCrafting.registerCrafting();
        TeleportationEntities.registerEntities();
        TeleportationBiomes.registerBiomes();
        TeleportationDimensions.registerDimensions();
        TpModEvents.register();
        
        GameRegistry.registerTileEntity(TileEntityTeleportationDoor.class, MODID + ":teleportation_door");
        
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new TeleportationModGUIHandler());
        
        VIPData.loadVIPs();
        
        GameRegistry.registerWorldGenerator(new OreWorldGenerator(), 0);
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandJump());
    }
}