import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;

/* Class for the character object.
 * This contains - Controller methods, the rendering and updates, and score tracking
 * I fucked up and coupled for two players specifically, so if I'm to add extra characters
 * I'd have to go through a shit tonne of rewriting... Um.. Decoupling's kind of hard at this point
 * and it's probably easier to recode stuff lol.
 */

public class Character extends Collision {
	private Image[] wizWalk;
	private Image[] wizStill;
	
	private final float VEL = 1f;

	private NewAnimation walking;
	private NewAnimation still;

	private int playerNo;
	private int health;
	private int spell;
	private int score;
	private float x, y, dir;
	private float width, height;
	private boolean isWalking;

	//okay you're coupling because you were in a time crunch for a 24 hour hackathon
	//this shouldn't break
	//but don't reuse this code for multiplayer games please.
	//2 players max..
	//don't do this again
	
	public Character(int player) throws SlickException
	{
		init(player);
	}

	public void init(int player) throws SlickException
	{
		wizWalk = new Image[2];
		wizWalk[0] = new Image("res" + File.separator + "Character Sprites" + File.separator + "Walking 1.png").getScaledCopy(0.8f);
		wizWalk[1] = new Image("res" + File.separator + "Character Sprites" + File.separator + "damaged.png").getScaledCopy(0.8f);

		wizStill = new Image[2];
		wizStill[0] = new Image("res" + File.separator + "Character Sprites" + File.separator + "wizard.png").getScaledCopy(0.8f);
		wizStill[1] = new Image("res" + File.separator + "Character Sprites" + File.separator + "Casting Spell.png").getScaledCopy(0.8f);

		walking = new NewAnimation(wizWalk, 100, false);
		still = new NewAnimation(wizStill, 1, false);
		
		if (player == 1)
		{
			x = 612-wizStill[0].getWidth()*2;
			dir = 2;
		}
		else
		{
			x = 612+wizStill[0].getWidth();
			dir = 3;
		}
		y = 325;
		playerNo = player;
		isWalking = false;
		
		health = 300;
		spell = 0;
		score = 0;
		
		width = wizStill[0].getWidth();
		height = wizStill[0].getHeight();
		
		this.setRoundRect(x, y, width, height, width/2);
	}

	/* Render sets the rotation of the sprites
	 * Sets the drawing of the score and health
	 */
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if (dir == 0)
		{
			still.setRotation(0);
			walking.setRotation(0);
		}
		else if (dir == 1)
		{
			still.setRotation(180);
			walking.setRotation(180);
		}
		else if (dir == 2)
		{
			still.setRotation(-90);
			walking.setRotation(-90);
		}
		else if (dir == 3)
		{
			still.setRotation(90);
			walking.setRotation(90);
		}

		walking.draw(x, y);
		
		g.setColor(new Color(Color.black));
		
		if (playerNo == 1) {
			g.drawString("P1 Health " + health, 15, 615);
			g.drawString("P1 Score: " + score, 15, 630);
		}
		
		if (playerNo == 2) {
			g.drawString("P2 Health " + health, 1050, 615);
			g.drawString("P2 Score: " + score, 1050, 630);
		}
		
		this.setRoundRect(x, y, width, height, width/2);
	}

	/* The controls are here (coupling made this not so easy)
	 * So just what the controls actually do in relation to the positioning of the characters
	 */
	public void update(GameContainer gc, Input input, int delta) throws SlickException{
		// TODO Auto-generated method stub
		if (playerNo == 1)
		{
			if (input.isKeyDown(input.KEY_UP))
			{
				dir = 0;
				if ((y - VEL) < 0)
					y += (VEL + 1);
				else
					y -= VEL;
			}

			if (input.isKeyDown(input.KEY_DOWN))
			{
				dir = 1;
				if ((y + VEL + height) > 650)
					y -= (VEL + 1);
				else
					y += VEL;
			}

			if (input.isKeyDown(input.KEY_LEFT))
			{
				dir = 2;
				if ((x - VEL) < 0) 
					x += (VEL + 1);
				else
					x -= VEL;
			}

			if (input.isKeyDown(input.KEY_RIGHT))
			{
				dir = 3;
				if ((x + VEL + width) > 1224) 
					x -= (VEL + 1);
				else
					x += VEL;
			}
			
			if (input.isKeyPressed(input.KEY_ENTER))
			{
				if (dir == 0)
					shoot(SceneManager.getInstance().getProjectiles(), new Projectile(this.x + 25, this.y, this.dir, this.playerNo));
				else if (dir == 1)
					shoot(SceneManager.getInstance().getProjectiles(), new Projectile(this.x + 25, this.y + height, this.dir, this.playerNo));
				else if (dir == 2)
					shoot(SceneManager.getInstance().getProjectiles(), new Projectile(this.x, this.y + 25, this.dir, this.playerNo));
				else if (dir == 3)
					shoot(SceneManager.getInstance().getProjectiles(), new Projectile(this.x + width, this.y + 25, this.dir, this.playerNo));					
			}
		}
		else
		{
			if (input.isKeyDown(Input.KEY_W))
			{
				dir = 0;
				if ((y - VEL) < 0) 
					y += (VEL + 1);
				else
					y -= VEL;
			}

			if (input.isKeyDown(Input.KEY_S))
			{
				dir = 1;
				
				if ((y + VEL + height) > 650) 
					y -= (VEL + 1);
				else
					y += VEL;
			}

			if (input.isKeyDown(Input.KEY_A))
			{
				dir = 2;
				
				if ((x - VEL) < 0) 
					x += (VEL + 1);
				else
					x -= VEL;
			}

			if (input.isKeyDown(Input.KEY_D))
			{
				dir = 3;
				
				if ((x + VEL + width) > 1224) 
					x -= (VEL + 1);
				else
					x += VEL;
			}
			
			if (input.isKeyPressed(Input.KEY_SPACE))
			{
				if (dir == 0)
					shoot(SceneManager.getInstance().getProjectiles(), new Projectile(this.x + 25, this.y, this.dir, this.playerNo));
				else if (dir == 1)
					shoot(SceneManager.getInstance().getProjectiles(), new Projectile(this.x + 25, this.y + height, this.dir, this.playerNo));
				else if (dir == 2)
					shoot(SceneManager.getInstance().getProjectiles(), new Projectile(this.x, this.y + 25, this.dir, this.playerNo));
				else if (dir == 3)
					shoot(SceneManager.getInstance().getProjectiles(), new Projectile(this.x + width, this.y + 25, this.dir, this.playerNo));					
			}
		}
	}

	//adds a projectile object to the list of projectiles to be drawn
	public void shoot(ArrayList<Projectile> list, Projectile p){
		list.add(p);
	}

	//just some getter and setter functions to be used in the game logic
	public void incrementScore(){
		score++;
	}
	
	public void takeDamage(int damage){
		health -= damage;
	}
	

	public boolean isDead(){
		return (this.health <= 0);
	}
	
	public int getHealth(){
		return health;
	}
	
	public void resetHealth(){
		health = 300;
	}
	
	public float getX(){
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public float getDir(){
		return this.dir;
	}

	public float getWidth(){
		return this.width;
	}

	public float getHeight(){
		return this.height;
	}
	
	public int getScore(){
		return this.score;
	}

}
