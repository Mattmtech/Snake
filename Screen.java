package Snake;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;


public class Screen extends JPanel implements Runnable
{
	
	public static final int WIDTH = 700, HEIGHT = 700;
	 private Thread thread;
	 private boolean running = false;//on&off  
	 
	 private Random rand;
	 private bodyPart b;
	 private ArrayList<bodyPart> snake; 
	 
	 private apple apple;
	 private ArrayList<apple> apples;
	 
	 private int x = 10,y = 10;//initial location of the head of snake
	 private int size = 3;
	 private int score;
	 
	 private boolean right = true, left = false, up = false, down = false;
	 private int ticks = 0;
	 private Key key;
	 
	public Screen()
	{
		key = new Key();
		setFocusable(true);//allows for the keys to be 'focused on' ie listened to
		addKeyListener(key);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		snake = new ArrayList<bodyPart>();
		apples = new ArrayList<apple>();
		rand = new Random();
		start();
		
	}
	
	public void tick()
	{
		if(snake.size()==0)//this creates the "head" of the snake
		{
			b=new bodyPart(x,y,10);
			snake.add(b);
		}
		
		if (apples.size()==0)//if theres no apple then spawn one
		{
			int x = rand.nextInt(69);
			int y = rand.nextInt(69);
			apple = new apple(x,y,10);
			apples.add(apple);
		}
		for (int i = 0; i < apples.size(); i++)
		{
			//if the snake 'head' lands the X and y value of the apple increase snakes size and remove apple
			if(x == apples.get(i).getX()&& y == apples.get(i).getY())
			{
				size++;
				apples.remove(i);
				//i--;
			}
		}
		
		for (int i =0; i < snake.size(); i++)
		{
			if(x == snake.get(i).getX() && y == snake.get(i).getY())//if the snake lands on itself
			{
				if ( i != snake.size()-1)//this excludes the head because it will always count the head of the snake
				{
					stop();
				}
			}
		}
		//if you go out of bounds on the x-axis
		if (x < 0 || x > 69)
		{
			stop();
		}
		//if you go out of bounds on the y-axis
		if(y <0 || y > 69)
		{
			stop();
		}
		
		ticks++;
		
		if (ticks > 250000)
		{
			if(right) x++;//this increments the x's in the array list shifting it right
			if(left) x--; //decrements the x's in the arraylist shifting the snake left
			if(up) y--;//decements the y's shifting the snake up
			if(down) y++; //increments the y's shifting the snake down
			
			ticks = 0;
			b = new bodyPart(x,y,10);//creates the new body part either +/- of x/y
			snake.add(b);//adds the new part to the snake
			
			
			if(snake.size()>size)
			{
				//since we just added to the snake we need to delete the last element of the array list (snake) to keep its proper size
				snake.remove(0);
				
			}
		}
	}
	
	public void paint(Graphics g)
	{
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for (int i =0; i < snake.size(); i++)//this draws the "bodyparts" of the snake
		{
			snake.get(i).draw(g);
		}
		for (int i = 0; i <apples.size(); i++)
		{
			apples.get(i).draw(g);
		}
	
	}
	
	public void start()
	{
		running = true;//
		thread = new Thread(this, "loop");
		thread.start();
	}
	
	public void stop()
	{
		score = size-3;
		running = false;
		System.out.println("GAME OVER" +"\n"+ "YOUR SCORE IS:" + score);
		try 
		{
			thread.join(); //this pauses the thread
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void run()
	{
		while(running)
		{
			tick();
			repaint();//refreshes the Jpanel
		}
	}

	
	private class Key implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) 
		{
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_RIGHT && !left)
			{
				down = false;
				up = false;
				right = true;
			}
			if (key == KeyEvent.VK_LEFT && !right)
			{
				down = false;
				up = false;
				left = true;
			}
			if (key == KeyEvent.VK_UP && !down)//not down prevents you from going back into yourself
			{
				right = false;
				up = true;
				left = false;
			}
			if (key == KeyEvent.VK_DOWN && !up)//not up prevents you from moving up into yourself when you're moving downwards
			{
				right = false;
				down = true;
				left = false;
			}
		}
		//inherited them from KeyListener
		@Override
		public void keyReleased(KeyEvent e) {			
		}
		@Override
		public void keyTyped(KeyEvent e) {			
		}
		
	}
}




