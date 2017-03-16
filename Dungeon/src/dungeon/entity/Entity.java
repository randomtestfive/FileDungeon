package dungeon.entity;

import java.awt.Graphics2D;

import dungeon.Room;

public class Entity
{
	protected int x;
	protected int y;
	protected int hp;
	protected Room room;
	
	public Entity(Room r, int x, int y, int hp)
	{
		this.x = x;
		this.y = y;
		this.hp = hp;
		room = r;
	}
	
	public void render(Graphics2D g2d, int size){}
	public void update(){}
	public void hit(int attack) { hp-=attack; }
	public boolean isDead() { return hp <= 0; }
	public int getHP() { return hp; }
	public int getX() { return x; }
	public int getY() { return y; }
	
	public static Entity create(Room r, String[] args)
	{
		if(args[1].equals("player"))
		{
			return new NicePlayer(r, Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
		}
		if(args[1].equals("basicenemy"))
		{
			return new BasicEnemy(r, Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
		}
		return null;
	}
}
