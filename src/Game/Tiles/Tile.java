package Game.Tiles;

import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Elemental on 1/1/2017.
 */

public class Tile {

    //statics

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(1);
    public static Tile graarockTile = new RockTile(Images.blocks[3],3);
    public static Tile dirtrockTile = new RockTile(Images.blocks[12],12);
    public static Tile dirtTile = new DirtTile(2);
    public static Tile upperleftTile = new BorderTile(Images.blocks[4],4);
    public static Tile upperrightTile = new BorderTile(Images.blocks[5],5);
    public static Tile lowerleftTile = new BorderTile(Images.blocks[6],6);
    public static Tile lowerrightTile = new BorderTile(Images.blocks[7],7);
    public static Tile leftwallTile = new BorderTile(Images.blocks[8],8);
    public static Tile rightwallTile = new BorderTile(Images.blocks[9],9);
    public static Tile topwallTile = new BorderTile(Images.blocks[10],10);
    public static Tile lowerwallTile = new BorderTile(Images.blocks[11],11);

    //CLASS


    protected BufferedImage texture;
    protected final int id;
    public static final int TILEWIDTH =64 ,TILEHEIGHT = 64;


    public Tile(BufferedImage texture, int id){

        this.texture=texture;
        this.id=id;

        tiles[id] = this;

    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture,x,y,TILEWIDTH,TILEHEIGHT,null);


    }

    public boolean isSolid(){
        return false;
    }

    public int getId() {
        return id;
    }
}
