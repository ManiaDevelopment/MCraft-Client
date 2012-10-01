package com.maniadevelopment.mcraft.client.minecraft.model.util;

import com.maniadevelopment.mcraft.client.minecraft.model.util.vector.*;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 9:49 PM
 */
public class Vertex
{
	public Vertex(float x, float y, float z, float u, float v)
	{
		vector = new Vector3Df(x, y, z);
		this.u = u;
		this.v = v;
	}

	private Vertex(Vertex vertex, float u, float v)
	{
		vector = vertex.vector;
		this.u = u;
		this.v = v;
	}

	public Vector3Df vector;
	public float u;
	public float v;

	public Vertex create(float u, float v)
	{
		Vertex vertex = new Vertex(this, u, v);

		return vertex;
	}
}
