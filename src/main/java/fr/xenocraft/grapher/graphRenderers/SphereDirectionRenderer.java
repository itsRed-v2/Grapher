package fr.xenocraft.grapher.graphRenderers;

import org.bukkit.*;
import org.bukkit.util.Vector;

import fr.xenocraft.grapher.GraphRenderer;

public class SphereDirectionRenderer extends GraphRenderer {
    
    public double size;
    public Location center;

    public SphereDirectionRenderer(Location center, double size) {
        this.size = size;
        this.center = center;
    }

    @Override
    public void run() {

        circle(Axis.X);
        circle(Axis.Y);
        circle(Axis.Z);

        center.getWorld().spawnParticle(Particle.REDSTONE, center, 1, 0, 0, 0, 1,
                new Particle.DustOptions(Color.fromRGB(255, 0, 0), 1), true);

        Vector marker = getBPoint(center.toVector(), center.getYaw(), center.getPitch(), size);

        center.getWorld().spawnParticle(Particle.REDSTONE, marker.getX(), marker.getY(), marker.getZ(),
                1,
                0, 0, 0,
                1,
                new Particle.DustOptions(Color.fromRGB(0, 255, 255), 1),
                true);
    }

    private void circle(Axis axis) {
        for (double angle = 0; angle < 2 * Math.PI; angle += 1 / (size * 5)) {

            double x = center.getX();
            double y = center.getY();
            double z = center.getZ();

            switch (axis) {
                case X -> {
                    y += Math.sin(angle) * size;
                    z += Math.cos(angle) * size;
                }
                case Y -> {
                    x += Math.sin(angle) * size;
                    z += Math.cos(angle) * size;
                }
                case Z -> {
                    x += Math.sin(angle) * size;
                    y += Math.cos(angle) * size;
                }
            }

            center.getWorld().spawnParticle(Particle.REDSTONE, x, y, z, 1, 0, 0, 0, 0,
                    new Particle.DustOptions(Color.fromRGB(255, 255, 0), .5f), true);

        }
    }

    private static Vector getBPoint(Vector pointA, double yaw, double pitch, double distance) {

        yaw *= 2 * Math.PI / 360 * -1;
        pitch *= 2 * Math.PI / 360 * -1;

        double multiplicator = Math.cos(pitch) * distance;

        Vector pointB = new Vector();

        pointB.setX(Math.sin(yaw) * multiplicator);
        pointB.setZ(Math.cos(yaw) * multiplicator);
        pointB.setY(Math.sin(pitch) * distance);

        pointB.add(pointA);

        return pointB;
    }
}
