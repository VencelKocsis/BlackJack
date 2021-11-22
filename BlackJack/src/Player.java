import java.awt.*;

import static java.lang.String.valueOf;

public class Player extends APlayer
{
    private int money = 1000;
    public boolean wasStand = false;

    public enum GAMESTATES
    {
        INITIAL_POT,
        GAME,
        BLACKJACK,
        BUST,
        WINNER,
        STAND,
        LOSE,
        DRAW
    }

    public void InitRound()
    {
        Game.pot = 0;
        Game.player.State = Player.GAMESTATES.INITIAL_POT;
        Game.player.ClearDeck();
        Game.player.setEditablePot(true);
        Game.dealer.ClearDeck();
        Game.player.wasStand = false;
    }

    public GAMESTATES State;

    private boolean editablePot = true;

    public Player()
    {
        super();
        State = GAMESTATES.INITIAL_POT;
    }

    public boolean isEditablePot()
    {
        return editablePot;
    }

    public void setEditablePot(boolean editablePot)
    {
        this.editablePot = editablePot;
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics g)
    {
        Font f2 = new Font("SansSerif", Font.BOLD, 20);
        g.setColor(Color.BLUE);
        g.setFont(f2);
        g.drawString("Moves: HIT (enter)| STAND (space)| DOUBLE DOWN (y)", 660, 670);

        Font f = new Font("SansSerif", Font.BOLD, 40);
        g.setFont(f);

        g.setColor(Color.BLACK);
        g.drawString("Player points: " + valueOf(this.GetPoints()), 150, 500);
        g.drawString("Player money: $" + valueOf(this.GetMoney()), 150, 560);

        g.setColor(Color.RED);

        switch (State)
        {
            case WINNER:
                if (wasStand)
                {
                    g.drawString("DEALER BUSTED  PLAYER WINS: $" + valueOf(2 * Game.pot), 400, Game.HEIGHT/2);
                }
                else
                {
                    g.drawString("PLAYER WINS $" + valueOf(2 * Game.pot), 400, Game.HEIGHT/2);
                }
                break;
            case BUST:
                g.drawString("ITS A BUST", 400, Game.HEIGHT/2);
                break;
            case BLACKJACK:
                g.drawString("PLAYER HAS BLACKJACK! WINS: " + valueOf(2.5 * Game.pot), 400, Game.HEIGHT/2);
                break;
            case LOSE:
                g.drawString("PLAYER HAS LOST", 400, Game.HEIGHT/2);
                break;
            case DRAW:
                g.drawString("PLAYER GETS INITIAL POT: " + valueOf(Game.pot), 400, Game.HEIGHT/2);
                break;
            default:
                g.drawString("Pot: " + valueOf(Game.pot), 400, Game.HEIGHT/2);
                break;
        }

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
