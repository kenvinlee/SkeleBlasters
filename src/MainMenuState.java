import java.io.File;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends MenuState
{
		
        public MainMenuState(int stateID)
        {
        		super(4, stateID, 150, 150);
        		this.stateID = stateID;
        }

        @Override
        public int getID()
        {
                return stateID;
        }
        
        
        public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
        {
               //super.setImages(new Image("Data" + File.separator + "HUD" + File.separator + "menu buttons.png"), new Image("Data" + File.separator + "HUD" + File.separator + "menu art.png"));
               //super.init(gc, sbg);
        }
        
        public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
        {
        	super.update(gc, sbg, delta);
        	
        	/*
        	//If the first button is clicked, switch to GameplayState
        	if(isClicked(gc, 0))
        	{
        		sbg.enterState(YouAreAWizard.GAMEPLAYSTATE);
        	}
        	//If the 2nd button is clicked, switch to InstructionState
        	if(isClicked(gc, 1))
        	{
        		sbg.enterState(YouAreAWizard.INSTRUCTIONSTATE);
        	}
        	//If the 3rd button is clicked, switch to CreditState
        	if(isClicked(gc, 2))
        	{
        		sbg.enterState(YouAreAWizard.CREDITSTATE);
        	}
        	//If the 4th button is clicked, exit
        	if(isClicked(gc, 3))
        	{
        		gc.exit();
        	}*/
        }
      
}