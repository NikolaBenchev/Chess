/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Interfaces.IPiece;
import Models.Cell;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author Nikola
 */
public class Board extends JFrame{
    IPiece[][] board = new IPiece[8][8];
    Cell[][] cell = new Cell[8][8];
    
    
    public void run()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        GridLayout grid = new GridLayout(8, 8);
        this.setLayout(grid);
        
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                int index = j;

                if(j > 4)
                    index = 8 - j - 1;

                Color color = (i % 2 == j % 2 ? Color.white : Color.gray);

                pieceType type = pieceType.values()[index];

                String pieceColor = (i < 2 ? "White" : "Black");

                cell[i][j] = new Cell(color);
                
                if(i < 2 || i > 5)
                    cell[i][j].addPiece(new Piece(type, pieceColor));
                
                this.add(cell[i][j]);
            }
        }
        
        this.setVisible(true);
        this.setSize(440, 420);
    };
}
