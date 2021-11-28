import java.awt.*;

import static java.lang.String.valueOf;

public class Player extends APlayer
{
    private int money;
    public boolean wasStand;

    public enum GAMESTATES
    {
        INITIAL_BET,
        GAME,
        BLACKJACK,
        BUST,
        WINNER,
        STAND,
        LOSE,
        DRAW,
        MENU
    }

    public void Reset()
    {
        money = 1000;
        State = Player.GAMESTATES.INITIAL_BET;
        ClearDeck();
        wasStand = false;
    }

    public void InitRound()
    {
        Game.bet = 0;
        Game.player.State = Player.GAMESTATES.INITIAL_BET;
        Game.player.ClearDeck();
        Game.dealer.ClearDeck();
        Game.player.wasStand = false;
        Game.dealer.setUnfold(false);
        SaveGame.Save(Game.player);
    }

    public GAMESTATES State;

    public Player()
    {
        State = GAMESTATES.INITIAL_BET;
        money = 1000;
        wasStand = false;
    }

    @Override
    public void render(Graphics g)
    {
        Font f2 = new Font("SansSerif", Font.BOLD, 20);
        g.setColor(Color.BLUE);
        g.setFont(f2);
        g.drawString("Moves: HIT (enter)| STAND (space)| DOUBLE DOWN (y) | ALL IN (a)", 620, 670);

        Font f = new Font("SansSerif", Font.BOLD, 40);
        g.setFont(f);

        g.setColor(Color.ORANGE);
        g.drawString("Player points: " + valueOf(this.GetPoints()), 150, 500);
        g.drawString("Player money: $" + valueOf(this.GetMoney()), 150, 560);

        g.setColor(Color.RED);

        switch (State)
        {
            case WINNER:
                if (wasStand)
                {
                    if (Game.dealer.GetPoints() > 21)
                    {
                        g.drawString("DEALER BUSTED  PLAYER WINS: $" + valueOf(2 * Game.bet), 400, Game.HEIGHT/2);
                    }
                    else
                    {
                        g.drawString("PLAYER WINS: $" + valueOf(2 * Game.bet), 400, Game.HEIGHT/2);
                    }
                }
                else
                {
                    g.drawString("PLAYER WINS $" + valueOf(2 * Game.bet), 400, Game.HEIGHT/2);
                }
                break;
            case BUST:
                g.drawString("ITS A BUST", 400, Game.HEIGHT/2);
                break;
            case BLACKJACK:
                g.drawString("PLAYER HAS BLACKJACK! WINS: " + valueOf(2.5 * Game.bet), 400, Game.HEIGHT/2);
                break;
            case LOSE:
                g.drawString("PLAYER HAS LOST", 400, Game.HEIGHT/2);
                break;
            case DRAW:
                g.drawString("PLAYER GETS INITIAL BET: " + valueOf(Game.bet), 400, Game.HEIGHT/2);
                break;
            case MENU:
                Game.menu = new Menu(g);
                break;
            default:
                g.drawString("BET: " + valueOf(Game.bet), 400, Game.HEIGHT/2);
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
