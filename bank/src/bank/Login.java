package bank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.*;
//import java.sql.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login extends JFrame implements ActionListener {
    JLabel l1, accLabel, passwordLabel;
    public JTextField accountNumber;
    JButton login, register;
    JPasswordField password;
    Connection conn;
    Statement stmt;
    ResultSet rs;

    public void DoConnect() {
        try {
            //STEP 1: Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver"); //JDBC driver name

            //Database Credentials
            String host = "jdbc:mysql://localhost/bank_task1"; //Database URL
            String uName = "root";
            String uPass = "";

            conn = DriverManager.getConnection(host, uName, uPass);


            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);



        } catch (SQLException | ClassNotFoundException err) {
            System.out.println("error");
        }
    }

    public Login() {
        setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");

        l1 = new JLabel("Login Details");
        l1.setForeground(Color.white);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        accLabel = new JLabel("Account Number:");
        passwordLabel = new JLabel("Password:");
        accountNumber = new JTextField();
        password = new JPasswordField();
        login = new JButton("Login");
        register = new JButton("Register");


        //btn1.addActionListener(this);
        //btn2.addActionListener(this);

        l1.setBounds(300, 110, 400, 30);
        accLabel.setBounds(80, 160, 200, 30);
        passwordLabel.setBounds(80, 210, 200, 30);
        accountNumber.setBounds(300, 160, 200, 30);
        password.setBounds(300, 210, 200, 30);
        login.setBounds(250, 270, 100, 30);
        register.setBounds(370, 270, 100, 30);
        add(l1);
        add(accLabel);
        add(passwordLabel);
        add(accountNumber);
        add(password);
        add(login);
        add(register);


        //actions
        register.addActionListener(this);
        login.addActionListener(this);
        DoConnect();
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == register) {

            register a = new register();
            a.setVisible(true);
            this.setVisible(false);
        }
        
        String pass = String.valueOf(password.getPassword());
        int accNo = Integer.parseInt(accountNumber.getText());
        boolean checkUser = false, checkPassword = false;
        try {
            String sql = "SELECT * FROM userTable";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int userId = rs.getInt("accountNumber");
                if (userId == accNo) {
                    checkUser = true;
                    String passcode = rs.getString("password");
                    if (pass.equals(passcode)) {
                        checkPassword = true; 
                        LoginSession.names = rs.getString("Surname") + " " + rs.getString("otherName");
                        LoginSession.UID = rs.getInt("accountNumber");
                        LoginSession.balance= rs.getInt("balance");
                    }
                }
            }
            rs.close();
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(Login.this, se.getMessage());
        }

        if (e.getSource() == login) {
            if (checkUser == true) {
                if (checkPassword == true) {
                    JOptionPane.showMessageDialog(Login.this, "Welcome " + LoginSession.names + " !!");
                    homePage a = new homePage();
                    a.setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Password or Account number is wrong!!!");
                }
            } else {
                JOptionPane.showMessageDialog(Login.this, "could not find User " + accNo + " in the database!!");
            }
        }




    }



}