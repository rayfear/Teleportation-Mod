package tpmod.block;

import tpmod.TeleportationMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTeleportingDirt extends Block
{
    public BlockTeleportingDirt()
    {
        super(Material.ground);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setStepSound(soundTypeGravel);
        this.setHarvestLevel("shovel", 1);
        this.setBlockName("TeleportationDirt");
        this.setHardness(1F);
        this.setBlockTextureName(TeleportationBlocks.modID("Teleportation Dirt"));
        this.setCreativeTab(TeleportationMod.teleportationTab);
    }
}
