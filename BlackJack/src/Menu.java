import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Menu
{
    public Menu(Graphics g)
    {
        this.render(g);
    }

    public void render(Graphics g)
    {
        BufferedImage wallpaper;

        // Beolvassa a kártyapakli képet
        InputStream stream = getClass().getResourceAsStream("wallpaper.png");
        try
        {
            assert stream != null;
            wallpaper = ImageIO.read(stream);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            g.drawImage(Image.resizeImage(wallpaper, Game.WIDTH, Game.HEIGHT), 0, 0, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        g.drawImage(wallpaper, 0, 0, null);

        /** MENU BUTTONS */
        g.setColor(Color.GREEN);
        g.fillRect(150, Game.HEIGHT / 2, 300, 80);
        g.fillRect(150, Game.HEIGHT / 2 + 100, 300, 80);
        g.fillRect(150, Game.HEIGHT / 2 + 200, 300, 80);

        Font f = new Font("SansSerif", Font.BOLD, 40);
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString("New Game", 200, Game.HEIGHT / 2 + 50);
        g.drawString("Load Game", 200, Game.HEIGHT / 2 + 150);
        g.drawString("Exit", 200, Game.HEIGHT / 2 + 250);

        g.dispose();
    }
}
