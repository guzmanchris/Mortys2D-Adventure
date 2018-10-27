package Game.GameStates;

import java.awt.Graphics;

import Main.Handler;
import Resources.Images;
import UI.ClickListlener;
import UI.UIImageButton;
import UI.UIManager;

public class ControlsState extends State{

	 private UIManager uiManager;
	 
	public ControlsState(Handler handler) {
		super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);
        
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-70, handler.getHeight()-32-10, 140, 32, Images.ReturntoMenu, new ClickListlener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);
                handler.getGame().reStart();
                State.setState(handler.getGame().menuState);
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
		 g.drawImage(Images.ControlsState,0,0,800,600,null);
	        uiManager.Render(g);
		
	}

}
