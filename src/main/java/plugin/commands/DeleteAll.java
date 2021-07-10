package plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import plugin.ImageDrawTask;
import plugin.Plugin;


public class DeleteAll implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        int tasksNumber = Plugin.tasks.size();
        for (ImageDrawTask task : Plugin.tasks)
            task.stopDrawing();

        Plugin.tasks.clear();
        sender.sendMessage(tasksNumber + " tasks were deleted");
        return true;
    }
}
