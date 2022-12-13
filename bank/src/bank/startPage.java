package bank;

import javax.swing.*;
import java.awt.event.ActionEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
//import java.awt.*;
//import java.sql.*;
import java.awt.event.ActionListener;

 
public class startPage extends JFrame implements ActionListener 
  { 
    JLabel l1, l2, l3;
    JTextField tf1;
    JButton btn1, btn2;

    
   
 
 public   startPage()
     {
        setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("homepage");
        l1 = new JLabel("WELCOME" );
        l1.setForeground(Color.white);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
	btn1 = new JButton("Register");
        btn2 = new JButton("Login");

 
        //btn1.addActionListener(this);
        //btn2.addActionListener(this);
 
        l1.setBounds(300, 110, 400, 30);


        btn1.setBounds(250, 270, 100, 30);
        btn2.setBounds(370, 270, 100, 30);
        add(l1);
        add(btn1);
        add(btn2);

        
        //actions
       btn2.addActionListener(this);
       btn1.addActionListener(this);
        
        

    }
 
     public static void main(String args[])
   {
        new startPage();
        
    }



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			if(e.getSource()==btn2){
				
				Login a = new Login();
				a.setVisible(true);
				this.setVisible(false);
			}
                        if(e.getSource()==btn1){
                            register b = new register();
                            b.setVisible(true);
                            this.setVisible(false);
                        }
		}



  }



  
