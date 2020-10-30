/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Models.PieceDrawing;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private PieceDrawing currentPiece = null;
    private Color color;
    
    public Cell(Color color)
    {
        this.setBackground(color);
        this.color = color;
    }
    
    public Color getColor()
    {
        return this.color;
    }
    
    public void addPiece(PieceDrawing piece)
    {
        this.add(piece);
        this.revalidate();
        this.repaint();
        this.setPiece(piece);
    }
    
    public void removePiece()
    {
        if(this.currentPiece == null)
        {
            System.out.println("currentPiece is null");
            return ;
        }
        this.remove(this.currentPiece);
        this.revalidate();
        this.repaint();
        this.setPiece(null);
    }
    
    public void setPiece(PieceDrawing piece)
    {
        this.currentPiece = piece;
    }
    
    public PieceDrawing getPiece()
    {
        if(this.currentPiece == null)
            return null;
        return this.currentPiece;
    }
}
