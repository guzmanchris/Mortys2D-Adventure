package UI;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Elemental on 2/11/2017.
 */
public class UISpellGUI extends UIObject {
    private BufferedImage images;
    private ClickListlener clicker;


    public UISpellGUI(float x, float y, int width, int height, BufferedImage images,ClickListlener clicker ) {
        super(x, y, width, height);
        this.images=images;
        this.clicker=clicker;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(active){
            g.drawImage(images,(int)x,(int)y,width,heith,null);
        }

    }


    @Override
    public void onClick()
    {
        clicker.onClick();
    }
}