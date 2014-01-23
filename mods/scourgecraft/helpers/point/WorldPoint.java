package mods.scourgecraft.helpers.point;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class WorldPoint extends Point
{
        public int                                        dim;

        public WorldPoint(int dimension, int x, int y, int z)
        {
                super(x, y, z);
                dim = dimension;
        }

        public WorldPoint(World world, int x, int y, int z)
        {
                super(x, y, z);
                dim = world.provider.dimensionId;
        }

        public WorldPoint(Entity player)
        {
                super(player);
                dim = player.dimension;
        }

        public int compareTo(WorldPoint p)
        {
                int diff = dim - p.dim;

                if (diff == 0)
                {
                        diff = super.compareTo(p);
                }
                return diff;
        }

        public boolean equals(WorldPoint p)
        {
                return dim == p.dim && super.equals(p);
        }

        public WorldPoint copy(WorldPoint p)
        {
                return new WorldPoint(p.dim, p.x, p.y, p.z);
        }

        @Override
        public String toString()
        {
                return "WorldPoint[" + dim + ", " + x + ", " + y + ", " + z + "]";
        }
}