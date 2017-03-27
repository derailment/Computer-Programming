import java.awt.*;
import java.awt.event.*;
 
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
 
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import java.util.*;

public class Visualizer extends JFrame implements ActionListener{
    
	private JTextField name_text=new JTextField(30);
    private JTextField row_text=new JTextField(2);
    private JTextField col_text=new JTextField(2);  
    private JButton file_in_button = new JButton("Browse");
    private JButton run_button = new JButton("Run");
    private JButton reset_button = new JButton("Reset");
    private JFrame frame;
    private JPanel panel2;
    private JPanel panel3;
 
    private String filename;
    private int row,col,namesize;
    
    private ArrayList<String> namelist;
    private ArrayList<Integer> idlist;
    
    public static void main(String[] args) {
        Visualizer vis=new Visualizer();
    }
    
    public Visualizer() {
        
        // Frame setting
        frame=new JFrame();
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Seat Generator");
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        
        // Panel 1 setting
        JPanel panel1=new JPanel();
        panel1.setBackground(Color.PINK);
        panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // Textfield setting
        name_text.setEditable(false);
        row_text.setEditable(true);
        col_text.setEditable(true);
        
        row_text.setHorizontalAlignment(JTextField.CENTER);
        col_text.setHorizontalAlignment(JTextField.CENTER);
        
        row_text.addActionListener(this);
        col_text.addActionListener(this);
        
        // Button setting
        file_in_button.addActionListener(this); 
        run_button.addActionListener(this); 
        reset_button.addActionListener(this); 
        
        // Label setting
        JLabel name_label=new JLabel("Name Sheet(.csv): ");
        JLabel row_label=new JLabel("Row Number: ");
        JLabel col_label=new JLabel("Column Number: ");
        
        name_label.setHorizontalAlignment(JLabel.CENTER);
        row_label.setHorizontalAlignment(JLabel.CENTER);
        col_label.setHorizontalAlignment(JLabel.CENTER);
        
        // Add components to panel 1
        panel1.add(name_label);
        panel1.add(name_text);
        panel1.add(file_in_button);
        panel1.add(row_label);
        panel1.add(row_text);
        panel1.add(col_label);
        panel1.add(col_text);
        panel1.add(run_button);
        panel1.add(reset_button);
        
        // Add panel 1 to frame
        frame.add(panel1);
        
        // Label setting
        JLabel desk=new JLabel("Desk");
    	desk.setHorizontalAlignment(JLabel.CENTER);
    	desk.setFont(new java.awt.Font("DIALOG",1,20)); 
    	desk.setBorder(BorderFactory.createEtchedBorder());
    	desk.setOpaque(true);
    	desk.setBackground(Color.GRAY);
    	desk.setForeground(Color.WHITE);       
        
        // Panel 2 setting
        panel2=new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.setBackground(Color.BLACK);
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        panel2.setPreferredSize(new Dimension((int)(screenSize.getWidth()*0.4),(int)(screenSize.getHeight()*0.05)));
        
        // Add components to panel 2
        panel2.add(desk);

        // Add panel 2 to frame
        frame.add(panel2);        
        		
    }
    
    @Override  
    public void actionPerformed(ActionEvent arg){
    	
        if(arg.getSource()==this.file_in_button){ // When clicking browse button
            JFileChooser chooser=new JFileChooser();  
            chooser.setDialogTitle("Choose a namelist!");  
            int result = chooser.showOpenDialog(this);   
            if (result == JFileChooser.APPROVE_OPTION) {  
                filename=chooser.getSelectedFile().getAbsolutePath(); 
                name_text.setText(filename);
            }
        }   
        else if(arg.getSource()==this.run_button){ // When clicking run button 
            row=Integer.parseInt(row_text.getText());
            col=Integer.parseInt(col_text.getText());
            try{
                namelist=new ArrayList<String>();
                idlist=new ArrayList<Integer>();
                SeatGenerator sg=new SeatGenerator(filename,row,col);
                namelist=sg.getNameList();
                idlist=sg.getIdList();
                namesize=sg.getNameSize();
                setSeat(); // Fill up panel 3
            } 
            catch(IOException ex){
                System.out.println (ex.toString());
            }
        }  
        else if(arg.getSource()==this.reset_button){ // When clicking reset button 
            filename=null;
            row=0;
            col=0;
            name_text.setText("");
            row_text.setText("");
            col_text.setText("");
            panel3.removeAll();
            panel3.updateUI();
        }
        
    }
    
    private void setSeat(){
    	try {
    		
    		// Export csv file
    		FileWriter filewriter = new FileWriter("seat.csv");
    	
	    	// Panel 3 setting
	    	panel3=new JPanel();
	        panel3.setLayout(new GridLayout(row,col));
	        panel3.setBackground(Color.BLACK);
	        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	        panel3.setPreferredSize(new Dimension((int)(screenSize.getWidth()*0.8),(int)(screenSize.getHeight()*0.8)));
	        
	        // Add panel 3 to frame
	        frame.add(panel3);
	        
	        // Seat setting
	        for(int i=0;i<row-1;i++){
	            for(int j=0;j<col;j++){
	            	String student = namelist.get(idlist.get(i*col+j));
	            	filewriter.append(student);
	            	if(j==col-1){
	            		filewriter.append("\n");
	            	}
	            	else {
	            		filewriter.append(",");
	            	}
	            	JLabel name=new JLabel(student);
	            	name.setHorizontalAlignment(JLabel.CENTER);
	            	name.setFont(new java.awt.Font("DIALOG",1,20)); 
	            	name.setBorder(BorderFactory.createEtchedBorder());
	            	name.setOpaque(true);
	            	name.setBackground(Color.GRAY);
	            	name.setForeground(Color.WHITE);
	            	GridBagConstraints pos_name = new GridBagConstraints();
	            	pos_name.gridx=j;
	            	pos_name.gridy=i;
	            	pos_name.gridwidth=1;
	            	pos_name.gridheight=1;
	            	pos_name.weightx = 1;
	            	pos_name.weighty = 1;
	            	panel3.add(name,pos_name);
	            }
	        }
	        
	        // Shift for classroom
	        int empty=row*col-namesize;
	        for(int j=0;j<col;j++){
	        	JLabel name;
	        	if(j<empty){
	        		name=new JLabel(namelist.get(idlist.get(row*col-empty+j)));
	        	}
	        	else{
	        		name=new JLabel(namelist.get(idlist.get((row-1)*col+j-empty)));
	        	}
	        	name.setHorizontalAlignment(JLabel.CENTER);
	        	name.setFont(new java.awt.Font("DIALOG",1,20)); 
	        	name.setBorder(BorderFactory.createEtchedBorder());
	        	name.setOpaque(true);
	        	name.setBackground(Color.GRAY);
	        	name.setForeground(Color.WHITE);
	        	GridBagConstraints pos_name = new GridBagConstraints();
	        	pos_name.gridx=j;
	        	pos_name.gridy=row-1;
	        	pos_name.gridwidth=1;
	        	pos_name.gridheight=1;
	        	pos_name.weightx = 1;
	        	pos_name.weighty = 1;
	        	panel3.add(name,pos_name);
	       }
	        
	        panel3.updateUI(); 
	        
	        filewriter.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
        
    }
    
}
