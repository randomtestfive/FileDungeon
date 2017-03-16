package dungeon.entity;

import java.math.BigDecimal;

import dungeon.Direction;
import dungeon.Room;

public abstract class MoveEntity extends Entity
{
	BigDecimal dx;
	BigDecimal dy;
	Direction d;
	boolean moving;
	String speed = "0.002";

	public MoveEntity(Room r, int x, int y, int hp)
	{
		super(r, x, y, hp);
		dx = new BigDecimal(Integer.toString(x));
		dy = new BigDecimal(Integer.toString(y));
		d = Direction.North;
		moving = false;
	}
	
	@Override
	public void update()
	{
		//System.out.println(dx getX());
		if(dx.compareTo(new BigDecimal(Integer.toString(getX()))) == 0  && dy.compareTo(new BigDecimal(Integer.toString(getY()))) == 0)
		{
			moving = false;
			updateOnGrid();
		}
		
		if((dx.compareTo(new BigDecimal("1")) == 0 && d.equals(Direction.West)) 
				|| (dx.compareTo(new BigDecimal(Integer.toString(room.getXSize()))) == 0 && d.equals(Direction.East)))
		{
			moving = false;
		}
		if((dy.compareTo(new BigDecimal("1")) == 0 && d.equals(Direction.North)) 
				|| (dy.compareTo(new BigDecimal(Integer.toString(room.getYSize()))) == 0 && d.equals(Direction.South)))
		{
			moving = false;
		}
		
		if(moving)
		{
			switch (d)
			{
			case East:
				dx = dx.add(new BigDecimal(speed));
				break;
			case North:
				dy = dy.subtract(new BigDecimal(speed));
				break;
			case South:
				dy = dy.add(new BigDecimal(speed));
				break;
			case West:
				dx = dx.subtract(new BigDecimal(speed));
				break;
			default:
				break;
			}
		}
	}
	
	public void move(Direction dir)
	{
		d = dir;
		moving = true;
	}
	
	@Override
	public int getX()
	{
		return (int)Math.round(dx.doubleValue());
	}
	
	@Override
	public int getY()
	{
		return (int)Math.round(dy.doubleValue());
	}

	public abstract void updateOnGrid();
}
