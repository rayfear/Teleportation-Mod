package tpmod.location;

public class Location
{
	public int x;
	public int y;
	public int z;
	
	public int dimension;
	
	public Location(int x, int y, int z, int dimension)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.dimension = dimension;
	}
	
	@Override
	public int hashCode()
	{
		return x + y * 25 + z * 52 + dimension * 25;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Location)
		{
			Location comparison = (Location) obj;
			
			return this.x == comparison.x && this.y == comparison.y && this.z == comparison.z && this.dimension == comparison.dimension;
		}
		
		return false;
	}
}
