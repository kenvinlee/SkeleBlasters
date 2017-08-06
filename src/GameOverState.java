import java.io.File;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameOverState extends BasicGameState{
	private int stateID = 3;
	private Image p1win, p2win;
	private int winner;
	
	Input in;
	
	public GameOverState(int stateID) throws SlickException
	{
		this.stateID = stateID;
		
	}
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		p1win = new Image("res" + File.separator + "p1win.png");
		p2win = new Image("res" + File.separator + "p2win.png");
		
		winner = SceneManager.getInstance().getWinner();
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		
		if (winner == 1)
			p1win.draw(275,160);
		else if (winner == 2)
			p2win.draw(275,160);
		
		g.setColor(new Color(Color.white));
		g.drawString("Hit Escape to restart", 275, 450);
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		winner = SceneManager.getInstance().getWinner();
		
		if (gc.getInput().isKeyPressed(in.KEY_ESCAPE))
		{
			SceneManager.getInstance().init();
			sbg.enterState(2);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

	public void winner(int win){
		//win is the playerNo which won
		winner = win;
	}
}
