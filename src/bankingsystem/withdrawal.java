package bankingsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class withdrawal extends JFrame implements ActionListener {

	JTextField t1, t2;
	JButton b1, b2;
	JLabel l1, l2;

	String pin;

	withdrawal(String pin) {
		this.pin = pin;
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankingsystem/icons/atmmachine.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1900, 1900, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l11 = new JLabel(i3);
		l11.setBounds(0, 0, 960, 1080);
		add(l11);

		l1 = new JLabel("MAXIMUM DAILY WITHDRAWAL AMOUNT IS RS.10,000");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("System", Font.BOLD, 15));

		l2 = new JLabel("PLEASE ENTER THE AMOUNT");
		l2.setForeground(Color.WHITE);
		l2.setFont(new Font("System", Font.BOLD, 16));

		t1 = new JTextField();
		t1.setFont(new Font("Raleway", Font.BOLD, 25));

		b1 = new JButton("WITHDRAW");
		b2 = new JButton("BACK");

		setLayout(null);

		l1.setBounds(215, 150, 800, 60);
		l11.add(l1);

		l2.setBounds(290, 205, 800, 60);
		l11.add(l2);

		t1.setBounds(245, 260, 330, 30);
		l11.add(t1);

		b1.setBounds(430, 300, 150, 35);
		l11.add(b1);

		b2.setBounds(430, 450, 150, 35);
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
				Connec c1 = new Connec();
				int balance = 0;

				if (t1.getText().equals(null)) {
					JOptionPane.showMessageDialog(null, "Enter The Amount You Want To Withdraw");
				} else {
					ResultSet rs = c1.s.executeQuery("select * from bank_acc where pin = '" + pin + "' ");

					while (rs.next()) {
						if (rs.getString("mode").equals("Deposit")) {
							balance += Integer.parseInt(rs.getString("amount"));
						} else {
							balance -= Integer.parseInt(rs.getString("amount"));
						}
					}
				}

				if (balance < Integer.parseInt(amount)) {
					JOptionPane.showMessageDialog(null, "Insufficient Balance");
					return; // function returned..action performed exited
				}

				c1.s.executeUpdate("insert into bank_acc values('" + pin + "' , '" + date + "' , 'Withdrawal' , '"
						+ amount + "' )");

				JOptionPane.showMessageDialog(null, "Rs. " + amount + " debited successfully");

				setVisible(false);
				new Transactions(pin).setVisible(true);

			}

			else if (ae.getSource() == b2) {
				setVisible(false);
				new Transactions(pin).setVisible(true);

			}

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new withdrawal("").setVisible(true);
	}
}
