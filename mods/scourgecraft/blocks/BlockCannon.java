package mods.scourgecraft.blocks;

import mods.scourgecraft.tileentity.TileEntityCannon;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCannon extends BlockContainer {

	public BlockCannon(int par1, Material par2Material) {
		super(par1, par2Material);
		
		setHardness(50.0F); 
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new TileEntityCannon();
	}

}
