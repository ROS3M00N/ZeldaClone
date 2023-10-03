package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class world {
	public static List<blocks> Blocks = new ArrayList<blocks>();
	
	
	public world() {
		for(int xx = 0; xx < 15*2; xx++) {
			Blocks.add(new blocks(xx*32,0));
		}
		for(int xx = 0; xx < 15*2; xx++) {
			Blocks.add(new blocks(xx*32,480-32));
		}
		
		for(int yy = 0; yy < 15*2; yy++) {
			Blocks.add(new blocks(0,yy*32));
		}
		for(int yy = 0; yy < 15*2; yy++) {
			Blocks.add(new blocks(640-32,yy*32));
		}}
		
	//Collisions
	public static boolean isFree(int x, int y) {
			for(int i = 0; i < Blocks.size(); i++) {
				blocks atualBlocks = Blocks.get(i);
				if(atualBlocks.intersects(new Rectangle(x,y,32,32))) {
					return false;
			}
		}
			return true;
	}
				
	
	
	//World generation
	public void render(Graphics g) {
		for(int i = 0; i < Blocks.size(); i++) {
		Blocks.get(i).render(g);
		}
	}
	
}
