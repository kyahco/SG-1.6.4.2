package mods.scourgecraft.data;

import java.util.HashMap;

import mods.scourgecraft.Home;
import mods.scourgecraft.player.ExtendedPlayer;
import cpw.mods.fml.common.network.Player;

public class PermissionManager 
{
	public static HashMap<String, Home> homeList = new HashMap<String, Home>();
	
	public static boolean canLeftClickBlock(String username, int x, int y, int z)
	{
		for (Home h : homeList.values())
		{
			if (PermissionManager.IsPointInHome(h, x, y, z))
			{
				if (username.equals(h.ownerUsername))
					return true; //If the spot is in your town, we can stop checking.
				else 
					return false;
			}
		}
		return true;
	}
	
	public static Home getHomeByPlayerName(String username)
	{
		for (String h : homeList.keySet())
		{
			if (h.equals(username))
				return homeList.get(h);
		}
		return null;
	}
	
	public static boolean canRightClickBlock(String username, int x, int y, int z)
	{
		for (Home h : homeList.values())
		{
			if (PermissionManager.IsPointInHome(h, x, y, z))
			{
				if (username.equals(h.ownerUsername))
					return true; //If the spot is in your town, we can stop checking.
			}
		}
		return false;
	}
	
	private static boolean IsPointInHome(Home h, int x, int y, int z)
	{
		int widthOfHome = HomeManager.getHomeSize(h.level);
		
		if (h.xCoord - widthOfHome <= x && h.xCoord + widthOfHome >= x 
				&& h.yCoord - widthOfHome <= y && h.yCoord + widthOfHome >= y
				&& h.zCoord - widthOfHome <= z && h.zCoord + widthOfHome >= z )
		{
			return true;
		}
		return false;
	}
}
