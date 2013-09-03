package com;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.PortalPosition;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import com.block.TeleportationBlocks;

public class TeleporterTp extends Teleporter
{

 private final Random random;
 /** Stores successful portal placement locations for rapid lookup. */
 private final LongHashMap destinationCoordinateCache = new LongHashMap();

 /**
  * A list of valid keys for the destinationCoordainteCache. These are based on the X & Z of the players initial
  * location.
  */
 private final List destinationCoordinateKeys = new ArrayList();
 private final WorldServer worldServerInstance;

  public TeleporterTp(WorldServer par1WorldServer)
 {
  super(par1WorldServer);
  this.worldServerInstance = par1WorldServer;
  this.random = new Random(par1WorldServer.getSeed());
 }

  @Override
 public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
 {
  if (this.worldServerInstance.provider.dimensionId != 1)
  {
   if (!this.placeInExistingPortal(par1Entity, par2, par4, par6, par8))
   {
    this.makePortal(par1Entity);
    this.placeInExistingPortal(par1Entity, par2, par4, par6, par8);
   }
  }
  else
  {
   int var9 = MathHelper.floor_double(par1Entity.posX);
   int var10 = MathHelper.floor_double(par1Entity.posY) - 1;
   int var11 = MathHelper.floor_double(par1Entity.posZ);
   byte var12 = 1;
   byte var13 = 0;

    for (int var14 = -2; var14 <= 2; ++var14)
   {
    for (int var15 = -2; var15 <= 2; ++var15)
    {
     for (int var16 = -1; var16 < 3; ++var16)
     {
      int var17 = var9 + var15 * var12 + var14 * var13;
      int var18 = var10 + var16;
      int var19 = var11 + var15 * var13 - var14 * var12;
      boolean var20 = var16 < 0;
      this.worldServerInstance.setBlock(var17, var18, var19, var20 ? TeleportationBlocks.TpBlock.blockID : 0);
     }
    }
   }

    par1Entity.setLocationAndAngles((double) var9, (double) var10, (double) var11, par1Entity.rotationYaw, 0.0F);
   par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
  }
 }

  @Override
 public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
 {
  short var9 = 128;
  double var10 = -1.0D;
  int var12 = 0;
  int var13 = 0;
  int var14 = 0;
  int var15 = MathHelper.floor_double(par1Entity.posX);
  int var16 = MathHelper.floor_double(par1Entity.posZ);
  long var17 = ChunkCoordIntPair.chunkXZ2Int(var15, var16);
  boolean var19 = true;
  double var27;
  int var48;

   if (this.destinationCoordinateCache.containsItem(var17))
  {
   PortalPosition var20 = (PortalPosition) this.destinationCoordinateCache.getValueByKey(var17);
   var10 = 0.0D;
   var12 = var20.posX;
   var13 = var20.posY;
   var14 = var20.posZ;
   var20.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
   var19 = false;
  }
  else
  {
   for (var48 = var15 - var9; var48 <= var15 + var9; ++var48)
   {
    double var21 = (double) var48 + 0.5D - par1Entity.posX;

     for (int var23 = var16 - var9; var23 <= var16 + var9; ++var23)
    {
     double var24 = (double) var23 + 0.5D - par1Entity.posZ;

      for (int var26 = this.worldServerInstance.getActualHeight() - 1; var26 >= 0; --var26)
     {
      if (this.worldServerInstance.getBlockId(var48, var26, var23) == TeleportationBlocks.portal.blockID)
      {
       while (this.worldServerInstance.getBlockId(var48, var26 - 1, var23) == TeleportationBlocks.portal.blockID)
       {
        --var26;
       }

        var27 = (double) var26 + 0.5D - par1Entity.posY;
       double var29 = var21 * var21 + var27 * var27 + var24 * var24;

        if (var10 < 0.0D || var29 < var10)
       {
        var10 = var29;
        var12 = var48;
        var13 = var26;
        var14 = var23;
       }
      }
     }
    }
   }
  }

   if (var10 >= 0.0D)
  {
   if (var19)
   {
    this.destinationCoordinateCache.add(var17, new PortalPosition(this, var12, var13, var14, this.worldServerInstance.getTotalWorldTime()));
    this.destinationCoordinateKeys.add(Long.valueOf(var17));
   }

    double var49 = (double) var12 + 0.5D;
   double var25 = (double) var13 + 0.5D;
   var27 = (double) var14 + 0.5D;
   int var50 = -1;

    if (this.worldServerInstance.getBlockId(var12 - 1, var13, var14) == TeleportationBlocks.portal.blockID)
   {
    var50 = 2;
   }

    if (this.worldServerInstance.getBlockId(var12 + 1, var13, var14) == TeleportationBlocks.portal.blockID)
   {
    var50 = 0;
   }

    if (this.worldServerInstance.getBlockId(var12, var13, var14 - 1) == TeleportationBlocks.portal.blockID)
   {
    var50 = 3;
   }

    if (this.worldServerInstance.getBlockId(var12, var13, var14 + 1) == TeleportationBlocks.portal.blockID)
   {
    var50 = 1;
   }

    int var30 = par1Entity.getTeleportDirection();

    if (var50 > -1)
   {
    int var31 = Direction.rotateLeft[var50];
    int var32 = Direction.offsetX[var50];
    int var33 = Direction.offsetZ[var50];
    int var34 = Direction.offsetX[var31];
    int var35 = Direction.offsetZ[var31];
    boolean var36 = !this.worldServerInstance.isAirBlock(var12 + var32 + var34, var13, var14 + var33 + var35) || !this.worldServerInstance.isAirBlock(var12 + var32 + var34, var13 + 1, var14 + var33 + var35);
    boolean var37 = !this.worldServerInstance.isAirBlock(var12 + var32, var13, var14 + var33) || !this.worldServerInstance.isAirBlock(var12 + var32, var13 + 1, var14 + var33);

     if (var36 && var37)
    {
     var50 = Direction.rotateOpposite[var50];
     var31 = Direction.rotateOpposite[var31];
     var32 = Direction.offsetX[var50];
     var33 = Direction.offsetZ[var50];
     var34 = Direction.offsetX[var31];
     var35 = Direction.offsetZ[var31];
     var48 = var12 - var34;
     var49 -= (double) var34;
     int var22 = var14 - var35;
     var27 -= (double) var35;
     var36 = !this.worldServerInstance.isAirBlock(var48 + var32 + var34, var13, var22 + var33 + var35) || !this.worldServerInstance.isAirBlock(var48 + var32 + var34, var13 + 1, var22 + var33 + var35);
     var37 = !this.worldServerInstance.isAirBlock(var48 + var32, var13, var22 + var33) || !this.worldServerInstance.isAirBlock(var48 + var32, var13 + 1, var22 + var33);
    }

     float var38 = 0.5F;
    float var39 = 0.5F;

     if (!var36 && var37)
    {
     var38 = 1.0F;
    }
    else if (var36 && !var37)
    {
     var38 = 0.0F;
    }
    else if (var36 && var37)
    {
     var39 = 0.0F;
    }

     var49 += (double) ((float) var34 * var38 + var39 * (float) var32);
    var27 += (double) ((float) var35 * var38 + var39 * (float) var33);
    float var40 = 0.0F;
    float var41 = 0.0F;
    float var42 = 0.0F;
    float var43 = 0.0F;

     if (var50 == var30)
    {
     var40 = 1.0F;
     var41 = 1.0F;
    }
    else if (var50 == Direction.rotateOpposite[var30])
    {
     var40 = -1.0F;
     var41 = -1.0F;
    }
    else if (var50 == Direction.rotateRight[var30])
    {
     var42 = 1.0F;
     var43 = -1.0F;
    }
    else
    {
     var42 = -1.0F;
     var43 = 1.0F;
    }

     double var44 = par1Entity.motionX;
    double var46 = par1Entity.motionZ;
    par1Entity.motionX = var44 * (double) var40 + var46 * (double) var43;
    par1Entity.motionZ = var44 * (double) var42 + var46 * (double) var41;
    par1Entity.rotationYaw = par8 - (float) (var30 * 90) + (float) (var50 * 90);
   }
   else
   {
    par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
   }

    par1Entity.setLocationAndAngles(var49 + 3, var25, var27 + 3, par1Entity.rotationYaw, par1Entity.rotationPitch);
   return true;
  }
  else
  {
   return false;
  }
 }

  public boolean makePortal(Entity par1Entity)
  {
      byte b0 = 16;
      double d0 = -1.0D;
      int i = MathHelper.floor_double(par1Entity.posX);
      int j = MathHelper.floor_double(par1Entity.posY);
      int k = MathHelper.floor_double(par1Entity.posZ);
      int l = i;
      int i1 = j;
      int j1 = k;
      int k1 = 0;
      int l1 = this.random.nextInt(4);
      int i2;
      double d1;
      double d2;
      int j2;
      int k2;
      int l2;
      int i3;
      int j3;
      int k3;
      int l3;
      int i4;
      int j4;
      int k4;
      double d3;
      double d4;

      for (i2 = i - b0; i2 <= i + b0; ++i2)
      {
          d1 = (double)i2 + 0.5D - par1Entity.posX;

          for (j2 = k - b0; j2 <= k + b0; ++j2)
          {
              d2 = (double)j2 + 0.5D - par1Entity.posZ;
              label274:

              for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2)
              {
                  if (this.worldServerInstance.isAirBlock(i2, k2, j2))
                  {
                      while (k2 > 0 && this.worldServerInstance.isAirBlock(i2, k2 - 1, j2))
                      {
                          --k2;
                      }

                      for (i3 = l1; i3 < l1 + 4; ++i3)
                      {
                          l2 = i3 % 2;
                          k3 = 1 - l2;

                          if (i3 % 4 >= 2)
                          {
                              l2 = -l2;
                              k3 = -k3;
                          }

                          for (j3 = 0; j3 < 3; ++j3)
                          {
                              for (i4 = 0; i4 < 4; ++i4)
                              {
                                  for (l3 = -1; l3 < 4; ++l3)
                                  {
                                      k4 = i2 + (i4 - 1) * l2 + j3 * k3;
                                      j4 = k2 + l3;
                                      int l4 = j2 + (i4 - 1) * k3 - j3 * l2;

                                      if (l3 < 0 && !this.worldServerInstance.getBlockMaterial(k4, j4, l4).isSolid() || l3 >= 0 && !this.worldServerInstance.isAirBlock(k4, j4, l4))
                                      {
                                          continue label274;
                                      }
                                  }
                              }
                          }

                          d4 = (double)k2 + 0.5D - par1Entity.posY;
                          d3 = d1 * d1 + d4 * d4 + d2 * d2;

                          if (d0 < 0.0D || d3 < d0)
                          {
                              d0 = d3;
                              l = i2;
                              i1 = k2;
                              j1 = j2;
                              k1 = i3 % 4;
                          }
                      }
                  }
              }
          }
      }

      if (d0 < 0.0D)
      {
          for (i2 = i - b0; i2 <= i + b0; ++i2)
          {
              d1 = (double)i2 + 0.5D - par1Entity.posX;

              for (j2 = k - b0; j2 <= k + b0; ++j2)
              {
                  d2 = (double)j2 + 0.5D - par1Entity.posZ;
                  label222:

                  for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2)
                  {
                      if (this.worldServerInstance.isAirBlock(i2, k2, j2))
                      {
                          while (k2 > 0 && this.worldServerInstance.isAirBlock(i2, k2 - 1, j2))
                          {
                              --k2;
                          }

                          for (i3 = l1; i3 < l1 + 2; ++i3)
                          {
                              l2 = i3 % 2;
                              k3 = 1 - l2;

                              for (j3 = 0; j3 < 4; ++j3)
                              {
                                  for (i4 = -1; i4 < 4; ++i4)
                                  {
                                      l3 = i2 + (j3 - 1) * l2;
                                      k4 = k2 + i4;
                                      j4 = j2 + (j3 - 1) * k3;

                                      if (i4 < 0 && !this.worldServerInstance.getBlockMaterial(l3, k4, j4).isSolid() || i4 >= 0 && !this.worldServerInstance.isAirBlock(l3, k4, j4))
                                      {
                                          continue label222;
                                      }
                                  }
                              }

                              d4 = (double)k2 + 0.5D - par1Entity.posY;
                              d3 = d1 * d1 + d4 * d4 + d2 * d2;

                              if (d0 < 0.0D || d3 < d0)
                              {
                                  d0 = d3;
                                  l = i2;
                                  i1 = k2;
                                  j1 = j2;
                                  k1 = i3 % 2;
                              }
                          }
                      }
                  }
              }
          }
      }

      int i5 = l;
      int j5 = i1;
      j2 = j1;
      int k5 = k1 % 2;
      int l5 = 1 - k5;

      if (k1 % 4 >= 2)
      {
          k5 = -k5;
          l5 = -l5;
      }

      boolean flag;

      if (d0 < 0.0D)
      {
          if (i1 < 70)
          {
              i1 = 70;
          }

          if (i1 > this.worldServerInstance.getActualHeight() - 10)
          {
              i1 = this.worldServerInstance.getActualHeight() - 10;
          }

          j5 = i1;

          for (k2 = -1; k2 <= 1; ++k2)
          {
              for (i3 = 1; i3 < 3; ++i3)
              {
                  for (l2 = -1; l2 < 3; ++l2)
                  {
                      k3 = i5 + (i3 - 1) * k5 + k2 * l5;
                      j3 = j5 + l2;
                      i4 = j2 + (i3 - 1) * l5 - k2 * k5;
                      flag = l2 < 0;
                      this.worldServerInstance.setBlock(k3, j3, i4, flag ? TeleportationBlocks.TpBlock.blockID : 0);
                  }
              }
          }
      }

      for (k2 = 0; k2 < 4; ++k2)
      {
          for (i3 = 0; i3 < 4; ++i3)
          {
              for (l2 = -1; l2 < 4; ++l2)
              {
                  k3 = i5 + (i3 - 1) * k5;
                  j3 = j5 + l2;
                  i4 = j2 + (i3 - 1) * l5;
                  flag = i3 == 0 || i3 == 3 || l2 == -1 || l2 == 3;
                  this.worldServerInstance.setBlock(k3, j3, i4, flag ? TeleportationBlocks.TpBlock.blockID : TeleportationBlocks.portal.blockID, 0, 2);
              }
          }

          for (i3 = 0; i3 < 4; ++i3)
          {
              for (l2 = -1; l2 < 4; ++l2)
              {
                  k3 = i5 + (i3 - 1) * k5;
                  j3 = j5 + l2;
                  i4 = j2 + (i3 - 1) * l5;
                  this.worldServerInstance.notifyBlocksOfNeighborChange(k3, j3, i4, this.worldServerInstance.getBlockId(k3, j3, i4));
              }
          }
      }

      return true;
  }

  /**
   * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
   * WorldServer.getTotalWorldTime() value.
   */
  public void removeStalePortalLocations(long par1)
  {
      if (par1 % 100L == 0L)
      {
          Iterator iterator = this.destinationCoordinateKeys.iterator();
          long j = par1 - 600L;

          while (iterator.hasNext())
          {
              Long olong = (Long)iterator.next();
              PortalPosition portalposition = (PortalPosition)this.destinationCoordinateCache.getValueByKey(olong.longValue());

              if (portalposition == null || portalposition.lastUpdateTime < j)
              {
                  iterator.remove();
                  this.destinationCoordinateCache.remove(olong.longValue());
              }
          }
      }
  }
}
