import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ball {

	private int diameter;
	private double x, y, previousX, previousY;

	public Ball(int x, int y, int diameter)
	{
		this.x = previousX = x;
		this.y = previousY = y;
		this.diameter = diameter;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getPreviousX()
	{
		return previousX;
	}
	
	public double getPreviousY()
	{
		return previousY;
	}
	public int getDiameter()
	{
		return diameter;
	}
	
	public void setX(double x)
	{
		setPreviousX(x);
		this.x = x;
	}
	
	public void setY(double y)
	{
		setPreviousY(y);
		this.y = y;
	}

	private void setPreviousX(double x)
	{
		previousX = x;
	}
	
	private void setPreviousY(double y)
	{
		previousY = y;
	}
	public void setDiameter(int diameter)
	{
		this.diameter = diameter;
	}
	
}
