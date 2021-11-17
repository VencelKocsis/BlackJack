import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class CardImage
{
    // A kártyapakli képből kivágva
    private final int width = 71; // egy kártyalap szélessége
    private final int height = 96; // egy kártyalap magassába

    BufferedImage deckImage; // a kártyapakli képe (cards.png)

    public CardImage()
    {
        // Beolvassa a kártyapakli képet
        InputStream stream = getClass().getResourceAsStream("cards.png");
        try
        {
            assert stream != null;
            deckImage = ImageIO.read(stream);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    BufferedImage getImage(int row, int column)
    {
        int x = 1 + (column * 2) + column * width; // a kivágott kártya szélessége
        int y = 1 + (row * 2) + row * height; // a kivágott kártya magassága

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(deckImage, 0, 0, width, height, x, y, x + width, y + height, null);

        // Felnagyítom a képet kétszeresére
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(2, 2);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
        image = bilinearScaleOp.filter(image, new BufferedImage(2*width, 2*height, image.getType()));

        return image;
    }
}














