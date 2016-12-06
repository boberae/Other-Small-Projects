package changeJarPack;

import java.awt.Color;
import javax.swing.*;

/**********************************************************************
Graphical user interface for ChangeJar

@author Tony Bober
@version 1.0
 ***********************************************************************/

public class MyChangeJar 
{
    //-----------------------------------------------------------------
    //  Creates and displays the frame.
    //-----------------------------------------------------------------
    public static void main(String[] args) {

        JFrame frame = new JFrame("Change Jar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel panel = new mainPanel();
		panel.setBackground (Color.getHSBColor(200,200,50));
        frame.getContentPane().add(panel);

        frame.setSize(1000, 550);
        frame.setVisible(true);
    }
}
