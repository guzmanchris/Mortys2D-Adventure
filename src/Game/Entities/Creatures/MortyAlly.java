package Game.Entities.Creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.sun.glass.events.KeyEvent;

import Game.Entities.EntityBase;
import Game.Inventories.Inventory;
import Game.Items.Item;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class MortyAlly extends CreatureBase {

	private Animation animDown, animUp, animLeft, animRight;
	
	private int animWalkingSpeed = 150;
    private Inventory Mortyinventory;
    private Rectangle MortyCam;
    
    private int healthcounter =0;
    

	
	public MortyAlly(Handler handler, float x, float y) {
		 super(handler, x, y, CreatureBase.DEFAULT_CREATURE_WIDTH, CreatureBase.DEFAULT_CREATURE_HEIGHT);
		 bounds.x=8*2;
	     bounds.y=18*2;
	     bounds.width=16*2;
	     bounds.height=14*2;
	     speed=DEFAULT_SPEED;
	     health=100;
	     visible=false;
	     
	     MortyCam= new Rectangle();

	     animDown = new Animation(animWalkingSpeed, Images.MortyAlly_front);
	     animLeft = new Animation(animWalkingSpeed,Images.MortyAlly_left);
	     animRight = new Animation(animWalkingSpeed,Images.MortyAlly_right);
	     animUp = new Animation(animWalkingSpeed,Images.MortyAlly_back);

	     Mortyinventory= new Inventory(handler);
	}

	@Override
	public void tick() {
		if(visible) {
			animDown.tick();
		    animUp.tick();
		    animRight.tick();
		    animLeft.tick();
	
		     //Press r to reposition next to player
		    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)) {
		    	spawn();
		    }
		    checkIfMove();
	
		    move();
	
	
		    if(isBeinghurt()){
		        healthcounter++;
		        if(healthcounter>=120){
		                setBeinghurt(false);
		                System.out.print(isBeinghurt());
		            }
		    }
		    if(healthcounter>=120&& !isBeinghurt()){
		            healthcounter=0;
		    }
	
	
		    Mortyinventory.tick();
		}
		else {
			for(Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()) {
				if(handler.getKeyManager().allybut && i.getName().equals("Wizard")) {
					visible=true;
					i.setCount(i.getCount()-1);
					spawn();
				}
			}
		}

	}

	
	private void checkIfMove() {
		xMove=0;
		yMove=0;
		
		if (x >= handler.getWorld().getEntityManager().getPlayer().getX() - 3 && x <= handler.getWorld().getEntityManager().getPlayer().getX() + 3) {//dont move
            xMove = 0;
        }
		else if(x>handler.getWorld().getEntityManager().getPlayer().getX()) {//move Left
			xMove = -speed; 
		}
		else if(x<handler.getWorld().getEntityManager().getPlayer().getX()) {//move Right
			xMove = speed;
		}
	 
		if ((y >= handler.getWorld().getEntityManager().getPlayer().getY()+60-3 && y <= handler.getWorld().getEntityManager().getPlayer().getY()+60+3) || (y >= handler.getWorld().getEntityManager().getPlayer().getY()-60-3 && y <= handler.getWorld().getEntityManager().getPlayer().getY()-60+3)) {//dont move
            yMove = 0;
            }
		else if(y>handler.getWorld().getEntityManager().getPlayer().getY()+60 || y>handler.getWorld().getEntityManager().getPlayer().getY()-60) {//move up
			yMove=-speed;
			}
		else if(y<handler.getWorld().getEntityManager().getPlayer().getY()+60 || y<handler.getWorld().getEntityManager().getPlayer().getY()-60) {//move down
			yMove=speed;
			}
			
	}
	
	public void spawn() {
		if(handler.getWorld().getEntityManager().getPlayer().getY()-60>64) {
			x=handler.getWorld().getEntityManager().getPlayer().getX();
			y=handler.getWorld().getEntityManager().getPlayer().getY()-60;
		}
		else {
			x=handler.getWorld().getEntityManager().getPlayer().getX();
			y=handler.getWorld().getEntityManager().getPlayer().getY()+60;
		}
	}

	
	@Override
	public void render(Graphics g) {
		if(visible) {
				g.drawImage(getCurrentAnimationFrame(animDown,animUp,animLeft,animRight,Images.MortyAlly_front,Images.MortyAlly_back,Images.MortyAlly_left,Images.MortyAlly_right), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		        if(isBeinghurt() && healthcounter<=120){
		            g.setColor(Color.white);
		            g.drawString("MortyHealth: " + getHealth(),(int) (x-handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()-20));
		        }
		}
	}

	@Override
	public void die() {
		//TODO play a sound.

	}

}
