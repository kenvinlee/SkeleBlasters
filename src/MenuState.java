import org.newdawn.slick.state.*;
import org.newdawn.slick.*;
import java.util.ArrayList;

/* MenuState.java
 * 
 * Tal Friedman
 * August 19, 2011
 * 
 * This is a helper class written to ease the creation of an intricate menu system.
 * Your menu states all extend this class, and feed in the constructor the image containing
 * all of the options, and the number of images. This class will internally deal with the
 * selection of options; simply call super.update, super.render, and super.init in the appropriate
 * subclass methods.
 * In addition, the isClicked method must be used for each of the options in the subclass, in order
 * to link the option press to an action.
 */
public class MenuState extends BasicGameState {

	protected int stateID = 1;
	private int startHeight, startWidth;
	private ArrayList<Image> options;
	private Image background;
	private Image option;
	private int numOptions;
	private float scaleStep = 0.0001f;
	private float[] scales;
	private Input input;
	private boolean mousePressed;
	private int mouseX, mouseY;
	
	public MenuState(int numOptions, int stateID, int startHeight, int startWidth)
	{
		this.numOptions = numOptions;
		this.stateID = stateID;
		this.startHeight = startHeight;
		this.startWidth = startWidth;
	}
	
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException 
	{
		options = new ArrayList<Image>();
		scales = new float[numOptions];
		
		for(int i = 0; i < numOptions; i++)
		{
			options.add(option.getSubImage(0, i * option.getHeight()/numOptions, option.getWidth(), option.getHeight()/numOptions));
			scales[i] = 1f;
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException 
	{
		background.draw(0,0);
		for(int i = 0; i < numOptions; i++)
		{
			options.get(i).draw(startWidth, i * options.get(0).getHeight() + startHeight, scales[i]);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame arg1, int delta) throws SlickException
	{
		input = gc.getInput();
		mousePressed = input.isMousePressed(input.MOUSE_LEFT_BUTTON);
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
		
		for(int i = 0; i < numOptions; i++)
		{
			boolean isSelected = (mouseY > i * options.get(0).getHeight() + startHeight && mouseY < (i+1) * options.get(0).getHeight()
								+ startHeight && mouseX > startWidth && mouseX < startWidth + option.getWidth());
			
			if(isSelected && scales[i] < 1.05f)
			{
				scales[i] += scaleStep * delta;
			}
			else if(!isSelected && scales[i] > 1.00f)
			{
				scales[i] -= scaleStep * delta;
			}
			else;
	
		}
		
		
	}
	
	protected boolean isClicked(GameContainer gc, int index)
	{
		return mousePressed &&(mouseY > index * options.get(0).getHeight() + startHeight && mouseY < (index+1) * options.get(0).getHeight()
				+ startHeight && mouseX > startWidth && mouseX < startWidth + option.getWidth());
	}
	
	protected void setImages(Image option, Image background)
	{
		this.option = option;
		this.background = background;
	}

}