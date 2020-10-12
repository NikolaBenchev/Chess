/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Models.Board;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Nikola
 */
public interface IPiece {
    Point position = null;
    
    public Point getPosition();
    public void setPosition(Point value);
    
    public ArrayList<Point> getPossibleMoves(Board board);
    
}
