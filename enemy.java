package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
	
	//Enemy AI
	public void hunt() {
		player p = game.Player;
		if(x < p.x && world.isFree(x+spd, y)) {
			if(new Random().nextInt(100)<50)
			x+=spd;
		}
		else if(x > p.x && world.isFree(x-spd, y)) {
			if(new Random().nextInt(100)<50)
			x-=spd;
		}
		if(y < p.y && world.isFree(x, y+spd)) {
			if(new Random().nextInt(100)<50)
			y+=spd;
		}
		else if(y > p.y && world.isFree(x, y-spd)) {
			if(new Random().nextInt(100)<50)
			y-=spd;
		}
		if(x > p.x) {
			if(new Random().nextInt(100)<25) {
					shoot = true;
		}
		else if(y > p.y) {
			if(new Random().nextInt(100)<25) {
					shoot = true;
			}
		}
	}
}

	//Frame to frame
	public void tick() {
		boolean moved = true;
		hunt();
		
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
	
	//Enemy renderer
	public void render(Graphics g) {
		g.drawImage(Spritesheet.enemy_front[curAnimation],x,y,32,32,null);
		
		//Bullet renderer
		for(int i = 0; i<bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
	
}
