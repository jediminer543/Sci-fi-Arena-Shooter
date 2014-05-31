package com.jediminer543.Arena.phys;

import org.lwjgl.util.vector.Vector3f;

import com.jediminer543.Arena.globals.GLOBALPHYS;

public class RigidBody implements IPhysTickable
{
	protected AABB aabb;
	
	protected Vector3f look = new Vector3f();
	
	/**
	 * Temporary buffer for look related movement
	 * Converted once every physics tick
	 */
	protected Vector3f lookVelocity = new Vector3f();
	
	/**
	 * Central velocity pool
	 */
	protected Vector3f velocity = new Vector3f();
	
	protected Vector3f position = new Vector3f();
	
	@Override
	public void physTick()
	{
		physConvertLook();
		physCalcVelocity();
	}
	
	void physCalcVelocity()
	{
		//determines if the body can move
		
		position.x += velocity.getX();
		position.y += velocity.getY();
		position.z += velocity.getZ();
		
		//decelerates by golbal deceleration constant
		this.physCalcVelocityDecelerate();
		
		
	}
	
	/**
	 * Calculates if the rigid body can move
	 */
	void physCalcVelocityMove()
	{
		AABB XpredictAABB = new AABB();
		XpredictAABB.size = new Vector3f(aabb.size.x, aabb.size.y, aabb.size.z);
	}
	
	void physCalcVelocityDecelerate()
	{
		if (velocity.x >= GLOBALPHYS.deceleration.x )
			velocity.x -= GLOBALPHYS.deceleration.x;
			else if (velocity.x <= -GLOBALPHYS.deceleration.x & velocity.x < 0)
			velocity.x += GLOBALPHYS.deceleration.x;
			else if ((velocity.x <= GLOBALPHYS.deceleration.x & velocity.x >= 0) | (velocity.x >= -GLOBALPHYS.deceleration.x & velocity.x <= 0) )
			velocity.x = 0;
		
		if (velocity.y >= GLOBALPHYS.deceleration.y )
			velocity.y -= GLOBALPHYS.deceleration.y;
			else if (velocity.y <= -GLOBALPHYS.deceleration.y & velocity.y < 0)
			velocity.y += GLOBALPHYS.deceleration.y;
			else if ((velocity.y <= GLOBALPHYS.deceleration.y & velocity.y >= 0) | (velocity.y >= -GLOBALPHYS.deceleration.y & velocity.y <= 0) )
			velocity.y = 0;
		
		if (velocity.z >= GLOBALPHYS.deceleration.z )
			velocity.z -= GLOBALPHYS.deceleration.z;
			else if (velocity.z <= -GLOBALPHYS.deceleration.z & velocity.z < 0)
			velocity.z += GLOBALPHYS.deceleration.z;
			else if ((velocity.z <= GLOBALPHYS.deceleration.z & velocity.z >= 0) | (velocity.z >= -GLOBALPHYS.deceleration.z & velocity.z <= 0) )
			velocity.z = 0;
	}
	
	
	void physConvertLook()
	{
		float Hypotonuse = lookVelocity.x; //TODO add delta
		float Ajacent =  Hypotonuse * (float) Math.cos(Math.toRadians(look.x));
		float Oposite = Hypotonuse * (float) Math.sin(Math.toRadians(look.x));
		velocity.z += Ajacent;
		velocity.x -= Oposite;
		
		float HypotonuseP = lookVelocity.z; //TODO add delta
		float AjacentP =  HypotonuseP * (float) Math.cos(Math.toRadians(look.x-90));
		float OpositeP = HypotonuseP * (float) Math.sin(Math.toRadians(look.x-90));
		velocity.z += AjacentP;
		velocity.x -= OpositeP;
		
		lookVelocity = new Vector3f();
	}
	
}
