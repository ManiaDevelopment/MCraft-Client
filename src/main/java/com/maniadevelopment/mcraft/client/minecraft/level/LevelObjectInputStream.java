package com.maniadevelopment.mcraft.client.minecraft.level;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/30/12
 * Time: 9:59 PM
 */
public class LevelObjectInputStream extends ObjectInputStream
{
	public LevelObjectInputStream(InputStream in) throws IOException
	{
		super(in);

		classes.add("Player$1");
	}

	@Override
	protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException
	{
		ObjectStreamClass osc = super.readClassDescriptor();

		return classes.contains(osc.getName()) ? ObjectStreamClass.lookup(Class.forName(osc.getName())) : osc;
	}

	public Set<String> classes = new HashSet<String>();
}
