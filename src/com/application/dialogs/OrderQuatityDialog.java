package com.application.dialogs;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.application.beans.Product;
import com.application.utility.NumberFormatDocument;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderQuatityDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Product selectedProduct;
	private JTextField textQuatity;
	private JTextField textCode;

	/**
	 * Create the dialog.
	 */
	public OrderQuatityDialog(java.awt.Frame parent, Product selectedProduct) {
		super(parent, true);
		initConponents();
		this.selectedProduct = selectedProduct;
		textCode.setText(selectedProduct.getIdProduct());//selectedProductเปรียบเสมือน Atthor.?
		
		textQuatity.setDocument(new NumberFormatDocument(3));//??????
		
	}
	
	private void initConponents() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 432, 218);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener (new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});
		
		contentPanel.setLayout(null);
		{
			textQuatity = new JTextField();
			textQuatity.setBounds(153, 107, 116, 22);
			contentPanel.add(textQuatity);
			textQuatity.setColumns(10);
		}
		{
			JLabel lblQuatity = new JLabel("Quatity:");
			lblQuatity.setBounds(62, 110, 64, 16);
			contentPanel.add(lblQuatity);
		}
		{
			textCode = new JTextField();
			textCode.setEditable(false);
			textCode.setBounds(153, 76, 116, 22);
			contentPanel.add(textCode);
			textCode.setColumns(10);
		}
		{
			JLabel lblNisitCode = new JLabel("Nisit Code: ");
			lblNisitCode.setBounds(29, 79, 105, 16);
			contentPanel.add(lblNisitCode);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 218, 432, 35);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Order");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						doOrder();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		this.selectedProduct = null;
		closeDialog();
	}
	
	private void doOrder() {
		this.selectedProduct.setQuatity((textQuatity.getText().length() > 0) ? Integer.parseInt(textQuatity.getText()) : 0);
		closeDialog();
	}
	
	private void closeDialog() {
		setVisible(false);
		dispose();
	}
	
	public static Product showDialog(java.awt.Frame parent, Product selectedProduct) {
		OrderQuatityDialog dialog = new OrderQuatityDialog(parent, selectedProduct);
		dialog.setTitle("Order Product Quatity");
		dialog.setLocationRelativeTo(null);  //ให้ dialog แสดงตรงกลางหน้าจอ
		dialog.setVisible(true);
		return dialog.selectedProduct;
	}

}
