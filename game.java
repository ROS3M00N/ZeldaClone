package zeldaminiclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class game extends Canvas implements Runnable, KeyListener{
	
	public static int WIDTH = 640, HEIGHT = 480;
	public static int SCALE = 3;
	public player Player;
	public world World;
	public List <enemy> enemies = new ArrayList<enemy>();
	
	
	public game() {
		this.addKeyListener(this);//Adiciona Eventos de Teclados
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		new Spritesheet();
		Player = new player(32,32);
		World = new world();
		enemies.add(new enemy(32,32));
		
	}
	
	//Game logic
	public void tick() {
		Player.tick();
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
		}
	}
	
	//Graphic renderer
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(0,135,15));
		g.fillRect(0,0,WIDTH*SCALE,HEIGHT*SCALE);
		World.render(g);
		Player.render(g);
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).render(g);
		}
		bs.show();
	}
	
	public static void main(String[] args) {
		game Game = new game();
		JFrame frame = new JFrame();
		
		//Game window creation
		frame.add(Game);
		frame.setTitle("Mini Zelda");
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		new Thread(Game).start();
	}
	
	//Frame Rate Definition
	@Override
	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	//Basic Movement (Moving)
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			Player.right = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			Player.left = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_Z) {
			Player.shoot = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			Player.up = true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			Player.down = true;
		}
		
	}
	
	//Basic Movement (stop)
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			Player.right = false;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			Player.left = false;
		}if(e.getKeyCode()==KeyEvent.VK_UP) {
			Player.up = false;
		}if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			Player.down = false;
		}
		
	}
	
	
	
}
