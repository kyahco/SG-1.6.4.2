package mods.scourgecraft;

import net.minecraft.entity.player.EntityPlayer;

public class Raid
{
	public EntityPlayer attacker;
	public EntityPlayer defender;
	public int timeLeft;
	public short roundType;
	//RoundType : 1 - Warmup 2 - Balance 3 - War 4 - End
	public boolean isEnded = false;
}
