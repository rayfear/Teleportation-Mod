package tpmod.doorlocator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import tpmod.block.tileentity.TileEntityTeleportationDoor;
import tpmod.location.Location;

public class TeleportationDoorLocator 
{
	public static Map<Location, Integer> doors = new HashMap<Location, Integer>();

	public static void teleportEntityToDoor(EntityLivingBase entity, TileEntityTeleportationDoor tileEntity)
	{
		World world = entity.worldObj;

		int frequency = tileEntity.frequency;
		
		if(!world.isRemote)
		{
			double x = entity.posX;
			double y = entity.posY;
			double z = entity.posZ;

			float yaw = entity.rotationYaw;
			float pitch = entity.rotationPitch;

			for (Entry<Location, Integer> door : doors.entrySet())
			{
				if(door.getValue().intValue() == frequency)
				{
					if(!isSameLocation(new Location(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, world.provider.dimensionId), door.getKey()))
					{
						x = door.getKey().x;
						y = door.getKey().y;
						z = door.getKey().z;
					}
				}
			}

			entity.setLocationAndAngles(x, y, z, yaw, pitch);
		}
	}

	public static boolean isSameLocation(Location location1, Location location2)
	{
		int x1 = location1.x;
		int y1 = location1.y;
		int z1 = location1.z;

		int dimension1 = location1.dimension;

		int x2 = location2.x;
		int y2 = location2.y;
		int z2 = location2.z;

		int dimension2 = location1.dimension;

		return x1 == x2 && y1 == y2 && z1 == z2 && dimension1 == dimension2;
	}

	public static void setFrequency(int x, int y, int z, int dimension, int freq)
	{
		Location location = new Location(x, y, z, dimension);

		boolean hasSetFreq = false;

		for (Entry<Location, Integer> door : doors.entrySet())
		{
			int x2 = door.getKey().x;
			int y2 = door.getKey().y;
			int z2 = door.getKey().z;

			int dimension2 = door.getKey().dimension;

			if(x2 == location.x && y2 == location.y && z2 == location.z && dimension2 == location.dimension)	
			{
				door.setValue(freq);
				hasSetFreq = true;
			}
		}

		if(!hasSetFreq)
		{
			doors.put(location, freq);
		}
	}

	public static boolean hasDoor(int x, int y, int z, int dimension)
	{
		return hasDoor(new Location(x, y, z, dimension));
	}

	public static boolean hasDoor(Location location)
	{
		for (Entry<Location, Integer> door : doors.entrySet())
		{
			int x = door.getKey().x;
			int y = door.getKey().y;
			int z = door.getKey().z;

			int dimension = door.getKey().dimension;

			if(x == location.x && y == location.y && z == location.z && dimension == location.dimension)	
			{
				return true;
			}
		}

		return false;
	}
}
