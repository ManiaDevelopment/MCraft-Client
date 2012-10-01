package com.maniadevelopment.mcraft.client.minecraft.model.util.vector;

import com.maniadevelopment.mcraft.client.minecraft.util.MathHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 9:05 PM
 */
public class Vector3Ds
{
	public Vector3Ds(short x, short y, short z)
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

	public short x;
	public short y;
	public short z;

	public Vector3Ds add(short xAdd, short yAdd, short zAdd)
	{
		Vector3Ds vector = new Vector3Ds((short)(x + xAdd), (short)(y + yAdd), (short)(z + zAdd));

		return vector;
	}

	public Vector3Ds subtract(short xSubtract, short ySubtract, short zSubtract)
	{
		Vector3Ds vector = new Vector3Ds((short)(x - xSubtract), (short)(y - ySubtract), (short)(z - zSubtract));

		return vector;
	}

	public Vector3Ds multiply(short xMultiply, short yMultiply, short zMultiply)
	{
		Vector3Ds vector = new Vector3Ds((short)(x * xMultiply), (short)(y * yMultiply), (short)(z * zMultiply));

		return vector;
	}

	public Vector3Ds divide(short xDivide, short yDivide, short zDivide)
	{
		Vector3Ds vector = new Vector3Ds((short)(x / xDivide), (short)(y / yDivide), (short)(z / zDivide));

		return vector;
	}

	public Vector3Ds normalize()
	{
		short normal = (short)MathHelper.sqrt((float)(x * x + y * y + z * z));
		Vector3Ds vector = new Vector3Ds((short)(x / normal), (short)(y / normal), (short)(z / normal));

		return vector;
	}

	public short distance(Vector3Ds vector)
	{
		short distanceX = (short)(vector.x - x);
		short distanceY = (short)(vector.y - y);
		short distanceZ = (short)(vector.z - z);

		return (short)(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
	}

	public short distanceSquared(Vector3Ds vector)
	{
		short distanceX = (short)(vector.x - x);
		short distanceY = (short)(vector.y - y);
		short distanceZ = (short)(vector.z - z);

		return (short)MathHelper.sqrt((float)(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ));
	}

	public Vector3Ds getXIntersection(Vector3Ds vector, short i)
	{
		short distanceX = (short)(vector.x - x);
		short distanceY = (short)(vector.y - y);
		short distanceZ = (short)(vector.z - z);

		return distanceX * distanceX < 1.0E-7F ? null : ((i = (short)((i - x) / distanceX)) >= 0.0F && i <= 1.0F ? new Vector3Ds((short)(x + distanceX * i), (short)(y + distanceY * i), (short)(z + distanceZ * i)) : null);
	}

	public Vector3Ds getYIntersection(Vector3Ds vector, short i)
	{
		short distanceX = (short)(vector.x - x);
		short distanceY = (short)(vector.y - y);
		short distanceZ = (short)(vector.z - z);

		return distanceY * distanceY < 1.0E-7F ? null : ((i = (short)((i - y) / distanceY)) >= 0.0F && i <= 1.0F ? new Vector3Ds((short)(x + distanceX * i), (short)(y + distanceY * i), (short)(z + distanceZ * i)) : null);
	}

	public Vector3Ds getZIntersection(Vector3Ds vector, short i)
	{
		short distanceX = (short)(vector.x - x);
		short distanceY = (short)(vector.y - y);
		short distanceZ = (short)(vector.z - z);

		return distanceZ * distanceZ < 1.0E-7F ? null : ((i = (short)((i - z) / distanceZ)) >= 0.0F && i <= 1.0F ? new Vector3Ds((short)(x + distanceX * i), (short)(y + distanceY * i), (short)(z + distanceZ * i)) : null);
	}
}
