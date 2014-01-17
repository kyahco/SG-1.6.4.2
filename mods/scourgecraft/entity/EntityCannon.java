package mods.scourgecraft.entity;

import mods.scourgecraft.entity.ai.EntityAICannonAttack;
import mods.scourgecraft.entity.projectile.EntityCannonBall;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityCannon  extends EntityMob implements IRangedAttackMob {
	
	private EntityAICannonAttack aiCannonAttack = new EntityAICannonAttack(this, 1.0D, 20, 60, 15.0F);
	
	 public EntityCannon(World world)
	  {
	    super(world);
	    this.preventEntitySpawning = true;
	    this.rotationPitch = -20.F;
	    this.setRotation(this.rotationYaw, this.rotationPitch);
	    this.setSize(1.5F, 1.0F);
	    this.yOffset = this.height / 2.0F;
	    
        //this.tasks.addTask(1, this.aiCannonAttack);
	    this.tasks.addTask(2, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	  }

	  public EntityCannon(World world, double d, double d1, double d2)
	  {
	    this(world);
	    setPosition(d, d1 + yOffset, d2);
	    this.motionX = 0.0D;
	    this.motionY = 0.0D;
	    this.motionZ = 0.0D;
	    this.prevPosX = d;
	    this.prevPosY = d1;
	    this.prevPosZ = d2;
	  }

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entitylivingbase,
			float f) 
	{
			//if (!isLoaded()) 
				//return;
		
			if (!this.worldObj.isRemote)
			{
				EntityCannonBall entitycannonball = new EntityCannonBall(this.worldObj, this, entitylivingbase, isSuperPowered());
				this.worldObj.spawnEntityInWorld(entitycannonball);
			}

			//setReloadInfo(false, 0);

			fireEffects();
	  }

	  public void fireEffects()
	  {
	    //this.worldObj.playSoundAtEntity(this, "random.explode", 8.0F, 1.0F / (this.rand.nextFloat() * 0.8F + 0.9F));
	    //this.worldObj.playSoundAtEntity(this, "ambient.weather.thunder", 8.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.6F));

	    float yaw = (float)Math.toRadians(this.rotationYaw);
	    double d = -MathHelper.sin(yaw) * -1.0F;
	    double d1 = MathHelper.cos(yaw) * -1.0F;
	    for (int i = 0; i < 20; i++)
	    {
	      this.worldObj.spawnParticle("smoke", this.posX + d + this.rand.nextDouble() * 0.5D - 0.25D, this.posY + this.rand.nextDouble() * 0.5D, this.posZ + d1 + this.rand.nextDouble() * 0.5D - 0.25D, this.rand.nextDouble() * 0.1D - 0.05D, this.rand.nextDouble() * 0.1D - 0.05D, this.rand.nextDouble() * 0.1D - 0.05D);
	    }

	    if (this.riddenByEntity != null)
	    {
	      this.riddenByEntity.rotationPitch += 10.0F;
	    }
	    attackEntityFrom(DamageSource.generic, 2.0F);
	  }
	
	/**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }
	
    protected void collideWithEntity(Entity par1Entity) {}

    protected void collideWithNearbyEntities() {}

	@Override
	protected boolean isMovementCeased()
    {
        return true;
    }

	  
	  public AxisAlignedBB getCollisionBox(Entity entity)
	  {
		  return entity.boundingBox;
	  }
	  
	  public AxisAlignedBB getBoundingBox()
	  {
	    return this.boundingBox;
	  }

	
	@Override
	public boolean canBePushed()
    {
        return false;
    }
	
	@Override
	public boolean isPushedByWater()
    {
        return false;
    }
	
	@Override
	/**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (ForgeHooks.onLivingAttack(this, par1DamageSource, par2)) return false;
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (this.worldObj.isRemote)
        {
            return false;
        }
        else
        {
            this.entityAge = 0;

            if (this.getHealth() <= 0.0F)
            {
                return false;
            }
            else if (par1DamageSource.isFireDamage() && this.isPotionActive(Potion.fireResistance))
            {
                return false;
            }
            else
            {
                if ((par1DamageSource == DamageSource.anvil || par1DamageSource == DamageSource.fallingBlock) && this.getCurrentItemOrArmor(4) != null)
                {
                    this.getCurrentItemOrArmor(4).damageItem((int)(par2 * 4.0F + this.rand.nextFloat() * par2 * 2.0F), this);
                    par2 *= 0.75F;
                }

                this.limbSwingAmount = 1.5F;
                boolean flag = true;

                if ((float)this.hurtResistantTime > (float)this.maxHurtResistantTime / 2.0F)
                {
                    if (par2 <= this.lastDamage)
                    {
                        return false;
                    }

                    this.damageEntity(par1DamageSource, par2 - this.lastDamage);
                    this.lastDamage = par2;
                    flag = false;
                }
                else
                {
                    this.lastDamage = par2;
                    this.prevHealth = this.getHealth();
                    this.hurtResistantTime = this.maxHurtResistantTime;
                    this.damageEntity(par1DamageSource, par2);
                    this.hurtTime = this.maxHurtTime = 10;
                }

                this.attackedAtYaw = 0.0F;
                Entity entity = par1DamageSource.getEntity();

                if (entity != null)
                {
                    if (entity instanceof EntityLivingBase)
                    {
                        this.setRevengeTarget((EntityLivingBase)entity);
                    }

                    if (entity instanceof EntityPlayer)
                    {
                        this.recentlyHit = 100;
                        this.attackingPlayer = (EntityPlayer)entity;
                    }
                    else if (entity instanceof EntityWolf)
                    {
                        EntityWolf entitywolf = (EntityWolf)entity;

                        if (entitywolf.isTamed())
                        {
                            this.recentlyHit = 100;
                            this.attackingPlayer = null;
                        }
                    }
                }

                if (flag)
                {
                    this.worldObj.setEntityState(this, (byte)2);

                    if (par1DamageSource != DamageSource.drown)
                    {
                        this.setBeenAttacked();
                    }

                    if (entity != null)
                    {
                        double d0 = entity.posX - this.posX;
                        double d1;

                        for (d1 = entity.posZ - this.posZ; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D)
                        {
                            d0 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.attackedAtYaw = (float)(Math.atan2(d1, d0) * 180.0D / Math.PI) - this.rotationYaw;
                    }
                    else
                    {
                        this.attackedAtYaw = (float)((int)(Math.random() * 2.0D) * 180);
                    }
                }

                if (this.getHealth() <= 0.0F)
                {
                    if (flag)
                    {
                        this.playSound(this.getDeathSound(), this.getSoundVolume(), this.getSoundPitch());
                    }

                    this.onDeath(par1DamageSource);
                }
                else if (flag)
                {
                    this.playSound(this.getHurtSound(), this.getSoundVolume(), this.getSoundPitch());
                }

                return true;
            }
        }
    }

	public float getCurrentDamage() {
		// TODO Auto-generated method stub
		return 1;
	}

	public float getRockDirection() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isSuperPowered() {
		// TODO Auto-generated method stub
		return false;
	}
}
