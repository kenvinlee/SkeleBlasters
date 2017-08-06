import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/*
 * SceneManager class - 
 * This class puts together all the objects onto the playing field
 * Draws the characters separately (bad)
 * Draws the list of projectiles (okay)
 * Draws the list of enemies (okay)
 * Draws the background (can't fuck this one up... I hope)
 * Programmed as a singleton class to easily get interactions between all objects
 */

public class SceneManager 
{
	private static SceneManager instance = null;

	private int winner;
	
	private Character p1, p2;
	private ArrayList<Projectile> projectiles;
	private ArrayList<SkeleSword> swords;
	
	private Background bg;

	private SkeleFactory sFact;
	
	private GameContainer gc;
	private Input input;

	public static SceneManager getInstance() throws SlickException {
		if (instance == null){
			instance = new SceneManager();
		}

		return instance;
	}

	public SceneManager() throws SlickException{
		init();
	}

	public void init() throws SlickException
	{
		winner = 0;
		
		p1 = new Character(1);
		p2 = new Character(2);

		sFact = new SkeleFactory();
		
		bg = new Background();
		
		projectiles = new ArrayList<Projectile>();
		swords = new ArrayList<SkeleSword>();
		sFact.spawn(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p1.getWidth(), p1.getHeight());
		swords.addAll(sFact.getSkeles());
	}


	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		bg.render(g);
		
		p1.render(gc, g);
		p2.render(gc, g);

		for (int i = 0; i < projectiles.size(); i++)
		{
			projectiles.get(i).render(g);
		}

		for (int i = 0; i < swords.size(); i++)
		{
			swords.get(i).render(g);
		}
	}

	public void update(GameContainer gc, Input in, StateBasedGame sbg, int delta) throws SlickException
	{
		p1.update(gc, in, delta);
		p2.update(gc, in, delta);

		//projectile collisions with the monsters
		//this is a mess because you have to check which player fired the projectile before incrementing the score
		for (int i = 0; i < projectiles.size(); i++)
		{	
			for (int j = 0; j < swords.size(); j++)
			{	
				projectiles.get(i).update(delta);
				
				if (projectiles.get(i).outOfBounds()){
					//must break or game will crash
					projectiles.remove(i);
					break;
				}
				else if (projectiles.get(i).cIntersect(swords.get(j).getrRect()) && projectiles.get(i).getPlayerNo() == 2)
				{
					if (swords.get(j).isDead(projectiles.get(i).getDamage())){
						p2.incrementScore();
						swords.remove(j);
					}
					else
						swords.get(j).takeDamage(projectiles.get(i).getDamage());
					projectiles.remove(i);
					break;
				}
				else if (projectiles.get(i).cIntersect(swords.get(j).getrRect()) && projectiles.get(i).getPlayerNo() == 1)
				{
					if (swords.get(j).isDead(projectiles.get(i).getDamage()))
					{
						p1.incrementScore();
						swords.remove(j);
					}
					else
						swords.get(j).takeDamage(projectiles.get(i).getDamage());
					projectiles.remove(i);
					break;
				}
			}
			//trim so that the arraylists don't grow exponentially and slow the program down to a sloth
			projectiles.trimToSize();
			swords.trimToSize();
		}
		
		//enemy-player collisions
		//TODO add player damage flashing
		for (int i = 0; i < swords.size(); i++)
		{
			swords.get(i).update(p1.getX(), p1.getY(), p2.getX(), p2.getY(), delta);
			
			if (p1.rRectIntersect(swords.get(i).getrRect()))
			{
				p1.takeDamage(swords.get(i).getDamage());
				///p2.reset();
				swords.remove(i);
			}
			else if (p2.rRectIntersect(swords.get(i).getrRect()))
			{
				p2.takeDamage(swords.get(i).getDamage());
				//p2.reset();
				swords.remove(i);
			}
			
			swords.trimToSize();
		}
		
		if (p1.isDead() || p2.getScore() == 50)
		{
			winner(2);
		} 
		else if (p2.isDead() || p1.getScore() == 50)
		{
			winner(1);
		}

		if (swords.isEmpty())
		{
			sFact.clear();
			sFact.newLvl();
			sFact.spawn(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p1.getWidth(), p1.getHeight());
			swords.addAll(sFact.getSkeles());
			
		}
	}

	public ArrayList<Projectile> getProjectiles(){
		return projectiles;
	}
	
	public void winner(int playerNo)
	{
		winner = playerNo;
	}
	
	public int getWinner()
	{
		return winner;
	}
	
	public void setGC(GameContainer gc){
		this.gc = gc;
	}
}
