package mods.scourgecraft.blocks;

import mods.scourgecraft.tileentity.TileEntityDefenseCannon;
import mods.scourgecraft.tileentity.TileEntityCannon;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDefenseCannon extends BlockContainer{
	
	public BlockDefenseCannon(int par1, Material par2Material) {
		super(par1, par2Material);
		
		setBlockUnbreakable();
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new TileEntityDefenseCannon();
	}
	
	//You don't want the normal render type, or it wont render properly.
	@Override
	public int getRenderType()
	{
	    return -1;
	}
	
	//It's not an opaque cube, so you need this.
	@Override
	public boolean isOpaqueCube() {
	    return false;
	}
	
	//It's not a normal block, so you need this too.
	public boolean renderAsNormalBlock() {
	    return false;
	}
}
