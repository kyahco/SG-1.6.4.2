package mods.scourgecraft.helpers;

import mods.scourgecraft.helpers.point.Point;
import mods.scourgecraft.helpers.point.WarpPoint;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class FunctionHelper 
{
	/**
     * instWarp a player to a point. Please use TeleportCenter!
     * @param player
     * @param p
     */
    public static void setPlayer(EntityPlayerMP player, WarpPoint p)
    {
            if (player.dimension != p.dim)
            {
                    MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(player, p.dim);
            }
            player.playerNetServerHandler.setPlayerLocation(p.xd, p.yd, p.zd, p.yaw, p.pitch);
            player.prevPosX = player.posX = p.xd;
            player.prevPosY = player.posY = p.yd;
            player.prevPosZ = player.posZ = p.zd;
    }

    /**
     * instWarp a player to a point. Please use TeleportCenter!
     * @param player
     * @param world
     * @param p
     */
    public static void setPlayer(EntityPlayerMP player, Point point, World world)
    {
            if (player.dimension != world.provider.dimensionId)
            {
                    MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(player, world.provider.dimensionId);
            }
            double x = point.x, y = point.y, z = point.z;
            x = x < 0 ? x - 0.5 : x + 0.5;
            z = z < 0 ? z - 0.5 : z + 0.5;
            player.playerNetServerHandler.setPlayerLocation(x, y, z, player.rotationYaw, player.rotationPitch);
    }
}
