/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Nikola
 */
public class Piece extends JLabel{
    Piece(pieceType type, String pieceColor)
    {
        
        
        Image img = null;
        try{
            img = ImageIO.read(new File("Images/Pawn_Black.png"));
        } catch(IOException e){
        }
        img = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  

        Icon icon = new ImageIcon(img);
        
        this.setIcon(icon);
    }
}
