package changeJarPack;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**********************************************************************
Graphical user interface functionality for ChangeJar

@author Tony Bober
@version 1.0
 ***********************************************************************/

public class MyChangeJarPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ChangeJar jar;
	private JLabel jarContentsLabel;
	private JLabel quartersLabel;
	private JLabel dimesLabel;
	private JLabel nickelsLabel;
	private JLabel penniesLabel;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;

	public MyChangeJarPanel() {
		setLayout (new BoxLayout(this, BoxLayout.X_AXIS));
		setAlignmentX(Component.CENTER_ALIGNMENT);
		setBackground (Color.getHSBColor(200,200,50));
		
		jar = new ChangeJar(2,3,4,5);
		jarContentsLabel = new JLabel();
		quartersLabel = new JLabel();
		dimesLabel = new JLabel();
		nickelsLabel = new JLabel();
		penniesLabel = new JLabel();

		button1 = new JButton ("+");
		button2 = new JButton ("+");
		button3 = new JButton ("+");
		button4 = new JButton ("+");
		button5 = new JButton ("-");
		button6 = new JButton ("-");
		button7 = new JButton ("-");
		button8 = new JButton ("-");

		Listener listen = new Listener();

		button1.addActionListener(listen);
		button2.addActionListener(listen);
		button3.addActionListener(listen);
		button4.addActionListener(listen);
		button5.addActionListener(listen);
		button6.addActionListener(listen);
		button7.addActionListener(listen);
		button8.addActionListener(listen);

		add (quartersLabel);
		add (button1);
		add (button5);
		add (Box.createRigidArea(new Dimension(20,0)));
		
		add (dimesLabel);
		add (button2);
		add (button6);
		add (Box.createRigidArea(new Dimension(20,0)));
		
		add (nickelsLabel);
		add (button3);
		add (button7);
		add (Box.createRigidArea(new Dimension(20,0)));
		
		add (penniesLabel);
		add (button4);
		add (button8);
		add (Box.createRigidArea(new Dimension(30,0)));
		
		add(jarContentsLabel);
		
		jarContentsLabel.setText(jar.toString());
		quartersLabel.setText("Quarters: ");
		dimesLabel.setText("Dimes: ");
		nickelsLabel.setText("Nickels: ");
		penniesLabel.setText("Pennies: ");
		
		
		button1.setBackground(Color.green);
		button2.setBackground(Color.green);
		button3.setBackground(Color.green);
		button4.setBackground(Color.green);
		button5.setBackground(Color.red);
		button6.setBackground(Color.red);
		button7.setBackground(Color.red);
		button8.setBackground(Color.red);
		
		Font italicFont = new Font("Serif", Font.ITALIC, 12);
		
		jarContentsLabel.setFont(italicFont);
		quartersLabel.setFont(italicFont);
		dimesLabel.setFont(italicFont);
		nickelsLabel.setFont(italicFont);
		penniesLabel.setFont(italicFont);
	}

	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()== button1) {
				jar.putIn(1,0,0,0);
				jarContentsLabel.setText(jar.toString());
			}
			if (e.getSource()== button2) {
				jar.putIn(0,1,0,0);
				jarContentsLabel.setText(jar.toString());
			}
			if (e.getSource()== button3) {
				jar.putIn(0,0,1,0);
				jarContentsLabel.setText(jar.toString());
			}
			if (e.getSource()== button4) {
				jar.putIn(0,0,0,1);
				jarContentsLabel.setText(jar.toString());
			}
			if (e.getSource()== button5) {
				jar.takeOut(1,0,0,0);
				jarContentsLabel.setText(jar.toString());
			}
			if (e.getSource()== button6) {
				jar.takeOut(0,1,0,0);
				jarContentsLabel.setText(jar.toString());
			}
			if (e.getSource()== button7) {
				jar.takeOut(0,0,1,0);
				jarContentsLabel.setText(jar.toString());
			}
			if (e.getSource()== button8) {
				jar.takeOut(0,0,0,1);
				jarContentsLabel.setText(jar.toString());
			}

		}
	} 
}
