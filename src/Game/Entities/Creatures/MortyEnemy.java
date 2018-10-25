package Game.Entities.Creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Game.Entities.EntityBase;
import Game.Inventories.Inventory;
import Game.Items.Item;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class MortyEnemy extends CreatureBase {

	private Animation animDown, animUp, animLeft, animRight;
	
	private int animWalkingSpeed = 150;
    private Inventory Mortyinventory;
    private Rectangle MortyCam;
    
    private int healthcounter =0;
    
    private Random randint;
    private int moveCount=0;
    private int direction;
    
    private File audioFile;
    private AudioInputStream audioStream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip;
    
    private File audioFile2;
    private AudioInputStream audioStream2;
    private AudioFormat format2;
    private DataLine.Info info2;
    private Clip audioClip2;
	
	public MortyEnemy(Handler handler, float x, float y) {
		 super(handler, x, y, CreatureBase.DEFAULT_CREATURE_WIDTH, CreatureBase.DEFAULT_CREATURE_HEIGHT);
		 bounds.x=8*2;
	     bounds.y=18*2;
	     bounds.width=16*2;
	     bounds.height=14*2;
	     speed=1.5f;
	     health=50;
	     
	     MortyCam= new Rectangle();



	     randint = new Random();
	     direction = randint.nextInt(4) + 1;

	     animDown = new Animation(animWalkingSpeed, Images.MortyEnemy_front);
	     animLeft = new Animation(animWalkingSpeed,Images.MortyEnemy_left);
	     animRight = new Animation(animWalkingSpeed,Images.MortyEnemy_right);
	     animUp = new Animation(animWalkingSpeed,Images.MortyEnemy_back);

	     Mortyinventory= new Inventory(handler);
	     
	     try {
	            audioFile = new File("res/music/zombieFollow.wav");
	            audioStream = AudioSystem.getAudioInputStream(audioFile);
	            format = audioStream.getFormat();
	            info = new DataLine.Info(Clip.class, format);
	            audioClip = (Clip) AudioSystem.getLine(info);
	            audioClip.open(audioStream);
	            
	            audioFile2 = new File("res/music/zombieDeath.wav");
	            audioStream2 = AudioSystem.getAudioInputStream(audioFile2);
	            format2 = audioStream.getFormat();
	            info2 = new DataLine.Info(Clip.class, format2);
	            audioClip2 = (Clip) AudioSystem.getLine(info2);
	            audioClip2.open(audioStream2);



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
		 animDown.tick();
	        animUp.tick();
	        animRight.tick();
	        animLeft.tick();

	        moveCount ++;
	        if(moveCount>=60){
	            moveCount=0;
	            direction = randint.nextInt(4) + 1;
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

	
	private void checkIfMove() {
        xMove = 0;
        yMove = 0;

        MortyCam.x = (int) (x - handler.getGameCamera().getxOffset() - (64 * 3));
        MortyCam.y = (int) (y - handler.getGameCamera().getyOffset() - (64 * 3));
        MortyCam.width = 64 * 7;
        MortyCam.height = 64 * 7;
        

        if (MortyCam.contains(handler.getWorld().getEntityManager().getPlayer().getX() - handler.getGameCamera().getxOffset(), handler.getWorld().getEntityManager().getPlayer().getY() - handler.getGameCamera().getyOffset())
                || MortyCam.contains(handler.getWorld().getEntityManager().getPlayer().getX() - handler.getGameCamera().getxOffset() + handler.getWorld().getEntityManager().getPlayer().getWidth(), handler.getWorld().getEntityManager().getPlayer().getY() - handler.getGameCamera().getyOffset() + handler.getWorld().getEntityManager().getPlayer().getHeight())) {

            Rectangle cb = getCollisionBounds(0, 0);
            Rectangle ar = new Rectangle();
            int arSize = 13;
            ar.width = arSize;
            ar.height = arSize;
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

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

            for (EntityBase e : handler.getWorld().getEntityManager().getEntities()) {
                if (e.equals(this))
                    continue;
                if (e.getCollisionBounds(0, 0).intersects(ar) && e.equals(handler.getWorld().getEntityManager().getPlayer())) {

                    checkAttacks();
                    return;
                }
            }


            if (x >= handler.getWorld().getEntityManager().getPlayer().getX() - 8 && x <= handler.getWorld().getEntityManager().getPlayer().getX() + 8) {//nada

                xMove = 0;
            } else if (x < handler.getWorld().getEntityManager().getPlayer().getX()) {//move right

                xMove = speed;

            } else if (x > handler.getWorld().getEntityManager().getPlayer().getX()) {//move left

                xMove = -speed;
            }

            if (y >= handler.getWorld().getEntityManager().getPlayer().getY() - 8 && y <= handler.getWorld().getEntityManager().getPlayer().getY() + 8) {//nada
                yMove = 0;
            } else if (y < handler.getWorld().getEntityManager().getPlayer().getY()) {//move down
                yMove = speed;

            } else if (y > handler.getWorld().getEntityManager().getPlayer().getY()) {//move up
                yMove = -speed;
            }
            


        } else {


            switch (direction) {
                case 1://up
                    yMove = -speed;
                    break;
                case 2://down
                    yMove = speed;
                    break;
                case 3://left
                    xMove = -speed;
                    break;
                case 4://right
                    xMove = speed;
                    break;
            }
            audioClip.stop();
        }
    }

	@Override
	public void render(Graphics g) {
		 g.drawImage(getCurrentAnimationFrame(animDown,animUp,animLeft,animRight,Images.MortyEnemy_front,Images.MortyEnemy_back,Images.MortyEnemy_left,Images.MortyEnemy_right), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	        if(isBeinghurt() && healthcounter<=120){
	            g.setColor(Color.white);
	            g.drawString("MortyHealth: " + getHealth(),(int) (x-handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()-20));
	        }

	}

	@Override
	public void die() {
		audioClip2.start();
		audioClip.close();
		handler.getWorld().getItemManager().addItem(Item.wizardItem.createNew((int)x + bounds.x,(int)y + bounds.y,1));

	}

}
