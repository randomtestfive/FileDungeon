package dungeon;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dungeon.entity.Entity;

public class Main
{
	public static JFrame frame;
	public static JPanel label;
	static RoomRenderer rr;
	
	public static void main(String[] args) throws IOException
	{
		frame = new JFrame("Image");
		label = new JPanel()
		{
			private static final long serialVersionUID = 6390681861392019965L;
			
			@Override
			public void paint(Graphics g)
			{
				Graphics2D g2d = (Graphics2D) g;
				rr.render(g2d, 50);
				g2d.dispose();
			}
		};
		Room r = RoomParser.parse(new File("H:/HORNETAY000/AP/textDungeon/Dungeon/src/level/dungeon_entrance.room"));
		System.out.println(r);
		Tileset ts = new Tileset("H:/HORNETAY000/AP/textDungeon/Dungeon/src/res");
		rr = new RoomRenderer(r, ts);
		int size = 50;
		
		while(true)
		{
			label.setPreferredSize(new Dimension((r.getXSize()+2)*size, (r.getYSize()+2)*size));
			label.repaint();
			
			frame.add(label);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			for(Entity e: r.getEntities())
			{
				e.update();
			}
		}
		
	}

}
