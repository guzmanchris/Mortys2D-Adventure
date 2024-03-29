package Game.Entities.Creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.sun.glass.events.KeyEvent;

import Game.Entities.EntityBase;
import Game.Inventories.Inventory;
import Game.Items.Item;
import Main.Handler;
import Resources.Animation;
import Resources.Images;
import Worlds.World2;


public class MortyAlly extends CreatureBase {

	private Animation animDown, animUp, animLeft, animRight;
	
	private int animWalkingSpeed = 150;
    private Inventory Mortyinventory;
    private Rectangle MortyCam;
    
    private int healthcounter =0;
    
    private boolean attackMode=false;
    
    private int ticks=0;
    
    private boolean justSpawned;
    
    private File audioFile;
    private AudioInputStream audioStream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip;

	
	public MortyAlly(Handler handler, float x, float y) {
		 super(handler, x, y, CreatureBase.DEFAULT_CREATURE_WIDTH, CreatureBase.DEFAULT_CREATURE_HEIGHT);
		 bounds.x=8*2;
	     bounds.y=18*2;
	     bounds.width=16*2;
	     bounds.height=14*2;
	     speed=DEFAULT_SPEED;
	     health=200;
	     visible=false;
	     justSpawned = false;
	     
	     MortyCam= new Rectangle();

	     animDown = new Animation(animWalkingSpeed, Images.MortyAlly_front);
	     animLeft = new Animation(animWalkingSpeed,Images.MortyAlly_left);
	     animRight = new Animation(animWalkingSpeed,Images.MortyAlly_right);
	     animUp = new Animation(animWalkingSpeed,Images.MortyAlly_back);

	     Mortyinventory= new Inventory(handler);
	     
	     try {
	            audioFile = new File("res/music/SendMortyOut.wav");
	            audioStream = AudioSystem.getAudioInputStream(audioFile);
	            format = audioStream.getFormat();
	            info = new DataLine.Info(Clip.class, format);
	            audioClip = (Clip) AudioSystem.getLine(info);
	            audioClip.open(audioStream);


	        } catch (UnsupportedAudioFileException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (LineUnavailableException e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public void tick() {
		if(visible) {
			ticks++;
			
			animDown.tick();
		    animUp.tick();
		    animRight.tick();
		    animLeft.tick();
	
		     //Press r to reposition next to player
		    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)) {
		    	spawn();
		    }
		    
		    //Press z to enable attack mode
	        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Z) && ticks>10){
	        	attackMode = !attackMode;
	        	ticks = 0;
	        }
	        
	        //Restart clip
	        if(justSpawned && ticks>125) {
	        	audioClip.setFramePosition(0);
	        	audioClip.stop();
	        	justSpawned = false;
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
			 if(handler.getWorld() instanceof World2 && itemUsed()) {
			    	visible=true;
				 	spawn();
			    }
		}

	}

	
	private void checkIfMove() {
		xMove=0;
		yMove=0;
		
		if(attackMode && enemiesInWorld()) {
			MortyCam.x = (int) (x - handler.getGameCamera().getxOffset() - (64 * 3));
		     MortyCam.y = (int) (y - handler.getGameCamera().getyOffset() - (64 * 3));
		     MortyCam.width = 64 * 7;
		     MortyCam.height = 64 * 7;
			
		     Rectangle cb = getCollisionBounds(0, 0);
	         Rectangle ar = new Rectangle();
	         int arSize = 13;
	         ar.width = arSize;
	         ar.height = arSize;
	
	         if (lu) {
	             ar.x = cb.x + cb.width / 2 - arSize / 2;
	             ar.y = cb.y - arSize;
	         } else if (ld) {
	             ar.x = cb.x + cb.width / 2 - arSize / 2;
	             ar.y = cb.y + cb.height;
	         } else if (ll) {
	             ar.x = cb.x - arSize;
	             ar.y = cb.y + cb.height / 2 - arSize / 2;
	         } else if (lr) {
	             ar.x = cb.x + cb.width;
	             ar.y = cb.y + cb.height / 2 - arSize / 2;
	         }
		   
			     for(EntityBase e : handler.getWorld().getEntityManager().getEntities()){
			    	 if(e instanceof SkelyEnemy || e instanceof SkelyEnemy2 || e instanceof MortyEnemy || e instanceof MortyEnemy2){ 
			    		 
				    	if(e.getCollisionBounds(0, 0).intersects(ar)) {	
			    		 checkAttacks();
				    	}
				    		 
				    		 
				    		 	if (x >= e.getX() - 8 && x <= e.getX() + 8) {//dont move
					 	            xMove = 0;
					 	        }
					 			else if(x>e.getX()) {//move Left
					 				xMove = -speed; 
					 			}
					 			else if(x<e.getX()) {//move Right
					 				xMove = speed;
					 			}
					 		 
					 			if ((y >= e.getY()-8 && y <= e.getY()+8)) {//dont move
					 	            yMove = 0;
					 	            }
					 			else if(y>e.getY()) {//move up
					 				yMove=-speed;
					 				}
					 			else if(y<e.getY()) {//move down
					 				yMove=speed;
					 				}
			 			
			    	 }
			     }
			}
		
			
		else {
				 float deltaY;
			     if (x >= handler.getWorld().getEntityManager().getPlayer().getX()-40 && x <= handler.getWorld().getEntityManager().getPlayer().getX() + 40) {//dont move
			            xMove = 0;
			        }
					else if(x>handler.getWorld().getEntityManager().getPlayer().getX()) {//move Left
						xMove = -speed; 
					}
					else if(x<handler.getWorld().getEntityManager().getPlayer().getX()) {//move Right
						xMove = speed;
					}
				 
					if ((y >= handler.getWorld().getEntityManager().getPlayer().getY()-40 && y <= handler.getWorld().getEntityManager().getPlayer().getY()+40)) {//dont move
			            yMove = 0;
			            }
					else if (y < handler.getWorld().getEntityManager().getPlayer().getY()) {//move down
		                yMove = speed;

		            } else if (y > handler.getWorld().getEntityManager().getPlayer().getY()) {//move up
		                yMove = -speed;
		            }	
			}
			
	}
	
	public void spawn() {
		audioClip.start();
		justSpawned = true;
		ticks=0;
		if(handler.getWorld().getEntityManager().getPlayer().getY()-60>64) {
			x=handler.getWorld().getEntityManager().getPlayer().getX();
			y=handler.getWorld().getEntityManager().getPlayer().getY()-60;
		}
		else {
			x=handler.getWorld().getEntityManager().getPlayer().getX();
			y=handler.getWorld().getEntityManager().getPlayer().getY()+60;
		}
		
	}

	
	public boolean enemiesInWorld() {
		for(EntityBase e :  handler.getWorld().getEntityManager().getEntities()) {
			if(e instanceof SkelyEnemy || e instanceof SkelyEnemy2 || e instanceof MortyEnemy || e instanceof MortyEnemy2 ) {
				return true;
			}
		}
		return false;
	}
	
	public boolean itemUsed() {
		    	for(Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()) {
		    		if(i.getId() == 5 ) {
		    			return false;
		    		}
		    	}
		    
		  return true;
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
