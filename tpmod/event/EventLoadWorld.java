package tpmod.event;

import tpmod.world.TeleportationWorldSavedData;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent.Load;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventLoadWorld 
{
	@SubscribeEvent
	public void onWorldLoad(Load event)
	{
		World world = event.world;
		
		TeleportationWorldSavedData.key = "";
		
		TeleportationWorldSavedData.getData(world);
	}
}
