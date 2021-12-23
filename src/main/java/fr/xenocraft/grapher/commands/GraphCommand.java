package fr.xenocraft.grapher.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.xenocraft.grapher.Grapher;
import fr.xenocraft.grapher.graphRenderers.LemniscateBernoulli;
import fr.xenocraft.grapher.graphRenderers.EightRenderer;
import fr.xenocraft.grapher.graphRenderers.LemniscateGerono;
import fr.xenocraft.grapher.graphRenderers.SphereDirectionRenderer;
import org.jetbrains.annotations.NotNull;

public class GraphCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player p)) return true;

        if (args.length < 1) return false;

        String action = args[0];

        if (action.equalsIgnoreCase("enable")) {
            if (args.length < 2) return false;

            if (args[1].equals("sphere")) Grapher.startGraph(new SphereDirectionRenderer(p.getLocation(), 3));
            if (args[1].equals("gerono")) Grapher.startGraph(new LemniscateGerono(p.getLocation()));
            if (args[1].equals("eight")) Grapher.startGraph(new EightRenderer(p.getLocation()));
            if (args[1].equals("bernoulli")) Grapher.startGraph(new LemniscateBernoulli(p.getLocation()));
        }

        else if (action.equalsIgnoreCase("disable")) Grapher.disable();

        else if (Grapher.getRenderer() instanceof EightRenderer
                && (action.equalsIgnoreCase("setAmplitude") || action.equalsIgnoreCase("setFrequency"))) {

            if (args.length < 3) return false;

            double value;
            try {
                value = Double.parseDouble(args[2]);
            } catch (NumberFormatException exception) {
                p.sendMessage("§cInvalid number: §6" + args[2]);
                return true;
            }

            if (action.equalsIgnoreCase("setAmplitude")) {
                if (args[1].equals("yaw")) ((EightRenderer) Grapher.getRenderer()).yawAmplitude = value;
                if (args[1].equals("pitch")) ((EightRenderer) Grapher.getRenderer()).pitchAmplitude = value;
            } else if (action.equalsIgnoreCase("setFrequency")) {
                if (args[1].equals("yaw")) ((EightRenderer) Grapher.getRenderer()).yawFrequency = value;
                if (args[1].equals("pitch")) ((EightRenderer) Grapher.getRenderer()).pitchFrequency = value;
            }
        }

        else return false;

        return true;

    }
}
