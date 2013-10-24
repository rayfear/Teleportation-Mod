package com.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class CommandJump extends CommandBase
{
    public String getCommandName()
    {
        return "jump";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 3;
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return "jump";
    }

    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        EntityPlayerMP player = getCommandSenderAsPlayer(par1ICommandSender);
        if (!player.worldObj.isRemote)
        {
            Vec3 var5 = player.getPosition(1.0F);
            ++var5.yCoord;
            Vec3 var6 = player.getLook(1.0F);
            Vec3 var7 = var5.addVector(var6.xCoord * 1000.0D, var6.yCoord * 1000.0D, var6.zCoord * 1000.0D);
            MovingObjectPosition var4 = player.worldObj.rayTraceBlocks_do_do(var5, var7, true, true);

            if (var4 != null && var4.typeOfHit == EnumMovingObjectType.TILE)
            {
                int var8 = var4.blockX;
                int var9 = var4.blockY;
                int var10 = var4.blockZ;

                if (!player.playerNetServerHandler.connectionClosed)
                {
                	player.setPositionAndUpdate((double)var8, (double)((float)var9 + 1.0F), (double)var10);
                	player.fallDistance = 0.0F;
               	 par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("Succsessfully jumped"));
                }
            }
            else
            {
            	 par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("No object in line of sight"));
            }
        }
       
    }
}
