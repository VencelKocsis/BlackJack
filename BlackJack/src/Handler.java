import java.awt.*;
import java.util.LinkedList;

public class Handler
{
    public LinkedList<APlayer> object = new LinkedList<APlayer>();

    // Runs through everything
    public void tick()
    {
        for(int i = 0; i < object.size(); i++)
        {
            object.get(i).tick();
        }
    }

    // Renders everything
    public void render(Graphics g)
    {
        for(int i = 0; i < object.size(); i++)
        {
            object.get(i).render(g);
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