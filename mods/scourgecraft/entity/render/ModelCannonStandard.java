package mods.scourgecraft.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCannonStandard extends ModelBase
{
  public ModelRenderer console_main_;
  public ModelRenderer console_side_l1;
  public ModelRenderer console_side_r1;
  public ModelRenderer base_1;
  public ModelRenderer base_2;
  public ModelRenderer base_stand;
  public ModelRenderer axis1;

  public ModelCannonStandard()
  {
    this.textureHeight = 32;
    this.textureWidth = 32;

    this.console_main_ = new ModelRenderer(this, 12, 20);
    this.console_main_.addBox(-2.5F, -1.0F, -1.0F, 5, 1, 2);
    this.console_main_.setRotationPoint(0.0F, 20.0F, 0.0F);
    this.console_main_.setTextureSize(32, 32);
    this.console_main_.mirror = true;
    setRotation(this.console_main_, 0.0F, 0.0F, 0.0F);
    this.console_side_l1 = new ModelRenderer(this, 26, 20);
    this.console_side_l1.addBox(2.5F, -4.0F, -1.0F, 1, 5, 2);
    this.console_side_l1.setRotationPoint(0.0F, 19.0F, 0.0F);
    this.console_side_l1.setTextureSize(32, 32);
    this.console_side_l1.mirror = true;
    setRotation(this.console_side_l1, 0.0F, 0.0F, 0.0F);
    this.console_side_r1 = new ModelRenderer(this, 26, 20);
    this.console_side_r1.addBox(-3.5F, -4.0F, -1.0F, 1, 5, 2);
    this.console_side_r1.setRotationPoint(0.0F, 19.0F, 0.0F);
    this.console_side_r1.setTextureSize(32, 32);
    this.console_side_r1.mirror = true;
    setRotation(this.console_side_r1, 0.0F, 0.0F, 0.0F);
    this.base_1 = new ModelRenderer(this, 0, 26);
    this.base_1.addBox(-2.0F, -2.0F, -2.0F, 4, 2, 4);
    this.base_1.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.base_1.setTextureSize(32, 32);
    this.base_1.mirror = true;
    setRotation(this.base_1, 0.0F, 0.0F, 0.0F);
    this.base_2 = new ModelRenderer(this, 16, 28);
    this.base_2.addBox(-1.5F, -3.0F, -1.5F, 3, 1, 3);
    this.base_2.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.base_2.setTextureSize(32, 32);
    this.base_2.mirror = true;
    setRotation(this.base_2, 0.0F, 0.0F, 0.0F);
    this.base_stand = new ModelRenderer(this, 0, 23);
    this.base_stand.addBox(-1.0F, -4.0F, -1.0F, 2, 1, 2);
    this.base_stand.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.base_stand.setTextureSize(32, 32);
    this.base_stand.mirror = true;
    setRotation(this.base_stand, 0.0F, 0.0F, 0.0F);
    this.axis1 = new ModelRenderer(this, 22, 23);
    this.axis1.addBox(-0.5F, -5.5F, -0.5F, 1, 3, 1);
    this.axis1.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.axis1.setTextureSize(32, 32);
    this.axis1.mirror = true;
    setRotation(this.axis1, 0.0F, 0.0F, 0.0F);
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.console_main_.render(f5);
    this.console_side_l1.render(f5);
    this.console_side_r1.render(f5);
    this.base_1.render(f5);
    this.base_2.render(f5);
    this.base_stand.render(f5);
    this.axis1.render(f5);
  }

  public void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  @Override
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}