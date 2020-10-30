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
public class Queen extends Piece{
    
    public Queen(pieceColor color, Point position) {
        super(color, position, pieceType.Queen);
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
