/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Models.enums.pieceType;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import Models.enums.*;

/**
 *
 * @author Nikola
 */
public class PieceDrawing extends JLabel{
    private Point position;
    private pieceColor color;
    private pieceType type;
    
    public PieceDrawing(pieceType type, pieceColor color, Point pos)
    {
        this.setType(type);
        this.setColor(color);
        this.setPosition(pos);
        
        Image img = null;
        try{
            img = ImageIO.read(new File("Images/" + type + "_" + color + ".png"));
        } catch(IOException e){
        }
        img = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);

        Icon icon = new ImageIcon(img);
        
        this.setIcon(icon);
    }
    
    public void setPosition(Point value)
    {
        this.position = value;
    }
    
    public Point getPosition()
    {
        return this.position;
    }
    
    public void setColor(pieceColor value)
    {
        this.color = value;
    }
    
    public pieceColor getColor()
    {
        return this.color;
    }
    
    public void setType(pieceType value)
    {
        this.type = value;
    }
    
    public pieceType getType()
    {
        if(this.type == null)
            return null;
        return this.type;
    }
}
