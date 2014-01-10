package mods.scourgecraft.player;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class PlayerEventListener {

	@ForgeSubscribe
    public void livingDies(LivingDeathEvent event)
    {
    }
	
	@ForgeSubscribe
	public void onEntityLivingDeath(LivingDeathEvent event)
	{
	}
	
	@ForgeSubscribe
	public void entityAttacked(LivingAttackEvent event)
	{
		Entity attackedEnt = event.entity;
		DamageSource attackSource = event.source;
		
	}

	public void onPlayerRespawn(EntityPlayer player) {
	}
	
	@ForgeSubscribe
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			if (event.entity.getExtendedProperties("ExtendedProps") == null) {
				System.out.println("[ScourgeCraft] Registering extended properties.");
				event.entity.registerExtendedProperties("ExtendedProps", new ExtendedPlayer((EntityPlayer)event.entity));
				if (event.entity.getExtendedProperties("ExtendedProps") != null)
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