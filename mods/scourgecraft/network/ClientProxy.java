package mods.scourgecraft.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;

import mods.scourgecraft.player.ExtendedPlayer;
import mods.scourgecraft.tick.PlayerOverlayGUI;
import mods.scourgecraft.tick.PlayerTickHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy 
{
	public static PlayerTickHandler playerTickHandler = new PlayerTickHandler();
	public static PlayerOverlayGUI playerOverlayGUI = new PlayerOverlayGUI();
	
	@Override
    public void registerHandlers()
    {
            super.registerHandlers();
            TickRegistry.registerTickHandler(playerTickHandler, Side.CLIENT);
            TickRegistry.registerTickHandler(playerOverlayGUI, Side.CLIENT);
    }
	
	public File getMinecraftDir()
    {
        return Minecraft.getMinecraft().mcDataDir;
    }
	
}