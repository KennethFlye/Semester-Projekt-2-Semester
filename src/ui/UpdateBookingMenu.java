package ui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JTable;

public class UpdateBookingMenu extends JFrame {
	private JTextField txtSearchDate;
	private JTable table;
	public UpdateBookingMenu() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 45, 45, 261, 0, 85, 0};
		gbl_topPanel.rowHeights = new int[]{0, 21, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		JLabel lblUpdateBookingHeader = new JLabel("New label");
		GridBagConstraints gbc_lblUpdateBookingHeader = new GridBagConstraints();
		gbc_lblUpdateBookingHeader.anchor = GridBagConstraints.WEST;
		gbc_lblUpdateBookingHeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpdateBookingHeader.gridx = 1;
		gbc_lblUpdateBookingHeader.gridy = 0;
		topPanel.add(lblUpdateBookingHeader, gbc_lblUpdateBookingHeader);
		
		JLabel lblSearchDate = new JLabel("Search date:");
		GridBagConstraints gbc_lblSearchDate = new GridBagConstraints();
		gbc_lblSearchDate.anchor = GridBagConstraints.EAST;
		gbc_lblSearchDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblSearchDate.gridx = 2;
		gbc_lblSearchDate.gridy = 1;
		topPanel.add(lblSearchDate, gbc_lblSearchDate);
		
		txtSearchDate = new JTextField();
		GridBagConstraints gbc_txtSearchDate = new GridBagConstraints();
		gbc_txtSearchDate.insets = new Insets(0, 0, 0, 5);
		gbc_txtSearchDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearchDate.gridx = 3;
		gbc_txtSearchDate.gridy = 1;
		topPanel.add(txtSearchDate, gbc_txtSearchDate);
		txtSearchDate.setColumns(10);
		
		JButton btnSearchDate = new JButton("Search");
		GridBagConstraints gbc_btnSearchDate = new GridBagConstraints();
		gbc_btnSearchDate.insets = new Insets(0, 0, 0, 5);
		gbc_btnSearchDate.anchor = GridBagConstraints.WEST;
		gbc_btnSearchDate.gridx = 4;
		gbc_btnSearchDate.gridy = 1;
		topPanel.add(btnSearchDate, gbc_btnSearchDate);
		
		JPanel bottomPanel = new JPanel();
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		JButton btnAccept = new JButton("Godkend");
		bottomPanel.add(btnAccept);
		
		JButton btnCancel = new JButton("Annuller");
		bottomPanel.add(btnCancel);
		
		table = new JTable();
		getContentPane().add(table, BorderLayout.CENTER);
	}

}
