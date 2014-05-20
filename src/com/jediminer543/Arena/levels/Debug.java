package com.jediminer543.Arena.levels;

import org.lwjgl.util.vector.Vector3f;

import com.jediminer543.Arena.globals.GLOBALS;
import com.jediminer543.Arena.renders.basic.Cube;

public class Debug// extends Level
{
	private static String name = "Debug";
	
	public static void load()
	{
		GLOBALS.levelName = name;
		loadWorldGL();
		loadWorldPhys();
	}
	
	public static void loadWorldGL()
	{
		GLOBALS.RenderPool.add(new Cube(new Vector3f(-50,-5,-50), new Vector3f(100, 1, 100)));
	}
	
	public static void loadWorldPhys()
	{
		
	}
	
	
}
