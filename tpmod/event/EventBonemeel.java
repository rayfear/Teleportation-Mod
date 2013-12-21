package tpmod.event;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import tpmod.block.BlockTpSapling;
import tpmod.block.TeleportationBlocks;

public class EventBonemeel
{
    @ForgeSubscribe
    public void onEvent(BonemealEvent e)
    {
        if (e.ID == TeleportationBlocks.tpSapling.blockID)
        {
            ((BlockTpSapling)TeleportationBlocks.tpSapling).markOrGrowMarked(e.world, e.X, e.Y, e.Z, e.world.rand);
        }
    }
}
