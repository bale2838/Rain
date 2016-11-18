package com.thecherno.rain.level;

import java.util.ArrayList;
import java.util.List;

import com.thecherno.rain.entity.Entity;
import com.thecherno.rain.entity.mob.Player;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.level.tile.Tile;

public class Level 
{
	public int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	public static Level spawn = new SpawnLevel("/levels/spawn.png");

	private List<Entity> entities = new ArrayList<Entity>();

	private List<Player> players = new ArrayList<Player>();


	public Level(int width, int height)
	{
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	public Level(String path)
	{
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel()
	{

	}

	protected void loadLevel(String path)
	{

	}

	public void update()
	{
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).update();
		}

		for(int i = 0; i < players.size(); i++)
		{
			players.get(i).update();
		}
	}

	private void remove()
	{
		for(int i = 0; i < entities.size(); i++)
		{
			if(entities.get(i).isRemoved()) entities.remove(i);
		}
		for(int i = 0; i < players.size(); i++)
		{
			if(players.get(i).isRemoved()) players.remove(i);
		}

	}

	private void time()
	{

	}

	public void add(Entity e)
	{
		e.init(this);
		if(e instanceof Player)
		{
			players.add((Player)e);
		}
		else
		{
			entities.add(e);
		}
	}

	public void render(int xScroll, int yScroll, Screen screen)
	{
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for(int y = y0; y < y1; y++)
		{
			for(int x = x0; x < x1; x++)
			{
				getTile(x, y).render(x, y, screen);
			}
		}

		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).render(screen);
		}

		for(int i = 0; i < players.size(); i++)
		{
			players.get(i).render(screen);
		}
	}

	public List<Player> getPlayers()
	{
		return players;
		//		for(int i = 0; i < entities.size(); i++)
		//		{
		//			if(entities.get(i) instanceof Player)
		//			{
		//				System.out.println("GOT PLAYER");
		//				return (Player) entities.get(i);
		//			}
		//		}
		//		System.out.println("GOT NULL\n");
		//		return null;
	}

	public Player getPlayer(int index)
	{
		return players.get(index);
	}

	public Player getClientPlayer()
	{
		return players.get(0);
	}

	public List<Entity> getEntities(Entity e, int radius)
	{
		List<Entity> result = new ArrayList<Entity>();
		List<Entity> allEntities= new ArrayList<Entity>();
		addEntities(allEntities);
		int ex = (int)e.getX();
		int ey = (int) e.getY();
		for(int i = 0; i < allEntities.size(); i++)
		{
			Entity entity = allEntities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();

			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx*dx) + (dy*dy));
			if(distance <= radius)
			{
				result.add(entity);
			}
		}

		return result;
	}

	public void addEntities(List<Entity> allEntities)
	{
		for(int i = 0; i < entities.size(); i++)
		{
			Entity entity = entities.get(i);
			allEntities.add(entity);

		}
		for(int i = 0; i < players.size(); i++)
		{
			Player player = players.get(i);
			allEntities.add(player);
		}
	}

	public List<Player> getPlayers(Entity e, int radius)
	{
		List<Entity> entities = getEntities(e, radius);
		List<Player> result = new ArrayList<Player>();
		for(int i = 0; i < entities.size(); i++)
		{
			if(entities.get(i) instanceof Player)
			{
				result.add((Player)entities.get(i));
			}
		}

		return result;
	}

	// Grass  = 0xff00ff00
	// Flower = 0xffffff00
	// Rock   = 0xff7f7f00
	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if(tiles[x + y * width] == Tile.col_spawn_floor) return Tile.spawn_floor;
		if(tiles[x + y * width] == Tile.col_spawn_grass) return Tile.spawn_grass;
		if(tiles[x + y * width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
		if(tiles[x + y * width] == Tile.col_spawn_wall1) return Tile.spawn_wall1;
		if(tiles[x + y * width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
		if(tiles[x + y * width] == Tile.col_spawn_water) return Tile.spawn_water;
		return Tile.voidTile;
	}
}