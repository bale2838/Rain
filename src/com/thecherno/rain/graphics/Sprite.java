package com.thecherno.rain.graphics;

import com.thecherno.rain.level.tile.spawn_level.SpawnFloorTile;
import com.thecherno.rain.level.tile.spawn_level.SpawnGrassTile;
import com.thecherno.rain.level.tile.spawn_level.SpawnHedgeTile;
import com.thecherno.rain.level.tile.spawn_level.SpawnWallTile;
import com.thecherno.rain.level.tile.spawn_level.SpawnWaterTile;

public class Sprite 
{
	public final int SIZE;
	private int x;
	private int y;
	private int width;
	private int height;
	public int[] pixels;
	protected SpriteSheet sheet;
	public final int TRANSPARENT_COL; //TODO might not need this

	public static Sprite grass = new Sprite(16, 0, 5, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);

	//Spawn Level Sprites here:
	public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_hedge = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_water = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_wall1 = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_wall2 = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_floor = new Sprite(16, 1, 1, SpriteSheet.spawn_level);

	//Player Sprites here:
	public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.tiles);
	//public static Sprite player_left = new Sprite(32, 3, 5, SpriteSheet.tiles);
	public static Sprite player_side = new Sprite(32, 1, 5, SpriteSheet.tiles);

	public static Sprite player_forward1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_forward2 = new Sprite(32, 0, 7, SpriteSheet.tiles);

	public static Sprite player_side1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_side2= new Sprite(32, 1, 7, SpriteSheet.tiles);

	public static Sprite player_back1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_back2= new Sprite(32, 2, 7, SpriteSheet.tiles);

	public static Sprite dummy = new Sprite(32, 0, 0, SpriteSheet.dummy_down);

	public Sprite(int width, int height, int[] pixels, int transparentColor)
	{
		if(width == height) SIZE = width;
		else SIZE = -1;
		this.TRANSPARENT_COL = transparentColor;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	protected Sprite(SpriteSheet sheet, int width, int height)
	{
		this(width, height, sheet.pixels, -1);
		this.sheet = sheet;
	}

	public static Sprite[] split(SpriteSheet sheet)
	{
		int amount = (sheet.getWidth() * sheet.getHeight()) / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT);
		Sprite[] sprites = new Sprite[amount];
		int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];
		int current = 0;

		for(int yp = 0; yp < sheet.getHeight() / sheet.SPRITE_HEIGHT; yp++)
		{
			for(int xp = 0; xp < sheet.getWidth() / sheet.SPRITE_WIDTH; xp++)
			{
				for(int y = 0; y < sheet.SPRITE_HEIGHT; y++)
				{
					for(int x = 0; x < sheet.SPRITE_WIDTH; x++)
					{
						int xOffset = x + xp * sheet.SPRITE_WIDTH;
						int yOffset = y + yp * sheet.SPRITE_HEIGHT;
						pixels[x + y * sheet.SPRITE_WIDTH] = sheet.getPixels()[xOffset + yOffset * sheet.getWidth()];
					}
				}
				sprites[current++] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
			}
		}
		return sprites;
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet)
	{
		this(size, size, null, -1);
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color)
	{
		this(size, size, null, -1);
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}

	public Sprite(int[] pixels, int width, int height)
	{
		this(width, height, pixels, -1);
		//SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		for(int i = 0; i < pixels.length; i++)
		{
			this.pixels[i] = pixels[i];
		}
	}

	//		public Sprite(int[] spritePixels, int width, int height)
	//		{
	//			this(width, height, spritePixels, -1);
	//		}

	private void setColor(int color)
	{
		for(int i = 0 ; i < SIZE*SIZE; i++)
		{
			pixels[i] = color;
		}
	}

	private void load()
	{
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SPRITE_WIDTH];
			}
		}
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}


}
