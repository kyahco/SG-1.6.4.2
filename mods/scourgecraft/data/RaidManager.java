package mods.scourgecraft.data;

import java.util.HashMap;
import java.util.List;

import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.helpers.FunctionHelper;
import mods.scourgecraft.helpers.Home;
import mods.scourgecraft.helpers.Raid;
import mods.scourgecraft.helpers.point.WarpPoint;
import mods.scourgecraft.network.packet.Packet1HomeInfo;
import mods.scourgecraft.network.packet.Packet6RaidInfo;
import mods.scourgecraft.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class RaidManager 
{
	private int tickCounter = 0;
	public static HashMap<String, Raid> raidList = new HashMap<String, Raid>();
	public static List<Raid> endedRaidList = Lists.newArrayList();
	
	public RaidManager()
	{
		
	}
	
	public static Raid startRaid(EntityPlayer par1PlayerAttacker, EntityPlayer par1PlayerDefender)
	{ 
		//Ran on server only.
		Raid r = new Raid();
		
		r.setAttacker(par1PlayerAttacker);
		r.setDefender(par1PlayerDefender);
		
		r.attackerHome = HomeManager.getHomeByPlayerName(r.attacker.username);
		r.defenderHome = HomeManager.getHomeByPlayerName(r.defender.username);
		
		r.roundType = 1;
		r.timeLeft = setTimeLeft(r.roundType);
		
		raidList.put(par1PlayerAttacker.username, r);
		
		r.attacker.worldObj.playSoundAtEntity(r.attacker, ScourgeCraftCore.modid.toLowerCase() + ":" + "warmup", 2.0f, 2.0f);
		r.defender.worldObj.playSoundAtEntity(r.defender, ScourgeCraftCore.modid.toLowerCase() + ":" + "warmup", 2.0f, 2.0f);

		r.attacker.addChatMessage("[ScourgeCraft] A Raid has been initiated vs " + r.defender.username);
		r.defender.addChatMessage("[ScourgeCraft] Warning! A Raid has been initiated by " + r.attacker.username + " on your home!");
		return r;
	}
	
	public static void playerLogin(EntityPlayer player)
	{
		for (Raid h : RaidManager.raidList.values())
		{
			PacketDispatcher.sendPacketToPlayer(new Packet6RaidInfo(h).makePacket(), (Player)player);;
		}
	}
	
	public static void tick(boolean isServer)
	{
		for (Raid r : raidList.values())
		{
			r.timeLeft--;
			if (r.timeLeft <= 0)
			{
				advanceRound(r, isServer);
			}
			if (r.isEnded)
			{
				endedRaidList.add(r);
			}
		}
		for (Raid r : endedRaidList)
		{
			raidList.remove(r.attackerName);
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

	private static void advanceRound(Raid r, boolean isServer)
	{
		switch (r.roundType)
		{
			case 1:
			{
				r.roundType = 2;
				r.timeLeft = setTimeLeft(r.roundType);
				
				if (isServer)
				{
					r.attacker.worldObj.playSoundAtEntity(r.attacker, ScourgeCraftCore.modid.toLowerCase() + ":" + "balance", 2.0f, 2.0f);
					r.defender.worldObj.playSoundAtEntity(r.defender, ScourgeCraftCore.modid.toLowerCase() + ":" + "balance", 2.0f, 2.0f);
					r.attacker.addChatMessage("[ScourgeCraft] You have been teleported to the home of " + r.defender.username);
					r.attacker.addChatMessage("[ScourgeCraft] Once the War period begins, you may attack!");
					r.defender.addChatMessage("[ScourgeCraft] You are being teleported to your home.");
					r.defender.addChatMessage("[ScourgeCraft] Protect yourself and your home!");
					
					FunctionHelper.setPlayer((EntityPlayerMP)r.attacker, new WarpPoint(0, r.defenderHome.xCoord + HomeManager.getHomeSize(r.defenderHome.level) + 5, r.defenderHome.yCoord, r.defenderHome.zCoord + HomeManager.getHomeSize(r.defenderHome.level) + 5, r.attacker.rotationYaw , r.attacker.rotationPitch));
					FunctionHelper.setPlayer((EntityPlayerMP)r.defender, new WarpPoint(0, r.defenderHome.xCoord, r.defenderHome.yCoord, r.defenderHome.zCoord, r.defender.rotationYaw , r.defender.rotationPitch));
				}
				break;
			}
			case 2:
			{
				r.roundType = 3;
				r.timeLeft = setTimeLeft(r.roundType);
				
				if (isServer)
				{
					r.defender.worldObj.playSoundAtEntity(r.defender, ScourgeCraftCore.modid.toLowerCase() + ":" + "warcry", 2.0f, 2.0f);
					r.attacker.worldObj.playSoundAtEntity(r.attacker, ScourgeCraftCore.modid.toLowerCase() + ":" + "warcry", 2.0f, 2.0f);
					
					r.attacker.addChatMessage("[ScourgeCraft] The War Period has begun! Best of luck!");
					r.defender.addChatMessage("[ScourgeCraft] The War Period has begun! Best of luck!");
				}
				break;
			}
			case 3:
			{
				r.roundType = 4;
				r.timeLeft = setTimeLeft(r.roundType);
				
				if (isServer)
				{
					r.defender.worldObj.playSoundAtEntity(r.defender, ScourgeCraftCore.modid.toLowerCase() + ":" + "warend", 2.0f, 2.0f);
					r.attacker.worldObj.playSoundAtEntity(r.attacker, ScourgeCraftCore.modid.toLowerCase() + ":" + "warend", 2.0f, 2.0f);
					r.attacker.addChatMessage("[ScourgeCraft] The War Period has ended! The Raid is now over.");
					r.defender.addChatMessage("[ScourgeCraft] The War Period has ended! The Raid is now over.");
				}
				break;
			}
			case 4:
			{
				if (isServer)
				{
					FunctionHelper.setPlayer((EntityPlayerMP)r.attacker, new WarpPoint(0, r.attackerHome.xCoord, r.attackerHome.yCoord + 1, r.attackerHome.zCoord, r.attacker.rotationYaw , r.attacker.rotationPitch));
					FunctionHelper.setPlayer((EntityPlayerMP)r.defender, new WarpPoint(0, r.defenderHome.xCoord, r.defenderHome.yCoord + 1, r.defenderHome.zCoord, r.defender.rotationYaw , r.defender.rotationPitch));
				}
				r.isEnded = true;
				break;
			}
		}
	}

	public static boolean canRaid(EntityPlayer player, EntityPlayer defend) 
	{
			if (player.username.equals(defend.username))
			{
				player.addChatMessage("[ScourgeCraft] You cannot raid yourself.");
				return false;
			}
			if (!HomeManager.hasHome(player.username))
			{
				player.addChatMessage("[ScourgeCraft] You cannot raid without a home yourself.");
				return false;
			}
			if (RaidManager.isInRaid(player.username))
			{
				player.addChatMessage("[ScourgeCraft] You already have a raid started.");
				return false;
			}

			if (!HomeManager.hasHome(defend.username))
			{
				player.addChatMessage("[ScourgeCraft] You cannot raid " + defend.username + " as he does not have a home.");
				return false;
			}
			if (RaidManager.isInRaid(defend.username))
			{
				player.addChatMessage("[ScourgeCraft] " + defend.username + " is already in a raid.");
				return false;
			}
		return true;
	}
	
	public static boolean isInRaid(String username)
	{
		if (raidList.containsKey(username))
			return true;
		
		for (Raid r : raidList.values())
		{
			if (r.defenderName.equals(username))
				return true;
		}
		return false;
	}
	
	public static Raid getRaidByUsername(String username)
	{
		if (raidList.get(username) != null)
			return raidList.get(username);
		for (Raid r : raidList.values())
		{
			if (r.defenderName.equals(username))
				return r;
		}
		return null;
	}
}
