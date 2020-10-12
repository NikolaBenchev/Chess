/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Nikola
 */
public class Cell extends JPanel{
    public Piece currentPiece = null;
    
    Cell(Color color)
    {
        this.setBackground(color);
    }
    
    public void addPiece(Piece piece)
    {
        currentPiece = piece;
        this.add(piece);
    }
    
    public void removePiece()
    {
        currentPiece = null;
        this.remove(currentPiece);
    }
}
