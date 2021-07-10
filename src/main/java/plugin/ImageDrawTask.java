package plugin;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class ImageDrawTask {
    private final ParticleImage image;
    private BukkitTask task;

    public ImageDrawTask(ParticleImage image) {
        this.image = image;
    }

    public void startDrawing(int period) {
        stopDrawing();

        task = new BukkitRunnable() {
            @Override
            public void run() {
                image.draw();
            }
        }.runTaskTimerAsynchronously(Plugin.getPlugin(Plugin.class), 0, period);
    }

    public void stopDrawing() {
        if (task == null) return;
        task.cancel();
    }
}
