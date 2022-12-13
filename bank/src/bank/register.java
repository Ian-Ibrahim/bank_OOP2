package bank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.util.logging.Logger;


 
public class register extends JFrame implements ActionListener 
  { 
    JLabel l1, l2, l3,l4,l5,l6,l7,l8,l9;
    JTextField accountNumber,surname,otherNames,nationalId,mobileNumber,email;
    JPasswordField password,password2;
    JButton submit, cancel;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    
    
    public void DoConnect(){
        try{
//STEP 1: Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");//JDBC driver name
            
            //Database Credentials
            String host = "jdbc:mysql://localhost/bank_task1"; //Database URL
            String uName = "root";
            String uPass = "";

           conn = DriverManager.getConnection(host,uName,uPass);

            
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            
           
        }
        catch (SQLException | ClassNotFoundException err){
            System.out.println("error");
        }
    }
     public   register()
     {
        setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("registration");
 
        
	submit = new JButton("Register");
        cancel = new JButton("Cancel");

 //labels
        l1 = new JLabel("REGISTER AN ACCOUNT");
        l1.setForeground(Color.black);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l1.setBounds(300, 70, 400, 30);   add(l1);
        l9 = new JLabel("Account Number:");l9.setBounds(80,120,400,30);add(l9);
        l2 = new JLabel("surname:");l2.setBounds(80,160,400,30);add(l2);
        l3 = new JLabel("other names:");l3.setBounds(80,200,400,30);add(l3);
        l4 = new JLabel("National ID:");l4.setBounds(80,240,400,30);add(l4);
        l5 = new JLabel("Mobile Number:");l5.setBounds(80,280,400,30);add(l5);
        l6 = new JLabel("Email:");l6.setBounds(80,320,400,30);add(l6);
        l7 = new JLabel("Password:");l7.setBounds(80,360,400,30);add(l7);
        l8 = new JLabel("Re-Enter Password:");l8.setBounds(80,400,400,30);add(l8);
        
         
  //textfields
        accountNumber= new JTextField();accountNumber.setBounds(300, 120, 200, 30);add(accountNumber);
        surname= new JTextField();surname.setBounds(300, 160, 200, 30);add(surname);        
        otherNames= new JTextField();otherNames.setBounds(300, 200, 200, 30);add(otherNames);       
        nationalId = new JTextField();nationalId.setBounds(300, 240, 200, 30);add(nationalId);       
        mobileNumber = new JTextField();mobileNumber.setBounds(300, 280, 200, 30);add(mobileNumber);        
        email= new JTextField();email.setBounds(300, 320, 200, 30);add(email);    
        password= new JPasswordField();password.setBounds(300, 360, 200, 30);add(password);        
        password2= new JPasswordField();password2.setBounds(300, 400, 200, 30);add(password2);   
        //buttons
        submit.setBounds(250, 480, 100, 30);add(submit);
        cancel.setBounds(370, 480, 100, 30);add(cancel);

        
        
        //actions
        submit.addActionListener(this);
       cancel.addActionListener(this);
        DoConnect();
        

    }
     
     @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
                     

		if(e.getSource()==submit){
                     String pass = String.valueOf(password.getPassword());
                     String re_pass = String.valueOf(password2.getPassword());
                     int natId = Integer.parseInt(nationalId.getText());
                     int phoneNo = Integer.parseInt(mobileNumber.getText());
                     int accNo = Integer.parseInt(accountNumber.getText());
                     if(pass.equals(re_pass)){ 
			try{
                           stmt.executeUpdate("INSERT INTO userTable ( accountNumber, password,surname, otherName, nationalId, mobile, email,balance) VALUES ('"+accNo+"','"+password.getText()+"','"+surname.getText()+"','"+otherNames.getText()+"','"+natId+"','"+phoneNo+"','"+email.getText()+"', 100 )");
                           JOptionPane.showMessageDialog(register.this, "Record saved!");
                           Login a = new Login();
		           a.setVisible(true);
		           this.setVisible(false);
                           submit.setEnabled(false);
                        }
                        catch(SQLException ex){
                            JOptionPane.showMessageDialog(register.this, ex.getMessage());
                        }	
                        catch(NumberFormatException ev){
                                JOptionPane.showMessageDialog(register.this, "Check that the national ID number and phone number are numbers only.");
                                System.out.println(ev.getMessage());
                        }
                      }else{
                       JOptionPane.showMessageDialog(register.this, "The passwords need to match!");
                        }
                }
              
               if(e.getSource()==cancel){
				
				startPage a = new startPage();
				a.setVisible(true);
				this.setVisible(false);
			}
			
			
		}
  }



  
