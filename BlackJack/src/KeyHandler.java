import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class KeyHandler implements KeyListener
{
    public static List<Key> keys = new ArrayList<Key>();

    public static class Key
    {
        public int presses, absorbs;
        public boolean down, clicked;

        public Key()
        {
            keys.add(this);
        }

        public void toggle(boolean pressed)
        {
            if (pressed != down)
            {
                down = pressed;
            }
            if (pressed)
            {
                presses++;
            }
        }

        public void tick()
        {
            if (absorbs < presses)
            {
                absorbs++;
            }
            else
            {
                clicked = false;
            }
        }
    }

    public Key enter = new Key();
    public Key space = new Key();
    public Key tab = new Key();
    public Key esc = new Key();

    public KeyHandler(Game game)
    {
        game.addKeyListener(this);
    }

    public KeyHandler()
    {

    }

    public void releaseAll()
    {
        for(int i = 0; i < keys.size(); i++)
        {
            keys.get(i).down = false;
        }
    }

    public void tick()
    {
        for (int i = 0; i < keys.size(); i++)
        {
            keys.get(i).tick();
        }
    }

    public void toggle(KeyEvent e, boolean pressed)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_SPACE) space.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_E) tab.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) esc.toggle(pressed);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        /** ACTION KEYS */
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            System.out.println("Player hit Enter: HIT");
            Game.player.AddCard(Game.GetRandomCard());
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            System.out.println("Player hit Space: STAND");
        }
        if (e.getKeyCode() == KeyEvent.VK_E)
        {
            System.out.println("Player hit Tab: DOUBLE DOWN");
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            System.out.println("Player hit Escape: EXIT");
            //TODO Save();
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
