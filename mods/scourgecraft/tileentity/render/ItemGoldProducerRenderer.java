package mods.scourgecraft.tileentity.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ItemGoldProducerRenderer implements IItemRenderer {
	
	private IModelCustom myModelLevel1;
		
	public void ItemHomeHallRenderer()
	{
		myModelLevel1 = AdvancedModelLoader.loadModel("/assets/scourgecraft/textures/model/goldproducer.obj");
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		 GL11.glPushMatrix();
	        GL11.glDisable(GL11.GL_LIGHTING);
	        
	        GL11.glScalef(1.4F, 1.4F, 1.4F);
	        GL11.glTranslatef(-1.0F, -1.675F, 0.0F);
	        GL11.glRotatef(-90F, 1F, 0, 0);

	        FMLClientHandler.instance().getClient().renderEngine.bindTexture(ModelResourceFile.goldProducer1);
	        myModelLevel1.renderAll();

	        GL11.glEnable(GL11.GL_LIGHTING);
	     GL11.glPopMatrix();
	}

}
