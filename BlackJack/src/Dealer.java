import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.String.valueOf;

public class Dealer extends APlayer
{
    private boolean unfold = false;

    public Dealer()
    {
        this.AddCard(Game.GetRandomCard());
    }

    public void FoldedCard(Graphics g)
    {
        BufferedImage back_image;

        // Beolvassa a kártyapakli képet
        InputStream stream = getClass().getResourceAsStream("cardback.png");
        try
        {
            assert stream != null;
            back_image = ImageIO.read(stream);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            g.drawImage(Image.resizeImage(back_image, 2 * 71, 2 * 96), 550, 0, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        g.drawImage(this.getCard(0).GetImage(), 550 + 71, 0, null);
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.ORANGE);

        if (Game.player.State != Player.GAMESTATES.INITIAL_BET && !this.unfold)
        {
            FoldedCard(g);
        }
        else if (this.unfold)
        {
            g.drawString("Dealer points: " + valueOf(this.GetPoints()), 150, 100);
            if (this.getDeckSize() > 0)
            {
                for (int i = 0; i < getDeckSize(); i++)
                {
                    g.drawImage(this.getCard(i).GetImage(), 550 + 71*i, 0, null);
                }
            }
        }
    }

    public void setUnfold(boolean unfold)
    {
        this.unfold = unfold;
    }
}
