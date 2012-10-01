package com.maniadevelopment.mcraft.client.minecraft.render;

import com.mojang.util.MathHelper;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 10:30 PM
 */
public class Frustrum
{
	public Frustrum()
	{
	}

	private static Frustrum instance = new Frustrum();

	public float[][] frustrum = new float[16][16];
	public float[] projection = new float[16];
	public float[] modelview = new float[16];
	public float[] clipping = new float[16];

	public FloatBuffer projectionBuff = BufferUtils.createFloatBuffer(16);
	public FloatBuffer modelviewBuff = BufferUtils.createFloatBuffer(16);
	public FloatBuffer unused = BufferUtils.createFloatBuffer(16);

	public static Frustrum update()
	{
		Frustrum frustrum1 = instance;

		instance.projectionBuff.clear();
		frustrum1.modelviewBuff.clear();
		frustrum1.unused.clear();

		GL11.glGetFloat(2983, frustrum1.projectionBuff);
		GL11.glGetFloat(2982, frustrum1.modelviewBuff);

		frustrum1.projectionBuff.flip().limit(16);
		frustrum1.projectionBuff.get(frustrum1.projection);

		frustrum1.modelviewBuff.flip().limit(16);
		frustrum1.modelviewBuff.get(frustrum1.modelview);

		frustrum1.clipping[0] = frustrum1.modelview[0] * frustrum1.projection[0] + frustrum1.modelview[1] * frustrum1.projection[4] + frustrum1.modelview[2] * frustrum1.projection[8] + frustrum1.modelview[3] * frustrum1.projection[12];
		frustrum1.clipping[1] = frustrum1.modelview[0] * frustrum1.projection[1] + frustrum1.modelview[1] * frustrum1.projection[5] + frustrum1.modelview[2] * frustrum1.projection[9] + frustrum1.modelview[3] * frustrum1.projection[13];
		frustrum1.clipping[2] = frustrum1.modelview[0] * frustrum1.projection[2] + frustrum1.modelview[1] * frustrum1.projection[6] + frustrum1.modelview[2] * frustrum1.projection[10] + frustrum1.modelview[3] * frustrum1.projection[14];
		frustrum1.clipping[3] = frustrum1.modelview[0] * frustrum1.projection[3] + frustrum1.modelview[1] * frustrum1.projection[7] + frustrum1.modelview[2] * frustrum1.projection[11] + frustrum1.modelview[3] * frustrum1.projection[15];
		frustrum1.clipping[4] = frustrum1.modelview[4] * frustrum1.projection[0] + frustrum1.modelview[5] * frustrum1.projection[4] + frustrum1.modelview[6] * frustrum1.projection[8] + frustrum1.modelview[7] * frustrum1.projection[12];
		frustrum1.clipping[5] = frustrum1.modelview[4] * frustrum1.projection[1] + frustrum1.modelview[5] * frustrum1.projection[5] + frustrum1.modelview[6] * frustrum1.projection[9] + frustrum1.modelview[7] * frustrum1.projection[13];
		frustrum1.clipping[6] = frustrum1.modelview[4] * frustrum1.projection[2] + frustrum1.modelview[5] * frustrum1.projection[6] + frustrum1.modelview[6] * frustrum1.projection[10] + frustrum1.modelview[7] * frustrum1.projection[14];
		frustrum1.clipping[7] = frustrum1.modelview[4] * frustrum1.projection[3] + frustrum1.modelview[5] * frustrum1.projection[7] + frustrum1.modelview[6] * frustrum1.projection[11] + frustrum1.modelview[7] * frustrum1.projection[15];
		frustrum1.clipping[8] = frustrum1.modelview[8] * frustrum1.projection[0] + frustrum1.modelview[9] * frustrum1.projection[4] + frustrum1.modelview[10] * frustrum1.projection[8] + frustrum1.modelview[11] * frustrum1.projection[12];
		frustrum1.clipping[9] = frustrum1.modelview[8] * frustrum1.projection[1] + frustrum1.modelview[9] * frustrum1.projection[5] + frustrum1.modelview[10] * frustrum1.projection[9] + frustrum1.modelview[11] * frustrum1.projection[13];
		frustrum1.clipping[10] = frustrum1.modelview[8] * frustrum1.projection[2] + frustrum1.modelview[9] * frustrum1.projection[6] + frustrum1.modelview[10] * frustrum1.projection[10] + frustrum1.modelview[11] * frustrum1.projection[14];
		frustrum1.clipping[11] = frustrum1.modelview[8] * frustrum1.projection[3] + frustrum1.modelview[9] * frustrum1.projection[7] + frustrum1.modelview[10] * frustrum1.projection[11] + frustrum1.modelview[11] * frustrum1.projection[15];
		frustrum1.clipping[12] = frustrum1.modelview[12] * frustrum1.projection[0] + frustrum1.modelview[13] * frustrum1.projection[4] + frustrum1.modelview[14] * frustrum1.projection[8] + frustrum1.modelview[15] * frustrum1.projection[12];
		frustrum1.clipping[13] = frustrum1.modelview[12] * frustrum1.projection[1] + frustrum1.modelview[13] * frustrum1.projection[5] + frustrum1.modelview[14] * frustrum1.projection[9] + frustrum1.modelview[15] * frustrum1.projection[13];
		frustrum1.clipping[14] = frustrum1.modelview[12] * frustrum1.projection[2] + frustrum1.modelview[13] * frustrum1.projection[6] + frustrum1.modelview[14] * frustrum1.projection[10] + frustrum1.modelview[15] * frustrum1.projection[14];
		frustrum1.clipping[15] = frustrum1.modelview[12] * frustrum1.projection[3] + frustrum1.modelview[13] * frustrum1.projection[7] + frustrum1.modelview[14] * frustrum1.projection[11] + frustrum1.modelview[15] * frustrum1.projection[15];

		frustrum1.frustrum[0][0] = frustrum1.clipping[3] - frustrum1.clipping[0];
		frustrum1.frustrum[0][1] = frustrum1.clipping[7] - frustrum1.clipping[4];
		frustrum1.frustrum[0][2] = frustrum1.clipping[11] - frustrum1.clipping[8];
		frustrum1.frustrum[0][3] = frustrum1.clipping[15] - frustrum1.clipping[12];
		normalize(frustrum1.frustrum, 0);

		frustrum1.frustrum[1][0] = frustrum1.clipping[3] + frustrum1.clipping[0];
		frustrum1.frustrum[1][1] = frustrum1.clipping[7] + frustrum1.clipping[4];
		frustrum1.frustrum[1][2] = frustrum1.clipping[11] + frustrum1.clipping[8];
		frustrum1.frustrum[1][3] = frustrum1.clipping[15] + frustrum1.clipping[12];
		normalize(frustrum1.frustrum, 1);

		frustrum1.frustrum[2][0] = frustrum1.clipping[3] + frustrum1.clipping[1];
		frustrum1.frustrum[2][1] = frustrum1.clipping[7] + frustrum1.clipping[5];
		frustrum1.frustrum[2][2] = frustrum1.clipping[11] + frustrum1.clipping[9];
		frustrum1.frustrum[2][3] = frustrum1.clipping[15] + frustrum1.clipping[13];
		normalize(frustrum1.frustrum, 2);

		frustrum1.frustrum[3][0] = frustrum1.clipping[3] - frustrum1.clipping[1];
		frustrum1.frustrum[3][1] = frustrum1.clipping[7] - frustrum1.clipping[5];
		frustrum1.frustrum[3][2] = frustrum1.clipping[11] - frustrum1.clipping[9];
		frustrum1.frustrum[3][3] = frustrum1.clipping[15] - frustrum1.clipping[13];
		normalize(frustrum1.frustrum, 3);

		frustrum1.frustrum[4][0] = frustrum1.clipping[3] - frustrum1.clipping[2];
		frustrum1.frustrum[4][1] = frustrum1.clipping[7] - frustrum1.clipping[6];
		frustrum1.frustrum[4][2] = frustrum1.clipping[11] - frustrum1.clipping[10];
		frustrum1.frustrum[4][3] = frustrum1.clipping[15] - frustrum1.clipping[14];
		normalize(frustrum1.frustrum, 4);

		frustrum1.frustrum[5][0] = frustrum1.clipping[3] + frustrum1.clipping[2];
		frustrum1.frustrum[5][1] = frustrum1.clipping[7] + frustrum1.clipping[6];
		frustrum1.frustrum[5][2] = frustrum1.clipping[11] + frustrum1.clipping[10];
		frustrum1.frustrum[5][3] = frustrum1.clipping[15] + frustrum1.clipping[14];
		normalize(frustrum1.frustrum, 5);

		return instance;
	}

	private static void normalize(float[][] frustrum, int i)
	{
		float normal = MathHelper.sqrt(frustrum[i][0] * frustrum[i][0] + frustrum[i][1] * frustrum[i][1] + frustrum[i][2] * frustrum[i][2]);

		frustrum[i][0] /= normal;
		frustrum[i][1] /= normal;
		frustrum[i][2] /= normal;
		frustrum[i][3] /= normal;
	}

	public boolean isBoxInFrustrum(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		for(int i = 0; i < 6; i++)
		{
			if(frustrum[i][0] * x1 + frustrum[i][1] * y1 + frustrum[i][2] * z1 + frustrum[i][3] <= 0.0F 
					&& frustrum[i][0] * x2 + frustrum[i][1] * y1 + frustrum[i][2] * z1 + frustrum[i][3] <= 0.0F 
					&& frustrum[i][0] * x1 + frustrum[i][1] * y2 + frustrum[i][2] * z1 + frustrum[i][3] <= 0.0F 
					&& frustrum[i][0] * x2 + frustrum[i][1] * y2 + frustrum[i][2] * z1 + frustrum[i][3] <= 0.0F 
					&& frustrum[i][0] * x1 + frustrum[i][1] * y1 + frustrum[i][2] * z2 + frustrum[i][3] <= 0.0F 
					&& frustrum[i][0] * x2 + frustrum[i][1] * y1 + frustrum[i][2] * z2 + frustrum[i][3] <= 0.0F 
					&& frustrum[i][0] * x1 + frustrum[i][1] * y2 + frustrum[i][2] * z2 + frustrum[i][3] <= 0.0F 
					&& frustrum[i][0] * x2 + frustrum[i][1] * y2 + frustrum[i][2] * z2 + frustrum[i][3] <= 0.0F)
			{
				return false;
			}
		}

		return true;
	}
}
