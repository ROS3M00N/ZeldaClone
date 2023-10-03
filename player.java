package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class player extends Rectangle {
	public int spd = 2;
	public boolean right, left, up, down;
	
	//Animations
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 15;
	
	//Shoot system
	public static List <bullet> bullets = new ArrayList<bullet>();
	public boolean shoot = false;
	public int direction = 1;
	
	public player(int x, int y) {
		super(x ,y, 32, 32);
	}

	//Frame to frame
	public void tick() {
		boolean moved = false;
		if(right && world.isFree(x+spd, y)) {
			x+=spd;
			moved = true;
			direction = 1;
		}
		else if(left && world.isFree(x-spd, y)) {
			x-=spd;
			moved = true;
			direction = -1;
		}
		if(up && world.isFree(x, y-spd)) {
			y-=spd;
			moved = true;
		}
		else if(down && world.isFree(x, y+spd)) {
			y+=spd;
			moved = true;
		}
		
		if(moved) {
		curFrames++;
		if(curFrames == targetFrames) {
			curFrames = 0;
			curAnimation++;
			if(curAnimation == Spritesheet.player_front.length) {
				curAnimation = 0;
				}
			}
		}
		
		//Shoot test
		if (shoot) {
			shoot = false;
			bullets.add(new bullet(x,y,direction));
		}	
		for(int i = 0; i<bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}
	
	//Player renderer
	public void render(Graphics g) {
		g.drawImage(Spritesheet.player_front[curAnimation],x,y,32,32,null);
		
		//Bullet renderer
		for(int i = 0; i<bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
	
}
