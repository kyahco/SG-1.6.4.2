package mods.scourgecraft.tileentity.render;

import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import mods.scourgecraft.item.render.ModelDefenseCannonRenderer;
import mods.scourgecraft.tileentity.TileEntityDefenseCannon;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;


/**
 * Created by kyahco on 1/27/14.
 */
public class ModelDefenseCannon extends TileEntitySpecialRenderer{

    private ModelDefenseCannonRenderer modelDefenseCannon;

    public ModelDefenseCannon()
    {
        modelDefenseCannon = new ModelDefenseCannonRenderer();// add Cannon model
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);// stops face culling
        GL11.glTranslated(x, y, z);
        modelDefenseCannon.render();// renders platform cannon stands on does not get rotated
        if (tileentity instanceof TileEntityDefenseCannon)
        {
            TileEntityDefenseCannon teRot = (TileEntityDefenseCannon)tileentity;//gets rotation for model
            GL11.glTranslatef(0.5F, 0F, 0.5F);// positions the model to the ceneter
            GL11.glRotatef(teRot.getyRot(), 0F, 1F, 0F);// rotates the model on the Y axis, getyRot found in TileEntityDefenseCannon
            GL11.glTranslatef(-0.5F,0F,-0.5F);// repositions the model after rotation
            modelDefenseCannon.renderCannon();// and render cannon parts of model that where just rotated    
        }
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();

    }

}
