package dungeon;

public enum Direction
{
	North(0), East(1), South(2), West(3);
	private final int v;
	Direction(int val){v = val;}
	public Direction next() { return Direction.values()[(v+1)%4]; }
	public Direction last() { return Direction.values()[(v-1)==-1?3:(v-1)]; }
}
