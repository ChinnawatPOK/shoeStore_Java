package com.application.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.application.beans.Customer;
import com.application.beans.Product;
import com.connection.ConnectionManager;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Receipt extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table_Bill;
	private BucketPanel bucketPanel;
	private JTextField textField_CusId;
	private JTextField textField_Name;
	private JTextField textField_Surname;
	private int row=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Receipt dialog = new Receipt();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Receipt() {
		setBounds(100, 100, 830, 606);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(24, 170, 752, 226);
			contentPanel.add(scrollPane);
			{
				table_Bill = new JTable();
				table_Bill.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] {
						}
						, new String[] { "ProductID", "Brand", "Type", "Series", "Price","Quatity","name"}) {
							boolean[] canEdit = new boolean[] { false, false, false, false, false, false,false };
							Class[] type = new Class[] { String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class};
				public Class getColumnClass(int columnIndex) {
					return type[columnIndex];
				}
	
				public boolean isCellEditTable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			});
				scrollPane.setViewportView(table_Bill);
			}
		}
		{
			JLabel label = new JLabel("\u0E43\u0E1A\u0E40\u0E2A\u0E23\u0E47\u0E08\u0E2A\u0E31\u0E48\u0E07\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
			label.setFont(new Font("Leelawadee UI", Font.BOLD, 26));
			label.setBounds(24, 23, 205, 45);
			contentPanel.add(label);
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(486, 23, 283, 113);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("CustomerID :");
		lblNewLabel_3.setBounds(12, 13, 76, 16);
		panel.add(lblNewLabel_3);
		
		textField_CusId = new JTextField();
		textField_CusId.setEditable(false);
		textField_CusId.setBounds(100, 10, 87, 22);
		panel.add(textField_CusId);
		textField_CusId.setColumns(10);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(22, 42, 56, 16);
		panel.add(lblName);
		
		textField_Name = new JTextField();
		textField_Name.setEditable(false);
		textField_Name.setBounds(100, 39, 116, 22);
		panel.add(textField_Name);
		textField_Name.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname :");
		lblSurname.setBounds(12, 71, 76, 16);
		panel.add(lblSurname);
		
		textField_Surname = new JTextField();
		textField_Surname.setEditable(false);
		textField_Surname.setBounds(100, 74, 116, 22);
		panel.add(textField_Surname);
		textField_Surname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u0E23\u0E32\u0E04\u0E32\u0E23\u0E27\u0E21 :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(161, 430, 131, 33);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u0E1A\u0E32\u0E17");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel_1.setBounds(484, 430, 78, 33);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("LOGO");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(49, 81, 142, 76);
		contentPanel.add(lblNewLabel_2);
		showOrderBill();
	}
	
	private void showOrderBill(){
	DefaultTableModel model1 =  (DefaultTableModel)table_Bill.getModel();
	Connection connect = null;
	Statement s = null;
	try{
	ConnectionManager connection = new ConnectionManager();
	connect = connection.getConnection();
	//Customer customer=new Customer();
	System.out.println(Customer.getCustomerID());
	s = connect.createStatement();
	String sql="SELECT orderpro.ProductID, product.brand,product.type,product.series,product.price,orderpro.Quatity,customer.name "
			+ " From orderpro "
			+ " JOIN product ON product.IDproduct = orderpro.ProductID "
			+ " JOIN customer ON customer.customerID = orderpro.CustomerID " 
			+ " WHERE orderpro.CustomerID = " + Customer.getCustomerID();
			
	ResultSet rec=s.executeQuery(sql);
	
	while((rec!=null)&&(rec.next())){
		model1.addRow(new Object[0]);
		model1.setValueAt(false, row, 0);
		model1.setValueAt(rec.getString("ProductID"), row, 0);
		model1.setValueAt(rec.getString("Brand"), row, 1);
		model1.setValueAt(rec.getString("Type"), row, 2);
		model1.setValueAt(rec.getString("Series"), row, 3);
		model1.setValueAt(rec.getString("Price"), row, 4);
		model1.setValueAt(rec.getString("Quatity"), row, 5);
		model1.setValueAt(rec.getString("Name"), row, 6);
		row++;
	}
	rec.close();
	
	}catch (Exception e) {
		System.out.println("Errorr");
		JOptionPane.showMessageDialog(null, e.getMessage());
	e.printStackTrace();
}
}
}
