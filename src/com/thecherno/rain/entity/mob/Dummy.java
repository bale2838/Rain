package com.thecherno.rain.entity.mob;

import com.thecherno.rain.graphics.AnimatedSprite;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.graphics.SpriteSheet;

public class Dummy extends Mob
{
	public boolean walking = false;
	private AnimatedSprite forward = new AnimatedSprite(SpriteSheet.dummy_forward, 32, 32, 3);
	private AnimatedSprite animSprite = forward;

	private int time  = 0;
	private int xa = 0;
	private int ya = 0;
	private int xCount = 0;
	private int yCount = 0;

	public Dummy(int x, int y)
	{
		this.x = x << 4;
		this.y = y << 4;
		sprite  = Sprite.dummy;
	}

	public void update()
	{
		time++;
		if(time % (random.nextInt(80) + 30) == 0)
		{
			xa = random.nextInt(3) - 1; // has vals -1, 0 ,1
			if(xa == 0)
			{
				ya = random.nextInt(3) - 1; // has vals -1, 0 ,1
			}
			else
			{
				ya = 0;
			}
			if(random.nextInt(4) == 0)
			{
				xa = 0;
				ya = 0;
			}
		}

		animSprite.update();
		if(ya < 0)
		{ 
			//ya--;
			dir = Direction.UP;
		}
		else if(ya > 0)
		{ 
			//ya++;
			animSprite = forward;
			dir = Direction.DOWN;
		}
		if(xa < 0)
		{ 
			//xa--;
			dir = Direction.LEFT;
		}
		else if(xa > 0)
		{ 
			//xa++;
			dir = Direction.RIGHT;
		};

		if(xa != 0 || ya != 0) 
		{
			move(xa, ya);
			walking = true;
		}
		else
		{
			walking = false;
		}

	}

	public void render(Screen screen)
	{
		sprite = animSprite.getSprite();
		screen.renderMob((int)x, (int)y, sprite, 0);
	}

}
