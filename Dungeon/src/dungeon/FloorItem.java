package dungeon;

import java.awt.Graphics2D;
import java.util.Arrays;

import dungeon.item.Item;

public class FloorItem
{
	private Item item;
	private int x;
	private int y;
	
	public FloorItem(Item i, int x, int y)
	{
		item = i;
		this.x = x;
		this.y = y;
	}
	
	public FloorItem(String data)
	{
		String[] args = data.split(" ");
		String[] itemArgs = Arrays.copyOfRange(args, 3, args.length);
		item = Item.create(itemArgs);
		x = Integer.parseInt(args[1]);
		y = Integer.parseInt(args[2]);
	}
	
	public Item getItem()
	{
		return item;
	}
	
	public void render(Graphics2D g2d, int size)
	{
		item.render(g2d, x*size, y*size, size);
	}
	
	public int getX() { return x; }
	public int getY() { return y; }

	@Override
	public String toString()
	{
		return "flooritem " + x + " " + y + " " + item.toString();
	}
}
