package tpmod.itemRenderers;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import tpmod.model.ModelMagicTeleportationWand;

public class RenderWand3 implements IItemRenderer
{
    protected ModelMagicTeleportationWand model = new ModelMagicTeleportationWand();
    private static final ResourceLocation png = new ResourceLocation("tpmod:textures/3D/Wand3.png");
    public boolean handleRenderType(ItemStack stack, IItemRenderer.ItemRenderType type)
    {
        switch (type)
        {
            case EQUIPPED:
                return true;

            case EQUIPPED_FIRST_PERSON:
                return true;

            case INVENTORY:
                return true;

            case ENTITY:
                return true;

            default:
                return false;
        }
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType var1, ItemStack var2, IItemRenderer.ItemRendererHelper var3)
    {
        return true;
    }

    public void renderWand(ItemRenderType type)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(png);

        if (type == ItemRenderType.EQUIPPED)
        {
            float scale = 6F;
            GL11.glScalef(scale, scale, scale);
            GL11.glRotatef(180, 0, 1, 0);
            GL11.glRotatef(75, 1, 0, 0);
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glRotatef(-25, 0, 1, 0);
        }

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            float scale = 5F;
            GL11.glScalef(scale, scale, scale);
            GL11.glRotatef(160, 0, 1, 0);
            GL11.glRotatef(75, 1, 0, 0);
            GL11.glRotatef(40, 2.5F, 0, 0);
            GL11.glRotatef(40, 2.5F, 0, 0);
            GL11.glTranslatef(-0.2F, 0.1F, -0.1F);
        }

        if (type == ItemRenderType.INVENTORY)
        {
            float scale = 2F;
            GL11.glScalef(scale, scale, scale);
            GL11.glRotatef(Sys.getTime() / 50F % 360F, 0F, 1F, 0F);
        }

        if (type == ItemRenderType.ENTITY)
        {
            GL11.glTranslatef(0.0F, 0.0F, 0F);
            GL11.glScalef(2.0F, 2.0F, 2.0F);
            GL11.glRotatef(Sys.getTime() / 40F % 360F, 0F, 1F, 0F);
        }

        model.renderAll();
        //GL11.glRotatef(angle, x, y, z);
    }
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack var2, Object ... data)
    {
        switch (type)
        {
            case EQUIPPED:
                this.renderWand(type);
                break;

            case INVENTORY:
                this.renderWand(type);
                break;

            case EQUIPPED_FIRST_PERSON:
                this.renderWand(type);
                break;

            case ENTITY:
                this.renderWand(type);
                break;

            default:
        }
    }
}
