package tpmod.event;

import net.minecraftforge.common.MinecraftForge;

public class TpModEvents
{
    public static void register()
    {
        MinecraftForge.EVENT_BUS.register(new EventBonemeel());
    }
}
