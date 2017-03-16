package dungeon.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import javax.imageio.ImageIO;

import dungeon.Direction;
import dungeon.Main;
import dungeon.Room;

public class Player extends Entity implements KeyListener
{
	HashMap<Direction, BufferedImage> sprites;
	Direction d;
	String in;
	String storedIn;
	BigDecimal dx;
	BigDecimal dy;
	String speed = "0.002";
	
	public Player(Room r, int x, int y, int hp)
	{
		super(r, x, y, hp);
		dx = new BigDecimal(Integer.toString(x));
		dy = new BigDecimal(Integer.toString(y));
		sprites = new HashMap<>();
		d = Direction.North;
		in = "";
		try
		{
			sprites.put(Direction.North, ImageIO.read(getClass().getResourceAsStream("/res/player/player-n.png")));
			sprites.put(Direction.East, ImageIO.read(getClass().getResourceAsStream("/res/player/player-e.png")));
			sprites.put(Direction.South, ImageIO.read(getClass().getResourceAsStream("/res/player/player-s.png")));
			sprites.put(Direction.West, ImageIO.read(getClass().getResourceAsStream("/res/player/player-w.png")));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Main.frame.addKeyListener(this);
		System.out.println("key");
	}
	
	@Override
	public void update()
	{
		//System.out.println(dx + " " + dy);
		if(dx.compareTo(new BigDecimal(Integer.toString(getX()))) == 0  && dy.compareTo(new BigDecimal(Integer.toString(getY()))) == 0)
		{
			storedIn = "";
			if(!in.equals(""))
			{
				storedIn = in;
			}
			if(storedIn.equals("w"))
				moveDirection(Direction.North);
			if(storedIn.equals("a"))
				moveDirection(Direction.West);
			if(storedIn.equals("s"))
				moveDirection(Direction.South);
			if(storedIn.equals("d"))
				moveDirection(Direction.East);
		}
		if((dx.compareTo(new BigDecimal("1")) == 0 && d.equals(Direction.West)) 
				|| (dx.compareTo(new BigDecimal(Integer.toString(room.getXSize()))) == 0 && d.equals(Direction.East)))
		{
			storedIn = "";
		}
		if((dy.compareTo(new BigDecimal("1")) == 0 && d.equals(Direction.North)) 
				|| (dy.compareTo(new BigDecimal(Integer.toString(room.getYSize()))) == 0 && d.equals(Direction.South)))
		{
			storedIn = "";
		}
		
		if(!storedIn.equals(""))
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
	
	@Override
	public void render(Graphics2D g2d, int size)
	{
		g2d.drawImage(sprites.get(d), (int)Math.round(dx.doubleValue()*size), (int)Math.round(dy.doubleValue()*size), size, size, null);
	}
	
	public void moveDirection(Direction d)
	{
		this.d = d;
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		if(arg0.getKeyCode() == KeyEvent.VK_W)
		{
			in = "w";
		}
		if(arg0.getKeyCode() == KeyEvent.VK_D)
		{
			in = "d";
		}
		if(arg0.getKeyCode() == KeyEvent.VK_A)
		{
			in = "a";
		}
		if(arg0.getKeyCode() == KeyEvent.VK_S)
		{
			in = "s";
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_W && in.equals("w"))
		{
			in = "";
		}
		if(arg0.getKeyCode() == KeyEvent.VK_D && in.equals("d"))
		{
			in = "";
		}
		if(arg0.getKeyCode() == KeyEvent.VK_A && in.equals("a"))
		{
			in ="";
		}
		if(arg0.getKeyCode() == KeyEvent.VK_S && in.equals("s"))
		{
			in = "";
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
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
	
	public static double round(double in)
	{
		int tmp = (int)(in*1000);
		return (double)(tmp)/1000.0;
	}
}
