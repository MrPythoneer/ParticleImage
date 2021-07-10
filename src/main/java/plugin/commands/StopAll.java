package plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import plugin.ImageDrawTask;
import plugin.Plugin;

public class StopAll implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int tasksNumber = Plugin.tasks.size();
        for (ImageDrawTask task : Plugin.tasks)
            task.stopDrawing();

        sender.sendMessage(tasksNumber + " tasks were stopped");

        return true;
    }
}
