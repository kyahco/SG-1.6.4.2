package mods.scourgecraft.tileentity;


import mods.scourgecraft.entity.EntityCannon;
import net.minecraft.entity.player.EntityPlayer;

public class TileEntityCannon extends TileEntityScourgeBuilding {

	private EntityCannon blaze;
	
	public TileEntityCannon() {
		super(100);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (!worldObj.isRemote)
		{
		EntityPlayer player = worldObj.getClosestPlayer(this.xCoord, this.yCoord, this.zCoord, 5.0F);
		if (player != null && isCompleted())
		{

			if (blaze == null)
			{
				blaze = new EntityCannon(worldObj);
				blaze.forceSpawn = true;
				blaze.setPosition(this.xCoord + .5, this.yCoord + 1.5, this.zCoord + .5);
				blaze.setTarget(player);
				worldObj.spawnEntityInWorld(blaze);
			}
			if (blaze.isDead)
				blaze = null;
		}
		}
	}

}
