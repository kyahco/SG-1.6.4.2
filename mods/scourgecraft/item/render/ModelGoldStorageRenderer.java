package mods.scourgecraft.item.render;

import mods.scourgecraft.tileentity.render.ModelResourceFile;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelGoldStorageRenderer
{
    private IModelCustom modelGoldstorage;

    public ModelGoldStorageRenderer()
    {
        modelGoldstorage = AdvancedModelLoader.loadModel("/assets/scourgecraft/textures/model/goldstorage.obj");
    }

    public void render()
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(ModelResourceFile.goldStorage1);
        modelGoldstorage.renderAll();
    }
}