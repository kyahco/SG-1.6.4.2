package mods.scourgecraft.client.gui;

import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.helpers.Home;
import mods.scourgecraft.network.packet.Packet2CreateHome;
import mods.scourgecraft.player.ExtendedPlayer;
import mods.scourgecraft.tileentity.TileEntityGoldProducer;
import mods.scourgecraft.tileentity.TileEntityGoldStorage;
import mods.scourgecraft.tileentity.TileEntityHomeHall;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiGoldStorage extends GuiScreen {
	
	TileEntityGoldStorage teGold;
	int xCoord;
	int yCoord;
	int zCoord;
	
	public GuiGoldStorage(TileEntityGoldStorage par1TeGold, int x, int y, int z) {
		teGold = par1TeGold;
		xCoord = x;
		yCoord = y;
		zCoord = z;
	}

	@Override 
	public void initGui()
	{
	}
	
	@Override
	public void drawScreen(int x, int y, float f) {
		drawDefaultBackground();
		
		GL11.glColor4f(1F, 1F, 1F, 1F);

		drawCenteredString(this.fontRenderer, "Gold Storage", this.width / 2, 10, 0x6699FF);
		
		if (teGold.getOwner().equals(Minecraft.getMinecraft().thePlayer.username)) // You own this Gold Producer, so display differently.
		{
			drawString(this.fontRenderer, "Storage Stats", 10, 50, 0xFFCC00);
			drawString(this.fontRenderer, "Level " + teGold.getLevel(), 10, 60, 0x66CC66);
			drawString(this.fontRenderer, "Gold Count : " + teGold.getGold(), 10, 70, 0x66CC66);
			drawString(this.fontRenderer, "Max Storage : " + (int)teGold.getMaxStorage(), 10, 80, 0x66CC66);
			if (teGold.hasNextLevel())
			{
				drawString(this.fontRenderer, "Next Level Stats", 10, 100, 0xFFCC00);
				drawString(this.fontRenderer, "Level " + (teGold.getLevel() + 1), 10, 110, 0x66CC66);
				drawString(this.fontRenderer, "Max Storage : " + (int)teGold.getMaxStorage(teGold.getLevel() + 1), 10, 120, 0x66CC66);
				drawString(this.fontRenderer, "Upgrade Requirements", 10, 140, 0xFFCC00);
				this.buttonList.add(new GuiButton(1, this.width - 124, 60, 115, 20, "Upgrade Level " + (teGold.getLevel() + 1)));
			}
			
		}
		else // Your Raiding.
		{
			
		}
		
		this.buttonList.add(new GuiButton(10, this.width - 50, 5, 45, 20, "Close"));
		
		super.drawScreen(x, y, f);
	}
	
	@Override
	public void updateScreen()
	{
	}
	
	public void actionPerformed(GuiButton button)
	{
		if (button.id == 0)
		{
		}
		else if (button.id == 1) // Attempting to Upgrade
		{
			
		}
		else if (button.id == 10)
		{
			Minecraft.getMinecraft().thePlayer.closeScreen();
		}
	}

}
