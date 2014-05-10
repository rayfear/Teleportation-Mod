package tpmod.proxies;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import tpmod.config.TeleportationConfig;
import tpmod.entity.EntityObserver;
import tpmod.entity.EntityWatcher;
import tpmod.entity.RenderObserver;
import tpmod.entity.RenderTpMob;
import tpmod.item.TeleportationItems;
import tpmod.itemRenderers.RenderWand;
import tpmod.itemRenderers.RenderWand2;
import tpmod.itemRenderers.RenderWand3;
import tpmod.model.ModelObserver;
import tpmod.model.ModelTeleportationMob;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxyTp extends CommonProxyTp
{
    @SuppressWarnings({ "deprecation", "static-access" })
	@Override
    public void register()
    {
        //Render
        if (TeleportationConfig.enable3D.getBoolean(true))
        {
			MinecraftForgeClient.registerItemRenderer(TeleportationItems.warpWand, (IItemRenderer)new RenderWand());
            MinecraftForgeClient.registerItemRenderer(TeleportationItems.portalTeleportationWand, (IItemRenderer)new RenderWand2());
            MinecraftForgeClient.registerItemRenderer(TeleportationItems.jumpWand, (IItemRenderer)new RenderWand3());
        }
        RenderingRegistry.instance().registerEntityRenderingHandler(EntityWatcher.class, new RenderTpMob(new ModelTeleportationMob(), 0.3F));
        RenderingRegistry.instance().registerEntityRenderingHandler(EntityObserver.class, new RenderObserver(new ModelObserver(), 0.3F));
    }
}
