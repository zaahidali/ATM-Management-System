package atm;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

/*\ I have to add these options
    Add Account
    Delete Account
    Edit Account
    Save to File
    Exit
    
*/
public class Admin extends JFrame implements ActionListener
{
    
    
     
    JButton addAccount,deleteAccount,editAccount,saveToFile,logOut;  
    JLabel atmLab;
    Container con;
    ArrayList customerlist;
    String s1,s2,s3;
    Admin()
    {
        super("ADMIN");
        customerlist=new ArrayList();       // array list
		
        con = this.getContentPane();
        con.setLayout(null);
        con.setBackground(Color.black);
       
        atmLab = new JLabel(new ImageIcon("admin2.png"));
        atmLab.setBounds(10,10,500,100);
        
        addAccount = new JButton("Add Account");
        addAccount.setBounds(20,120,150,30);
               
        deleteAccount = new JButton("Delete Account");
        deleteAccount.setBounds(350,120,150,30);
        
        editAccount = new JButton("Edit Account");
        editAccount.setBounds(20,200,150,30);
        
        saveToFile = new JButton("Save to File");
        saveToFile.setBounds(350,200,150,30);
        
        logOut = new JButton("Logout");
        logOut.setBounds(190,250,150,30);

       
       con.add(atmLab);
       con.add(addAccount);
       con.add(deleteAccount);
       con.add(editAccount);
       con.add(saveToFile);
       con.add(logOut);
       
    /****************************     Registered Buttons With ActionListener      ******************************/
    
    addAccount.addActionListener(this);
    deleteAccount.addActionListener(this);
    editAccount.addActionListener(this);
    saveToFile.addActionListener(this);
    logOut.addActionListener(this);
    
    
    }
    
    /*******************************ADD ACCOUNT************************************************/
    public void addPersons()
	{
		String pincode=JOptionPane.showInputDialog(null,"please enter PINCODE NO","PINCODE ENTRY",JOptionPane.QUESTION_MESSAGE);
		for(int i=0;i<customerlist.size();i++)
		{
			AccountData atm=(AccountData)customerlist.get(i);
			if(pincode.equals(atm.pincode))
			{
				pincode=JOptionPane.showInputDialog(null,"SORRY!This pincode is already in used for one customer\nEnter another pincode","PINCODE ENTRY",JOptionPane.QUESTION_MESSAGE);	
			}
		}
		String customername=JOptionPane.showInputDialog(null,"Please Enter Customer Name","CUSTOMER NAME",JOptionPane.QUESTION_MESSAGE);
		String accounttype=JOptionPane.showInputDialog(null,"Please Enter Account Type\n(Saving or Current)","ACCOUNT TYPE ENTRY FOR CUSTOMER",JOptionPane.QUESTION_MESSAGE);
		String accountnumber=JOptionPane.showInputDialog(null,"Enter Account Number","ACCOUNT NUMBER ENTRY OF CUSTOMER",JOptionPane.QUESTION_MESSAGE);
		String startbalance=JOptionPane.showInputDialog(null,"Enter Starting Balance","STARTING BALANCE ENTRY OF CUSTOMER",JOptionPane.QUESTION_MESSAGE);

		AccountData atm=new AccountData(pincode,customername,accounttype,accountnumber,startbalance);
		customerlist.add(atm);
	}
    
    /**************************************SAVE ACCOUNT*********************************************************/
    
    public void savePerson()
	{
		try
		{
			AccountData atm;
			String line,line1;

			FileWriter fr=new FileWriter("Customer Record.txt");
			PrintWriter pw=new PrintWriter(fr);

			FileWriter fr1=new FileWriter("Customers Record.txt");
			PrintWriter pw1=new PrintWriter(fr1);

			pw1.print("PINCODE\t\t\tCUSTOMER NAME\t\t      ACCOUNT TYPE\t\tACCOUNT NUMBER\t\tSTARTING BALANCE\n");	
			for (int i=0;i<customerlist.size(); i++)
			{
				atm=(AccountData)customerlist.get(i);
				line=atm.pincode+","+atm.customername+","+atm.accounttype+","+atm.accountnumber+","+atm.startbalance+"\n";		
				line1=atm.pincode+"\t\t\t"+atm.customername+"\t\t\t"+atm.accounttype+"\t\t\t"+atm.accountnumber+"\t\t\t"+atm.startbalance;
				pw1.println(line1);
				pw.print(line);
			}
			pw.flush();
			pw1.flush();
			pw.close();
			pw1.close();
			fr.close();
			fr1.close();
		}
		catch(IOException ioEX)
		{
			System.out.println(ioEX);
		}
		
	}
/********************************************DELETE ACCOUNT*******************************************************/
    
    public void delete(String n)
	{
		int aa;
		
		for(int i=0;i<customerlist.size();i++)
		{
			AccountData atm=(AccountData)customerlist.get(i);
			if(n.equals(atm.pincode))
			{
				aa=JOptionPane.showConfirmDialog(null,"Do you really want to delete The Following Record"+"\n\nPINCODE : "+atm.pincode+"\nCustomer name : "+atm.customername+"\nAccount Type : "+atm.accounttype+
				"\nAccount Number : "+atm.accountnumber+"\nTotal Balance : "+atm.startbalance,"CONFIRMATION ABOUT DELETION",JOptionPane.YES_NO_OPTION);
				if(aa==JOptionPane.YES_OPTION)
				{
					customerlist.remove(i);
				}
				else
				{
					break;
				}	
			}
			
		}
		
	}
    
    /****************************************EDIT ACCOUNT****************************************************/
   

	public void edit(String n)
	{
		String aa;
		int ch;
		for(int i=0;i<customerlist.size();i++)
		{
	int bb;
	AccountData atm=(AccountData)customerlist.get(i);
	if(n.equals(atm.pincode))
            {
	bb=JOptionPane.showConfirmDialog(null,"Do You Want To Edit The Following Record"+"\n\nPINCODE : "+atm.pincode+"\nCustomer name : "+atm.customername+"\nAccount Type : "+atm.accounttype+
	"\nAccount Number : "+atm.accountnumber+"\nTotal Balance : "+atm.startbalance,"CONFIRMATION BOX",JOptionPane.YES_NO_OPTION);
	if(bb==JOptionPane.YES_OPTION)
            {
	aa=JOptionPane.showInputDialog(null,"Enter 1 To Edit PinCode\nEnter 2 To Edit Customer Name \nEnter 3 To Edit Account Type\nEnter 4 Account Number\nEnter 5 To Edit Starting Balance\n\n ","EDITING MENU",JOptionPane.INFORMATION_MESSAGE);
	ch=Integer.parseInt(aa);										
	switch(ch)
	{
	case 1:
	atm.pincode=JOptionPane.showInputDialog(null,"Enter new PinCode to Replace old one","EDIT PINCODE",JOptionPane.QUESTION_MESSAGE);
	savePerson();
	break;

	case 2:
	atm.customername=JOptionPane.showInputDialog(null,"Enter New Customer Name to Replace Old One","EDIT CUSTOMER NAME",JOptionPane.QUESTION_MESSAGE);
	savePerson();
	break;
	
	case 3:
	atm.accounttype=JOptionPane.showInputDialog(null,"Enter Account Type\n(Saving or Current)","EDIT ACCOUNT TYPE",JOptionPane.QUESTION_MESSAGE);
	savePerson();
	break;

	case 4:
	atm.accountnumber=JOptionPane.showInputDialog(null,"Enter Account Number","ACCOUNT NUMBER",JOptionPane.QUESTION_MESSAGE);
	savePerson();
	break;
        
	case 5:
	atm.startbalance=JOptionPane.showInputDialog(null,"Enter new Starting Balance to Replace the Old One","EDIT STARTING BALANCE",JOptionPane.QUESTION_MESSAGE);
	savePerson();
	break;
        
	default:
	JOptionPane.showMessageDialog(null,"Oh No! You have entered the Wrong Pin Code \nPlease Enter Valid Pincode","SORRY",JOptionPane.WARNING_MESSAGE);
		}
			
		}
        else
	{
		break;
	}
	}
				
		}
	}

    /************************************************************************************************************/
    
    /***************************************************************************************************/
    public void actionPerformed(ActionEvent e)
    {

    
        JButton b = (JButton)e.getSource();
        
        
        if(b==addAccount)
        {
            
            addPersons();
        }
        if(b==deleteAccount)
        {
            s1=JOptionPane.showInputDialog(null,"Enter PinCode To Delete Account","DELETION MENU",JOptionPane.QUESTION_MESSAGE);
            delete(s1);
        }
        if(b==editAccount)
        {
            s1=JOptionPane.showInputDialog(null,"Enter PinCode To Edit Account","EDITING MENU",JOptionPane.QUESTION_MESSAGE);
            edit(s1);
        }
        if(b==saveToFile)
        {
            savePerson();
        }       	
        
        if(b == logOut)
        {					
	int n=JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Exit",JOptionPane.YES_NO_OPTION);
		if(n==JOptionPane.YES_OPTION)
                {
		JOptionPane.showMessageDialog(null,"Good Bye","ATM",JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
		dispose();
                }

        }
    
    }  
   
}
