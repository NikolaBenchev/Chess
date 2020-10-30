/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Interfaces.IPiece;
import Models.Board;
import Models.PieceDrawing;
import Models.Pieces.*;
import Models.enums.pieceType;
import static Models.enums.pieceType.Rook;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Models.enums.*;

/**
 *
 * @author Nikola
 */

public class PromotionScreen extends JFrame{
    public Cell[] promotionCell = new Cell[4];
    
    public Board fullBoard;
    public IPiece[][] board;
    public Cell[][] cell;
    
    public Point promotingPos;
    public int typeIndex = -1;
    
    
    
    public PromotionScreen()
    {
        GridLayout layout = new GridLayout(1, 4);
        this.setLayout(layout);
        this.setSize(220, 90);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        for(int i = 0; i < 4; i++)
        {
            Color color = (i % 2 == 0 ? Color.white : Color.gray);
            promotionCell[i] = new Cell(color);
            this.add(promotionCell[i]);
        }
        
        this.addMouseListener(onClick);
    }
    
    public void show(pieceColor color, Board fullBoard, Point promotingPos)
    {
        this.setVisible(true);
        
        this.fullBoard = fullBoard;
        this.promotingPos = promotingPos;
        
        this.board = fullBoard.getBoard();
        this.cell = fullBoard.getCells();
        
        for(int i = 0; i < 4; i++)
        {
            pieceType type = null;
            
            switch(i)
            {
                case 0:
                    type = pieceType.Rook;
                    break;
                case 1:
                    type = pieceType.Knight;
                    break;
                case 2:
                    type = pieceType.Bishop;
                    break;
                case 3:
                    type = pieceType.Queen;
                    break;
            }
            promotionCell[i].addPiece(new PieceDrawing(type, color, new Point(i * 50, 0)));
        }
    }
    
    MouseAdapter onClick = new MouseAdapter()
    {
        public void mouseClicked(MouseEvent ev)
        {
            int x = ev.getPoint().x;

            x -= 10;
            x /= 50;
            
            typeIndex = x;

            Pawn pawnCast = (Pawn)board[promotingPos.y][promotingPos.x];
            
            fullBoard.promote(promotingPos, typeIndex);
            
            dispose();
            board = new IPiece[8][8];
            cell = new Cell[8][8];
        }
    };
}
