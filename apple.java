package Snake;

import java.awt.Color;
import java.awt.Graphics;

public class apple 
{
	
	public static Color red= new Color(16711680);
	private int x, y, width,height;
	public  apple(int x, int y, int tilesize)//changes the apple's x,y, and tile size to the inputed x,y,tilesize.
	{
		this.x = x;
		this.y = y;
		width = tilesize;
		height = tilesize;
	}
	public void tick()
	{
		
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect(x * width, y * height, width, height);
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
