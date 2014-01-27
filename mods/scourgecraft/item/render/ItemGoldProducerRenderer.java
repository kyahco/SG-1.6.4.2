package mods.scourgecraft.item.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import mods.scourgecraft.tileentity.render.ModelResourceFile;

public class ItemGoldProducerRenderer implements IItemRenderer {
	
	private ModelGoldProducerRenderer modelGoldProducer;
		
	public ItemGoldProducerRenderer()
	{
		modelGoldProducer = new ModelGoldProducerRenderer();
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
	        switch (type)
	        {
	            case ENTITY:
	            {
	                renderGoldProducer(0F, -0.5F, 0.0F, -0.5F);
	                return;
	            }
	            case EQUIPPED:
	            {
	            	renderGoldProducer(270F, 0.7F, 0.2F, -0.3F);
	            	return;

	            }
	            case EQUIPPED_FIRST_PERSON:
	            {
	                renderGoldProducer(270F, 0.7F, 0.3F, -0.0F);
	                return;
	            }
	            case INVENTORY:
	            {
	                renderGoldProducer(0F, 0.0F, 0.1F, 0.0F);
	                return;
	            }
	            default:
	            {
	            }
	        }
	}
	
	public void renderGoldProducer(float rot, float x, float y,float z)
	{
		GL11.glPushMatrix();
        	GL11.glDisable(GL11.GL_LIGHTING);
		
			GL11.glScalef(1.1F, 1.1F, 1.1F);
	        GL11.glTranslatef(x, y, z);
	        GL11.glRotatef(rot, 0, 1F, 0);

	        modelGoldProducer.render();

	        GL11.glEnable(GL11.GL_LIGHTING);
	     GL11.glPopMatrix();
	}

}
