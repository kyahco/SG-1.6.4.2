package mods.scourgecraft.tileentity.render;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

/**
 * Created by kyahco on 1/26/14.
 */
public class ModelHomeHallRenderer {

    private IModelCustom modelHomeHall;

    public ModelHomeHallRenderer()
    {
        modelHomeHall = AdvancedModelLoader.loadModel("/assets/scourgecraft/textures/model/hometable1.obj");
    }

    public void render()
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(ModelResourceFile.homeTable1);
        modelHomeHall.renderAll();
    }
}
