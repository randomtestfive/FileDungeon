package dungeon.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import dungeon.Direction;
import dungeon.Room;

public class BasicEnemy extends MoveEntity
{
	
	public BasicEnemy(Room r, int x, int y, int hp)
	{
		super(r, x, y, hp);
		speed = "0.001";
	}
	
	@Override
	public void render(Graphics2D g2d, int size)
	{
		g2d.setColor(Color.red);
		g2d.fillRect((int)Math.round(dx.doubleValue()*size), (int)Math.round(dy.doubleValue()*size), size, size);
	}

	@Override
	public void updateOnGrid()
	{
		NicePlayer near = nearest();
//		System.out.println((((int)Math.toDegrees(Math.atan2(near.getY() - getY(), near.getX() - getX())))+360)%360);
//		System.out.println(Math.round(((((int)Math.toDegrees(Math.atan2(near.getY() - getY(), near.getX() - getX())))+360)%360)/90.0));
		switch((int)Math.round(((((int)Math.toDegrees(Math.atan2(near.getY() - getY(), near.getX() - getX())))+360)%360)/90.0)%4)
		{
		case 3:
			move(Direction.North);
			break;
		case 0:
			move(Direction.East);
			break;
		case 1:
			move(Direction.South);
			break;
		case 2:
			move(Direction.West);
			break;
		}
	}
	
	private NicePlayer nearest()
	{
		NicePlayer out = null;
		for(Entity e : room.getEntities())
		{
			if(e instanceof NicePlayer)
			{
				if(out == null) { out = (NicePlayer) e; continue; }
				if(Point.distance(this.getX(), this.getY(), e.getX(), e.getY()) > Point.distance(this.getX(), this.getY(), out.getX(), out.getY()));
				{
					out = (NicePlayer) e;
				}
			}
		}
		return out;
	}

}
