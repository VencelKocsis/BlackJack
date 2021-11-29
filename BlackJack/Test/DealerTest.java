import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DealerTest
{
    private Dealer dealer;
    private Game game;

    @Before
    public void setUp() throws Exception
    {
        game = new Game();
        dealer = new Dealer();
        assertNotEquals(null, dealer);
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void getCard()
    {
        Card c = new Card(1, 1);
        dealer.AddCard(c);
        assertNotEquals(null, dealer.getCard(0));
        assertEquals(c, dealer.getCard(1));
    }

    @Test
    public void addCard()
    {
        dealer.AddCard(new Card(1, 1));
        assertNotEquals(0, dealer.getDeckSize());
        assertEquals(2, dealer.getDeckSize());
    }

    @Test
    public void getDeckSize()
    {
        dealer.AddCard(new Card(1, 1));
        assertEquals(2, dealer.getDeckSize());
    }

    @Test
    public void clearDeck()
    {
        dealer.AddCard(new Card(1, 1));
        assertNotEquals(0, dealer.getDeckSize());

        dealer.ClearDeck();
        assertEquals(0, dealer.getDeckSize());
    }

    @Test
    public void getPoints()
    {
        dealer.AddCard(new Card(1, 1));
        assertNotEquals(0, dealer.getDeckSize());
        assertNotEquals(0, dealer.GetPoints());

        dealer.ClearDeck();
        assertEquals(0, dealer.getDeckSize());
        assertNotEquals(2, dealer.GetPoints());
    }

    @Test
    public void reset()
    {

    }

    @Test
    public void foldedCard()
    {

    }

    @Test
    public void render()
    {

    }

    @Test
    public void setUnfold()
    {

    }
}