package Snake;

import java.awt.Color;
import java.awt.Graphics;

public class bodyPart 
{
	public static Color green= new Color(4325120);//specific green instead of default green    4325120
	
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

	private int x,y,width,height;
	
public bodyPart(int x, int y, int tilesize)
{
	//these variables are constantly changing which in effect 'moves' the snake
	this.x=x;
	this.y=y;
	width = tilesize;
	height=tilesize;
}


public void tick()
{
	
}

public void draw(Graphics g)
{
	g.setColor(Color.green);
	g.fillRect(x*width, y*height, width, height); //this colors the snake as green
	
}







}
