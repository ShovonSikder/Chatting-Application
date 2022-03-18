
package chatting.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame implements ActionListener{
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static Socket s;
    
    static DataInputStream din;
    static DataOutputStream dout;
    Boolean typing;
    Client(){
        //first panel
      p1=new JPanel();
      p1.setLayout(null);
      p1.setBackground(Color.DARK_GRAY);
      p1.setBounds(0,0,400,50);
      add(p1);
       //goback img 
      ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("chatting\\application\\icons\\3.png")) ;
      Image i2 = i1.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
      ImageIcon i3=new ImageIcon(i2);
      JLabel l1=new JLabel(i3);
      l1.setBounds(5,10,30,30);
      p1.add(l1);
      //colose
      l1.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent ae){
              System.exit(0);
          }
      });
       
      //DP image
      ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("chatting\\application\\icons\\2.png")) ;
      Image i5 = i4.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
      ImageIcon i6=new ImageIcon(i5); 
      JLabel l2=new JLabel(i6);
      l2.setBounds(40,10,30,30);
      p1.add(l2);
      
      //name
      JLabel l3=new JLabel("Nazmul Hasan");
      l3.setFont(new Font("TIMES NEW ROMAN",Font.BOLD, 12));
      l3.setBounds(80, 10, 100, 18);
      l3.setForeground(Color.WHITE);
      p1.add(l3);
      
      //active status
      JLabel l4=new JLabel("Active Now");
      l4.setFont(new Font("SAN_SERIF",Font.PLAIN, 10));
      l4.setBounds(80, 25, 100, 20);
      l4.setForeground(Color.WHITE);
      p1.add(l4);
      
      Timer t=new Timer(1,new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent ae){
              if(!typing)
                  l4.setText("Active Now"); 
          }
          
      });
      t.setInitialDelay(1000);
      
      //video icon
      ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("chatting\\application\\icons\\video.png")) ;
      Image i8 = i7.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
      ImageIcon i9=new ImageIcon(i8); 
      JLabel l5=new JLabel(i9);
      l5.setBounds(250,10,30,30);
      p1.add(l5);
      
      //phone icon
      ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("chatting\\application\\icons\\phone.png")) ;
      Image i11 = i10.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
      ImageIcon i12=new ImageIcon(i11); 
      JLabel l6=new JLabel(i12);
      l6.setBounds(300,10,30,30);
      p1.add(l6);
      
      //menu 3dot
      ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("chatting\\application\\icons\\3icon.png")) ;
      Image i14 = i13.getImage().getScaledInstance(13, 25,Image.SCALE_DEFAULT);
      ImageIcon i15=new ImageIcon(i14); 
      JLabel l7=new JLabel(i15);
      l7.setBounds(350,10,15,30);
      p1.add(l7);
      
      //textarea
      a1=new JTextArea();
      a1.setBounds(5,55,390,480);
      a1.setBackground(Color.yellow);
      a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
     
      a1.setLineWrap(true);
      a1.setWrapStyleWord(true);
      //a1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      a1.setEditable(false);
    
    add(a1);
   
      
     //textfield
     t1=new JTextField();
     t1.setBounds(5, 550, 290, 40);
     t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
     add(t1);
     
     t1.addKeyListener(new KeyAdapter(){
         @Override
         public void keyPressed(KeyEvent ke){
             l4.setText("typing...");
             t.stop();
             typing=true;
         }
         @Override
         public void keyReleased(KeyEvent ke){
             typing=false;
             if(!t.isRunning())
                 t.start();
         }
     });
     //sending button
     b1=new JButton("Send");
     b1.setBounds(300, 550, 90, 40);
     b1.setBackground(Color.DARK_GRAY);
     b1.setForeground(Color.WHITE);
     b1.setFont(new Font("SAN_SERIF",Font.BOLD,12));
     add(b1);
      
      b1.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent ae){
              try{  
              String out=t1.getText();
                if("".equals(out)) return;
                 if("".equals(a1.getText()))
                    a1.setText("\n\t\t\t"+out);
                else
                 a1.setText(a1.getText()+"\n\n\t\t\t"+out);
                 dout.writeUTF(out);
                t1.setText("");
              }catch(Exception e){
                  
              }
          }
      });
      //JFrame 
        getContentPane().setBackground(Color.blue);
        setLayout(null);
        setSize(400,600);
        setLocation(800,100);
        setUndecorated(true);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
      
        
    }
    
    public static void main(String[] args){
        new Client().setVisible(true);
        
        try{
           s=new Socket("127.0.0.2",18345);
           din=new DataInputStream(s.getInputStream());
           dout=new DataOutputStream(s.getOutputStream());
           while(true){
           String msgin=din.readUTF();
           if("".equals(a1.getText()))
                    a1.setText("\n"+msgin);
                else
                 a1.setText(a1.getText()+"\n\n"+msgin);
           }
        }catch(Exception e){
            
        }
    }

    
}
