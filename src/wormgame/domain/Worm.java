
package wormgame.domain;

import wormgame.Direction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import wormgame.game.WormGame;

public class Worm {
    
    private Direction direction;
    private List<Piece> pieces;
    
    public Worm(int originalX, int originalY, Direction originalDirection) {
        pieces = new ArrayList<Piece>();
        pieces.add(new Piece(originalX, originalY));
        direction = originalDirection; 
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public int getLength() {
        return getPieces().size();
    }
    
    public List<Piece> getPieces() {
        List<Piece> pieces = new LinkedList<Piece>(this.pieces);
        Iterator<Piece> iterator = pieces.iterator();
        
        while(iterator.hasNext()) {
            Piece piece = iterator.next();
            if(piece.getX() == -5)
                iterator.remove();
        }
        
        return this.pieces;
    }    
    
    public void move() {
        if(pieces.size() < 3)
            grow();
        
        if(pieces.contains(new Piece(-5, 0)))
            pieces.remove(new Piece(-5, 0));
        else
            pieces.remove(pieces.get(0));
        
        Piece head = null;
        for(Piece piece : pieces) {
            if(piece.getX() != -5)
                head = piece;
        }
        
        int newX = head.getX(), newY = head.getY();
        switch(direction) {
            case UP: newY--; break;
            case DOWN: newY++; break;
            case LEFT: newX--; break;
            case RIGHT: newX++;
        }
        
        pieces.add(new Piece(newX, newY));
    }
    
    public void grow() {
        pieces.add(new Piece(-5, 0));
    }
    
    public boolean runsInto(Piece piece) {
        System.out.println(piece);
        System.out.println(pieces);
        for(Piece wormPiece : pieces) {
            if(wormPiece.equals(piece))
                return true;
        }
        return false;
    }
    
    public boolean runsIntoItself() {
        for(Piece piece : pieces) {
            List<Piece> copy = new LinkedList<Piece>(pieces);
            copy.remove(piece);
            
            if(copy.contains(piece)) {
                return true;
            }
        }
        return false;
    }
       
}
