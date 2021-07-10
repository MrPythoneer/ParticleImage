package plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import plugin.Plugin;

public class DeleteImage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Wrong arguments");
            return false;
        }

        try {
            int imgId = Integer.parseInt(args[0]);
            Plugin.tasks.get(imgId).stopDrawing();
            Plugin.tasks.remove(imgId);
            return true;
        } catch (IndexOutOfBoundsException ignored) {
            sender.sendMessage("Index Out Of Bounds");
            return false;
        } catch (NumberFormatException ignored) {
            sender.sendMessage("Not a number");
            return false;
        }
    }
}
