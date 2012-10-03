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

/**
 * Custom input stream for loading levels.
 */
public class LevelObjectInputStream extends ObjectInputStream
{
	/**
	 * Construct a new LevelObjectInputStream.
	 *
	 * @param in Level input stream.
	 * @throws IOException
	 */
	public LevelObjectInputStream(InputStream in) throws IOException
	{
		super(in);

		classes.add("Player$1");
	}

	/**
	 * Read the classes instead of super.
	 *
	 * @return Bla.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Override
	protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException
	{
		ObjectStreamClass osc = super.readClassDescriptor();

		return classes.contains(osc.getName()) ? ObjectStreamClass.lookup(Class.forName(osc.getName())) : osc;
	}

	/**
	 * Classes to read.
	 */
	public Set<String> classes = new HashSet<String>();
}
