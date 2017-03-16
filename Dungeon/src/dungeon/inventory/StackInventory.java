package dungeon.inventory;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import dungeon.entity.NicePlayer;
import dungeon.item.Item;

public class StackInventory extends Inventory implements KeyListener
{
	private boolean opened = false;
	private ArrayList<Item> stack;
	private int size;

	public StackInventory(NicePlayer p)
	{
		super(p);
		stack = new ArrayList<>();
		size = 5;
	}

	@Override
	public void open()
	{
		opened = true;
	}

	@Override
	public void close()
	{
		opened = false;
	}

	@Override
	public boolean addItem(Item i)
	{
		if(stack.size() == size)
		{
			dropItem(stack.get(0));
			stack.remove(0);
		}
		stack.add(i);
		return true;
	}

	@Override
	public void render(Graphics2D g2d)
	{
//		System.out.print(isOpen());
//		System.out.println(stack);
		for(int y = 0; y < stack.size(); y++)
		{
			int s = 40;
			if(stack.size() - 1 == y && isOpen())
			{
				s = 80;
			}
			stack.get(y).render(g2d, 10, (y*50)+10, s);
		}
	}

	@Override
	public void addListeners(JFrame frame)
	{
		frame.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		//System.out.println("key");
		if(opened)
		{
			if(arg0.getKeyCode() == KeyEvent.VK_Z)
			{
				//System.out.println(stack.get(stack.size()-1));
				if(stack.size() > 0)
				{
					dropItem(stack.get(stack.size()-1));
					stack.remove(stack.size()-1);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public boolean isOpen()
	{
		return opened;
	}
}
