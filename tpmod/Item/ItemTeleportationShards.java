package tpmod.Item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTeleportationShards extends Item
{
    public ItemTeleportationShards(int id)
    {
        super(id);
        this.setUnlocalizedName("Teleport_Shards");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("tpmod:Teleport_Shards");
    }
}
