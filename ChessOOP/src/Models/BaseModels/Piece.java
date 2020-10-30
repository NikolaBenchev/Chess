/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.BaseModels;

import Interfaces.IPiece;
import Models.enums.*;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Nikola
 */
public abstract class Piece implements IPiece{
    private Point position;
    private pieceColor color;
    private boolean hasMoved = false;
    private pieceType type;
    
    public Piece(pieceColor color, Point position, pieceType type)
    {
        this.setColor(color);
        this.setPosition(position);
        this.setType(type);
    }
    
    public Point getPosition()
    {
        return position;
    }
    
    public void setPosition(Point value)
            throws RuntimeException
    {
        if (value == null)
        {
            throw new RuntimeException();
        }
        this.position = value;
    }
    
    public pieceColor getColor()
    {
        return this.color;
    }
    
    public void setColor(pieceColor value)
    {
        this.color = value;
    }
    
    public boolean getHasMoved()
    {
        return this.hasMoved;
    }
    
    public void setHasMoved()
    {
        this.hasMoved = true;
    }
    
    public ArrayList<Point> getPossibleMoves(IPiece[][] board)
    {
        ArrayList<Point> possibleMoves = new ArrayList<Point>();
        
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        
        return possibleMoves;
    }
    
    public pieceType getType()
    {
        return this.type;
    }
    
    public void setType(pieceType value)
    {
        this.type = value;
    }
    
    public ArrayList<Point> getBlockingMoves(IPiece attackingPiece, IPiece[][] board, Point kingPos)
    {
        ArrayList<Point> blockingMoves = new ArrayList<Point>();
        if(attackingPiece.getType() == pieceType.Knight || attackingPiece.getType() == pieceType.Pawn)
        {
            Point enemyPos = attackingPiece.getPosition();
            
            ArrayList<Point> possibleMoves = this.getPossibleMoves(board);
            
            for(int i = 0; i < possibleMoves.size(); i++)
            {
                if(possibleMoves.get(i).x == enemyPos.x && possibleMoves.get(i).y == enemyPos.y)
                {
                    blockingMoves.add(enemyPos);
                    break;
                }
            }
            
            return blockingMoves;
        }
        
        // Check the type and where the piece is in terms of king position
        // Get all the possible moves of the attacking piece towards the king
        // Check if the current piece can go on one of those positions
        
        ArrayList<Point> possibleMoves = this.getPossibleMoves(board);
        
        Point enemyPos = attackingPiece.getPosition();
        
        int dirX = (enemyPos.x < kingPos.x ? 1 : enemyPos.x == kingPos.x ? 0 : -1);
        int dirY = (enemyPos.y < kingPos.y ? 1 : enemyPos.y == kingPos.y ? 0 : -1);
        
        System.out.println(possibleMoves.size());
//        System.out.println(dirX + " " + dirY);
        
        while(enemyPos.x != kingPos.x && enemyPos.y != kingPos.y)
        {
            for(int i = 0; i < possibleMoves.size(); i++)
            {
                if(enemyPos.x == possibleMoves.get(i).x && enemyPos.y == possibleMoves.get(i).y)
                {
                    System.out.println(enemyPos.x + "" + enemyPos.y);
                    System.out.println(possibleMoves.get(i).x + "" + possibleMoves.get(i).y);
                    blockingMoves.add(possibleMoves.get(i));
                    break;
                }
            }
            
            enemyPos.x += dirX;
            enemyPos.y += dirY;
        }
        
        return blockingMoves;
    }
}
