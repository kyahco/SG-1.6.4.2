package mods.scourgecraft.data;

import java.util.HashMap;
import java.util.List;

import mods.scourgecraft.Home;
import mods.scourgecraft.Raid;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.network.packet.Packet1HomeInfo;
import mods.scourgecraft.network.packet.Packet6RaidInfo;
import mods.scourgecraft.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class RaidManager 
{
	public static HashMap<String, Raid> raidList = new HashMap<String, Raid>();
	public static List<Raid> endedRaidList = Lists.newArrayList();
	
	public RaidManager()
	{
		
	}
	
	public static Raid startRaid(EntityPlayer par1PlayerAttacker, EntityPlayer par1PlayerDefender)
	{
		Raid r = new Raid();
		r.attacker = par1PlayerAttacker;
		r.defender = par1PlayerDefender;
		r.roundType = 1;
		r.timeLeft = setTimeLeft(r.roundType);
		raidList.put(par1PlayerAttacker.username, r);
		r.attacker.worldObj.playSoundAtEntity(r.attacker, ScourgeCraftCore.modid.toLowerCase() + ":" + "warmup", 2.0f, 2.0f);
		return r;
	}
	
	public static void playerLogin(EntityPlayer player)
	{
		for (Raid h : RaidManager.raidList.values())
		{
			PacketDispatcher.sendPacketToPlayer(new Packet6RaidInfo(h).makePacket(), (Player)player);;
		}
	}
	
	public static void tick()
	{
		for (Raid r : raidList.values())
		{
			r.timeLeft--;
			if (r.timeLeft == 0)
			{
				advanceRound(r);
			}
			if (r.isEnded)
			{
				endedRaidList.add(r);
			}
		}
		for (Raid r : endedRaidList)
		{
			ExtendedPlayer extPlayer = ExtendedPlayer.getExtendedPlayer(r.attacker);
			extPlayer.myRaid = null;
			extPlayer = ExtendedPlayer.getExtendedPlayer(r.defender);
			extPlayer.myRaid = null;
			raidList.remove(r.attacker);
		}
		endedRaidList.clear();
	}
	
	private static int setTimeLeft(short round)
	{
		switch (round)
		{
			case 1:
				return 400;
			case 2:
				return 400;
			case 3:
				return 400;
			case 4:
				return 400;
		}
		return 0;
	}

	private static void advanceRound(Raid r)
	{
		switch (r.roundType)
		{
			case 1:
			{
				r.roundType = 2;
				r.attacker.worldObj.playSoundAtEntity(r.attacker, ScourgeCraftCore.modid.toLowerCase() + ":" + "balance", 2.0f, 2.0f);
				r.timeLeft = setTimeLeft(r.roundType);
				break;
			}
			case 2:
			{
				r.roundType = 3;
				r.attacker.worldObj.playSoundAtEntity(r.attacker, ScourgeCraftCore.modid.toLowerCase() + ":" + "warcry", 2.0f, 2.0f);
				r.timeLeft = setTimeLeft(r.roundType);
				break;
			}
			case 3:
			{
				r.roundType = 4;
				r.attacker.worldObj.playSoundAtEntity(r.attacker, ScourgeCraftCore.modid.toLowerCase() + ":" + "warend", 2.0f, 2.0f);
				r.timeLeft = setTimeLeft(r.roundType);
			}
			case 4:
			{
				r.isEnded = true;
				break;
			}
		}
	}

	public static boolean canRaid(EntityPlayer player, EntityPlayer defend) {
		ExtendedPlayer extPlayer = ExtendedPlayer.getExtendedPlayer(player);
		if (extPlayer != null)
		{
			if (player.username.equals(defend.username))
			{
				player.addChatMessage("[ScourgeCraft] You cannot raid yourself.");
				return false;
			}
			if (extPlayer.myHome == null)
			{
				player.addChatMessage("[ScourgeCraft] You cannot raid without a home yourself.");
				return false;
			}
			if (extPlayer.myRaid != null)
			{
				player.addChatMessage("[ScourgeCraft] You already have a raid started.");
				return false;
			}
		}
		extPlayer = ExtendedPlayer.getExtendedPlayer(defend);
		if (extPlayer != null)
		{
			if (extPlayer.myHome == null)
			{
				player.addChatMessage("[ScourgeCraft] You cannot raid " + defend.username + " as he does not have a home.");
				return false;
			}
			if (extPlayer.myRaid != null)
			{
				player.addChatMessage("[ScourgeCraft] " + defend.username + " is already in a raid.");
				return false;
			}
		}
		return true;
	}
	
}
