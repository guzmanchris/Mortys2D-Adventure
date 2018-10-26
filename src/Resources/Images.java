package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Elemental on 12/19/2016.
 */
public class Images {

    private static final int width = 32, height = 32;
    private static final int Bwidth = 64, Bheight = 64;
    private static final int Rwidth = 56, Rheight = 93;
    private static final int Fwidth = 512, Fheight = 197;

    public static BufferedImage[] blocks;
    public static BufferedImage[] blocks2;
    public static BufferedImage[] player_right;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_front;
    public static BufferedImage[] player_back;
    public static BufferedImage[] SkelyEnemy_right;
    public static BufferedImage[] SkelyEnemy_left;
    public static BufferedImage[] SkelyEnemy_front;
    public static BufferedImage[] SkelyEnemy_back;
    public static BufferedImage[] MortyEnemy_right;
    public static BufferedImage[] MortyEnemy_left;
    public static BufferedImage[] MortyEnemy_front;
    public static BufferedImage[] MortyEnemy_back;
    public static BufferedImage[] MortyAlly_right;
    public static BufferedImage[] MortyAlly_left;
    public static BufferedImage[] MortyAlly_front;
    public static BufferedImage[] MortyAlly_back;
    public static BufferedImage[] butstart;
    public static BufferedImage[] enterThePortal;
    public static BufferedImage[] particleSmoke;
    public static BufferedImage[] items;
    public static BufferedImage[] numbers;
    public static BufferedImage inventory;
    public static BufferedImage title;
    public static BufferedImage instructions;
    public static BufferedImage gameOver;
    public static BufferedImage gameWon;
    public static BufferedImage door;
    public static BufferedImage coinBlock;
    public static BufferedImage healItem;
    public static BufferedImage kalaxianCrystal;
    public static BufferedImage kalaxianCrystalBlock;
    public static BufferedImage E;
    public static BufferedImage EP;
    public static BufferedImage Pause;
    public static BufferedImage[] Resume;
    public static BufferedImage[] BTitle;
    public static BufferedImage[] Options;
    public static BufferedImage[] Runes;
    public static ImageIcon icon;
    public static BufferedImage[] FireBallLeft;
    public static BufferedImage[] FireBallRight;
    public static BufferedImage[] FireBallUp;
    public static BufferedImage[] FireBallDown;
    public static BufferedImage loading;
    public static BufferedImage spellGUI;
    public static BufferedImage keyItem;
    public static BufferedImage coinItem;
    public static BufferedImage wizardHumanoid;
    public static BufferedImage wizardItem;
    public static BufferedImage[] wizardInstructions;


    public Images() {

        SpriteSheet newsheet = new SpriteSheet(Images.loadImage("/Sheets/SpriteSheet.png"));
        SpriteSheet newworldsheet = new SpriteSheet (Images.loadImage("/Sheets/master-tileset.png"));
        SpriteSheet numsheet = new SpriteSheet(Images.loadImage("/Sheets/numsheet.png"));
        SpriteSheet runesheet = new SpriteSheet(Images.loadImage("/Sheets/runes.png"));
        SpriteSheet FireBallsheet = new SpriteSheet(Images.loadImage("/Sheets/FireBall.png"));
        SpriteSheet FireBallRightsheet = new SpriteSheet(Images.loadImage("/Sheets/FireBallRight.png"));
        SpriteSheet FireBallUpsheet = new SpriteSheet(Images.loadImage("/Sheets/FireBallUp.png"));
        SpriteSheet FireBallDownsheet = new SpriteSheet(Images.loadImage("/Sheets/FireBallDown.png"));
        SpriteSheet MortyEnemyRight = new SpriteSheet(Images.loadImage("/Sheets/zombieMortyR.png"));
        SpriteSheet MortyEnemyFLB = new SpriteSheet(Images.loadImage("/Sheets/zombieMorty.png"));
        SpriteSheet MortyAllyRight = new SpriteSheet(Images.loadImage("/Sheets/wizardMortyR.png"));
        SpriteSheet MortyAllyFLB = new SpriteSheet(Images.loadImage("/Sheets/wizardMorty.png"));
        SpriteSheet RegularMortyRight = new SpriteSheet(Images.loadImage("/Sheets/regularMortyR.png"));
        SpriteSheet RegularMortyFLB = new SpriteSheet(Images.loadImage("/Sheets/regularMorty.png"));


        blocks = new BufferedImage[15];
        blocks2 = new BufferedImage[15];

        player_left = new BufferedImage[4];
        player_right = new BufferedImage[4];
        player_front = new BufferedImage[4];
        player_back = new BufferedImage[4];

        SkelyEnemy_left = new BufferedImage[4];
        SkelyEnemy_right = new BufferedImage[4];
        SkelyEnemy_front = new BufferedImage[4];
        SkelyEnemy_back = new BufferedImage[4];
        
        MortyEnemy_left = new BufferedImage[4];
        MortyEnemy_right = new BufferedImage[4];
        MortyEnemy_front = new BufferedImage[4];
        MortyEnemy_back = new BufferedImage[4];
        
        MortyAlly_left = new BufferedImage[4];
        MortyAlly_right = new BufferedImage[4];
        MortyAlly_front = new BufferedImage[4];
        MortyAlly_back = new BufferedImage[4];

        butstart = new BufferedImage[3];
        enterThePortal = new BufferedImage[3];
        particleSmoke = new BufferedImage[3];
        items = new BufferedImage[3];
        numbers = new BufferedImage[21];
        Resume = new BufferedImage[2];
        BTitle = new BufferedImage[2];
        Options = new BufferedImage[2];
        Runes = new BufferedImage[36];

        FireBallLeft = new BufferedImage[6];
        FireBallRight = new BufferedImage[6];
        FireBallUp = new BufferedImage[6];
        FireBallDown = new BufferedImage[6];
        
        wizardInstructions = new BufferedImage[2];



        try {

            loading = ImageIO.read(getClass().getResourceAsStream("/Sheets/loading.png"));
            spellGUI = ImageIO.read(getClass().getResourceAsStream("/Sheets/SpellGUI.png"));
            keyItem = ImageIO.read(getClass().getResourceAsStream("/Sheets/keyItem.png"));
            coinItem = ImageIO.read(getClass().getResourceAsStream("/Sheets/coinItem.png"));
            coinBlock = ImageIO.read(getClass().getResourceAsStream("/Sheets/coinBlock.png"));
            healItem = ImageIO.read(getClass().getResourceAsStream("/Sheets/Heal_Icon.png"));
            kalaxianCrystal = ImageIO.read(getClass().getResourceAsStream("/Sheets/KalaxianCrystal2.png"));
            kalaxianCrystalBlock = ImageIO.read(getClass().getResourceAsStream("/Sheets/kalaxianCrystalBlock.png"));
            coinItem = ImageIO.read(getClass().getResourceAsStream("/Sheets/coinItem.png"));
            wizardHumanoid = ImageIO.read(getClass().getResourceAsStream("/Sheets/wizardHumanoid.png"));
            wizardItem = ImageIO.read(getClass().getResourceAsStream("/Sheets/wizardItem.png"));

            inventory = ImageIO.read(getClass().getResourceAsStream("/Sheets/guit.png"));
            title = ImageIO.read(getClass().getResourceAsStream("/Sheets/RickAndMortyMenu.png"));
            instructions = ImageIO.read(getClass().getResourceAsStream("/Sheets/instructionsState.png"));
            gameOver = ImageIO.read(getClass().getResourceAsStream("/Sheets/youAreDead.jpg"));
            gameWon = ImageIO.read(getClass().getResourceAsStream("/Sheets/gameWon.jpg"));//TODO
            door = ImageIO.read(getClass().getResourceAsStream("/Sheets/portal.png"));
            E = ImageIO.read(getClass().getResourceAsStream("/Buttons/E.png"));
            EP = ImageIO.read(getClass().getResourceAsStream("/Buttons/EP.png"));
            Pause = ImageIO.read(getClass().getResourceAsStream("/Buttons/Pause.png"));
            Resume[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Resume.png"));
            Resume[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/ResumeP.png"));
            BTitle[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitle.png"));
            BTitle[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitleP.png"));
            Options[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Options.png"));
            Options[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/OptionsP.png"));

            //icon
            icon = new ImageIcon(runesheet.crop(Rwidth*1,Rheight*0,Rwidth,Rheight));

            //fireball left
            FireBallLeft[0]= FireBallsheet.crop(Fwidth*0,Fheight*0,Fwidth,Fheight);
            FireBallLeft[1]= FireBallsheet.crop(Fwidth*1,Fheight*0,Fwidth,Fheight);
            FireBallLeft[2]= FireBallsheet.crop(Fwidth*2,Fheight*0,Fwidth,Fheight);
            FireBallLeft[3]= FireBallsheet.crop(Fwidth*0,Fheight*1,Fwidth,Fheight);
            FireBallLeft[4]= FireBallsheet.crop(Fwidth*1,Fheight*1,Fwidth,Fheight);
            FireBallLeft[5]= FireBallsheet.crop(Fwidth*2,Fheight*1,Fwidth,Fheight);

            //fireball right
            FireBallRight[0]= FireBallRightsheet.crop(Fwidth*0,Fheight*0,Fwidth,Fheight);
            FireBallRight[1]= FireBallRightsheet.crop(Fwidth*1,Fheight*0,Fwidth,Fheight);
            FireBallRight[2]= FireBallRightsheet.crop(Fwidth*2,Fheight*0,Fwidth,Fheight);
            FireBallRight[3]= FireBallRightsheet.crop(Fwidth*0,Fheight*1,Fwidth,Fheight);
            FireBallRight[4]= FireBallRightsheet.crop(Fwidth*1,Fheight*1,Fwidth,Fheight);
            FireBallRight[5]= FireBallRightsheet.crop(Fwidth*2,Fheight*1,Fwidth,Fheight);

            //fireball up
            FireBallUp[0]= FireBallUpsheet.crop(Fheight*1,Fwidth*0,Fheight,Fwidth);
            FireBallUp[1]= FireBallUpsheet.crop(Fheight*1,Fwidth*1,Fheight,Fwidth);
            FireBallUp[2]= FireBallUpsheet.crop(Fheight*1,Fwidth*2,Fheight,Fwidth);
            FireBallUp[3]= FireBallUpsheet.crop(Fheight*0,Fwidth*0,Fheight,Fwidth);
            FireBallUp[4]= FireBallUpsheet.crop(Fheight*0,Fwidth*1,Fheight,Fwidth);
            FireBallUp[5]= FireBallUpsheet.crop(Fheight*0,Fwidth*2,Fheight,Fwidth);

            //fireball down
            FireBallDown[0]= FireBallDownsheet.crop(Fheight*1,Fwidth*0,Fheight,Fwidth);
            FireBallDown[1]= FireBallDownsheet.crop(Fheight*1,Fwidth*1,Fheight,Fwidth);
            FireBallDown[2]= FireBallDownsheet.crop(Fheight*1,Fwidth*2,Fheight,Fwidth);
            FireBallDown[3]= FireBallDownsheet.crop(Fheight*0,Fwidth*0,Fheight,Fwidth);
            FireBallDown[4]= FireBallDownsheet.crop(Fheight*0,Fwidth*1,Fheight,Fwidth);
            FireBallDown[5]= FireBallDownsheet.crop(Fheight*0,Fwidth*2,Fheight,Fwidth);

            Runes[0]= runesheet.crop(Rwidth*0,Rheight*0,Rwidth,Rheight);//Runes
            Runes[1]= runesheet.crop(Rwidth*1,Rheight*0,Rwidth,Rheight);
            Runes[2]= runesheet.crop(Rwidth*2,Rheight*0,Rwidth,Rheight);
            Runes[3]= runesheet.crop(Rwidth*3,Rheight*0,Rwidth,Rheight);
            Runes[4]= runesheet.crop(Rwidth*4,Rheight*0,Rwidth,Rheight);
            Runes[5]= runesheet.crop(Rwidth*5,Rheight*0,Rwidth,Rheight);
            Runes[6]= runesheet.crop(Rwidth*6,Rheight*0,Rwidth,Rheight);
            Runes[7]= runesheet.crop(Rwidth*7,Rheight*0,Rwidth,Rheight);


            blocks[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/Slime.png"));

            butstart[0]= newsheet.crop(11,422,93,34);//normbut
            butstart[1]= newsheet.crop(11,456,93,33);//hoverbut
            butstart[2]= newsheet.crop(11,489,93,32);//clickbut
            
            enterThePortal[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/enterThePortal.png"));
            enterThePortal[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/enterThePortalH.png"));
            enterThePortal[2] = ImageIO.read(getClass().getResourceAsStream("/Buttons/enterThePortalC.png"));

            particleSmoke[0]= newsheet.crop(111,397,18,38);
            particleSmoke[1]= newsheet.crop(129,399,20,35);
            particleSmoke[2]= newsheet.crop(154,400,20,35);

            items[0]= newsheet.crop(114,448,30,24);//log
            
            wizardInstructions[0] = ImageIO.read(getClass().getResource("/Sheets/retrieve.png"));
            wizardInstructions[1] = ImageIO.read(getClass().getResource("/Sheets/continue.png"));

            numbers[1]= numsheet.crop(17,15,17,22);
            numbers[2]= numsheet.crop(64,16,14,19);
            numbers[3]= numsheet.crop(110,16,14,19);
            numbers[4]= numsheet.crop(154,17,15,19);
            numbers[5]= numsheet.crop(19,61,13,20);
            numbers[6]= numsheet.crop(64,61,14,20);
            numbers[7]= numsheet.crop(110,62,14,19);
            numbers[8]= numsheet.crop(156,61,13,20);
            numbers[9]= numsheet.crop(19,107,13,20);
            numbers[10]= numsheet.crop(60,107,24,20);
            numbers[11]= numsheet.crop(107,107,20,19);
            numbers[12]= numsheet.crop(151,107,23,19);
            numbers[13]= numsheet.crop(14,152,23,20);
            numbers[14]= numsheet.crop(60,152,23,20);
            numbers[15]= numsheet.crop(105,153,24,20);
            numbers[16]= numsheet.crop(151,153,23,20);
            numbers[17]= numsheet.crop(14,198,24,20);
            numbers[18]= numsheet.crop(60,198,23,20);
            numbers[19]= numsheet.crop(106,198,23,21);
            numbers[20]= numsheet.crop(149,198,28,20);


            //block images,array index is equal to block id
            blocks[1] = newsheet.crop(0,324,Bwidth,Bheight);//grass
            blocks[2] = newsheet.crop(67,260,Bwidth,Bheight);//dirt
            blocks[3] = newsheet.crop(67,324,Bwidth,Bheight);//dirtrock
            blocks[4] = newsheet.crop(0,0,Bwidth,Bheight);//uppperleft
            blocks[5] = newsheet.crop(67,0,Bwidth,Bheight);//upperright
            blocks[6] = newsheet.crop(67,65,Bwidth,Bheight);//lowerleft
            blocks[7] = newsheet.crop(0,65,Bwidth,Bheight);//lowerright
            blocks[8] = newsheet.crop(0,195,Bwidth,Bheight);//leftwall
            blocks[9] = newsheet.crop(67,195,Bwidth,Bheight);//rightwall
            blocks[10] = newsheet.crop(0,130,Bwidth,Bheight);//topwall
            blocks[11] = newsheet.crop(67,130,Bwidth,Bheight);//lowerwall
            blocks[12] = newsheet.crop(0,260,Bwidth,Bheight);//mossyrock
            blocks[13] = newsheet.crop(176,0,Bwidth,Bheight*2);//tree
            blocks[14] = newsheet.crop(174,410,78,74);//rock
            
            blocks2[0] = newworldsheet.crop(0,0,64,64);//stonewall
            blocks2[1] = newworldsheet.crop(0,64,64,64);//sand
            blocks2[2] = newworldsheet.crop(0,128,64,64);//grass
            blocks2[3] = newworldsheet.crop(0,192,64,64);//dirt
            blocks2[4] = newworldsheet.crop(0,256,64,64);//brickwall
            blocks2[5] = newworldsheet.crop(128,256,64,64);//brickfloor


            //player anim
            player_front[0]=RegularMortyFLB.crop(25,54,82,110);
            player_front[1]=RegularMortyFLB.crop(154,50,82,116);
            player_front[2]=RegularMortyFLB.crop(283,52,82,110);
            player_front[3]=RegularMortyFLB.crop(413,51,82,116);

            player_left[0]=RegularMortyFLB.crop(25,220,80,111);
            player_left[1]=RegularMortyFLB.crop(153,220,81,111);
            player_left[2]=RegularMortyFLB.crop(282,220,81,111);
            player_left[3]=RegularMortyFLB.crop(413,221,78,111);

            player_right[3]=RegularMortyRight.crop(23,51,80,111);
            player_right[0]=RegularMortyRight.crop(152,51,81,110);
            player_right[1]=RegularMortyRight.crop(281,51,80,111);
            player_right[2]=RegularMortyRight.crop(410,51,80,110);

            player_back[0]=RegularMortyFLB.crop(26,386,83,111);
            player_back[1]=RegularMortyFLB.crop(155,384,82,114);
            player_back[2]=RegularMortyFLB.crop(285,386,82,111);
            player_back[3]=RegularMortyFLB.crop(415,384,80,112);

            //Skely enemy anim
            SkelyEnemy_front[0]=newsheet.crop(132,131+130,width,height);
            SkelyEnemy_front[1]=newsheet.crop(164,131+130,width,height);
            SkelyEnemy_front[2]=newsheet.crop(196,131+130,width,height);
            SkelyEnemy_front[3]=newsheet.crop(228,131+130,28,height);

            SkelyEnemy_left[0]=newsheet.crop(132,163+130,width,height);
            SkelyEnemy_left[1]=newsheet.crop(164,163+130,width,height);
            SkelyEnemy_left[2]=newsheet.crop(196,163+130,width,height);
            SkelyEnemy_left[3]=newsheet.crop(228,163+130,28,height);

            SkelyEnemy_right[0]=newsheet.crop(132,195+130,width,height);
            SkelyEnemy_right[1]=newsheet.crop(164,195+130,width,height);
            SkelyEnemy_right[2]=newsheet.crop(196,195+130,width,height);
            SkelyEnemy_right[3]=newsheet.crop(228,195+130,28,height);

            SkelyEnemy_back[0]=newsheet.crop(132,227+130,width,height);
            SkelyEnemy_back[1]=newsheet.crop(164,227+130,width,height);
            SkelyEnemy_back[2]=newsheet.crop(196,227+130,width,height);
            SkelyEnemy_back[3]=newsheet.crop(228,227+130,28,height);
            
            //MortyEnemy anim
            MortyEnemy_front[0]=MortyEnemyFLB.crop(14,50,82,111);
            MortyEnemy_front[1]=MortyEnemyFLB.crop(139,46,84,115);
            MortyEnemy_front[2]=MortyEnemyFLB.crop(274,49,82,112);
            MortyEnemy_front[3]=MortyEnemyFLB.crop(409,50,81,112);

            MortyEnemy_left[0]=MortyEnemyFLB.crop(18,216,85,113);
            MortyEnemy_left[1]=MortyEnemyFLB.crop(158,216,79,115);
            MortyEnemy_left[2]=MortyEnemyFLB.crop(278,216,84,113);
            MortyEnemy_left[3]=MortyEnemyFLB.crop(403,216,83,114);

            MortyEnemy_right[0]=MortyEnemyRight.crop(397,24,83,112);
            MortyEnemy_right[1]=MortyEnemyRight.crop(262,24,80,114);
      		MortyEnemy_right[2]=MortyEnemyRight.crop(136,24,85,112);
            MortyEnemy_right[3]=MortyEnemyRight.crop(13,24,84,114);

            MortyEnemy_back[0]=MortyEnemyFLB.crop(23,385,82,111);
            MortyEnemy_back[1]=MortyEnemyFLB.crop(155,383,86,114);
            MortyEnemy_back[2]=MortyEnemyFLB.crop(281,385,84,111);
            MortyEnemy_back[3]=MortyEnemyFLB.crop(405,383,83,112);

            //MortyAlly anim
            MortyAlly_front[0]=MortyAllyFLB.crop(18,37,93,115);
            MortyAlly_front[1]=MortyAllyFLB.crop(150,33,91,120);
            MortyAlly_front[2]=MortyAllyFLB.crop(277,36,93,116);
            MortyAlly_front[3]=MortyAllyFLB.crop(411,35,87,119);

            MortyAlly_left[0]=MortyAllyFLB.crop(24,204,82,116);
            MortyAlly_left[1]=MortyAllyFLB.crop(152,204,82,116);
            MortyAlly_left[2]=MortyAllyFLB.crop(282,204,83,116);
            MortyAlly_left[3]=MortyAllyFLB.crop(412,204,84,116);

            MortyAlly_right[3]=MortyAllyRight.crop(8,50,81,116);
            MortyAlly_right[0]=MortyAllyRight.crop(138,50,81,115);
            MortyAlly_right[1]=MortyAllyRight.crop(268,50,81,116);
            MortyAlly_right[2]=MortyAllyRight.crop(398,50,79,115);

            MortyAlly_back[0]=MortyAllyFLB.crop(25,373,84,111);
            MortyAlly_back[1]=MortyAllyFLB.crop(155,375,83,111);
            MortyAlly_back[2]=MortyAllyFLB.crop(284,373,83,112);
            MortyAlly_back[3]=MortyAllyFLB.crop(414,372,81,112);

            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }



}