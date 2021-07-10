package plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import plugin.Plugin;

public class DrawImage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int imgId, period;
        switch (args.length) {
            case 1 -> {
                imgId = Integer.parseInt(args[0]);
                period = 20;
            }
            case 2 -> {
                imgId = Integer.parseInt(args[0]);
                period = Integer.parseInt(args[1]);
            }
            default -> {
                sender.sendMessage("Wrong arguments");
                return false;
            }
        }

        try {
            Plugin.tasks.get(imgId).startDrawing(period);
            return true;
        } catch (IndexOutOfBoundsException ignored) {
            sender.sendMessage("Index Out Of Bounds");
            return false;
        }
    }
}
