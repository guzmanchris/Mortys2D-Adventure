package Game.Entities.Statics;

import Game.Entities.Creatures.Player;
import Main.Handler;
import Resources.Images;
import Worlds.BaseWorld;

import java.awt.*;


public class WizardHumanoid extends StaticEntity {

    private Rectangle ir = new Rectangle();
    public Boolean EP = false;

    private BaseWorld world;

    public WizardHumanoid(Handler handler, float x, float y,BaseWorld world) {
        super(handler, x, y, 96, 79);
        this.world=world;
        health=10000000;
        bounds.x=0;
        bounds.y=0;
        bounds.width = 79;
        bounds.height = 96;

        ir.width = bounds.width;
        ir.height = bounds.height;
        int irx=(int)(bounds.x-handler.getGameCamera().getxOffset()+x);
        int iry= (int)(bounds.y-handler.getGameCamera().getyOffset()+height);
        ir.y=iry;
        ir.x=irx;
    }

    @Override
    public void tick() {

        if(isBeinghurt()){
            setHealth(10000000);
        }

        if(handler.getKeyManager().attbut){
            EP=true;

        }else if(!handler.getKeyManager().attbut){
            EP=false;
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.wizardHumanoid,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);

        g.setColor(Color.black);
        checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());
    }

    private void checkForPlayer(Graphics g, Player p) {
        Rectangle pr = p.getCollisionBounds(0,0);

        if(ir.contains(pr) && !EP){
            g.drawImage(Images.E,(int) x+width,(int) y+10,32,32,null);
        }else if(ir.contains(pr) && EP){
            //TODO ask for items and check if player has them

        }


    }

    @Override
    public void die() {

    }
}
