import GUI.GuiScreen;

import java.awt.*;

import static java.lang.String.valueOf;

public class Player extends APlayer
{
    private int money = 1000;
    private KeyHandler keyHandler;

    public Player()
    {
        super();
        keyHandler = new KeyHandler();
    }

    public void input(KeyHandler key)
    {
        key.enter.tick();
        key.space.tick();
        key.tab.tick();
        key.esc.tick();
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawString("Player points: " + valueOf(this.GetPoints()), 400, 500);
        g.drawString("Player money: $" + valueOf(this.GetMoney()), 400, 520);

        if (this.getDeckSize() > 0)
        {
            for (int i = 0; i < getDeckSize(); i++)
            {
                g.drawImage(this.getCard(i).GetImage(), 550 + 71*i, 428, null);
            }
        }
    }

    public int GetMoney()
    {
        return money;
    }

    public void AddMoney(int d)
    {
        money += d;
    }

    public void ReduceMoney(int d)
    {
        money -= d;
    }
}
