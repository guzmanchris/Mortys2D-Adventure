package Game.SpellCast;

import Main.Handler;

import java.awt.image.BufferedImage;

/**
 * Created by Elemental on 2/11/2017.
 */
public abstract class Spells {

    protected int id;
    protected BufferedImage textture;
    protected Handler handler;

    public Spells(Handler handler, BufferedImage texture, int id){
        this.id=id;
        this.textture=texture;
        this.handler=handler;

    }
    public void Fire(){

    }

    public int getId() {
        return id;
    }
}
