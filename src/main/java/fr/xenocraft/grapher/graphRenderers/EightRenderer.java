package fr.xenocraft.grapher.graphRenderers;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

import fr.xenocraft.grapher.GraphRenderer;

public class EightRenderer extends GraphRenderer {

    public Location center;
    public int size = 2;
    private double loop = 0.0;
    public double pitchAmplitude = 1.0;
    public double yawAmplitude = 1.0;
    public double pitchFrequency = 1.0;
    public double yawFrequency = 2.0;

    public EightRenderer(Location center) {
        this.center = center;
        this.rhythm = 1;
    }

    @Override
    public void run() {

        loop += 1 / 20f;
        if (loop > 2 * Math.PI) loop = 0;

        double yaw = calcYaw(loop);
        double pitch = calcPitch(loop);

        center.getWorld().spawnParticle(Particle.REDSTONE, center, 1,
                new Particle.DustOptions(Color.RED, .7f));

        Location rayCast = center.clone();
        Vector direction = direction(yaw, pitch).multiply(0.1);

        for (int i = 0; i < 10 * size; i++) {
            center.getWorld().spawnParticle(Particle.REDSTONE, rayCast, 1,
                    new Particle.DustOptions(Color.YELLOW, .5f));
            
            rayCast.add(direction);
        }

        renderTrajectory();

    }

    private double calcPitch(double t) {
        return Math.cos(pitchFrequency * t) * pitchAmplitude;
    }

    private double calcYaw(double t) {
        return Math.sin(yawFrequency * t) * yawAmplitude;
    }

    private void renderTrajectory() {
        for (double t = 0; t < 2 * Math.PI; t += 1 / 50f) {
            
            double yaw = calcYaw(t);
            double pitch = calcPitch(t);

            Location point = center.clone().add(direction(yaw, pitch).multiply(size));

            center.getWorld().spawnParticle(Particle.REDSTONE, point, 1,
                    new Particle.DustOptions(Color.AQUA, .5f));
        }
    }

    private static Vector direction(double yaw, double pitch) {

        double multiplicator = Math.cos(pitch);

        Vector pointB = new Vector();

        pointB.setX(Math.sin(yaw) * multiplicator);
        pointB.setZ(Math.cos(yaw) * multiplicator);
        pointB.setY(Math.sin(pitch));

        return pointB;
    }
}
