package com.application.menu;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import com.application.beans.Customer;
import com.connection.ConnectionManager;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;

import java.sql.*;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
public class BucketProduct extends JDialog {

	private final JPanel contentPanel = 
			new JPanel();
	//private JTable table;
	private javax.swing.JTable table;
	private JTextField textCusIDBuc;
	private JTextField textNameBuc;
	private JTextField textSurnameBuc;
private int row=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BucketProduct dialog = new BucketProduct();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BucketProduct() {
		iniComponents();
		showOrderBucket();
		
	}
	private void iniComponents(){
		setBounds(100, 100, 916, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new LineBorder(Color.WHITE));
		scrollPane_1.setBounds(239, 157, 644, 263);
		contentPanel.add(scrollPane_1);
		
		//table = new JTable();
		table = new javax.swing.JTable();
		table.setAutoCreateRowSorter(true);
		table.setModel(new javax.swing.table.DefaultTableModel(
			new Object[][] {}
			, new String[] { "ProductID","Price" ,"Quatity","Brand","Name"}) {
				
		//ตอนแรกมีบรรทัด76-86 แต่ comment ไว้ไม่ ERROR
			/*boolean[] canEdit = new boolean[] {  false, false, false, false, false,false };
				Class[] type = new Class[] { String.class, String.class, String.class, String.class, String.class, int.class };
				
		
		public Class getColumnClass(int columnIndex) {
			return type[columnIndex];
		}

		public boolean isCellEditTable(int rowIndex, int columnIndex) {
			return canEdit[columnIndex];
		}*/
	});
		scrollPane_1.setViewportView(table);
		
		
		JLabel label = new JLabel("\u0E15\u0E30\u0E01\u0E23\u0E49\u0E32\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32 ");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 32));
		label.setBounds(165, 12, 190, 74);
		contentPanel.add(label);
		
		JLabel lblNewLabel = new JLabel("Order Busket");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(166, 66, 177, 22);
		contentPanel.add(lblNewLabel);
		
		JLabel LBlogoBuc = new JLabel("");
		LBlogoBuc.setBounds(18, 15, 134, 129);
		contentPanel.add(LBlogoBuc);
		LBlogoBuc.setIcon(null);
		showImage("icon busket.png", LBlogoBuc);
		
		
		textCusIDBuc = new JTextField();
		textCusIDBuc.setForeground(Color.WHITE);
		textCusIDBuc.setFont(new Font("Tahoma", Font.PLAIN, 26));
		textCusIDBuc.setEditable(false);
		textCusIDBuc.setBackground(Color.BLACK);
		textCusIDBuc.setBounds(816, 22, 56, 53);
		textCusIDBuc.setBorder ( new LineBorder(Color.BLACK) );
		contentPanel.add(textCusIDBuc);
		textCusIDBuc.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CustomerID ");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(668, 37, 161, 37);
		contentPanel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		textNameBuc = new JTextField();
		textNameBuc.setForeground(Color.BLACK);
		textNameBuc.setBackground(Color.WHITE);
		textNameBuc.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textNameBuc.setBounds(44, 244, 147, 42);
		contentPanel.add(textNameBuc);
		textNameBuc.setEditable(false);
		textNameBuc.setText("pokk");
		textNameBuc.setBorder ( new LineBorder(Color.WHITE) );
		textNameBuc.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Name ");
		lblNewLabel_2.setBounds(31, 221, 65, 16);
		contentPanel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		textSurnameBuc = new JTextField();
		textSurnameBuc.setBackground(Color.WHITE);
		textSurnameBuc.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textSurnameBuc.setBounds(44, 321, 152, 37);
		contentPanel.add(textSurnameBuc);
		textSurnameBuc.setBorder ( new LineBorder(Color.WHITE) );
		textSurnameBuc.setEditable(false);
		textSurnameBuc.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Surname ");
		lblNewLabel_3.setBounds(31, 299, 78, 20);
		contentPanel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel LBbuck2 = new JLabel("");
		LBbuck2.setIcon(new ImageIcon("D:\\eclipse\\PreProject\\image\\login_Window\\bucket\\icon3.png"));
		LBbuck2.setBounds(82, 157, 65, 59);
		contentPanel.add(LBbuck2);
		showImage("icon_bcuk.png", LBbuck2);
		
		JPanel panel = new JPanel();
		panel.setBounds(18, 201, 190, 186);
		panel.setBorder(new LineBorder(Color.WHITE));
		contentPanel.add(panel);
		
		JLabel lblNewLabel_5 = new JLabel("\u0E1B\u0E23\u0E30\u0E27\u0E31\u0E15\u0E34\u0E01\u0E32\u0E23\u0E2A\u0E31\u0E48\u0E07\u0E0B\u0E37\u0E49\u0E2D\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32\u0E17\u0E31\u0E49\u0E07\u0E2B\u0E21\u0E14\u0E02\u0E2D\u0E07\u0E04\u0E38\u0E13");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_5.setBounds(209, 119, 308, 22);
		contentPanel.add(lblNewLabel_5);
		
		JLabel LBback_left = new JLabel("");
		LBback_left.setBounds(0, 107, 220, 387);
		LBback_left.setBorder(new LineBorder(Color.WHITE, 2));
		contentPanel.add(LBback_left);
		showImage("bg.jpg", LBback_left);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(0, 0, 899, 106);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnClose = new JButton("   Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		
		JLabel LBexit = new JLabel("");
		LBexit.setBounds(763, 448, 32, 28);
		contentPanel.add(LBexit);
		btnClose.setBackground(Color.WHITE);
		btnClose.setIcon(new ImageIcon("D:\\eclipse\\PreProject\\image\\login_Window\\bucket\\close.png"));
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClose.setBorder(new LineBorder(Color.WHITE, 2));
		btnClose.setBounds(754, 443, 118, 37);
		contentPanel.add(btnClose);
		showImage("exit.png", LBexit);
	}

	
	private void showOrderBucket(){
		DefaultTableModel model1 =  (DefaultTableModel)table.getModel();
		Connection connect = null;
		Statement s = null;
		try{
		ConnectionManager connection = new ConnectionManager();
		connect = connection.getConnection();
		Customer customer=new Customer();
		System.out.println(customer.getCustomerID());
		s = connect.createStatement();
		String sql=" SELECT orderpro.ProductID, product.price, orderpro.Quatity, product.brand , customer.name "
				+ " From orderpro "
				+ " JOIN product ON product.IDproduct = orderpro.ProductID "
				+ " JOIN customer ON customer.customerID = orderpro.CustomerID" 
				+ " WHERE orderpro.CustomerID = "+customer.getCustomerID()+ "";
		//String sql="SELECT ProductID,Price,Quatity,Brand From orderpro INNER JOIN product ON product.IDproduct=orderpro.ProductID " ;
		ResultSet rec=s.executeQuery(sql);
		
		while((rec!=null)&&(rec.next())){
			//ProductID", "Brand", "Series", "Type", "Price" ,"Quatity"
			model1.addRow(new Object[0]);
			model1.setValueAt(false, row, 0);
			model1.setValueAt(rec.getString("ProductID"), row, 0);
			model1.setValueAt(rec.getString("Price"), row, 1);
			model1.setValueAt(rec.getString("Quatity"), row, 2);
			model1.setValueAt(rec.getString("Brand"), row, 3);
			model1.setValueAt(rec.getString("Name"), row, 4);
			row++;
		}
		rec.close();
		textCusIDBuc.setText(String.valueOf(Customer.getCustomerID()));
		textSurnameBuc.setText(Customer.getSurname());
		textNameBuc.setText(Customer.getName());
		
		}catch (Exception e) {
			System.out.println("Errorr");
			JOptionPane.showMessageDialog(null, e.getMessage());
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
