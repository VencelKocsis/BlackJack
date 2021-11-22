import java.awt.*;
import java.util.LinkedList;

public class Handler
{
    public static LinkedList<APlayer> object = new LinkedList<APlayer>();

    // Runs through everything
    public void tick()
    {
        for (APlayer aPlayer : object)
        {
            aPlayer.tick();
        }
    }

    // Renders everything
    public void render(Graphics g)
    {
        for (APlayer aPlayer : object)
        {
            aPlayer.render(g);
        }
    }

    public APlayer addObject(APlayer tempObject)
    {
        object.add(tempObject);
        return tempObject;
    }

    public void removeObject(APlayer tempObject)
    {
        object.remove(tempObject);
    }
}