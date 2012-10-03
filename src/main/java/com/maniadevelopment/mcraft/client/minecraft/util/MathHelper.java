package com.maniadevelopment.mcraft.client.minecraft.util;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 8:34 PM
 */

/**
 * An advance version of Math. Sick bro?!
 */
public class MathHelper
{
	/**
	 * The sin table.
	 */
	private static float[] SIN_TABLE = new float[65536];

	/**
	 * Sine a number.
	 *
	 * @param number Number to sine.
	 * @return The sined number.
	 */
	public static float sin(float number)
	{
		return SIN_TABLE[(int)(number * 10430.378F) & '\uffff'];
	}

	/**
	 * Cosine a number.
	 *
	 * @param number Number to cosine.
	 * @return The cosined number.
	 */
	public static float cos(float number)
	{
		return SIN_TABLE[(int)(number * 10430.378F + 16384.0F) & '\uffff'];
	}

	/**
	 * Get the square root of a number.
	 *
	 * @param number The number to get the square root of.
	 * @return The square root of the number.
	 */
	public static float sqrt(float number)
	{
		return (float)Math.sqrt((double)number);
	}

	static
	{
		for(int i = 0; i < 65536; i++)
		{
			SIN_TABLE[i] = (float)Math.sin((double)i * 3.141592653589793D * 2.0D / 65536.0D);
		}

	}
}
