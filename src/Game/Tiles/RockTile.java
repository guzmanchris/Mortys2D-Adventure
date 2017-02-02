package Game.Tiles;

import java.awt.image.BufferedImage;

/**
 * Created by Elemental on 1/1/2017.
 */
public class RockTile extends Tile {
    public RockTile(BufferedImage texture,int id) {
        super(texture, id);

    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
