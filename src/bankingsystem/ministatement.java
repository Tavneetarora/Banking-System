package bankingsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ministatement extends JFrame implements ActionListener {

	JButton b1;
	JLabel l1;

	ministatement(String pin) {

		super("mini statement");

		getContentPane().setBackground(Color.WHITE);

		setSize(400, 600);
		setLayout(null);
		setLocation(20, 20);

		l1 = new JLabel();
		add(l1);

		JLabel l2 = new JLabel("BANK STATEMENT");
		l2.setFont(new Font("System", Font.BOLD, 20));
		l2.setBounds(100, 20, 400, 100);
		add(l2);

		JLabel l3 = new JLabel();
		l3.setBounds(20, 80, 300, 20);
		add(l3);

		JLabel l4 = new JLabel();
		l4.setBounds(20, 400, 300, 20);
		add(l4);

		try {
			Connec c = new Connec();
			ResultSet rs = c.s.executeQuery("select * from login where pin = '" + pin + "'");
			l3.setText("Card Number:    " + rs.getString("cardno").substring(0, 4) + "XXXXXXXX"
					+ rs.getString("cardno").substring(12));
		}

		catch (Exception e) {
		}

		try {
			int balance = 0;
			Connec c1 = new Connec();
			ResultSet rs = c1.s.executeQuery("SELECT * FROM bank_acc where pin = '" + pin + "'");
			while (rs.next()) {
				l1.setText(l1.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+ rs.getString("mode") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+ rs.getString("amount") + "<br><br><html>");
				if (rs.getString("mode").equals("Deposit")) {
					balance += Integer.parseInt(rs.getString("amount"));
				} else {
					balance -= Integer.parseInt(rs.getString("amount"));
				}
			}

			l4.setText("Your total Balance is Rs " + balance);

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		setLayout(null);
		b1 = new JButton("Exit");
		add(b1);

		l1.setBounds(20, 140, 400, 200);
		b1.setBounds(20, 500, 100, 25);
		b1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {

		setVisible(false);

	}

	public static void main(String[] args) {

		new ministatement("").setVisible(true);
	}

}
package bankingsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ministatement extends JFrame implements ActionListener {

	JButton b1;
	JLabel l1;

	ministatement(String pin) {

		super("mini statement");

		getContentPane().setBackground(Color.WHITE);

		setSize(400, 600);
		setLayout(null);
		setLocation(20, 20);

		l1 = new JLabel();
		add(l1);

		JLabel l2 = new JLabel("BANK STATEMENT");
		l2.setFont(new Font("System", Font.BOLD, 20));
		l2.setBounds(100, 20, 400, 100);
		add(l2);

		JLabel l3 = new JLabel();
		l3.setBounds(20, 80, 300, 20);
		add(l3);

		JLabel l4 = new JLabel();
		l4.setBounds(20, 400, 300, 20);
		add(l4);

		try {
			Connec c = new Connec();
			ResultSet rs = c.s.executeQuery("select * from login where pin = '" + pin + "'");
			l3.setText("Card Number:    " + rs.getString("cardno").substring(0, 4) + "XXXXXXXX"
					+ rs.getString("cardno").substring(12));
		}

		catch (Exception e) {
		}

		try {
			int balance = 0;
			Connec c1 = new Connec();
			ResultSet rs = c1.s.executeQuery("SELECT * FROM bank_acc where pin = '" + pin + "'");
			while (rs.next()) {
				l1.setText(l1.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+ rs.getString("mode") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+ rs.getString("amount") + "<br><br><html>");
				if (rs.getString("mode").equals("Deposit")) {
					balance += Integer.parseInt(rs.getString("amount"));
				} else {
					balance -= Integer.parseInt(rs.getString("amount"));
				}
			}

			l4.setText("Your total Balance is Rs " + balance);

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		setLayout(null);
		b1 = new JButton("Exit");
		add(b1);

		l1.setBounds(20, 140, 400, 200);
		b1.setBounds(20, 500, 100, 25);
		b1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {

		setVisible(false);

	}

	public static void main(String[] args) {

		new ministatement("").setVisible(true);
	}

}
