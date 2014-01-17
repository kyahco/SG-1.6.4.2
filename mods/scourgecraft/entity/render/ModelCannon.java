package mods.scourgecraft.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCannon extends ModelBase
{
  public ModelRenderer swiwel_front;
  public ModelRenderer swivel_back;
  public ModelRenderer swivel_main;
  public ModelRenderer axis;
  public ModelRenderer seal;
  public ModelRenderer handcrap;
  public ModelRenderer fronttip;
  public ModelRenderer backtip;
  public ModelRenderer console_main_;
  public ModelRenderer console_side_l1;
  public ModelRenderer console_side_r1;
  public ModelRenderer base_1;
  public ModelRenderer base_2;
  public ModelRenderer base_stand;
  public ModelRenderer axis1;

  public ModelCannon()
  {
    this.textureHeight = 32;
    this.textureWidth = 32;

    this.swiwel_front = new ModelRenderer(this, 12, 12);
    this.swiwel_front.addBox(-2.0F, -2.0F, -11.0F, 4, 4, 2);
    this.swiwel_front.setRotationPoint(0.0F, 16.0F, 0.0F);
    this.swiwel_front.setTextureSize(32, 32);
    this.swiwel_front.mirror = true;
    setRotation(this.swiwel_front, 0.0F, 0.0F, 0.0F);
    this.swivel_back = new ModelRenderer(this, 12, 0);
    this.swivel_back.addBox(-2.0F, -0.5F, -2.0F, 4, 8, 4);
    this.swivel_back.setRotationPoint(0.0F, 16.0F, 0.0F);
    this.swivel_back.setTextureSize(32, 32);
    this.swivel_back.mirror = true;
    setRotation(this.swivel_back, 1.570796F, 0.0F, 0.0F);
    this.swivel_main = new ModelRenderer(this, 0, 0);
    this.swivel_main.addBox(-1.5F, -11.5F, -1.5F, 3, 20, 3);
    this.swivel_main.setRotationPoint(0.0F, 16.0F, 0.0F);
    this.swivel_main.setTextureSize(32, 32);
    this.swivel_main.mirror = true;
    setRotation(this.swivel_main, 1.570796F, 0.0F, 0.0F);
    this.axis = new ModelRenderer(this, 12, 18);
    this.axis.addBox(-4.0F, -0.5F, -0.5F, 8, 1, 1);
    this.axis.setRotationPoint(0.0F, 16.0F, 0.0F);
    this.axis.setTextureSize(32, 32);
    this.axis.mirror = true;
    setRotation(this.axis, 1.570796F, 0.0F, 0.0F);
    this.seal = new ModelRenderer(this, 9, 0);
    this.seal.addBox(-1.0F, -2.5F, 5.5F, 2, 1, 1);
    this.seal.setRotationPoint(0.0F, 16.0F, 0.0F);
    this.seal.setTextureSize(32, 32);
    this.seal.mirror = true;
    setRotation(this.seal, 0.0F, 0.0F, 0.0F);
    this.handcrap = new ModelRenderer(this, 28, 0);
    this.handcrap.addBox(-0.5F, 8.5F, -0.5F, 1, 7, 1);
    this.handcrap.setRotationPoint(0.0F, 16.0F, 0.0F);
    this.handcrap.setTextureSize(32, 32);
    this.handcrap.mirror = true;
    setRotation(this.handcrap, 1.570796F, 0.0F, 0.0F);
    this.fronttip = new ModelRenderer(this, 24, 12);
    this.fronttip.addBox(-1.0F, 9.0F, -1.0F, 2, 1, 2);
    this.fronttip.setRotationPoint(0.0F, 16.0F, 0.0F);
    this.fronttip.setTextureSize(32, 32);
    this.fronttip.mirror = true;
    setRotation(this.fronttip, 1.570796F, 0.0F, 0.0F);
    this.backtip = new ModelRenderer(this, 24, 12);
    this.backtip.addBox(-1.0F, 15.5F, -1.0F, 2, 1, 2);
    this.backtip.setRotationPoint(0.0F, 16.0F, 0.0F);
    this.backtip.setTextureSize(32, 32);
    this.backtip.mirror = true;
    setRotation(this.backtip, 1.570796F, 0.0F, 0.0F);
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

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.swiwel_front.render(f5);
    this.swivel_back.render(f5);
    this.swivel_main.render(f5);
    this.axis.render(f5);
    this.seal.render(f5);
    this.handcrap.render(f5);
    this.fronttip.render(f5);
    this.backtip.render(f5);
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