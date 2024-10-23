//package com.example;

package com.example.atm_Simulator_System;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupTwo extends JFrame implements ActionListener {

        JTextField  pantext,adhartext;
        JRadioButton yes,no;
        JButton next, back;
        String formno;

        JComboBox<String> relegionn, valcategory, incomeee, edu, ocu;


        SignupTwo(String formno) {
            this.formno = formno;
            setLayout(null);
            System.out.println(formno);

            setTitle("New Account Application Form - Page 2");


            JLabel additionalDetails = new JLabel("Page 2 : Additional Details");

            additionalDetails.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 18));
            additionalDetails.setBounds(290, 80, 400, 30);
            add(additionalDetails);


            JLabel relegion = new JLabel("Relegion :");
            relegion.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
            relegion.setBounds(100, 140, 100, 30);
            add(relegion);

            String valrel[] = {"Hindu", "Muslim", "sikh", "christan", "Bodh","Others"};
            relegionn = new JComboBox(valrel);
            relegionn.setBounds(350, 140, 250, 30);
            relegionn.setFont(new Font("Arial", Font.BOLD, 15));
            relegionn.setBackground(Color.white);
            add(relegionn);


            JLabel category = new JLabel("Category :");
            category.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
            category.setBounds(100, 190, 150, 30);
            add(category);

            String valcat[] = {"General", "OBC", "SC", "ST", "Other"};
            valcategory = new JComboBox(valcat);
            valcategory.setBounds(350, 190, 250, 30);
            valcategory.setFont(new Font("Arial", Font.BOLD, 15));
            valcategory.setBackground(Color.white);
            add(valcategory);


            JLabel income = new JLabel("Income :");
            income.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
            income.setBounds(100, 240, 150, 30);
            add(income);

            String valinc[] = {">20,000", ">50,000", ">1,00,000", ">5,00,000", "<5,00,000"};
            incomeee = new JComboBox(valinc);
            incomeee.setBounds(350, 240, 250, 30);
            incomeee.setFont(new Font("Arial", Font.BOLD, 15));
            incomeee.setBackground(Color.white);
            add(incomeee);

            JLabel education = new JLabel("Education ");
            education.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
            education.setBounds(100, 305, 150, 30);
            add(education);

            JLabel qualification = new JLabel("Qualification :");
            qualification.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
            qualification.setBounds(100, 325, 150, 30);
            add(qualification);

            String valedu[] = {"10th", "12th", "Diploma", "Graduation", "Post-Graduation","Doctrate","Others"};
            edu = new JComboBox(valedu);
            edu.setBounds(350, 310, 250, 30);
            edu.setFont(new Font("Arial", Font.BOLD, 15));
            edu.setBackground(Color.white);
            add(edu);

            JLabel occupation = new JLabel("Occupation :");
            occupation.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
            occupation.setBounds(100, 390, 150, 30);
            add(occupation);

            String valocu[] = {"Salaried", "Buisness", "Student", "Retired", "Self-Employed","Others"};
            ocu = new JComboBox(valocu);
            ocu.setBounds(350, 390, 250, 30);
            ocu.setFont(new Font("Arial", Font.BOLD, 15));
            ocu.setBackground(Color.white);
            add(ocu);

            JLabel pan = new JLabel("PAN Number :");
            pan.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
            pan.setBounds(100, 440, 150, 30);
            add(pan);

            pantext = new JTextField();
            pantext.setBounds(350, 440, 250, 30);
            pantext.setFont(new Font("Arial", Font.BOLD, 15));
            add(pantext);


            JLabel adhar = new JLabel("Adhar No :");
            adhar.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
            adhar.setBounds(100, 490, 150, 30);
            add(adhar);

            adhartext = new JTextField();
            adhartext.setBounds(350, 490, 250, 30);
            adhartext.setFont(new Font("Arial", Font.BOLD, 15));
            add(adhartext);


            JLabel senior = new JLabel("Senior Citizen :");
            senior.setFont(new Font("Raleway", Font.LAYOUT_LEFT_TO_RIGHT, 20));
            senior.setBounds(100, 540, 150, 30);
            add(senior);

            yes = new JRadioButton("YES");
            yes.setBounds(350,540,70,30);
            yes.setBackground(Color.PINK);
            add(yes);

            no = new JRadioButton("NO");
            no.setBounds(450,540,70,30);
            no.setBackground(Color.PINK);
            add(no);


            ButtonGroup buttonselect = new ButtonGroup();
            buttonselect.add(yes);
            buttonselect.add(no);

            next = new JButton("NEXT");
            next.setBackground(Color.RED);
            next.setForeground(Color.black);
            next.setFont(new Font("Raleway", Font.BOLD, 16));
            next.setBounds(520, 590, 80, 30);
            next.addActionListener(this);
            add(next);


            back = new JButton("BACK");
            back.setBackground(Color.RED);
            back.setForeground(Color.black);
            back.setFont(new Font("Raleway", Font.BOLD, 16));
            back.setBounds(420, 590, 80, 30);
            back.addActionListener(this);
            add(back);

            getContentPane().setBackground(Color.cyan);//for background colour , it is in AWT package

            setSize(800, 700);
            setVisible(true);
            setLocation(350, 50);
        }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            // Handle Next Button
            String religion = (String) relegionn.getSelectedItem();
            String category = (String) valcategory.getSelectedItem();
            String income = (String) incomeee.getSelectedItem();
            String education = (String) edu.getSelectedItem();
            String occupation = (String) ocu.getSelectedItem();
            int seniorcitizen = yes.isSelected() ? 1 : 0;
            String pan = pantext.getText();
            String adhar = adhartext.getText();

            // Store data to local storage
            storeDataLocally(formno, religion, category, income, education, occupation, pan, adhar, seniorcitizen);

            setVisible(false);
                new SignupThree(formno).setVisible(true);
        } else if (ae.getSource() == back) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }

    private void storeDataLocally(String formno, String religion, String category, String income, String education, String occupation, String pan, String adhar, int seniorcitizen) {
        JsonObject userData = new JsonObject();
        userData.addProperty("formno", formno);
        userData.addProperty("religion", religion);
        userData.addProperty("category", category);
        userData.addProperty("income", income);
        userData.addProperty("education", education);
        userData.addProperty("occupation", occupation);
        userData.addProperty("pan", pan);
        userData.addProperty("adhar", adhar);
        userData.addProperty("seniorcitizen", seniorcitizen == 1 ? "Yes" : "No");

        FileUtils.writeToFile(userData, "Signuptwo.json");
    }
    public static void main(String[] args) {

            new SignupTwo("").setVisible(true);
        }
    }