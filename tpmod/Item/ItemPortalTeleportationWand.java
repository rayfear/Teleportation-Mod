package tpmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tpmod.TeleportationMod;
import tpmod.teleporter.TeleporterClient;

public class ItemPortalTeleportationWand extends Item
{
    public ItemPortalTeleportationWand()
    {
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(100);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par2EntityPlayer)
    {
        TeleporterClient teleporter = new TeleporterClient(par2World);
        teleporter.placeInPortal(par2EntityPlayer, 0, 0, 0, 0);
        par1ItemStack.damageItem(1, par2EntityPlayer);
        return par1ItemStack;
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
        this.itemIcon = iconReg.registerIcon(TeleportationMod.MODID + ":" + "Portal Wand");
    }
}
