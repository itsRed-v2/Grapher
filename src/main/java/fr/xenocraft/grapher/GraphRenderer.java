package fr.xenocraft.grapher;

import org.bukkit.scheduler.BukkitRunnable;

public abstract class GraphRenderer extends BukkitRunnable {

    public int rhythm = 2;

    public void start(Main main) {
        this.runTaskTimerAsynchronously(main, 5, rhythm);
    }
    
}
