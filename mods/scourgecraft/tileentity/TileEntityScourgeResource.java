package mods.scourgecraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityScourgeResource extends TileEntityScourgeBuilding {


	public static final String NBT_RESOURCE_COUNT = "ResourceCount";
	public static final String NBT_STEAL_AMOUNT = "StealAmount";
	protected double goldCount;
	protected double stealAmount; //When a raid begins, this gets set and its the max amount that can be stolen.
	protected double stealPercentage;
	protected boolean raidActive = false;
	

	public TileEntityScourgeResource(int par1BuildTime) {
		super(par1BuildTime);
		// TODO Auto-generated constructor stub
	}
	
	public void startRaid()
	{
		raidActive = true;
		stealAmount = (goldCount * stealPercentage);
		goldCount -= stealAmount;
	}
	
	public void endRaid()
	{
		raidActive = false;
		goldCount += stealAmount;
		stealAmount = 0;
	}
	
	public double steal(double percentTake)
	{
		double stolenAmount = stealAmount * percentTake;
		stealAmount -= stolenAmount;
		
		return stolenAmount;
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		goldCount = nbt.getDouble(NBT_RESOURCE_COUNT);
		stealAmount = nbt.getDouble(NBT_STEAL_AMOUNT);
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setDouble(NBT_RESOURCE_COUNT, goldCount);
		nbt.setDouble(NBT_STEAL_AMOUNT, stealAmount);
	}

	public int getGold()
	{
		return (int)goldCount;
	}
	
	public void setGold(double amount)
	{
		goldCount = amount;
	}
	
	public double getMaxStorage(int par1Level)
	{
		return 0.00;
	}
	
	public double getRate(int par1Level)
	{		
		return 0.00;
	}
	
	public double getRate()
	{
		return getRate(level);
	}
	
	public double getMaxStorage()
	{
		return getMaxStorage(level);
	}
	
	public double getStealAmount()
	{
		return stealAmount;
	}
	
	public boolean isRaidActive()
	{
		return raidActive;
	}
	
}
