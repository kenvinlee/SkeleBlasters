import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

//heeeeeeeeeeeeeeeeeeeeeere comes the spagheetttiiii
public class SkeleSword extends Collision
{
	private final float VEL = 0.1f;
	
	private float x, y, dir, width, height;
	private double p1d, p2d;
	private int health;
	private int damage;
	private int target;
	
	private Image[] walk;
	private Image[] attack;
	
	private NewAnimation walking;
	private NewAnimation attacking;
	
	public SkeleSword(float x, float y, float p1x, float p1y, float p2x, float p2y, float width, float height) throws SlickException
	{
		init(x, y, p1x, p1y, p2x, p2y, width, height);
	}
	
	public void init(float x, float y, float p1x, float p1y, float p2x, float p2y, float width, float height) throws SlickException
	{
		this.x = x;
		this.y = y;
		dir = 0;
		health = 50;
		
		damage = 30;
		
		walk = new Image[3];
		walk[0] = new Image("res" + File.separator + "Monster Sprites" + File.separator + "sword01.png").getScaledCopy(1.1f);
		walk[1] = new Image("res" + File.separator + "Monster Sprites" + File.separator + "sword02.png").getScaledCopy(1.1f);
		walk[2] = new Image("res" + File.separator + "Monster Sprites" + File.separator + "sword03.png").getScaledCopy(1.1f);
		
		attack = new Image[2];
		attack[0] = new Image("res" + File.separator + "Monster Sprites" + File.separator + "slash01.png").getScaledCopy(1.1f);
		attack[0] = new Image("res" + File.separator + "Monster Sprites" + File.separator + "slash01.png").getScaledCopy(1.1f);
		
		walking = new NewAnimation(walk, 100, true);
		attacking = new NewAnimation(attack, 200, false);
	
		this.width = walk[0].getWidth();
		this.height = walk[0].getHeight();
		
		this.chooseTarget(p1x, p1y, p2x, p2y, x, y);
		this.setRoundRect(x, y, width, height, width/2);
	}
	
	public void render(Graphics g) throws SlickException
	{
		walking.draw(x, y);
		this.setRoundRect(x, y, width, height, width/2);
	}
	
	public void update(float p1x, float p1y, float p2x, float p2y, int delta) throws SlickException
	{	
		if (target == 1)
		{
			//then we use p1's points
			if (this.x - p1x >= 0) {
				dir = 2;
				this.x -= VEL;
			}
			else {
				dir = 3;
				this.x += VEL;
			}
			
			if (this.y - p1y >= 0) {
				this.y -= VEL;
			}
			else {
				this.y += VEL;
			}
			
		}
		else 
		{
			//then we use p2's points
			if (this.x - p2x >= 0) {
				dir = 2;
				this.x -= VEL;
			}
			else {
				dir = 3;
				this.x += VEL;
			}
			
			if (this.y - p2y >= 0) {
				this.y -= VEL;
			}
			else {
				this.y += VEL;
			}
		}
		
	}
	
	public void chooseTarget(float p1x, float p1y, float p2x, float p2y, float width, float height){
		double y1 = Math.pow(((this.y - this.height/2) - (p1y - height/2)), 2);
		double x1 = Math.pow(((this.x - this.width/2) - (p1x - width/2)), 2);
		p1d = Math.sqrt(y1 + x1);
		
		double y2 = Math.pow(((this.y - this.height/2) - (p2y - height/2)), 2);
		double x2 = Math.pow(((this.x - this.width/2) - (p2x - width/2)), 2);
		p2d = Math.sqrt(y2 + x2);
		
		if (p1d <= p2d)
			target = 1;
		else
			target = 2;
	}
	
	public void takeDamage(int damage){
		health -= damage;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public boolean isDead(int damage){
		return ((health - damage) < 0);
	}
}
