package com.jediminer543.Arena.levels;

import com.jediminer543.Arena.globals.GLOBALS;

public class Level
{
	private static String name;

	public static void load()
	{
		GLOBALS.levelName = getName();
	}
	
	public static String getName() {
		return name;
	}
}
