import java.awt.*;
import static java.lang.String.valueOf;

public class Dealer extends APlayer
{
    public Dealer()
    {
        super();
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawString("Dealer points: " + valueOf(this.GetPoints()), 400, 100);

        if (this.getDeckSize() > 0)
        {
            for (int i = 0; i < getDeckSize(); i++)
            {
                g.drawImage(this.getCard(i).GetImage(), 550 + 71*i, 0, null);
            }
        }
    }
}
