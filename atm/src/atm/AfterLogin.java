package atm;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
 
public class AfterLogin extends JFrame implements ActionListener
{
    
    JButton equiryBtn,withdrawBtn,logoutBtn,transferBtn;  
    JLabel atmLab;
    Container con;
    ArrayList customerlist;
    Admin adm = new Admin();
    String s1;
    
    AfterLogin()
    {
        
        super("Transaction");
        customerlist=new ArrayList();
        
        con = this.getContentPane();
        con.setLayout(null);
        con.setBackground(Color.BLACK);
       
        
        atmLab = new JLabel(new ImageIcon("atm.png"));
        atmLab.setBounds(60,10,300,100);
        
        equiryBtn = new JButton("Balance Enquiry");
        equiryBtn.setBounds(10,130,150,40);
        
        transferBtn = new JButton("Transfer Money");
        transferBtn.setBounds(260,130,150,40);
        
        withdrawBtn = new JButton("WithDraw Money");
        withdrawBtn.setBounds(260,230,150,40);
        
     
        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(10,230,150,40);
               
       con.add(atmLab);
       con.add(equiryBtn);
       con.add(withdrawBtn);
       con.add(transferBtn);
       con.add(logoutBtn);
    /********************************************************************/
    
    equiryBtn.addActionListener(this);
    transferBtn.addActionListener(this);
    withdrawBtn.addActionListener(this);
    logoutBtn.addActionListener(this);
   
    loadPersons();
    }
    /*****************************Load Data from File****************************************/ 
   
    	public void loadPersons()
	{
		String ss[]=null;
		String pincode,customername,accounttype,accountnumber,startbalance;
		
		try
		{
			FileReader fr=new FileReader("Customer Record.txt");
			BufferedReader br=new BufferedReader(fr);
			

			String line=br.readLine();	
			
			while(line != null)
			{
				ss=line.split(",");
				pincode=ss[0];
				customername=ss[1];
				accounttype=ss[2];
				accountnumber=ss[3];
				startbalance=ss[4];

				AccountData atm=new AccountData(pincode,customername,accounttype,accountnumber,startbalance);
				customerlist.add(atm);
				line=br.readLine();
			}
				br.close();
				fr.close();
		}
		catch(IOException ioEX)
		{
			System.out.println(ioEX);
		}
	}

/***************************************************************************************************************************/


/********************************************************* Balance Enquiry of Customer ************************************/        
	public void inquiry(String n)
	{
		for(int i=0;i<customerlist.size();i++)
		{
			AccountData atm=(AccountData)customerlist.get(i);
			if(n.equals(atm.pincode))
			{
				JOptionPane.showMessageDialog(null,"Welcome to your atm data Mr  ."+atm.customername+"\nYour Total Cash Is : "+atm.startbalance,"WELCOME WELCOME MR  "+atm.customername,JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
/***************************************************************************************************************************/


/****************************************************** Balance Transfer ***************************************************/
	public void transfer(String k)
	{
		String a,b,c;
		int d,e,f;


		for(int i=0;i<customerlist.size();i++)
		{
			AccountData atm=(AccountData)customerlist.get(i);
			if(k.equals(atm.pincode));
			{
				a=atm.startbalance;
				d=Integer.parseInt(a);

				c=JOptionPane.showInputDialog(null,"Enter The Account Number To whom you Transfer Amount","MONEY TRANSACTION MENU",JOptionPane.QUESTION_MESSAGE);
				b=JOptionPane.showInputDialog(null,"Enter The Amount To Transfer","MONEYTRANSACTION MENU",JOptionPane.QUESTION_MESSAGE);
				e=Integer.parseInt(b);

				f=d-e;
				while(f < 0)
				{
					a=atm.startbalance;
					d=Integer.parseInt(a);

					b=JOptionPane.showInputDialog(null,"Invalid Amount\nPlease Enter The Suffecient Amount To Transfer","MONEYTRANSACTION MENU",JOptionPane.WARNING_MESSAGE);
					e=Integer.parseInt(b);
					f=d-e;
				}
				String u=String.valueOf(f);
				atm.startbalance=u;
				
				JOptionPane.showMessageDialog(null,"Transaction is done succesfully\n\nAmount of "+b+"is transferd To "+c+"\n\nYour Total Cash Is : "+atm.startbalance,"MONEY TRANSACTION PROCESSED",JOptionPane.INFORMATION_MESSAGE);
				
                                Admin as = new Admin();
				as.savePerson();
			}	
		}
	}

/**********************************************************************************************************************************/


/********************************************************* WithDraw Balance ******************************************************/        
	public void withdraw(String o)
	{
		String a,b,c;
		int d,e,f;

		for(int i=0;i<customerlist.size();i++)
		{
			AccountData atm=(AccountData)customerlist.get(i);
			if(o.equals(atm.pincode))
			{
				a=atm.startbalance;
				d=Integer.parseInt(a);

				b=JOptionPane.showInputDialog(null,"Enter The Amout To Withdarw","WITHDARW MENU",JOptionPane.QUESTION_MESSAGE);
				e=Integer.parseInt(b);

				f=d-e;

				while(f <0)
				{
					a=atm.startbalance;
					d=Integer.parseInt(a);

					b=JOptionPane.showInputDialog(null,"Invalid Amount\nPlease Enter The Suffecient Amount To WithDraw","WITHDRAW MENU",JOptionPane.WARNING_MESSAGE);
					e=Integer.parseInt(b);

					f=d-e;
				}
				c=String.valueOf(f);
				atm.startbalance=c;
				JOptionPane.showMessageDialog(null,"Withdarw proccesed\nYou have Withdarwed Amount of"+b+"\nYour Total Cash Is now: "+atm.startbalance,"Information",JOptionPane.INFORMATION_MESSAGE);
				Admin ad = new Admin();
                                ad.savePerson();
			}
		}
	}
/********************************************************************************************************************************/



/*************************************** ActionListener Code for Buttons ***********************************************************/
    
    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton)e.getSource();
        
	if(b == equiryBtn)
        {		
            s1= JOptionPane.showInputDialog(null,"Enter PinCode To Check Account Balance ","Check Balance",JOptionPane.QUESTION_MESSAGE);
            
            
            		for(int i=0;i<customerlist.size();i++)
		{
			AccountData atm=(AccountData)customerlist.get(i);

			if(!s1.equals(atm.pincode))
			{
				JOptionPane.showMessageDialog(null,"You have entered Wrong Pincode \nPlease Enter Valid Pincode!!!!","Warning",JOptionPane.WARNING_MESSAGE);
					
			}
                        else if(s1.equals(atm.pincode))
			{
			
                            inquiry(s1);
                        }            
                }
        }
/******************************************************************************************************************************/
        
        if(b == withdrawBtn)
        {
          s1=JOptionPane.showInputDialog(null,"Enter PinCode To withDraw Balance ","Withdraw Balance",JOptionPane.QUESTION_MESSAGE);
           for(int i=0;i<customerlist.size();i++)
		{
			AccountData atm=(AccountData)customerlist.get(i);

                         if(s1.equals(atm.pincode))
			{
			
                            withdraw(s1);
                        }
                         
                         else if(!s1.equals(atm.pincode))
			{
				JOptionPane.showMessageDialog(null,"You have entered Wrong Pincode \nPlease Enter Valid Pincode!!!!","Warning",JOptionPane.WARNING_MESSAGE);
					
			}
                }
        }
/******************************************************************************************************************************/

        if(b == transferBtn)
        {
             s1=JOptionPane.showInputDialog(null,"Enter PinCode To Transfer Balance ","Share balance",JOptionPane.QUESTION_MESSAGE);
           	
             for(int i=0;i<customerlist.size();i++)
		{
			AccountData atm=(AccountData)customerlist.get(i);

			if(!s1.equals(atm.pincode))
			{
				JOptionPane.showMessageDialog(null,"You have entered Wrong Pincode \nPlease Enter Valid Pincode!!!!","Warning",JOptionPane.WARNING_MESSAGE);
					
			}
                        else if(s1.equals(atm.pincode))
			{
			
                            transfer(s1);
                        }            
                }        
        }
        
/******************************************************************************************************************************/
        
            if(b == logoutBtn)
        {					
	int n=JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Exit",JOptionPane.YES_NO_OPTION);
		if(n==JOptionPane.YES_OPTION)
                {
		JOptionPane.showMessageDialog(null,"Good Bye","ATM",JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
                }

        }
            
   /******************************************************************************************************************************/                             
    
    
    
    
    }  
   
}
