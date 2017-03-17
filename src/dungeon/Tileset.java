package dungeon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tileset
{
	ArrayList<BufferedImage> floor;
	BufferedImage wall_n;
	BufferedImage wall_e;
	BufferedImage wall_s;
	BufferedImage wall_w;
	
	static long seed;
	
	public Tileset(String folderdir) throws IOException
	{
		floor = new ArrayList<>();
		int i = 0;
		File f;
		seed = System.nanoTime();
		while((f = new File(folderdir + "/floor-" + i + ".png")).exists())
		{
			floor.add(ImageIO.read(f));
			i++;
		}
		wall_n = ImageIO.read(new File(folderdir + "/wall-n.png"));
		wall_e = ImageIO.read(new File(folderdir + "/wall-e.png"));
		wall_s = ImageIO.read(new File(folderdir + "/wall-s.png"));
		wall_w = ImageIO.read(new File(folderdir + "/wall-w.png"));
	}
	
	public BufferedImage getFloorTile(long hash)
	{
		Random r = new Random(hash*seed);
		int out = r.nextInt(floor.size());
		return floor.get(out);
	}
	
	public BufferedImage getWall(Direction dir)
	{
		switch (dir)
		{
		case East:
			return wall_e;
		case North:
			return wall_n;
		case South:
			return wall_s;
		case West:
			return wall_w;
		default:
			System.err.println("why");
			return null;
		}
	}
}
