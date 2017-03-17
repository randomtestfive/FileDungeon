package dungeon.item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import dungeon.Direction;
import dungeon.Door;
import dungeon.Room;
import dungeon.entity.Entity;

public class Key implements Item
{
	private String key;
	private BufferedImage sprite;
	
	@Override
	public void initWithData(String data)
	{
		String[] args = data.split(" ");
		key = args[1];
		try {
			sprite = ImageIO.read(this.getClass().getResourceAsStream("/res/key.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void use(Room room, int x, int y)
	{
		for(Door d : room.getDoors())
		{
			if(d.getX() == x && d.getY() == y)
			{
				d.unlock(key);
			}
		}
	}

	@Override
	public void attack(Room room, int x, int y, Direction d)
	{
		switch (d)
		{
		case East:
			x++;
			break;
		case North:
			y--;
			break;
		case South:
			y++;
			break;
		case West:
			x--;
			break;
		default:
			System.err.println("why");
			break;
		}
		for(Entity e : room.getEntities())
		{
			if(e.getX() == x && e.getY() == y)
			{
				e.hit(1);
			}
		}
	}

	@Override
	public void render(Graphics2D g2d, int x, int y, int size)
	{
		g2d.drawImage(sprite, x, y, size, size, null);
	}

	@Override
	public String toString()
	{
		return "key " + key; 
	}
}
