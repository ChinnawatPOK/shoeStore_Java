package com.application.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.application.beans.Product;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class DetailDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textCode_product;
	private JTextField textName;
	private JTextField text_Price;
	private JTextField text_Series;
	private JTextField text_Brand;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel LBpicture;
	private String pic;

	

	/**
	 * Create the dialog.
	 */
	public DetailDialog(java.awt.Frame parent, Product selectedProduct) {
		super(parent, true);
		System.out.println(selectedProduct.getPicture());
		setImage(selectedProduct.getPicture());
		initConponents();
		initial(selectedProduct);
		
	}
	
	private void initConponents() {
		setBounds(100, 100, 932, 712);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNisitCode = new JLabel("Brand :");
			lblNisitCode.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNisitCode.setBounds(539, 584, 66, 16);
			contentPanel.add(lblNisitCode);
		}
		{
			textCode_product = new JTextField();
			textCode_product.setEditable(false);
			textCode_product.setBounds(617, 581, 181, 22);
			textCode_product.setBackground ( Color.WHITE );
			textCode_product.setBorder ( BorderFactory.createLineBorder ( Color.white ) );
			contentPanel.add(textCode_product);
			textCode_product.setColumns(10);
		}
		{
			JLabel lblName = new JLabel("Name: ");
			lblName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblName.setBounds(539, 620, 66, 16);
			contentPanel.add(lblName);
		}
		{
			textName = new JTextField();
			textName.setEditable(false);
			textName.setColumns(10);
			textName.setBounds(627, 616, 181, 22);
			contentPanel.add(textName);
		}
		{
			JLabel LBpicture = new JLabel("");
			LBpicture.setBackground(Color.GREEN);
			LBpicture.setBounds(12, 93, 410, 525);
			contentPanel.add(LBpicture);
			showImage(getImage(),LBpicture);
		}
		
		
		text_Price = new JTextField();
		text_Price.setFont(new Font("Tahoma", Font.ITALIC, 19));
		text_Price.setEditable(false);
		text_Price.setText("");
		text_Price.setBounds(694, 93, 101, 50);
		text_Price.setBackground ( Color.WHITE );
		text_Price.setBorder ( BorderFactory.createLineBorder ( Color.white ) );
		contentPanel.add(text_Price);
		text_Price.setColumns(10);
		{
			text_Series = new JTextField();
			text_Series.setEditable(false);
			text_Series.setText("");
			text_Series.setFont(new Font("Tahoma", Font.BOLD, 30));
			text_Series.setBounds(445, 156, 457, 108);
			text_Series.setBackground ( Color.WHITE );
			text_Series.setBorder ( BorderFactory.createLineBorder ( Color.white ) );
			contentPanel.add(text_Series);
			text_Series.setColumns(10);
		}
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(575, 491, 181, 55);
		contentPanel.add(btnNewButton);
		{
			text_Brand = new JTextField();
			text_Brand.setEditable(false);
			text_Brand.setFont(new Font("Tahoma", Font.BOLD, 36));
			text_Brand.setBounds(100, 30, 220, 50);
			text_Brand.setBackground ( Color.WHITE );
			text_Brand.setBorder ( BorderFactory.createLineBorder ( Color.white ) );
			contentPanel.add(text_Brand);
			text_Brand.setColumns(10);
		}
		{
			lblNewLabel = new JLabel("\u0E23\u0E32\u0E04\u0E32 ");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lblNewLabel.setBounds(628, 102, 66, 31);
			contentPanel.add(lblNewLabel);
		}
		{
			lblNewLabel_1 = new JLabel("TBH");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lblNewLabel_1.setBounds(805, 104, 84, 26);
			contentPanel.add(lblNewLabel_1);
		}
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(455, 274, 343, 22);
		contentPanel.add(textPane);
	}
	
	private void initial(Product selectedProduct) {
		textCode_product.setText(selectedProduct.getIdProduct());
		text_Brand.setText(selectedProduct.getBrand());
		text_Price.setText(selectedProduct.getPrice());
		text_Series.setText(selectedProduct.getSeries());
		
		
		//textName.setText(selectedProduct.getName());
	}
	
	public static void showDialog(java.awt.Frame parent, Product selectedProduct) {
		DetailDialog dialog = new DetailDialog(parent, selectedProduct);
		dialog.setTitle("Product Detail");
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	private void setImage(String pic ){
		this.pic=pic;
	}
	private String getImage(){
		return this.pic;
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
