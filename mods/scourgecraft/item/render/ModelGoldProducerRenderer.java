package mods.scourgecraft.item.render;

import org.lwjgl.opengl.GL11;

import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.tileentity.TileEntityGoldProducer;
import mods.scourgecraft.tileentity.render.ModelResourceFile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelGoldProducerRenderer
{
    private IModelCustom modelGoldProducer;

    public ModelGoldProducerRenderer()
    {
        modelGoldProducer = AdvancedModelLoader.loadModel("/assets/scourgecraft/textures/model/goldproducer.obj");
    }

    public void render()
    {
    	Minecraft.getMinecraft().renderEngine.bindTexture(ModelResourceFile.goldProducer1);
        modelGoldProducer.renderAll();
    }
}
