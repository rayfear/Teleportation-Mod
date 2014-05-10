package tpmod.event;

import net.minecraftforge.event.entity.player.BonemealEvent;
import tpmod.block.BlockTpSapling;
import tpmod.block.TeleportationBlocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventBonemeel
{
    @SubscribeEvent
    public void onEvent(BonemealEvent e)
    {
        if (e.block == TeleportationBlocks.teleportationSapling)
        {
            ((BlockTpSapling)TeleportationBlocks.teleportationSapling).markOrGrowMarked(e.world, e.x, e.y, e.z, e.world.rand);
        }
    }
}
