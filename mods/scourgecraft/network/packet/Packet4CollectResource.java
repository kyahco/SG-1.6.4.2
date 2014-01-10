package mods.scourgecraft.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;
import mods.scourgecraft.Home;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.data.HomeManager;
import mods.scourgecraft.data.PermissionManager;
import mods.scourgecraft.network.ScourgePacket;
import mods.scourgecraft.player.ExtendedPlayer;
import mods.scourgecraft.tileentity.TileEntityHomeHall;
import mods.scourgecraft.tileentity.TileEntityScourgeResource;

public class Packet4CollectResource extends ScourgePacket {

	private int xCoord;
	private int yCoord;
	private int zCoord;
   
    public Packet4CollectResource(TileEntityScourgeResource par1Resource) {
    		this.xCoord = par1Resource.xCoord;
    		this.yCoord = par1Resource.yCoord;
    		this.zCoord = par1Resource.zCoord;
    }

    public Packet4CollectResource() { } // Be sure to always have the default constructor in your class, or the reflection code will fail!

    @Override
    protected void write(ByteArrayDataOutput out) {
    		out.writeInt(xCoord);
    		out.writeInt(yCoord);
    		out.writeInt(zCoord);
    }

    @Override
    protected void read(ByteArrayDataInput in) {
    	xCoord = in.readInt();
    	yCoord = in.readInt();
    	zCoord = in.readInt();
    }

    @Override
    protected void execute(EntityPlayer player, Side side) {
            if (side.isServer()) {
            	TileEntity te = player.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
            	if (te instanceof TileEntityScourgeResource)
            	{
            		HomeManager.distributeResource(player, (TileEntityScourgeResource)te);
            	}
            } 
    }
}