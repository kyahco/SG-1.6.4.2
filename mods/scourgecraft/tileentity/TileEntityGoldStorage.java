package mods.scourgecraft.tileentity;

public class TileEntityGoldStorage extends TileEntityScourgeResource
{	
	public static final int BUILD_TIME = 1000;
	
	public TileEntityGoldStorage() {
		super(BUILD_TIME);
		
		stealPercentage = 0.10;
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
		}
		
		return 0.00;
	}
	
	@Override
	public double getRate(int par1Level)
	{		
		return 0.00;
	}
	
	@Override
	public boolean hasNextLevel()
	{ 
		return  getMaxStorage(level + 1) != 0.00;
	}
	
	public static int getTotalMaxByTownLevel(int par1Level)
	{
		switch (par1Level)
		{
			case 1:
				return 1;
			case 2:
				return 1;
		}
		
		return 0;
	}
	
	public double storeGold(double amount)
	{
		if (amount + getGold() >= getMaxStorage())
		{
			double amountLeft = getMaxStorage() - getGold();
			amountLeft = amount - amountLeft;
			return amountLeft;
		}
		else
		{
			setGold(amount + getGold());
			return 0;
		}
	}
}
