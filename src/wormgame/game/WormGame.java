package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.gui.Updatable;
import wormgame.domain.*;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;

    public WormGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;

        addActionListener(this);
        setInitialDelay(2000);
        
        worm = new Worm(width/2, height/2, Direction.DOWN);
        
        generateApple();
    }
    
    public void generateApple() {
        int randomX = 0, randomY = 0;
        do {
            randomX = new Random().nextInt(width);
            randomY = new Random().nextInt(height);
        }
        while(worm.getPieces().contains(new Piece(randomX, randomY)));
        
        setApple(new Apple(randomX, randomY));
    }
    
    public Worm getWorm() {
        return worm;
    }
    
    public void setWorm(Worm worm) {
        this.worm = worm;
    }
    
    public Apple getApple() {
        return apple;
    }
    
    public void setApple(Apple apple) {
        this.apple = apple;
    }
    
    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        worm.move();
        
        if(worm.runsInto(apple)) {
            worm.grow();
            generateApple();
        }
        
        
        if(worm.runsIntoItself()) {
            continues = false;
        }
        
        for(Piece piece : worm.getPieces()) {
            if(piece.getX() == -1 || piece.getX() == width || piece.getY() == -1 || piece.getY() == height)
                continues = false;
        }
        updatable.update();
        
        this.setDelay(1000 / worm.getLength());
    
    }

}
