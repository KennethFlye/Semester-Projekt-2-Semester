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
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

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
	private JTextField textFieldAmountOfPeople;
	private JTextField textField;

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
		GridBagLayout gbl_centerPanelEast = new GridBagLayout();
		gbl_centerPanelEast.columnWidths = new int[]{0, 0, 0};
		gbl_centerPanelEast.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerPanelEast.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanelEast.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanelEast.setLayout(gbl_centerPanelEast);
		
		JLabel lblBookingType = new JLabel("Booking Type");
		GridBagConstraints gbc_lblBookingType = new GridBagConstraints();
		gbc_lblBookingType.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingType.gridx = 0;
		gbc_lblBookingType.gridy = 0;
		centerPanelEast.add(lblBookingType, gbc_lblBookingType);
		
		JComboBox comboBoxBookingType = new JComboBox();
		GridBagConstraints gbc_comboBoxBookingType = new GridBagConstraints();
		gbc_comboBoxBookingType.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxBookingType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxBookingType.gridx = 1;
		gbc_comboBoxBookingType.gridy = 0;
		centerPanelEast.add(comboBoxBookingType, gbc_comboBoxBookingType);
		
		JLabel lblAmountOfPeople = new JLabel("Antal Personer");
		GridBagConstraints gbc_lblAmountOfPeople = new GridBagConstraints();
		gbc_lblAmountOfPeople.anchor = GridBagConstraints.EAST;
		gbc_lblAmountOfPeople.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmountOfPeople.gridx = 0;
		gbc_lblAmountOfPeople.gridy = 1;
		centerPanelEast.add(lblAmountOfPeople, gbc_lblAmountOfPeople);
		
		textFieldAmountOfPeople = new JTextField();
		GridBagConstraints gbc_textFieldAmountOfPeople = new GridBagConstraints();
		gbc_textFieldAmountOfPeople.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAmountOfPeople.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAmountOfPeople.gridx = 1;
		gbc_textFieldAmountOfPeople.gridy = 1;
		centerPanelEast.add(textFieldAmountOfPeople, gbc_textFieldAmountOfPeople);
		textFieldAmountOfPeople.setColumns(10);
		
		JLabel lblTimeSlot = new JLabel("Tidspunkt");
		GridBagConstraints gbc_lblTimeSlot = new GridBagConstraints();
		gbc_lblTimeSlot.anchor = GridBagConstraints.EAST;
		gbc_lblTimeSlot.insets = new Insets(0, 0, 5, 5);
		gbc_lblTimeSlot.gridx = 0;
		gbc_lblTimeSlot.gridy = 2;
		centerPanelEast.add(lblTimeSlot, gbc_lblTimeSlot);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		centerPanelEast.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblEventTime = new JLabel("Event Tid");
		GridBagConstraints gbc_lblEventTime = new GridBagConstraints();
		gbc_lblEventTime.anchor = GridBagConstraints.EAST;
		gbc_lblEventTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblEventTime.gridx = 0;
		gbc_lblEventTime.gridy = 3;
		centerPanelEast.add(lblEventTime, gbc_lblEventTime);
		
		JComboBox comboBoxEventTime = new JComboBox();
		GridBagConstraints gbc_comboBoxEventTime = new GridBagConstraints();
		gbc_comboBoxEventTime.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxEventTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEventTime.gridx = 1;
		gbc_comboBoxEventTime.gridy = 3;
		centerPanelEast.add(comboBoxEventTime, gbc_comboBoxEventTime);
		
		JLabel lblRaceType = new JLabel("Løbstype");
		GridBagConstraints gbc_lblRaceType = new GridBagConstraints();
		gbc_lblRaceType.anchor = GridBagConstraints.EAST;
		gbc_lblRaceType.insets = new Insets(0, 0, 5, 5);
		gbc_lblRaceType.gridx = 0;
		gbc_lblRaceType.gridy = 4;
		centerPanelEast.add(lblRaceType, gbc_lblRaceType);
		
		JComboBox comboBoxRaceType = new JComboBox();
		GridBagConstraints gbc_comboBoxRaceType = new GridBagConstraints();
		gbc_comboBoxRaceType.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxRaceType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRaceType.gridx = 1;
		gbc_comboBoxRaceType.gridy = 4;
		centerPanelEast.add(comboBoxRaceType, gbc_comboBoxRaceType);
		
		JLabel lblCatering = new JLabel("Mad");
		GridBagConstraints gbc_lblCatering = new GridBagConstraints();
		gbc_lblCatering.insets = new Insets(0, 0, 5, 5);
		gbc_lblCatering.gridx = 0;
		gbc_lblCatering.gridy = 5;
		centerPanelEast.add(lblCatering, gbc_lblCatering);
		
		JRadioButton rdbtnCatering = new JRadioButton("");
		GridBagConstraints gbc_rdbtnCatering = new GridBagConstraints();
		gbc_rdbtnCatering.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnCatering.gridx = 1;
		gbc_rdbtnCatering.gridy = 5;
		centerPanelEast.add(rdbtnCatering, gbc_rdbtnCatering);
		
		JLabel lblCateringMenu = new JLabel("Mad Type");
		GridBagConstraints gbc_lblCateringMenu = new GridBagConstraints();
		gbc_lblCateringMenu.anchor = GridBagConstraints.EAST;
		gbc_lblCateringMenu.insets = new Insets(0, 0, 5, 5);
		gbc_lblCateringMenu.gridx = 0;
		gbc_lblCateringMenu.gridy = 6;
		centerPanelEast.add(lblCateringMenu, gbc_lblCateringMenu);
		
		JComboBox comboBoxFoodType = new JComboBox();
		GridBagConstraints gbc_comboBoxFoodType = new GridBagConstraints();
		gbc_comboBoxFoodType.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxFoodType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFoodType.gridx = 1;
		gbc_comboBoxFoodType.gridy = 6;
		centerPanelEast.add(comboBoxFoodType, gbc_comboBoxFoodType);
		
		JButton btnAccept = new JButton("Godkend");
		GridBagConstraints gbc_btnAccept = new GridBagConstraints();
		gbc_btnAccept.insets = new Insets(0, 0, 0, 5);
		gbc_btnAccept.gridx = 0;
		gbc_btnAccept.gridy = 9;
		centerPanelEast.add(btnAccept, gbc_btnAccept);
		
		JButton btnCancel = new JButton("Annuller");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 9;
		centerPanelEast.add(btnCancel, gbc_btnCancel);
	}

}
