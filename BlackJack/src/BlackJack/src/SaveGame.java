import java.io.*;

public class SaveGame
{
    public static void Save(Player p)
    {
        try
        {
            FileOutputStream f = new FileOutputStream("saved_game");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(p);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Game SAVED");
    }

    public static Player Load()
    {
        Player p = null;
        try
        {
            FileInputStream f = new FileInputStream("saved_game");
            ObjectInputStream in = new ObjectInputStream(f);
            p = (Player) in.readObject();
            in.close();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return p;
    }
}
