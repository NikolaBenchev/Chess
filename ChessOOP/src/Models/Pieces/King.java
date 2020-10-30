/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Pieces;

import Interfaces.IPiece;
import Models.BaseModels.Piece;
import Models.Player;
import UI.PromotionScreen;
import java.awt.Point;
import java.util.ArrayList;
import Models.enums.*;

/**
 *
 * @author Nikola
 */
public class King extends Piece{
    
    public King(pieceColor color, Point position) {
        super(color, position, pieceType.King);
    }
    
    public ArrayList<Point> getPossibleMoves(IPiece[][] board, Player enemyPlayer)
    {
        ArrayList<Point> possibleMoves = new ArrayList<Point>();
        
        int x = this.getPosition().x;
        int y = this.getPosition().y;
        
        for(int i = -1; i < 2; i++)
        {
            for(int j = -1; j < 2; j++)
            {
                if(y + i >= 0 && y + i < 8 && x + j >= 0 && x + j < 8)
                {
                    if(board[y + i][x + j] == null)
                    {
                        possibleMoves.add(new Point(x + j, y + i));
                    }
                    else if(i != 0 || j != 0)
                    {
                        boolean isSameColor = board[y + i][x + j].getColor().equals(this.getColor());
                        if(!isSameColor)
                        {
                            possibleMoves.add(new Point(x + j, y + i));
                        }
                    }
                }
                    
            }
        }
        
        System.out.println(this.getHasMoved());
        
        if(!this.getHasMoved())
        {
            // Long Castle
            if(!board[y][0].getHasMoved())
            {
                boolean isPossible = true;
                for(int i = x - 1; i > 0; i--)
                {
                    if(board[y][i] != null)
                        isPossible = false;
                }
                
                if(isPossible)
                    possibleMoves.add(new Point(x - 2, y));
            }
                
            // Short Castle
            if(!board[y][7].getHasMoved())
            {
                boolean isPossible = true;
                for(int i = x + 1; i < 7; i++)
                {
                    if(board[y][i] != null)
                        isPossible = false;
                }
                if(isPossible)
                    possibleMoves.add(new Point(x + 2, y));
            }
            
        }
        
        ArrayList<IPiece> enemyPieces;
        
        if(enemyPlayer == null)
        {
            enemyPieces = new ArrayList<IPiece>();
        }else
        {
            enemyPieces = enemyPlayer.getMovablePieces(board);
        }
        
        for(int i = 0; i < enemyPieces.size(); i++)
        {
            ArrayList<Point> enemyMoves = enemyPieces.get(i).getPossibleMoves(board);
            
            for(int j = 0; j < possibleMoves.size(); j++)
            {
                if(enemyPieces.get(i).getType() == pieceType.Pawn)
                {
                    Point enemyPiecePos = enemyPieces.get(i).getPosition();
                    Pawn pawnCast = (Pawn)enemyPieces.get(i);
                    
                    int dir = pawnCast.getDir();
                    
                    for(int d = -1; d < 2; d += 2)
                    {
                        if(enemyPiecePos.x + d == possibleMoves.get(j).x && enemyPiecePos.y + dir == possibleMoves.get(j).y)
                        {
                            possibleMoves.remove(possibleMoves.get(j));
                            break;
                        }
                    }
                }else
                {
                    for(int k = 0; k < enemyMoves.size(); k++)
                    {
                        Point currentEnemyMove = enemyMoves.get(k);
                        if(possibleMoves.get(j).x == currentEnemyMove.x && possibleMoves.get(j).y == currentEnemyMove.y)
                        {
                            possibleMoves.remove(possibleMoves.get(j));
                            break;
                        }
                    }
                }
            }
        }
        
        return possibleMoves;
    }
}
