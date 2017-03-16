package dungeon;

import java.util.ArrayList;
import java.util.List;

import dungeon.entity.Entity;

public class Room
{
	private List<Door> doors;
	private List<FloorItem> floorItems;
	private List<Entity> entities;
	private int xSize;
	private int ySize;
	
	public Room(int x, int y)
	{
		doors = new ArrayList<Door>();
		floorItems = new ArrayList<FloorItem>();
		entities = new ArrayList<Entity>();
		xSize = x;
		ySize = y;
	}
	
	public void addDoor(Door d)
	{
		doors.add(d);
	}
	
	public void addFloorItem(FloorItem fi)
	{
		floorItems.add(fi);
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
	
	public List<Door> getDoors() { return doors; }
	public List<Entity> getEntities() { return entities; }
	public List<FloorItem> getFloorItems() { return floorItems; }
	public int getXSize() { return xSize; }
	public int getYSize() { return ySize; }
	
	public void update()
	{
		for(Entity e : entities)
		{
			e.update();
		}
	}
	
	@Override
	public String toString()
	{
		String out = "size " + getXSize() + " " + getYSize();
		for(Door d : getDoors())
		{
			out += "\n" + d.toString();
		}
		for(FloorItem fl : getFloorItems())
		{
			out += "\n" + fl.toString();
		}
		return out;
	}
}
