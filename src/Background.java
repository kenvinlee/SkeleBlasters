import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/* Class that contains background of the game. Nothing too special...
 * Generally speaking I'd just import an image, but I don't have one at the moment
 * */
public class Background {
	private Rectangle top, bot, left, right;

	public Background() throws SlickException{
		init();
	}
	
	public void init() throws SlickException{
		top = new Rectangle(0, 0, 1224, 150);
		bot = new Rectangle(0, 500, 1224, 150);
		left = new Rectangle(0, 0, 200, 650);
		right = new Rectangle(974, 0, 250, 650);
	}
	
	public void render(Graphics g) throws SlickException{
		g.setColor(new Color(Color.gray));
		g.fill(top);
		g.fill(bot);
		g.fill(left);
		g.fill(right);
	}
}
