/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Pieces;

import Interfaces.IPiece;
import Models.BaseModels.Piece;
import Models.enums.*;
import UI.PromotionScreen;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Nikola
 */

public class Rook extends Piece{

    public Rook(pieceColor color, Point position) {
        super(color, position, pieceType.Rook);
    }

    @Override
    public ArrayList<Point> getPossibleMoves(IPiece[][] board) {
        ArrayList<Point> possibleMoves = new ArrayList<Point>();
        
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        
        ArrayList<Point> above = new ArrayList<Point>();
        ArrayList<Point> below = new ArrayList<Point>();
        ArrayList<Point> left = new ArrayList<Point>();
        ArrayList<Point> right = new ArrayList<Point>();
        
        // Y
        
        for(int i = 0; i < 8; i++)
        {
            if(board[i][x] == null)
            {
                if(i < y)
                    above.add(new Point(x, i));
                else if(i > y)
                    below.add(new Point(x, i));
            }else
            {
                boolean isSameColor = board[i][x].getColor().equals(this.getColor());
                if(!isSameColor)
                {
                    
                    if(i < y)
                    {
                        above.clear();
                        above.add(new Point(x, i));
                    }
                    else if(i > y)
                    {
                        below.add(new Point(x, i));
                        break;
                    }
                        
                }else
                {
                    if(i < y)
                        above.clear();
                    else if(i > y)
                        break;
                }
            }
        }
        
        //X
        
        for(int i = 0; i < 8; i++)
        {
            if(board[y][i] == null)
            {
                if(i < x)
                    left.add(new Point(i, y));
                else if(i > x)
                    right.add(new Point(i, y));
            }else
            {
                boolean isSameColor = board[y][i].getColor().equals(this.getColor());
                if(!isSameColor)
                {
                    if(i < x)
                    {
                        left.clear();
                        left.add(new Point(i, y));
                    }
                    else if(i > x)
                    {
                        right.add(new Point(i, y));
                        break;
                    }   
                }else
                {
                    if(i < x)
                        left.clear();
                    else if(i > x)
                        break;
                }
            }
        }
        
        possibleMoves.addAll(above);
        possibleMoves.addAll(below);
        possibleMoves.addAll(left);
        possibleMoves.addAll(right);
        
        return possibleMoves;
    }

//    @Override
//    public void Promote(PromotionScreen promotionWindow) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
