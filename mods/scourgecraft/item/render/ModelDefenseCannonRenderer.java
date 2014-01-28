package mods.scourgecraft.item.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;


/**
 * Created by kyahco on 1/27/14.
 */
@SideOnly(Side.CLIENT)
public class ModelDefenseCannonRenderer {

    private IModelCustom modelDefenseCannon;

    public ModelDefenseCannonRenderer()
    {
        modelDefenseCannon = AdvancedModelLoader.loadModel("/assets/scourgecraft/textures/model/cannon.obj");
    }
    
    //renders just the stand and the cannon so the base does not rotate.
    public void renderCannon()
    {
    	modelDefenseCannon.renderPart("cannon_Cube001");
    	modelDefenseCannon.renderPart("stand_Cube002");
    }

    public void render()
    {
        //Minecraft.getMinecraft().renderEngine.bindTexture(ModelResourceFile.); TODO: add texture!
        modelDefenseCannon.renderPart("platform_Cube");
    }
}
