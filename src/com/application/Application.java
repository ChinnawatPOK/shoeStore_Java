package com.application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.application.beans.Customer;
import com.application.beans.Product;
import com.application.menu.BucketProduct;
import com.application.menu.ProductManagement;
import com.application.menu.Register;
import com.connection.ConnectionManager;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Application extends JFrame {

	private JPanel contentPane;
	private JTextField textUser;
	private JTextField textPassword;
	private JLabel LBbackground;
	private JLabel LBicon_login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Application() {
		setTitle("Login");
		setBackground(Color.ORANGE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 964, 562);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(571, 57, 307, 267);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(162, 205, 97, 25);
		panel.add(btnCancel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(53, 205, 97, 25);
		panel.add(btnLogin);
		
		textPassword = new JTextField();
		textPassword.setBounds(96, 163, 116, 22);
		panel.add(textPassword);
		textPassword.setColumns(10);
		
		textUser = new JTextField();
		textUser.setBounds(96, 124, 116, 22);
		panel.add(textUser);
		textUser.setColumns(10);
		
		JLabel lblBetterAloneLife = new JLabel("A PRODUCT");
		lblBetterAloneLife.setForeground(Color.WHITE);
		lblBetterAloneLife.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblBetterAloneLife.setBounds(220, 278, 206, 53);
		panel.add(lblBetterAloneLife);
		
		JLabel lblNewLabel_2 = new JLabel("OF DESIGN");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(217, 315, 97, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(209, 232, 105, 22);
		panel.add(lblRegister);
		lblRegister.setForeground(Color.RED);
		lblRegister.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Register register=new Register();
				register.setModal(true);
				register.setVisible(true);
			}
		});
		lblRegister.setBackground(Color.WHITE);
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(574, 0, 97, 103);
		panel.add(lblNewLabel_3);
		
		JLabel lblMemberLogin = new JLabel("SIGN IN");
		lblMemberLogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMemberLogin.setBounds(118, 95, 72, 16);
		panel.add(lblMemberLogin);
		
		JLabel LBicon_login = new JLabel("iconlogin");
		LBicon_login.setBounds(118, 13, 77, 74);
		panel.add(LBicon_login);
		showImage("icon_login.png", LBicon_login);
		
		JLabel LBuser = new JLabel("");
		LBuser.setBounds(58, 121, 26, 25);
		panel.add(LBuser);
		showImage("user.png", LBuser);
		
		JLabel LBpassword = new JLabel("");
		LBpassword.setBounds(58, 159, 26, 25);
		panel.add(LBpassword);
		showImage("password.png", LBpassword);
		
		JLabel lblProductOf = new JLabel("PRODUCT OF");
		lblProductOf.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblProductOf.setForeground(Color.WHITE);
		lblProductOf.setBounds(361, 337, 184, 29);
		contentPane.add(lblProductOf);
		
		JLabel LBorder = new JLabel("");
		LBorder.setBounds(629, 411, 37, 37);
		contentPane.add(LBorder);
		showImage("icon_search.png", LBorder);
		
		JLabel LBsearch = new JLabel("");
		LBsearch.setBounds(607, 344, 37, 37);
		contentPane.add(LBsearch);
		showImage("icon_buc.png", LBsearch);
		
		JButton btnSearch = new JButton("         Search & Order Basket");
		btnSearch.setBounds(582, 337, 296, 52);
		contentPane.add(btnSearch);
		btnSearch.setEnabled(false);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ProductManagement prodMangement = new ProductManagement();
				//prodMangement.populate();
				prodMangement.setVisible(true);
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnOrderBucket = new JButton("   Order Bucket");
		btnOrderBucket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnOrderBucket.setBounds(582, 402, 296, 52);
		contentPane.add(btnOrderBucket);
		btnOrderBucket.setEnabled(false);
		btnOrderBucket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BucketProduct bucketPro=new BucketProduct();
				ProductManagement proManage=new ProductManagement ();
				//proManage.doOrderProduct();
				bucketPro.setModal(false);
				bucketPro.setVisible(true);
				
				
			}
		});
		btnOrderBucket.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNewLabel_1 = new JLabel("YOUR LIFE");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(360, 359, 152, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel LBbackTOP = new JLabel("bcakTOP");
		LBbackTOP.setBounds(68, 57, 502, 400);
		contentPane.add(LBbackTOP);
		showImage("backTOP1.jpg", LBbackTOP);
		
		JLabel LBbackground = new JLabel("backgroud");
		LBbackground.setBounds(0, 0, 946, 515);
		contentPane.add(LBbackground);
		showImage("background1.jpg", LBbackground);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(407, 357, 56, 16);
		contentPane.add(lblNewLabel);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean validLogin = doLogin(textUser.getText(), textPassword.getText());
				btnSearch.setEnabled(validLogin);
				btnOrderBucket.setEnabled(validLogin);
				showImage("icon_buc.png", LBsearch);
				showImage("icon_search.png", LBorder);
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
	}
	
	private boolean doLogin(String user, String password) {
		try {
			ConnectionManager connection = new ConnectionManager();
			Connection connect = connection.getConnection();
			Statement s = connect.createStatement();
		
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT * FROM customer ");
			sb.append(" WHERE user = '").append(user).append("'");
			sb.append(" AND password = '").append(password).append("'");
			ResultSet rec = s.executeQuery(sb.toString());
			if (rec != null && rec.next()) {
				// Login succcess.
				
				Customer customer=new Customer();
				customer.setCustomerID(Integer.parseInt(rec.getString("customerID")));
				customer.setName(rec.getString("name"));
				customer.setSurname(rec.getString("surname"));
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
	/*private String doLoginBucket(String user, String password) {
		try {
			ConnectionManager connection = new ConnectionManager();
			Connection connect = connection.getConnection();
			Statement s = connect.createStatement();
		
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT * FROM customer ");
			sb.append(" WHERE user = '").append(user).append("'");
			sb.append(" AND password = '").append(password).append("'");
			ResultSet rec = s.executeQuery(sb.toString());
			if (rec != null && rec.next()) {
				// Login succcess Bucket.
				
				Customer customer=new Customer();
				customer.setCustomerID(Integer.parseInt(rec.getString("customerID")));
				return customer.getUser();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return customer.getUser();
	}
}*/

