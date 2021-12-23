package fr.xenocraft.grapher;

import org.bukkit.plugin.java.JavaPlugin;

import fr.xenocraft.grapher.commands.GraphCommand;
import fr.xenocraft.grapher.commands.GraphTab;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        
        registerCommands();

        Grapher.init(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @SuppressWarnings("ConstantConditions")
    private void registerCommands() {
        // Commands & tab completers
        getCommand("graph").setExecutor(new GraphCommand());
            getCommand("graph").setTabCompleter(new GraphTab());
    }
}
