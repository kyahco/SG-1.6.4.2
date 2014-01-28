package mods.scourgecraft.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * Created by kyahco on 1/27/14.
 */
public class TileEntityDefenseCannon extends TileEntity {

    private int yRot = 0;// rotation on the y axis
    private static final float RANGE = 20F; //range of cannon

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
            double pPosx = this.worldObj.getClosestPlayer((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, RANGE).posX;
            double pPosz = this.worldObj.getClosestPlayer((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, RANGE).posZ;
            //gets Delta comparison (pretty self evident)
            double deltaX = pPosx - this.xCoord;
            double deltaZ = pPosz - this.zCoord;
            // the magic sauce, gives the rotation (in radiants first that is then converted in-line to degrees)
            this.setyRotot((int)(((Math.atan2(deltaZ,deltaX)*180)/Math.PI)+90)*-1);
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
