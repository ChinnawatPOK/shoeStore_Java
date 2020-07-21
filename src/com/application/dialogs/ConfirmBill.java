package com.application.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.application.menu.Receipt;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConfirmBill extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfirmBill dialog = new ConfirmBill();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			//dialog.setModal(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfirmBill() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		setBounds(((Double)(width / 2)).intValue() - 200, ((Double)(height / 2)).intValue() - 200
				, 495, 311);//x1,x2<x1>
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u0E22\u0E37\u0E19\u0E22\u0E31\u0E19\u0E01\u0E32\u0E23\u0E2A\u0E31\u0E48\u0E07\u0E0B\u0E37\u0E49\u0E2D\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32\u0E40\u0E23\u0E35\u0E22\u0E1A\u0E23\u0E49\u0E2D\u0E22\u0E41\u0E25\u0E49\u0E27");
			label.setFont(new Font("Tahoma", Font.BOLD, 27));
			label.setBounds(29, 31, 448, 54);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u0E25\u0E39\u0E01\u0E04\u0E49\u0E32\u0E15\u0E49\u0E2D\u0E07\u0E01\u0E32\u0E23\u0E14\u0E39\u0E43\u0E1A\u0E40\u0E2A\u0E23\u0E47\u0E08\u0E2B\u0E23\u0E37\u0E2D\u0E44\u0E21\u0E48");
			label.setFont(new Font("Tahoma", Font.ITALIC, 22));
			label.setBounds(48, 123, 331, 27);
			contentPanel.add(label);
		}
		{
			JButton btnYes = new JButton("YES");
			btnYes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					Receipt receipt=new Receipt();;
					receipt.setVisible(true);
				}
			});
			btnYes.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnYes.setBounds(109, 185, 103, 36);
			contentPanel.add(btnYes);
		}
		{
			JButton btnNO = new JButton("NO");
			btnNO.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			btnNO.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnNO.setBounds(276, 185, 103, 36);
			contentPanel.add(btnNO);
		}
	}

}
