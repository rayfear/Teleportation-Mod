package com;

import net.minecraft.util.ChunkCoordinates;

public class PortalPositionClient extends ChunkCoordinates
{
    public long field_85087_d;

    final TeleporterClient field_85088_e;

    public PortalPositionClient(TeleporterClient par1Teleporter, int par2, int par3, int par4, long par5)
    {
        super(par2, par3, par4);
        this.field_85088_e = par1Teleporter;
        this.field_85087_d = par5;
    }
}
