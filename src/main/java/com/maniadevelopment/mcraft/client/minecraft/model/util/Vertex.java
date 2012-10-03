package com.maniadevelopment.mcraft.client.minecraft.model.util;

import com.maniadevelopment.mcraft.client.minecraft.model.util.vector.*;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 9:49 PM
 */

/**
 * A 3D vertex.
 * NOTE: Uses the Vector3Df class.
 */
public class Vertex
{
	/**
	 * Create a new vertex.
	 *
	 * @param x X coord.
	 * @param y Y coord.
	 * @param z Z coord.
	 * @param u The horizontal coord for the texture mapping.
	 * @param v The vertical coord for the texture mapping.
	 */
	public Vertex(float x, float y, float z, float u, float v)
	{
		vector = new Vector3Df(x, y, z);
		this.u = u;
		this.v = v;
	}

	/**
	 * Create a new vector with an existing vertex's vector.
	 *
	 * @param vertex An existing vertex. Only uses the vector.
	 * @param u The horizontal coord for the texture mapping.
	 * @param v The vertical coord for the texture mapping.
	 */
	private Vertex(Vertex vertex, float u, float v)
	{
		vector = vertex.vector;
		this.u = u;
		this.v = v;
	}

	/**
	 * The vector.
	 */
	public Vector3Df vector;
	/**
	 * The horizontal coord for the texture mapping.
	 */
	public float u;
	/**
	 * The vertical coord for the texture mapping.
	 */
	public float v;

	/**
	 * Create a new vertex from this vertex with a new u and v.
	 *
	 * @param u The horizontal coord for the texture mapping.
	 * @param v The vertical coord for the texture mapping.
	 * @return The new vertex.
	 */
	public Vertex create(float u, float v)
	{
		Vertex vertex = new Vertex(this, u, v);

		return vertex;
	}
}
