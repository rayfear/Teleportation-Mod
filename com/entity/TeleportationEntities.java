package com.entity;

import com.TeleporterMod;
import com.model.ModelTeleportationMob;
import com.world.TeleportationBiomes;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;

public class TeleportationEntities 
{
	static int startEntityId = 345;
	
	public static void registerEntities()
	{
		EntityRegistry.registerModEntity(EntityTeleportationMob.class, "TpMob", 1, TeleporterMod.instance, 80, 1, true);
		EntityRegistry.addSpawn(EntityTeleportationMob.class, 20, 2, 4, EnumCreatureType.monster,  TeleportationBiomes.teleportbiome);
		LanguageRegistry.instance().addStringLocalization("TpMob", "TpMob");
		registerEntityEgg(EntityTeleportationMob.class, 0x505B09, 0x161616);
		LanguageRegistry.instance().addStringLocalization("entity.Teleport.TpMob.name", "Tp Mob");
		RenderingRegistry.instance().registerEntityRenderingHandler(EntityTeleportationMob.class, new RenderTpMob(new ModelTeleportationMob(), 0.3F));
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

	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) 
	{
		int id = getUniqueEntityId();


		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	}
}
