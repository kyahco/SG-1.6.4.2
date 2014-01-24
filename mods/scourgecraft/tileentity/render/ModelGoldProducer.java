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
	private ModelGoldProducerRenderer modelGoldProducer;
	
	public ModelGoldProducer()
	{
		modelGoldProducer = new ModelGoldProducerRenderer();
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
                    modelGoldProducer.render();
        			break;
        		}
        	}
        }
        GL11.glPopMatrix();
		
	}

}
