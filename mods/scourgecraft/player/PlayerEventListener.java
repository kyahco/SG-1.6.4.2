package mods.scourgecraft.player;

import java.util.HashMap;

import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.data.HomeManager;
import mods.scourgecraft.data.RaidManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PlayerEventListener {
	public static int VIT_FOR_PLAYER_KILL = 3;
	
	@ForgeSubscribe
    public void livingDies(LivingDeathEvent event)
    {
    }
	
	@ForgeSubscribe
	public void onEntityLivingDeath(LivingDeathEvent event)
	{
		if (event.source.getEntity() instanceof EntityPlayer && event.entity instanceof EntityPlayer)
		{
			ExtendedPlayer extAttacker = ExtendedPlayer.getExtendedPlayer((EntityPlayer)event.source.getEntity());
			
			extAttacker.addVitality(VIT_FOR_PLAYER_KILL);
			((EntityPlayer)event.source.getEntity()).addChatMessage("You have recieved " + VIT_FOR_PLAYER_KILL + " Vitality Points for killing " + ((EntityPlayer)event.entity).username);
		}
		
		if (event.entity instanceof EntityPlayer)
		{
			ExtendedPlayer.saveProxyData((EntityPlayer) event.entity);
		}
	}
	
	@ForgeSubscribe
	public void entityAttacked(LivingAttackEvent event)
	{
		Entity attackedEnt = event.entity;
		DamageSource attackSource = event.source;
		
	}
	

	@ForgeSubscribe
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayer)
			ExtendedPlayer.loadProxyData((EntityPlayer) event.entity);
	}
	
	@ForgeSubscribe
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			if (event.entity.getExtendedProperties(ExtendedPlayer.EXT_PLAYER) == null)
			{
				System.out.println("[ScourgeCraft] Registering extended properties.");
				ExtendedPlayer.register((EntityPlayer)event.entity);
				if (event.entity.getExtendedProperties(ExtendedPlayer.EXT_PLAYER) != null)
				{
					System.out.println("[ScourgeCraft] Extended properties registered successfully.");
				}
			}
			else
			{
				System.out.println("[ScourgeCraft] Extended properties already exist.");
			}
		}
	}
}