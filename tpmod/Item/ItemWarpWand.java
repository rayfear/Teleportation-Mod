package tpmod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import tpmod.TeleportationMod;
import tpmod.location.SavedLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWarpWand extends Item
{
    public ItemWarpWand()
    {
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(100);
    }

    private SavedLocation location = null;

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par1ItemStack.damageItem(1, par3EntityPlayer);

        if (!par3EntityPlayer.isSneaking())
        {
            if (hasSavedPosition(par1ItemStack))
            {
                readPosition(par1ItemStack, par3EntityPlayer);
                par3EntityPlayer.fallDistance = 0F;
            }
            else
            {
                savePosition(par1ItemStack, par3EntityPlayer);
            }
        }
        else
        {
            if (!hasSavedPosition(par1ItemStack))
            {
                savePosition(par1ItemStack, par3EntityPlayer);
            }
            else
            {
                par1ItemStack.stackTagCompound.removeTag("position");
                savePosition(par1ItemStack, par3EntityPlayer);
            }
        }

        return par1ItemStack;
    }

    private boolean hasSavedPosition(ItemStack par1ItemStack)
    {
        return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("position");
    }

    private void savePosition(ItemStack par1ItemStack, EntityPlayer par3EntityPlayer)
    {
        if (par1ItemStack.stackTagCompound == null)
        {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }

        if (!par1ItemStack.stackTagCompound.hasKey("position"))
        {
            par1ItemStack.stackTagCompound.setTag("position", new NBTTagList());
        }

        NBTTagList positionTagList = (NBTTagList)par1ItemStack.stackTagCompound.getTag("position");
        NBTTagCompound positionTag = new NBTTagCompound();
        positionTag.setDouble("x", par3EntityPlayer.posX);
        positionTag.setDouble("y", par3EntityPlayer.posY);
        positionTag.setDouble("z", par3EntityPlayer.posZ);
        positionTagList.appendTag(positionTag);
    }

    private void readPosition(ItemStack par1ItemStack, EntityPlayer par3EntityPlayer)
    {
        NBTTagList positionTagList = (NBTTagList)par1ItemStack.stackTagCompound.getTag("position");
        NBTTagCompound positionTag = (NBTTagCompound) positionTagList.getCompoundTagAt(0);
        double x = positionTag.getDouble("x");
        double y = positionTag.getDouble("y");
        double z = positionTag.getDouble("z");
        par3EntityPlayer.setPosition(x, y, z);
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {
        if (location != null)
        {
            if (stack.stackTagCompound == null)
            {
                stack.setTagCompound(new NBTTagCompound());
            }

            if (!stack.stackTagCompound.hasKey("ench"))
            {
                stack.stackTagCompound.setTag("gegy", new NBTTagList());
            }

            NBTTagList gegy1000Tags = (NBTTagList)stack.stackTagCompound.getTag("gegy");
            NBTTagCompound position = new NBTTagCompound();
            position.setDouble("x", location._x);
            position.setDouble("y", location._y);
            position.setDouble("z", location._z);
            gegy1000Tags.appendTag(position);
        }
    }
    
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
    
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return par1ItemStack.getItemDamage() == 0 ? EnumRarity.rare : EnumRarity.epic;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconReg)
    {
        this.itemIcon = iconReg.registerIcon(TeleportationMod.MODID + ":" + "Warp Wand");
    }
}
