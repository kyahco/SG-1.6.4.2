package mods.scourgecraft.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.entity.EntityCannon;
import mods.scourgecraft.player.ExtendedPlayer;
import mods.scourgecraft.tick.ServerTickHandler;
import mods.scourgecraft.tileentity.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy implements IGuiHandler {
	
	private static final Map<String, ExtendedPlayer> extendedEntityData = new HashMap<String, ExtendedPlayer>();
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	public void registerHandlers()
    {
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
		
		EntityRegistry.registerGlobalEntityID(EntityCannon.class, "ECannon", EntityRegistry.findGlobalUniqueEntityId());
		LanguageRegistry.instance().addStringLocalization("entity.ECannon.name", "en_US", "Defense Cannon");
		
    	GameRegistry.registerTileEntity(TileEntityScourgeBuilding.class, ScourgeCraftCore.modid + "SCTileEntity");
    	GameRegistry.registerTileEntity(TileEntityHomeHall.class, ScourgeCraftCore.modid + "TEHomeHall");
    	GameRegistry.registerTileEntity(TileEntityGoldProducer.class, ScourgeCraftCore.modid + "TEGoldProducer");
    	GameRegistry.registerTileEntity(TileEntityGoldStorage.class, ScourgeCraftCore.modid + "TEGoldStorage");
    	GameRegistry.registerTileEntity(TileEntityCannon.class, ScourgeCraftCore.modid + "TECannon");
        GameRegistry.registerTileEntity(TileEntityRaidCenter.class, ScourgeCraftCore.modid + "TERaidCenter");
    }
	
	public File getMinecraftDir()
    {
        return new File(".");
    }
	

	/**
	* Adds an entity's custom data to the map for temporary storage
	* @param compound An NBT Tag Compound that stores the IExtendedEntityProperties data only
	*/
	public static void storeEntityData(String name, ExtendedPlayer compound)
	{
		extendedEntityData.put(name, compound);
	}
		
	/**
	* Removes the compound from the map and returns the NBT tag stored for name or null if none exists
	*/
	public static ExtendedPlayer getEntityData(String name)
	{
		return extendedEntityData.remove(name);
	}
	
	public static boolean containsEntityData(String name)
	{
		return extendedEntityData.containsKey(name);
	}
}
