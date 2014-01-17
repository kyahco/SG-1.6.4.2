package mods.scourgecraft.entity.render;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import mods.scourgecraft.entity.EntityCannon;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderCannon extends Render
{
  protected ModelCannon modelCannon;
  protected ModelCannonBarrel modelBarrel;
  protected ModelCannonStandard modelStandard;
  private Random rand;

  public RenderCannon()
  {
    this.shadowSize = 1.0F;
    this.modelCannon = new ModelCannon();
    this.modelBarrel = new ModelCannonBarrel();
    this.modelStandard = new ModelCannonStandard();
    this.rand = new Random();
  }

@Override
public void doRender(Entity entity, double d0, double d1, double d2, float f,
		float f1) {
	EntityCannon entitycannon = (EntityCannon)entity;
	GL11.glPushMatrix();

    float rot = entitycannon.prevRotationPitch + (entitycannon.rotationPitch - entitycannon.prevRotationPitch) * f1;
    rot = Math.min(rot, 20.0F);

    GL11.glTranslatef((float)d0, (float)d1 + 1.9F, (float)d2);
    GL11.glRotatef(-f, 0.0F, 1.0F, 0.0F);
    float f3 = entitycannon.getLastAttackerTime() - f1;
    float f4 = entitycannon.getCurrentDamage() - f1;
    if (f4 < 0.0F)
    {
      f4 = 0.0F;
    }
    if (f3 > 0.0F)
    {
      GL11.glRotatef(MathHelper.sin(f3) * f3 * f4 / 10.0F * entitycannon.getRockDirection() / 5.0F, 0.0F, 0.0F, 1.0F);
    }
    this.bindEntityTexture(entitycannon);
    
    GL11.glScalef(-1.6F, -1.6F, 1.6F);
    if ((entitycannon.isSuperPowered()) && (entitycannon.ticksExisted % 5 < 2))
    {
      float f5 = 1.5F;
      GL11.glColor3f(entitycannon.getBrightness(0.0F) * f5, entitycannon.getBrightness(0.0F) * f5, entitycannon.getBrightness(0.0F) * f5);
    }

    GL11.glPushMatrix();
    GL11.glTranslatef(0.0F, 1.0F, 0.0F);
    GL11.glRotatef(rot, 1.0F, 0.0F, 0.0F);
    GL11.glTranslatef(0.0F, -1.0F, 0.0F);
    this.modelBarrel.render(entitycannon, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    GL11.glPopMatrix();

    float yaw = -(float)Math.toRadians(f);
    this.modelStandard.base_1.rotateAngleY = yaw;
    this.modelStandard.base_2.rotateAngleY = yaw;
    this.modelStandard.base_stand.rotateAngleY = yaw;
    this.modelStandard.render(entitycannon, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    GL11.glPopMatrix();
	
}

		@Override
		protected ResourceLocation getEntityTexture(Entity entity) {
			return RenderResourceGui.renderCannon;
	}
}