package tpmod.item;

import tpmod.TeleportationMod;
import tpmod.block.TeleportationBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFlintAndDiamond extends Item
{
    public ItemFlintAndDiamond()
    {
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(64);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
        if (par7 == 0)
        {
            --y;
        }

        if (par7 == 1)
        {
            ++y;
        }

        if (par7 == 2)
        {
            --z;
        }

        if (par7 == 3)
        {
            ++z;
        }

        if (par7 == 4)
        {
            --x;
        }

        if (par7 == 5)
        {
            ++x;
        }

        if (!player.canPlayerEdit(x, y, z, par7, stack))
        {
            return false;
        }
        else
        {
            Block block = world.getBlock(x, y, z);

            if (block == Blocks.air)
            {
                world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                world.setBlock(x, y, z, TeleportationBlocks.teleportationFire);
            }

            stack.damageItem(1, player);
            return true;
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconReg)
    {
        this.itemIcon = iconReg.registerIcon(TeleportationMod.MODID + ":" + "Flint And Diamond");
    }
}
