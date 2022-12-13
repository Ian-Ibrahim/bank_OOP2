
package bank;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class homePage extends JFrame implements ActionListener{
     JButton accountDetails,deposit,withdraw;
    JLabel label1,operations,balanceLabel,welcome;
    JTextField userId,balanceValue,names;
     String user = Integer.toString(LoginSession.UID);
     String bal = Integer.toString(LoginSession.balance);
     Connection conn;
    Statement stmt;
    ResultSet rs;
    public homePage(){
        setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("HOME");
         javax.swing.border.Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0);
        //labels
        JLabel welcome= new JLabel("welcome");welcome.setBounds(0,0,400,30);add(welcome);
        JLabel label1= new JLabel("Acc N°");label1.setBounds(0,50,400,30);add(label1);
        JLabel balanceLabel= new JLabel("balance");balanceLabel.setBounds(0,100,400,30);add(balanceLabel);
        JLabel operations= new JLabel("Operations");operations.setBounds(0,150,400,30);add(operations);
        //textfields 
        names= new JTextField();names.setBounds(100, 0, 200, 30);add(names);names.setEditable(false);names.setBorder(border);
        names.setText(LoginSession.names);
        userId= new JTextField();userId.setBounds(100, 50, 200, 30);add(userId);userId.setEditable(false);userId.setBorder(border);
        userId.setText(user);
        balanceValue= new JTextField();balanceValue.setBounds(100, 100, 200, 60);add(balanceValue);balanceValue.setEditable(false);
         balanceValue.setForeground(Color.green); balanceValue.setBorder(border);
         balanceValue.setFont(new Font("Serif", Font.BOLD, 50));
         balanceValue.setText(bal);

        //buttons
        accountDetails = new JButton("Account Details");accountDetails.setBounds(0,200,150,40);add(accountDetails);
        deposit = new JButton("deposit");deposit.setBounds(200,200,150,40);add(deposit);
        withdraw = new JButton("withdraw");withdraw.setBounds(400,200,150,40);add(withdraw);
        //actionListeners
        accountDetails.addActionListener(this);
         withdraw.addActionListener(this);
         deposit.addActionListener(this);

    }
    @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			if(e.getSource()==accountDetails){
				
				accountDetails a = new accountDetails();
				a.setVisible(true);
				this.setVisible(false);
			}
                        if(e.getSource()==deposit){
				
				deposit a = new deposit();
				a.setVisible(true);
				this.setVisible(false);
			}
                        if(e.getSource()==withdraw){
				
				withdraw a = new withdraw();
				a.setVisible(true);
				this.setVisible(false);
			}
		}
    
}

