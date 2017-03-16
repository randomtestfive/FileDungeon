package dungeon.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import dungeon.Direction;
import dungeon.FloorItem;
import dungeon.Main;
import dungeon.Room;
import dungeon.inventory.Inventory;
import dungeon.inventory.StackInventory;

public class NicePlayer extends MoveEntity implements KeyListener
{
	HashMap<Direction, ArrayList<BufferedImage>> sprites;
	ArrayList<Direction> inputs;
	Inventory inventory;

	public NicePlayer(Room r, int x, int y, int hp)
	{
		super(r, x, y, hp);
		sprites = new HashMap<>();
		try
		{
			for(Direction d : Direction.values())
			{
				sprites.put(d, new ArrayList<BufferedImage>());
				char dir = d.toString().charAt(0);
				dir = Character.toLowerCase(dir);
				int i = 0;
				while(getClass().getResourceAsStream("/res/player/player-" + dir + "-" + i + ".png") != null)
				{
					sprites.get(d).add(ImageIO.read(getClass().getResourceAsStream("/res/player/player-" + dir + "-" + i + ".png")));
					i++;
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		inputs = new ArrayList<>();
		inventory = new StackInventory(this);
		inventory.addListeners(Main.frame);
		Main.frame.addKeyListener(this);
	}

	@Override
	public void updateOnGrid()
	{
		if(!inputs.isEmpty())
		move(inputs.get(inputs.size()-1));
		inventory.render(null);
	}
	
	@Override
	public void render(Graphics2D g2d, int size)
	{
		g2d.drawImage(sprites.get(d).get(0), (int)Math.round(dx.doubleValue()*size), (int)Math.round(dy.doubleValue()*size), size, size, null);
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		if(!inventory.isOpen())
		{
			if(arg0.getKeyCode() == KeyEvent.VK_W || arg0.getKeyCode() == KeyEvent.VK_UP)
			{
				inputs.remove(Direction.North);
				inputs.add(Direction.North);
			}
			if(arg0.getKeyCode() == KeyEvent.VK_S || arg0.getKeyCode() == KeyEvent.VK_DOWN)
			{
				inputs.remove(Direction.South);
				inputs.add(Direction.South);
			}
			if(arg0.getKeyCode() == KeyEvent.VK_D || arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				inputs.remove(Direction.East);
				inputs.add(Direction.East);
			}
			if(arg0.getKeyCode() == KeyEvent.VK_A || arg0.getKeyCode() == KeyEvent.VK_LEFT)
			{
				inputs.remove(Direction.West);
				inputs.add(Direction.West);
			}
			if(arg0.getKeyCode() == KeyEvent.VK_C)
			{
				inventory.open();
			}
			if(arg0.getKeyCode() == KeyEvent.VK_Z)
			{
				FloorItem r = null;
				for(FloorItem fi : room.getFloorItems())
				{
					if(fi.getX() == getX() && fi.getY() == getY())
					{
						r = fi;
					}
				}
				if(r != null)
				{
					room.getFloorItems().remove(r);
					inventory.addItem(r.getItem());
				}
			}
		}
		else
		{
			if(arg0.getKeyCode() == KeyEvent.VK_X)
			{
				inventory.close();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		if(arg0.getKeyCode() == KeyEvent.VK_W || arg0.getKeyCode() == KeyEvent.VK_UP)
		{
			inputs.remove(Direction.North);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_S || arg0.getKeyCode() == KeyEvent.VK_DOWN)
		{
			inputs.remove(Direction.South);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_D || arg0.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			inputs.remove(Direction.East);
		}
		if(arg0.getKeyCode() == KeyEvent.VK_A || arg0.getKeyCode() == KeyEvent.VK_LEFT)
		{
			inputs.remove(Direction.West);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	public Room getRoom() { return room; }

}
