package dungeon.inventory;

import java.awt.Graphics2D;

import javax.swing.JFrame;

import dungeon.FloorItem;
import dungeon.Room;
import dungeon.entity.NicePlayer;
import dungeon.item.Item;

public abstract class Inventory
{
	protected NicePlayer player;
	protected Room room;
	
	public Inventory(NicePlayer p)
	{
		player = p;
		room = player.getRoom();
	}
	
	public void dropItem(Item i)
	{
		room.addFloorItem(new FloorItem(i, player.getX(), player.getY()));
	}
	
	public void setRoom(Room r) { room = r; }
	
	public abstract void open();
	public abstract void close();
	public abstract boolean isOpen();
	public abstract boolean addItem(Item i);
	public abstract void addListeners(JFrame frame);
	public abstract void render(Graphics2D g2d);
}
