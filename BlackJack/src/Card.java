import java.awt.image.BufferedImage;

public class Card
{
    private CardImage image;
    private int value;
    private boolean ace;
    private final int row;
    private final int column;
    public Card(int r, int c)
    {
        row = r;
        column = c;
        image = new CardImage();
        SetValue();
    }

    void SetValue()
    {
        value = Game.Values.get(column);

        if (column != 0) ace = false;
        else ace = true;
    }

    BufferedImage GetImage()
    {
        return image.getImage(row, column);
    }
    int GetValue()
    {
        return value;
    }
    boolean IsAce()
    {
        return ace;
    }
}
