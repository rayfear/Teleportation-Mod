package tpmod.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import tpmod.updates.Update;
import tpmod.updates.UpdateChecker;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventJoinWorld
{
	@SubscribeEvent
	public void onEvent(EntityJoinWorldEvent e)
	{
		if(e.entity.worldObj.isRemote)
		{
			if (e.entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) e.entity;

				player.addChatMessage(new ChatComponentText("[Teleportation Mod] Checking for updates..."));

				UpdateChecker checker = new UpdateChecker();
				Update update = checker.checkForUpdates();
				
				if(update.isAvailable)
				{
					player.addChatMessage(new ChatComponentText(""));
					player.addChatMessage(new ChatComponentText("Version " + update.version + " is now available!"));
					player.addChatMessage(new ChatComponentText("What's New: " + update.update));
					player.addChatMessage(new ChatComponentText(""));
				}
				else
				{
					player.addChatMessage(new ChatComponentText("No new updates are available, you have the latest version."));
				}
				
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}
	}
}
