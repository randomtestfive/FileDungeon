package dungeon.item;

import java.awt.Graphics2D;

import dungeon.*;

public interface Item
{
	public abstract void initWithData(String data);
	public abstract void use(Room room, int x, int y);
	public abstract void attack(Room room, int x, int y, Direction d);
	public abstract void render(Graphics2D g2d, int x, int y, int size);
	
	public static Item create(String[] args)
	{
		Item item = null;
		switch(args[0])
		{
		case "key":
			item = new Key();
			item.initWithData(args[0] + " " + args[1]);
			break;
		case "bellpepper":
			item = new BellPepper();
			break;
		}
		return item;
	}
}
