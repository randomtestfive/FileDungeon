package dungeon;

public class Door
{
	private boolean unlocked;
	private String keyRequired;
	private int x;
	private int y;
	private int outX;
	private int outY;
	private String roomLink;
	public final static String KEY_NONE = "NONE";
	public final static String KEY_ANY = "ANY";
	
	public Door(String data)
	{
		String[] args = data.split(" ");
		x = Integer.parseInt(args[1]);
		y = Integer.parseInt(args[2]);
		outX = Integer.parseInt(args[3]);
		outY = Integer.parseInt(args[4]);
		roomLink = args[5];
		keyRequired = args[6];
		unlocked = !(keyRequired == KEY_NONE);
	}
	
	public boolean unlock(String key)
	{
		if(keyRequired == KEY_ANY || keyRequired == key)
		{
			unlocked = true;
			return true;
		}
		return false;
	}
	
	public boolean isUnlocked(){ return unlocked; }
	public int getX() { return x; }
	public int getY() { return y; }
	
	@Override 
	public String toString()
	{
		return "door " + x + " " + y + " " + outX + " " + outY + " " + roomLink + " " + keyRequired;
	}
}
