
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

public class accountDetails extends JFrame implements ActionListener {
    JLabel l1, l2, l3,l4,l5,l6,l7,l9;
    JTable deposits;
    JTextField accountNumber,surname,otherNames,nationalId,mobileNumber,email,balance;
    JButton submit,back;
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
                        String sql = "SELECT * FROM userTable";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int userId = rs.getInt("accountNumber");
                if (userId == LoginSession.UID) {
                    accountNumber.setText(userID);
                    mobileNumber.setText(Integer.toString(rs.getInt("mobile")));
                    nationalId.setText(Integer.toString(rs.getInt("nationalId")));
                    balance.setText(Integer.toString(rs.getInt("balance")));
                    surname.setText(rs.getString("surname"));
                    otherNames.setText(rs.getString("otherName"));
                    email.setText(rs.getString("email"));

                }
            }

        } catch (SQLException | ClassNotFoundException err) {
            System.out.println("error");
        }
    }
    public accountDetails(){
        setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ACCOUNT DETAILS");
        l1 = new JLabel("USER DETAILS");
        l1.setForeground(Color.white);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l1.setBounds(300, 70, 400, 30);   add(l1);
        l9 = new JLabel("Account Number:");l9.setBounds(80,120,400,30);add(l9);
        l2 = new JLabel("surname:");l2.setBounds(80,160,400,30);add(l2);
        l3 = new JLabel("other names:");l3.setBounds(80,200,400,30);add(l3);
        l4 = new JLabel("National ID:");l4.setBounds(80,240,400,30);add(l4);
        l5 = new JLabel("Mobile Number:");l5.setBounds(80,280,400,30);add(l5);
        l6 = new JLabel("Email:");l6.setBounds(80,320,400,30);add(l6);
        l7 = new JLabel("balance:");l7.setBounds(80,360,400,30);add(l7);

         //textfields
        accountNumber= new JTextField();accountNumber.setBounds(300, 120, 200, 30);add(accountNumber);
        accountNumber.setEditable(false);
        surname= new JTextField();surname.setBounds(300, 160, 200, 30);add(surname);        
        otherNames= new JTextField();otherNames.setBounds(300, 200, 200, 30);add(otherNames);       
        nationalId = new JTextField();nationalId.setBounds(300, 240, 200, 30);add(nationalId);       
        mobileNumber = new JTextField();mobileNumber.setBounds(300, 280, 200, 30);add(mobileNumber);        
        email= new JTextField();email.setBounds(300, 320, 200, 30);add(email);   
        balance= new JTextField();balance.setBounds(300, 360, 200, 30);add(balance); 
        balance.setEditable(false);balance.setText(bal);
               //buttons
        submit = new JButton("Update Details");submit.setBounds(0,510,440,40);add(submit);
        back = new JButton("back");back.setBounds(0,560,440,40);add(back);
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
                    int mobile = Integer.parseInt(mobileNumber.getText());
                int NID = Integer.parseInt(nationalId.getText());
                          try{
                             stmt.executeUpdate("UPDATE userTable SET mobile= '"+mobile+"', surname= '"+surname.getText()+"', nationalId= '"+NID+"',otherName= '"+otherNames.getText()+"', email= '"+email.getText()+"'  WHERE accountNumber= '" +LoginSession.UID+"' ");                            
                             JOptionPane.showMessageDialog(accountDetails.this, "User details updated succesfully!");
                        }
                        catch(SQLException ex){
                            JOptionPane.showMessageDialog(accountDetails.this, ex.getMessage());
                        }	
                        catch(NumberFormatException ev){
                                JOptionPane.showMessageDialog(accountDetails.this, "Check that the mobile Number and nationalId are numbers.");
                                System.out.println(ev.getMessage());
			}
                }
                
			
		}
    
}
