package com.application.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.application.beans.Customer;
import com.application.beans.Product;
import com.connection.ConnectionManager;

import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Register extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textUser;
	private JTextField textPassword;
	private JTextField textPhoneNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Register dialog = new Register();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Register() {
		setTitle("Register");
		setBounds(100, 100, 962, 571);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(607, 57, 273, 426);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel LBsmallsub = new JLabel("New label");
		LBsmallsub.setBounds(160, 381, 27, 24);
		panel.add(LBsmallsub);
		showImage("icon_submit.png", LBsmallsub);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setForeground(Color.BLACK);
		lblRegister.setBounds(103, 70, 85, 29);
		panel.add(lblRegister);
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
		{
			JLabel lblNewLabel = new JLabel("Name ");
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setBounds(45, 99, 64, 16);
			panel.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		
		JLabel lblSurname = new JLabel("surname ");
		lblSurname.setForeground(Color.BLACK);
		lblSurname.setBounds(45, 155, 85, 16);
		panel.add(lblSurname);
		lblSurname.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblPhonenumber = new JLabel("Phone Number ");
		lblPhonenumber.setForeground(Color.BLACK);
		lblPhonenumber.setBounds(45, 211, 120, 16);
		panel.add(lblPhonenumber);
		lblPhonenumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblUsre = new JLabel("User ");
		lblUsre.setForeground(Color.BLACK);
		lblUsre.setBounds(45, 265, 79, 20);
		panel.add(lblUsre);
		lblUsre.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblPassword = new JLabel("Password ");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setBounds(45, 320, 93, 19);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textPassword = new JTextField();
		textPassword.setForeground(Color.BLACK);
		textPassword.setBounds(45, 340, 125, 22);
		panel.add(textPassword);
		textPassword.setColumns(10);
		
		textUser = new JTextField();
		textUser.setForeground(Color.BLACK);
		textUser.setBounds(45, 287, 125, 22);
		panel.add(textUser);
		textUser.setColumns(10);
		
		textPhoneNumber = new JTextField();
		textPhoneNumber.setForeground(Color.BLACK);
		textPhoneNumber.setBounds(45, 231, 150, 22);
		panel.add(textPhoneNumber);
		textPhoneNumber.setColumns(10);
		
		textSurname = new JTextField();
		textSurname.setForeground(Color.BLACK);
		textSurname.setBounds(45, 176, 187, 21);
		panel.add(textSurname);
		textSurname.setColumns(10);
		{
			textName = new JTextField();
			textName.setForeground(Color.BLACK);
			textName.setBounds(45, 120, 187, 22);
			panel.add(textName);
			textName.setColumns(10);
		}
		
		JButton btnSubmit = new JButton("     Submit");
		btnSubmit.setIcon(new ImageIcon("D:\\eclipse\\PreProject\\image\\login_Window\\Register_win\\submit2.png"));
		btnSubmit.setForeground(Color.BLACK);
		btnSubmit.setBounds(150, 375, 111, 37);
		panel.add(btnSubmit);
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblPleaseRegister = new JLabel("Please Register");
		lblPleaseRegister.setForeground(Color.WHITE);
		lblPleaseRegister.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblPleaseRegister.setBounds(165, 318, 231, 34);
		panel.add(lblPleaseRegister);
		
		JLabel lblNewLabel_4 = new JLabel("Make sure your account");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(165, 345, 231, 29);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\eclipse\\PreProject\\image\\login_Window\\Register_win\\bg3_1.1.jpg"));
		lblNewLabel_2.setBounds(502, -6, 268, 411);
		panel.add(lblNewLabel_2);
		
		JLabel LBicon_regis = new JLabel("New label");
		LBicon_regis.setBounds(106, 10, 64, 57);
		panel.add(LBicon_regis);
		showImage("icon_regis.png", LBicon_regis);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InsertData();
			}
		});
		
		JLabel lblPleaseRegister_1 = new JLabel("Please Register");
		lblPleaseRegister_1.setForeground(Color.WHITE);
		lblPleaseRegister_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblPleaseRegister_1.setBounds(86, 380, 249, 34);
		contentPanel.add(lblPleaseRegister_1);
		
		JLabel lblNewLabel_1 = new JLabel("acount of your");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(86, 403, 198, 34);
		contentPanel.add(lblNewLabel_1);
		
		JLabel LBpic_regis = new JLabel("New label");
		LBpic_regis.setBounds(60, 57, 549, 426);
		contentPanel.add(LBpic_regis);
		showImage("picRegis2.jpg", LBpic_regis);
		
		JLabel LBbackRegis = new JLabel("");
		LBbackRegis.setIcon(new ImageIcon("D:\\eclipse\\PreProject\\image\\login_Window\\Register_win\\bg4.jpg"));
		LBbackRegis.setBounds(0, 0, 944, 526);
		contentPanel.add(LBbackRegis);
		showImage("BackpicRegis.jpg", LBbackRegis );
	}
	
	/*private boolean doCreate(Customer customer) {
		try {
			ConnectionManager connection = new ConnectionManager();
			Connection connect = connection.getConnection();
			Statement s = connect.createStatement();
		
			StringBuilder sb = new StringBuilder();
			sb.append(" INSERT INTO customer (customerid, B, C) ");
			sb.append(" VALUES ( max(customerid) + 1 )");
			sb.append("         , ").append(customer.Name);
			sb.append("         , ").append(customer.LastName);
			sb.append(" ) ");
			
			int rec = s.executeUpdate(sb.toString());
			if (rec == 1) {
				// Login succcess.
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}*/
	public void InsertData(){
		
				Customer customer = new Customer();
				customer.setName(textName.getText());
				customer.setSurname(textSurname.getText());
				customer.setPhoneNumber(textPhoneNumber.getText());
				customer.setUser(textUser.getText());
				customer.setPassword(textPassword.getText());
				//System.out.println(customer.getName());

		try {
			ConnectionManager connection = new ConnectionManager();
			Connection connect = connection.getConnection();
			Statement s = connect.createStatement();
			
			StringBuilder sb = new StringBuilder();
			sb.append("select max(customerid) + 1 AS nextCustNo from customer");
			ResultSet rec = s.executeQuery(sb.toString());
			
			int nextCustID = (rec.next()) ? rec.getInt("nextCustNo") : 0;
		
			if (nextCustID > 0) {
				
				sb = new StringBuilder();
				sb.append(" INSERT INTO `customer` (`customerID`, `name`, `surname`, `phoneNumber`, `user`, `password`) VALUES (" + nextCustID + ", '"+customer.getName()
				+"', '"+customer.getSurname()+"', '"+customer.getPhoneNumber()+"', '"+customer.getUser()+"', '"+customer.getPassword()+"') ");
				int recResult = s.executeUpdate(sb.toString());
				if (recResult == 1) {
					// Register succcess.
					System.out.println("Register success.");
				} else {
					System.out.println("Register not success.");
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void showImage(String name,JLabel label ){
		BufferedImage img=null;
		try{
			InputStream in=getClass().getClassLoader().getResourceAsStream(name);
			img=ImageIO.read(in);
			Image dimg=img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon=new ImageIcon(dimg);
			label.setIcon(imageIcon);
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
