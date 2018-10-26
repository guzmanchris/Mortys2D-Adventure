package Game.GameStates;



import Main.Game;
import Resources.Images;
import UI.ClickListlener;
import UI.UIImageButton;
import UI.UIManager;
import Main.Handler;

import java.awt.*;

/**
 * Created by Elemental on 12/10/2016.
 */
public class GameOverState extends State {

    private UIManager uiManager;

    public GameOverState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);
        
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-70, 100, 140, 32, Images.PlayAgain, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().instructionsState);
        }));
        
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-70, 100+32+16, 140, 32, Images.ReturntoMenu, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
        }));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
    	g.drawImage(Images.gameOver,0,0,800,600,null);
        uiManager.Render(g);

    }


}
