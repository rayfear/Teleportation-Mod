package tpmod.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tpmod.TeleportationMod;
import tpmod.location.SavedLocation;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTpSword extends ItemSword
{
    private float weaponDamage;
    private final ToolMaterial toolMaterial;
    int itemDamage;

    private SavedLocation location = null;
    public static double x;
    public static double y;
    public static double z;

    public ItemTpSword(ToolMaterial toolMaterial)
    {
        super(toolMaterial);
        this.toolMaterial = toolMaterial;
        this.maxStackSize = 1;
        this.setMaxDamage(200);
        this.weaponDamage = 0.5F;
    }
    
    /**
     * getDamageVsEntity
     */
    public float func_150931_i()
    {
        return this.toolMaterial.getDamageVsEntity();
    }

    /**
     * onBlockDestroyed
     */
    public float func_150893_a(ItemStack stack, Block block)
    {
        if (block == Blocks.web)
        {
            return 15.0F;
        }
        else
        {
            Material material = block.getMaterial();
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
        }
    }
    
    
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {
        if (location != null)
        {
            if (stack.stackTagCompound == null)
            {
                stack.setTagCompound(new NBTTagCompound());
            }

            if (!stack.stackTagCompound.hasKey("ench"))
            {
                stack.stackTagCompound.setTag("gegy", new NBTTagList());
            }

            NBTTagList var3 = (NBTTagList)stack.stackTagCompound.getTag("gegy");
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setDouble("x", location._x);
            nbt.setDouble("y", location._y);
            nbt.setDouble("z", location._z);
            var3.appendTag(nbt);
        }
    }

    private void saveTpPosition(ItemStack stack, EntityPlayer player)
    {
        if (stack.stackTagCompound == null)
        {
            stack.setTagCompound(new NBTTagCompound());
        }

        if (!stack.stackTagCompound.hasKey("tpPosition"))
        {
            stack.stackTagCompound.setTag("tpPosition", new NBTTagList());
        }

        NBTTagList tpPositionTagList = (NBTTagList)stack.stackTagCompound.getTag("tpPosition");
        NBTTagCompound tpPositionTag = new NBTTagCompound();
        tpPositionTag.setDouble("x", player.posX);
        tpPositionTag.setDouble("y", player.posY);
        tpPositionTag.setDouble("z", player.posZ);
        tpPositionTagList.appendTag(tpPositionTag);
    }

    private boolean hasSavedPosition(ItemStack stack)
    {
        return stack.stackTagCompound != null && stack.stackTagCompound.hasKey("tpPosition");
    }

    public static void readTpPos(ItemStack stack, Entity entity)
    {
        NBTTagList tpPositionTagList = (NBTTagList)stack.stackTagCompound.getTag("tpPosition");
        NBTTagCompound tpPositionTag = (NBTTagCompound) tpPositionTagList.getCompoundTagAt(0);
        x = tpPositionTag.getDouble("x");
        y = tpPositionTag.getDouble("y");
        z = tpPositionTag.getDouble("z");
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase entityHitting)
    {
        if (this.hasSavedPosition(stack))
        {
            readTpPos(stack, entityHit);

            entityHit.setPosition(x, y, z);
            
//            for (int i = 0; i < 30; i++) 
//            {
//    			double r = ((double)rand.nextFloat() - 0.5D) * 0.5D;
//    			double g = ((double)rand.nextFloat() - 0.5D) * 0.5D;
//    			double b = ((double)rand.nextFloat() - 0.5D) * 0.5D;
//                
//                double randX = entityHit.posX + (rand.nextDouble() - 0.5D) * (double)entityHit.width;
//    			double randY = (entityHit.posY + rand.nextDouble() * (double)entityHit.height) - 0.25D;
//    			double randZ = entityHit.posZ + (rand.nextDouble() - 0.5D) * (double)entityHit.width;
//    			
//    			if(entityHit.worldObj.isRemote)
//    			{
//    				entityHit.worldObj.spawnParticle("portal", randX, randY, randZ, r, g, b);
//    			}
//			}
        }

        stack.damageItem(1, entityHitting);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entityHitting)
    {
        if (block.getBlockHardness(world, x, y, z) != 0.0D)
        {
            stack.damageItem(2, entityHitting);
            this.itemDamage = stack.getItemDamage();
        }

        return true;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.block;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (player.isSneaking())
        {
            if (!world.isRemote)
            {
            	if(player.getClass() != EntityClientPlayerMP.class)
            	{
            		if (!this.hasSavedPosition(stack))
                    {
                        this.saveTpPosition(stack, player);
                        player.addChatMessage(new ChatComponentText("Successfully Set Co-ords"));
                    }
                    else
                    {
                        stack.stackTagCompound.removeTag("tpPosition");
                        this.saveTpPosition(stack, player);
                        player.addChatMessage(new ChatComponentText("Successfully Set Co-ords"));
                    }	
            	}
            }
        }
        else
        {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }

        return stack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    /**
     * Return the name for this tool's material.
     */
    public String getToolMaterialName()
    {
        return this.toolMaterial.toString();
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.weaponDamage, 0));
        return multimap;
    }
    
    public EnumRarity getRarity(ItemStack stack)
    {
        return stack.getItemDamage() == 0 ? EnumRarity.rare : EnumRarity.epic;
    }
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconReg)
    {
        this.itemIcon = iconReg.registerIcon(TeleportationMod.MODID + ":" + "Teleportation Sword");
    }
}
