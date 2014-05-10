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
import tpmod.helper.TeleportationHelper;

public class ItemJumpWand extends Item
{
    public ItemJumpWand()
    {
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(100);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        TeleportationHelper tpHelper = new TeleportationHelper();
        tpHelper.jump(world, player);
        itemStack.damageItem(1, player);
        return itemStack;
    }

    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
    
    public EnumRarity getRarity(ItemStack stack)
    {
        return stack.getItemDamage() == 0 ? EnumRarity.rare : EnumRarity.epic;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconReg)
    {
        this.itemIcon = iconReg.registerIcon(TeleportationMod.MODID + ":" + "Jump Wand");
    }
}
