package com.application.menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.application.beans.Customer;
import com.application.beans.Product;
import com.application.dialogs.ConfirmBill;
import com.application.dialogs.DetailDialog;
import com.application.dialogs.OrderQuatityDialog;
import com.connection.ConnectionManager;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class ProductManagement extends JFrame implements TableModelListener {

	private int row = 0;
	
	private BucketPanel bucketPanel;
	
	private JPanel contentPane;
	private javax.swing.JTable tableProduct;
	private JButton btnOrder;
	private JButton btnDetail;
	private JComboBox ComboBoxBrand;
	private JComboBox ComboBoxType;
	

	private DefaultTableModel tableProductModel;
	private Map<Integer, Product> productMap;
	//private final int COL_INDEX = 0;
	private final int COL_ID_PRODUCT = 0;
	private final int COL_BRAND = 1;
	private final int COL_TYPE = 2;
	private final int COL_SERIES = 3;
	private final int COL_PRICE = 4;
	private final int COL_PICTURE = 5;
	private final int COL_DETAILS = 6;
	private JTextField textBeginPrice;
	private JTextField textEndPrice;

	/**
	 * Create the frame.
	 */
	public ProductManagement() {
		iniComponents();
		setTableRenderer();
	}

	private void iniComponents() {
		
		/*DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tableProduct.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);*/
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1382, 860);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
	//	panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Shopping Cart",
			//	TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 0, 1414, 888);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Brand");
		lblNewLabel.setFont(new Font("FC Active", Font.BOLD, 34));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(-12, 176, 139, 34);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblName = new JLabel("Type");
		lblName.setFont(new Font("FC Active", Font.BOLD, 34));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(28, 288, 80, 34);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblName);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(50, 637, 173, 53);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionSearch();
			}
		});
		panel.add(btnSearch);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(456, 756, 91, 25);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//clearTextSearch();
			}
		});
		panel.add(btnCancel);
		
		btnDetail = new JButton("Detail");
		btnDetail.setBounds(892, 375, 97, 25);
		btnDetail.setEnabled(false);
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDetails();
			}
		});
		panel.add(btnDetail);
		
		btnOrder = new JButton("Order");
		btnOrder.setBounds(580, 363, 97, 25);
		btnOrder.setEnabled(false);
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("CustomerID Order is >>"+Customer.getCustomerID());
				System.out.println("ProductID order is >>"+getSelectedProduct().getIdProduct());
				// when btn order was press
				doOrderProduct();//�ʴ��Table ��ҧ��ҧ
				//tableProduct.clearSelection(); �繡�������������͡�ͧ tableProduct ��ѧ�ҡҡ� �� order
				//OrderToDatabase();
			}
		});
		panel.add(btnOrder);
		
		bucketPanel = new BucketPanel();
		bucketPanel.setBounds(320, 413, 995, 305);
		panel.add(bucketPanel);
		
//comboBox Search
		ComboBoxBrand = new JComboBox();
		ComboBoxBrand.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ComboBoxBrand.setBounds(50, 211, 170, 34);
		ComboBoxBrand.setToolTipText("brand");
		ComboBoxBrand.insertItemAt("Nike", 0);
		ComboBoxBrand.insertItemAt("Adidas", 1);
		ComboBoxBrand.insertItemAt("Fila", 2);
		panel.add(ComboBoxBrand);
		
		ComboBoxType = new JComboBox();
		ComboBoxType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ComboBoxType.setBounds(50, 323, 170, 38);
		ComboBoxType.insertItemAt("Running", 0);
		ComboBoxType.insertItemAt("Sneaker", 1);
		ComboBoxType.insertItemAt("Flip-Flop", 2);
		panel.add(ComboBoxType);
		
		JButton btnComfirm = new JButton("Comfirm");
		btnComfirm.setBounds(706, 734, 219, 68);
		btnComfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				OrderToDatabase();
				ConfirmBill confirm=new ConfirmBill();
				confirm.setVisible(true);
				dispose();
			}
		});
		panel.add(btnComfirm);
		
		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(40, 402, 109, 28);
		panel.add(lblNewLabel_1);
		
		textBeginPrice = new JTextField();
		textBeginPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textBeginPrice.setBounds(107, 443, 103, 38);
		//textBeginPrice.setBorder(arg0);
		panel.add(textBeginPrice);
		textBeginPrice.setColumns(10);
		
		textEndPrice = new JTextField();
		textEndPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textEndPrice.setBounds(107, 521, 103, 40);
		panel.add(textEndPrice);
		textEndPrice.setColumns(10);
								
								JScrollPane scrollPane = new JScrollPane();
								scrollPane.setBounds(338, 66, 965, 268);
								panel.add(scrollPane);
								
										tableProduct = new javax.swing.JTable();
										scrollPane.setViewportView(tableProduct);
										tableProduct.setShowGrid(false);
										tableProduct.setAutoCreateRowSorter(true);
										tableProduct.setModel(new javax.swing.table.DefaultTableModel(
											new Object[][] {
											}
											, new String[] { "IDProduct", "Brand", "Type", "Series", "Price" }) {
												boolean[] canEdit = new boolean[] { false, false, false, false, false };
												Class[] type = new Class[] {String.class, String.class, String.class, String.class, String.class };

												public Class getColumnClass(int columnIndex) {
													return type[columnIndex];
												}
	
												public boolean isCellEditTable(int rowIndex, int columnIndex) {
													return canEdit[columnIndex];
												}
											});
										tableProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
										
										JLabel lblSearch = new JLabel("Search");
										lblSearch.setFont(new Font("FC Active", Font.BOLD, 51));
										lblSearch.setForeground(Color.WHITE);
										lblSearch.setBounds(138, 57, 188, 40);
										panel.add(lblSearch);
										
										JLabel lblStart = new JLabel("start");
										lblStart.setFont(new Font("Tahoma", Font.BOLD, 18));
										lblStart.setForeground(Color.YELLOW);
										lblStart.setBounds(59, 448, 56, 16);
										panel.add(lblStart);
										
										JLabel lblTo = new JLabel("TO");
										lblTo.setForeground(Color.WHITE);
										lblTo.setFont(new Font("Tahoma", Font.BOLD, 18));
										lblTo.setBounds(138, 492, 41, 16);
										panel.add(lblTo);
										
										JLabel lblFinal = new JLabel("Final");
										lblFinal.setForeground(Color.YELLOW);
										lblFinal.setFont(new Font("Tahoma", Font.BOLD, 18));
										lblFinal.setBounds(59, 525, 56, 16);
										panel.add(lblFinal);
										
										JLabel lblRequiment = new JLabel("Requiment");
										lblRequiment.setFont(new Font("FC Active", Font.PLAIN, 30));
										lblRequiment.setForeground(Color.WHITE);
										lblRequiment.setBounds(136, 85, 190, 50);
										panel.add(lblRequiment);
										
										JLabel LBsearch = new JLabel("New label");
										LBsearch.setBounds(28, 39, 103, 96);
										panel.add(LBsearch);
										showImage("search1.png", LBsearch);
										
										JLabel LBblack = new JLabel("");
										LBblack.setBounds(0, 0, 293, 816);
										panel.add(LBblack);
										showImage("black.png", LBblack);
										
	}

	private void setTableRenderer() {
		tableProduct.getSelectionModel().addListSelectionListener((ListSelectionEvent e) ->{
			boolean enable = tableProduct.getSelectedRowCount() > 0;//�������ѧ�ҡ��á������ search �����ա�����͡�������
			btnDetail.setEnabled(enable);
			btnOrder.setEnabled(enable);
			
			//�Ѵ������� Center �ͧ����Cell ���ѹ�������>>>�����繵͹click ��������ç��ҧ᷹
			/*DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			tableProduct.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);*/
			
			/*tableProduct.getColumnModel().getColumn(0).setPreferredWidth(50);
			tableProduct.setForeground(Color.black);*/
			
		});
		
		tableProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(evt.getClickCount() == 2) {
//					doDetails();
				}
			}
		});
		
//		TableColumnModel colModel = tableNisit.getColumnModel();
//		int numColumn = colModel.getColumnCount();
//		for (int i = 0; i < numColumn; i++) {
//			TableColumn column = tableNisit.getColumnModel().getColumn(i);
//			// column.setCellRenderer(cellRenderer);
//			// column.setHeaderRenderer(headerRenderer);
//		}
		tableProductModel = (DefaultTableModel) tableProduct.getModel();
		tableProduct.getTableHeader().setResizingAllowed(true);
		tableProduct.getTableHeader().setReorderingAllowed(true);
		tableProduct.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Set column width
		tableProduct.getColumnModel().getColumn(COL_ID_PRODUCT).setPreferredWidth(80);
		tableProduct.getModel().addTableModelListener(this);
	}

	private Product getSelectedProduct() {
		if (!tableProduct.getSelectionModel().isSelectionEmpty()) {//isSelectionEmpty()�繡�õ�Ǩ�ͺ��Ң����Ŷ١���͡��������� �׹��� true ���������ա�ö١���͡
			//getSelectionModel()��¡�ä׹��ҽ���Ѻ�������˹� mode ���Ѻ JTree ��� �������ö���͡����������§�����������������¢����� 
			int modelRowIndex = tableProduct.convertRowIndexToModel(tableProduct.getSelectedRow());
			return (Product) productMap.get(modelRowIndex);
		}
		return null;
	}

	private void populateData(Product[] productList) {
		clearTableProduct();
//		if (tableModel.getRowCount() > 0) {
//			tableModel.setRowCount(0);
//		}
		// Set data to Map object.
		productMap = new HashMap<Integer, Product>();
		int rowIndex = 0;
		for (Product product : productList) {
			productMap.put(rowIndex, product);

			tableProductModel.insertRow(rowIndex, new Object[tableProductModel.getColumnCount()]);
			setValueAtTable(product, rowIndex);
			rowIndex++;
		}
		tableProduct.clearSelection();
	}

	private void setValueAtTable(Product product, int row) {
		tableProductModel.setValueAt(product.getIdProduct(), row, COL_ID_PRODUCT);
		tableProductModel.setValueAt(product.getBrand(), row, COL_BRAND);
		tableProductModel.setValueAt(product.getType(), row, COL_TYPE);
		tableProductModel.setValueAt(product.getSeries(), row, COL_SERIES);
		tableProductModel.setValueAt(product.getPrice(), row, COL_PRICE);
//		tableProductModel.setValueAt(product.getPicture(), row, COL_PICTURE);
		//tableProductModel.setValueAt(product.getDetails(), row, COL_DETAILS);

	}

	private void clearTableProduct() {
		if (tableProductModel != null) {
			int tableRowCount = tableProduct.getRowCount();
			while (tableRowCount > 0) {
				tableProductModel.removeRow(--tableRowCount);
			}
		}
	}
	
	/*private void clearTextSearch() {
		textSearchBrand.setText("");
		textSearchType.setText("");
		populate();
	}*/

	public void populate() {
		Connection connect = null;
		Statement s = null;
		try {
				ArrayList<Product> productList = new ArrayList<Product>();
			ConnectionManager connection = new ConnectionManager();
			connect = connection.getConnection();
			s = connect.createStatement();
			StringBuilder sb = new StringBuilder();
			/*Product c=new Product();
			c.setBrand(ComboBoxBrand.getSelectedItem().toString());
			System.out.println(ComboBoxBrand.getSelectedItem());
			c.setType(ComboBoxType.getSelectedItem().toString());*/
			
			//String sql="SELECT * FROM product "+ "WHERE brand LIKE '%" +ComboBoxBrand.getSelectedItem().toString()+ "%'"
			//		+" AND type LIKE '%" +ComboBoxType.getSelectedItem().toString()+ "%'"+"ORDER BY brand";
			//sb.append(" FROM product  ");
			sb.append(" SELECT *FROM product");
			if(ComboBoxType.getSelectedItem()==null ){//****µ�� if �����*****
			sb.append(" WHERE  brand LIKE '%").append(ComboBoxBrand.getSelectedItem()).append("%'");}
				else if(ComboBoxBrand.getSelectedItem()==null ){//****µ�� if �����*****
						sb.append(" WHERE  type LIKE '%").append(ComboBoxType.getSelectedItem()).append("%'");}
					else
						{sb.append(" WHERE  brand LIKE '%").append(ComboBoxBrand.getSelectedItem()).append("%'");
						sb.append("AND type LIKE '%").append(ComboBoxType.getSelectedItem()).append("%'");
						}
			//sb.append("AND type LIKE '%").append(ComboBoxType.getSelectedItem()).append("%'");
			if(textBeginPrice.getText().length() > 0 && textEndPrice.getText().length() > 0) {
				sb.append(" AND price BETWEEN ").append(textBeginPrice.getText()).append(" AND ").append(textEndPrice.getText());}
				else if(textBeginPrice.getText().length() > 0 && textEndPrice.getText().length() == 0) {
						sb.append(" AND price > ").append(textBeginPrice.getText()); }
					else if(textBeginPrice.getText().length() == 0 && textEndPrice.getText().length() > 0) {
							sb.append(" AND price < ").append(textEndPrice.getText()); }
			sb.append(" ORDER BY brand");
			//if (ComboBoxBrand.getSelectedItem() > 0 || textSearchType.getText().length() > 0) {
				//if (textSearchBrand.getText().length() > 0) {
					/*if (sb.toString().indexOf("WHERE") > 0) {
						sb.append(" AND brand like '%").append(ComboBoxBrand.getSelectedItem()).append("%'");
					} else {
						sb.append(" WHERE brand like '%").append(ComboBoxBrand.getSelectedItem()).append("%'");
					
				}

				/*if (textSearchType.getText().length() > 0) {
					if (sb.toString().indexOf("WHERE") > 0) {
						sb.append(" AND type like '%").append(ComboBoxType.getSelectedItem()).append("%'");
					} else {
						sb.append(" WHERE type like '%").append(ComboBoxType.getSelectedItem()).append("%'");
					}
				}*/
		//	}
			//sb.append(" ORDER BY brand ");

			ResultSet rec = s.executeQuery(sb.toString());
			if (rec != null) {
				while (rec.next()) {
					Product product = new Product();
					product.setIdProduct(rec.getString("IDproduct"));
					product.setBrand(rec.getString("brand"));
					product.setType(rec.getString("type"));
					product.setSeries(rec.getString("series"));
					product.setPrice(rec.getString("price"));
					product.setPicture(rec.getString("picture"));
					//product.setDetails(rec.getString("details"));

					productList.add(product);
				}
			}
			rec.close();
			populateData(productList.toArray(new Product[productList.size()]));
			} catch (Exception e) {
			System.out.println("Errorr");
				JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		try {
			if (s != null) {
				s.close();
				connect.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void actionSearch() {
		populate();
	}
	
	private void doDetails() {
		DetailDialog.showDialog(this, getSelectedProduct());
	}
	
	// Method  Order to database
	public void OrderToDatabase(){
		try {
			ConnectionManager connection = new ConnectionManager();
			Connection connect = connection.getConnection();
			Statement s = connect.createStatement();
			
			Product[] orderList = bucketPanel.getAllBucketOrder();
			for(Product order : orderList) {
				StringBuilder sb = new StringBuilder();
				sb.append("select IFNULL(max(orderID) + 1, 1) AS nextOderNo from orderpro");//???????????????????
				ResultSet rec = s.executeQuery(sb.toString());
				int nextOderNo = (rec.next()) ? rec.getInt("nextOderNo") : 1;
				//int nextNo = (rec.next()) ? rec.getInt("nextNo") : 1;
			
				if (nextOderNo >= 0) { 
					sb = new StringBuilder();
					sb.append(" INSERT INTO `orderpro` (`Orderid`, `ProductID`, `Quatity`, `CustomerID`, `orderNo`) VALUES (" + nextOderNo+ ", '"+order.getIdProduct()
					+"', '"+order.getQuatity()+"', '"+Customer.getCustomerID()+"','1') ");//�ͧ��� Order No
					int recResult = s.executeUpdate(sb.toString());
					if (recResult == 1) {
						// Order succcess.
						System.out.println("Order success.");
					} else {
						System.out.println("Order not success.");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*public void doOrderBill() {
		Product product = getSelectedProduct();
		if(product != null) {
			//printOutput(product);
			bill.populateData(product);
			
		}
	}*/
	
	public void doOrderProduct() {
		Product product = OrderQuatityDialog.showDialog(this, getSelectedProduct());
		if(product != null) {
			//printOutput(product);
			//send data to bucket panel
			bucketPanel.populateData(product);
			
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
	
	//public void 
	
	/*private void printOutput(Product product) {
		System.out.println("Nisit Code: " + product.getNisitcode());
		System.out.println("Name: " + product.getName());
		System.out.println("Surname: " + product.getLastname());
		System.out.println("GPA: " + product.getGpa());
		System.out.println("Teacher: " + product.getTeacher());
		System.out.println("Quatity: " + product.getQuatity());
	}*/

	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub

	}
}
