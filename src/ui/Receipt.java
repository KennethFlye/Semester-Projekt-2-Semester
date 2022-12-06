package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JTable;

public class Receipt extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel dtm;
	private JTable tableReceipt;
	
	private String[] columnNames = {"1"};
	//114 Line
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ArrayList<String> info = new ArrayList<>();
			info.add("Hej");
			info.add("Med");
			info.add("Dig");
			Receipt dialog = new Receipt(info);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Receipt(ArrayList<String> receiptInfo) {
		setBounds(100, 100, 450, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			tableReceipt = new JTable();
			
			dtm = new DefaultTableModel(0,0);
			dtm.setColumnIdentifiers(columnNames);
			
			tableReceipt.setModel(dtm);
			
			contentPanel.add(tableReceipt);
			
			tableReceipt.getColumnModel().getColumn(0).setMinWidth(400); //Gør Større
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				okButton.addActionListener((e) -> handleOkayClickedEvent());
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				cancelButton.addActionListener((e) -> handleCancelClickedEvent());
			}
		}
		
		
		
		for(int i = 0; i < receiptInfo.size(); i++) {
			dtm.addRow(new Object[] {receiptInfo.get(i)});
		}
		
	}

	private void handleCancelClickedEvent() {
		this.dispose();
	}

	private void handleOkayClickedEvent() {
		this.dispose();
	}

}
