package dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dungeon.entity.Entity;

public class RoomParser
{	
	public static Room parse(String in)
	{
		Room r = null;
		Scanner s = new Scanner(in);
		while(s.hasNextLine())
		{
			String oString = s.nextLine();
			String[] data = oString.split(" ");
			switch(data[0])
			{
			case "size":
				r = new Room(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
				break;
			case "door":
				r.addDoor(new Door(oString));
				break;
			case "flooritem":
				r.addFloorItem(new FloorItem(oString));
				break;
			case "entity":
				r.addEntity(Entity.create(r, data));
			}
			//if(r != null) { System.out.println(r); }
				
		}
		s.close();
		return r;
	}
	
	public static Room parse(File in)
	{
		try
		{
			Scanner s = new Scanner(in);
			String room = "";
			while(s.hasNextLine())
			{
				room += (s.nextLine() + "\n");
			}
			s.close();
			return parse(room);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException("File must exist");
		}
		
	}
}
