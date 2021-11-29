import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    public KeyHandler(Game game)
    {
        game.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (Game.player.State)
        {
            case INITIAL_BET:
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_UP:
                        if (Game.bet <= Game.player.GetMoney() - 100)
                            Game.bet += 100;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (e.getKeyCode() == KeyEvent.VK_DOWN && Game.bet >= 100)
                            Game.bet -= 100;
                        break;
                    case KeyEvent.VK_A:
                        System.out.println("Player hit A: ALL IN");
                        Game.bet = Game.player.GetMoney();
                        break;
                    case KeyEvent.VK_ENTER:
                        if (Game.bet == 0)
                        {
                            Game.player.State = Player.GAMESTATES.INITIAL_BET;
                            System.out.println("Select Initial pot!");
                        }
                        else
                        {
                            Game.player.ReduceMoney(Game.bet);
                            Game.player.State = Player.GAMESTATES.GAME;
                            Game.dealer.AddCard(Game.GetRandomCard());
                            Game.player.AddCard(Game.GetRandomCard());
                            Game.player.AddCard(Game.GetRandomCard());
                            System.out.println("Initial Pot: " + Game.bet);
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.out.println("Player hit ESCAPE: MENU from Initial");
                        Game.player.State = Player.GAMESTATES.MENU;
                        SaveGame.Save(Game.player);
                        break;
                }
                break;
            case GAME:
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_ENTER:
                        System.out.println("Player hit Enter: HIT");
                        Game.player.AddCard(Game.GetRandomCard());
                        Game.dealer.setUnfold(true);
                        break;
                    case KeyEvent.VK_SPACE:
                        System.out.println("Player hit Space: STAND");
                        Game.player.State = Player.GAMESTATES.STAND;
                        Game.player.wasStand = true;
                        Game.dealer.setUnfold(true);
                        break;
                    case KeyEvent.VK_Y:
                        System.out.println("Player hit Tab: DOUBLE DOWN");
                        Game.player.ReduceMoney(Game.bet);
                        Game.bet *= 2;
                        Game.player.AddCard(Game.GetRandomCard());
                        Game.dealer.setUnfold(true);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.out.println("Player hit ESCAPE: MENU from Game");
                        SaveGame.Save(Game.player);
                        Game.player.State = Player.GAMESTATES.MENU;
                        break;
                    default:
                        System.out.println("Wrong input");
                        break;
                }
                /** DEALER HAS BLACKJACK */
                if (Game.dealer.GetPoints() == 21)
                {
                    Game.player.State = Player.GAMESTATES.LOSE;
                }
                /** PLAYER HAS BLACKJACK */
                else if(Game.player.GetPoints() == 21)
                {
                    Game.player.State = Player.GAMESTATES.BLACKJACK;
                }
                /** DEALER BUSTED */
                if (Game.player.GetPoints() > 21)
                {
                    Game.player.State = Player.GAMESTATES.BUST;
                    System.out.println("Its a BUST Player lose: " + Game.bet);
                }
                /** PLAYER STAND */
                if (Game.player.wasStand)
                {
                    /** DEALER BUSTED | PLAYER WINS */
                    Game.dealer.AddCard(Game.GetRandomCard());
                    if (Game.dealer.GetPoints() > 21)
                    {
                        Game.player.State = Player.GAMESTATES.WINNER;
                    }
                    /** DEALER WINS */
                    else if (Game.dealer.GetPoints() > Game.player.GetPoints())
                    {
                        Game.player.State = Player.GAMESTATES.LOSE;
                    }
                    /** PLAYER WINS */
                    else if (Game.player.GetPoints() > Game.dealer.GetPoints())
                    {
                        Game.player.State = Player.GAMESTATES.WINNER;

                        /** PLAYER HAS BLACKJACK */
                        if (Game.player.GetPoints() == 21)
                        {
                            Game.player.State = Player.GAMESTATES.BLACKJACK;
                        }
                    }
                    /** DRAW */
                    else if (Game.player.GetPoints() == Game.dealer.GetPoints())
                    {
                        Game.player.State = Player.GAMESTATES.DRAW;
                    }
                }
                break;
            case BLACKJACK:
                Game.player.AddMoney((int) (2.5 * Game.bet));
                Game.player.InitRound();
                break;
            case BUST:
            case LOSE:
                Game.bet = 0;
                Game.player.State = Player.GAMESTATES.INITIAL_BET;
                Game.player.InitRound();
                break;
            case WINNER:
                Game.player.AddMoney(2 * Game.bet);
                Game.player.InitRound();
                break;
            case STAND:
                Game.player.wasStand = true;
                break;
            case DRAW:
                Game.player.AddMoney(Game.bet);
                Game.player.InitRound();
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
