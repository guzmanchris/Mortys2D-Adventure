package Game.Entities.Statics;

import Game.Entities.EntityBase;
import Game.Entities.Creatures.Player;
import Game.Items.Item;
import Main.Handler;
import Resources.Images;
import Worlds.BaseWorld;

import java.awt.*;


public class GuardMorty extends StaticEntity {

    private Rectangle ir = new Rectangle();
    public Boolean EP = false;

    private BaseWorld world;
    private int keys;
    private int coins;
    private int ticks=0;
    

    public GuardMorty(Handler handler, float x, float y,BaseWorld world) {
        super(handler, x, y, 96, 79);
        this.world=world;
        health=10000000;
        bounds.x=0;
        bounds.y=0;
        bounds.width = 79;
        bounds.height = 96;
        keys = 2;
        coins = 6; 

        ir.width = bounds.width;
        ir.height = bounds.height;
        int irx=(int)(bounds.x-handler.getGameCamera().getxOffset()+x);
        int iry= (int)(bounds.y-handler.getGameCamera().getyOffset()+height);
        ir.y=iry;
        ir.x=irx;
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
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.GuardMorty,(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),64,64,null);

        g.setColor(Color.black);
        checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());
    }

    private void checkForPlayer(Graphics g, Player p) {
        Rectangle pr = p.getCollisionBounds(0,0);

        
        if(!ir.contains(pr)) EP = false;
        if(ir.contains(pr) && !EP){
            g.drawImage(Images.E,(int) x+width,(int) y+10,32,32,null);
        }else if(ir.contains(pr)){
        	if(keys>0 || coins>0) {
	        	g.drawImage(Images.wizardInstructions[0],(int) x+width,(int) y, null);
	            
	            //Draw Items Required
	            g.drawImage(Images.keyItem,(int) x+width+8,(int) y+50-16, 32, 32, null);
	            g.drawString(Integer.toString(keys),(int) x+width+8+16 ,(int) y+50+16+20 );
	            g.drawImage(Images.coinItem,(int) x+width+8+32+20, (int) y+50-16,32,32,null);
	            g.drawString(Integer.toString(coins),(int) x+width+8+32+20+16 ,(int) y+50+16+20);
	            
	            //Retrieve Items if on players inventory
	            for(Item item : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()) {
	            		if(item.getId() == 4 && keys>0) {
	            			item.setCount(item.getCount()-1);
	            			keys--;
	            		}
	            		if(item.getId() == 3 && coins>0) {
	            			item.setCount(item.getCount()-1);
	            			coins--;
	            		}
	            }
        		}
	        	else {
	        		for(EntityBase e : handler.getWorld().getEntityManager().getEntities()) {
	        			if(e instanceof Door) {
	        				e.setVisible(true);
	        			}
	        		}
	        		g.drawImage(Images.wizardInstructions[1],(int) x+width,(int) y, null);
	        	}
        }

    }

    @Override
    public void die() {

    }
}
