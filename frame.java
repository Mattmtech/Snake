package Snake;




import java.awt.GridLayout;

import javax.swing.JFrame;

import Snake.Screen;

public class frame extends JFrame
{
	
public frame()
{
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("Snake");
	setResizable(false);
	inside();
}

public void inside()
{
	setLayout(new GridLayout(1,1,0,0));
	Screen s = new Screen();
	add(s);
	
	pack();//this sizes the frame ie demensions of the frame 
	setLocationRelativeTo(null);//centers the jframe
	setVisible(true);
}

public static void main(String[] agrs)
{
	new frame();
}







}
