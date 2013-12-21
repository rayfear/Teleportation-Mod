package tpmod.Item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemObsidianStick extends Item
{
    public ItemObsidianStick(int id)
    {
        super(id);
        this.setUnlocalizedName("Obsidian_Sticks");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("tpmod:Obsidian_Sticks");
    }
}
