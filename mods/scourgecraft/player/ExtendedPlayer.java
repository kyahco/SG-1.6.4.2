package mods.scourgecraft.player;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import mods.scourgecraft.Home;
import mods.scourgecraft.Raid;
import mods.scourgecraft.network.packet.Packet3ExtendedInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties
{
	public Home myHome;
	public Raid myRaid;

	// I always include the entity to which the properties belong for easy access
	// It's final because we won't be changing which player it is
	private final EntityPlayer player;
	
	private int vitality;
	

	public ExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
	}

	public static ExtendedPlayer getExtendedPlayer(EntityPlayer player)
	{
		return (ExtendedPlayer) player.getExtendedProperties("ExtendedProps");
	}
	
	public final boolean consumeVitality(int amount)
	{
		// This variable makes it easier to write the rest of the method
		int vit = vitality;

		// These two lines are the same as before
		boolean sufficient = amount <= vit;
		vit -= (amount < vit ? amount : vit);

		vitality = vit;

		return sufficient;
	}

	public final void addVitality(int amount)
	{
		vitality += amount;
	}
	
	public final int getVitality()
	{
		return vitality;
	}
	
	public final void setVitality(int vit)
	{
		vitality = vit;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setInteger("Vitality", this.vitality);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		this.vitality = (compound.getInteger("Vitality"));
		System.out.println("[ScourgeCraft] Loaded Vitality from NBT. Vitality = " + this.vitality);
	}

	@Override
	public void init(Entity entity, World world) {
		System.out.println("[ScourgeCraft] Initializing extended properties.");
		
	}
	
	public static void playerLogin(EntityPlayer player)
	{
		PacketDispatcher.sendPacketToPlayer(new Packet3ExtendedInfo(ExtendedPlayer.getExtendedPlayer(player)).makePacket(), (Player)player);
	}

}