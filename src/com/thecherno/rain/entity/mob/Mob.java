package com.thecherno.rain.entity.mob;

import com.thecherno.rain.entity.Entity;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.level.Level;

public abstract class Mob extends Entity
{
	//protected int oldDir = 0;
	protected boolean walking = false;

	protected enum Direction
	{
		UP, DOWN, LEFT, RIGHT
	}

	protected Direction dir;

	public void move(double xa, double ya)
	{
		if(xa != 0 && ya != 0)
		{
			move(xa, 0);
			move(0, ya);
			return;
		}

		if(xa > 0) dir = Direction.RIGHT; // oldDir = 1; 
		if(xa < 0) dir = Direction.LEFT;  // oldDir = 3;
		if(ya > 0) dir = Direction.DOWN;  // oldDir = 2; 
		if(ya < 0) dir = Direction.UP;    // oldDir = 0;

		//		if(!collision(xa, ya))
		//		{
		//			x += xa;
		//			y += ya;
		//		}

		while(xa != 0)
		{
			if(Math.abs(xa) > 1)
			{
				if(!collision(abs(xa), ya))
				{
					this.x += abs(xa);
				}
				xa -= abs(xa);
			}
			else
			{
				if(!collision(abs(xa), ya))
				{
					this.x += xa;
				}
				xa = 0;
			}
		}

		while(ya != 0)
		{
			if(Math.abs(ya) > 1)
			{
				if(!collision(xa, abs(ya)))
				{
					this.y += abs(ya);
				}
				ya -= abs(ya);
			}
			else
			{
				if(!collision(xa, abs(ya)))
				{
					this.y += ya;
				}
				ya = 0;
			}
		}
	}

	public int abs(double value)
	{
		if(value < 0)
		{
			return -1;
		}
		return 1;
	}

	public abstract void update();

	public abstract void render(Screen screen);

	private boolean collision(double xa, double ya)
	{
		boolean solid  = false;
		for(int c = 0; c < 4; c++)
		{
			// Corner Code: 
			// x: c % 2 * 2 - 1
			// y: c / 2 * 2 - 1
			double xt = ((x + xa) - c % 2 * 7 - 1) / 16;
			double yt = ((y + ya) - c / 2 * 12 + 12) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if(c % 2 == 0) ix = (int) Math.floor(xt);
			if(c / 2 == 0) iy = (int) Math.floor(yt);
			if(level.getTile(ix, iy).solid()) solid = true;
		}		
		return solid;
	}

	public void init(Level level)
	{
		this.level = level;
	}
}
