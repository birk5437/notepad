import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Notepad extends JFrame implements ActionListener
{

   String doActionString = new String();
   boolean okPressed = false;
   String str;
   JTextArea textArea = new JTextArea();
   FileWriter writer;
   FileReader reader;
   JFileChooser chooser = new JFileChooser();
   JFrame confirmFrame = new JFrame("Warning");
   JFrame fontFrame = new JFrame("Font");

   public Notepad()
   {
   
   
	
      JPanel confirmPanel = new JPanel();
      confirmPanel.setLayout(new GridLayout(2,1));
      JPanel confirmPanel2 = new JPanel();
      JPanel confirmButtonPanel = new JPanel();
      JButton ok = new JButton("OK");
      confirmPanel2.add(new JLabel("WARNING: All unsaved data will be lost!"));
      confirmPanel.add(confirmPanel2);
      confirmButtonPanel.add(ok);
      JButton cancel = new JButton("CANCEL");
      confirmButtonPanel.add(cancel);
      ok.addActionListener(this);
      cancel.addActionListener(this);
      confirmPanel.add(confirmButtonPanel);
      confirmFrame.getContentPane().add(confirmPanel);
      
      confirmFrame.setSize(350,100);
      confirmFrame.setLocationRelativeTo(new Container());

      JScrollPane scrollPane = new JScrollPane(textArea);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      JMenuBar menuBar = new JMenuBar();
      JMenu file = new JMenu("File");
	JMenu format = new JMenu("Format");
	format.add(new JMenuItem("Font"));
      JMenuItem createNew = new JMenuItem("New");
      createNew.addActionListener(this);
      JMenu edit = new JMenu("Edit");
      JMenuItem open = new JMenuItem("Open");
	open.addActionListener(this);
      JMenuItem save = new JMenuItem("Save");
	save.addActionListener(this);
      JMenuItem menuExit = new JMenuItem("Exit");
      menuExit.addActionListener(this);      

      JPanel main = new JPanel();
	main.setLayout(new BorderLayout());
      main.add(scrollPane, BorderLayout.CENTER);
      file.add(createNew);
      file.add(open);
      file.add(save);
      file.add(menuExit);
      menuBar.add(file);
      this.setJMenuBar(menuBar);
      getContentPane().add(main);
   }//Notepad

   public void actionPerformed(ActionEvent e)
   {
      if(e.getActionCommand() == "Exit")
         System.exit(0);
      else if(e.getActionCommand() == "New")
	{
	   doActionString = "new";
         confirmFrame.setVisible(true);
	}//else if
	else if (e.getActionCommand() == "Open")
	{
	   confirmFrame.setVisible(true);
	   doActionString = "open";	   
      }//else if		

	else if(e.getActionCommand() == "Save")
	{
	  
	   try
	   {
		chooser.showSaveDialog(this);
            writer = new FileWriter(chooser.getSelectedFile());
		System.out.println(textArea.getText());
		writer.write(textArea.getText(), 0, textArea.getText().length());
		writer.close();
		okPressed = false;
	   }//try

	   catch(IOException ex)
	   {
		JOptionPane.showMessageDialog(this, "File I/O error");
		okPressed = false;
	   }//catch
      }//else if

	else if (e.getActionCommand() == "OK")
	{   
	   textArea.setText("");
	   confirmFrame.setVisible(false);
	   doAction();

	}//else if

      else
         confirmFrame.setVisible(false);
	   okPressed = false;
   }



   public void doAction()
   {
	if (doActionString == "open")	
	{
            try
	      {
               int strLength = 0;
	         chooser.showOpenDialog(this);
	         reader = new FileReader(chooser.getSelectedFile());

	         while(reader.read() != -1)
	         {
	            strLength++;
	         }//while

	         char ch[];
	         ch = new char[strLength];
	         FileReader reader2 = new FileReader(chooser.getSelectedFile());
	         reader2.read(ch);
	         str = new String(ch);
	         textArea.setText(str);
	         okPressed = false;
            }//try

	      catch(IOException ioe)
            {
	         okPressed = false;
	         JOptionPane.showMessageDialog(this, "File cannot be opened");
            }//catch
	   }//if
   }//doAction


   public static void main(String args[])
   {   
	JFrame frame = new Notepad();
      frame.setSize(425,410);
	frame.setTitle("Notepad");
	frame.setLocationRelativeTo(new Container());
      frame.setVisible(true);
  }//main
}//Notepad
      