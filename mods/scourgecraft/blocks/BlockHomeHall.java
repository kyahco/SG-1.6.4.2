package mods.scourgecraft.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.data.PermissionManager;
import mods.scourgecraft.tileentity.TileEntityScourgeBuilding;
import mods.scourgecraft.tileentity.TileEntityHomeHall;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockHomeHall extends BlockContainer {

	public static int DISTANCE_BETWEEN_HOMES = 25;
	
	public BlockHomeHall(int par1) {
        super(par1, Material.iron);
	}

	/**
	* When this method is called, your block should register all the icons it needs with the given IconRegister. This
	* * is the only chance you get to register icons.
	*/
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(ScourgeCraftCore.modid + ":" + "homeHall");
	}
	
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
	}
	
	/**
	* Called upon block activation (right click on the block.)
	*/
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
	    if (par1World.isRemote)
	    {
	        return true;
	    }
	    else
	    {
	    	TileEntityHomeHall teHome = (TileEntityHomeHall)par1World.getBlockTileEntity(par2, par3, par4);
	    	
	    	if (teHome.isCompleted())
	    	{
	    		par5EntityPlayer.openGui(ScourgeCraftCore.instance, 0, par1World, par2, par3, par4);
	    	}
	    	else
	    		par5EntityPlayer.openGui(ScourgeCraftCore.instance, 1, par1World, par2, par3, par4);
	    	
	        return true;
	    }
	}
	
	
	@Override
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }
	
	@Override
	public boolean canDropFromExplosion(Explosion par1Explosion)
	{
		return false;
	}
	
	public int getMobilityFlag()
    {
		return 2; //Do not allow to be moved by Pistons or such.
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityHomeHall();
	}
}
