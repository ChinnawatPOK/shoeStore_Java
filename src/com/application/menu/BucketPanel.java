package com.application.menu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.application.beans.Product;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class BucketPanel extends JPanel implements TableModelListener {
	
	private JTable tableBucket;
	private JButton btnDelete;
	
	private DefaultTableModel tableModel;
	private DefaultTableModel tableProductModel;

	//private DefaultTableModel tableBucket;
	private Map<Integer, Product> productMap = new HashMap<Integer, Product>();;
	private final int COL_ID_PRODUCT = 0;
	private final int COL_BRAND = 1;
	private final int COL_TYPE = 2;
	private final int COL_SERIES = 3;
	private final int COL_PRICE = 4;
	private final int COL_QUATITY = 5;
	

	/**
	 * Create the panel.
	 */
	public BucketPanel() {
		initComponents();
		setTableRenderer();
	}
	
	private void initComponents() {
		setBorder(new TitledBorder(null, "Order Bucket", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		JScrollPane scrollPanetableBucket = new JScrollPane();
		scrollPanetableBucket.setBounds(12, 29, 873, 255);
		add(scrollPanetableBucket);
		
		tableBucket = new JTable();
		
		tableBucket = new javax.swing.JTable();
		tableBucket.setAutoCreateRowSorter(true);
		tableBucket.setModel(new javax.swing.table.DefaultTableModel(
			new Object[][] {
			}
			, new String[] { "ProductID", "Brand", "Type", "Series", "Price","Quatity"}) {
				boolean[] canEdit = new boolean[] { false, false, false, false, false, false,false };
				Class[] type = new Class[] { String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class};

				public Class getColumnClass(int columnIndex) {
					return type[columnIndex];
				}
	
				public boolean isCellEditTable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			});
		tableBucket.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPanetableBucket.setViewportView(tableBucket);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDelete();
			}
		});
		btnDelete.setBounds(897, 97, 82, 25);
		add(btnDelete);
	}
	
	private void setTableRenderer() {
		tableBucket.getSelectionModel().addListSelectionListener((ListSelectionEvent e) ->{
			btnDelete.setEnabled(tableBucket.getSelectedRowCount() > 0);
			
		});
		
		tableModel = (DefaultTableModel) tableBucket.getModel();
		tableBucket.getTableHeader().setResizingAllowed(true);
		tableBucket.getTableHeader().setReorderingAllowed(true);
		tableBucket.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Set column width
		tableBucket.getColumnModel().getColumn(COL_ID_PRODUCT).setPreferredWidth(200);
		tableBucket.getModel().addTableModelListener(this);
	}
	
	public void populateData(Product product) {
		System.out.println(calPrice(product));
		product.setTotalPrice(calPrice(product));
		int maxRow = tableBucket.getRowCount();//ดูว่าตารางข้างล่าง มีแถวทั้งหมดกี่แถว
		// put to Map ---> productMap.put(maxRow, product);
		productMap.put(maxRow, product);//เอาค่าต่างที่เลือกมาแล้ว แล้วกดorder มาset ลงmap

		tableModel.insertRow(maxRow, new Object[tableModel.getColumnCount()]);//????
		setValueAtTable(product, maxRow);
		tableBucket.clearSelection();
	}
	
	private double calPrice(Product product){
		return (product.getQuatity()*Double.parseDouble(product.getPrice()));
	}

	private void setValueAtTable(Product product, int row) {
		tableModel.setValueAt(product.getIdProduct(), row, COL_ID_PRODUCT);
		tableModel.setValueAt(product.getBrand(), row, COL_BRAND);
		tableModel.setValueAt(product.getType(), row, COL_TYPE);
		tableModel.setValueAt(product.getSeries(), row, COL_SERIES);
		tableModel.setValueAt(product.getTotalPrice(), row, COL_PRICE);
		tableModel.setValueAt(product.getQuatity(), row, COL_QUATITY);	
	}

	public Product[] getAllBucketOrder() { 
		ArrayList<Product> oderList = new ArrayList<Product>();//ArrayList<typeNAme> NameArray = new ArrayList<typeNAme>()
		int rowCount = tableBucket.getRowCount();//แถวที่order มาใส่ตารางข้างล่างว่ามีกี่แถว
		for(int i = 0; i < rowCount; i++) {
			int modelRowIndex = tableBucket.convertRowIndexToModel(i);
			Product order = (Product) productMap.get(modelRowIndex);
			oderList.add(order);
		}
		return oderList.toArray(new Product[oderList.size()]);
	}
	
	private void doDelete() {
		if (tableModel != null) {//ทำไมมใช้ tableBucket ไม่ได้ทำไมต้องสร้าง model
			int tableRowSelect = tableBucket.getSelectedRow();
			if (tableRowSelect >= 0) {
				tableModel.removeRow(tableRowSelect);
			}
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}

}
