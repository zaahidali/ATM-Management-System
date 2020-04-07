package atm;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class AtmMainDriver 
{
    public static void main(String[] args) 
    {
        LoginForm f = new LoginForm ();        
        f.setVisible(true);
        f.setSize(540,350);
        f.setLocation(450,220);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
}
