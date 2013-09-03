package com.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.Item.TeleportationItems;
import com.block.TeleportationBlocks;

import cpw.mods.fml.common.registry.GameRegistry;

public class TeleportationCrafting 
{
	public static void registerCrafting()
	{
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.ObsidianSticks, 4),
				"x",
				"x", 
				
				'x', Block.obsidian);
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.TeleporterRemember, 1), 
				"y",
				"x",
				"x", 
				
				'x', TeleportationItems.ObsidianSticks, 'y', TeleportationItems.TeleportationBall);

		GameRegistry.addRecipe(new ItemStack(TeleportationItems.Teleporter, 1), 
				"y",
				"x",
				"x", 
				
				'x', TeleportationItems.ObsidianSticks, 'y', TeleportationItems.MiniPortal);

		GameRegistry.addRecipe(new ItemStack(TeleportationBlocks.TpBlock, 1),
				"tt",
				"tt", 
				
				't', TeleportationItems.TeleportationShards);

		GameRegistry.addRecipe(new ItemStack(TeleportationItems.FlintAndDiamond, 1), 
				"d ",
				" f", 
				
				'f', Item.flint,'d', Item.diamond);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.Teleporter, 1),
				"t",
				
				't', TeleportationItems.Teleporter);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.TeleporterRemember, 1), 
				"t",

				't', TeleportationItems.TeleporterRemember);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.TeleportationBall, 1),
				"ggg",
				"gtg",
				"ggg", 
				
				't', TeleportationItems.TeleportationShards, 'g',Block.glass);

		GameRegistry.addRecipe(new ItemStack(TeleportationItems.MiniPortal, 1), 
				"ggg",
				"gtg",
				"ggg", 
				
				't', Item.flintAndSteel, 'g',Block.obsidian);

		GameRegistry.addRecipe(new ItemStack(TeleportationItems.LookingWand, 1),
				"e",
				"s",
				"s",
				
				'e', TeleportationItems.LookingEye, 's', TeleportationItems.ObsidianSticks);


	}
}
