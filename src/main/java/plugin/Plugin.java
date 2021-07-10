package plugin;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.commands.*;

import java.util.ArrayList;
import java.util.List;

public class Plugin extends JavaPlugin {
    public static final List<ImageDrawTask> tasks = new ArrayList<>();

    @Override
    public void onEnable() {
        System.out.println("Plugin enabled");

        final PluginCommand initImage = getCommand("init_image");
        final PluginCommand drawImage = getCommand("draw_image");
        final PluginCommand deleteImage = getCommand("delete_image");
        final PluginCommand deleteAll = getCommand("delete_all");
        final PluginCommand stopAll = getCommand("stop_all");
        if (initImage == null || drawImage == null || deleteImage == null || deleteAll == null || stopAll == null) {
            System.err.println("Particle Image :: ERROR :: Cannot load the plugin");
        } else {
            System.out.println("Plugin started");
            initImage.setExecutor(new InitImage());
            drawImage.setExecutor(new DrawImage());
            deleteImage.setExecutor(new DeleteImage());
            deleteAll.setExecutor(new DeleteAll());
            stopAll.setExecutor(new StopAll());
        }
    }

    @Override
    public void onDisable() {
        for (ImageDrawTask task : tasks)
            task.stopDrawing();

        tasks.clear();
    }
}
