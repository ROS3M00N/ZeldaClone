package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;

public class blocks extends Rectangle{

	public blocks(int x, int y) {
		super(x,y,32,32);
	}
	
	public void render(Graphics g) {
		g.drawImage(Spritesheet.tileTree, x, y, 32, 32, null);
	}
	
}
