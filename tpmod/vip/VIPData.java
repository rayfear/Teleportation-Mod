package tpmod.vip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tpmod.updates.TextDecoder;

public class VIPData 
{
    public static List<String> vips = new ArrayList<String>();

	public static List<String> loadVIPs()
	{
		try 
		{
			URL url = new URL("https://dl.dropboxusercontent.com/u/90093617/VIP'S/Teleportation%20Mod/Teleportation%20Mod%20VIPS.txt");

			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String str;
			String text = null;
			
			while ((str = in.readLine()) != null) 
			{
				text = text + str;
			}
			
			in.close();

			TextDecoder decoder = new TextDecoder();

			String[] arrayOfNames = decoder.decode(text, "VIP's");
			
			vips = decoder.stringArrayToList(arrayOfNames);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return vips;
	}
    
    
}
