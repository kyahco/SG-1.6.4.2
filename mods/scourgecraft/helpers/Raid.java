package mods.scourgecraft.helpers;

import net.minecraft.entity.player.EntityPlayer;

public class Raid
{
	public String attackerName;
	public String defenderName;
	//Attacker and Defender will only be set by the server.  The client will have no idea, and this will be null.  Check StartRaid for set by Server
	public EntityPlayer attacker;
	public EntityPlayer defender;
	public int timeLeft;
	public short roundType;
	//RoundType : 1 - Warmup 2 - Balance 3 - War 4 - End
	public boolean isEnded = false;
	public Home defenderHome;
	public Home attackerHome;
	
	public double goldStolen;
	
	public void setAttacker(EntityPlayer par1Attacker)
	{
		attacker = par1Attacker;
		attackerName = par1Attacker.username;
	}
	public void setDefender(EntityPlayer par1Defender)
	{
		defender = par1Defender;
		defenderName = par1Defender.username;
	}
}
