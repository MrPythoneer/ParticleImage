package plugin.commands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.ImageDrawTask;
import plugin.Plugin;

import java.lang.reflect.InvocationTargetException;

//import static plugin.Plugin.protocolManager;

public class DeleteAll implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
//        if (sender instanceof Player player) {
//            PacketContainer fakeExplosion = protocolManager.
//                    createPacket(PacketType.Play.Server.EXPLOSION);
//
//            fakeExplosion.getDoubles().
//                    write(0, player.getLocation().getX()).
//                    write(1, player.getLocation().getY()).
//                    write(2, player.getLocation().getZ());
//            fakeExplosion.getFloat().write(0, 3.0F);
//
//            try {
//                protocolManager.sendServerPacket(player, fakeExplosion);
//            } catch (InvocationTargetException e) {
//                System.err.println("Particle Image :: ERROR :: " + e.getMessage());
//                player.sendMessage(e.getMessage());
//                return true;
//            }
//        }

        int tasksNumber = Plugin.tasks.size();
        for (ImageDrawTask task : Plugin.tasks)
            task.stopDrawing();

        Plugin.tasks.clear();
        sender.sendMessage(tasksNumber + " tasks were deleted");
        return true;
    }
}
