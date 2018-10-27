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
public class GameWonState extends State {

    private UIManager uiManager;

    public GameWonState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);


        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-70, handler.getHeight()/2-16, 140, 32, Images.ReturntoMenu, new ClickListlener() {
            @Override
            public void onClick() {
            	handler.getMouseManager().setUimanager(null);
                State.setState(handler.getGame().menuState);
            }
        }));
        
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-70, handler.getHeight()/2-16+32+16, 140, 32, Images.PlayAgain, new ClickListlener() {
            @Override
            public void onClick() {
            	handler.getMouseManager().setUimanager(null);
                State.setState(handler.getGame().instructionsState);
            }
        }));
    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
        g.drawImage(Images.title,0,0,800,600,null);
        uiManager.Render(g);

    }


}
