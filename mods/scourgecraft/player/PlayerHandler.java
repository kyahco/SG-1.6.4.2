package mods.scourgecraft.player;

import cpw.mods.fml.common.IPlayerTracker;
import mods.scourgecraft.Home;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.data.HomeManager;
import mods.scourgecraft.data.RaidManager;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerHandler implements IPlayerTracker
{
        @Override
        public void onPlayerLogin(EntityPlayer player)
        { //Ran by Server only.  Not on client.
        	HomeManager.playerLogin(player);
        	RaidManager.playerLogin(player);
        	ExtendedPlayer.playerLogin(player);
        }

        @Override
        public void onPlayerLogout(EntityPlayer player)
        {
        }

        @Override
        public void onPlayerChangedDimension(EntityPlayer player)
        {
        }

        @Override
        public void onPlayerRespawn(EntityPlayer player)
        {	//Ran by Server.
        	//Is there a better place to Sync all Properties?
        	HomeManager.playerLogin(player);
        	RaidManager.playerLogin(player);
        	ExtendedPlayer.playerLogin(player);
        	
        	ExtendedPlayer extPlayer = ExtendedPlayer.getExtendedPlayer(player);
        	Home h = HomeManager.getHomeByPlayerName(player.username);
        	if (h != null)
        		player.setPosition(h.xCoord, h.yCoord + 1, h.zCoord);
        }
}