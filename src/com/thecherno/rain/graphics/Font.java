package com.thecherno.rain.graphics;

public class Font
{
	
		private static SpriteSheet font = new SpriteSheet("/fonts/arial.png", 16);
		private static Sprite[] characters = Sprite.split(font);
		//private static SpriteSheet font_characters = new SpriteSheet(font, , y, width, height, spriteSize);
		
		private static String charIndex = "ABCDEFGHIJKLM" + //
									      "NOPQRSTUVWXYZ" + //
									      "abcdefghijklm" + //
									      "nopqrstuvwxyz" + //
									      "0123456789.,'" + //
									      "'\"\";:!@$%()-+";
		
		public Font()
		{
			
		}
		
		public void render(Screen screen)
		{
			screen.renderSprite(50, 50, characters[4 * 13], true);
		}

}
