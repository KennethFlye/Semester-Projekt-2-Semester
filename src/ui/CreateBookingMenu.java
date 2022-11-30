package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class CreateBookingMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCustomerPhone;
	private JTextField textFieldPhoneNo;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JTextField textFieldBirthDate;
	private JTextField textFieldAddress;
	private JTextField textFieldZipcode;
	private JTextField textFieldCity;
	private JTextField textFieldCountry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateBookingMenu frame = new CreateBookingMenu();
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
	public CreateBookingMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel northPanelWest = new JPanel();
		northPanel.add(northPanelWest, BorderLayout.NORTH);
		northPanelWest.setLayout(new BorderLayout(0, 0));
		
		JToggleButton tglbtnOverview = new JToggleButton("≡");
		tglbtnOverview.setFont(new Font("Tahoma", Font.BOLD, 11));
		northPanelWest.add(tglbtnOverview, BorderLayout.WEST);
		
		JButton btnExit = new JButton("X");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		northPanelWest.add(btnExit, BorderLayout.EAST);
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanelWest = new JPanel();
		centerPanel.add(centerPanelWest, BorderLayout.WEST);
		centerPanelWest.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		centerPanelWest.add(panel, BorderLayout.NORTH);
		
		textFieldCustomerPhone = new JTextField();
		textFieldCustomerPhone.setText("Tlf.");
		panel.add(textFieldCustomerPhone);
		textFieldCustomerPhone.setColumns(10);
		
		JButton btnSearchForCustomer = new JButton("Søg");
		panel.add(btnSearchForCustomer);
		
		JPanel panel_1 = new JPanel();
		centerPanelWest.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblPhoneNo = new JLabel("TLF*");
		GridBagConstraints gbc_lblPhoneNo = new GridBagConstraints();
		gbc_lblPhoneNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNo.gridx = 0;
		gbc_lblPhoneNo.gridy = 0;
		panel_1.add(lblPhoneNo, gbc_lblPhoneNo);
		
		textFieldPhoneNo = new JTextField();
		GridBagConstraints gbc_textFieldPhoneNo = new GridBagConstraints();
		gbc_textFieldPhoneNo.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPhoneNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPhoneNo.gridx = 1;
		gbc_textFieldPhoneNo.gridy = 0;
		panel_1.add(textFieldPhoneNo, gbc_textFieldPhoneNo);
		textFieldPhoneNo.setColumns(20);
		
		JLabel lblName = new JLabel("Name*");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		panel_1.add(lblName, gbc_lblName);
		
		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 1;
		panel_1.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 2;
		panel_1.add(lblEmail, gbc_lblEmail);
		
		textFieldEmail = new JTextField();
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 2;
		panel_1.add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblBirthDate = new JLabel("Fødselsdag");
		GridBagConstraints gbc_lblBirthDate = new GridBagConstraints();
		gbc_lblBirthDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirthDate.gridx = 0;
		gbc_lblBirthDate.gridy = 3;
		panel_1.add(lblBirthDate, gbc_lblBirthDate);
		
		textFieldBirthDate = new JTextField();
		GridBagConstraints gbc_textFieldBirthDate = new GridBagConstraints();
		gbc_textFieldBirthDate.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldBirthDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBirthDate.gridx = 1;
		gbc_textFieldBirthDate.gridy = 3;
		panel_1.add(textFieldBirthDate, gbc_textFieldBirthDate);
		textFieldBirthDate.setColumns(10);
		
		JLabel lblAddress = new JLabel("Adresse");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 4;
		panel_1.add(lblAddress, gbc_lblAddress);
		
		textFieldAddress = new JTextField();
		GridBagConstraints gbc_textFieldAddress = new GridBagConstraints();
		gbc_textFieldAddress.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAddress.gridx = 1;
		gbc_textFieldAddress.gridy = 4;
		panel_1.add(textFieldAddress, gbc_textFieldAddress);
		textFieldAddress.setColumns(10);
		
		JLabel lblZipcode = new JLabel("Postnummer");
		GridBagConstraints gbc_lblZipcode = new GridBagConstraints();
		gbc_lblZipcode.insets = new Insets(0, 0, 5, 5);
		gbc_lblZipcode.gridx = 0;
		gbc_lblZipcode.gridy = 5;
		panel_1.add(lblZipcode, gbc_lblZipcode);
		
		textFieldZipcode = new JTextField();
		GridBagConstraints gbc_textFieldZipcode = new GridBagConstraints();
		gbc_textFieldZipcode.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldZipcode.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldZipcode.gridx = 1;
		gbc_textFieldZipcode.gridy = 5;
		panel_1.add(textFieldZipcode, gbc_textFieldZipcode);
		textFieldZipcode.setColumns(10);
		
		JLabel lblCity = new JLabel("By");
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.gridx = 0;
		gbc_lblCity.gridy = 6;
		panel_1.add(lblCity, gbc_lblCity);
		
		textFieldCity = new JTextField();
		GridBagConstraints gbc_textFieldCity = new GridBagConstraints();
		gbc_textFieldCity.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCity.gridx = 1;
		gbc_textFieldCity.gridy = 6;
		panel_1.add(textFieldCity, gbc_textFieldCity);
		textFieldCity.setColumns(10);
		
		JLabel lblCountry = new JLabel("Land");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 0;
		gbc_lblCountry.gridy = 7;
		panel_1.add(lblCountry, gbc_lblCountry);
		
		textFieldCountry = new JTextField();
		GridBagConstraints gbc_textFieldCountry = new GridBagConstraints();
		gbc_textFieldCountry.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCountry.gridx = 1;
		gbc_textFieldCountry.gridy = 7;
		panel_1.add(textFieldCountry, gbc_textFieldCountry);
		textFieldCountry.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 8;
		panel_1.add(panel_2, gbc_panel_2);
		
		JButton btnAddCustomer = new JButton("Tilføj");
		panel_2.add(btnAddCustomer);
		
		JButton btnResetCustomer = new JButton("Reset");
		panel_2.add(btnResetCustomer);
		
		JPanel centerPanelEast = new JPanel();
		centerPanel.add(centerPanelEast, BorderLayout.EAST);
	}

}
