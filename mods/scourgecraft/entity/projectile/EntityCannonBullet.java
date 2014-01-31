package mods.scourgecraft.entity.projectile;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityCannonBullet extends EntityThrowable
{
   public EntityCannonBullet(World par1World)
   {
       super(par1World);
   }
   public EntityCannonBullet(World par1World, EntityLiving par2EntityLiving)
   {
       super(par1World, par2EntityLiving);
   }
   public EntityCannonBullet(World par1World, double par2, double par4, double par6)
   {
       super(par1World, par2, par4, par6);
   }
   @Override
   protected void onImpact(MovingObjectPosition movingobjectposition)
   // TODO Auto-generated method stub
   }
}