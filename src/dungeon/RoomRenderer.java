package dungeon;

import java.awt.Graphics2D;

import dungeon.entity.Entity;

public class RoomRenderer
{
	private Room r;
	private Tileset ts;
	
	public RoomRenderer(Room room, Tileset tileset)
	{
		r = room;
		ts = tileset;
	}
	
	public void render(Graphics2D g2d, int tilesize)
	{
		for(int y = tilesize; y < (r.getYSize()+1)*tilesize; y+=tilesize)
		{
			g2d.drawImage(ts.getWall(Direction.East), 0, y, tilesize, tilesize, null);
		}
		for(int y = tilesize; y < (r.getYSize()+1)*tilesize; y+=tilesize)
		{
			g2d.drawImage(ts.getWall(Direction.West), (r.getXSize())*tilesize+tilesize, y, tilesize, tilesize, null);
		}
		for(int x = tilesize; x < (r.getXSize()+1)*tilesize; x+=tilesize)
		{
			g2d.drawImage(ts.getWall(Direction.North), x, 0, tilesize, tilesize, null);
		}
		for(int x = tilesize; x < (r.getXSize()+1)*tilesize; x+=tilesize)
		{
			g2d.drawImage(ts.getWall(Direction.South), x, (r.getYSize())*tilesize+tilesize, tilesize, tilesize, null);
		}
		for(int y = tilesize; y < (r.getYSize()+1)*tilesize; y+=tilesize)
		{
			for(int x = tilesize; x < (r.getXSize()+1)*tilesize; x+=tilesize)
			{
				g2d.drawImage(ts.getFloorTile(hashPair(x, y)), x, y, tilesize, tilesize, null);
			}
		}
		for(Door d : r.getDoors())
		{
			d.render(g2d, tilesize);
		}
		for(FloorItem fi : r.getFloorItems())
		{
			fi.render(g2d, tilesize);
		}
		for(Entity e : r.getEntities())
		{
			e.render(g2d, tilesize);
		}
	}
	
	public static int hashPair(int x, int y)
	{
		return (int) Math.round((0.5)*(x + y)*(x + y + 1) + y);
	}
}
