package tpmod.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tpmod.TeleportationMod;

public class ItemPlayerTeleporter extends Item
{
	public ItemPlayerTeleporter()
	{
		super();
		this.setUnlocalizedName("player_teleporter");
		this.setTextureName(TeleportationMod.MODID + ":player_teleporter");
		this.setCreativeTab(TeleportationMod.teleportationTab);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.setLocationAndAngles(player.posX, player.posY + 200, player.posZ, player.rotationYaw, player.rotationPitch);
		EntityPlayer closestPlayer = world.getClosestPlayer(player.posX, player.posY - 200, player.posZ, 199);
		
		if(closestPlayer != null)
		{
			player.setLocationAndAngles(closestPlayer.posX, closestPlayer.posY, closestPlayer.posZ, closestPlayer.rotationYaw, closestPlayer.rotationPitch);
		}
		else
		{
			player.setLocationAndAngles(player.posX, player.posY - 203, player.posZ, player.rotationYaw, player.rotationPitch);
			player.fallDistance = 0;
			player.addChatMessage(new ChatComponentText("No Player Found!"));
		}
		
		return stack;
	}
}
