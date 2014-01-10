package mods.scourgecraft.permission;

import java.io.IOException;
import java.util.ArrayList;

import mods.scourgecraft.Home;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.data.HomeManager;
import mods.scourgecraft.data.PermissionManager;
import mods.scourgecraft.player.ExtendedPlayer;
import mods.scourgecraft.tileentity.TileEntityGoldProducer;
import mods.scourgecraft.tileentity.TileEntityHomeBuilding;
import mods.scourgecraft.tileentity.TileEntityHomeHall;
import mods.scourgecraft.tileentity.TileEntityScourgeBuilding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PermissionEventListener 
{

	@ForgeSubscribe
	public void ServerChat(ServerChatEvent event)
	{
		String line = event.component.toStringWithFormatting(true);

	}
	
	 @ForgeSubscribe
	  public void PlayerInteract(PlayerInteractEvent event)
	  {
		 String cancelMessage = "";
		 boolean cancelEvent = false;
	    if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK)
	    {

	      if (event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == ScourgeCraftCore.configBlocks.homeHallID)
	      {
	    	  cancelEvent = true;
	    	  cancelMessage = "Home Halls cannot be destroyed.";
	      }
	      
	      if (!PermissionManager.canLeftClickBlock(event.entityPlayer.username, event.x, event.y, event.z))
	      {
	    	  cancelEvent = true;
	    	  cancelMessage = "You cannot modify another players home.";
	      }

	      if (cancelEvent)
	      {
	        if (event.isCancelable())
	        {
	        	if (event.entityPlayer.worldObj.isRemote)
	        		event.entityPlayer.addChatMessage(cancelMessage);
	          event.setCanceled(true);
	        }
	      }
	    }
	    
	    if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
	    {
		   if (!PermissionManager.canRightClickBlock(event.entityPlayer.username, event.x, event.y, event.z))
		   {
			   cancelEvent = true;
		    	cancelMessage = "You cannot build outside your home.";
		    }
	      
	      if (cancelEvent)
	      {
	    	  if (event.entityPlayer.worldObj.isRemote)
	        		event.entityPlayer.addChatMessage(cancelMessage);
	          event.setCanceled(true);
	      }
	    }
	  }

	public ArrayList<String> GetAvailablePerms()
	  {
	    ArrayList perms = new ArrayList();
	    perms.add("kick");
	    perms.add("ban");
	    perms.add("mute");
	    perms.add("build");
	    perms.add("demolish");
	    perms.add("home");
	    perms.add("tp");
	    perms.add("tpme");
	    perms.add("give");
	    perms.add("giveme");
	    perms.add("setspawn");
	    perms.add("tpspawn");
	    perms.add("protect");

	    return perms;
	  }

	  public boolean PlayerIsOP(String username)
	  {
	    if (MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer()).isPlayerOpped(username)) {
	      return true;
	    }
	    return false;
	  }
}
