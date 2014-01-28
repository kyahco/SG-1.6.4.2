package mods.scourgecraft.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;

import mods.scourgecraft.tileentity.render.ModelDefenseCannon;
import mods.scourgecraft.tileentity.TileEntityDefenseCannon;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.blocks.BlockGoldProducer;
import mods.scourgecraft.config.ConfigBlocks;
import mods.scourgecraft.entity.EntityCannon;
import mods.scourgecraft.entity.projectile.EntityCannonBall;
import mods.scourgecraft.entity.render.RenderCannon;
import mods.scourgecraft.entity.render.RenderCannonBall;
import mods.scourgecraft.item.render.ItemGoldProducerRenderer;
import mods.scourgecraft.item.render.ItemGoldStorageRenderer;
import mods.scourgecraft.item.render.ItemHomeHallRenderer;
import mods.scourgecraft.item.render.ItemRaidCenterRenderer;
import mods.scourgecraft.player.ExtendedPlayer;
import mods.scourgecraft.tick.PlayerOverlayGUI;
import mods.scourgecraft.tick.PlayerTickHandler;
import mods.scourgecraft.tileentity.TileEntityGoldProducer;
import mods.scourgecraft.tileentity.TileEntityGoldStorage;
import mods.scourgecraft.tileentity.TileEntityHomeHall;
import mods.scourgecraft.tileentity.TileEntityRaidCenter;
import mods.scourgecraft.tileentity.TileEntityScourgeBuilding;
import mods.scourgecraft.tileentity.render.ModelGoldProducer;
import mods.scourgecraft.tileentity.render.ModelGoldStorage;
import mods.scourgecraft.tileentity.render.ModelHomeHall;
import mods.scourgecraft.tileentity.render.ModelRaidCenter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
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
            
            MinecraftForgeClient.registerItemRenderer(ConfigBlocks.goldProducerID, new ItemGoldProducerRenderer());
            MinecraftForgeClient.registerItemRenderer(ConfigBlocks.goldStorageID, new ItemGoldStorageRenderer());
            MinecraftForgeClient.registerItemRenderer(ConfigBlocks.raidCenterID, new ItemRaidCenterRenderer());
            MinecraftForgeClient.registerItemRenderer(ConfigBlocks.homeHallID, new ItemHomeHallRenderer());
        	
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHomeHall.class, new ModelHomeHall());
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGoldStorage.class, new ModelGoldStorage());
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGoldProducer.class, new ModelGoldProducer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRaidCenter.class, new ModelRaidCenter());
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDefenseCannon.class, new ModelDefenseCannon());
            
            RenderingRegistry.registerEntityRenderingHandler(EntityCannon.class, new RenderCannon());
            RenderingRegistry.registerEntityRenderingHandler(EntityCannonBall.class, new RenderCannonBall());
    }
	
	public File getMinecraftDir()
    {
        return Minecraft.getMinecraft().mcDataDir;
    }
	
}