package tpmod.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TeleportationHelper
{
    public void jump(World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            Vec3 vec3 = player.getPosition(1.0F);
            ++vec3.yCoord;
            Vec3 lookVec = player.getLook(1.0F);
            Vec3 addedVector = vec3.addVector(lookVec.xCoord * 1000.0D, lookVec.yCoord * 1000.0D, lookVec.zCoord * 1000.0D);
            MovingObjectPosition movingObjPos = world.rayTraceBlocks_do_do(vec3, addedVector, true, true);

            if (movingObjPos != null && movingObjPos.typeOfHit == EnumMovingObjectType.TILE)
            {
                int blockX = movingObjPos.blockX;
                int blockY = movingObjPos.blockY;
                int blockZ = movingObjPos.blockZ;
                EntityPlayerMP playerMP = (EntityPlayerMP)player;

                if (!playerMP.playerNetServerHandler.connectionClosed)
                {
                    playerMP.setPositionAndUpdate((double)blockX, (double)((float)blockY + 1.0F), (double)blockZ);
                    playerMP.fallDistance = 0.0F;
                }
            }
        }
    }
}
