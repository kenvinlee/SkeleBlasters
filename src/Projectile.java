import java.io.File;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile extends Collision{
	private final float VEL = 1f;

	private Image projectile;
	private float x, y, dir;
	private int damage, playerNo;

	public Projectile(float x, float y, float dir, int playerNo) throws SlickException{
		init(x, y, dir, playerNo);
	}

	public void init(float x, float y, float dir, int playerNo) throws SlickException
	{
		projectile = new Image("res" + File.separator + "Spell Sprites" + File.separator + "regular attack.png").getScaledCopy(0.5f);
		
		this.setCirc(x + projectile.getWidth()/2, y + projectile.getWidth()/2, projectile.getWidth()/2);
		
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.playerNo = playerNo;
		
		damage = 30;
	}

	public void render(Graphics g) throws SlickException{
		g.drawImage(projectile, x, y);
		this.setCirc(x + projectile.getWidth()/2, y + projectile.getWidth()/2, projectile.getWidth()/2);
	}

	public void update(int delta) throws SlickException{
		if (dir == 0)
		{
			y -= VEL;
		}
		else if (dir == 1)
		{
			y += VEL;
		}
		else if (dir == 2)
		{
			x -= VEL;
		}
		else if (dir == 3)
		{
			x += VEL;
		}
	}
	
	public boolean outOfBounds(){
		return (x < 0 || x > 1224 || y < 0 || y > 650); 
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public int getPlayerNo(){
		return this.playerNo;
	}
	
	public int getDamage(){
		return damage;
	}
}
