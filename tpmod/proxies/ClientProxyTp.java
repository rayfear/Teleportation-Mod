package tpmod.proxies;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import tpmod.Item.TeleportationItems;
import tpmod.block.TeleportationBlocks;
import tpmod.config.TeleportationConfig;
import tpmod.crafting.TeleportationCrafting;
import tpmod.entity.TeleportationEntities;
import tpmod.event.TpModEvents;
import tpmod.itemRenderers.RenderWand;
import tpmod.itemRenderers.RenderWand2;
import tpmod.itemRenderers.RenderWand3;
import tpmod.world.TeleportationBiomes;
import tpmod.world.TeleportationDimensions;

public class ClientProxyTp extends CommonProxyTp
{
    @Override
    public void register()
    {
        //Render
        if (TeleportationConfig.enable3D.getBoolean(true))
        {
            MinecraftForgeClient.registerItemRenderer(TeleportationItems.TeleporterRemember.itemID, (IItemRenderer)new RenderWand());
            MinecraftForgeClient.registerItemRenderer(TeleportationItems.Teleporter.itemID, (IItemRenderer)new RenderWand2());
            MinecraftForgeClient.registerItemRenderer(TeleportationItems.LookingWand.itemID, (IItemRenderer)new RenderWand3());
        }

        TeleportationBlocks.registerBlocks();
        TeleportationItems.registerItems();
        TeleportationCrafting.registerCrafting();
        TeleportationEntities.registerEntities();
        TeleportationBiomes.registerBiomes();
        TeleportationDimensions.registerDimensions();
        TpModEvents.register();
    }
}
