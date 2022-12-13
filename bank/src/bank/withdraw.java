/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class withdraw extends JFrame implements ActionListener {
    JButton submit,back;
    JTextField names, balanceValue,amount,user;
    String userID = Integer.toString(LoginSession.UID);
     String bal = Integer.toString(LoginSession.balance);
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
    public withdraw(){
        setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("withdraw");
        
        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0);

        //labels
        JLabel l1= new JLabel("Your balance");l1.setBounds(0,100,400,30);add(l1);
        JLabel l2= new JLabel("withdraw");l2.setBounds(0,180,400,30);add(l2);
        JLabel l3= new JLabel("Enter your amount");l3.setBounds(30,200,400,30);add(l3);
        JLabel l4= new JLabel("Your account number");l4.setBounds(30,300,400,30);add(l4);
        //textfield
         names= new JTextField();names.setBounds(200, 0, 200, 30);add(names);names.setEditable(false);names.setBorder(border);
        names.setText(LoginSession.names);
         balanceValue= new JTextField();balanceValue.setBounds(200, 100, 200,60);add(balanceValue);balanceValue.setEditable(false);
        balanceValue.setText(bal);
        amount = new JTextField();amount.setBounds(30,250,400,30);add(amount);
        user = new JTextField();user.setBounds(30,350,400,30);add(user);
        user.setText(userID);user.setEditable(false);user.setBorder(border);
        
       //set color and fonts of textfields
        amount.setForeground(Color.red);amount.setFont(new Font("Serif", Font.BOLD, 20));
        balanceValue.setFont(new Font("Serif", Font.BOLD, 50));balanceValue.setForeground(Color.green); balanceValue.setBorder(border);
        
       //buttons
        submit = new JButton("Send My Request");submit.setBounds(0,400,440,40);add(submit);
        back = new JButton("back");back.setBounds(0,450,440,40);add(back);
        //addactionlisteners
        back.addActionListener(this);
        submit.addActionListener(this); 
        DoConnect();
    }
    @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			if(e.getSource()==back){

				homePage a = new homePage();
				a.setVisible(true);
				this.setVisible(false);
			}
                        if(e.getSource()==submit){
               int balance = Integer.parseInt(balanceValue.getText());
                int withdrawal = Integer.parseInt(amount.getText());
                int newBalance=(balance-withdrawal);
                int accNo= LoginSession.UID;
                          try{
                              if(balance>withdrawal){
                             stmt.executeUpdate("UPDATE userTable SET balance= '"+newBalance+"' WHERE accountNumber= '" +LoginSession.UID+"' ");
                             stmt.executeUpdate("INSERT INTO withdrawals ( accountNumber, recipient, amount, newBalance) VALUES ('"+accNo+"','"+accNo+"','"+amount.getText()+"', '"+newBalance+"')");
                             String sql = "SELECT * FROM withdrawals";
            ResultSet rs = stmt.executeQuery(sql);
                             while (rs.next()) {
                                 int userId = rs.getInt("accountNumber");
                            if (userId == accNo) { 
                              LoginSession.balance=rs.getInt("newBalance");

                             }
                             }
                             submit.setEnabled(false);
                             JOptionPane.showMessageDialog(withdraw.this, "succesfull withdrawal!");
                              }else{
                                  JOptionPane.showMessageDialog(withdraw.this, "Cannot withdraw more than what is in the account !!!");
                              }
                        }
                        catch(SQLException ex){
                            JOptionPane.showMessageDialog(withdraw.this, ex.getMessage());
                        }	
                        catch(NumberFormatException ev){
                                JOptionPane.showMessageDialog(withdraw.this, "Check that the amount is a number.");
                                System.out.println(ev.getMessage());
                        }
                        }
                            
		}
    
}
