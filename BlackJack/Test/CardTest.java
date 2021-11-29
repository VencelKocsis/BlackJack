import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CardTest
{
    private Card c;
    private HashMap<Integer, Integer> Values;
    Game game;

    @Before
    public void setUp() throws Exception
    {
        game = new Game();
        c = new Card(1, 1);
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void setValue()
    {
        c.SetValue();
        assertEquals(2, c.GetValue());
    }

    @Test
    public void getImage()
    {

    }

    @Test
    public void getValue()
    {
        assertEquals(2, c.GetValue());
        assertNotEquals(5, c.GetValue());
    }

    @Test
    public void isAce()
    {
        Card c1 = new Card(0, 0);
        Card c2 = new Card(1, 13);

        assertTrue(c1.IsAce());
        assertFalse(c2.IsAce());
    }
}