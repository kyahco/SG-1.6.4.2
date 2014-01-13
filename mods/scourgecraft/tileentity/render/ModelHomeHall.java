package mods.scourgecraft.tileentity.render;

import org.lwjgl.opengl.GL11;

import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.tileentity.TileEntityHomeHall;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelHomeHall extends TileEntitySpecialRenderer
{
	private IModelCustom myModelLevel1;
	private IModelCustom myModelLevel2;
	private IModelCustom myModelLevel3;
	private IModelCustom myModelLevel4;
	
	public ModelHomeHall()
	{
		myModelLevel1 = AdvancedModelLoader.loadModel("/assets/scourgecraft/textures/model/hometable1.obj");
		myModelLevel2 = AdvancedModelLoader.loadModel("/assets/scourgecraft/textures/model/hometable2.obj");
		myModelLevel3 = AdvancedModelLoader.loadModel("/assets/scourgecraft/textures/model/hometable3.obj");
		myModelLevel4 = AdvancedModelLoader.loadModel("/assets/scourgecraft/textures/model/hometable4.obj");
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        if (tileentity instanceof TileEntityHomeHall)
        {
        	TileEntityHomeHall teHome = (TileEntityHomeHall)tileentity;
        	switch (teHome.getLevel())
        	{
        		case 1:
        		{
                    Minecraft.getMinecraft().renderEngine.bindTexture(ModelResourceFile.homeTable1);
                    myModelLevel1.renderAll();
        			break;
        		}
        		case 2:
        		{
        			Minecraft.getMinecraft().renderEngine.bindTexture(ModelResourceFile.homeTable2);
                    myModelLevel2.renderAll();
        			break;
        		}
        		case 3:
        		{
        			Minecraft.getMinecraft().renderEngine.bindTexture(ModelResourceFile.homeTable3);
                    myModelLevel3.renderAll();
        			break;
        		}
        		case 4:
        		{
        			Minecraft.getMinecraft().renderEngine.bindTexture(ModelResourceFile.homeTable4);
                    myModelLevel4.renderAll();
        			break;
        		}
        	}
        }
        GL11.glPopMatrix();
		
	}

}
