package Game.Entities.Statics;

import Game.Entities.EntityBase;
import Game.Entities.Creatures.Player;
import Game.Items.Item;
import Main.Handler;
import Resources.Images;

import java.awt.*;


public class InjuredMorty extends StaticEntity {

    private Rectangle ir = new Rectangle();
    public Boolean EP = false;
    public boolean finalQuestComplete;
;
    private int ticks=0;
    

    public InjuredMorty(Handler handler, float x, float y) {
        super(handler, x, y, 96, 79);
        health=10000000;
        bounds.x=0;
        bounds.y=0;
        bounds.width = 79;
        bounds.height = 96;
        
        finalQuestComplete = false;

        ir.width = bounds.width;
        ir.height = bounds.height;
        int irx=(int)(bounds.x-handler.getGameCamera().getxOffset()+x);
        int iry= (int)(bounds.y-handler.getGameCamera().getyOffset()+height);
        ir.y=iry;
        ir.x=irx;
        
    }
    

	public boolean isFinalQuestComplete() {
		return finalQuestComplete;
	}


	@Override
    public void tick() {

		ticks++;
		
        if(isBeinghurt()){
            setHealth(10000000);
        }

        if(handler.getKeyManager().attbut && ticks>10){
            EP=!EP;
            ticks=0;
        }
        
        for(EntityBase e : handler.getWorld().getEntityManager().getEntities()) {
        	if(e instanceof Door) {
        		e.setVisible(true);
        	}
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.InjuredMorty,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),64,64,null);

        g.setColor(Color.black);	 
        	checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());
    }

    private void checkForPlayer(Graphics g, Player p) {
        Rectangle pr = p.getCollisionBounds(0,0);

        for(Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()) {
    		if(i.getName() == "Kalaxian Crystal" && i.getCount()>0) {
        			finalQuestComplete=true;
    		}
        }
        
        if(!ir.contains(pr)) EP = false;
        if(ir.contains(pr) && !EP){
            g.drawImage(Images.E,(int) x+width,(int) y+10,32,32,null);
        }else if(ir.contains(pr) && EP){
		        			
        	if(!finalQuestComplete) {
        		g.drawImage(Images.injuredMortyMessages[0],(int) x+width,(int) y+10,null);
		        }
		    else {
		  		g.drawImage(Images.injuredMortyMessages[1],(int) x+width,(int) y+10,null);
		     	}
	        			
        }
    		}


    @Override
    public void die() {

    }
}
