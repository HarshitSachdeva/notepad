
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;


public class Notepad implements ActionListener {
    
    Font f=new Font("Arial",Font.PLAIN,20);
    
    JTextArea ta=new JTextArea();
    Scanner s;
    JFileChooser jfc;
    JScrollPane sp=new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JFrame jf;
    JMenuBar jmb=new JMenuBar();
    JMenu fileMenu=new JMenu("File");
    JMenu EditMenu=new JMenu("Edit");
    JMenu formatMenu=new JMenu("Format");
    JMenu viewMenu=new JMenu("View");
    JMenu helpMenu=new JMenu("Help");
    
    JMenuItem fNew=new JMenuItem("New");
    JMenuItem fopen;
    JMenuItem fsave=new JMenuItem("Save");
    JMenuItem fsaveas=new JMenuItem("Save As");
    JMenuItem fpage =new JMenuItem("Page Setup");
    JMenuItem fprint=new JMenuItem("Print");
    JMenuItem fexit=new JMenuItem("Exit");
    JMenuItem eundo=new JMenuItem("Undo");
    JMenuItem ecut=new JMenuItem("Cut");
    JMenuItem ecopy=new JMenuItem("Copy");
    JMenuItem epaste=new JMenuItem("Paste");
    JMenuItem edel=new JMenuItem("Delete");
    JMenuItem efind=new JMenuItem("Find");
    JMenuItem efindnext=new JMenuItem("Find Next");
    JMenuItem ereplace=new JMenuItem("Replace");
    JMenuItem egoto=new JMenuItem("Go to");
    JMenuItem eselectall=new JMenuItem("Select All");
    JMenuItem edate=new JMenuItem("Date/time");
    
    
    JMenuItem forWordWrap=new JMenuItem("Wrap Word");
    JMenuItem forFont=new JMenuItem("Font");
    
    JMenuItem view=new JMenuItem("View");
    
    JMenuItem hview=new JMenuItem("View Help");
    JMenuItem habout=new JMenuItem("About Notepad");
    
    
    Notepad(){
        jf=new JFrame();
        s=new Scanner(System.in);
        jfc=new JFileChooser();
        fopen=new JMenuItem("Open");
    
        fNew.addActionListener(this);
        fexit.addActionListener(this);
        fopen.addActionListener(this);
        fsave.addActionListener(this);
        fprint.addActionListener(this);
        ecut.addActionListener(this);
        ecopy.addActionListener(this);
        epaste.addActionListener(this);
        habout.addActionListener(this);
        
        fileMenu.add(fNew);
        fileMenu.add(fopen);
        fileMenu.add(fsave);
        fileMenu.add(fsaveas);
        fileMenu.add(fpage);
        fileMenu.add(fprint);
        fileMenu.add(fexit);
        
        EditMenu.add(eundo);
        EditMenu.add(ecut);
        EditMenu.add(ecopy);
        EditMenu.add(epaste);
        EditMenu.add(edel);
        EditMenu.add(efind);
        EditMenu.add(efindnext);
        EditMenu.add(ereplace);
        EditMenu.add(egoto);
        EditMenu.add(eselectall);
        EditMenu.add(edate);
        
        formatMenu.add(forWordWrap);
        formatMenu.add(forFont);
        
        viewMenu.add(view);
        
        helpMenu.add(hview);
        helpMenu.add(habout);
        
        
        //fileMenu.setMnemonic(KeyEvent.VK_F);
        
        
        KeyStroke open=KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK);
        fopen.setAccelerator(open);
        
        KeyStroke sav=KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK);
        fsave.setAccelerator(sav);
        
         KeyStroke ne=KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK);
        fNew.setAccelerator(ne);
        
        KeyStroke clo=KeyStroke.getKeyStroke(KeyEvent.VK_W,KeyEvent.CTRL_DOWN_MASK);
        fexit.setAccelerator(clo);
        
        KeyStroke prnt=KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK);
        fprint.setAccelerator(prnt);
        
        KeyStroke cu=KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK);
        ecut.setAccelerator(cu);
        
        KeyStroke cpy=KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK);
        ecopy.setAccelerator(cpy);
        
        KeyStroke pst=KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK);
        epaste.setAccelerator(pst);
        
        KeyStroke ab=KeyStroke.getKeyStroke(KeyEvent.VK_I,KeyEvent.CTRL_DOWN_MASK);
        habout.setAccelerator(ab);
        
        
        
        
        
        jmb.add(fileMenu);
        jmb.add(EditMenu);
        jmb.add(formatMenu);
        jmb.add(viewMenu);
        jmb.add(helpMenu);
        
        
        jf.setJMenuBar(jmb);
        jf.add(sp);
        
        sp.setSize(795,495);
        ta.setFont(f);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        
        
        jf.setTitle("Notepad");
        jf.setSize(800,500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        
      
        jf.addWindowListener(new WindowAdapter()
        {
            
            public void windowClosing(WindowEvent we) {
                int n;
                n=JOptionPane.showOptionDialog(null,"Do you Want to save your changes","Notepad",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                if(n==JOptionPane.NO_OPTION)
                    System.exit(0);
                else if(n==JOptionPane.CLOSED_OPTION)
                    jf.setVisible(true);
                else if(n==JOptionPane.YES_OPTION) {
                    JFrame jf=new JFrame();
                    int p=jfc.showSaveDialog(jf);
                    if(p==JFileChooser.APPROVE_OPTION) {
                        try (FileWriter f1=new FileWriter(jfc.getSelectedFile()))
                        {
                            BufferedWriter br=new BufferedWriter(f1);
                            Scanner s=new Scanner(ta.getText());
                            while(s.hasNext()) {
                                br.write(s.nextLine());
                                br.newLine();
                    
                            }
                        br.close();
                        }
                        catch(IOException ae) {
                            System.out.println(ae);
                        }
                    }
           
                }
                System.exit(0);    
               
            }
            
        });
        
        
    }
       @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(fNew)){
            if(ta.getText().isEmpty())
                ta.setText("");
            else {      
                int n;
                n=JOptionPane.showOptionDialog(null,"Do you Want to save your changes","Notepad",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                if(n==JOptionPane.NO_OPTION){
                    jf.setVisible(true);
                    ta.setText("");
                }
                else if(n==JOptionPane.CLOSED_OPTION)
                    jf.setVisible(true);
                else if(n==JOptionPane.YES_OPTION) {
                    JFrame jf=new JFrame();
                    int p=jfc.showSaveDialog(jf);
                    if(p==JFileChooser.APPROVE_OPTION) {
                        try (FileWriter f1=new FileWriter(jfc.getSelectedFile()))
                        {
                            BufferedWriter br=new BufferedWriter(f1);
                            Scanner s=new Scanner(ta.getText());
                            while(s.hasNext()) {
                                br.write(s.nextLine());
                                br.newLine();
                    
                            }
                            br.close();
                            ta.setText("");
                
                        }
                        catch(IOException ae) {
                            System.out.println(ae);
                        }
                    }
           
                }  
               
            }        
        }
            
        
        if(e.getSource().equals(fexit)){
           System.exit(0);
            
        }
        if(e.getSource().equals(fopen)) {
            if(ta.getText().isEmpty()) {
                int n;
                n = jfc.showOpenDialog(fopen);
                if(n==JFileChooser.APPROVE_OPTION) {
                    try {
                        s=new Scanner(jfc.getSelectedFile());
                        while(s.hasNext()) {
                            String str=s.nextLine();
                            ta.setText(str+ta.getText()+"\n");
                        }

                    }
                    catch(FileNotFoundException ex) {
                        System.out.println(ex);
                    }
                }
            }
            else {
                int n;
                n=JOptionPane.showOptionDialog(null,"Do you Want to save your changes","Notepad",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                if(n==JOptionPane.NO_OPTION){
                    jf.setVisible(true);
                    ta.setText("");
                }
                else if(n==JOptionPane.CLOSED_OPTION)
                    jf.setVisible(true);
                else if(n==JOptionPane.YES_OPTION) {
                    JFrame jf=new JFrame();
                    int p=jfc.showSaveDialog(jf);
                    if(p==JFileChooser.APPROVE_OPTION) {
                    try (FileWriter f1=new FileWriter(jfc.getSelectedFile()))
                    {
                    BufferedWriter br=new BufferedWriter(f1);
                    Scanner s=new Scanner(ta.getText());
                    while(s.hasNext()) {
                        br.write(s.nextLine());
                        br.newLine();
                    
                    }
                    br.close();
                    ta.setText("");
                
                }
                catch(IOException ae) {
                    System.out.println(ae);
                }
                }
           
            }  
            int p;
            p = jfc.showOpenDialog(fopen);
            if(p==JFileChooser.APPROVE_OPTION) {
                try {
                   s=new Scanner(jfc.getSelectedFile());
                    while(s.hasNext()) {
                        String str=s.nextLine();
                        ta.setText(str+ta.getText()+"\n");   
                    }
                    
                }
                catch(FileNotFoundException ex) {
                    System.out.println(ex);
                }
            }
                
                
        
        }
             
                
    }
            
        if(e.getSource().equals(fsave)){
           
            int p=jfc.showSaveDialog(jf);
            if(p==JOptionPane.NO_OPTION){
                 jf.setVisible(true);
                 
            }
            if(p==JFileChooser.APPROVE_OPTION) {
                try (FileWriter f1=new FileWriter(jfc.getSelectedFile()))
                {
                    BufferedWriter br=new BufferedWriter(f1);
                    s=new Scanner(ta.getText());
                    while(s.hasNext()) {
                        br.write(s.nextLine());
                        br.newLine();

                    }
                    br.close();
                }
                catch(IOException ae) {
                        System.out.println(ae);
                }
            }

        }

        if(e.getSource().equals(fprint)) {
            try {
                ta.print();
            } 
            catch (PrinterException ex) {
              System.out.println(ex);
            }
        }
        if(e.getSource().equals(ecut)) {
            ta.cut();
        }
        if(e.getSource().equals(ecopy)) {
            ta.copy();   
        }
        if(e.getSource().equals(epaste)) {
            ta.paste();
        }
        }
         
    public static void main(String[] args) {
        Notepad n=new Notepad();
    }           
}

    
  

    
