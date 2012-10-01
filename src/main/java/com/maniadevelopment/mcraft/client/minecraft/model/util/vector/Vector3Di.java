package com.maniadevelopment.mcraft.client.minecraft.model.util.vector;

import com.maniadevelopment.mcraft.client.minecraft.util.MathHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 9:05 PM
 */
public class Vector3Di
{
	public Vector3Di(int x, int y, int z)
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

	public int x;
	public int y;
	public int z;

	public Vector3Di add(int xAdd, int yAdd, int zAdd)
	{
		Vector3Di vector = new Vector3Di(x + xAdd, y + yAdd, z + zAdd);

		return vector;
	}

	public Vector3Di subtract(int xSubtract, int ySubtract, int zSubtract)
	{
		Vector3Di vector = new Vector3Di(x - xSubtract, y - ySubtract, z - zSubtract);

		return vector;
	}

	public Vector3Di multiply(int xMultiply, int yMultiply, int zMultiply)
	{
		Vector3Di vector = new Vector3Di(x * xMultiply, y * yMultiply, z * zMultiply);

		return vector;
	}

	public Vector3Di divide(int xDivide, int yDivide, int zDivide)
	{
		Vector3Di vector = new Vector3Di(x / xDivide, y / yDivide, z / zDivide);

		return vector;
	}

	public Vector3Di normalize()
	{
		int normal = (int)MathHelper.sqrt((float)(x * x + y * y + z * z));
		Vector3Di vector = new Vector3Di(x / normal, y / normal, z / normal);

		return vector;
	}

	public int distance(Vector3Di vector)
	{
		int distanceX = vector.x - x;
		int distanceY = vector.y - y;
		int distanceZ = vector.z - z;

		return distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
	}

	public int distanceSquared(Vector3Di vector)
	{
		int distanceX = vector.x - x;
		int distanceY = vector.y - y;
		int distanceZ = vector.z - z;

		return (int)MathHelper.sqrt((float)(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ));
	}

	public Vector3Di getXIntersection(Vector3Di vector, int i)
	{
		int distanceX = vector.x - x;
		int distanceY = vector.y - y;
		int distanceZ = vector.z - z;

		return distanceX * distanceX < 1.0E-7F ? null : ((i = (i - x) / distanceX) >= 0.0F && i <= 1.0F ? new Vector3Di(x + distanceX * i, y + distanceY * i, z + distanceZ * i) : null);
	}

	public Vector3Di getYIntersection(Vector3Di vector, int i)
	{
		int distanceX = vector.x - x;
		int distanceY = vector.y - y;
		int distanceZ = vector.z - z;

		return distanceY * distanceY < 1.0E-7F ? null : ((i = (i - y) / distanceY) >= 0.0F && i <= 1.0F ? new Vector3Di(x + distanceX * i, y + distanceY * i, z + distanceZ * i) : null);
	}

	public Vector3Di getZIntersection(Vector3Di vector, int i)
	{
		int distanceX = vector.x - x;
		int distanceY = vector.y - y;
		int distanceZ = vector.z - z;

		return distanceZ * distanceZ < 1.0E-7F ? null : ((i = (i - z) / distanceZ) >= 0.0F && i <= 1.0F ? new Vector3Di(x + distanceX * i, y + distanceY * i, z + distanceZ * i) : null);
	}
}
