import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
    public static int WIDTH = 1280;
    public static int HEIGHT  = 720;
    public String title = "Black Jack";
    public static boolean loadedGame = false;

    public static Graphics g;

    private static Thread thread;
    private boolean isRunning = false;
    public static Menu menu;

    public static Player player;
    public static Dealer dealer;
    public static int bet;

    private static InputStream stream;

    private static KeyHandler key;
    private static MouseHandler mouse;
    private Handler handler;

    public static HashMap<Integer, Integer> Values;

    /** Starting the Game */
    public Game()
    {
        new Window(WIDTH, HEIGHT, title, this);

        start();
    }

    public static Card GetRandomCard()
    {
        return new Card(new Random().nextInt(4), new Random().nextInt(13));
    }

    private void FillValues()
    {
        Values.put(0, 11);
        Values.put(1, 2);
        Values.put(2, 3);
        Values.put(3, 4);
        Values.put(4, 5);
        Values.put(5, 6);
        Values.put(6, 7);
        Values.put(7, 8);
        Values.put(8, 9);
        Values.put(9, 10);
        Values.put(10, 10);
        Values.put(11, 10);
        Values.put(12, 10);
        Values.put(13, 10);
    }

    public static void NewGame()
    {
        loadedGame = false;
        bet = 0;
        player.Reset();
        dealer.Reset();
    }

    private void init()
    {
        Values = new HashMap<Integer, Integer>();
        FillValues();

        handler = new Handler();
        key = new KeyHandler(this);
        mouse = new MouseHandler(this);

        File f = new File("C:\\BME VIK\\II. ev\\I. felev\\Prog3\\HF\\BlackJack\\saved_game");

        if (f.exists())
        {
            player = SaveGame.Load();
            loadedGame = true;
            System.out.println("Game loaded from file");
        }
        else
        {
            player = new Player();
        }

        dealer = new Dealer();
        player.State = Player.GAMESTATES.MENU;

        handler.addObject(player);
        handler.addObject(dealer);
    }

    public static Thread GetThread()
    {
        return thread;
    }

    public static InputStream GetStream()
    {
        return stream;
    }

    private synchronized void start()
    {
        if (isRunning) return;

        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    private synchronized void stop()
    {
        if (!isRunning) return;

        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        isRunning = false;
    }

    public void run()
    {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1)
            {
                delta--;
            }
            try
            {
                render();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void render() throws IOException
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        BufferedImage wallpaper;

        // Beolvassa a kártyapakli képet
        stream = getClass().getResourceAsStream("game_wp.png");
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
            stream.close();
        }
        g.drawImage(wallpaper, 0, 0, null);
        handler.render(g);
        bs.show();
        g.dispose();
    }

    public static void main(String[] args)
    {
        new Game();
    }
}