package com.maniadevelopment.mcraft.client.minecraft.model.util.vector;

import com.maniadevelopment.mcraft.client.minecraft.util.MathHelper;

/**
 * Created with floatelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 9:05 PM
 */
public class Vector3Df
{
	public Vector3Df(float x, float y, float z)
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

	public float x;
	public float y;
	public float z;

	public Vector3Df add(float xAdd, float yAdd, float zAdd)
	{
		Vector3Df vector = new Vector3Df(x + xAdd, y + yAdd, z + zAdd);

		return vector;
	}

	public Vector3Df subtract(float xSubtract, float ySubtract, float zSubtract)
	{
		Vector3Df vector = new Vector3Df(x - xSubtract, y - ySubtract, z - zSubtract);

		return vector;
	}

	public Vector3Df multiply(float xMultiply, float yMultiply, float zMultiply)
	{
		Vector3Df vector = new Vector3Df(x * xMultiply, y * yMultiply, z * zMultiply);

		return vector;
	}

	public Vector3Df divide(float xDivide, float yDivide, float zDivide)
	{
		Vector3Df vector = new Vector3Df(x / xDivide, y / yDivide, z / zDivide);

		return vector;
	}

	public Vector3Df normalize()
	{
		float normal = MathHelper.sqrt(x * x + y * y + z * z);
		Vector3Df vector = new Vector3Df(x / normal, y / normal, z / normal);

		return vector;
	}

	public float distance(Vector3Df vector)
	{
		float distanceX = vector.x - x;
		float distanceY = vector.y - y;
		float distanceZ = vector.z - z;

		return distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
	}

	public float distanceSquared(Vector3Df vector)
	{
		float distanceX = vector.x - x;
		float distanceY = vector.y - y;
		float distanceZ = vector.z - z;

		return MathHelper.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
	}

	public Vector3Df getXfloatersection(Vector3Df vector, float i)
	{
		float distanceX = vector.x - x;
		float distanceY = vector.y - y;
		float distanceZ = vector.z - z;

		return distanceX * distanceX < 1.0E-7F ? null : ((i = (i - x) / distanceX) >= 0.0F && i <= 1.0F ? new Vector3Df(x + distanceX * i, y + distanceY * i, z + distanceZ * i) : null);
	}

	public Vector3Df getYfloatersection(Vector3Df vector, float i)
	{
		float distanceX = vector.x - x;
		float distanceY = vector.y - y;
		float distanceZ = vector.z - z;

		return distanceY * distanceY < 1.0E-7F ? null : ((i = (i - y) / distanceY) >= 0.0F && i <= 1.0F ? new Vector3Df(x + distanceX * i, y + distanceY * i, z + distanceZ * i) : null);
	}

	public Vector3Df getZfloatersection(Vector3Df vector, float i)
	{
		float distanceX = vector.x - x;
		float distanceY = vector.y - y;
		float distanceZ = vector.z - z;

		return distanceZ * distanceZ < 1.0E-7F ? null : ((i = (i - z) / distanceZ) >= 0.0F && i <= 1.0F ? new Vector3Df(x + distanceX * i, y + distanceY * i, z + distanceZ * i) : null);
	}
}
