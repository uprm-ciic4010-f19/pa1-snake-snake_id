package Game.GameStates;

import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class PauseState extends State {

    private int count = 0;
    private UIManager uiManager;

    public PauseState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);
        
        //This is a resume button which does work
        uiManager.addObjects(new UIImageButton(56, 223, 128, 64, Images.Resume, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().gameState);
        }));
        
        //This is the back to title button which we repurposed from the Options button coso.
        uiManager.addObjects(new UIImageButton(56, 223+(64+16), 128, 64, Images.BTitle, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
        }));
        
        //as you can see the options button is missing because we have no options to set.
        //I mean this is snake game what options can you even set?
        




    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        count++;
        if( count>=30){
            count=30;
        }
        if(handler.getKeyManager().pbutt && count>=30){
            count=0;

            State.setState(handler.getGame().gameState);
        }


    }

    @Override
    public void render(Graphics g) {
    	//Had to change the size of the image to 600x600 as it was originally 800x600
        g.drawImage(Images.Pause,0,0,600,600,null);
        uiManager.Render(g);

    }
}
