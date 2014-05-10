package tpmod.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import tpmod.doorlocator.TeleportationDoorLocator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSetTeleportationDoorLocation extends GuiScreen
{
	private final EntityPlayer player;
	byte b0 = -16;
	protected int xSize = 176;
	protected int ySize = 166;
	private GuiTextField frequency;

	public static int GUIID = 54;
	
	public int x;
	public int y;
	public int z;

	public int freq;
	
	public GuiSetTeleportationDoorLocation(EntityPlayer player, int x, int y, int z, int freq)
	{
		this.player = player;
		this.x = x;
		this.y = y;
		this.z = z;
		this.freq = freq;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@SuppressWarnings("unchecked")
	public void initGui()
	{
		this.buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + b0, 98, 20, "Ok"));
		this.buttonList.add(new GuiButton(1, this.width / 2, this.height / 4 + 96 + b0, 98, 20, "Cancel"));
		this.frequency = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
		this.frequency.setFocused(true);
		this.frequency.setText("" + freq);
		this.frequency.setMaxStringLength(100);
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	public void updateScreen()
	{
		try
		{
			this.frequency.updateCursorCounter();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		super.updateScreen();
	}
	/**
	 * Called when the screen is unloaded. Used to disable keyboard repeat events
	 */
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}

	/**
	 * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
	 */
	protected void keyTyped(char par1, int par2)
	{
		if (!this.frequency.textboxKeyTyped(par1, par2))
		{
			super.keyTyped(par1, par2);
		}
	}

	/**
	 * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
	 */
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 0)
		{
			String stringFrequency = frequency.getText();
			this.mc.displayGuiScreen((GuiScreen)null);

			try 
			{
				int intFrequency = Integer.parseInt(stringFrequency);

				TeleportationDoorLocator.setFrequency(x, y, z, player.worldObj.provider.dimensionId, intFrequency);
			}
			catch (Exception e)
			{
				this.player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "[Teleportation Mod Error] " + stringFrequency + " is not a number!"));
			}
		}

		if (button.id == 1)
		{
			this.mc.displayGuiScreen((GuiScreen)null);
		}
	}

	/**
	 * Called when the mouse is clicked.
	 */
	protected void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
		this.frequency.mouseClicked(par1, par2, par3);
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int par1, int par2, float par3)
	{
		super.drawScreen(par1, par2, par3);

		try
		{
			this.frequency.drawTextBox();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		try
		{
			this.drawCenteredString(this.fontRendererObj, "Set Teleportation Door Frequency", this.width / 2, 40, 16777215);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
