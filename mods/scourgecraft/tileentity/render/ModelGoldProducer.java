package mods.scourgecraft.tileentity.render;

import org.lwjgl.opengl.GL11;

import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.tileentity.TileEntityGoldProducer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelGoldProducer extends TileEntitySpecialRenderer
{
	private IModelCustom myModelLevel1;
	
	public ModelGoldProducer()
	{
		myModelLevel1 = AdvancedModelLoader.loadModel("/assets/scourgecraft/textures/model/goldproducer.obj");
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        if (tileentity instanceof TileEntityGoldProducer)
        {
        	TileEntityGoldProducer teHome = (TileEntityGoldProducer)tileentity;
        	switch (teHome.getLevel())
        	{
        		case 1:
        		{
                    Minecraft.getMinecraft().renderEngine.bindTexture(ModelResourceFile.goldProducer1);
                    myModelLevel1.renderAll();
        			break;
        		}
        	}
        }
        GL11.glPopMatrix();
		
	}

}