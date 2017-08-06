import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameState extends BasicGameState{
	private int stateID = 2;
	private GameContainer gc;
	private Input input;
	
	private SceneManager scene;
	
	public GameState(int stateID){
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		this.gc = gc;
		
		scene = scene.getInstance();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		scene.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		input = gc.getInput();
		input.initControllers();
		input.clearControlPressedRecord();
		
		scene.update(gc, input, sbg, delta);
		if (scene.getWinner() != 0)
		{
			sbg.enterState(3);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}
}
