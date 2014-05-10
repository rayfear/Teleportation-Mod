package tpmod.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TeleportationHelper
{
	public void jump(World world, EntityPlayer player)
	{
		Vec3 vec3 = player.getPosition(1.0F);
		++vec3.yCoord;
		Vec3 playerLook = player.getLook(1.0F);
		Vec3 addVec3 = vec3.addVector(playerLook.xCoord * 1000.0D, playerLook.yCoord * 1000.0D, playerLook.zCoord * 1000.0D);
		MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3, addVec3, true);

		if (movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
		{
			int blockX = movingobjectposition.blockX;
			int blockY = movingobjectposition.blockY;
			int blockZ = movingobjectposition.blockZ;

			player.setPositionAndUpdate((double)blockX, (double)((float)blockY + 1.0F), (double)blockZ);
			player.fallDistance = 0.0F;
		}
	}
}
