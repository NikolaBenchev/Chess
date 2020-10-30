package Models;

import Models.enums.pieceType;
import Interfaces.IPiece;
import UI.Cell;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *
 * @author Nikola
 *
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Interfaces.IPiece;
import Models.PieceDrawing;
import UI.Cell;
import Models.Pieces.*;
import Models.*;
import UI.PromotionScreen;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import Models.enums.*;
import java.awt.Dimension;

/**
 *
 * @author Nikola Benchev
 */

public class Board extends JFrame{
    IPiece[][] board = new IPiece[8][8];
    Cell[][] cell = new Cell[8][8];
    
    
    Player currentPlayer;
    Player enemyPlayer;
    
    PromotionScreen promotionWindow = null;
    
    MouseAdapter onClick = new MouseAdapter(){
            
        public PieceDrawing movingPiece;
        public Point mousePos;

        public ArrayList<Point> possibleMoves = new ArrayList<Point>();

        public int click = 0;

        @Override
        public void mousePressed(MouseEvent ev) {
            reDrawCells();
            
            ArrayList<IPiece> attackingPiece = colorIfChecked(currentPlayer, enemyPlayer);
            boolean isCurrentPlayerChecked = (attackingPiece.size() != 0);

            System.out.println("attack: " + attackingPiece.size());
            System.out.println("isChecked: " + isCurrentPlayerChecked);
            
            mousePos = ev.getPoint();

            mousePos = getMousePos(mousePos);
            
            int x = mousePos.x;
            int y = mousePos.y;
            
            // Check if a piece is currently selected

            if(movingPiece == null)
            {
                possibleMoves = setMovingPiece(mousePos, isCurrentPlayerChecked, attackingPiece);
                if(possibleMoves != null)
                    movingPiece = cell[y][x].getPiece();
            }else
            {
                setMovingPosition(mousePos, possibleMoves, movingPiece);
                
                movingPiece = null;
                possibleMoves = new ArrayList<Point>();
            }
        }
    };
    
    public void run()
    {
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        GridLayout grid = new GridLayout(8, 8);
        this.setLayout(grid);
        
        currentPlayer = new Player(pieceColor.White);
        enemyPlayer = new Player(pieceColor.Black);
        
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                pieceType type = null; 
                
                Color cellColor = (i % 2 == j % 2 ? Color.white : Color.gray);
                
                pieceColor color = (i < 2 ? pieceColor.Black : pieceColor.White);

                cell[i][j] = new Cell(cellColor);
                board[i][j] = null;
                if(i < 2 || i > 5)
                {    
                    switch(j)
                    {
                        case 0:
                        case 7:
                            type = pieceType.values()[0];
                            board[i][j] = new Rook(color, new Point(j, i));
                            break;
                        case 1:
                        case 6:
                            type = pieceType.values()[1];
                            board[i][j] = new Knight(color, new Point(j, i));
                            break;
                        case 2:
                        case 5:
                            type = pieceType.values()[2];
                            board[i][j] = new Bishop(color, new Point(j, i));
                            break;
                        case 3:
                            type = pieceType.values()[3];
                            board[i][j] = new Queen(color, new Point(j, i));
                            break;
                        case 4:
                            type = pieceType.values()[4];
                            board[i][j] = new King(color, new Point(j, i));
                            break;
                        default:
                            break;
                    }
                    
                    if(i == 1 || i == 6)
                    {
                        type = pieceType.Pawn;
                        board[i][j] = new Pawn(color, new Point(j, i));
                    }                    
                    
                    if(i > 2)
                        currentPlayer.addPiece(board[i][j]);
                    else
                        enemyPlayer.addPiece(board[i][j]);
                    cell[i][j].addPiece(new PieceDrawing(type, color, new Point(j, i)));
                    
                }                
                this.add(cell[i][j]);
            }
        }
        
        this.setVisible(true);
        this.setSize(420, 440);
        this.setLocationRelativeTo(null);
        
        promotionWindow = new PromotionScreen();
        
        this.addMouseListener(onClick);
    };
   
    public void reDrawCells()
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                Color bgColor = cell[i][j].getColor();
                cell[i][j].setBackground(bgColor);
            }
        }
    }
    
    public void colorPossibleMoves(Point mousePos, ArrayList<Point> possibleMoves)
    {
        int x = mousePos.x;
        int y = mousePos.y;
        
        for(int i = 0; i < possibleMoves.size(); i++)
        {
            int moveX = possibleMoves.get(i).x;
            int moveY = possibleMoves.get(i).y;

            cell[moveY][moveX].setBackground(Color.green);
        }

        cell[y][x].setBackground(Color.green);
    }
    
    public ArrayList<Point> setMovingPiece(Point mousePos, boolean isCurrentPlayerChecked, ArrayList<IPiece> attackingPiece)
    {
        ArrayList<IPiece> movablePieces;
        ArrayList<Point> possibleMoves = new ArrayList<Point>();
        
        int x = mousePos.x;
        int y = mousePos.y;

        if(board[y][x] == null)
            return null;

        if(board[y][x].getColor() != currentPlayer.getColor())
            return null;
        
        movablePieces = currentPlayer.getMovablePieces(getBoard());
        
        if(isCurrentPlayerChecked)
        {
            if(attackingPiece.size() >= 2)
            {
                if(board[y][x].getType() == pieceType.King)
                {
                    King kingCast = (King)board[y][x];
                    
                    possibleMoves = kingCast.getPossibleMoves(getBoard(), enemyPlayer);
                }else
                {
                    possibleMoves = new ArrayList<Point>();
                }
            }else
            {
                if(board[y][x].getType() == pieceType.King)
                {
                    King kingCast = (King)board[y][x];
                    
                    possibleMoves = kingCast.getPossibleMoves(getBoard(), enemyPlayer);
                }else
                {
                    possibleMoves = board[y][x].getBlockingMoves(attackingPiece.get(0), getBoard(), currentPlayer.getKingPosition());
                }
            }
            return possibleMoves;
        }

        if(board[y][x].getType() == pieceType.King)
        {
            King kingCast = (King)board[y][x];
            possibleMoves = kingCast.getPossibleMoves(getBoard(), enemyPlayer);
        }else
        {
            possibleMoves = board[y][x].getPossibleMoves(getBoard());
        }
        
        
        colorPossibleMoves(mousePos, possibleMoves);
        
        return possibleMoves;
    }
    
    public void setMovingPosition(Point mousePos, ArrayList<Point> possibleMoves, PieceDrawing movingPiece)
    {
        int x = mousePos.x;
        int y = mousePos.y;

        boolean isPossible = false;

        // Checks if the move is possible
        for(int i = 0; i < possibleMoves.size(); i++)
        {
            if(possibleMoves.get(i).x == mousePos.x && possibleMoves.get(i).y == mousePos.y)
            {
                isPossible = true;
                break;
            }
        }

        System.out.println("isPossible: " + isPossible);

        
        if(isPossible)
        {
            int movingX = movingPiece.getPosition().x;

            // Checks for Castle
            if(movingPiece.getType() == pieceType.King)
            {
                currentPlayer.setKingPosition(mousePos);
                //Moves the rook
                if(Math.abs(movingX - x) == 2)
                    Castle(mousePos);
            }

            //Captures if there is a piece on the cell
            if(cell[y][x].getPiece() != null)
            {
                enemyPlayer.getPieces().remove(board[y][x]);
                cell[y][x].removePiece();
            }

            
            movePiece(mousePos, movingPiece.getPosition(), movingPiece);

            
            //Checks if a pawn has moved to the last rank
            if(cell[y][x].getPiece().getType() == pieceType.Pawn)
            {
                Pawn pawnCast = (Pawn)board[y][x];
                if(pawnCast.canPromote())
                {
                    promotionWindow.show(board[y][x].getColor(), this, board[y][x].getPosition());
                }
            }
            
            Player swap = currentPlayer;
            currentPlayer = enemyPlayer;
            enemyPlayer = swap;
            
        }
        
        colorIfChecked(currentPlayer, enemyPlayer);
    }
    
    public Point getMousePos(Point mousePos)
    {
        mousePos.x -= 10;
        mousePos.y -= 30;
        
        mousePos.x /= 50;
        mousePos.y /= 50;
        
        //Sets the cords to always be in the board
        
        if(mousePos.x > 7)
            mousePos.x = 7;
        if(mousePos.x  < 0)
            mousePos.x = 0;
        if(mousePos.y > 7)
            mousePos.y = 7;
        if(mousePos.y  < 0)
            mousePos.y = 0;
        
        return mousePos;
    }
    
    public void printCells()
    {
        System.out.println();
        System.out.println("Cells");

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(cell[i][j].getPiece() != null)
                    System.out.print(cell[i][j].getPiece().getType() + " ");
                else
                    System.out.print(cell[i][j].getPiece() + " ");

            }
            System.out.println();
        }
    }
    
    public void Castle(Point mousePos)
    {
        int x = mousePos.x;
        int y = mousePos.y;
        
        //Moves the rook
        
        if(x < 4)
        {
            PieceDrawing piece = cell[y][0].getPiece();
            cell[y][3].addPiece(new PieceDrawing(piece.getType(), piece.getColor(), new Point(3, piece.getPosition().y)));
            cell[y][0].removePiece();

            board[y][3] = board[y][0];
            board[y][3].setPosition(new Point(3, y));
            board[y][3].setHasMoved();
            board[y][0] = null;
        }
        else
        {
            PieceDrawing piece = cell[y][7].getPiece();
            cell[y][5].addPiece(new PieceDrawing(piece.getType(), piece.getColor(), new Point(5, piece.getPosition().y)));
            cell[y][7].removePiece();

            board[y][5] = board[y][7];
            board[y][5].setPosition(new Point(5, y));
            board[y][5].setHasMoved();

            board[y][7] = null;
        }
    }
    
    public void movePiece(Point mousePos, Point movingPos, PieceDrawing movingPiece)
    {
        int x = mousePos.x;
        int y = mousePos.y;
        
        int movingX = movingPos.x;
        int movingY = movingPos.y;
        
        cell[y][x].addPiece(new PieceDrawing(movingPiece.getType(), movingPiece.getColor(), new Point(x, y)));
        cell[movingY][movingX].removePiece();

        board[y][x] = board[movingY][movingX];
        board[y][x].setPosition(new Point(x, y));
        board[y][x].setHasMoved();

        board[movingY][movingX] = null;

        cell[movingY][movingX].setBackground(cell[movingY][movingX].getColor());
    }
    
    public void promote(Point promotingPos, int typeIndex)
    {
        int x = promotingPos.x;
        int y = promotingPos.y;
        
        PieceDrawing currentPieceDrawing = cell[y][x].getPiece();
        
        cell[y][x].removePiece();
                
        switch(typeIndex)
        {
            case 0:
                board[y][x] = new Rook(board[y][x].getColor(), board[y][x].getPosition());
                cell[y][x].addPiece(new PieceDrawing(pieceType.Rook, currentPieceDrawing.getColor(), currentPieceDrawing.getPosition()));
                break;
            case 1:
                board[y][x] = new Knight(board[y][x].getColor(), board[y][x].getPosition());
                cell[y][x].addPiece(new PieceDrawing(pieceType.Knight, currentPieceDrawing.getColor(), currentPieceDrawing.getPosition()));
                break;
            case 2:
                board[y][x] = new Bishop(board[y][x].getColor(), board[y][x].getPosition());
                cell[y][x].addPiece(new PieceDrawing(pieceType.Bishop, currentPieceDrawing.getColor(), currentPieceDrawing.getPosition()));
                break;
            case 3:
                board[y][x] = new Queen(board[y][x].getColor(), board[y][x].getPosition());
                cell[y][x].addPiece(new PieceDrawing(pieceType.Queen, currentPieceDrawing.getColor(), currentPieceDrawing.getPosition()));
                break;
            default:
                break;
        }
    }
    
    public IPiece[][] getBoard()
    {
        return this.board;
    }
    
    public Cell[][] getCells()
    {
        return this.cell;
    }
    
    public ArrayList<IPiece> colorIfChecked(Player currentPlayer, Player enemyPlayer)
    {
        ArrayList<IPiece> currentPieces = currentPlayer.getMovablePieces(getBoard());
        ArrayList<IPiece> enemyPieces = enemyPlayer.getMovablePieces(getBoard());

        ArrayList<IPiece> attackingPieces = new ArrayList<IPiece>();
        
        System.out.println("currentSize: " + currentPieces.size());
        System.out.println("enemySize: " + enemyPieces.size());
        
        for(int i = 0; i < currentPieces.size(); i++)
        {
            if(enemyPlayer.isChecked(currentPieces.get(i), getBoard()))
            {
                int kingX = enemyPlayer.getKingPosition().x;
                int kingY = enemyPlayer.getKingPosition().y;

                cell[kingY][kingX].setBackground(Color.red);
                break;
            }
        }
        
        for(int i = 0; i < enemyPieces.size(); i++)
        {
            if(currentPlayer.isChecked(enemyPieces.get(i), getBoard()))
            {
                int kingX = currentPlayer.getKingPosition().x;
                int kingY = currentPlayer.getKingPosition().y;

                cell[kingY][kingX].setBackground(Color.red);
                attackingPieces.add(enemyPieces.get(i));
            }
        }
        
        return attackingPieces;
    }
}
