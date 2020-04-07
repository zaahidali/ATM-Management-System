package atm;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class LoginForm extends JFrame implements ActionListener
{
    

    JPanel panel;
    JLabel lab1,lab2,picLab,userLab,adminLab;
    JCheckBox box;
    JButton btn1,btn2;
    JTextField Tfield;
    JPasswordField Pfield;
    String s1,s2,s3,s4;
    String password;
    char pa[];
    ImageIcon img;
    JRadioButton user,admin;

    
    LoginForm()
    {  
        super("Login Forum");
        this.setLocationRelativeTo(null);
       
        panel = new JPanel();
        
        lab1 = new JLabel("User Name :");
        lab2 = new JLabel("Password :");
        userLab = new JLabel("User");
        adminLab = new JLabel("Admin");
       
        box = new JCheckBox();
        btn1 = new JButton("Login");
        btn2 = new JButton("Reset");
        img = new ImageIcon("ims.jpg");
        picLab = new JLabel(img);
        
        /************************************* Radio Button **************************/
        user = new JRadioButton();
        admin = new JRadioButton();
        /*****************************************************************************/
        
        Tfield = new JTextField();
        Tfield.setColumns(5);
        Pfield = new JPasswordField();
        Pfield.setColumns(6);
        
        
/************************************************Add Elements to Panel*******************************************************************/        
        this.add(panel);
        panel.add(picLab);        
        panel.add(lab1);
        panel.add(Tfield);
        panel.add(lab2);
        panel.add(Pfield);        
        panel.add(box);         
        panel.add(btn1);
        panel.add(btn2);      
        panel.add(user);
        panel.add(userLab);
        panel.add(admin);
        panel.add(adminLab); 
        
 /********************************************************************************************************************************/
               
        ButtonGroup group = new ButtonGroup();
        group.add(user);
        group.add(admin);
        
        
 /*************************************** Frame Setting ***********************************************************************/        
        this.setVisible(true);
        this.setSize(590,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
 /********************************************************************************************************************************/

 
 /**********************  For Color & Border ************************************************************************************/
        panel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
        panel.setBackground(Color.white);
        panel.setForeground(Color.gray);
/********************************************************************************************************************************/        

 /******************************** Registered JCheckbox and Buttons with ActionListener ******************************************/        
         box.addActionListener(this);
         btn1.addActionListener(this);
         btn2.addActionListener(this);
 /********************************************************************************************************************************/

    }        
     /***************************** ActionListener Code For Buttons ***************************************************/
         
     public void actionPerformed(ActionEvent ae)
 {
                if(ae.getSource()==btn1)
              {                 
                FileReader fr = null;
                BufferedReader br = null;
           try
            {
                fr = new FileReader("loginInfo.txt");
                br = new BufferedReader(fr);
	
                s1 = br.readLine();
                s4 = br.readLine();     // for Password
       
                char [] p = Pfield.getPassword();
                 password = new String(p);                
                String text = Tfield.getText();
                
                
                if(text.isEmpty() && password.isEmpty()) 
             { 
                 JOptionPane.showMessageDialog(null,"ENTER YOUR USER NAME and Password"); 
             } 
             else if(text.isEmpty() ) 
             { 
                 JOptionPane.showMessageDialog(null,"ENTER YOUR USER NAME "); 
             }
             
             else if( password.isEmpty()) 
             { 
                 JOptionPane.showMessageDialog(null,"ENTER YOUR Password"); 
             }
                  else if(!Tfield.getText().equals(s1) && !password.equals(s4))
                        { 
                JOptionPane.showMessageDialog(null,"WRONG USERNAME AND PASSWORD"); 
                        }
                  else if (!user.isSelected() && !admin.isSelected())
                        { 
                JOptionPane.showMessageDialog(null,"Please Select on Option admin or user"); 
                        }
                
                  else if (Tfield.getText().equals(s1) && password.equals(s4) )
                  {
                      JOptionPane.showMessageDialog(null, "Login Successfully");
                     if(user.isSelected())   
                     {
/****************************************** USER ****************************************************************************/                         
                     AfterLogin  t = new AfterLogin ();
                     t.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                     t.setSize(460,400);
                     t.setVisible(true);
                     t.setLocationRelativeTo(null);
                     }
                     else if(admin.isSelected())
                  {

/*****************************************  Admin ***************************************************************************/
                     Admin  t2 = new Admin ();
                     t2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                     t2.setSize(540,350);
                     t2.setVisible(true);
                     t2.setLocationRelativeTo(null);
/****************************************************************************************************************************/
                  }
                    }
        
                     fr.close();
                     br.close();
            }
                 catch(IOException i)
                {
                    i.printStackTrace();
                }
                  
                }          
              else if(ae.getSource()==btn2)
              {
                Tfield.setText("");
                Pfield.setText("");
              }
            if (box.isSelected()) 
            {
            Pfield.setEchoChar((char) 0);
           
            }   
            else
            {
            Pfield.setEchoChar('*');
            }     
                
 }
     
     public static void main(String args[]) 
     {  
             
                new LoginForm().setVisible(true); 
            
        
      }
     
}
    

