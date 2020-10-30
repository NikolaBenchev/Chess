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
public class Knight extends Piece{

    public Knight(pieceColor color, Point position) {
        super(color, position, pieceType.Knight);
    }

    @Override
    public ArrayList<Point> getPossibleMoves(IPiece[][] board) {
        ArrayList<Point> possibleMoves = new ArrayList<Point>();
        /*
             2 -1
             2  1
            -2 -1
            -2  1
             1 -2
             1  2
            -1 -2
            -1  2
        */
        
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        
        int values[] = {-1, 1, -2, 2};
        
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                int difX = values[i];
                int difYIndex = j;
                if(i < 2)
                {
                    difYIndex += 2;
                }
                
                int difY = values[difYIndex];
                
                if(y + difY >= 0 && y + difY < 8 && x + difX >= 0 && x + difX < 8)
                {
                    if(board[y + difY][x + difX] == null)
                    {   
                        possibleMoves.add(new Point(x + difX, y + difY));
                    }
                    else
                    {
                        boolean isSameColor = board[y + difY][x + difX].getColor().equals(this.getColor());
                        if(!isSameColor)
                        {
                            possibleMoves.add(new Point(x + difX, y + difY));
                        }
                    }
                }
            }
        }
        
        
        return possibleMoves;
    }
}
