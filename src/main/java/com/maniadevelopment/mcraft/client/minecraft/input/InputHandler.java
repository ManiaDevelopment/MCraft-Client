package com.maniadevelopment.mcraft.client.minecraft.input;

import com.maniadevelopment.mcraft.client.minecraft.settings.GameSettings;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 10:22 PM
 */
public class InputHandler
{
	public InputHandler(GameSettings gameSettings)
	{
		this.gameSettings = gameSettings;

		xxa = 0.0F;
		yya = 0.0F;
		jumping = false;

		keyStates = new boolean[10];
	}

	public GameSettings gameSettings;

	public float xxa;
	public float yya;
	public boolean jumping;

	public boolean[] keyStates;

	public void updateMovement()
	{
		xxa = 0.0F;
		yya = 0.0F;

		if(keyStates[0])
		{
			yya--;
		}

		if(keyStates[1])
		{
			yya++;
		}

		if(keyStates[2])
		{
			xxa--;
		}

		if(keyStates[3])
		{
			xxa++;
		}

		jumping = keyStates[4];
	}

	public void setKeyState(int key, boolean state)
	{
		byte index = -1;

		if(key == gameSettings.forwardKey.key)
		{
			index = 0;
		}

		if(key == gameSettings.backKey.key)
		{
			index = 1;
		}

		if(key == gameSettings.leftKey.key)
		{
			index = 2;
		}

		if(key == gameSettings.rightKey.key)
		{
			index = 3;
		}

		if(key == gameSettings.jumpKey.key)
		{
			index = 4;
		}

		if(index >= 0)
		{
			keyStates[index] = state;
		}
	}

	public void resetKeys()
	{
		for(int i = 0; i < keyStates.length; ++i)
		{
			keyStates[i] = false;
		}
	}
}
