
package wormgame.gui;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import wormgame.game.WormGame;
import wormgame.domain.*;

public class DrawingBoard extends JPanel implements Updatable {
    
    private WormGame game;
    private int pieceLength;
    
    public DrawingBoard(WormGame game, int pieceLength) {
        this.game = game;
        this.pieceLength = pieceLength;
    }
    
    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        
        gr.setColor(Color.BLACK);
        for(Piece piece : game.getWorm().getPieces()) {
            gr.fill3DRect(piece.getX() * pieceLength, piece.getY() * pieceLength, pieceLength, pieceLength, true);
        }
        
        gr.setColor(Color.RED);
        Piece apple = game.getApple();
        gr.fillOval(apple.getX() * pieceLength, apple.getY() * pieceLength, pieceLength, pieceLength);
    }

    @Override
    public void update() {
        this.repaint();
    }
    
    
}

