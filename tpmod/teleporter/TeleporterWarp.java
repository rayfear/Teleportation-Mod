package tpmod.teleporter;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterWarp extends Teleporter
{
	public TeleporterWarp(WorldServer par1WorldServer)
	{
		super(par1WorldServer);
	}

	@Override
	public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	{

	}

	@Override
	public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	{
		return true;
	}

	public boolean makePortal(Entity entity)
	{
		return true;
	}

	/**
	 * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
	 * WorldServer.getTotalWorldTime() value.
	 */
	public void removeStalePortalLocations(long par1)
	{

	}
}
