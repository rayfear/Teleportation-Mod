package tpmod.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderObserver extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation("tpmod:textures/mob/Observer.png");
    private static final ResourceLocation eyestexture = new ResourceLocation("tpmod:textures/mob/Observer_Eyes.png");

    public RenderObserver(ModelBase model, float f)
    {
        super(model, f);
        this.setRenderPassModel(model);
    }

    protected ResourceLocation getTexture(EntityObserver observer)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getTexture((EntityObserver)entity);
    }
    
    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityObserver observer, int par2, float par3)
    {
        if (par2 != 0)
        {
            return -1;
        }
        else
        {
            this.bindTexture(eyestexture);
            float f1 = 1.0F;
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_LIGHTING);

            if (observer.isInvisible())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }

            char c0 = 61680;
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
            
            return 1;
        }
    }
    
    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase entity, int par2, float par3)
    {
        return this.shouldRenderPass((EntityObserver)entity, par2, par3);
    }
}
