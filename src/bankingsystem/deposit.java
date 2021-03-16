package bankingsystem;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class deposit extends JFrame implements ActionListener {

	JTextField t1, t2;
	JButton b1, b2;
	JLabel l1;
	String pin;

	deposit(String pin) {
		this.pin = pin;
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankingsystem/icons/atmmachine.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1900, 1900, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l11 = new JLabel(i3);
		l11.setBounds(0, 0, 960, 1080);
		add(l11);

		l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System", Font.BOLD, 20));

		t1 = new JTextField();
		t1.setFont(new Font("Raleway", Font.BOLD, 35));

		b1 = new JButton("DEPOSIT");

		b2 = new JButton("BACK");

		setLayout(null);

		l1.setBounds(210, 150, 500, 60);
		l11.add(l1);

		t1.setBounds(250, 200, 320, 50);
		l11.add(t1);

		b1.setBounds(450, 385, 150, 35);
		l11.add(b1);

		b2.setBounds(450, 440, 150, 35);
		l11.add(b2);

		b1.addActionListener(this);
		b2.addActionListener(this);

		setSize(960, 1080);
		setLocation(500, 0);
		setUndecorated(true);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {

		try {
			if (ae.getSource() == b1) {
				String amount = t1.getText();
				Date date = new Date();
				if (t1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
				}

				else {
					Connec c1 = new Connec();
					c1.s.executeUpdate(
							"insert into bank_acc values('" + pin + "', '" + date + "', 'Deposit', '" + amount + "')");
					JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");
					setVisible(false);
					new Transactions(pin).setVisible(true);
				}
			}

			else if (ae.getSource() == b2) {
				setVisible(false);
				new Transactions(pin).setVisible(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new deposit("").setVisible(true);
	}

}
