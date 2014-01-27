package mods.scourgecraft.item.render;

import cpw.mods.fml.client.FMLClientHandler;
import mods.scourgecraft.tileentity.render.ModelResourceFile;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

/**
* Created by kyahco on 1/26/14.
*/
public class ItemGoldStorageRenderer implements IItemRenderer {

    private ModelGoldStorageRenderer modelGoldstorage;

    public ItemGoldStorageRenderer()
    {
        modelGoldstorage = new ModelGoldStorageRenderer();
    }

    @Override
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
            case ENTITY:
            {
                renderGoldStorage(0F, -0.5F, 0.0F, -0.5F);
                return;
            }
            case EQUIPPED:
            {
                renderGoldStorage(270F, 0.7F, 0.2F, -0.3F);
                return;

            }
            case EQUIPPED_FIRST_PERSON:
            {
                renderGoldStorage(270F, 0.7F, 0.3F, -0.0F);
                return;
            }
            case INVENTORY:
            {
                renderGoldStorage(0F, 0.0F, 0.1F, 0.0F);
                return;
            }
            default:
            {
            }
        }
    }

    public void renderGoldStorage(float rot, float x, float y, float z)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glScalef(1.1F, 1.1F, 1.1F);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(rot, 0, 1F, 0);

        modelGoldstorage.render();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}