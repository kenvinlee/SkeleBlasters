import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;

public class Collision 
{
	private Rectangle rect;
	private Circle circ;
	private RoundedRectangle rRect;
	
	public Collision()
	{
		
	}
	
	public void setRect(float x, float y, float width, float height)
	{
		rect = new Rectangle(x, y, width, height);
	}
	
	public void setRoundRect(float x, float y, float width, float height, float radius)
	{
		rRect = new RoundedRectangle(x, y, width, height, radius);
	}
	
	public void setCirc(float x, float y, float radius)
	{
		circ = new Circle(x, y, radius);
	}
	
	public boolean rIntersect(Shape s)
	{
		return rect.intersects(s);
	}
	
	public boolean rRectIntersect(Shape s)
	{
		return rRect.intersects(s);
	}
	
	public boolean cIntersect(Shape s)
	{
		return circ.intersects(s);
	}
	
	public void fillCirc(Graphics g)
	{
		g.setColor(new Color(Color.white));
		g.fill(circ);
	}
	
	public void fillrRect(Graphics g)
	{
		g.setColor(new Color(Color.white));
		g.fill(rRect);
	}
	
	public Circle getCirc()
	{
		return circ;
	}
	
	public RoundedRectangle getrRect()
	{
		return rRect;
	}
	
}
