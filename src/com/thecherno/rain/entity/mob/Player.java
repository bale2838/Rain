package com.thecherno.rain.entity.mob;

import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;
import com.thecherno.rain.entity.Entity;
import com.thecherno.rain.graphics.AnimatedSprite;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.graphics.SpriteSheet;
import com.thecherno.rain.input.Keyboard;

public class Player extends Mob
{

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	public boolean walking = false;
	private AnimatedSprite test = new AnimatedSprite(SpriteSheet.player_forward, 32, 32, 3);
	private AnimatedSprite animSprite = test;


	public Player(Keyboard input)
	{
		this.input = input;
		sprite = Sprite.player_forward;
	}

	public Player(int x, int y, Keyboard input)
	{
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward;
	}

	public void update()
	{
		animSprite.update();
		double xa = 0, ya = 0;
		double speed = 2.0;
		if(anim < 7500) anim++;
		else anim = 0;
		if(input.up) ya -= speed;
		if(input.down) ya += speed;
		if(input.left) xa -= speed;
		if(input.right) xa += speed;

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
		/*
		int flip = 0;
		
		if(oldDir == 0) 
		{
			sprite = Sprite.player_forward;
			if(walking)
			{
				if(anim % 20 > 10)
				{
					sprite = Sprite.player_forward1;
				}
				else
				{
					sprite = Sprite.player_forward2;
				}
			}
		}
		if(oldDir == 1) 
		{
			sprite = Sprite.player_side;
			if(walking)
			{
				if(anim % 20 > 10)
				{
					sprite = Sprite.player_side1;
				}
				else
				{
					sprite = Sprite.player_side2;
				}
			}
		}
		if(oldDir == 2) 
		{
			sprite = Sprite.player_back;
			if(walking)
			{
				if(anim % 20 > 10)
				{
					sprite = Sprite.player_back1;
				}
				else
				{
					sprite = Sprite.player_back2;
				}
			}
		}
		if(oldDir == 3) 
		{
			sprite = Sprite.player_side;
			if(walking)
			{
				if(anim % 20 > 10)
				{
					sprite = Sprite.player_side1;
				}
				else
				{
					sprite = Sprite.player_side2;
				}
			}
			flip = 1;
		}
		*/
		sprite = animSprite.getSprite();
		//com.thecherno.rain.util.Debug.drawRect(screen, 50, 50, 16, 16, false);
		screen.renderMob((int)(x - 16), (int)(y - 16), sprite, 0);
	}
}
