import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class APlayer implements Serializable
{
    private ArrayList<Card> deck;
    private int points;

    public APlayer()
    {
        deck = new ArrayList<Card>();
        points = 0;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public Card getCard(int index)
    {
        return deck.get(index);
    }

    public void AddCard(Card c)
    {
        deck.add(c);
        if (c.IsAce() && points + 11 > 21)
        {
            points += 1;
        }
        else
        {
            points += c.GetValue();
        }
    }

    public int getDeckSize()
    {
        return deck.size();
    }

    public void ClearDeck()
    {
        deck.clear();
        points = 0;
    }

    int GetPoints()
    {
        return points;
    }
}
