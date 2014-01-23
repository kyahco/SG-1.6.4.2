package mods.scourgecraft.player;

import cpw.mods.fml.common.IPlayerTracker;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.data.HomeManager;
import mods.scourgecraft.data.RaidManager;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerHandler implements IPlayerTracker
{
        @Override
        public void onPlayerLogin(EntityPlayer player)
        { //Ran by Server only.  Not on client.
        	HomeManager.playerLogin(player, true);
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
        	HomeManager.playerLogin(player, false);
        	RaidManager.playerLogin(player);
        	ExtendedPlayer.playerLogin(player);
        	
        	ExtendedPlayer extPlayer = ExtendedPlayer.getExtendedPlayer(player);
        	if (extPlayer != null && extPlayer.myHome != null)
        		player.setPosition(extPlayer.myHome.xCoord, extPlayer.myHome.yCoord + 1, extPlayer.myHome.zCoord);
        }
}