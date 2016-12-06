package changeJarPack;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainPanel extends JPanel {

	private static final long serialVersionUID = 2L;
	private MyChangeJarPanel panel1;
	private MyChangeJarPanel panel2;
	private MyChangeJarPanel panel3;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JButton suspendButton;

	public mainPanel () {
		setLayout (new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panel1 = new MyChangeJarPanel();
		panel2 = new MyChangeJarPanel();
		panel3 = new MyChangeJarPanel();
		ChangeJar.suspend(false);
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		suspendButton = new JButton ("Suspend Operation");

		add (Box.createRigidArea(new Dimension(0,20)));
		add (label1);
		add (Box.createRigidArea(new Dimension(0,20)));
		add (panel1);
		add (Box.createRigidArea(new Dimension(0,80)));
		add (label2);
		add (Box.createRigidArea(new Dimension(0,20)));
		add (panel2);
		add (Box.createRigidArea(new Dimension(0,80)));
		add (label3);
		add (Box.createRigidArea(new Dimension(0,20)));
		add (panel3);
		add (Box.createRigidArea(new Dimension(0,60)));
		add (suspendButton);
		
		Listener listen = new Listener();
		suspendButton.addActionListener(listen);
		
		label1.setText("CHANGE JAR 1:");
		label2.setText("CHANGE JAR 2:");
		label3.setText("CHANGE JAR 3:");
		
		Font boldFont = new Font("Serif", Font.BOLD, 24);
		
		label1.setFont(boldFont);
		label2.setFont(boldFont);
		label3.setFont(boldFont);
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		label2.setAlignmentX(Component.CENTER_ALIGNMENT);
		label3.setAlignmentX(Component.CENTER_ALIGNMENT);
		suspendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		setVisible(true);
	}
	
	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()== suspendButton) {
				if (ChangeJar.isSuspended())
				{
					ChangeJar.suspend(false);
					suspendButton.setText("Suspend operation");
				}
				else
				{
					ChangeJar.suspend(true);
					suspendButton.setText("Unuspend operation");
				}
			}
		}
	}

}