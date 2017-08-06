import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class NewAnimation extends Animation
{
	public NewAnimation(Image[] i, int delay, boolean update)
	{
		super(i, delay, update);
	}
	
	public void setRotation(float theta)
	{
		for(int i = 0; i < this.getFrameCount(); i++)
		{
			Image image = this.getImage(i);
			image.setRotation(theta);
		}
	}
	
	public void setCenterRotation(float x, float y)
	{
		for(int i = 0; i < this.getFrameCount(); i++)
		{
			Image image = this.getImage(i);
			image.setCenterOfRotation(x, y);
		}
	}
	
	public float getRotation(Animation a)
	{
		return a.getImage(0).getRotation();
	}
}
