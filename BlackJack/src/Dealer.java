import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
            g.drawImage(resizeImage(back_image, 2 * 71, 2 * 96), 550, 0, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        g.drawImage(this.getCard(0).GetImage(), 550 + 71, 0, null);
    }

    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException
    {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.BLACK);

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

    public boolean isUnfold()
    {
        return unfold;
    }

    public void setUnfold(boolean unfold)
    {
        this.unfold = unfold;
    }
}
