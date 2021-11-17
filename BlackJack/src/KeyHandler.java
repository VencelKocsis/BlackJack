import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener
{
    public static List<Key> keys = new ArrayList<Key>();

    public static class Key
    {
        public int presses, absorbs;
        public boolean down, clicked;

        public Key()
        {
            keys.add(this);
        }

        public void tick()
        {
            if (absorbs < presses)
            {
                absorbs++;
            }
            else
            {
                clicked = false;
            }
        }
    }

    public KeyHandler(Game game)
    {
        game.addKeyListener(this);
    }

    public KeyHandler()
    {

    }

    public void releaseAll()
    {
        for(int i = 0; i < keys.size(); i++)
        {
            keys.get(i).down = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (Game.player.State)
        {
            case INITIAL_POT:
                if (Game.player.isEditablePot())
                {
                    if (e.getKeyCode() == KeyEvent.VK_UP && Game.pot <= Game.player.GetMoney() - 100)
                    {
                        Game.pot += 100;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN && Game.pot >= 100)
                    {
                        Game.pot -= 100;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        Game.player.setEditablePot(false);
                        Game.player.ReduceMoney(Game.pot);
                        Game.player.State = Player.GAMESTATES.GAME;
                        Game.dealer.AddCard(Game.GetRandomCard());
                        Game.dealer.AddCard(Game.GetRandomCard());
                        System.out.println("Initial Pot: " + Game.pot);
                    }
                }
                break;
            case GAME:
                if (Game.player.wasStand)
                {
                    if (Game.player.GetPoints() == Game.dealer.GetPoints())
                    {
                        Game.player.State = Player.GAMESTATES.DRAW;
                        System.out.println("Player Has Initial Pot" + Game.pot);
                    }
                    Game.dealer.AddCard(Game.GetRandomCard());
                    if (Game.dealer.GetPoints() > 21)
                    {
                        Game.player.State = Player.GAMESTATES.WINNER;
                        System.out.println("Player Wins: " + 2 * Game.pot);
                    }
                    else
                    {
                        if (Game.dealer.GetPoints() > Game.player.GetPoints())
                        {
                            Game.player.State = Player.GAMESTATES.LOSE;
                        }
                        else if (Game.player.GetPoints() > Game.dealer.GetPoints())
                        {
                            Game.player.State = Player.GAMESTATES.WINNER;
                        }
                    }
                }
                else
                {
                    if (Game.player.GetPoints() > 21)
                    {
                        Game.player.State = Player.GAMESTATES.BUST;
                        System.out.println("Its a BUST Player lose: " + 2 * Game.pot);
                    }
                    else
                    {
                        if (Game.player.GetPoints() > Game.dealer.GetPoints() && Game.player.getDeckSize() >= 2)
                        {
                            if (Game.player.GetPoints() <= 21)
                            {
                                if (Game.player.GetPoints() == 21 && Game.player.getDeckSize() == 2)
                                {
                                    Game.player.State = Player.GAMESTATES.BLACKJACK;
                                    System.out.println("Player has BLACKJACK and Wins: " + 2.5 * Game.pot);
                                }
                                Game.player.State = Player.GAMESTATES.WINNER;
                                System.out.println("Player Wins: " + 2 * Game.pot);
                            }
                        }
                        else
                        {
                            switch (e.getKeyCode())
                            {
                                case KeyEvent.VK_ENTER:
                                    System.out.println("Player hit Enter: HIT");
                                    Game.player.AddCard(Game.GetRandomCard());
                                    break;
                                case KeyEvent.VK_SPACE:
                                    System.out.println("Player hit Space: STAND");
                                    Game.player.State = Player.GAMESTATES.STAND;
                                    break;
                                case KeyEvent.VK_E:
                                    // NEW GAME at this moment
                                    Game.player.ClearDeck();
                                    Game.player.setEditablePot(true);
                                    break;
                                case KeyEvent.VK_Y:
                                    System.out.println("Player hit Tab: DOUBLE DOWN");
                                    Game.pot *= 2;
                                    Game.player.AddCard(Game.GetRandomCard());
                                    break;
                                case KeyEvent.VK_ESCAPE:
                                    System.out.println("Player hit Escape: EXIT");
                                    System.exit(0);
                                    //TODO Save();
                                    break;
                                default:
                                    System.out.println("Wrong input");
                                    break;
                            }
                        }
                    }
                }
                break;
            case BLACKJACK:
                Game.player.AddMoney((int) (2.5 * Game.pot));
                Game.pot = 0;
                Game.player.State = Player.GAMESTATES.INITIAL_POT;
                Game.player.ClearDeck();
                Game.player.setEditablePot(true);
                Game.dealer.ClearDeck();
                Game.player.wasStand = false;
                break;
            case BUST:
            case LOSE:
                Game.pot = 0;
                Game.player.State = Player.GAMESTATES.INITIAL_POT;
                Game.player.ClearDeck();
                Game.player.setEditablePot(true);
                Game.dealer.ClearDeck();
                Game.player.wasStand = false;
                break;
            case WINNER:
                Game.player.AddMoney(2 * Game.pot);
                Game.player.State = Player.GAMESTATES.INITIAL_POT;
                Game.pot = 0;
                Game.player.ClearDeck();
                Game.player.setEditablePot(true);
                Game.dealer.ClearDeck();
                Game.player.wasStand = false;
                break;
            case STAND:
                Game.dealer.AddCard(Game.GetRandomCard());
                Game.player.wasStand = true;
                break;
            case DRAW:
                Game.player.AddMoney(Game.pot);
                Game.pot = 0;
                Game.player.ClearDeck();
                Game.player.setEditablePot(true);
                Game.dealer.ClearDeck();
                Game.player.wasStand = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
