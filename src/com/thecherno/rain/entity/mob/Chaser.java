package com.thecherno.rain.entity.mob;

import java.util.List;

import com.thecherno.rain.graphics.AnimatedSprite;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.graphics.SpriteSheet;

public class Chaser extends Mob
{

	private AnimatedSprite forward = new AnimatedSprite(SpriteSheet.dummy_forward, 32, 32, 3);
	private AnimatedSprite animSprite = forward;

	private int time = 0;
	private double xa = 0;
	private double ya = 0;
	private double speed = 0.8;

	public Chaser(int x, int y)
	{
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}

	private void move()
	{
		xa = 0;
		ya = 0;

		List<Player> players = level.getPlayers(this, 80);
		if(players.size() > 0)
		{
			Player player = players.get(0);
			if((int)x < (int)player.getX()) xa += speed;
			if((int)x > (int)player.getX()) xa -= speed;
			if((int)y < (int)player.getY()) ya += speed;
			if((int)y > (int)player.getY()) ya -= speed;
		}

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

	public void update()
	{
		move();
		time++;
		if(time % (random.nextInt(80) + 30) == 0)
		{
			xa = random.nextInt(3) - 1;//vals -1,0,1
			if(xa == 0)
			{
				ya = random.nextInt(3) - 1;
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
	}


	public void render(Screen screen)
	{
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), this);

	}

}
