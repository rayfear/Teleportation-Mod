package tpmod.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import tpmod.item.TeleportationItems;

public class TabTeleportation extends CreativeTabs
{
    public TabTeleportation(String label)
    {
        super(label);
    }
    
	@Override
	public Item getTabIconItem()
	{
		 return TeleportationItems.watchingEye;
	}
}
