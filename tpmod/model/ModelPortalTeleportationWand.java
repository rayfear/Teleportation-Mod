package tpmod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPortalTeleportationWand extends ModelBase
{
    public ModelRenderer parts[] = new ModelRenderer[6];
    
    public ModelPortalTeleportationWand()
    {
        textureWidth = 64;
        textureHeight = 32;
        parts[5] = new ModelRenderer(this, 0, 0);
        parts[5].addBox(0F, 0F, 0F, 1, 1, 12);
        parts[5].setRotationPoint(0F, 0F, 0F);
        parts[5].setTextureSize(64, 32);
        parts[5].mirror = true;
        setRotation(parts[5], 0F, 0F, 0F);
        parts[4] = new ModelRenderer(this, 26, 8);
        parts[4].addBox(0F, 0F, 0F, 1, 5, 1);
        parts[4].setRotationPoint(0F, -2F, -1F);
        parts[4].setTextureSize(64, 32);
        parts[4].mirror = true;
        setRotation(parts[4], 0F, 0F, 0F);
        parts[3] = new ModelRenderer(this, 26, 8);
        parts[3].addBox(0F, 0F, 0F, 1, 1, 5);
        parts[3].setRotationPoint(0F, 2F, -6F);
        parts[3].setTextureSize(64, 32);
        parts[3].mirror = true;
        setRotation(parts[3], 0F, 0F, 0F);
        parts[2] = new ModelRenderer(this, 26, 8);
        parts[2].addBox(0F, 0F, 0F, 1, 1, 5);
        parts[2].setRotationPoint(0F, -2F, -6F);
        parts[2].setTextureSize(64, 32);
        parts[2].mirror = true;
        setRotation(parts[2], 0F, 0F, 0F);
        parts[1] = new ModelRenderer(this, 26, 8);
        parts[1].addBox(0F, 0F, 0F, 1, 5, 1);
        parts[1].setRotationPoint(0F, -2F, -7F);
        parts[1].setTextureSize(64, 32);
        parts[1].mirror = true;
        setRotation(parts[1], 0F, 0F, 0F);
        parts[0] = new ModelRenderer(this, 26, 0);
        parts[0].addBox(0F, 0F, 0F, 1, 3, 5);
        parts[0].setRotationPoint(0F, -1F, -6F);
        parts[0].setTextureSize(64, 32);
        parts[0].mirror = true;
        setRotation(parts[0], 0F, 0F, 0F);
    }

    public void render(Entity entity, float time, float swingDistance, float f2, float f3, float f4, float f5)
    {
        super.render(entity, time, swingDistance, f2, f3, f4, f5);
        setRotationAngles(time, swingDistance, f2, f3, f4, f5, entity);
    }

    public void renderAll()
    {
        for (final ModelRenderer currentModel : this.parts)
        {
            currentModel.render(0.0625F);
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float time, float swingDistance, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(time, swingDistance, f2, f3, f4, f5, entity);
    }
}
