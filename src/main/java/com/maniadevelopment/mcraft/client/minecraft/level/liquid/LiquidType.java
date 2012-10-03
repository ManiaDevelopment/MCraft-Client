package com.maniadevelopment.mcraft.client.minecraft.level.liquid;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 9:57 PM
 */

/**
 * Define the possible liquid types.
 */
public class LiquidType
{
	/**
	 * The creation of a liquid type.
	 *
	 * @param type The type.
	 */
	private LiquidType(int type)
	{
		values = new LiquidType[4];

		values[type] = this;
	}

	/**
	 * LiquidType values.
	 */
	public LiquidType[] values;

	/**
	 * Represents a block that is not liquid.
	 */
	public static final LiquidType NOT_LIQUID = new LiquidType(0);
	/**
	 * Represents a block that is water.
	 */
	public static final LiquidType WATER = new LiquidType(1);
	/**
	 * Represents a block that is lava.
	 */
	public static final LiquidType LAVA = new LiquidType(2);
}
