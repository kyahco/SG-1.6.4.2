package mods.scourgecraft.network.packet;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;
import mods.scourgecraft.Home;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.data.PermissionManager;
import mods.scourgecraft.network.ClientProxy;
import mods.scourgecraft.network.ScourgePacket;
import mods.scourgecraft.player.ExtendedPlayer;

public class Packet1HomeInfo extends ScourgePacket {

	private Home home;
   
    public Packet1HomeInfo(Home par1Home) {
    		this.home = par1Home;
    }

    public Packet1HomeInfo() { } // Be sure to always have the default constructor in your class, or the reflection code will fail!

    @Override
    protected void write(ByteArrayDataOutput out) {
    		out.writeUTF(home.name);
    		out.writeUTF(home.ownerUsername);
    		out.writeInt(home.level);
    		out.writeInt(home.xCoord);
    		out.writeInt(home.yCoord);
    		out.writeInt(home.zCoord);
    }

    @Override
    protected void read(ByteArrayDataInput in) {
    	home = new Home();
    	home.name = in.readUTF();
    	home.ownerUsername = in.readUTF();
    	home.level = in.readInt();
    	home.xCoord = in.readInt();
    	home.yCoord = in.readInt();
    	home.zCoord = in.readInt();
    }

    @Override
    protected void execute(EntityPlayer player, Side side) {
            if (side.isClient()) {
            	if (home.ownerUsername.equals(player.username))
            	{
            		ExtendedPlayer extPlayer = ExtendedPlayer.getExtendedPlayer(player);
                    if (extPlayer != null)
                    	extPlayer.myHome = home;
            	}
            	PermissionManager.homeList.put(home.ownerUsername, home);
            } 
    }
}