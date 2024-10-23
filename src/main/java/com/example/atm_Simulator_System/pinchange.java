//package com.example;

package com.example.atm_Simulator_System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class pinchange extends JFrame implements ActionListener {
    JPasswordField pin, repin;
    JButton change, back;
    String pinnumber;

    pinchange(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Change Your PIN");
        text.setBounds(235, 280, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        JLabel pintext = new JLabel("New PIN");
        pintext.setBounds(165, 320, 180, 25);
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.LAYOUT_LEFT_TO_RIGHT, 16));
        image.add(pintext);

        pin = new JPasswordField();
        pin.setBounds(330, 320, 180, 25);
        pin.setFont(new Font("Arial", Font.BOLD, 15));
        add(pin);

        JLabel repintext = new JLabel("Re-Enter New PIN");
        repintext.setBounds(165, 360, 180, 25);
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.LAYOUT_LEFT_TO_RIGHT, 16));
        image.add(repintext);

        repin = new JPasswordField();
        repin.setBounds(330, 360, 180, 25);
        repin.setFont(new Font("Arial", Font.BOLD, 15));
        add(repin);

        change = new JButton("Change");
        change.setBackground(Color.RED);
        change.setForeground(Color.black);
        change.setFont(new Font("Raleway", Font.BOLD, 16));
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        add(change);

        back = new JButton("Back");
        back.setBackground(Color.RED);
        back.setForeground(Color.black);
        back.setFont(new Font("Raleway", Font.BOLD, 16));
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        add(back);


        setSize(900, 900);
        setUndecorated(true);
        setLocation(300, 0);
        setVisible(true);
    }

    public static void main(String[] args) {
        new pinchange("").setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {
                String npin = pin.getText();
                String rpin = repin.getText();
                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered Pin does not Match");
                    return;
                } else if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter Pin");
                    return;
                } else if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Re-Enter Pin");
                    return;
                }

                connectJDBC con = connectJDBC.getInstance();

                String query1 = "UPDATE bank SET pinnumber = ? WHERE pinnumber = ?";
                String query2 = "UPDATE login SET pinnumber = ? WHERE pinnumber = ?";
                String query3 = "UPDATE signupthree SET pinnumber = ? WHERE pinnumber = ?";

                try (Connection conn = con.getConnection();
                     PreparedStatement pstmt1 = conn.prepareStatement(query1);
                     PreparedStatement pstmt2 = conn.prepareStatement(query2);
                     PreparedStatement pstmt3 = conn.prepareStatement(query3)) {

                    pstmt1.setString(1, rpin);
                    pstmt1.setString(2, pinnumber);
                    pstmt2.setString(1, rpin);
                    pstmt2.setString(2, pinnumber);
                    pstmt3.setString(1, rpin);
                    pstmt3.setString(2, pinnumber);

                    pstmt1.executeUpdate();
                    pstmt2.executeUpdate();
                    pstmt3.executeUpdate();

                    String userEmail = getUserEmailByPinNumber(pinnumber);
                    if (userEmail != null) {
                        EmailUtility.sendEmail(userEmail, "PIN Change Notification",
                                "Your PIN has been successfully changed.");
                    }

                    JOptionPane.showMessageDialog(null, "PIN changed successfully.");
                    setVisible(false);
                    new transactions(rpin).setVisible(true);

                }
                catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "Error updating pin: " + sqlException.getMessage());
                    sqlException.printStackTrace();
                } finally {
                    // Close the connection in the 'finally' block
                    if (con != null && con.s != null) {
                        try {
                            con.s.close();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == back) {
            // Handle back button action
            setVisible(false);
            new transactions(pinnumber).setVisible(true);
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
}