package aes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Random;

import javax.swing.*;



public class AESprojectFinal extends JFrame implements ActionListener
{
	JTabbedPane tabbedPane;
	JPanel panel1,panel2;
	CheckboxGroup g1,g2;
	
	Checkbox c11,c12,c13;
	Button b11,b12,b13,b14,b15;
	Label l11,l12,l13,l14,l15;//l16,l17;
	TextField t11,t12,t13,t14;
	//TextArea  t15,t16;
	
	Checkbox c21,c22,c23;
	Button b23,b24,b25;
	Label l21,l22,l23,l24,l25;//l26,l27;
	TextField t21,t22,t23,t24;
	//TextArea  t25,t26;
	
	int codeNk,decodeNk;
	String codereadfilename;
	String decodereadfilename;
	String codewritefilename;
	String decodewritefilename;
	
	//Hex
	String oxcodeCipherKey;
	String oxdecodeCipherKey;
	String oxcodeRandomCector;
	String oxdecodeRandomCector;
	//value storage
	String codeCipherKey;
	String decodeCipherKey;
	String codeRandomCector;
	String decodeRandomCector;
	
	JFileChooser fileChooser1;
	JFileChooser fileChooser2;
	JFileChooser fileChooser3;
	JFileChooser fileChooser4;
	
	public void display() {
		
		setSize(900,300);
		setResizable(false);
		setLocation(200,300);
		
		g1=new CheckboxGroup();
		c11=new Checkbox("128",g1,true);
		c12=new Checkbox("192",g1,false);
		c13=new Checkbox("256",g1,false);
		
		b11=new Button("random key");
		b12=new Button("random vector");
		
		b13=new Button("open");
		b14=new Button("open");
		b15=new Button("encrypt");
		
		b11.addActionListener(this);
		b12.addActionListener(this);
		b13.addActionListener(this);
		b14.addActionListener(this);
		b15.addActionListener(this);
		
		l11=new Label("Key size：");
		l12=new Label("Key Secret：");
		l13=new Label("Vector IV：");
		l14=new Label("Input File：");
		l15=new Label("Output File：");
		
		t11=new TextField(66);
		t12=new TextField(66);
		t13=new TextField(34);
		t14=new TextField(34);
		
		panel1.add(l11);
		panel1.add(c11);
		panel1.add(c12);
		panel1.add(c13);
		panel1.add(b11);
		panel1.add(b12);
		
		panel1.add(l12);
		panel1.add(t11);
		
		panel1.add(l13);
		panel1.add(t12);
		
		panel1.add(l14);
		panel1.add(t13);
		panel1.add(b13);
		
		panel1.add(l15);
		panel1.add(t14);
		
		panel1.add(b14);
		panel1.add(b15);
		
		GridBagLayout gb1 = new GridBagLayout();
		panel1.setLayout(gb1);
		GridBagConstraints gbc = new GridBagConstraints();
		
		//第一行
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(l11, gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(c11, gbc);

		gbc.gridx=2;
		gbc.gridy=0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(c12, gbc);
		

		gbc.gridx=3;
		gbc.gridy=0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(c13, gbc);

		gbc.gridx=4;
		gbc.gridy=0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(b11, gbc);

		gbc.gridx=5;
		gbc.gridy=0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;	
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(b12, gbc);
		

		//2nd line
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;	
		gbc.gridwidth = 1;	
		gbc.gridheight = 1;	
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(l12, gbc);


		gbc.gridx=1;
		gbc.gridy=1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 10;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gb1.setConstraints(t11, gbc);
		

		//3rd line
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(l13, gbc);

		gbc.gridx=1;
		gbc.gridy=2;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 10;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gb1.setConstraints(t12, gbc);
		
		//4th line
		gbc.gridx=0;
		gbc.gridy=3;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(l14, gbc);


		gbc.gridx=1;
		gbc.gridy=3;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 9;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(t13, gbc);

		gbc.gridx=11;
		gbc.gridy=3;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(b13, gbc);
		
		//5th line
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(l15, gbc);

		gbc.gridx=1;
		gbc.gridy=4;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 9;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(t14, gbc);

		gbc.gridx=11;
		gbc.gridy=4;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(b14, gbc);
		
		gbc.gridx=4;
		gbc.gridy=7;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb1.setConstraints(b15, gbc);		
		
		
		
		
		//decrypt
		
		g2=new CheckboxGroup();
		c21=new Checkbox("128",g2,true);
		c22=new Checkbox("192",g2,false);
		c23=new Checkbox("256",g2,false);
		
		
		b23=new Button("open");
		b24=new Button("open");
		b25=new Button("create");
		

		b23.addActionListener(this);
		b24.addActionListener(this);
		b25.addActionListener(this);
		
		l21=new Label("Key Size：");
		l22=new Label("Key secret：");
		l23=new Label("Vector IV：");
		l24=new Label("Input File：");
		l25=new Label("Output File：");
		
		t21=new TextField(66);
		t22=new TextField(66);
		t23=new TextField(34);
		t24=new TextField(34);
		
		panel2.add(l21);
		panel2.add(c21);
		panel2.add(c22);
		panel2.add(c23);
		
		panel2.add(l22);
		panel2.add(t21);
		
		panel2.add(l23);
		panel2.add(t22);
		
		panel2.add(l24);
		panel2.add(t23);
		panel2.add(b23);
		
		panel2.add(l25);
		panel2.add(t24);
		panel2.add(b24);
		
		panel2.add(b25);
		
		GridBagLayout gb2 = new GridBagLayout();
		panel2.setLayout(gb2);
		
		//line 1
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(l21, gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(c21, gbc);

		gbc.gridx=2;
		gbc.gridy=0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(c22, gbc);
		

		gbc.gridx=3;
		gbc.gridy=0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(c23, gbc);

		//2
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;	
		gbc.gridwidth = 1;	
		gbc.gridheight = 1;	
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(l22, gbc);


		gbc.gridx=1;
		gbc.gridy=1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 10;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gb2.setConstraints(t21, gbc);
		

		//3
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(l23, gbc);

		gbc.gridx=1;
		gbc.gridy=2;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 10;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gb2.setConstraints(t22, gbc);
		
		//4
		gbc.gridx=0;
		gbc.gridy=3;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(l24, gbc);


		gbc.gridx=1;
		gbc.gridy=3;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 9;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(t23, gbc);

		gbc.gridx=11;
		gbc.gridy=3;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(b23, gbc);
		
		//5
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(l25, gbc);

		gbc.gridx=1;
		gbc.gridy=4;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 9;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(t24, gbc);

		gbc.gridx=11;
		gbc.gridy=4;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(b24, gbc);
		
		gbc.gridx=4;
		gbc.gridy=7;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gb2.setConstraints(b25, gbc);		
		
		t13.setEditable(false);
		t14.setEditable(false);
		t23.setEditable(false);
		t24.setEditable(false);
		
	}
	
	public AESprojectFinal()
	{
		codeNk=4;
		decodeNk=4;

		codereadfilename="";
		decodereadfilename="";
		codewritefilename="";
		decodewritefilename="";
		codeCipherKey="";
		decodeCipherKey="";
		codeRandomCector="";
		decodeRandomCector="";
		
		File dir=new File("C:\\Users\\nhokv\\Desktop");
		if(!dir.exists()){
		    dir.mkdir();
		}
		fileChooser1= new JFileChooser("C:\\Users\\nhokv\\Desktop");
		fileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);

		fileChooser2= new JFileChooser("C:\\Users\\nhokv\\Desktop");
		fileChooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		

		fileChooser3= new JFileChooser("C:\\Users\\nhokv\\Desktop");
		fileChooser3.setFileSelectionMode(JFileChooser.FILES_ONLY);
		

		fileChooser4= new JFileChooser("C:\\Users\\nhokv\\Desktop");
		fileChooser4.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		Container c = getContentPane();
		tabbedPane=new JTabbedPane();
		panel1=new JPanel();
		panel2=new JPanel();
		
		tabbedPane.addTab("Encrypt",null,panel1,"First panel");
		tabbedPane.addTab("Decrypt",null,panel2,"Second panel");
		c.add(tabbedPane);
		c.setBackground(Color.white);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String args[])
	{
		(new AESprojectFinal()).display();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b11)
		{
			if(c11.getState())
				codeNk=4;
			else if(c12.getState())
				codeNk=6;
			else if(c13.getState())
				codeNk=8;
			BuildAESKey rv=new BuildAESKey(codeNk);
			oxcodeCipherKey=rv.getKeyIn0x();
			codeCipherKey=rv.getKey();
			t11.setText(oxcodeCipherKey);
		}
		else if(e.getSource()==b12)
		{
			BuildAESKey rv=new BuildAESKey(4);
			oxcodeRandomCector=rv.getKeyIn0x();
			codeRandomCector=rv.getKey();
			t12.setText(oxcodeRandomCector);
		}
		else if(e.getSource()==b13)
		{
			
			int returnVal = fileChooser1.showOpenDialog(fileChooser1);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{ 
				codereadfilename= fileChooser1.getSelectedFile().getAbsolutePath();
				
				t13.setText(codereadfilename);
			}
		}
		else if(e.getSource()==b14)
		{
			int returnVal = fileChooser2.showOpenDialog(fileChooser2);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				Tools t=new Tools();
				codewritefilename=t.getfileform(codereadfilename);
				codewritefilename= fileChooser2.getSelectedFile().getAbsolutePath()+"\\DecryptedFile"+codewritefilename;
				t14.setText(codewritefilename);
			}
		}
		//encrypt
		else if(e.getSource()==b15)
		{
			long startTime=System.currentTimeMillis(); 
			if(t11.getText().isEmpty()||t12.getText().isEmpty()||t13.getText().isEmpty()||t14.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "please enter input");
				return ;
			}
			Tools t=new Tools();
			oxcodeCipherKey=t11.getText();
			if(oxcodeCipherKey.length()==34)
			{
				codeNk=4;
				c11.setState(true);
				codeCipherKey=t.oxtoString(oxcodeCipherKey,codeNk);
			}
			else if(oxcodeCipherKey.length()==50)
			{
				codeNk=6;
				c12.setState(true);
				codeCipherKey=t.oxtoString(oxcodeCipherKey,codeNk);
			}
			else if(oxcodeCipherKey.length()==66)
			{
				codeNk=8;
				c13.setState(true);
				codeCipherKey=t.oxtoString(oxcodeCipherKey,codeNk);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "The number of bits key is wrong, please start with 0x");
				return;
			}
			oxcodeRandomCector=t12.getText();
			if(oxcodeRandomCector.length()==34)
			{
				codeRandomCector=t.oxtoString(oxcodeRandomCector,4);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Random vector digits are wrong, please start with 0x");
				return;
			}
			
			//encrpted
			AESCode c=new AESCode(codeNk);
			ReadandWriteFileBynByte1 f=new ReadandWriteFileBynByte1();
			f.SetN(4);
			f.ReadFile(codereadfilename);
			f.WriteFile(codewritefilename);
			String ctemp;//plaintText
			String ptemp;//cipherText
			try {
				ctemp=f.ReadNByte();
				if(ctemp.length()==17)
				{
					ctemp=ctemp.substring(0,ctemp.length()-1);
					ctemp=t.xorForString(ctemp, codeRandomCector);
					if(c.encryption(ctemp, codeCipherKey))
					{
						ptemp=c.getCipherText();
						f.WriteNByte(ptemp);
						long endTime=System.currentTimeMillis();
						long time=endTime-startTime;
						JOptionPane.showMessageDialog(this, "Encrypted！"+"Encrypting Time : "+time+"ms");
						
						f.CloseFiles();
						return ;
					}
					else
					{
						JOptionPane.showMessageDialog(this, "encrypting failed！");
						f.CloseFiles();
						return;
					}
				}
				ptemp=codeRandomCector;//Random Vector
				while(ctemp.length()==16) {
					ctemp=t.xorForString(ctemp, ptemp);
					if(c.encryption(ctemp, codeCipherKey))
					{
						ptemp=c.getCipherText();
						f.WriteNByte(ptemp);
					}
					else
					{
						JOptionPane.showMessageDialog(this, "encrypting failed！");
						f.CloseFiles();
						return;
					}
					ctemp=f.ReadNByte();
				}
				ctemp=ctemp.substring(0, ctemp.length()-1);
				ctemp=t.xorForString(ctemp, ptemp);
				if(c.encryption(ctemp, codeCipherKey))
				{
					ptemp=c.getCipherText();
					f.WriteNByte(ptemp);
					long endTime=System.currentTimeMillis();
					long time=endTime-startTime;
					JOptionPane.showMessageDialog(this, "Encrypted！"+"Encrypting Time"+time+"ms");
					
					return;
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Encrypting Fail！");
					f.CloseFiles();
					return;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==b23)
		{
			int returnVal = fileChooser3.showOpenDialog(fileChooser3);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{ 
				decodereadfilename= fileChooser3.getSelectedFile().getAbsolutePath();
				t23.setText(decodereadfilename);
			}
			
		}
		else if(e.getSource()==b24)
		{
			int returnVal = fileChooser4.showOpenDialog(fileChooser4);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{ 	
				Tools t=new Tools();
				decodewritefilename=t.getfileform(decodereadfilename);
				decodewritefilename= fileChooser4.getSelectedFile().getAbsolutePath()+"\\Encrypted File"+decodewritefilename;
				t24.setText(decodewritefilename);
			}
		}
		
		//解密
		else if(e.getSource()==b25)
		{
			long startTime=System.currentTimeMillis(); 
			if(t21.getText().isEmpty()||t22.getText().isEmpty()||t23.getText().isEmpty()||t24.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please enter input！");
				return ;
			}
			Tools t=new Tools();
			oxdecodeCipherKey=t21.getText();
			if(oxdecodeCipherKey.length()==34)
			{
				decodeNk=4;
				c21.setState(true);
				decodeCipherKey=t.oxtoString(oxdecodeCipherKey,decodeNk);
			}
			else if(oxdecodeCipherKey.length()==50)
			{
				decodeNk=6;
				c22.setState(true);
				decodeCipherKey=t.oxtoString(oxdecodeCipherKey,decodeNk);
			}
			else if(oxdecodeCipherKey.length()==66)
			{
				decodeNk=8;
				c23.setState(true);
				decodeCipherKey=t.oxtoString(oxdecodeCipherKey,decodeNk);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Incorrect number of key bits,Please start with 0x!");
				return;
			}
			oxdecodeRandomCector=t22.getText();
			if(oxdecodeRandomCector.length()==34)
			{
				decodeRandomCector=t.oxtoString(oxdecodeRandomCector,4);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Random vector bit error,Please start with 0x!");
				return;
			}
			
			
			//Officially decrypted
			
			AESDecode c=new AESDecode(decodeNk);
			ReadandWriteFileBynByte2 f=new ReadandWriteFileBynByte2();
			f.SetN(4);
			f.ReadFile(decodereadfilename);
			f.WriteFile(decodewritefilename);
			String ctemp;//plainText
			String ptemp;//CipherText
			String pptemp;//NextCipherText
			try {
				ptemp=f.ReadNByte();
				if(ptemp.length()==1)
				{
					JOptionPane.showMessageDialog(this, "Decrypted File is Null！Decrypting failed");
					f.CloseFiles();
					return;
				}
				ctemp=decodeRandomCector;//Random vector
				while(ptemp.length()==16) {
					if(c.decryption(ptemp, decodeCipherKey))
					{
						ctemp=t.xorForString(ctemp, c.getDeCipherText());
						
						pptemp=f.ReadNByte();

						
						if(pptemp.length()==16)//continue
						{
							f.WriteNByte(ctemp);
							ctemp=ptemp;
							ptemp=pptemp;
						}
						else
						{
							f.WriteLastByte(ctemp);
							long endTime=System.currentTimeMillis();
							long time=endTime-startTime;
							JOptionPane.showMessageDialog(this, "Decrypted！"+"Decrypting Time"+time+"ms");
							
							try {
								Process process = Runtime.getRuntime().exec("cmd.exe /c "+decodewritefilename);
								} catch (Exception e1) {
								e1.printStackTrace();
								}
							f.CloseFiles();
							return;
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Encrypting Fail！");
						f.CloseFiles();
						return;
					}
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
}

}
//tools，use in GUI
class Tools{
	//Each byte type saved as String type is ANDed and output as String type
	public String xorForString(String s1,String s2) {
		String temp="";
		char ctemp;
		byte btemp,b1temp,b2temp;
		
        for(int i=0;i<s1.length();i++)
        {
            b1temp=(byte)s1.charAt(i);
            b2temp=(byte)s2.charAt(i);
            btemp=(byte)(b1temp^b2temp);
            ctemp=(char)btemp;
            temp+=ctemp;
        }
        return temp;
	}
	//Convert the byte type saved in String type to hexadecimal and save it as a hexadecimal string, Nk is the word length
	public String stringIn0x(String in,int Nk)
	{
		byte s[]=new byte[in.length()];
		for(int i=0;i<Nk*4;i++)
		{
			s[i]=(byte)in.charAt(i);
		}
		String temp="0x";
		String ctemp;
		for(int i=0;i<Nk*4;i++)
		{
			ctemp=Integer.toHexString((int)(0xff&(s[i])));
			if(ctemp.length()==1)
				ctemp="0"+ctemp;
			temp+=ctemp;
		}
		return temp;
	}
	//Convert a hexadecimal number string to a numeric value and store it in a string, Nk is the word length
	public String oxtoString (String in,int Nk)
	{
		int sin[]=new int[in.length()-2];
		in=in.substring(2,in.length());
		for(int i=0;i<Nk*8;i++)
		{
			sin[i]=Integer.valueOf(in.charAt(i));
		}
		String temp="";
		for(int i=0;i<Nk*4;i++)
		{
			temp+=(char)(byte)(sin[i*2]*16+sin[i*2+1]);
		}
		return temp;
	}
	//Get the file extension
	public String getfileform(String filename)
	{
		int lastIndexOf = filename.lastIndexOf(".");
		String suffix = filename.substring(lastIndexOf);
		return suffix;
	}
}
//Random vector generation class
class BuildAESKey{
	int Nk;
	byte key[];
	
	//Generate a random vector of Nk words and save it in the object
	public BuildAESKey(int Nk) {
		this.Nk=Nk;
		key=new byte[Nk*4];
        Random random = new Random();
        random.nextBytes(key);
	}
	//Get the random vector in the form of a byte array
	public void getKey(byte fkey[]) {
		for(int i=0;i<Nk*4;i++)
		{
			fkey[i]=key[i];
		}
	}
	//Get a random vector in the form of a string
	public String getKey() {
		String temp="";
		for(int i=0;i<Nk*4;i++)
		{
			temp+=(char)key[i];
		}
		return temp;
	}
	//Obtain a random vector in the form of a hexadecimal string, with "0x" in front of the vector
	public String getKeyIn0x() {
		String temp="0x";
		String ctemp;
		for(int i=0;i<Nk*4;i++)
		{
			ctemp=Integer.toHexString((int)(0xff&(key[i])));
			
			if(ctemp.length()==1)
				ctemp="0"+ctemp;
			temp+=ctemp;
			
		}
		return temp;
	}
	

	public String stringIn0x(String in)
	{
		byte s[]=new byte[in.length()];
		for(int i=0;i<Nk*4;i++)
		{
			s[i]=(byte)in.charAt(i);
		}
		String temp="0x";
		String ctemp;
		for(int i=0;i<Nk*4;i++)
		{
			ctemp=Integer.toHexString((int)(0xff&(s[i])));
			if(ctemp.length()==1)
				ctemp="0"+ctemp;
			temp+=ctemp;
		}
		return temp;
	}
	
	public String oxtoString (String in,int Nk)
	{
		int sin[]=new int[in.length()-2];
		in=in.substring(2,in.length());
		for(int i=0;i<Nk*8;i++)
		{
			sin[i]=Integer.valueOf(in.charAt(i));
		}
		String temp="";
		for(int i=0;i<Nk*4;i++)
		{
			temp+=(char)(byte)(sin[i*2]*16+sin[i*2+1]);
		}
		return temp;
	}
}
//Pure AES encryption
class AESCode {
    byte cipherKey[];//Original encryption key
    Word plaintext[];//Original input plaintext
    Word cipherText[];//Encrypted ciphertext
    int Nb;//Data packet length/32 bits
    int Nk;//Key group length/32bits
    int Nr;//Rounds
    Word Rcon[];//Constants used for key expansion
    byte mixColumnMatrix[][];
    Word wordKey[];//Extended key
    byte Sbox[];//S box 16*16 bytes
    AESCode(int fNk){
        Nb=4;
        Nk=fNk;
        switch(Nk)
        {
            case 4:Nr=10;break;
            case 6:Nr=12;break;
            case 8:Nr=14;break;
            default:Nr=0;break;
        }
        setSbox();
        initRcon();
        initMixColumnMatrix();
    }
    //Encryption//Unique input interface
    public boolean encryption(String plain,String Key){
        if(!setPlainText(plain))
        {
            return false;
        }
        if(cipherKey==null)
        {
        	setCipherKey(Key);
        	keyExpansion();
        }
        int i,j;
        cipherText=new Word[4];
        for(i=0;i<Nb;i++)
        {
            cipherText[i]=new Word();
            for(j=0;j<4;j++)
            {
                cipherText[i].setByte(j,plaintext[i].getByte(j));
            }
        }
        
        addRoundKey(cipherText,0);
        
        for(i=1;i<Nr;i++)
        {
            subNbWords(cipherText);
            
            rotNbWord(cipherText);
            
            mixColumn(cipherText);
            
            addRoundKey(cipherText,i);
            
        }
        subNbWords(cipherText);
        
        rotNbWord(cipherText);
        
        addRoundKey(cipherText, Nr);
        
        return true;
    }
    //Get ciphertext
    public String getCipherText(){
        String temp="";
        char ctemp;
        int i,j;
        for(i=0;i<Nb;i++)
        {
            for(j=0;j<4;j++)
            {
                ctemp=(char)(cipherText[i].getByte(j));
                temp+=ctemp;
            }
        }
        return temp;
    }
    //Initialize column confusion matrix
    private void initMixColumnMatrix(){
        mixColumnMatrix=new byte[][]{
            {0x02,0x03,0x01,0x01},
            {0x01,0x02,0x03,0x01},
            {0x01,0x01,0x02,0x03},
            {0x03,0x01,0x01,0x02},
        };
    }
    //Plain text initialization, the input plain text is a string, and each byte occupies a string
    private boolean setPlainText(String plain)
    {
        if(plain.length()!=4*Nb)//Input length error
        {
            return false;
        }
        plaintext=new Word[Nb];
        for(int i=0;i<Nb;i++)
        {
            plaintext[i]=new Word();
            for(int j=0;j<4;j++)
            {
                plaintext[i].wordbyte[j]=(byte)plain.charAt(i*4+j);
            }
        }
        return true;
    }
    //S box initialization
    private void setSbox(){
        char cSbox[]=new char[]{
            0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5,
            0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
            0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0,
            0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
            0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc,
            0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
            0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a,
            0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
            0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0,
            0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
            0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b,
            0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
            0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85,
            0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
            0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5,
            0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
            0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17,
            0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
            0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88,
            0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
            0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c,
            0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
            0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9,
            0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
            0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6,
            0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
            0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e,
            0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
            0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94,
            0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
            0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68,
            0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16
        };
        Sbox=new byte[256];
        for(int i=0;i<256;i++)
        {
            Sbox[i]=(byte)(cSbox[i]);
        }
    }
    //Key initialization
    private void setCipherKey(String Key)
    {
        int L=4*Nk;
        cipherKey=new byte[L];
        for(int i=0;i<L;i++)
        {
            cipherKey[i]=(byte)Key.charAt(i);
        }
    }
    //Initialize wheel constant
    private void initRcon(){
        int L=11;
        Rcon=new Word[L];
        int i,j=0;
        byte temp=(byte)0x00;
        for(i=0;i<L;i++)
        {
            Rcon[i]=new Word();
            for(j=0;j<4;j++)
            {
                Rcon[i].setByte(j,temp);
            }
        }
        Rcon[1].wordbyte[0]=(byte)0x01;
        for(i=2;i<L;i++)
        {
            Rcon[i].setByte(0,GFMultiplyByte(Rcon[i-1].wordbyte[0],(byte)0x02));
        }
    }
    
    
        //S-box permutation based on a set of plaintext
    private void subNbWords(Word w[])
    {
        for(int i=0;i<Nb;i++)
        {
            w[i]=subWord(w[i]);
        }
    }
    //S-box replacement in units of words
    private Word subWord(Word w){
        Word wtemp=new Word();
        for(int i=0;i<4;i++)
        {
            byte L,R;
            byte btemp=w.getByte(i);
            L=((byte)((btemp&(0xff))>>>4));
            R=(byte)(btemp&0x0f);
            btemp=Sbox[((int)L)*16+((int)R)];
            wtemp.setByte(i, btemp);
        }
        return wtemp;
    }

    //XOR
    private Word wordXOR(Word w1,Word w2)
    {
        return w1.wordXOR(w2);
    }
    
        private void rotNbWord(Word w[])
    {
        int i,j;
        for(i=0;i<Nb;i++)
        {
            for(j=0;j<i;j++)
            {
                w[i]=rotWord(w[i]);
            }
        }
    }
    //Shift left one byte within word
    private Word rotWord(Word w1)
    {
        w1.rotWord();
        return w1;
    } 
    //Clear text grouping line shift

    //Round key extension
    private void keyExpansion(){
        int i=0;
        int j;
        Word temp=new Word();
        int L=Nb*(Nr+1);
        wordKey=new Word[L];
        for(i=0;i<Nk;i++)
        {
            wordKey[i]=new Word();
            for(j=0;j<4;j++)
            {
                wordKey[i].setByte(j, cipherKey[4*i+j]);
            }
        }
        for(i=Nk;i<Nb*(Nr+1);i++)
        {
            wordKey[i]=new Word();
            temp.wordCopy(wordKey[i-1]);
            if((i%Nk)==0)
            {
                temp=rotWord(temp);
                temp=subWord(temp);
                temp=wordXOR(temp, Rcon[i/Nk]);
            }
            else if(Nk>6&&(i%Nk==4))
            {
                temp=subWord(temp);
            }
            wordKey[i]=wordXOR(wordKey[i-Nk], temp);
        }
        
    }
    //GF domain multiplication
    private byte GFMultiplyByte(byte L,byte R){  
        byte temp[]=new byte[8];
        byte result=(byte)0x00;
        temp[0]=L;
        int i;
        for(i=1;i<8;i++)
        {
            if((temp[i-1]&((byte)0x80))==((byte)0x80))
            {
                temp[i]=(byte)((temp[i-1]<<1)^((byte)0x1b));
            }
            else{
                temp[i]=(byte)(temp[i-1]<<1);
            }
        }
        for(i=0;i<8;i++)
        {
            if(((int)(((R>>>i)&((byte)0x01))))==1)
            {
                result=(byte)(result^temp[i]);
            }
        }
        return result;
    }
    //Column confusion in a set of plaintext
    private void mixColumn(Word w[])
    {
        Word result[]=new Word[Nb];
        int i,j,k;
        for(i=0;i<Nb;i++)
        {
            result[i]=new Word();
            for(j=0;j<4;j++)
            {
                result[i].setByte(j,GFMultiplyByte(mixColumnMatrix[i][0],w[0].getByte(j)));
                for(k=1;k<4;k++)
                {
                    result[i].setByte(j,(byte)(result[i].getByte(j)^(GFMultiplyByte(mixColumnMatrix[i][k],w[k].getByte(j)))));
                }
            }
        }
        for(i=0;i<Nb;i++)
        {
            for(j=0;j<4;j++)
            {
                w[i].setByte(j, result[i].getByte(j));
            }
        }
    }
    //Round key plus
    private void addRoundKey(Word w[],int round)
    {
        int i,j;
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                w[i].setByte(j,(byte)((w[i].getByte(j))^(wordKey[i+4*round].getByte(j))));
            }
        }
    }
    
    //For testing，Output a group in the console
    private void showNbWords(Word w[])
    {
    	for(int i=0;i<Nb;i++)
    	{
    		w[i].showWord();
    	}
    }
}
class AESDecode {
    byte cipherKey[];//Original encryption key 16 bytes
    Word cipherText[];//The input plaintext 4 words 16 bytes
    Word deCipherText[];//The decrypted ciphertext 4 words 16 bytes
    int Nb;//Data packet length/32bits
    int Nk;//Key group length/32bits
    int Nr;//Rounds
    Word Rcon[];//Constant 11 words for key expansion
    Word wordKey[];//Extended key
    byte Sbox[];//S box 16*16 bytes
    byte invSbox[];//Inverse S box 16*16 bytes
    byte invMixColumnMatrix[][];//4*4 bytes

    AESDecode(int fNk){
        Nb=4;
        Nk=fNk;
        switch(Nk)
        {
            case 4:Nr=10;break;
            case 6:Nr=12;break;
            case 8:Nr=14;break;
            default:Nr=0;break;
        }
        setSbox();
        setInvSbox();
        initRcon();
        initInvMixColumnMatrix();
    }
    //Decrypt//The only input interface
    public boolean decryption(String cipher,String Key){
        if(!setCipherText(cipher))
        {
            return false;
        }
        if(cipherKey==null)
        {
        	setCipherKey(Key);
        	keyExpansion();
        }
        int i,j;
        deCipherText=new Word[4];
        for(i=0;i<Nb;i++)
        {
            deCipherText[i]=new Word();
            for(j=0;j<4;j++)
            {
                deCipherText[i].setByte(j,cipherText[i].getByte(j));
            }
        }
        
        addRoundKey(deCipherText,Nr);

        for(i=Nr-1;i>0;i--)
        {
            invRotNbWord(deCipherText);    ///
            
            invSubNbWords(deCipherText);

            addRoundKey(deCipherText,i);
            
            invMixColumn(deCipherText);

        }
        invRotNbWord(deCipherText);
        
        invSubNbWords(deCipherText);
        
        addRoundKey(deCipherText, 0);
        
        return true;
    }
    //Get decrypted text
    public String getDeCipherText(){
        String temp="";
        char ctemp;
        int i,j;
        for(i=0;i<Nb;i++)
        {
            for(j=0;j<4;j++)
            {
                ctemp=(char)(deCipherText[i].getByte(j));
                temp+=ctemp;
            }
        }
        return temp;
    }
    //Initialize the inverse confusion matrix
    private void initInvMixColumnMatrix(){
        invMixColumnMatrix=new byte[][]{
            {0x0e,0x0b,0x0d,0x09},
            {0x09,0x0e,0x0b,0x0d},
            {0x0d,0x09,0x0e,0x0b},
            {0x0b,0x0d,0x09,0x0e},
        };
    }
    //Ciphertext initialization，The input ciphertext is a string，Each byte occupies a string
    private boolean setCipherText(String cipher)
    {
        if(cipher.length()!=4*Nb)//Input length error
        {
            return false;
        }
        cipherText=new Word[Nb];
        for(int i=0;i<Nb;i++)
        {
            cipherText[i]=new Word();
            for(int j=0;j<4;j++)
            {
                cipherText[i].wordbyte[j]=(byte)cipher.charAt(i*4+j);
            }
        }
        return true;
    }
    //S box initialization
    private void setSbox(){
        char cSbox[]=new char[]{
            0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5,
            0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
            0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0,
            0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
            0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc,
            0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
            0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a,
            0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
            0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0,
            0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
            0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b,
            0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
            0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85,
            0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
            0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5,
            0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
            0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17,
            0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
            0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88,
            0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
            0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c,
            0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
            0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9,
            0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
            0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6,
            0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
            0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e,
            0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
            0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94,
            0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
            0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68,
            0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16
        };
        Sbox=new byte[256];
        for(int i=0;i<256;i++)
        {
            Sbox[i]=(byte)(cSbox[i]);
        }
    }
    //Inverse S-box initialization
    private void setInvSbox(){
        char cSbox[]=new char[]{
            0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38,
            0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb,
            0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87,
            0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb,
            0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d,
            0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e,
            0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2,
            0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25,
            0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16,
            0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92,
            0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda,
            0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84,
            0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a,
            0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06,
            0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02,
            0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b,
            0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea,
            0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73,
            0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85,
            0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e,
            0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89,
            0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b,
            0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20,
            0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4,
            0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31,
            0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f,
            0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d,
            0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef,
            0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0,
            0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61,
            0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26,
            0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d
        };
        invSbox=new byte[256];
        for(int i=0;i<256;i++)
        {
            invSbox[i]=(byte)(cSbox[i]);
        }
    }
    //Key initialization
    private void setCipherKey(String Key)
    {
        int L=4*Nk;
        cipherKey=new byte[L];
        for(int i=0;i<L;i++)
        {
            cipherKey[i]=(byte)Key.charAt(i);
        }
    }
    //Initialize wheel constant
    private void initRcon(){
        int L=11;
        Rcon=new Word[L];
        int i,j=0;
        byte temp=(byte)0x00;
        for(i=0;i<L;i++)
        {
            Rcon[i]=new Word();
            for(j=0;j<4;j++)
            {
                Rcon[i].setByte(j,temp);
            }
        }
        Rcon[1].wordbyte[0]=(byte)0x01;
        for(i=2;i<L;i++)
        {
            Rcon[i].setByte(0,GFMultiplyByte(Rcon[i-1].wordbyte[0],(byte)0x02));
        }
    }
    //S-box replacement in units of words
    private Word subWord(Word w){
        Word wtemp=new Word();
        for(int i=0;i<4;i++)
        {
            byte L,R;
            byte btemp=w.getByte(i);
            L=((byte)((btemp&(0xff))>>>4));
            R=(byte)(btemp&0x0f);
            btemp=Sbox[((int)L)*16+((int)R)];
            wtemp.setByte(i, btemp);
        }
        return wtemp;
    }
    //Inverse S-box permutation in units of words
    private Word invSubWord(Word w){
        Word wtemp=new Word();
        for(int i=0;i<4;i++)
        {
            byte L,R;
            byte btemp=w.getByte(i);
            L=((byte)((btemp&(0xff))>>>4));
            R=(byte)(btemp&0x0f);
            btemp=invSbox[((int)L)*16+((int)R)];
            wtemp.setByte(i, btemp);
        }
        return wtemp;
    }
    //Inverse S-box permutation based on a set of plaintext
    private void invSubNbWords(Word w[])
    {
        for(int i=0;i<Nb;i++)
        {
            w[i]=invSubWord(w[i]);
        }
    }
    //XOR
    private Word wordXOR(Word w1,Word w2)
    {
        return w1.wordXOR(w2);
    }
    //Shift left one byte within word
    private Word rotWord(Word w1)
    {
        w1.rotWord();
        return w1;
    } 
    //Move one byte to the right within a word
    private Word invRotWord(Word w1)
    {
        w1.invRotWord();
        return w1;
    }
    //Clear text grouping line shift
    private void invRotNbWord(Word w[])
    {
        int i,j;
        for(i=0;i<Nb;i++)
        {
            for(j=0;j<i;j++)
            {
                w[i]=invRotWord(w[i]);
            }
        }
    }
    //Round key extension
    private void keyExpansion(){
        int i=0;
        int j;
        Word temp=new Word();
        int L=Nb*(Nr+1);
        wordKey=new Word[L];
        for(i=0;i<Nk;i++)
        {
            wordKey[i]=new Word();
            for(j=0;j<4;j++)
            {
                wordKey[i].setByte(j, cipherKey[4*i+j]);
            }
        }
        for(i=Nk;i<Nb*(Nr+1);i++)
        {
            wordKey[i]=new Word();
            temp.wordCopy(wordKey[i-1]);
            if((i%Nk)==0)
            {
                temp=rotWord(temp);
                temp=subWord(temp);
                temp=wordXOR(temp, Rcon[i/Nk]);
            }
            else if(Nk>6&&(i%Nk==4))
            {
                temp=subWord(temp);
            }
            wordKey[i]=wordXOR(wordKey[i-Nk], temp);
        }
        
    }
    //GF domain multiplication
    private byte GFMultiplyByte(byte L,byte R){  
        byte temp[]=new byte[8];
        byte result=(byte)0x00;
        temp[0]=L;
        int i;
        for(i=1;i<8;i++)
        {
            if((temp[i-1]&((byte)0x80))==((byte)0x80))
            {
                temp[i]=(byte)((temp[i-1]<<1)^((byte)0x1b));
            }
            else{
                temp[i]=(byte)(temp[i-1]<<1);
            }
        }
        for(i=0;i<8;i++)
        {
            if(((int)(((R>>>i)&((byte)0x01))))==1)
            {
                result=(byte)(result^temp[i]);
            }
        }
        return result;
    }
    //Inverse confusion with a set of plain text as a unit
    private void invMixColumn(Word w[])
    {
        Word result[]=new Word[Nb];
        int i,j,k;
        for(i=0;i<Nb;i++)
        {
            result[i]=new Word();
            for(j=0;j<4;j++)
            {
                result[i].setByte(j,GFMultiplyByte(invMixColumnMatrix[i][0],w[0].getByte(j)));
                for(k=1;k<4;k++)
                {
                    result[i].setByte(j,(byte)(result[i].getByte(j)^(GFMultiplyByte(invMixColumnMatrix[i][k],w[k].getByte(j)))));
                }
            }
        }
        for(i=0;i<Nb;i++)
        {
            for(j=0;j<4;j++)
            {
                w[i].setByte(j, result[i].getByte(j));
            }
        }
    }
    //Round key plus
    private void addRoundKey(Word w[],int round)
    {
        int i,j;
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                w[i].setByte(j,(byte)((w[i].getByte(j))^(wordKey[i+4*round].getByte(j))));
            }
        }
    }
    private void showNbWords(Word w[])
    {
    	for(int i=0;i<Nb;i++)
    	{
    		w[i].showWord();
    	}
    }

}

//Encrypted file io
class ReadandWriteFileBynByte1{
    int n;
    ReadFileByByte rb;
    WriteFileByByte wb;
    public ReadandWriteFileBynByte1(){
        rb=new ReadFileByByte();
        wb=new WriteFileByByte();
    }
    public void SetN(int Nb){
        n=4*Nb;
    }
    public void ReadFile(String path)
    {
        try{
            rb.ReadFile(path);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void WriteFile(String path){ 
        try {
                wb.WriteFile(path);
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    //The string at the end of the text is 17 digits
    public String ReadNByte() throws IOException {
        String temp="";
        int j=0;
        if(!rb.ReadAByte())
        {
            for(int i=n;i>0;i--)
            {
                temp+=(char)(byte)(n);
            }
            temp+="0";
            return temp;
        }
        temp+=rb.GetChar();
        for(int i=n-1;i>0;i--)
        {
            if(rb.ReadAByte())
            {
                temp+=rb.GetChar();
            }
            else
            {
                if(j==0)
                {
                    j=i;
                }
                temp+=(char)(byte)(j);
            }
        }
        if(j!=0)
        {
            temp+="0";
        }
        return temp;
    }
    public boolean WriteNByte(String fs) throws IOException {
        if(fs.length()!=n)
             return false;
        for(int i=0;i<fs.length();i++)
        {
            wb.Setbyte(fs.charAt(i));
            wb.WriteAByte();
        }
        return true;
    }
    public void CloseFiles() throws IOException {
        rb.CloseFile();
        wb.CloseFile();
    } 
}


//Decrypt file io
class ReadandWriteFileBynByte2{
    int n;
    ReadFileByByte rb;
    WriteFileByByte wb;
    public ReadandWriteFileBynByte2(){
        rb=new ReadFileByByte();
        wb=new WriteFileByByte();
    }
    public void SetN(int Nb){
        n=4*Nb;
    }
    public void ReadFile(String path)
    {
        try{
            rb.ReadFile(path);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void WriteFile(String path){ 
        try {
                wb.WriteFile(path);
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    //Return "0" when the number of bytes does not match;
    public String ReadNByte() throws IOException {
        String temp="";
        if(!rb.ReadAByte())
        {
            temp="0";
            return temp;
        }
        temp+=rb.GetChar();
        for(int i=n-1;i>0;i--)
        {
            if(rb.ReadAByte())
            {
                temp+=rb.GetChar();
            }
            else
            {
                temp="0";
                return temp;
            }
        }
        return temp;
    }
    public boolean WriteNByte(String fs) throws IOException {
        for(int i=0;i<fs.length();i++)
        {
            wb.Setbyte(fs.charAt(i));
            wb.WriteAByte();
        }
        return true;
    }
    public boolean WriteLastByte(String fs) throws IOException {
        int fn=(int)(byte)fs.charAt(fs.length()-1);
    	for(int i=0;i<n-fn;i++)
        {
            wb.Setbyte(fs.charAt(i));
            wb.WriteAByte();
        }
        return true;
    }
    
    public void CloseFiles() throws IOException {
        rb.CloseFile();
        wb.CloseFile();
    } 
}


class ReadFileByByte {
    File F;//document
    InputStream fis;//File pointer
    char c;
    int sum;
    public boolean ReadAByte() throws IOException{
        int temp=0;
        boolean IfReadAByte=false;
        try{
            if((temp=fis.read())!=-1)
            {
                c=((char)temp);
                sum++;
                IfReadAByte=true;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return IfReadAByte;
    }
    public char GetChar()
    {
        return c;
    }
    public void ReadFile(String path) throws IOException{
        F=new File(path);
        fis=null;
        try {
                fis=new FileInputStream(F);
            } 
        catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }
    public void CloseFile () throws IOException {
        try{}
        finally{
        if(fis!=null)
            fis.close();
        }
    }
}

class WriteFileByByte{
    File F;
    OutputStream fis;
    char c;
    int sum;
    public boolean WriteAByte() throws IOException{
        boolean IfWriteAByte=false;
        try{
            fis.write(c);
            sum++;
            IfWriteAByte=true;
        }
        catch (IOException e) {
            System.out.print("Exception");
        }
        return IfWriteAByte;
    }
    public void Setbyte(char fc)
    {
        c=fc;
    }
    public void WriteFile(String path) throws IOException{
        F=new File(path);
        fis=null;
        try {
                fis=new FileOutputStream(F);
            } 
        catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }
    public void CloseFile () throws IOException {
        try{}
        finally{
        if(fis!=null)
            fis.close();
        }
    }
}

class Word{
    byte wordbyte[];
    public Word() {
        wordbyte=new byte[4];
    }
    public Word(byte w[]) {
        wordbyte=new byte[4];
        setWord(w);
    }

    public void setWord(byte w[])
    {
        wordbyte[0]=w[0];
        wordbyte[1]=w[1];
        wordbyte[2]=w[2];
        wordbyte[3]=w[3];
    }
    public byte getByte(int index){
        return wordbyte[index];
    }
    public void setByte(int index,byte b)
    {
        wordbyte[index]=b;
    }
    //1 byte left
    public void rotWord(){
        byte temp=wordbyte[0];
        wordbyte[0]=wordbyte[1];
        wordbyte[1]=wordbyte[2];
        wordbyte[2]=wordbyte[3];
        wordbyte[3]=temp;

    }
    //Shift right by 1 byte
    public void invRotWord(){
        byte temp=wordbyte[3];
        wordbyte[3]=wordbyte[2];
        wordbyte[2]=wordbyte[1];
        wordbyte[1]=wordbyte[0];
        wordbyte[0]=temp;

    }
    public void wordCopy(Word w)
    {
        setWord(w.wordbyte);
    }
    //Return result，Do not change the content of the word
    public Word wordXOR(Word w)
    {
        byte temp[]=new byte[4];
        Word wtemp=new Word();
        for(int i=0;i<4;i++)
        {
            temp[i]=(byte)(wordbyte[i]^w.getByte(i));
        }
        wtemp.setWord(temp);
        return wtemp;
    }
    public void showWord() {
            for(int j=0;j<4;j++)
            {
            	System.out.print(Integer.toHexString((int)(0xff&(wordbyte[j]))));
            	System.out.print(" ");
            }
        	System.out.println(" ");
    }
}