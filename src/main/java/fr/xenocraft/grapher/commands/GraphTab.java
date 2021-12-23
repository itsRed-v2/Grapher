package fr.xenocraft.grapher.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import fr.xenocraft.grapher.Grapher;
import fr.xenocraft.grapher.graphRenderers.EightRenderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GraphTab implements TabCompleter {

    public static final List<String> ACTIONS = new ArrayList<>();
    static {
        ACTIONS.add("enable");
        ACTIONS.add("disable");
    }

    public static final List<String> MultiplicableActions = new ArrayList<>();
    static {
        MultiplicableActions.add("enable");
        MultiplicableActions.add("disable");
        MultiplicableActions.add("setAmplitude");
        MultiplicableActions.add("setFrequency");
    }

    public static final List<String> GRAPHNAMES = new ArrayList<>();
    static {
        GRAPHNAMES.add("sphere");
        GRAPHNAMES.add("gerono");
        GRAPHNAMES.add("eight");
        GRAPHNAMES.add("bernoulli");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        if (Grapher.getRenderer() instanceof EightRenderer) {
            if (args.length == 1) return MultiplicableActions;
            if ((args[0].equals("setAmplitude") || args[0].equals("setFrequency"))
                    && args.length == 2)
                return List.of("yaw", "pitch");
        }

        if (args.length == 1) return ACTIONS;

        if (args.length == 2 && args[0].equals("enable")) return GRAPHNAMES;

        return new ArrayList<>();
    }
}
