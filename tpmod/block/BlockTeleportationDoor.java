package tpmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tpmod.doorlocator.TeleportationDoorLocator;
import tpmod.gui.GuiSetTeleportationDoorLocation;
import tpmod.item.TeleportationItems;
import tpmod.location.Location;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTeleportationDoor extends Block
{
	@SideOnly(Side.CLIENT)
	private IIcon[] texturesUpper;
	@SideOnly(Side.CLIENT)
	private IIcon[] texturesLower;

	public BlockTeleportationDoor()
	{
		super(Material.iron);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		this.setHarvestLevel("pickaxe", 2);
		this.setBlockName("TeleportationDoor");
		this.setBlockTextureName(TeleportationBlocks.modID("TeleportationDoor"));
		this.setHardness(2F);
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.texturesLower[0];
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int metadata)
	{
		if (metadata != 1 && metadata != 0)
		{
			int fullMetadata = this.getFullMeta(world, x, y, z);
			int orientation = getOrientation(world, x, y, z);
			boolean isDoorOpen = isDoorOpen(fullMetadata);
			boolean reversed = false;
			boolean isUpperDoorBlock = (fullMetadata & 8) != 0;

			if (isDoorOpen)
			{
				if (orientation == 0 && metadata == 2)
				{
					reversed = !reversed;
				}
				else if (orientation == 1 && metadata == 5)
				{
					reversed = !reversed;
				}
				else if (orientation == 2 && metadata == 3)
				{
					reversed = !reversed;
				}
				else if (orientation == 3 && metadata == 4)
				{
					reversed = !reversed;
				}
			}
			else
			{
				if (orientation == 0 && metadata == 5)
				{
					reversed = !reversed;
				}
				else if (orientation == 1 && metadata == 3)
				{
					reversed = !reversed;
				}
				else if (orientation == 2 && metadata == 4)
				{
					reversed = !reversed;
				}
				else if (orientation == 3 && metadata == 2)
				{
					reversed = !reversed;
				}

				if ((fullMetadata & 16) != 0)
				{
					reversed = !reversed;
				}
			}

			return isUpperDoorBlock ? this.texturesUpper[reversed?1:0] : this.texturesLower[reversed?1:0];
		}
		else
		{
			return this.texturesLower[0];
		}
	}

	private boolean isDoorOpen(int fullMetadata) 
	{
		return (fullMetadata & 4) != 0;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.texturesUpper = new IIcon[2];
		this.texturesLower = new IIcon[2];
		this.texturesUpper[0] = iconRegister.registerIcon(this.getTextureName() + "_upper");
		this.texturesLower[0] = iconRegister.registerIcon(this.getTextureName() + "_lower");
		this.texturesUpper[1] = new IconFlipped(this.texturesUpper[0], true, false);
		this.texturesLower[1] = new IconFlipped(this.texturesLower[0], true, false);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}


	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) 
	{
		if (world.isRemote)
		{
			return;
		}

		// Check that this is the top block of the door
		if (world.getBlock(x, y - 1, z) instanceof BlockTeleportationDoor)
		{
			int metadata = world.getBlockMetadata(x, y - 1, z);
			boolean canUse = isDoorOpen(metadata);

			if (canUse && entity instanceof EntityPlayer)
			{
				canUse = isEntityFacingDoor(metadata, (EntityLivingBase) entity);
			}

			if (canUse && entity instanceof EntityLivingBase)
			{
				TeleportationDoorLocator.teleportEntityToDoor((EntityLivingBase)entity, new Location(x, y, z, world.provider.dimensionId));
			}
		}
		else if (world.getBlock(x, y + 1, z) instanceof BlockTeleportationDoor)
		{
			onEntityCollidedWithBlock(world, x, y + 1, z, entity);
		}
	}

	protected static boolean isEntityFacingDoor(int metadata, EntityLivingBase entity)
	{
		int direction = MathHelper.floor_double((entity.rotationYaw + 90) * 4.0F / 360.0F + 0.5D) & 3;
		return ((metadata & 3) == direction);
	}


	public boolean getBlocksMovement(IBlockAccess world, int x, int y, int z)
	{
		int fullMetadata = this.getFullMeta(world, x, y, z);

		return isDoorOpen(fullMetadata);
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return 7;
	}

	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		this.setDoorRotation(this.getFullMeta(world, x, y, z));
	}

	public int getOrientation(IBlockAccess world, int x, int y, int z)
	{
		return this.getFullMeta(world, x, y, z) & 3;
	}

	public boolean func_150015_f(IBlockAccess world, int x, int y, int z)
	{
		return (this.getFullMeta(world, x, y, z) & 4) != 0;
	}

	private void setDoorRotation(int i)
	{
		float f = 0.1875F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
		int j = i & 3;
		boolean flag = isDoorOpen(i);
		boolean flag1 = (i & 16) != 0;

		if (j == 0)
		{
			if (flag)
			{
				if (!flag1)
				{
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
				}
				else
				{
					this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
				}
			}
			else
			{
				this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
			}
		}
		else if (j == 1)
		{
			if (flag)
			{
				if (!flag1)
				{
					this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				}
				else
				{
					this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
				}
			}
			else
			{
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
			}
		}
		else if (j == 2)
		{
			if (flag)
			{
				if (!flag1)
				{
					this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
				}
				else
				{
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
				}
			}
			else
			{
				this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			}
		}
		else if (j == 3)
		{
			if (flag)
			{
				if (!flag1)
				{
					this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
				}
				else
				{
					this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				}
			}
			else
			{
				this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
			}
		}
	}

	/**
	 * Called when a player hits the block. Args: world, x, y, z, player
	 */
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if(!player.isSneaking())
		{
			int i1 = this.getFullMeta(world, x, y, z);
			int j1 = i1 & 7;
			j1 ^= 4;

			if ((i1 & 8) == 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, j1, 2);
				world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			}
			else
			{
				world.setBlockMetadataWithNotify(x, y - 1, z, j1, 2);
				world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
			}

			world.playAuxSFXAtEntity(player, 1003, x, y, z, 0);
		}
		else
		{	
			Minecraft.getMinecraft().displayGuiScreen(new GuiSetTeleportationDoorLocation(player, x, y, z, TeleportationDoorLocator.getFrequency(world, x, y, z, world.provider.dimensionId)));	
		}

		return true;
	}

	public void onBlockAdded(World world, int x, int y, int z) 
	{	
		TeleportationDoorLocator.setFrequency(x, y, z, world.provider.dimensionId, 0);
	}

	public void onPoweredBlockChange(World world, int x, int y, int z, boolean p_150014_5_)
	{
		int l = this.getFullMeta(world, x, y, z);
		boolean flag1 = isDoorOpen(l);

		if (flag1 != p_150014_5_)
		{
			int i1 = l & 7;
			i1 ^= 4;

			if ((l & 8) == 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, i1, 2);
				world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			}
			else
			{
				world.setBlockMetadataWithNotify(x, y - 1, z, i1, 2);
				world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
			}

			world.playAuxSFXAtEntity((EntityPlayer)null, 1003, x, y, z, 0);
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor Block
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour)
	{
		int l = world.getBlockMetadata(x, y, z);

		if ((l & 8) == 0)
		{
			boolean flag = false;

			if (world.getBlock(x, y + 1, z) != this)
			{
				world.setBlockToAir(x, y, z);
				flag = true;
			}

			if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z))
			{
				world.setBlockToAir(x, y, z);
				flag = true;

				if (world.getBlock(x, y + 1, z) == this)
				{
					world.setBlockToAir(x, y + 1, z);
				}
			}

			if (flag)
			{
				if (!world.isRemote)
				{
					this.dropBlockAsItem(world, x, y, z, l, 0);
				}
			}
			else
			{
				boolean flag1 = world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y + 1, z);

				if ((flag1 || neighbour.canProvidePower()) && neighbour != this)
				{
					this.onPoweredBlockChange(world, x, y, z, flag1);
				}
			}
		}
		else
		{
			if (world.getBlock(x, y - 1, z) != this)
			{
				world.setBlockToAir(x, y, z);
			}

			if (neighbour != this)
			{
				this.onNeighborBlockChange(world, x, y - 1, z, neighbour);
			}
		}
	}

	public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_)
	{
		return TeleportationItems.teleportationDoor;
	}

	/**
	 * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
	 * x, y, z, startVec, endVec
	 */
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.collisionRayTrace(world, x, y, z, start, end);
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return y >= world.getHeight() - 1 ? false : World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && super.canPlaceBlockAt(world, x, y, z) && super.canPlaceBlockAt(world, x, y + 1, z);
	}

	/**
	 * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
	 * and stop pistons
	 */
	public int getMobilityFlag()
	{
		return 1;
	}

	public int getFullMeta(IBlockAccess world, int x, int y, int z)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		boolean flag = (metadata & 8) != 0;
		int i1;
		int j1;

		if (flag)
		{
			i1 = world.getBlockMetadata(x, y - 1, z);
			j1 = metadata;
		}
		else
		{
			i1 = metadata;
			j1 = world.getBlockMetadata(x, y + 1, z);
		}

		boolean flag1 = (j1 & 1) != 0;
		return i1 & 7 | (flag ? 8 : 0) | (flag1 ? 16 : 0);
	}

	/**
	 * Gets an item for the block being called on. Args: world, x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return TeleportationItems.teleportationDoor;
	}

	/**
	 * Called when the block is attempted to be harvested
	 */
	public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player)
	{
		if (player.capabilities.isCreativeMode && (metadata & 8) != 0 && world.getBlock(x, y - 1, z) == this)
		{
			world.setBlockToAir(x, y - 1, z);
		}

		TeleportationDoorLocator.setFrequency(x, y, z, world.provider.dimensionId, TeleportationDoorLocator.getFrequency(world, x, y, z, world.provider.dimensionId));
	}
}