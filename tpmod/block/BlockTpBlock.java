package tpmod.block;

import java.util.Random;

import net.minecraft.block.BlockStone;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockTpBlock extends BlockStone
{
    public BlockTpBlock(int par1, int par2)
    {
        super(par1);
    }
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return 2408;
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon("tpmod:Tp_Block");
    }
}
