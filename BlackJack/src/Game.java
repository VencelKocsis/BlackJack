import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
    public static int WIDTH = 1280;
    public static int HEIGHT  = 720;
    public String title = "Black Jack";

    private Thread thread;
    private boolean isRunning = false;

    public static Player player;
    public static Dealer dealer;
    public static int pot;

    private static KeyHandler key;
    private MouseHandler mouse;
    private Handler handler;

    /** Starting the Game */
    public Game()
    {
        new Window(WIDTH, HEIGHT, title, this);

        start();
    }

    public static Card GetRandomCard()
    {
        Random random = new Random();
        return new Card(random.nextInt(4), random.nextInt(14));
    }

    private void init()
    {
        handler = new Handler();
        key = new KeyHandler(this);

        File f = new File("C:\\BME VIK\\II. ev\\I. felev\\Prog3\\HF\\BlackJack\\saved_game");

        if (f.exists())
        {
            player = SaveGame.Load();
            System.out.println("Game loaded from file");
        }
        else
        {
            player = new Player();
        }

        dealer = new Dealer();

        handler.addObject(player);
        handler.addObject(dealer);
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
                tick();
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    //Updates the game
    private void tick()
    {
        handler.tick();
    }

    private void render()
    {
        // Renders the game
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(81,80,77));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        bs.show();
        g.dispose();
    }

    public static void main(String[] args)
    {
        new Game();
    }
}