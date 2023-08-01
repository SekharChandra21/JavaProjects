package bank_management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame{
    Bank b;
    JButton checkBalanceBtn, withdrawBtn, depositBtn, logoutBtn;
    JLabel label1, label2;

    public Main(Bank bank) {
        b = bank;
        Font font = new Font("Times New Roman", Font.BOLD, 20);

        setLayout(null);

        label1 = new JLabel("Welcome "+b.holderName);
        label1.setFont(font);
        label1.setBounds(50, 50, 200, 30);
        add(label1);

        label2 = new JLabel();
        label2.setFont(font);
        label2.setBounds(50, 250, 300, 30);
        add(label2);

        checkBalanceBtn = new JButton("Check Balance");
        checkBalanceBtn.setBounds(150, 100, 150, 30);
        add(checkBalanceBtn);
        checkBalanceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                label2.setText("current balance : " + b.balance+" Rs.");
            }
        });

        withdrawBtn = new JButton("withdraw");
        withdrawBtn.setBounds(175, 150, 100, 30);
        add(withdrawBtn);
        withdrawBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    long amount = Long.parseLong(JOptionPane.showInputDialog(null, "Enter amount to Withdraw..."));
                    if(b.balance < amount) {
                        JOptionPane.showMessageDialog(null, "failed");
                        label2.setText("less bank balance");
                    }

                    else {
                        b.balance -= amount;
                        JOptionPane.showMessageDialog(null, "Success");
                        label2.setText("Available balance : " + (b.balance));
                    }
                } 

                catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Enter valid amount");
                }
                
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        depositBtn = new JButton("Deposit");
        depositBtn.setBounds(175, 200, 100, 30);
        add(depositBtn);
        depositBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    long amount = Long.parseLong(JOptionPane.showInputDialog(null, "Enter amount to Deposit..."));
                    b.balance += amount;
                    JOptionPane.showMessageDialog(null, "Success");
                    label2.setText("current balance : " + b.balance);
                } 

                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Enter valid amount");
                }
            }
        });

        logoutBtn = new JButton("LOGOUT");
        logoutBtn.setBounds(300, 200, 100, 30);
        add(logoutBtn);
        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();
                new App();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 200, 600, 400);
        setTitle("Main Page");
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main(new Bank("123456789", "user01", 1000));
    }
}
