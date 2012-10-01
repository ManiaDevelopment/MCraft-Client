package com.maniadevelopment.mcraft.client.minecraft.model.util.vector;

import com.maniadevelopment.mcraft.client.minecraft.util.MathHelper;

/**
 * Created with doubleelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 9:05 PM
 */
public class Vector3Dd
{
	public Vector3Dd(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString()
	{
		return "(x: " + x + ", y: " + y + ", z: " + z + ")";
	}

	public double x;
	public double y;
	public double z;

	public Vector3Dd add(double xAdd, double yAdd, double zAdd)
	{
		Vector3Dd vector = new Vector3Dd(x + xAdd, y + yAdd, z + zAdd);

		return vector;
	}

	public Vector3Dd subtract(double xSubtract, double ySubtract, double zSubtract)
	{
		Vector3Dd vector = new Vector3Dd(x - xSubtract, y - ySubtract, z - zSubtract);

		return vector;
	}

	public Vector3Dd multiply(double xMultiply, double yMultiply, double zMultiply)
	{
		Vector3Dd vector = new Vector3Dd(x * xMultiply, y * yMultiply, z * zMultiply);

		return vector;
	}

	public Vector3Dd divide(double xDivide, double yDivide, double zDivide)
	{
		Vector3Dd vector = new Vector3Dd(x / xDivide, y / yDivide, z / zDivide);

		return vector;
	}

	public Vector3Dd normalize()
	{
		double normal = MathHelper.sqrt((float)(x * x + y * y + z * z));
		Vector3Dd vector = new Vector3Dd(x / normal, y / normal, z / normal);

		return vector;
	}

	public double distance(Vector3Dd vector)
	{
		double distanceX = vector.x - x;
		double distanceY = vector.y - y;
		double distanceZ = vector.z - z;

		return distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
	}

	public double distanceSquared(Vector3Dd vector)
	{
		double distanceX = vector.x - x;
		double distanceY = vector.y - y;
		double distanceZ = vector.z - z;

		return MathHelper.sqrt((float)(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ));
	}

	public Vector3Dd getXdoubleersection(Vector3Dd vector, double i)
	{
		double distanceX = vector.x - x;
		double distanceY = vector.y - y;
		double distanceZ = vector.z - z;

		return distanceX * distanceX < 1.0E-7F ? null : ((i = (i - x) / distanceX) >= 0.0F && i <= 1.0F ? new Vector3Dd(x + distanceX * i, y + distanceY * i, z + distanceZ * i) : null);
	}

	public Vector3Dd getYdoubleersection(Vector3Dd vector, double i)
	{
		double distanceX = vector.x - x;
		double distanceY = vector.y - y;
		double distanceZ = vector.z - z;

		return distanceY * distanceY < 1.0E-7F ? null : ((i = (i - y) / distanceY) >= 0.0F && i <= 1.0F ? new Vector3Dd(x + distanceX * i, y + distanceY * i, z + distanceZ * i) : null);
	}

	public Vector3Dd getZdoubleersection(Vector3Dd vector, double i)
	{
		double distanceX = vector.x - x;
		double distanceY = vector.y - y;
		double distanceZ = vector.z - z;

		return distanceZ * distanceZ < 1.0E-7F ? null : ((i = (i - z) / distanceZ) >= 0.0F && i <= 1.0F ? new Vector3Dd(x + distanceX * i, y + distanceY * i, z + distanceZ * i) : null);
	}
}
