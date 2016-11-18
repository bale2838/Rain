package com.thecherno.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.thecherno.rain.entity.mob.Chaser;
import com.thecherno.rain.entity.mob.Dummy;
import com.thecherno.rain.level.tile.Tile;

public class SpawnLevel extends Level
{
	public SpawnLevel(String path)
	{
		super(path);
	}

	protected void loadLevel(String path)
	{
		try
		{
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		}
		catch(IOException e)
		{
			System.out.println("Exception! Could not load level file!");
		}
		//add(new Chaser(20,55));
		for(int i = 0; i < 5; i++)
		{
			//add(new Dummy(20, 55));
		}
		
	}

	// Grass  = 0xff00ff00
	// Flower = 0xffffff00
	// Rock   = 0xff7f7f00
	protected void generateLevel()
	{
	}
}