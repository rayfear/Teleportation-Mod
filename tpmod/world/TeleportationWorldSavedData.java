package tpmod.world;

import java.util.Map.Entry;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import tpmod.doorlocator.TeleportationDoorLocator;
import tpmod.location.Location;

public class TeleportationWorldSavedData extends WorldSavedData
{
	public static String key;

	public TeleportationWorldSavedData(String key) 
	{
		super(key);
		TeleportationWorldSavedData.key = key;
	}

	public static TeleportationWorldSavedData getData(World world)
	{
		MapStorage storage = world.perWorldStorage;
		TeleportationWorldSavedData result = (TeleportationWorldSavedData)storage.loadData(TeleportationWorldSavedData.class, key);
		if (result == null)
		{
			result = new TeleportationWorldSavedData(key);
			result.markDirty();
			storage.setData(key, result);
		}
		return result;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) 
	{
		int length = nbt.getInteger("Amount");
		
		for (int i = 0; i < length; i++) 
		{
			int x = nbt.getInteger("X " + i);
			int y = nbt.getInteger("Y " + i);
			int z = nbt.getInteger("Z " + i);
			
			int dimension = nbt.getInteger("Dimension " + i);;
			
			int frequency = nbt.getInteger("Frequency " + i);;
			
			Location doorloc = new Location(x, y, z, dimension);
			
			TeleportationDoorLocator.doors.put(doorloc, frequency);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		int i = 0;
		
		for (Entry<Location, Integer> currentDoor :  TeleportationDoorLocator.doors.entrySet()) 
		{
			int frequency = currentDoor.getValue();
			Location location = currentDoor.getKey();

			nbt.setInteger("X " + i, location.x);
			nbt.setInteger("Y " + i, location.y);
			nbt.setInteger("Z " + i, location.z);
			
			nbt.setInteger("Dimension " + i, location.dimension);
			
			nbt.setInteger("Frequency " + i, frequency);
			
			i++;
		}
		
		nbt.setInteger("Length", i);
	}

}
