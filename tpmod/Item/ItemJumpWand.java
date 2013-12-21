package tpmod.Item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tpmod.TeleporterMod;
import tpmod.helper.TeleportationHelper;

public class ItemJumpWand extends Item
{
    public ItemJumpWand(int id)
    {
        super(id);
        this.setUnlocalizedName("Looking_Vec_Wand");
        this.setCreativeTab(TeleporterMod.TpTab);
        this.maxStackSize = 1;
        this.setMaxDamage(100);
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("tpmod:Looking_Vec_Wand");
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        TeleportationHelper tpHelper = new TeleportationHelper();
        tpHelper.jump(world, player);
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
}
