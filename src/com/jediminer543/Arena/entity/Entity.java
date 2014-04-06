package com.jediminer543.Arena.entity;

import org.lwjgl.util.vector.Vector3f;

public class Entity
{
	/**
	 * The position of the entity
	 */
	public Vector3f position;
	
	/**
	 * The velocity of the entity
	 */
	public Vector3f velocity;
	
	public boolean alive;
	
	public boolean needsTicking;
	
	
	public void updateTick()
	{
		tickVelocity();
		tickValidatePosition();
	}
	
	public void tickVelocity()
	{
		position.x += velocity.getX();
		position.y += velocity.getY();
		position.z += velocity.getZ();
	}
	
	public void tickValidatePosition()
	{
		if (position.y <= 0)
		{
			alive = false;
		}
	}
	
	
}
