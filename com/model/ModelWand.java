
package com.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWand extends ModelBase
{
	
	public ModelRenderer parts[] = new ModelRenderer[7];

  //fields
    ModelRenderer Handle;
    ModelRenderer Main_Structure;
    ModelRenderer Holder1;
    ModelRenderer Holder2;
    ModelRenderer Holder3;
    ModelRenderer Holder_4;
    ModelRenderer Ball_of_magic;
  
  public ModelWand()
  {
    textureWidth = 64;
    textureHeight = 32;
    
    parts[6] = new ModelRenderer(this, 14, 0);
    parts[6].addBox(0F, 0F, 0F, 1, 1, 8);
    parts[6].setRotationPoint(-2F, -2F, 0F);
    parts[6].setTextureSize(64, 32);
    parts[6].mirror = true;
    setRotation(parts[6], 0F, 0F, 0F);
    parts[5] = new ModelRenderer(this, 0, 0);
    parts[5].addBox(0F, 0F, 0F, 3, 3, 4);
    parts[5].setRotationPoint(-3F, -3F, -4F);
    parts[5].setTextureSize(64, 32);
    parts[5].mirror = true;
    setRotation(parts[5], 0F, 0F, 0F);
    parts[4] = new ModelRenderer(this, 32, 0);
    parts[4].addBox(0F, 0F, 0F, 1, 1, 5);
    parts[4].setRotationPoint(0F, -2F, -7F);
    parts[4].setTextureSize(64, 32);
    parts[4].mirror = true;
    setRotation(parts[4], 0F, 0F, 0F);
    parts[3] = new ModelRenderer(this, 32, 0);
    parts[3].addBox(0F, 0F, 0F, 1, 1, 5);
    parts[3].setRotationPoint(-4F, -2F, -7F);
    parts[3].setTextureSize(64, 32);
    parts[3].mirror = true;
    setRotation(parts[3], 0F, 0F, 0F);
    parts[2] = new ModelRenderer(this, 32, 0);
    parts[2].addBox(0F, 0F, 0F, 1, 1, 5);
    parts[2].setRotationPoint(-2F, 0F, -7F);
    parts[2].setTextureSize(64, 32);
    parts[2].mirror = true;
    setRotation(parts[2], 0F, 0F, 0F);
    parts[1] = new ModelRenderer(this, 32, 0);
    parts[1].addBox(0F, 0F, 0F, 1, 1, 5);
    parts[1].setRotationPoint(-2F, -4F, -7F);
    parts[1].setTextureSize(64, 32);
    parts[1].mirror = true;
    setRotation(parts[1], 0F, 0F, 0F);
    parts[0] = new ModelRenderer(this, 32, 6);
    parts[0].addBox(0F, 0F, 0F, 1, 1, 1);
    parts[0].setRotationPoint(-2F, -2F, -6F);
    parts[0].setTextureSize(64, 32);
    parts[0].mirror = true;
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Handle.render(f5);
    Main_Structure.render(f5);
    Holder1.render(f5);
    Holder2.render(f5);
    Holder3.render(f5);
    Holder_4.render(f5);
    Ball_of_magic.render(f5);
  }
  
  public void renderAll()
  {
      for (final ModelRenderer nmtmr : this.parts)
      {
          nmtmr.render(0.0625F);
      }
  }

  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}

