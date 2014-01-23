package mods.scourgecraft.tileentity.render;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelGoldProducerRenderer
{
    private IModelCustom modelGoldProducer;

    public ModelGoldProducerRenderer()
    {
        modelGoldProducer = AdvancedModelLoader.loadModel("/assets/scourgecraft/textures/model/goldproducer.obj");
    }

    public void render()
    {
        modelGoldProducer.renderPart("Cube");
    }
}
