package com.jediminer543.Arena.globals;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.TrueTypeFont;

import com.jediminer543.Arena.Tickable;
import com.jediminer543.Arena.phys.AABB;
import com.jediminer543.Arena.renders.IRenderable;

public class GLOBALS
{
	public static String PLAYERNAME;
	
	public static int SCREEN_X, SCREEN_Y;
	
	public static float FOV;
	
	public static List<Tickable> TickPool = new ArrayList<Tickable>();
	public static List<IRenderable> RenderPool = new ArrayList<IRenderable>();
	
	public static List<AABB> AABBPool = new ArrayList<AABB>();
	
	public static TrueTypeFont TitleText;
	
	public static TrueTypeFont DebugText;
	
	public static FloatBuffer GLUMatrix = BufferUtils.createFloatBuffer(16);
	public static FloatBuffer HUDMatrix = BufferUtils.createFloatBuffer(16);
	
	
}
