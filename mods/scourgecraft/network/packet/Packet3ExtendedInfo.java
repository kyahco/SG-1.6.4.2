package mods.scourgecraft.network.packet;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;
import mods.scourgecraft.Home;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.network.ClientProxy;
import mods.scourgecraft.network.ScourgePacket;
import mods.scourgecraft.player.ExtendedPlayer;

public class Packet3ExtendedInfo extends ScourgePacket {

	private int vitalitiy;
   
    public Packet3ExtendedInfo(ExtendedPlayer player) {
    	vitalitiy = player.getVitality();
    }

    public Packet3ExtendedInfo() { } // Be sure to always have the default constructor in your class, or the reflection code will fail!

    @Override
    protected void write(ByteArrayDataOutput out) {
    		out.writeInt(vitalitiy);
    }

    @Override
    protected void read(ByteArrayDataInput in) {
    	vitalitiy = in.readInt();
    }

    @Override
    protected void execute(EntityPlayer player, Side side) {
            if (side.isClient()) {
            		ExtendedPlayer par1Player = ExtendedPlayer.getExtendedPlayer(player);
                    if (par1Player != null)
                    	par1Player.setVitality(vitalitiy);
            } 
    }
}