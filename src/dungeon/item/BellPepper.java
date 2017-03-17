package dungeon.item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import dungeon.Direction;
import dungeon.Room;

public class BellPepper implements Item
{
	BufferedImage bellPepper;

	public BellPepper()
	{
		try
		{
			bellPepper = ImageIO.read(getClass().getResourceAsStream("/res/bellpepper.png.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void initWithData(String data)
	{
		
	}

	@Override
	public void use(Room room, int x, int y)
	{
		
	}

	@Override
	public void attack(Room room, int x, int y, Direction d)
	{
		
	}

	@Override
	public void render(Graphics2D g2d, int x, int y, int size)
	{
		g2d.drawImage(bellPepper, x, y, size, size, null);
	}
	
	@Override
	public String toString()
	{
		return "bellpepper";
	}

}
