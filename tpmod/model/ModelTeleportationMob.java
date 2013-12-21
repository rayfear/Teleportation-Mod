package tpmod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTeleportationMob extends ModelBase
{
    //fields
    ModelRenderer Body;
    ModelRenderer RectangleR;
    ModelRenderer RectangeL;
    ModelRenderer EyeL;
    ModelRenderer EyeR;

    public ModelTeleportationMob()
    {
        textureWidth = 64;
        textureHeight = 32;
        Body = new ModelRenderer(this, 0, 0);
        Body.addBox(0F, 0F, 0F, 12, 5, 8);
        Body.setRotationPoint(-6F, 19F, -4F);
        Body.setTextureSize(64, 32);
        Body.mirror = true;
        setRotation(Body, 0F, 0F, 0F);
        RectangleR = new ModelRenderer(this, 52, 0);
        RectangleR.addBox(0F, 0F, 0F, 1, 3, 1);
        RectangleR.setRotationPoint(-4F, 16F, -2F);
        RectangleR.setTextureSize(64, 32);
        RectangleR.mirror = true;
        setRotation(RectangleR, 0F, 0F, 0F);
        RectangeL = new ModelRenderer(this, 52, 0);
        RectangeL.addBox(0F, 0F, 0F, 1, 3, 1);
        RectangeL.setRotationPoint(3F, 16F, -2F);
        RectangeL.setTextureSize(64, 32);
        RectangeL.mirror = true;
        setRotation(RectangeL, 0F, 0F, 0F);
        EyeL = new ModelRenderer(this, 40, 0);
        EyeL.addBox(0F, 0F, 0F, 3, 3, 3);
        EyeL.setRotationPoint(2F, 13F, -3F);
        EyeL.setTextureSize(64, 32);
        EyeL.mirror = true;
        setRotation(EyeL, 0F, 0F, 0F);
        EyeR = new ModelRenderer(this, 40, 0);
        EyeR.addBox(0F, 0F, 0F, 3, 3, 3);
        EyeR.setRotationPoint(-5F, 13F, -3F);
        EyeR.setTextureSize(64, 32);
        EyeR.mirror = true;
        setRotation(EyeR, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Body.render(f5);
        RectangleR.render(f5);
        RectangeL.render(f5);
        EyeL.render(f5);
        EyeR.render(f5);
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
