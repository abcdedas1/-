package 弹球游戏;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JFrame;
public class step1 {
	private JFrame frame = new JFrame("弹球游戏");
	//桌面的宽度(长)
	private final int TABLE_WIDTH=300;
	//桌面的高度(宽)
	private final int TABLE_HEIGHT=400;
	//球拍的宽和长
	private final int RACKET_WIDTH=60;
	private final int RACKET_HEIGHT=20;
	//球的大小
	private final int BALL_SIZE=16;
	//球的x-y坐标
	private int ballx1 = 120;
	private int bally1=20;
	//第二个球的x-y坐标
	private int ballx2 = 130;
	private int bally2=40;
	private int speedY=10;
	private int speedX=5;
	//球拍x-y坐标
	private int racketx = 120;
	private final int rackety = 340;
	//判断当前游戏是否结束
	private boolean isOver = false;
	//声明一个定时器
	private Timer timer;
	//自定义一个类充当画布
	private class MyCanvas extends Canvas{
		public void paint(Graphics g)//设计小球
		{
			if(isOver)
			{//游戏结束
				g.setColor(Color.BLUE);
				g.setFont(new Font("Times",Font.BOLD,30));
				g.drawString("游戏结束",50,200);
			}
			else
			{//游戏中
				g.setColor(Color.RED);
				g.fillOval(ballx1, bally1, BALL_SIZE, BALL_SIZE);
			//	g.setColor(Color.BLUE);
			//	g.fillOval(ballx2, bally2, BALL_SIZE, BALL_SIZE);
				
				g.setColor(Color.PINK);
				g.fillRect(racketx,rackety,RACKET_WIDTH,RACKET_HEIGHT);
				
			}
		}
		
		
	}
	MyCanvas drawArea = new MyCanvas();
	public void init()
	{
		KeyListener listener = new KeyAdapter() 
		{
			public void keyPressed(KeyEvent e)
			{
				int keyCode = e.getKeyCode();
				int i=0;
				if(keyCode == KeyEvent.VK_LEFT);
				{
					i=1;
				}
				if(keyCode == KeyEvent.VK_RIGHT)
				{
					i=2;
				}
				if(i==1&&racketx>0)
				{
					racketx-=10;
				}
				else if(i==2&&racketx<TABLE_WIDTH - RACKET_WIDTH)
				{
					racketx+=10;
				}
			}
		};
		frame.addKeyListener(listener);
		drawArea.addKeyListener(listener);
		ActionListener task = new ActionListener(){
		
			public void actionPerformed(ActionEvent e)
			{
				if(ballx1<=0||ballx1>=(TABLE_WIDTH-BALL_SIZE)  
				 ||ballx2<=0||ballx2>=(TABLE_WIDTH-BALL_SIZE))
				{
					speedX=-speedX;
				}
				if(bally1<=0||(bally1>rackety-BALL_SIZE&&ballx1>racketx&&ballx1<racketx+RACKET_WIDTH)  
				|| bally2<=0||(bally2>rackety-BALL_SIZE&&ballx2>racketx&&ballx2<racketx+RACKET_WIDTH))
				{
					speedY=-speedY;
				}
				if(bally1>rackety-BALL_SIZE&&(ballx1<racketx||ballx1>racketx+RACKET_WIDTH) ||
				   bally2>rackety-BALL_SIZE&&(ballx2<racketx||ballx2>racketx+RACKET_WIDTH))
				{
					timer.stop();
					isOver=true;
					drawArea.repaint();
				}
				ballx1+=speedX;
				bally1+=speedY;
				
				
				ballx2+=speedX;
				bally2+=speedY;
				drawArea.repaint();
		}
		};
		timer = new Timer(100,task);
		timer.start();
		drawArea.setPreferredSize(new Dimension(TABLE_WIDTH,TABLE_HEIGHT));
		frame.add(drawArea);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new step1().init();
	}
}
