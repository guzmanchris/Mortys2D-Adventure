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
public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);


        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-70, 340, 140, 32, Images.BeginAdventure, new ClickListlener() {
            @Override
            public void onClick() {
            	handler.getMouseManager().setUimanager(null);
                State.setState(handler.getGame().instructionsState);
            }
        }));
        
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-70, 340+32+16, 140, 32, Images.Controls, new ClickListlener() {
            @Override
            public void onClick() {
            	handler.getMouseManager().setUimanager(null);
                //State.setState(handler.getGame().controlState);
            }
        }));
    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();

        // Temporarily just go directly to the GameState, skip the menu state!
        //handler.getMouseManager().setUimanager(null);
        //State.setState(handler.getGame().gameState);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
        g.drawImage(Images.title,0,0,800,600,null);
        uiManager.Render(g);

    }


}
