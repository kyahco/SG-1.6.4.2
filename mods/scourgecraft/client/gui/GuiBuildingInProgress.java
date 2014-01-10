package mods.scourgecraft.client.gui;

import mods.scourgecraft.ScourgeCraftCore;
import mods.scourgecraft.player.ExtendedPlayer;
import mods.scourgecraft.tileentity.TileEntityHomeHall;
import mods.scourgecraft.tileentity.TileEntityScourgeBuilding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class GuiBuildingInProgress extends GuiScreen {
	
	public final int xSizeOfTexture = 233;
	public final int ySizeOfTexture = 137;
	private TileEntityScourgeBuilding teHome;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	
	public GuiBuildingInProgress(TileEntityScourgeBuilding par1TeHome, int x, int y, int z) {
		teHome = par1TeHome;
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
		
		Minecraft.getMinecraft().renderEngine.bindTexture(GuiResourceFile.factionSelectorBackgroundGui);
		
		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;
		
		drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);

		drawCenteredString(this.fontRenderer, "Building Under Construction : " + teHome.percentCompleted() + "% Completed!", this.width / 2, posY + 50, 0xFFFFFF);
	}
	
	@Override
	public void updateScreen()
	{
		if (teHome.isCompleted())
			Minecraft.getMinecraft().thePlayer.closeScreen();
	}

}
