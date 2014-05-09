package tpmod.event;

import tpmod.config.TeleportationConfig;
import net.minecraftforge.common.MinecraftForge;

public class TpModEvents
{
    public static void register()
    {
        MinecraftForge.EVENT_BUS.register(new EventBonemeel());
        
        MinecraftForge.EVENT_BUS.register(new EventLoadWorld());
        
        if(TeleportationConfig.enableUpdateCheck.getBoolean(true))
        {
            MinecraftForge.EVENT_BUS.register(new EventJoinWorld());
        }
    }
}
