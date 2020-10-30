/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Board;
import Models.enums.*;
import UI.PromotionScreen;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Nikola
 */
public interface IPiece {
    
    public Point getPosition();
    public void setPosition(Point value);
    
    public pieceColor getColor();
    public void setColor(pieceColor value);
    
    public ArrayList<Point> getPossibleMoves(IPiece[][] board);
    public ArrayList<Point> getBlockingMoves(IPiece attackingPiece, IPiece[][] board, Point kingPos);
    
    public boolean getHasMoved();
    public void setHasMoved();
    
    public pieceType getType();
    public void setType(pieceType value);
    
//    public void Promote(PromotionScreen promotionWindow);
}
