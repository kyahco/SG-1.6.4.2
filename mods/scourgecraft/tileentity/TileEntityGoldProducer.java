package mods.scourgecraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGoldProducer extends TileEntityScourgeResource
{
	public static final int BUILD_TIME = 1000;
	
	public TileEntityGoldProducer() {
		super(BUILD_TIME);
	}
	

	@Override
	public void updateEntity()
	{
		if (isCompleted())
		{
			if (goldCount >= getMaxStorage())
				goldCount = getMaxStorage();
			else
				goldCount += getRate();
		}
		
		super.updateEntity();
	}
	
	@Override
	public boolean canUpdate()
	{
		if (goldCount == getMaxStorage()) // No reason to update this TE if the storage is maxed.
			return false;
		return true;
	}
	
	@Override
	public double getRate(int par1Level)
	{
		switch (par1Level)
		{
			case 1: // This will give 1.2 Gold every minute.  
				return 0.001;
			case 2:
				return 0.002;
			case 3:
				return 0.004;
		}
		
		return 0.00;
	}
	
	@Override
	public double getMaxStorage(int par1Level)
	{
		switch (par1Level)
		{
			case 1:
				return 100.00;
			case 2:
				return 200.00;
			case 3: 
				return 500.00;
		}
		
		return 0.00;
	}
	
	@Override
	public boolean hasNextLevel()
	{ 
		return getRate(level + 1) != 0.00 && getMaxStorage(level + 1) != 0.00 && upgradeTime(level + 1) != 0;
	}
	
	@Override
	public int upgradeTime(int level)
	{
		switch(level)
		{
			case 2:
				return 3000;
			case 3:
				return 10000;
		}
		return 0; //We should never hit this.
	}

	public static int getTotalMaxByTownLevel(int par1Level)
	{
		switch (par1Level)
		{
			case 1:
				return 2;
			case 2:
				return 2;
		}
		
		return 0;
	}
}
