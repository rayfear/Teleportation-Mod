package tpmod.Item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tpmod.TeleporterMod;

public class ItemTeleportationBall extends Item
{
    public ItemTeleportationBall(int id)
    {
        super(id);
        this.setUnlocalizedName("Teleport_Ball");
        this.setCreativeTab(TeleporterMod.TpTab);
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("tpmod:Teleport_Ball");
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
