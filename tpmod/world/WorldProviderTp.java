package tpmod.world;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderTp extends WorldProvider
{
	/**
	 * creates a new world chunk manager for WorldProvider
	 */
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerTp(TeleportationBiomes.teleportbiome, 1.0F, 1.0F);
		this.isHellWorld = false;
		this.hasNoSky = false;
		this.dimensionId = TeleportationDimensions.dimension;
	}

	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderTp(this.worldObj, this.worldObj.getSeed(), hasNoSky);
	}

	/**
	 * Creates the light to brightness table
	 */
	protected void generateLightBrightnessTable()
	{
		super.generateLightBrightnessTable();

		for (int i = 0; i <= 15; ++i)
		{
			this.lightBrightnessTable[i] = this.lightBrightnessTable[i] - 0.075f;
		}
	}

	public boolean isSurfaceWorld()
	{
		return true;
	}
	
	/**
	 * Will check if the x, z position specified is alright to be set as the map spawn point
	 */
	public boolean canCoordinateBeSpawn(int par1, int par2)
	{
		return true;
	}

	public float calculateCelestialAngle(long par1, float par3)
	{
		int var4 = (int)(par1 % 24000L);
		float var5 = ((float)var4 + par3) / 24000.0F - 0.25F;

		if (var5 < 0.0F)
		{
			++var5;
		}

		if (var5 > 1.0F)
		{
			--var5;
		}

		float var6 = var5;
		var5 = 1.0F - (float)((Math.cos((double)var5 * Math.PI) + 1.0D) / 2.0D);
		var5 = var6 + (var5 - var6) / 3.0F;
		return var5;
	}


	/**
	 * True if the player can respawn in this dimension (true = overworld, false = nether).
	 */
	public boolean canRespawnHere()
	{
		return false;
	}

	/**
	 * Returns true if the given X,Z coordinate should show environmental fog.
	 */
	public boolean doesXZShowFog(int par1, int par2)
	{
		return false;
	}

	/**
	 * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
	 */
	public String getDimensionName()
	{
		return "Teleportation Dimension";
	}
}
