package zeldaminiclone;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	public static BufferedImage spritesheet;
	
	
	public static BufferedImage[] player_front;
	public static BufferedImage[] enemy_front;
	public static BufferedImage tileTree;
	
	public  Spritesheet() {
		try {
			spritesheet = ImageIO.read(getClass().getResource("/aula05-spritesheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Walking Down
		player_front = new BufferedImage[2];
		player_front[0] = Spritesheet.getSprite(0, 11, 16, 16);
		player_front[1] = Spritesheet.getSprite(19, 11, 16, 16);
		
		//Enemy path
		enemy_front = new BufferedImage[2];
		enemy_front[0] = Spritesheet.getSprite(139, 209, 16, 16);
		enemy_front[1] = Spritesheet.getSprite(157, 209, 16, 16);
		
		//World wall
		tileTree = Spritesheet.getSprite(279, 231, 32, 32);
	}
	
	public static BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
}
