
package bankingsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class login extends JFrame implements ActionListener {
	JLabel l1, l2, l3;
	JTextField tf1;
	JPasswordField pf2;
	JButton b1, b2, b3;

	login() {
		super("BANKING SYSTEM");

		l1 = new JLabel("WELCOME TO BANKING SYSTEM");
		l1.setFont(new Font("Osward", Font.BOLD, 25));
		l1.setBounds(180, 40, 450, 40);
		add(l1);

		l2 = new JLabel("Account Number:");
		l2.setFont(new Font("Raleway", Font.BOLD, 20));
		l2.setBounds(125, 150, 375, 30);
		add(l2);

		l3 = new JLabel("Account PIN:");
		l3.setFont(new Font("Raleway", Font.BOLD, 20));
		l3.setBounds(125, 220, 375, 30);
		add(l3);

		tf1 = new JTextField(15);
		tf1.setBounds(300, 150, 230, 30);
		tf1.setFont(new Font("Arial", Font.BOLD, 14));
		add(tf1);

		pf2 = new JPasswordField(15);
		pf2.setFont(new Font("Arial", Font.BOLD, 14));
		pf2.setBounds(300, 220, 230, 30);
		add(pf2);

		b1 = new JButton("LOG IN");
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);

		b2 = new JButton("CLEAR");
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);

		b3 = new JButton("CREATE LOGIN");
		b3.setBackground(Color.BLACK);
		b3.setForeground(Color.WHITE);

		setLayout(null);

		b1.setFont(new Font("Arial", Font.BOLD, 14));
		b1.setBounds(300, 300, 100, 30);
		add(b1);

		b2.setFont(new Font("Arial", Font.BOLD, 14));
		b2.setBounds(430, 300, 100, 30);
		add(b2);

		b3.setFont(new Font("Arial", Font.BOLD, 14));
		b3.setBounds(300, 350, 230, 30);
		add(b3);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

		getContentPane().setBackground(Color.WHITE);

		setSize(750, 600);
		setLocation(500, 200);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {

		try {

			if (ae.getSource() == b1) {
				Connec c1 = new Connec();
				String cardno = tf1.getText();
				String pin = pf2.getText();
				String q = "select * from login where cardno = '" + cardno + "' and pin = '" + pin + "'";
				ResultSet rs = c1.s.executeQuery(q);
				if (rs.next()) {
					new Transactions(pin).setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
				}
			} else if (ae.getSource() == b2) {
				tf1.setText("");
				pf2.setText("");
			} else if (ae.getSource() == b3) {
				setVisible(false);
				new signup1().setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void main(String[] args) {
		new login().setVisible(true);
	}

}
