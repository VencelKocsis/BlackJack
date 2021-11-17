import java.awt.*;
import java.util.ArrayList;

public abstract class APlayer
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
        points += c.GetValue();
    }

    public int getDeckSize()
    {
        return deck.size();
    }

    public void ClearDeck()
    {
        deck.clear();
    }

    int GetPoints()
    {
        return points;
    }
}
