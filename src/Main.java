import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.geom.*;

public class Main extends StateBasedGame
{	
	public static final int MAINMENUSTATE = 1;
	public static final int GAMESTATE = 2;
	public static final int GAMEOVERSTATE = 3;
	
	public Main() throws SlickException
	{
		// TODO Better background
		// TODO controller input fix
		// TODO Main Menu?
		// TODO Extra spells?
		
		super("Skele Blasting");
		
		//this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new GameState(GAMESTATE));
		this.addState(new GameOverState(GAMEOVERSTATE));
		this.enterState(GAMESTATE);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		this.getState(GAMESTATE).init(gc, this);
		SceneManager.getInstance().setGC(gc);
	}

	public void controllerButtonPressed(int controller, int button)
	{
		System.out.println(controller + ", " + button);
	}
	
	public static void main(String[] args) throws SlickException
	{
		AppGameContainer app = new AppGameContainer(new Main());
		app.setDisplayMode(1224, 650, false);
		app.setVSync(true);
		app.setShowFPS(true);
		app.setAlwaysRender(true);
		app.start();
	}
}
