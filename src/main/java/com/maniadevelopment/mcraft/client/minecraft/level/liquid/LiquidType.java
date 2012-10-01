package com.maniadevelopment.mcraft.client.minecraft.level.liquid;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 9:57 PM
 */
public class LiquidType
{
	private LiquidType(int type)
	{
		values = new LiquidType[4];

		values[type] = this;
	}

	public LiquidType[] values;

	public static final LiquidType NOT_LIQUID = new LiquidType(0);
	public static final LiquidType WATER = new LiquidType(1);
	public static final LiquidType LAVA = new LiquidType(2);
}
