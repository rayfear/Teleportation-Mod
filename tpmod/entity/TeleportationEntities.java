package tpmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import tpmod.TeleportationMod;
import cpw.mods.fml.common.registry.EntityRegistry;

public class TeleportationEntities
{
	static int startEntityId = 345;

	public static void registerEntities()
	{
		EntityRegistry.registerModEntity(EntityWatcher.class, "Watcher", 1, TeleportationMod.INSTANCE, 80, 1, true);
		EntityRegistry.registerModEntity(EntityObserver.class, "Observer", 2, TeleportationMod.INSTANCE, 80, 1, true);
		registerEntityEgg(EntityWatcher.class, 0x505B09, 0x161616);
		registerEntityEgg(EntityObserver.class, 0x1D1D1D, 0x1820F2);
	}

	public static int getUniqueEntityId()
	{
		do
		{
			startEntityId++;
		}
		while (EntityList.getStringFromID(startEntityId) != null);

		return startEntityId;
	}

	@SuppressWarnings("unchecked")
	public static void registerEntityEgg(Class <? extends Entity > entity, int primaryColor, int secondaryColor)
	{
		int id = getUniqueEntityId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	}
}
