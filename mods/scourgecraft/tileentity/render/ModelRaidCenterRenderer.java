package mods.scourgecraft.tileentity.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelRaidCenterRenderer
{
    private IModelCustom modelRaidCenter;

    public ModelRaidCenterRenderer()
    {
        modelRaidCenter = AdvancedModelLoader.loadModel("/assets/scourgecraft/textures/model/raidtable.obj");
    }

    public void render()
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(ModelResourceFile.raidCenter);
        modelRaidCenter.renderAll();
    }
}
