import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
                Game.player.State = Player.GAMESTATES.INITIAL_BET;
                Game.player.NewGame();
            }
            /** LOAD GAME BUTTON */
            if (getX() >  150 && getX() < 400 && getY() > 460 && getY() < 520)
            {
                System.out.println("Load Game clicked");
                Game.player.State = Player.GAMESTATES.INITIAL_BET;
            }
            /** EXIT BUTTON */
            if (getX() >  150 && getX() < 400 && getY() > 560 && getY() < 620)
            {
                System.out.println("Exit clicked");
                SaveGame.Save(Game.player);
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
