package tpmod.updates;

public class Update 
{
	public boolean isAvailable;
	public String version;
	public String update;
	
	public Update(boolean isAvailable, String version, String update)
	{
		this.isAvailable = isAvailable;
		this.version = version;
		this.update = update;
	}
	public Update()
	{
		this.isAvailable = false;
	}
}
