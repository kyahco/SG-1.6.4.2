package mods.scourgecraft.entity.projectile;

import mods.scourgecraft.WeaponDamageSource;
import mods.scourgecraft.entity.EntityCannon;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityCannonBall extends EntityProjectile
{
  public EntityCannonBall(World world)
  {
    super(world);
  }

  public EntityCannonBall(World world, double d, double d1, double d2)
  {
    this(world);
    setPosition(d, d1, d2);
    this.yOffset = 0.0F;
  }

  public EntityCannonBall(World world, EntityCannon entitycannon, EntityLivingBase target, boolean superPowered)
  {
    this(world);
    this.shootingEntity = entitycannon;
    setSize(0.5F, 0.5F);

    this.posY = entitycannon.posY + (double)entitycannon.getEyeHeight() - 0.10000000149011612D;
    double d0 = target.posX - entitycannon.posX;
    double d1 = target.boundingBox.minY + (double)(target.height / 3.0F) - this.posY;
    double d2 = target.posZ - entitycannon.posZ;
    double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

    if (d3 >= 1.0E-7D)
    {
        float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
        float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
        double d4 = d0 / d3;
        double d5 = d2 / d3;
        this.setLocationAndAngles(entitycannon.posX + d4, this.posY, entitycannon.posZ + d5, f2, f3);
        this.yOffset = 0.0F;
        float f4 = (float)d3 * 0.2F;
        setIsCritical(superPowered);
        this.setThrowableHeading(d0, d1 + (double)f4, d2, superPowered ? 4.0F : 2.0F, superPowered ? 0.1F : 2.0F);
    }
  }

  public void onUpdate()
  {
    super.onUpdate();

    double speed = Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
    double amount = 8.0D;
    if (speed > 1.0D)
    {
      for (int i1 = 1; i1 < amount; i1++)
      {
        this.worldObj.spawnParticle("smoke", this.posX + this.motionX * i1 / amount, this.posY + this.motionY * i1 / amount, this.posZ + this.motionZ * i1 / amount, 0.0D, 0.0D, 0.0D);
      }
    }
  }

  public void createCrater()
  {
    if ((this.worldObj.isRemote) || (!this.inGround) || (isInWater())) 
    	return;

    setDead();
  }

  public void onEntityHit(Entity entity)
  {
    DamageSource damagesource = null;
    if (this.shootingEntity == null)
    {
      damagesource = WeaponDamageSource.causeProjectileWeaponDamage(this, this);
    }
    else {
      damagesource = WeaponDamageSource.causeProjectileWeaponDamage(this, this.shootingEntity);
    }
    if (entity.attackEntityFrom(damagesource, 30.0F))
    {
      this.worldObj.playSoundAtEntity(this, "random.damage.hurtflesh", 1.0F, 1.2F / (this.rand.nextFloat() * 0.4F + 0.7F));
    }
  }

  public void onGroundHit(MovingObjectPosition mop)
  {
    this.xTile = mop.blockX;
    this.yTile = mop.blockY;
    this.zTile = mop.blockZ;
    this.inTile = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
    this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
    this.motionX = ((float)(mop.hitVec.xCoord - this.posX));
    this.motionY = ((float)(mop.hitVec.yCoord - this.posY));
    this.motionZ = ((float)(mop.hitVec.zCoord - this.posZ));
    float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
    this.posX -= this.motionX / f1 * 0.05D;
    this.posY -= this.motionY / f1 * 0.05D;
    this.posZ -= this.motionZ / f1 * 0.05D;
    this.inGround = true;

    if (this.inTile != 0)
    {
      Block.blocksList[this.inTile].onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
    }

    createCrater();
  }

  public boolean canBeCritical()
  {
    return true;
  }

  public float getAirResistance()
  {
    return 0.98F;
  }

  public float getGravity()
  {
    return 0.04F;
  }

  public ItemStack getPickupItem()
  {
    return null;
  }

  public float getShadowSize()
  {
    return 0.5F;
  }
}