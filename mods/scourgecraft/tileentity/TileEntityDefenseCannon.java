package mods.scourgecraft.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.entity.projectile.EntitySnowball;

/**
 * Created by kyahco on 1/27/14.
 */
public class TileEntityDefenseCannon extends TileEntity {

    private int yRot = 0;// rotation on the y axis
    private static final float RANGE = 20F; //range of cannon
    private int timer = 0;

    public TileEntityDefenseCannon()
    {

    }

    //detects if player is in range of cannon
    public boolean playerInRange()
    {
        return this.worldObj.getClosestPlayer((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, RANGE) != null;
    }

    @Override
    public void updateEntity()
    {
        if(playerInRange())
        {
        	//gets X and Z of closest player
            double pPosx = this.worldObj.getClosestPlayer((double)this.xCoord + 0.5D, (double)this.yCoord + 1.3D, (double)this.zCoord + 0.5D, RANGE).posX-0.5;
            double pPosz = this.worldObj.getClosestPlayer((double)this.xCoord + 0.5D, (double)this.yCoord + 1.3D, (double)this.zCoord + 0.5D, RANGE).posZ-0.5;
            //gets Delta comparison (pretty self evident)
            double deltaX = pPosx - this.xCoord;
            double deltaZ = pPosz - this.zCoord;
            // the magic sauce, gives the rotation (in radiants first that is then converted in-line to degrees)
            this.setyRotot((int)((Math.toDegrees((Math.atan2(deltaZ,deltaX)))+90)*-1));
            
            if(timer >= 60)
            {
            	EntitySnowball entpro = new EntitySnowball(this.getWorldObj(), this.xCoord+0.5D, this.yCoord+ 1.3D, this.zCoord+0.5D);
            	entpro.motionX += Math.cos(Math.toRadians((this.getyRot()+90)*-1));
            	entpro.motionZ += Math.sin(Math.toRadians((this.getyRot()+90)*-1));
            	entpro.setThrowableHeading(entpro.motionX, entpro.motionY, entpro.motionZ, 1.0F, 1.0F);
            	this.worldObj.spawnEntityInWorld(entpro);
            	timer = 0;
            }
            timer++;
        }
    }

    public void setyRotot(int Xrot)
    {
        yRot = Xrot;
    }

    public int getyRot()
    {
        return this.yRot;
    }
}
