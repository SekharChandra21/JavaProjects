package bank_management;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// creating jframe
public class App extends JFrame{

    JLabel label1, label2;
    JTextField accountField;
    JPasswordField passwordField;
    JButton loginBtn;
    int tries = 3;
    Font font;
    
    public App() {
        setLayout(null);

        font = new Font("Times New Roman", Font.BOLD, 20);

        label1 = new JLabel("Enter Account Number");
        label1.setBounds(50, 50, 240, 30);
        label1.setFont(font);
        add(label1);

        accountField = new JTextField();
        accountField.setBounds(270, 50, 200, 30);
        accountField.setFont(font);
        add(accountField);
        
        label2 = new JLabel("Enter your password");
        label2.setBounds(50, 100, 240, 30);
        label2.setFont(font);
        add(label2);

        passwordField = new JPasswordField();
        passwordField.setBounds(270, 100, 200, 30);
        passwordField.setFont(font);
        add(passwordField);

        loginBtn = new JButton("LOGIN");
        loginBtn.setBounds(200, 150, 100, 30);
        add(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(tries <= 0) {
                    JOptionPane.showMessageDialog(null, "Sorry, you crossed the limit");
                }

                else {
                    String accountNumber = accountField.getText().trim();
                    String password = new String(passwordField.getPassword()).trim();
                    System.out.println("Account number : " + accountNumber);
                    System.out.println("Password : " + password);

                    if(accountNumber.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter details in all felids");
                    }

                    else {
                        Bank b;
                        try {
                            b = Bank.isValidUser(accountNumber, password);
                            System.out.println(b);
                        
                            if(b == null) {
                                tries--;
                                JOptionPane.showMessageDialog(null, "Incorrect details....!!! still "+tries+" remaining");
                            }

                            else {
                                dispose();
                                new Main(b);
                            }
                        } 
                        
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        setTitle("LOGIN");
        setBounds(400, 200, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
