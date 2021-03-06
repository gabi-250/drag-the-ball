import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class BallPanel extends JPanel {

	private Ball ball;
	private JFrame frame;
	private int frameWidth, frameHeight;
	private int previousX, previousY, x, y;
	private int xIncrement, yIncrement;
	private int mouseX, mouseY, draggedMouseX, draggedMouseY;
	private boolean moveDown, moveRight, begin, grabBall;

	public BallPanel()
	{
		super();
		ball = new Ball(20, 30, 50);
		xIncrement = yIncrement = 1;
		addMouseListener(new SpecialMouseListener());
		
		addMouseMotionListener(new MouseAdapter() {
			
			public void mouseDragged(MouseEvent event)
			{
				x = event.getX();
				y = event.getY();
				
				if( x > previousX )
				{
					moveRight = true;
				}
				else
				{
					moveRight = false;
				}
				
				if( y > previousY )
				{
					moveDown = true;
				}
				else
				{
					moveDown = false;
				}
				
				xIncrement = Math.abs( previousX - x);
				yIncrement = Math.abs( previousY - y);
				
				begin = true;

				draggedMouseX = event.getX();
				draggedMouseY = event.getY();
				
				if( grabBall == false)
				{
					moveBall();			
				}
				else
				{
					followMouse();
				}
			}		
		});
		
	}

	public void setFrame(JFrame frame)
	{
		this.frame = frame;

		updateSizes(frame.getWidth(), frame.getHeight());
	}
	
	public void updateSizes(int width, int height)
	{
		frameWidth = width;
		frameHeight = height;
		ball.setX( (frameWidth - ball.getDiameter() ) / 2);
		ball.setY( (frameHeight - ball.getDiameter() )/ 2);
//		System.out.println(this.getWidth() +" " + this.getHeight());
		this.setSize(frameWidth, frameHeight);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		
		graphics.setPaint(Color.GREEN);

		Ellipse2D.Double circle = new Ellipse2D.Double(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
	
		//graphics.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
		graphics.fill(circle);
		if( frame.getWidth() != frameWidth || frame.getHeight() != frameHeight )
		{
			repaint();
			updateSizes(frame.getWidth(), frame.getHeight());
		}	
		
		try
		{
			Thread.sleep(7);
		}
		catch(InterruptedException e)
		{}
		if( begin && !grabBall)
		{
			moveBall();
		}
		else if( grabBall )
		{
			followMouse();
		}
		
	}
	
	public void moveBall()
	{
		if(moveRight == true)
		{
			if(ball.getX() < frameWidth - ball.getDiameter())
			{
				ball.setX(ball.getX() + 1);
				
				if( moveDown == true)
				{
					if( ball.getY() < this.getWidth() - ball.getDiameter())
					{
						ball.setY(ball.getY() + 1);
					}
					else
					{
						moveDown = false;
					}
				}
				else
				{
					if( ball.getY() >= 0)
					{
						ball.setY(ball.getY() - 1);
					}
					else
					{
						moveDown = true;
					}
				}
			}
			else
			{
				moveRight = false;
			}
		}
		else
		{
			if(ball.getX() >= 0)
			{
				ball.setX(ball.getX() - 1);
				
				if( moveDown == true)
				{
					if( ball.getY() < this.getWidth() - ball.getDiameter())
					{
						ball.setY(ball.getY() + 1);
					}
					else
					{
						moveDown = false;
					}
				}
				else
				{
					if( ball.getY() >= 0)
					{
						ball.setY(ball.getY() - 1);
					}
					else
					{
						moveDown = true;
					}

				}
			}
			else
			{
				moveRight = true;
			}

		}
		
		repaint();
	}
	
	public void followMouse()
	{
		if( draggedMouseX != -1 && draggedMouseY != -1)
		{
			ball.setX(draggedMouseX - ball.getDiameter() / 2);
			ball.setY(draggedMouseY - ball.getDiameter() / 2);
		//System.out.println(draggedMouseX + " "+ draggedMouseY);
			repaint();
		}
	}
	
	public class SpecialMouseListener extends MouseAdapter {

		public void mousePressed(MouseEvent event)
		{
			previousX = event.getX();
			previousY = event.getY();
			mouseX = event.getX();
			mouseY = event.getY();	
			double radius = (double) ball.getDiameter() / 2;
			
			double ballCenterX = ball.getX() + radius;
			double ballCenterY = ball.getY() + radius;
			
			draggedMouseX = draggedMouseY = -1;
			
			if( Math.abs(mouseX - ballCenterX) < radius &&  Math.abs(mouseY - ballCenterY) < radius )
			{
				grabBall = true;
			
				followMouse();
			}
			
			//System.out.println(mouseX +" " + mouseY+ " "+ ball.getX() +" "+ball.getY());
		}
		
		public void mouseReleased(MouseEvent event)
		{
			grabBall = false;
			moveBall();
		}		
	}
	
}
