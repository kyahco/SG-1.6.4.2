package mods.scourgecraft.data;

import java.util.HashMap;
import java.util.List;

import mods.scourgecraft.ScourgeCraftCore;
import net.minecraft.entity.player.EntityPlayer;

import com.google.common.collect.Lists;

public class RaidManager 
{
	public HashMap<String, Raid> raidList = new HashMap<String, Raid>();
	public List<Raid> endedRaidList = Lists.newArrayList();
	
	public class Raid
	{
		public EntityPlayer attacker;
		public EntityPlayer defender;
		public int timeLeft;
		public short roundType;
		private boolean isEnded = false;
		
		//RoundType : 1 - Warmup 2 - Balance 3 - War 4 - End
	}
	
	public RaidManager()
	{
		
	}
	
	public Raid startRaid(EntityPlayer par1PlayerAttacker, EntityPlayer par1PlayerDefender)
	{
		Raid r = new Raid();
		r.attacker = par1PlayerAttacker;
		r.defender = par1PlayerDefender;
		r.roundType = 1;
		r.timeLeft = setTimeLeft(r.roundType);
		raidList.put(par1PlayerAttacker.username, r);
		return r;
	}
	
	public void tick()
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
			raidList.remove(r.attacker);
		}
		endedRaidList.clear();
	}
	
	private int setTimeLeft(short round)
	{
		switch (round)
		{
			case 1:
				return 250;
			case 2:
				return 2000;
			case 3:
				return 1000;
			case 4:
				return 1000;
		}
		return 0;
	}

	private void advanceRound(Raid r)
	{
		switch (r.roundType)
		{
			case 1:
			{
				r.roundType = 2;
				r.attacker.worldObj.playSoundEffect((double)((float)r.attacker.posX + 0.5F), (double)((float)r.attacker.posY + 0.5F), (double)((float)r.attacker.posZ + 0.5F), ScourgeCraftCore.modid.toLowerCase() + ":" + "balance", 2.0f, 2.0f);
				r.timeLeft = setTimeLeft(r.roundType);
				break;
			}
			case 2:
			{
				r.roundType = 3;
				r.timeLeft = setTimeLeft(r.roundType);
				break;
			}
			case 3:
			{
				r.roundType = 4;
				r.timeLeft = setTimeLeft(r.roundType);
			}
			case 4:
			{
				r.isEnded = true;
				break;
			}
		}
	}
	
}
