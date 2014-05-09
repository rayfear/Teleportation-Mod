package tpmod.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;


public class TeleportationConfig
{  
	public static int dimensionID;
	
    public static Property enable3D;
    public static Property enableUpdateCheck;

    public static void loadConfig(Configuration config)
    {
        config.load();
        
        dimensionID = config.get(Configuration.CATEGORY_GENERAL, "Dimension ID", 20).getInt();
       
        enable3D = config.get(Configuration.CATEGORY_GENERAL, "Should Enable 3D Items", true);
        enableUpdateCheck = config.get(Configuration.CATEGORY_GENERAL, "Enable Update Checker", true);
        
        config.save();
    }
}
