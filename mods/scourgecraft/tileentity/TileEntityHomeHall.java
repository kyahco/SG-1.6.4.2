package mods.scourgecraft.tileentity;

import java.util.List;

import com.google.common.collect.Lists;

import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.data.HomeManager;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityHomeHall extends TileEntityHomeBuilding
{
	public static final String NBT_LEVEL = "Level";
	public static final String NBT_NAME = "Name";
	
	private String name;
	private int level;
	
	public TileEntityHomeHall()
	{
		super(500);
		name = "";
		level = 1;
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		level = nbt.getInteger(NBT_LEVEL);
		if (nbt.hasKey(NBT_NAME))
			name = nbt.getString(NBT_NAME);
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setInteger(NBT_LEVEL, level);
		if (!name.isEmpty())
			nbt.setString(NBT_NAME, name);
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String par1Name)
	{
		name = par1Name;
	}
	
	public int getBuildingCount(int blockId)
	{
		int widthOfHome = HomeManager.getHomeSize(level);
		int totalCount = 0;
		for (int x = xCoord - widthOfHome; x <= xCoord + widthOfHome; x++)
		{
			for (int y = yCoord - widthOfHome; y <= yCoord + widthOfHome; y++)
			{
				for (int z = zCoord - widthOfHome; z <= zCoord + widthOfHome; z++)
				{
					if (worldObj.getBlockId(x, y, z) == blockId)
						totalCount++;
				}
			}
		}
		return totalCount;
	}
	
	public int getTotalResourceCount(int blockId)
	{
		int widthOfHome = HomeManager.getHomeSize(level);
		int totalCount = 0;
		for (int x = xCoord - widthOfHome; x <= xCoord + widthOfHome; x++)
		{
			for (int y = yCoord - widthOfHome; y <= yCoord + widthOfHome; y++)
			{
				for (int z = zCoord - widthOfHome; z <= zCoord + widthOfHome; z++)
				{
					if (worldObj.getBlockId(x, y, z) == blockId)
					{
						TileEntity te = worldObj.getBlockTileEntity(x, y, z);
						if (te instanceof TileEntityScourgeResource)
						{
							TileEntityScourgeResource teResource = (TileEntityScourgeResource)te;
							totalCount += teResource.getGold();
						}
					}
				}
			}
		}
		return totalCount;
	}
	
	public List<TileEntityScourgeBuilding> getBuildingsByBlock(int blockId)
	{
		List<TileEntityScourgeBuilding> toReturn = Lists.newArrayList();
		int widthOfHome = HomeManager.getHomeSize(level);
		for (int x = xCoord - widthOfHome; x <= xCoord + widthOfHome; x++)
		{
			for (int y = yCoord - widthOfHome; y <= yCoord + widthOfHome; y++)
			{
				for (int z = zCoord - widthOfHome; z <= zCoord + widthOfHome; z++)
				{
					if (worldObj.getBlockId(x, y, z) == blockId)
						toReturn.add((TileEntityScourgeBuilding)worldObj.getBlockTileEntity(x, y, z));
				}
			}
		}
		return toReturn;
	}
	
	public void build()
	{
		if (!sentBuildCommands)
		{
			int widthOfHome = HomeManager.getHomeSize(1);
			sentBuildCommands = true;
			for (int x = this.xCoord - widthOfHome; x <= xCoord + widthOfHome; x++)
			{
				for (int z = zCoord - widthOfHome; z <= zCoord + widthOfHome; z++)
				{
					addBuildingTask(new BuildingTask(x, yCoord - 2, z, Block.bedrock.blockID));
					if (x == xCoord - widthOfHome)
						addBuildingTask(new BuildingTask(x, yCoord - 1, z, Block.stone.blockID));
					if (x == xCoord + widthOfHome)
						addBuildingTask(new BuildingTask(x, yCoord - 1, z, Block.stone.blockID));
				}
				addBuildingTask(new BuildingTask(x, yCoord - 1, zCoord + widthOfHome, Block.stone.blockID));
				addBuildingTask(new BuildingTask(x, yCoord - 1, zCoord - widthOfHome, Block.stone.blockID));
			}
			
			super.build(); //Always call Super.Build at end  This way if its alrdy completed, we clear the list. Avoiding server load.
		}
	}
	
	
}
