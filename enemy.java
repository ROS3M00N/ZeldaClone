package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;


//pos 1: x139 y209
//pos 2: x157 y209

public class enemy extends Rectangle {
	public int spd = 1;
	public int right = 1, left = 0, up = 0, down = 0;
	
	//Animations
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 15;
	
	//Shoot system
	public static List <bullet> bullets = new ArrayList<bullet>();
	public boolean shoot = false;
	public int direction = 1;
	
	public enemy(int x, int y) {
		super(x ,y, 32, 32);
	}

	//Frame to frame
	public void tick() {
		boolean moved = true;
		if(right == 1 && world.isFree(x+1, y)) {
			x++;
		}
		
		if(moved) {
		curFrames++;
		if(curFrames == targetFrames) {
			curFrames = 0;
			curAnimation++;
			if(curAnimation == Spritesheet.enemy_front.length) {
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
		g.drawImage(Spritesheet.enemy_front[curAnimation],x,y,32,32,null);
		
		//Bullet renderer
		for(int i = 0; i<bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
	
}
