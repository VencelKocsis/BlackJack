import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest
{
    private Player player;
    private Game game;

    @Before
    public void setUp() throws Exception
    {
        player = new Player();
        game = new Game();
        assertNotEquals(null, player);
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void getCard()
    {
        Card c = new Card(1, 1);
        player.AddCard(c);
        assertNotEquals(null, player.getCard(0));
        assertEquals(c, player.getCard(0));
    }

    @Test
    public void addCard()
    {
        player.AddCard(new Card(1, 1));
        assertNotEquals(0, player.getDeckSize());
        assertEquals(1, player.getDeckSize());
    }

    @Test
    public void getDeckSize()
    {
        player.AddCard(new Card(1, 1));
        assertEquals(1, player.getDeckSize());
    }

    @Test
    public void clearDeck()
    {
        player.AddCard(new Card(1, 1));
        assertNotEquals(0, player.getDeckSize());

        player.ClearDeck();
        assertEquals(0, player.getDeckSize());
    }

    @Test
    public void getPoints()
    {
        player.AddCard(new Card(1, 1));
        assertNotEquals(0, player.getDeckSize());
        assertEquals(2, player.GetPoints());

        player.ClearDeck();
        assertEquals(0, player.getDeckSize());
        assertNotEquals(2, player.GetPoints());
    }

    @Test
    public void reset()
    {

    }

    @Test
    public void initRound()
    {
        player.InitRound();
        assertNotEquals(Player.GAMESTATES.MENU, player.State);
        assertEquals(Player.GAMESTATES.INITIAL_BET, player.State);
        assertNotEquals(1, player.getDeckSize());
        assertEquals(0, player.getDeckSize());
        assertNotEquals(true, player.wasStand);
        assertFalse(player.wasStand);
    }

    @Test
    public void render()
    {

    }

    @Test
    public void getMoney()
    {
        assertEquals(1000, player.GetMoney());
    }

    @Test
    public void addMoney()
    {
        player.AddMoney(100);
        assertEquals(1100, player.GetMoney());
    }

    @Test
    public void reduceMoney()
    {
        player.ReduceMoney(100);
        assertEquals(900, player.GetMoney());
    }
}