package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class bullet extends Rectangle{

	public int direction = 1;
	public int spd = 8;
	
	public int frames = 0;
	
	public bullet(int x, int y, int direction) {
		super(x+16,y+16,8,8);
		this.direction = direction;
	}
	
	public void tick() {
		x+=spd*direction;
		frames++;
		if(frames == 60) {
			player.bullets.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, width, height);
	}
	
}
