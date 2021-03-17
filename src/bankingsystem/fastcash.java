package bankingsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class fastcash extends JFrame implements ActionListener {

	JLabel l1, l2;
	JButton b1, b2, b3, b4, b5, b6, b7, b8;
	JTextField t1;

	String pin;

	fastcash(String pin) {
		this.pin = pin;
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankingsystem/icons/atmmachine.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1900, 1900, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l11 = new JLabel(i3);
		l11.setBounds(0, 0, 960, 1080);
		add(l11);

		l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System", Font.BOLD, 16));

		b1 = new JButton("Rs 100");
		b2 = new JButton("Rs 500");
		b3 = new JButton("Rs 1000");
		b4 = new JButton("Rs 2000");
		b5 = new JButton("Rs 5000");
		b6 = new JButton("Rs 10000");
		b7 = new JButton("BACK");

		setLayout(null);

		l1.setBounds(290, 200, 700, 35);
		l11.add(l1);

		b1.setBounds(220, 270, 150, 35);
		l11.add(b1);

		b2.setBounds(450, 270, 150, 35);
		l11.add(b2);

		b3.setBounds(220, 325, 150, 35);
		l11.add(b3);

		b4.setBounds(450, 325, 150, 35);
		l11.add(b4);

		b5.setBounds(220, 380, 150, 35);
		l11.add(b5);

		b6.setBounds(450, 380, 150, 35);
		l11.add(b6);

		b7.setBounds(450, 435, 150, 35);
		l11.add(b7);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);

		setSize(960, 1080);
		setLocation(500, 0);
		setUndecorated(true);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		try {
			String amount = ((JButton) ae.getSource()).getText().substring(3); // k
			Connec c = new Connec();
			ResultSet rs = c.s.executeQuery("select * from bank_acc where pin = '" + pin + "'");
			int balance = 0;
			while (rs.next()) {
				if (rs.getString("mode").equals("Deposit")) {
					balance += Integer.parseInt(rs.getString("amount"));
				} else {
					balance -= Integer.parseInt(rs.getString("amount"));
				}

			} // String num = "17";
			if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
				JOptionPane.showMessageDialog(null, "Insuffient Balance");
				return;
			}

			if (ae.getSource() == b7) {
				setVisible(false);
				new Transactions(pin).setVisible(true);
			} else {
				Date date = new Date();
				c.s.executeUpdate(
						"insert into bank_acc values('" + pin + "', '" + date + "', 'Withdrawl', '" + amount + "')");
				JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");

				setVisible(false);
				new Transactions(pin).setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new fastcash("").setVisible(true);
	}

}
