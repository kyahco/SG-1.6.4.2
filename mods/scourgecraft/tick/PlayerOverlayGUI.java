package mods.scourgecraft.tick;

import java.util.EnumSet;

import org.lwjgl.opengl.GL11;

import mods.scourgecraft.client.gui.GuiResourceFile;
import mods.scourgecraft.data.RaidManager;
import mods.scourgecraft.helpers.FunctionHelper;
import mods.scourgecraft.helpers.Raid;
import mods.scourgecraft.player.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerOverlayGUI  implements ITickHandler {

	private final Minecraft mc;
	private int ticks = 0;
	private ExtendedPlayer extPlayer;
	
	public PlayerOverlayGUI()
	{
		mc = Minecraft.getMinecraft();
	}
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (mc != null)
		{
			if (mc.inGameHasFocus)
			{
				//if (extPlayer == null)
					extPlayer = ExtendedPlayer.getExtendedPlayer(mc.thePlayer);
				
				ticks++;
				
				ScaledResolution res = new ScaledResolution(this.mc.gameSettings,
				this.mc.displayWidth, this.mc.displayHeight);
				FontRenderer fontRender = mc.fontRenderer;
				int width = res.getScaledWidth();
				int height = res.getScaledHeight();
				int color = 0xFFFFFF;
				mc.entityRenderer.setupOverlayRendering();
				GuiIngame gui = mc.ingameGUI;

				// draw
				fontRender.drawString("ScourgeCraft", (width / 2) - (fontRender.getStringWidth("ScourgeCraft") / 2) , 2, color);
                fontRender.drawString("VIT:" + Integer.toString(extPlayer.getVitality()), 3 , height / 2 + 32, color);
				
				if (ticks == 100)
					ticks = 0;
				
				if (RaidManager.isInRaid(mc.thePlayer.username))
				{
					Raid r = RaidManager.getRaidByUsername(mc.thePlayer.username);
					mc.getTextureManager().bindTexture(GuiResourceFile.factions);
					if (r.roundType == 2)	
						gui.drawTexturedModalRect(width / 2 - 100, 2, 0, 0, 32, 32);
					else if (r.roundType == 3)
						gui.drawTexturedModalRect(width / 2 - 100, 2, 32, 0, 32, 32);
					else if (r.roundType == 4)
						gui.drawTexturedModalRect(width / 2 - 100, 2, 64, 0, 32, 32);
					else if (r.roundType == 4 && r.isEnded)
						gui.drawTexturedModalRect(width / 2 - 100, 2, 96, 0, 32, 32);
					
					fontRender.drawString("Raid Event :" + FunctionHelper.parseTimeFromTicks(r.timeLeft), (width / 2) - (fontRender.getStringWidth("ScourgeCraft") / 2) , 14, color);
				}
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "overlayGUI";
	}

}
