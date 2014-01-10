package mods.scourgecraft.tileentity;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import mods.scourgecraft.ScourgeCraftCore;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityHomeBuilding extends TileEntityScourgeBuilding 
{

	//This class is special, since the building occurs on Home blocks after someone claims it.
	
	public class BuildingTask
	{
		public int xCoord;
		public int yCoord;
		public int zCoord;
		public int blockId;
		
		public BuildingTask(int x, int y, int z, int block)
		{
			xCoord = x;
			yCoord = y;
			zCoord = z;
			blockId = block;
		}
	}
	
	public static final String NBT_OWNER_NAME = "Owner";
	public static final String NBT_BUILD_TIME = "BuildTime";
	public static final String NBT_TIME_LEFT = "TimeLeft";
	
	private List<BuildingTask> buildingTasks = Lists.newArrayList();
	private String owner;
	private int buildTime;
	private int timeLeft = 0;
	private int delay;
	protected boolean sentBuildCommands = false;
	

	public TileEntityHomeBuilding(int par1BuildTime) {
		super(par1BuildTime);
			buildTime = par1BuildTime;
			owner = "";
	}
	
	@Override
	public void updateEntity()
	{
		if (sentBuildCommands)
		{
			if (buildingTasks.size() > 0)
			{
				if (timeLeft % delay == 0)
				{
					BuildingTask task = buildingTasks.remove(0);
					worldObj.setBlock(task.xCoord, task.yCoord, task.zCoord, task.blockId);
				}
			}
			if (timeLeft > 0)
				timeLeft -= 1;
			else 
			{
				timeLeft = 0;
				for (BuildingTask t : buildingTasks)
				{
					worldObj.setBlock(t.xCoord, t.yCoord, t.zCoord, t.blockId);
				}
				buildingTasks.clear();
			}
		}
	}
	
	public boolean isCompleted()
	{
		return timeLeft == 0;
	}
	
	public int percentCompleted()
	{
		return (int)(((buildTime - timeLeft)/(float)buildTime) * 100);
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		if (nbt.hasKey(NBT_OWNER_NAME))
			owner = nbt.getString(NBT_OWNER_NAME);
		buildTime = nbt.getInteger(NBT_BUILD_TIME);
		timeLeft = nbt.getInteger(NBT_TIME_LEFT);
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		if (!owner.isEmpty())
			nbt.setString(NBT_OWNER_NAME, owner);
		nbt.setInteger(NBT_BUILD_TIME, buildTime);
		nbt.setInteger(NBT_TIME_LEFT, timeLeft);
	}
	
	public String getOwner()
	{
		return owner;
	}
	
	public void setOwner(String par1Owner)
	{
		owner = par1Owner;
	}
	
	@Override
	public boolean canUpdate()
	{
		if (isCompleted() && sentBuildCommands)
			return false;
		return true;
	}
	
	protected void addBuildingTask(BuildingTask task)
	{
		buildingTasks.add(task);
	}
	
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tileTag = new NBTTagCompound();
		this.writeToNBT(tileTag);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, tileTag);
	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		this.readFromNBT(pkt.data);
	}
	
	public void build()
	{
		timeLeft = buildTime;
		if (isCompleted())
			buildingTasks.clear();
		
		if (buildingTasks.size() > 0)
			delay = buildTime / buildingTasks.size();
	}
}
