package plugin;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ParticleImage {
    private BufferedImage image;
    private Location location;

    public ParticleImage(BufferedImage image) {
        this.image = image;
    }

    public static ParticleImage fromURL(String url) {
        final BufferedImage[] image = new BufferedImage[1];

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    final URL imageUrl = new URL(url);
                    image[0] = ImageIO.read(imageUrl);
                } catch (Exception e) {
                    System.err.println("Particle Image :: ERROR :: " + e.getMessage());
                }
            }
        }.run();

        if (image[0] != null)
            return new ParticleImage(image[0]);
        else
            return null;
    }

    public void resize(int width, int height) {
        final Image scaledInstance = image.getScaledInstance(width, height, Image.SCALE_FAST);
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(scaledInstance, 0, 0, null);
        graphics.dispose();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void draw() {
        final World world = location.getWorld();
        if (world == null) {
            System.err.println("Particle Image :: ERROR :: The task cannot be run in a null world");
            return;
        }

        final double x = location.getX();
        final double y = location.getY();
        final double z = location.getZ();

        for (int py = 0; py < image.getHeight(); py++) {
            for (int px = 0; px < image.getWidth(); px++) {
                int rgb = image.getRGB(px, py) & 0xffffff;
                org.bukkit.Color color = Color.fromRGB(rgb);
                world.spawnParticle(Particle.REDSTONE, x + px / 8f, y - py / 10f, z, 0, new Particle.DustOptions(color, 1));
            }
        }
    }

    public ImageDrawTask createTask() {
        return new ImageDrawTask(this);
    }
}
