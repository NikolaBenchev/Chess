/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Pieces;

import Interfaces.IPiece;
import Models.BaseModels.Piece;
import UI.PromotionScreen;
import java.awt.Point;
import java.util.ArrayList;
import Models.enums.*;

/**
 *
 * @author Nikola
 */
public class Bishop extends Piece{
    
    public Bishop(pieceColor color, Point position) {
        super(color, position, pieceType.Bishop);
    }
    
    
    public ArrayList<Point> getPossibleMoves(IPiece[][] board)
    {
        ArrayList<Point> possibleMoves = new ArrayList<Point>();
        
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        
        ArrayList<Point> topLeft = new ArrayList<Point>();
        ArrayList<Point> topRight = new ArrayList<Point>();
        ArrayList<Point> bottomLeft = new ArrayList<Point>();
        ArrayList<Point> bottomRight = new ArrayList<Point>();
        
        Point topCorner = new Point(0, 0);
        Point bottomCorner = new Point(0, 0);
        
        if(x > y)
        {
            topCorner.x = x - y;
            topCorner.y = 0;
        }else
        {   
            topCorner.x = 0;
            topCorner.y = y - x;
        }
        
        if(x > 7 - y)
        {
            bottomCorner.x = x - 7 + y;
            bottomCorner.y = 7;
        }else
        {
            bottomCorner.x = 0;
            bottomCorner.y = y + x;
        }
        
        while(topCorner.x < 8 && topCorner.y < 8)
        {
            if(topCorner.x < x)
            {
                if(board[topCorner.y][topCorner.x] == null)
                {
                    topLeft.add(new Point(topCorner.x, topCorner.y));
                }
                else
                {
                    topLeft.clear();
                    
                    boolean isSameColor = board[topCorner.y][topCorner.x].getColor().equals(this.getColor());
                    
                    if(!isSameColor)
                    {
                        topLeft.add(new Point(topCorner.x, topCorner.y));
                    }
                }
                
            }
            else if (topCorner.x != x)
            {
                if(board[topCorner.y][topCorner.x] == null)
                {
                    bottomRight.add(new Point(topCorner.x, topCorner.y));
                }
                else
                {
                    boolean isSameColor = board[topCorner.y][topCorner.x].getColor().equals(this.getColor());
                    
                    if(!isSameColor)
                    {
                    bottomRight.add(new Point(topCorner.x, topCorner.y));
                    }
                    
                    break;
                }   
            }
            
            topCorner.x += 1;
            topCorner.y += 1;
        }
        
        while(bottomCorner.x < 8 && bottomCorner.y >= 0)
        {
            if(bottomCorner.x < x)
            {
                if(board[bottomCorner.y][bottomCorner.x] == null)
                {
                    bottomLeft.add(new Point(bottomCorner.x, bottomCorner.y));
                }
                else
                {
                    bottomLeft.clear();
                    
                    boolean isSameColor = board[bottomCorner.y][bottomCorner.x].getColor().equals(this.getColor());
                    
                    if(!isSameColor)
                    {
                        bottomLeft.add(new Point(bottomCorner.x, bottomCorner.y));
                    }
                }
                
            }
            else if (bottomCorner.x != x)
            {
                if(board[bottomCorner.y][bottomCorner.x] == null)
                {
                    topRight.add(new Point(bottomCorner.x, bottomCorner.y));
                }
                else
                {
                    boolean isSameColor = board[bottomCorner.y][bottomCorner.x].getColor().equals(this.getColor());
                    
                    if(!isSameColor)
                    {
                    topRight.add(new Point(bottomCorner.x, bottomCorner.y));
                    }
                    
                    break;
                }   
            }
            bottomCorner.x += 1;
            bottomCorner.y -= 1;
        }
        
        possibleMoves.addAll(topLeft);
        possibleMoves.addAll(bottomLeft);
        possibleMoves.addAll(topRight);
        possibleMoves.addAll(bottomRight);
        
        return possibleMoves;
    }

    
//    @Override
//    public void Promote(PromotionScreen promotionWindow) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
