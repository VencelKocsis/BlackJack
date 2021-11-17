import GUI.GuiScreen;

import javax.swing.*;
import java.awt.event.*;

/** JAVA TURORIAL GUI  */

public class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener, Runnable
{
    private GuiScreen screen;

    public GamePanel()
    {
        screen = GuiScreen.getInstance();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

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

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }

    private void render()
    {
        screen.render();
    }

    @Override
    public void run()
    {

    }
}
