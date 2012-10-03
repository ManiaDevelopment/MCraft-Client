package com.maniadevelopment.mcraft.client.minecraft.model.util.vector;

import com.maniadevelopment.mcraft.client.minecraft.util.MathHelper;

/**
 * Created with floatelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 9:05 PM
 */

/**
 * 3D Vector using floats.
 */
public class Vector3Df
{
	/**
	 * Create a new vector with floats.
	 *
	 * @param x x coord.
	 * @param y y coord.
	 * @param z z coord.
	 */
	public Vector3Df(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Override toString to return a more logical and informational string.
	 *
	 * @return A more logical response.
	 */
	@Override
	public String toString()
	{
		return "(x: " + x + ", y: " + y + ", z: " + z + ")";
	}

	/**
	 * The x coord.
	 */
	public float x;
	/**
	 * The y coord.
	 */
	public float y;
	/**
	 * The z coord.
	 */
	public float z;

	/**
	 * Add to x, y, and z.
	 * NOTE: Use 0 if you don't want to add.
	 *
	 * @param xAdd Amount to add to x.
	 * @param yAdd Amount to add to y.
	 * @param zAdd Amount to add to z.
	 * @return The new vector formed.
	 */
	public Vector3Df add(float xAdd, float yAdd, float zAdd)
	{
		Vector3Df vector = new Vector3Df(x + xAdd, y + yAdd, z + zAdd);

		return vector;
	}

	/**
	 * Subtract from x, y, and z.
	 * NOTE: Use 0 if you don't want to subtract.
	 *
	 * @param xSubtract Amount to subtract from x.
	 * @param ySubtract Amount to subtract from y.
	 * @param zSubtract Amount to subtract from z.
	 * @return The new vector formed.
	 */
	public Vector3Df subtract(float xSubtract, float ySubtract, float zSubtract)
	{
		Vector3Df vector = new Vector3Df(x - xSubtract, y - ySubtract, z - zSubtract);

		return vector;
	}

	/**
	 * Multiply x, y, and z.
	 * NOTE: Use 1 if you don't want to multiply.
	 *
	 * @param xMultiply Amount to multiply by x.
	 * @param yMultiply Amount to multiply by y.
	 * @param zMultiply Amount to multiply by z.
	 * @return The new vector formed.
	 */
	public Vector3Df multiply(float xMultiply, float yMultiply, float zMultiply)
	{
		Vector3Df vector = new Vector3Df(x * xMultiply, y * yMultiply, z * zMultiply);

		return vector;
	}

	/**
	 * Divide x, y, and z.
	 * NOTE: Use 1 if you want to divide.
	 *
	 * @param xDivide Amount to divide x by.
	 * @param yDivide Amount to divide y by.
	 * @param zDivide Amount to divide z by.
	 * @return The new vector formed.
	 */
	public Vector3Df divide(float xDivide, float yDivide, float zDivide)
	{
		Vector3Df vector = new Vector3Df(x / xDivide, y / yDivide, z / zDivide);

		return vector;
	}

	/**
	 * Get the normalized vector of this vector.
	 *
	 * @return The normalized vector.
	 */
	public Vector3Df normalize()
	{
		float normal = MathHelper.sqrt(x * x + y * y + z * z);
		Vector3Df vector = new Vector3Df(x / normal, y / normal, z / normal);

		return vector;
	}

	/**
	 * Find the distance between this vector and another.
	 *
	 * @param vector The other vector.
	 * @return The distance.
	 */
	public float distance(Vector3Df vector)
	{
		float distanceX = vector.x - x;
		float distanceY = vector.y - y;
		float distanceZ = vector.z - z;

		return distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
	}

	/**
	 * Find the distance between this vector and another squared.
	 *
	 * @param vector The other vector.
	 * @return The distance squared.
	 */
	public float distanceSquared(Vector3Df vector)
	{
		float distanceX = vector.x - x;
		float distanceY = vector.y - y;
		float distanceZ = vector.z - z;

		return MathHelper.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
	}

	/**
	 * Get the X intersection of this vector and another.
	 *
	 * @param vector The other vector.
	 * @param i
	 * @return The x intersection.
	 */
	public Vector3Df getXIntersection(Vector3Df vector, float i)
	{
		float distanceX = vector.x - x;
		float distanceY = vector.y - y;
		float distanceZ = vector.z - z;

		return distanceX * distanceX < 1.0E-7F ? null : ((i = (i - x) / distanceX) >= 0.0F && i <= 1.0F ? new Vector3Df(x + distanceX * i, y + distanceY * i, z + distanceZ * i) : null);
	}

	/**
	 * Get the Y intersection of this vector and another.
	 *
	 * @param vector The other vector.
	 * @param i
	 * @return The y intersection.
	 */
	public Vector3Df getYIntersection(Vector3Df vector, float i)
	{
		float distanceX = vector.x - x;
		float distanceY = vector.y - y;
		float distanceZ = vector.z - z;

		return distanceY * distanceY < 1.0E-7F ? null : ((i = (i - y) / distanceY) >= 0.0F && i <= 1.0F ? new Vector3Df(x + distanceX * i, y + distanceY * i, z + distanceZ * i) : null);
	}

	/**
	 * Get the Z intersection of this vector and another.
	 *
	 * @param vector The other vector.
	 * @param i
	 * @return The z intersection.
	 */
	public Vector3Df getZIntersection(Vector3Df vector, float i)
	{
		float distanceX = vector.x - x;
		float distanceY = vector.y - y;
		float distanceZ = vector.z - z;

		return distanceZ * distanceZ < 1.0E-7F ? null : ((i = (i - z) / distanceZ) >= 0.0F && i <= 1.0F ? new Vector3Df(x + distanceX * i, y + distanceY * i, z + distanceZ * i) : null);
	}
}
