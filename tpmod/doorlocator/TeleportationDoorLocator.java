package tpmod.doorlocator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import tpmod.location.Location;

public class TeleportationDoorLocator 
{
	public static Map<Location, Integer> doors = new HashMap<Location, Integer>();

	public static int getFrequency(World world, int x, int y, int z, int dimension)
	{
		return getFrequency(world, new Location(x, y, z, dimension));
	}
	
	public static int getFrequency(World world, Location location)
	{
		if(!world.isRemote)
		{
			if(location != null && doors.size() > 0 && doors.containsKey(location))
			{
				return doors.get(location);
			}
		}
		
		return 0;
	}
	
	public static void teleportEntityToDoor(EntityLivingBase entity, Location location)
	{
		World world = entity.worldObj;

		int frequency = getFrequency(world, location);
		
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
					if(!location.equals(door.getKey()))
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

	public static void setFrequency(int x, int y, int z, int dimension, int freq)
	{
		Location location = new Location(x, y, z, dimension);

		boolean hasSetFreq = false;

		for (Entry<Location, Integer> door : doors.entrySet())
		{
			if(location.equals(door.getKey()))	
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
