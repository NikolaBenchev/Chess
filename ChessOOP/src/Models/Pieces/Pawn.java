/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Pieces;

import Interfaces.IPiece;
import Models.BaseModels.Piece;
import Models.enums.pieceType;
import UI.PromotionScreen;
import java.awt.Point;
import java.util.ArrayList;
import Models.enums.*;

/**
 *
 * @author Nikola
 */

public class Pawn extends Piece{
    private int dir = -1;

    public Pawn(pieceColor color, Point position) {
        super(color, position, pieceType.Pawn);
        if(this.getColor() == pieceColor.Black)
            dir = 1;
    }
    
    public ArrayList<Point> getPossibleMoves(IPiece[][] board) {
        ArrayList<Point> possibleMoves = new ArrayList<Point>();
       
        Point position = this.getPosition();
        
        int x = position.x;
        int y = position.y;
        if(y + dir >= 0 && y + dir < 8)
        {
            if(board[y + dir][x] == null)
            {
                possibleMoves.add(new Point(x, y + dir));
            }
        }
        
        if(y + dir * 2 >= 0 && y + dir * 2 < 8)
        {
            if(board[y + dir * 2][x] == null && possibleMoves.size() > 0)
            {
                if(!this.getHasMoved())
                {
                    possibleMoves.add(new Point(x, y + dir * 2));
                }
            }
        }
        
        // Taking
        boolean isSameColor;
        
        if(x + 1 < 8 && y + dir < 8 && y + dir >= 0)
        {
            if(board[y + dir][x + 1] != null)
            {
                isSameColor = board[y + dir][x + 1].getColor().equals(this.getColor());
                if(!isSameColor)
                {
                    possibleMoves.add(new Point(x + 1, y + dir));
                }
            }
        }
        
        if(x - 1 >= 0 && y + dir < 8 && y + dir >= 0)
        {
            if(board[y + dir][x - 1] != null)
            {
                isSameColor = board[y + dir][x - 1].getColor().equals(this.getColor());
                if(!isSameColor)
                {
                    possibleMoves.add(new Point(x - 1, y + dir));
                }
            }
        }
        
        // TODO add ampasan
        
        return possibleMoves;
    }
    
    public boolean canPromote()
    {
        pieceColor color = this.getColor();
        int y = this.getPosition().y;
        
        return (this.dir == 1 && y == 7 || this.dir == -1 && y == 0);
    }
    
    public int getDir()
    {
        return this.dir;
    }
}
