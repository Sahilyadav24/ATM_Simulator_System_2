//package com.example;

package com.example.atm_Simulator_System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FastCash extends JFrame implements ActionListener {
    JButton withdrawal, fastcash, ministatement, pinchange, deposit, balanceenquiry, exit;
    String pinnumber;

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Select Withdrawal Amount");
        text.setBounds(235, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        withdrawal = new JButton("Rs 100");
        withdrawal.setBounds(355, 415, 150, 30);
        withdrawal.addActionListener(this);
        image.add(withdrawal);

        fastcash = new JButton("Rs 500");
        fastcash.setBounds(355, 450, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("Rs 1000");
        ministatement.setBounds(170, 450, 150, 30);
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("Rs 2000");
        pinchange.setBounds(170, 415, 150, 30);
        pinchange.addActionListener(this);
        image.add(pinchange);

        deposit = new JButton("Rs 5000");
        deposit.setBounds(355, 485, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        balanceenquiry = new JButton("Rs 10000");
        balanceenquiry.setBounds(170, 485, 150, 30);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        exit = new JButton("BACK");
        exit.setBounds(355, 520, 150, 30);
        exit.addActionListener(this);
        image.add(exit);

        setSize(900, 900);
        setUndecorated(true);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            setVisible(false);
            new transactions(pinnumber).setVisible(true);
        } else {
            String amount = ((JButton) ae.getSource()).getText().substring(3);

            connectJDBC con = connectJDBC.getInstance();
            try (Statement stmt = con.getConnection().createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM bank WHERE pinnumber ='" + pinnumber + "'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("Amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("Amount"));
                    }
                }

                if (balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                Date date = new Date(System.currentTimeMillis());
                String query = "INSERT INTO bank (pinnumber, date, type, amount) VALUES('" + pinnumber + "','" + date + "','Withdrawal','" + amount + "')";
                stmt.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");

                String userEmail = getUserEmailByPinNumber(pinnumber); // Retrieve the user's email
                if (userEmail != null) {
                    EmailUtility.sendEmail(userEmail, "Deposit Notification", "You have successfully deposited Rs " + amount + " into your account.");
                }
                setVisible(false);
                new transactions(pinnumber).setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String getUserEmailByPinNumber(String pinnumber) {
        String email = null;
        String query = "SELECT email FROM SignupThree WHERE cardnumber = (SELECT cardnumber FROM Login WHERE pinnumber = ?)";

        // Use the singleton connection instance
        try (Connection connection = connectJDBC.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, pinnumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                email = resultSet.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    public static void main(String[] args) {
        new FastCash(" ");
    }
}
