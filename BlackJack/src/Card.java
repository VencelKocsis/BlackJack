import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Card
{
    private CardImage image;
    private int value;
    private boolean ace;
    private int row;
    private int column;
    private static final HashMap<Integer, Integer> Values = new HashMap<Integer, Integer>();

    static
    {
        Values.put(0, 11);
        Values.put(1, 2);
        Values.put(2, 3);
        Values.put(3, 4);
        Values.put(4, 5);
        Values.put(5, 6);
        Values.put(6, 7);
        Values.put(7, 8);
        Values.put(8, 9);
        Values.put(9, 10);
        Values.put(10, 10);
        Values.put(11, 10);
        Values.put(12, 10);
    }

    public Card(int r, int c)
    {
        row = r;
        column = c;
        image = new CardImage();
        SetValue();
    }

    void SetValue()
    {
        value = Values.get(column);

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
