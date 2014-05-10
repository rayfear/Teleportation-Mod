package tpmod.item;

import tpmod.TeleportationMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemObsidianStick extends Item
{
    public ItemObsidianStick()
    {
        super();
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconReg)
    {
        this.itemIcon = iconReg.registerIcon(TeleportationMod.MODID + ":" + "Obsidian Sticks");
    }
}
