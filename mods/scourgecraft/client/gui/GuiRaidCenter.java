package mods.scourgecraft.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.logging.Level;

import mods.scourgecraft.data.HomeManager;
import mods.scourgecraft.helpers.Home;
import mods.scourgecraft.network.packet.Packet5StartRaid;
import mods.scourgecraft.tileentity.TileEntityRaidCenter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;



public class GuiRaidCenter extends GuiScreen
{
	private GuiTextField txtRaidName;
	private String townName = "";
	private EntityPlayer ePlayer;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private TileEntityRaidCenter tileRaid;
	private int ticks = 0;
	List<Home> townList;
	private int pageNumber = 0;
	
	public GuiRaidCenter(EntityPlayer par1EntityPlayer, TileEntityRaidCenter par1Tile, int x, int y, int z) {
		ePlayer = par1EntityPlayer;
		tileRaid = par1Tile;
		xCoord = x;
		yCoord = y;
		zCoord = z;
	}

	@Override 
	public void initGui()
	{
		txtRaidName = new GuiTextField(fontRenderer, this.width - 105, this.height - 25, 100, 20);
		txtRaidName.setMaxStringLength(16);
		townList = HomeManager.getHomesStartWith(townName, 10, pageNumber);
	}
	
	@Override
	public void drawScreen(int x, int y, float f) {
		ticks++;
		drawDefaultBackground();
		
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		drawCenteredString(this.fontRenderer, "Raid Center", this.width / 2, 10, 0x6699FF);
		drawString(this.fontRenderer, "Quick Search : ", this.width - 180, this.height - 18, 0xFFCC00);
		
		if (ticks % 20 == 0)
		{
			townList = HomeManager.getHomesStartWith(townName, 10, 0);
			ticks = 0;
		}
		drawString(this.fontRenderer, "Town Name", 10, 50, 0xFFFFFF);
		drawString(this.fontRenderer, "Town Owner", 80, 50, 0xFFFFFF);
		drawString(this.fontRenderer, "Town Level", 150, 50, 0xFFFFFF);
		drawString(this.fontRenderer, "Action", 265, 50, 0xFFFFFF);
		for (int i = 0; i < townList.size(); i++)
		{
			drawString(this.fontRenderer, townList.get(i).name, 10, (i * 20) + 70, 0xFFCC00);
			drawString(this.fontRenderer, townList.get(i).ownerUsername, 80, (i * 20) + 70, 0x66CC66);
			drawString(this.fontRenderer, townList.get(i).level + "", 150, (i * 20) + 70, 0x66CC66);
			this.buttonList.add(new GuiButton(20 + i, 250, (i * 20) + 70 - 7, 65, 20, "Raid Town"));
		}
		this.buttonList.add(new GuiButton(8, 50, this.height - 25, 45, 20, "< Page"));
		this.buttonList.add(new GuiButton(9, 100, this.height - 25, 45, 20, "Page >"));
		
		txtRaidName.drawTextBox();
		
		this.buttonList.add(new GuiButton(10, this.width - 50, 5, 45, 20, "Close"));
		super.drawScreen(x, y, f);
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	public void keyTyped(char c, int i)
	{
		super.keyTyped(c, i);
		txtRaidName.textboxKeyTyped(c, i);
	}
	
	public void mouseClicked(int i, int j, int k)
	{
		super.mouseClicked(i, j, k);
		txtRaidName.mouseClicked(i, j, k);
	}
	
	public void updateScreen()
	{
		townName = txtRaidName.getText();
	}
	
	public void actionPerformed(GuiButton button)
	{
		if (button.id == 0)
		{
			
		}
		else if (button.id == 8)
		{
			if (pageNumber <= 0)
				return;
			else 
				pageNumber--;
		}
		else if (button.id == 9)
		{
			pageNumber++;
		}
		else if (button.id == 10)
		{
			Minecraft.getMinecraft().thePlayer.closeScreen();
		}
		else if (button.id >= 20 && button.id <= 30)
		{
			if (townList.size() >= button.id - 20)
			{
				Home h = townList.get(button.id - 20);
				if (h != null)
				{
					PacketDispatcher.sendPacketToServer(new Packet5StartRaid(ePlayer.username, h.ownerUsername).makePacket());
				}
			}
		}
	}
}
