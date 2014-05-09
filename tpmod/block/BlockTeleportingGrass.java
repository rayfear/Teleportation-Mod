package tpmod.block;

import java.util.Random;

import tpmod.TeleportationMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTeleportingGrass extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon top;

    protected BlockTeleportingGrass()
    {
        super(Material.grass);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeGrass);
        this.setHarvestLevel("shovel", 1);
        this.setBlockName("TeleportationGrass");
        this.setHardness(1F);
        this.setBlockTextureName("Teleportation Grass");
        this.setCreativeTab(TeleportationMod.teleportationTab);
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.top : (side == 0 ? (TeleportationBlocks.teleportationDirt).getBlockTextureFromSide(side) : this.blockIcon);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
            if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
            {
                world.setBlock(x, y, z, TeleportationBlocks.teleportationDirt);
            }
            else if (world.getBlockLightValue(x, y + 1, z) >= 9)
            {
                for (int l = 0; l < 4; ++l)
                {
                    int i1 = x + rand.nextInt(3) - 1;
                    int j1 = y + rand.nextInt(5) - 3;
                    int k1 = z + rand.nextInt(3) - 1;

                    if (world.getBlock(i1, j1, k1) == TeleportationBlocks.teleportationDirt && world.getBlockMetadata(i1, j1, k1) == 0 && world.getBlockLightValue(i1, j1 + 1, k1) >= 4 && world.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
                    {
                        world.setBlock(i1, j1, k1, this);
                    }
                }
            }
        }
    }

    public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_)
    {
        return (TeleportationBlocks.teleportationDirt).getItemDropped(0, rand, p_149650_3_);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        if (side == 1)
        {
            return this.top;
        }
        else if (side == 0)
        {
            return (TeleportationBlocks.teleportationDirt).getBlockTextureFromSide(side);
        }
        else
        {
            return this.blockIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconReg)
    {
        this.blockIcon = iconReg.registerIcon("tpmod:" + this.getTextureName() + "_side");
        this.top = iconReg.registerIcon("tpmod:" + this.getTextureName() + "_top");
    }


}