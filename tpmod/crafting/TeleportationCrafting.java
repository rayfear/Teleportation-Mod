package tpmod.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tpmod.block.TeleportationBlocks;
import tpmod.item.TeleportationItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class TeleportationCrafting
{
	public static void registerCrafting()
	{  	
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.obsidianSticks, 4),
				"x",
				"x",
				'x', Blocks.obsidian);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.warpWand, 1),
				"y",
				"x",
				"x",
				'x', TeleportationItems.obsidianSticks, 'y', TeleportationItems.teleportationOrb);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.portalTeleportationWand, 1),
				"y",
				"x",
				"x",
				'x', TeleportationItems.obsidianSticks, 'y', TeleportationItems.portalWandTop);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationBlocks.teleportationBlock, 1),
				"tt",
				"tt",
				't', TeleportationItems.teleportationShards);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.flintAndDiamond, 1),
				"d ",
				" f",
				'f', Items.flint, 'd', Items.diamond);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.teleportationOrb, 1),
				"ggg",
				"gtg",
				"ggg",
				't', TeleportationBlocks.teleportationBlock, 'g', Blocks.glass);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.portalWandTop, 1),
				"ggg",
				"gtg",
				"ggg",
				't', Items.flint_and_steel, 'g', Blocks.obsidian);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.jumpWand, 1),
				"e",
				"s",
				"s",
				'e', TeleportationItems.watchingEye, 's', TeleportationItems.obsidianSticks);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.tpBow, 1),
				" os",
				"oes",
				" os",
				'o', TeleportationItems.obsidianSticks, 's', Items.string, 'e', Items.ender_pearl);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.tpArrow, 4),
				"e",
				"o",
				"f",
				'o', TeleportationItems.obsidianSticks, 'f', Items.feather, 'e', Items.ender_pearl);
		
		GameRegistry.addRecipe(new ItemStack(TeleportationItems.tpSword, 1),
				"ete",
				"ete",
				" o ",
				'o', TeleportationItems.obsidianSticks, 'e', Items.ender_pearl, 't', TeleportationBlocks.teleportationBlock);

		GameRegistry.addSmelting(TeleportationItems.rawEnderMeat, new ItemStack(TeleportationItems.cookedEnderMeat), 1F);
	}


}
