package tpmod.command;

import tpmod.helper.TeleportationHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

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

    public String getCommandUsage(ICommandSender sender)
    {
        return "jump";
    }

    public void processCommand(ICommandSender sender, String[] args)
    {
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        TeleportationHelper tpHelper = new TeleportationHelper();
        tpHelper.jump(player.worldObj, player);
    }

	@Override
	public int compareTo(Object arg0)
	{
		return 0;
	}
}
