package mods.scourgecraft.gen;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import mods.scourgecraft.ScourgeCraftCore;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class GenerateHome implements IWorldGenerator 
{
	
	public void generateSurface(World world, Random random, int i, int j)
	{
	  BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(i, j);

	   for(int k = 0; k < 10; k++)
	   {
		   boolean canSpawn = true;
	        int randPosX = i + random.nextInt(16);
	        int randPosY = random.nextInt(128);
	        int randPosZ = j + random.nextInt(16);
	        
	        if (world.canBlockSeeTheSky(randPosX, randPosY, randPosZ) && 
	        		(world.getBlockId(randPosX, randPosY - 1, randPosZ) == Block.sand.blockID ||
	        		world.getBlockId(randPosX, randPosY - 1, randPosZ) == Block.grass.blockID ||
	        		world.getBlockId(randPosX, randPosY - 1, randPosZ) == Block.dirt.blockID || 
	        		world.getBlockId(randPosX, randPosY - 1, randPosZ) == Block.snow.blockID))
	        {
	        	for (int x = randPosX - 25; x < randPosX + 25; x++)
	        	{
	        		for (int z = randPosZ - 25; z < randPosZ + 25; z++)
		        	{
		        		int b = world.getBlockId(x, randPosY, z);
		        		if (b == ScourgeCraftCore.configBlocks.homeHall.blockID)
		        			canSpawn = false;
		        	}
	        	}
		        
		        if (canSpawn)
		        	world.setBlock(randPosX, randPosY, randPosZ, ScourgeCraftCore.configBlocks.homeHall.blockID);
	        }
	        
	   }
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		switch(world.provider.dimensionId) {
		case -1: 
			break;
		case 0: 
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1: 
			break;
		default:
			break;
		}
		
	}

}
