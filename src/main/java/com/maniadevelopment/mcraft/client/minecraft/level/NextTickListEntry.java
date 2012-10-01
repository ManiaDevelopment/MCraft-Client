package com.maniadevelopment.mcraft.client.minecraft.level;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 10:01 PM
 */
public class NextTickListEntry
{
	public NextTickListEntry(int x, int y, int z, int block)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.block = block;
	}

	public int x;
	public int y;
	public int z;
	public int block;
	public int ticks;
}
