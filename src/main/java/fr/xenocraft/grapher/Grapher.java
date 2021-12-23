package fr.xenocraft.grapher;

import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

public class Grapher {

    private static Main main;

    private static GraphRenderer renderer;

    private static boolean enabled = false;

    public static void init(Main main) {
        Grapher.main = main;
    }

    public static void startGraph(GraphRenderer graph) {
        if (renderer != null) renderer.cancel();
        renderer = graph;
        renderer.start(main);
        enabled = true;
    }

    public static void disable() {
        if (enabled) {
            enabled = false;
            renderer.cancel();
            renderer = null;
        }
    }

    public static GraphRenderer getRenderer() {
        return renderer;
    }

}
