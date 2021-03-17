package bankingsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;

public class balanceenquiry extends JFrame implements ActionListener {

	JTextField t1, t2;
	JButton b1, b2, b3;
	JLabel l1, l2, l3;
	String pin;

	balanceenquiry(String pin) {
		this.pin = pin;

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankingsystem/icons/atmmachine.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1900, 1900, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l11 = new JLabel(i3);
		l11.setBounds(0, 0, 960, 1080);
		add(l11);

		l1 = new JLabel();
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System", Font.BOLD, 20));

		b1 = new JButton("BACK");

		setLayout(null);

		l1.setBounds(225, 270, 400, 35);
		l11.add(l1);

		b1.setBounds(450, 333, 150, 35);
		l11.add(b1);

		int balance = 0;

		try {
			Connec c1 = new Connec();
			ResultSet rs = c1.s.executeQuery("select * from bank_acc where pin = '" + pin + "'");
			while (rs.next()) {
				if (rs.getString("mode").equals("Deposit")) {
					balance += Integer.parseInt(rs.getString("amount"));
				} else {
					balance -= Integer.parseInt(rs.getString("amount"));
				}
			}
		}

		catch (Exception e) {
		}

		l1.setText("Your Current Account Balance is Rs " + balance);

		b1.addActionListener(this);

		setSize(960, 1080);
		setUndecorated(true);
		setLocation(500, 0);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		setVisible(false);
		new Transactions(pin).setVisible(true);
	}

	public static void main(String[] args) {
		new balanceenquiry("").setVisible(true);
	}

}
