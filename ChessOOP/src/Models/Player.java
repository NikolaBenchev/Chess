/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Interfaces.IPiece;
import Models.BaseModels.Piece;
import java.awt.Point;
import java.util.ArrayList;
import Models.enums.*;

/**
 *
 * @author Nikola
 */
public class Player {
    private ArrayList<IPiece> pieces = new ArrayList<IPiece>();
    private pieceColor color = null;
    private Point kingPosition;
    
    private int score;
    
    public Player(pieceColor color)
    {
        this.setColor(color);
        
        if(color == pieceColor.Black)
            this.setKingPosition(new Point(4, 0));
        else
            this.setKingPosition(new Point(4, 7));
        
        score = 0;
    }
    
    public void upScore(int value)
    {
        this.score += value;
    }
    
    public int getScore()
    {
        return this.score;
    }
    
    public Point getKingPosition()
    {
        return this.kingPosition;
    }
    
    public void setKingPosition(Point value)
    {
        this.kingPosition = value;
    }
    
    public void setColor(pieceColor value)
    {
        this.color = value;
    }
    
    public pieceColor getColor()
    {
        return this.color;
    }
    
    public ArrayList<IPiece> getPieces()
    {
        return this.pieces;
    }
    
    public void addPiece(IPiece piece)
    {
        this.pieces.add(piece);
    }
    
    public ArrayList<IPiece> getMovablePieces(IPiece[][] board)
    {
        ArrayList<IPiece> movablePieces = new ArrayList<IPiece>();
        ArrayList<IPiece> piece = this.getPieces();
        
        for(int i = 0; i < pieces.size(); i++)
        {
            int numberOfMoves = piece.get(i).getPossibleMoves(board).size();
            if(numberOfMoves > 0)
                movablePieces.add(piece.get(i));
        }
        
        return movablePieces;
    }
    
    public boolean isChecked(IPiece attackingPiece, IPiece[][] board)
    {
        ArrayList<Point> attackedPosition = attackingPiece.getPossibleMoves(board);
        for(int i = 0; i < attackedPosition.size(); i++)
        {
            if(attackedPosition.get(i).x == this.getKingPosition().x &&
                attackedPosition.get(i).y == this.getKingPosition().y)
            {
                return true;
            }
        }
        return false;
    }
}
