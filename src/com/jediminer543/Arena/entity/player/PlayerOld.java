package com.jediminer543.Arena.entity.player;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;

import com.jediminer543.Arena.Tickable;
import com.jediminer543.Arena.util.TextHandeler;



public class PlayerOld implements Tickable
{
	
	float pitch, yaw;
	
	int health;
	
	/**
	 * The position of the player
	 */
	public Vector3f position;
	
	/**
	 * The forward velocity of the player
	 */
	public Vector3f velocity;
	
	public float lookVelocity, lookPerpendicularVelocity;
	
	public PlayerOld(Vector3f position)
	{
		this.position = position;
	}
	
	@Override
	public void updateTick()
	{
		velocity = new Vector3f();
		
		lookPerpendicularVelocity = 0;
		lookVelocity = 0;
		
		int DX = Mouse.getDX();
		int DY = Mouse.getDY();
		
		yaw += DX * 0.15;
		pitch += -DY * 0.1;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		lookVelocity += (float) (1 * 0.1);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
		lookVelocity += (float) (-1 * 0.1);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		lookPerpendicularVelocity += (float) (1 * 0.1);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
		lookPerpendicularVelocity += (float) (-1 * 0.1);
		
		float Hypotonuse = lookVelocity; //TODO add delta
		float Ajacent =  Hypotonuse * (float) Math.cos(Math.toRadians(yaw));
		float Oposite = Hypotonuse * (float) Math.sin(Math.toRadians(yaw));
		velocity.z += Ajacent;
		velocity.x -= Oposite;
		
		float HypotonuseP = lookPerpendicularVelocity; //TODO add delta
		float AjacentP =  HypotonuseP * (float) Math.cos(Math.toRadians(yaw-90));
		float OpositeP = HypotonuseP * (float) Math.sin(Math.toRadians(yaw-90));
		velocity.z += AjacentP;
		velocity.x -= OpositeP;
		
		tickCamera();
		tickDebug();
		tickVelocity();
	}

	public void tickValidatePosition()
	{
		if (position.y <= 0)
		{
			health = 0;
		}
	}
	
	@Deprecated
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
		GL11.glRotatef(pitch, 1, 0, 0);
		GL11.glRotatef(yaw, 0, 1, 0);
		GL11.glTranslatef(position.x, position.y, position.z);
	}
	
	public void tickDebug()
	{

		TextHandeler.drawMainStringHUD(10, 12, "Debug Info", Color.black);
		TextHandeler.drawMainStringHUD(10, 24, " Player Location:", Color.black);
		TextHandeler.drawMainStringHUD(10, 36, "  X = " + position.x, Color.black);
		TextHandeler.drawMainStringHUD(10, 48, "  Y = " + position.y, Color.black);
		TextHandeler.drawMainStringHUD(10, 60, "  Z = " + position.z, Color.black);
		TextHandeler.drawMainStringHUD(10, 72, "  Pitch = " + pitch, Color.black);
		TextHandeler.drawMainStringHUD(10, 84, "  Yaw = " + yaw, Color.black);
		TextHandeler.drawMainStringHUD(10, 96, "  DeltaWheel = " + Mouse.getDWheel(), Color.black);
		
	}
}
