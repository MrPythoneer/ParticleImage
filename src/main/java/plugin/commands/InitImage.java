package plugin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.ParticleImage;
import plugin.Plugin;

public class InitImage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Should be a player");
            return true;
        }

        int width = 50;
        int height = 50;
        double x, y, z;

        switch (args.length) {
            case 4 -> {
                x = Double.parseDouble(args[1]);
                y = Double.parseDouble(args[2]);
                z = Double.parseDouble(args[3]);
            }
            case 6 -> {
                x = Double.parseDouble(args[1]);
                y = Double.parseDouble(args[2]);
                z = Double.parseDouble(args[3]);
                width = Integer.parseInt(args[4]);
                height = Integer.parseInt(args[5]);
            }
            default -> {
                sender.sendMessage("Wrong arguments");
                return false;
            }
        }

        final ParticleImage image = ParticleImage.fromURL(args[0]);
        if (image == null) {
            sender.sendMessage("Cannot parse the image");
            return true;
        }

        image.setLocation(new Location(player.getWorld(), x, y, z));
        image.resize(width, height);

        sender.sendMessage("imgID: " + Plugin.tasks.size());
        Plugin.tasks.add(image.createTask());

        return true;
    }
}
