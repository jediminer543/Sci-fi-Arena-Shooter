package com.jediminer543.Arena.entity.player;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;

import com.jediminer543.Arena.Tickable;
import com.jediminer543.Arena.phys.RigidBody;
import com.jediminer543.Arena.util.TextHandeler;



public class Player extends RigidBody implements Tickable
{
	
	float pitch, yaw;
	
	int health;
	
	/**
	 * The position of the player
	 */
	//public Vector3f position;
	
	/**
	 * The forward velocity of the player
	 */
	//public Vector3f velocity;
	
	//public float lookVelocity, lookPerpendicularVelocity;
	
	public Player(Vector3f position)
	{
		this.position = position;
	}
	
	@Override
	public void updateTick()
	{
		velocity = new Vector3f();
		
		int DX = Mouse.getDX();
		int DY = Mouse.getDY();
		
		look.x += DX * 0.15;
		look.y += -DY * 0.1;
		
		if (look.y > 90)
			look.y = 90;
		if (look.y < -90)
			look.y = -90;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		lookVelocity.x += (float) (10 * 0.1);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		lookVelocity.x += (float) (-10 * 0.1);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		lookVelocity.z += (float) (10 * 0.1);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		lookVelocity.z += (float) (-10 * 0.1);
		
		tickCamera();
		tickDebug();
		//tickVelocity();
	}

	public void tickValidatePosition()
	{
		if (position.y <= 0)
		{
			health = 0;
		}
	}
	
	public void tickVelocity()
	{
		position.x += velocity.getX();
		position.y += velocity.getY();
		position.z += velocity.getZ();
	}
	
	public void tickCamera()
	{
		GL11.glLoadIdentity();
		//GL11.glRotatef(180, 0, 0, 1);
		GL11.glRotatef(look.y, 1, 0, 0);
		GL11.glRotatef(look.x, 0, 1, 0);
		GL11.glTranslatef(position.x, position.y, position.z);
	}
	
	public void tickDebug()
	{

		TextHandeler.drawMainStringHUD(10, 12, "Debug Info", Color.black);
		TextHandeler.drawMainStringHUD(10, 24, " Player Location:", Color.black);
		TextHandeler.drawMainStringHUD(10, 36, "  X = " + position.x, Color.black);
		TextHandeler.drawMainStringHUD(10, 48, "  Y = " + position.y, Color.black);
		TextHandeler.drawMainStringHUD(10, 60, "  Z = " + position.z, Color.black);
		TextHandeler.drawMainStringHUD(10, 72, "  Pitch = " + look.x, Color.black);
		TextHandeler.drawMainStringHUD(10, 84, "  Yaw = " + look.y, Color.black);
		TextHandeler.drawMainStringHUD(10, 96, "  DeltaWheel = " + Mouse.getDWheel(), Color.black);
		
	}
}
