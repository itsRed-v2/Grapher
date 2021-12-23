package fr.xenocraft.grapher.graphRenderers;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

import fr.xenocraft.grapher.GraphRenderer;

public class LemniscateBernoulli extends GraphRenderer {

    public Location center;
    public int size = 2;

    public LemniscateBernoulli(Location center) {
        this.center = center;
    }

    @Override
    public void run() {

        for (double t = 0; t < 2 * Math.PI; t += 1 / 30f) {

            double scale = 2 / (3 - Math.cos(2 * t));

            double x = scale * Math.cos(t);
            double z = scale * Math.sin(2 * t) / 2;

            x *= size;
            z *= size;

            double y = center.getY();
            x += center.getX();
            z += center.getZ();

            center.getWorld().spawnParticle(Particle.REDSTONE, x, y, z, 1,
                    new Particle.DustOptions(Color.YELLOW, .5f));

        }

    }
}
