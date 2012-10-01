package com.maniadevelopment.mcraft.client.minecraft.util;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 8:34 PM
 */
public class MathHelper
{
	private static float[] SIN_TABLE = new float[65536];

	public static float sin(float number)
	{
		return SIN_TABLE[(int)(number * 10430.378F) & '\uffff'];
	}

	public static float cos(float number) {
		return SIN_TABLE[(int)(number * 10430.378F + 16384.0F) & '\uffff'];
	}

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
