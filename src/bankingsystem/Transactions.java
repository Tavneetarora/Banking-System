package bankingsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Transactions extends JFrame implements ActionListener {

	JLabel l1;
	JButton b1, b2, b3, b4, b5, b6, b7;
	String pin;

	Transactions(String pin) {
		this.pin = pin;
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankingsystem/icons/atmmachine.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1750, 1750, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l11 = new JLabel(i3);
		l11.setBounds(0, 0, 960, 1080);
		add(l11);

		l1 = new JLabel("Please Select Your Transaction");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System", Font.BOLD, 16));

		b1 = new JButton("DEPOSIT");
		b2 = new JButton("CASH WITHDRAWL");
		b3 = new JButton("FAST CASH");
		b4 = new JButton("MINI STATEMENT");
		b5 = new JButton("PIN CHANGE");
		b6 = new JButton("BALANCE ENQUIRY");
		b7 = new JButton("EXIT");

		setLayout(null);

		l1.setBounds(295, 200, 700, 35);
		l11.add(l1);

		b1.setBounds(255, 280, 150, 35);
		l11.add(b1);

		b2.setBounds(255, 335, 150, 35);
		l11.add(b2);

		b3.setBounds(255, 390, 150, 35);
		l11.add(b3);

		b4.setBounds(425, 280, 150, 35);
		l11.add(b4);

		b5.setBounds(255, 445, 150, 35);
		l11.add(b5);

		b6.setBounds(425, 335, 150, 35);
		l11.add(b6);

		b7.setBounds(425, 390, 150, 35);
		l11.add(b7);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);

		setSize(960, 800);
		setLocation(400, 20);
		setUndecorated(true);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == b1) {
			setVisible(false);
			new deposit(pin).setVisible(true);
		}

		else if (ae.getSource() == b2) {
			setVisible(false);
			new withdrawal(pin).setVisible(true);
		}

		else if (ae.getSource() == b3) {
			setVisible(false);
			new fastcash(pin).setVisible(true);
		}

		else if (ae.getSource() == b4) {
			setVisible(false);
			new ministatement(pin).setVisible(true);
		}

		else if (ae.getSource() == b5) {
			setVisible(false);
			new pin(pin).setVisible(true);
		}

		else if (ae.getSource() == b6) {
			this.setVisible(false);
			new balanceenquiry(pin).setVisible(true);
		}

		else if (ae.getSource() == b7) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new Transactions("").setVisible(true);
	}
}
