package mods.scourgecraft.tileentity.render;

import mods.scourgecraft.tileentity.TileEntityRaidCenter;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class ModelRaidCenter extends TileEntitySpecialRenderer
{
    private ModelRaidCenterRenderer myModelLevel1;

    public ModelRaidCenter()
    {
        myModelLevel1 = new ModelRaidCenterRenderer();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
    {
        GL11.glPushMatrix();
            GL11.glTranslated(x, y, z);
            myModelLevel1.render();
        GL11.glPopMatrix();

    }

}
