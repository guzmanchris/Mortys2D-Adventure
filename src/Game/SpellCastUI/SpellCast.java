package Game.SpellCastUI;

import Main.Handler;
import Resources.Images;
import UI.UIInventory;
import UI.UIManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Elemental on 2/10/2017.
 */
public class SpellCast {

    private Handler handler;
    private boolean active = false;
    private UIManager uiManager;

    public SpellCast(Handler handler){
        this.handler=handler;

        uiManager = new UIManager(handler);
        uiManager.addObjects(new UIInventory(598,556, 202, 44, Images.spellGUI,() -> {

        }));

    }
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)){
            active=!active;
        }
        if(!active){
            return;
        }






    }

    public void render(Graphics g) {

        if (!active) {
            uiManager.isActive(uiManager.getObjects(), false);
            return;
        }


        uiManager.isActive(uiManager.getObjects(), true);
        uiManager.Render(g);
    }

    //GET SET
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }



}
