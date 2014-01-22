package mods.scourgecraft.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;
import mods.scourgecraft.Home;
import mods.scourgecraft.Raid;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.data.HomeManager;
import mods.scourgecraft.data.PermissionManager;
import mods.scourgecraft.data.RaidManager;
import mods.scourgecraft.network.ClientProxy;
import mods.scourgecraft.network.ScourgePacket;
import mods.scourgecraft.player.ExtendedPlayer;

public class Packet6RaidInfo extends ScourgePacket {

	private Raid raid;
	private String attacker;
	private String defender;
   
    public Packet6RaidInfo(Raid par1Raid) {
    		this.raid = par1Raid;
    }

    public Packet6RaidInfo() { } // Be sure to always have the default constructor in your class, or the reflection code will fail!

    @Override
    protected void write(ByteArrayDataOutput out) {
    		out.writeUTF(raid.attacker.username);
    		out.writeUTF(raid.defender.username);
    		out.writeInt(raid.timeLeft);
    		out.writeShort(raid.roundType);
    }

    @Override
    protected void read(ByteArrayDataInput in) {
    	raid = new Raid();
    	attacker = in.readUTF();
    	defender = in.readUTF();
    	raid.timeLeft = in.readInt();
    	raid.roundType = in.readShort();
    }

    @Override
    protected void execute(EntityPlayer player, Side side) {
            if (side.isClient()) {
            	for (Object ws : Minecraft.getMinecraft().thePlayer.worldObj.playerEntities)
            	{
            		EntityPlayer thisPlayer = (EntityPlayer)ws;
            		if (thisPlayer.username.equals(defender))
            			raid.defender = thisPlayer;
            		else if (thisPlayer.username.equals(attacker))
            			raid.attacker = thisPlayer;
            		if (raid.defender != null && raid.attacker != null) //We have both values, leave loop.
            			break;
            	}
            	raid.attackerHome = HomeManager.getHomeByPlayerName(raid.attacker.username);
            	raid.defenderHome = HomeManager.getHomeByPlayerName(raid.defender.username);
            	if (raid.attacker.username.equals(player.username) || 
            			raid.defender.username.equals(player.username))
            		ExtendedPlayer.getExtendedPlayer(player).myRaid = raid;
            	
            	RaidManager.raidList.put(attacker, raid);
            } 
    }
}