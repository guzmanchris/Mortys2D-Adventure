package Game.Entities.Statics;

import Game.items.Item;
import Game.tiles.Tile;
import Resources.Images;
import main.Handler;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Elemental on 1/2/2017.
 */
public class Rock extends StaticEntity {

    File audioFile;
    AudioInputStream audioStream;
    AudioFormat format;
    DataLine.Info info;
    Clip audioClip;

    public Rock(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x=0;
        bounds.y=0;
        bounds.width = 64;
        bounds.height = 64;
        try {
            audioFile = new File("res/music/Pickaxe.wav");
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
        if(isBeinghurt()){
            audioClip.start();
        }
        if(!isBeinghurt() && !handler.getKeyManager().attbut){
            audioClip.stop();
        }
        if(!isActive()){
            audioClip.stop();

        }

    }

    @Override
    public void render(Graphics g) {
        renderLife(g);
        g.drawImage(Images.blocks[14],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);

    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int)x + bounds.x,(int)y + bounds.y));
    }
}
