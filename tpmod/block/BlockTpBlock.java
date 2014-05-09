package tpmod.block;

import tpmod.TeleportationMod;
import net.minecraft.block.BlockStone;

public class BlockTpBlock extends BlockStone
{
    public BlockTpBlock()
    {
        super();
        this.setHarvestLevel("pickaxe", 2);
        this.setBlockName("TeleportationBlock");
        this.setHardness(5F);
        this.setBlockTextureName(TeleportationBlocks.modID("teleportation_block"));
        this.setCreativeTab(TeleportationMod.teleportationTab);
    }
    
}
