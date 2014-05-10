package tpmod.updates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import tpmod.TeleportationMod;

public class UpdateChecker 
{
	public Update checkForUpdates()
	{
		boolean isNewUpdateAvailable = false;

		try 
		{
			// Create a URL for the desired page
			URL url = new URL("https://dl.dropboxusercontent.com/u/90093617/Mod%20Updates/Teleportation%20Mod/Teleportation%20Mod%20Updates.txt");

			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			String text = null;
			while ((str = in.readLine()) != null) 
			{
				text = text + str;
			}
			in.close();

			TextDecoder decoder = new TextDecoder();

			String[] values = decoder.decode(text, "Latest", "UpdateLog");

			Version newestVersion = new Version(values[0]);
			Version currentVersion = new Version(TeleportationMod.VERSION);

			if(newestVersion.compareTo(currentVersion) == 1)
			{
				isNewUpdateAvailable = true;
				return new Update(isNewUpdateAvailable, values[0], values[1]);
			}
			else
			{
				isNewUpdateAvailable = false;
				return new Update();
			}

		} 
		catch (Exception e) 
		{
			System.err.println("[Teleportation Mod] Failed to read mod version! Please notify the author!");
			e.printStackTrace();
			return new Update();
		}

	}
}
