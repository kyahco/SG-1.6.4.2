package mods.scourgecraft.data;

import java.util.HashMap;

import mods.scourgecraft.helpers.Home;
import mods.scourgecraft.player.ExtendedPlayer;
import cpw.mods.fml.common.network.Player;

public class PermissionManager 
{

	
	public static boolean canLeftClickBlock(String username, int x, int y, int z)
	{
		for (Home h : HomeManager.homeList.values())
		{
			if (HomeManager.IsPointInHome(h, x, y, z))
			{
				if (username.equals(h.ownerUsername))
					return true; //If the spot is in your town, we can stop checking.
				else 
					return false;
			}
		}
		return true;
	}
	
	public static boolean canRightClickBlock(String username, int x, int y, int z)
	{
		for (Home h : HomeManager.homeList.values())
		{
			if (HomeManager.IsPointInHome(h, x, y, z))
			{
				if (username.equals(h.ownerUsername))
					return true; //If the spot is in your town, we can stop checking.
			}
		}
		return false;
	}
}
