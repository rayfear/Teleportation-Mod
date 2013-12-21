package tpmod.Item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import tpmod.positions.SavedLocation;

public class ItemWarpWand extends Item
{
    public ItemWarpWand(int index)
    {
        super(index);
        this.setUnlocalizedName("Teleportation_Wand");
        this._index = index;
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.maxStackSize = 1;
        this.setMaxDamage(101);
        this.isFull3D();
    }

    private int _index;
    private Boolean _initialised;
    private SavedLocation location = null;

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("tpmod:Teleportation_Wand");
    }

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

    private void savePosition(ItemStack par1ItemStack,
                              EntityPlayer par3EntityPlayer)
    {
        if (par1ItemStack.stackTagCompound == null)
        {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }

        if (!par1ItemStack.stackTagCompound.hasKey("position"))
        {
            par1ItemStack.stackTagCompound.setTag("position", new NBTTagList("position"));
        }

        NBTTagList positionTagList = (NBTTagList)par1ItemStack.stackTagCompound.getTag("position");
        NBTTagCompound positionTag = new NBTTagCompound();
        positionTag.setDouble("x", par3EntityPlayer.posX);
        positionTag.setDouble("y", par3EntityPlayer.posY);
        positionTag.setDouble("z", par3EntityPlayer.posZ);
        positionTagList.appendTag(positionTag);
    }

    private void readPosition(ItemStack par1ItemStack,
                              EntityPlayer par3EntityPlayer)
    {
        NBTTagList positionTagList = (NBTTagList)par1ItemStack.stackTagCompound.getTag("position");
        NBTTagCompound positionTag = (NBTTagCompound) positionTagList.tagAt(0);
        double x = positionTag.getDouble("x");
        double y = positionTag.getDouble("y");
        double z = positionTag.getDouble("z");
        par3EntityPlayer.setPosition(x, y, z);
    }

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (location != null)
        {
            if (par1ItemStack.stackTagCompound == null)
            {
                par1ItemStack.setTagCompound(new NBTTagCompound());
            }

            if (!par1ItemStack.stackTagCompound.hasKey("ench"))
            {
                par1ItemStack.stackTagCompound.setTag("gegy", new NBTTagList("gegy"));
            }

            NBTTagList var3 = (NBTTagList)par1ItemStack.stackTagCompound.getTag("gegy");
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setDouble("x", location._x);
            var4.setDouble("y", location._y);
            var4.setDouble("z", location._z);
            var3.appendTag(var4);
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
}
