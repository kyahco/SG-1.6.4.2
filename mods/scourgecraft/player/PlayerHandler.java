package mods.scourgecraft.player;

import cpw.mods.fml.common.IPlayerTracker;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.data.HomeManager;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerHandler implements IPlayerTracker
{
        @Override
        public void onPlayerLogin(EntityPlayer player)
        { //Ran by Server only.  Not on client.
        	HomeManager.playerLogin(player);
        	ScourgeCraftCore.instance.raidManager.playerLogin(player);
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
        {
        }
}