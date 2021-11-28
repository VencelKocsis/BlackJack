import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

public class MouseHandler implements MouseListener, MouseMotionListener
{
    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;

    public MouseHandler(Game game)
    {
        game.addMouseListener(this);
        game.addMouseMotionListener(this);
    }

    public int getX() {
        return mouseX;
    }

    public int getY() {
        return mouseY;
    }

    public int getButton() {
        return mouseB;
    }


    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (Game.player.State == Player.GAMESTATES.MENU)
        {
            /** NEW GAME BUTTON */
            if (getX() >  150 && getX() < 400 && getY() > 360 && getY() < 420)
            {
                System.out.println("New Game clicked");
                Game.NewGame();
            }
            /** LOAD GAME BUTTON */
            if (getX() >  150 && getX() < 400 && getY() > 460 && getY() < 520)
            {
                System.out.println("Load Game clicked");
                File f = new File("C:\\BME VIK\\II. ev\\I. felev\\Prog3\\HF\\BlackJack\\saved_game");

                if (f.exists())
                {
                    Game.player = SaveGame.Load();
                    System.out.println("Game loaded from file");
                }
                else
                {
                    System.out.println("Nothing has saved -> New Game");
                    Game.NewGame();
                }
            }
            /** EXIT BUTTON */
            if (getX() >  150 && getX() < 400 && getY() > 560 && getY() < 620)
            {
                System.out.println("Exit clicked");
                SaveGame.Save(Game.player);
                try
                {
                    Game.GetStream().close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                //Game.GetThread().stop();
                System.exit(0);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        mouseB = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
