package ui;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BookingCtrl;
import database.DataAccessException;
import model.CateringMenu.EnumMenu;
import model.Customer;
import model.EventType.EnumType;
import model.PatternCheck;

import javax.swing.JToggleButton;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

public class CreateBookingMenu extends JFrame {

	private JPanel contentPane;
	
	private JLabel lblRaceType, lblCateringMenu, lblCatering, lblAmountOfPeople, lblTImeSlotEvent, lblEventTime, lblTimeSlotGokart;
	
	private JButton btnAddCateringMenu, btnChooseTimeSlotEvent, btnChooseTimeSlotGokart, btnAddCustomer;
	
	private JRadioButton rdbtnCatering;
	
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
	private JTextField textFieldTimeSlotGokart;
	
	private JComboBox comboBoxBookingType;
	private JComboBox comboBoxRaceType;
	private JComboBox comboBoxEventTime;
	private JComboBox comboBoxFoodType;
	
	private LocalDateTime startTimeGokart, finishTimeGokart;
	
	
	private BookingCtrl bookingCtrl;
	
	private String[] bookingTypes = {"Gokart & Event Pakke", "Gokart", "Event"};
	private String[] raceTypes = {EnumType.FORMULA_1.getLabel(), EnumType.LARGE_FORMULA_1.getLabel(), EnumType.LE_MANS_1_HOUR.getLabel()};
	private String[] eventLength = {EnumType.EVENT_HALL_1_HOUR.getLabel(), EnumType.EVENT_HALL_1_AND_HALF_HOUR.getLabel(), EnumType.EVENT_HALL_2_HOURS.getLabel()};
	private String[] foodTypes = {EnumMenu.CHICKEN.getLabel(), EnumMenu.EGGS.getLabel(), EnumMenu.FRIKADEL.getLabel()};
	private JTextField textFieldTimeSlotEvent;
	
	private boolean peopleAdded;

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
		setBounds(100, 100, 913, 343);
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
		textFieldCustomerPhone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldCustomerPhone.setText("");
			}
		});
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
		
		btnAddCustomer = new JButton("Tilføj");
		panel_2.add(btnAddCustomer);
		
		JButton btnResetCustomer = new JButton("Reset");
		panel_2.add(btnResetCustomer);
		
		JPanel centerPanelEast = new JPanel();
		centerPanel.add(centerPanelEast, BorderLayout.EAST);
		GridBagLayout gbl_centerPanelEast = new GridBagLayout();
		gbl_centerPanelEast.columnWidths = new int[]{0, 0, 0, 0};
		gbl_centerPanelEast.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerPanelEast.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_centerPanelEast.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanelEast.setLayout(gbl_centerPanelEast);
		
		JLabel lblBookingType = new JLabel("Booking Type");
		GridBagConstraints gbc_lblBookingType = new GridBagConstraints();
		gbc_lblBookingType.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingType.gridx = 0;
		gbc_lblBookingType.gridy = 0;
		centerPanelEast.add(lblBookingType, gbc_lblBookingType);
		
		comboBoxBookingType = new JComboBox(bookingTypes);
		GridBagConstraints gbc_comboBoxBookingType = new GridBagConstraints();
		gbc_comboBoxBookingType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxBookingType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxBookingType.gridx = 1;
		gbc_comboBoxBookingType.gridy = 0;
		centerPanelEast.add(comboBoxBookingType, gbc_comboBoxBookingType);
		
		lblAmountOfPeople = new JLabel("Antal Personer");
		GridBagConstraints gbc_lblAmountOfPeople = new GridBagConstraints();
		gbc_lblAmountOfPeople.anchor = GridBagConstraints.EAST;
		gbc_lblAmountOfPeople.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmountOfPeople.gridx = 0;
		gbc_lblAmountOfPeople.gridy = 1;
		centerPanelEast.add(lblAmountOfPeople, gbc_lblAmountOfPeople);
		
		textFieldAmountOfPeople = new JTextField();
		GridBagConstraints gbc_textFieldAmountOfPeople = new GridBagConstraints();
		gbc_textFieldAmountOfPeople.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAmountOfPeople.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAmountOfPeople.gridx = 1;
		gbc_textFieldAmountOfPeople.gridy = 1;
		centerPanelEast.add(textFieldAmountOfPeople, gbc_textFieldAmountOfPeople);
		textFieldAmountOfPeople.setColumns(10);
		
		JButton btnAddAmountOfPeople = new JButton("Tilføj");
		GridBagConstraints gbc_btnAddAmountOfPeople = new GridBagConstraints();
		gbc_btnAddAmountOfPeople.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddAmountOfPeople.gridx = 2;
		gbc_btnAddAmountOfPeople.gridy = 1;
		centerPanelEast.add(btnAddAmountOfPeople, gbc_btnAddAmountOfPeople);
		btnAddAmountOfPeople.addActionListener((e) -> handleAddAmountOfPeopleEvent());
		
		lblRaceType = new JLabel("Løbstype");
		GridBagConstraints gbc_lblRaceType = new GridBagConstraints();
		gbc_lblRaceType.anchor = GridBagConstraints.EAST;
		gbc_lblRaceType.insets = new Insets(0, 0, 5, 5);
		gbc_lblRaceType.gridx = 0;
		gbc_lblRaceType.gridy = 2;
		centerPanelEast.add(lblRaceType, gbc_lblRaceType);
		
		comboBoxRaceType = new JComboBox(raceTypes);
		GridBagConstraints gbc_comboBoxRaceType = new GridBagConstraints();
		gbc_comboBoxRaceType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxRaceType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRaceType.gridx = 1;
		gbc_comboBoxRaceType.gridy = 2;
		centerPanelEast.add(comboBoxRaceType, gbc_comboBoxRaceType);
		
		lblEventTime = new JLabel("Event Tid");
		GridBagConstraints gbc_lblEventTime = new GridBagConstraints();
		gbc_lblEventTime.anchor = GridBagConstraints.EAST;
		gbc_lblEventTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblEventTime.gridx = 0;
		gbc_lblEventTime.gridy = 3;
		centerPanelEast.add(lblEventTime, gbc_lblEventTime);
		
		comboBoxEventTime = new JComboBox(eventLength);
		GridBagConstraints gbc_comboBoxEventTime = new GridBagConstraints();
		gbc_comboBoxEventTime.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEventTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEventTime.gridx = 1;
		gbc_comboBoxEventTime.gridy = 3;
		centerPanelEast.add(comboBoxEventTime, gbc_comboBoxEventTime);
		
		lblTimeSlotGokart = new JLabel("Tidspunkt Gokart");
		GridBagConstraints gbc_lblTimeSlotGokart = new GridBagConstraints();
		gbc_lblTimeSlotGokart.anchor = GridBagConstraints.EAST;
		gbc_lblTimeSlotGokart.insets = new Insets(0, 0, 5, 5);
		gbc_lblTimeSlotGokart.gridx = 0;
		gbc_lblTimeSlotGokart.gridy = 4;
		centerPanelEast.add(lblTimeSlotGokart, gbc_lblTimeSlotGokart);
		
		textFieldTimeSlotGokart = new JTextField();
		GridBagConstraints gbc_textFieldTimeSlotGokart = new GridBagConstraints();
		gbc_textFieldTimeSlotGokart.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTimeSlotGokart.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTimeSlotGokart.gridx = 1;
		gbc_textFieldTimeSlotGokart.gridy = 4;
		centerPanelEast.add(textFieldTimeSlotGokart, gbc_textFieldTimeSlotGokart);
		textFieldTimeSlotGokart.setColumns(30);
		textFieldTimeSlotGokart.setEditable(false);
		
		btnChooseTimeSlotGokart = new JButton("Vælg Tidspunkt");
		btnChooseTimeSlotGokart.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_btnChooseTimeSlotGokart = new GridBagConstraints();
		gbc_btnChooseTimeSlotGokart.insets = new Insets(0, 0, 5, 0);
		gbc_btnChooseTimeSlotGokart.gridx = 2;
		gbc_btnChooseTimeSlotGokart.gridy = 4;
		centerPanelEast.add(btnChooseTimeSlotGokart, gbc_btnChooseTimeSlotGokart);
		btnChooseTimeSlotGokart.addActionListener((e) -> handleTimeSlotEventGokart());
		
		
		lblTImeSlotEvent = new JLabel("Tidspunkt Event");
		GridBagConstraints gbc_lblTImeSlotEvent = new GridBagConstraints();
		gbc_lblTImeSlotEvent.anchor = GridBagConstraints.EAST;
		gbc_lblTImeSlotEvent.insets = new Insets(0, 0, 5, 5);
		gbc_lblTImeSlotEvent.gridx = 0;
		gbc_lblTImeSlotEvent.gridy = 5;
		centerPanelEast.add(lblTImeSlotEvent, gbc_lblTImeSlotEvent);
		
		textFieldTimeSlotEvent = new JTextField();
		GridBagConstraints gbc_textFieldTimeSlotEvent = new GridBagConstraints();
		gbc_textFieldTimeSlotEvent.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTimeSlotEvent.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTimeSlotEvent.gridx = 1;
		gbc_textFieldTimeSlotEvent.gridy = 5;
		centerPanelEast.add(textFieldTimeSlotEvent, gbc_textFieldTimeSlotEvent);
		textFieldTimeSlotEvent.setColumns(30);
		
		textFieldTimeSlotEvent.setEditable(false);
		
		btnChooseTimeSlotEvent = new JButton("Vælg Tidspunkt");
		btnChooseTimeSlotEvent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_btnChooseTimeSlotEvent = new GridBagConstraints();
		gbc_btnChooseTimeSlotEvent.insets = new Insets(0, 0, 5, 0);
		gbc_btnChooseTimeSlotEvent.gridx = 2;
		gbc_btnChooseTimeSlotEvent.gridy = 5;
		centerPanelEast.add(btnChooseTimeSlotEvent, gbc_btnChooseTimeSlotEvent);
		btnChooseTimeSlotEvent.addActionListener((e) -> handleTimeSlotEventEvent());
		
		
		lblCatering = new JLabel("Mad");
		GridBagConstraints gbc_lblCatering = new GridBagConstraints();
		gbc_lblCatering.insets = new Insets(0, 0, 5, 5);
		gbc_lblCatering.gridx = 0;
		gbc_lblCatering.gridy = 6;
		centerPanelEast.add(lblCatering, gbc_lblCatering);
		
		rdbtnCatering = new JRadioButton("");
		GridBagConstraints gbc_rdbtnCatering = new GridBagConstraints();
		gbc_rdbtnCatering.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnCatering.gridx = 1;
		gbc_rdbtnCatering.gridy = 6;
		centerPanelEast.add(rdbtnCatering, gbc_rdbtnCatering);
		
		lblCateringMenu = new JLabel("Mad Type");
		GridBagConstraints gbc_lblCateringMenu = new GridBagConstraints();
		gbc_lblCateringMenu.anchor = GridBagConstraints.EAST;
		gbc_lblCateringMenu.insets = new Insets(0, 0, 5, 5);
		gbc_lblCateringMenu.gridx = 0;
		gbc_lblCateringMenu.gridy = 7;
		centerPanelEast.add(lblCateringMenu, gbc_lblCateringMenu);
		
		comboBoxFoodType = new JComboBox(foodTypes);
		GridBagConstraints gbc_comboBoxFoodType = new GridBagConstraints();
		gbc_comboBoxFoodType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFoodType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFoodType.gridx = 1;
		gbc_comboBoxFoodType.gridy = 7;
		centerPanelEast.add(comboBoxFoodType, gbc_comboBoxFoodType);
		
		btnAddCateringMenu = new JButton("Tilføj");
		GridBagConstraints gbc_btnAddCateringMenu = new GridBagConstraints();
		gbc_btnAddCateringMenu.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddCateringMenu.gridx = 2;
		gbc_btnAddCateringMenu.gridy = 7;
		centerPanelEast.add(btnAddCateringMenu, gbc_btnAddCateringMenu);
		
		JButton btnAccept = new JButton("Godkend");
		GridBagConstraints gbc_btnAccept = new GridBagConstraints();
		gbc_btnAccept.insets = new Insets(0, 0, 0, 5);
		gbc_btnAccept.gridx = 0;
		gbc_btnAccept.gridy = 9;
		centerPanelEast.add(btnAccept, gbc_btnAccept);
		
		JButton btnCancel = new JButton("Annuller");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 9;
		centerPanelEast.add(btnCancel, gbc_btnCancel);
		
		
		//Buttons Action Listener
		btnExit.addActionListener((e) -> handleExitEvent());	
		btnSearchForCustomer.addActionListener((e) -> handleSearchForCustomerEvent());
		btnAddCustomer.addActionListener((e) -> handleAddCustomerEvent());
		btnResetCustomer.addActionListener((e) -> handleResetCustomerEvent());
		btnAddCateringMenu.addActionListener((e) -> handleAddCateringMenuEvent());
		btnAccept.addActionListener((e) -> handleAcceptBookingEvent());
		btnCancel.addActionListener((e) -> handleCancelBookingEvent());
		
		//ComboBox
		comboBoxBookingType.addActionListener((e) -> handleBookingTypeEvent());
				
		//Radio Button
		rdbtnCatering.addActionListener((e) -> handleCateringToggleEvent());
		
		
		//Use Case
		try {
			bookingCtrl = new BookingCtrl();
			
			bookingCtrl.createBooking();
		} catch (DataAccessException e1) {
			// TODO Giv besked om hvad der gik galt
			e1.printStackTrace();
		}
		
		setCateringVisibility(false);
		
	}

	private void handleCateringToggleEvent() {
		// TODO Auto-generated method stub
		
		if(rdbtnCatering.getSelectedObjects() == null) {
			
			setCateringVisibility(false);
			
		}
		else {
			
			setCateringVisibility(true);
			
		}
		
	}

	private void handleBookingTypeEvent() {
		// TODO Auto-generated method stub
		if(comboBoxBookingType.getSelectedItem().toString().equals("Gokart & Event Pakke")) {
			
			setEventVisibility(true);
			setGokartVisibility(true);
			
		}
		else if(comboBoxBookingType.getSelectedItem().toString().equals("Gokart")) {
			
			setEventVisibility(false);
			setGokartVisibility(true);
			
			
		}
		else if(comboBoxBookingType.getSelectedItem().toString().equals("Event")) {
			
			setEventVisibility(true);
			setGokartVisibility(false);

		}
	}
	
	private void setEventVisibility(boolean visibility) {
		
		comboBoxEventTime.setVisible(visibility);
		lblEventTime.setVisible(visibility);
		
		textFieldTimeSlotEvent.setVisible(visibility);
		lblTImeSlotEvent.setVisible(visibility);
		btnChooseTimeSlotEvent.setVisible(visibility);
		
	}
	
	private void setGokartVisibility(boolean visibility) {
		
		comboBoxRaceType.setVisible(visibility);
		lblRaceType.setVisible(visibility);
		
		textFieldTimeSlotGokart.setVisible(visibility);
		lblTimeSlotGokart.setVisible(visibility);
		btnChooseTimeSlotGokart.setVisible(visibility);
		
	}
	
	private void setCateringVisibility(boolean visibility) {
		
		lblCateringMenu.setVisible(visibility);
		comboBoxFoodType.setVisible(visibility);
		btnAddCateringMenu.setVisible(visibility);
		
	}

	private void handleAddCateringMenuEvent() {
		// TODO tilføj Id til EnumMenu
		
		try {
			bookingCtrl.addCateringMenu(EnumMenu.valueOfLabel((String)comboBoxFoodType.getSelectedItem()).getId());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //
		
	}

	private void handleAddAmountOfPeopleEvent() {
		try {
			if(Integer.parseInt(textFieldAmountOfPeople.getText())>0) {
				peopleAdded = bookingCtrl.addAmountOfPeople(Integer.parseInt(textFieldAmountOfPeople.getText()));
			}
			else {
				JOptionPane.showMessageDialog(null, "Input skal v�re positivt tal");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Input skal v�re et tal");
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
	}

	private void handleTimeSlotEventEvent() {
		
		if(peopleAdded) {
			TimeSlotWindowEvent dialog = new TimeSlotWindowEvent(bookingCtrl);	
			LocalDateTime eventStartTime = dialog.showDialog();
			
			if(eventStartTime != null) {
				
				
				String eventLabel = (String)comboBoxEventTime.getSelectedItem();
				LocalDateTime finishTime;
				
				try {
					finishTime = bookingCtrl.addTimeslot(eventLabel, eventStartTime, eventStartTime.plusMinutes(EnumType.valueOfLabel(eventLabel).getLength()));
					
					textFieldTimeSlotEvent.setText(eventStartTime.toString() + " /-/ " + finishTime.toString());
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Personer skal være tilføjet til ordren");
		}
		
		
		
	}

	private void handleTimeSlotEventGokart() {
		
		if(peopleAdded) {
			String eventLabel = (String)comboBoxRaceType.getSelectedItem();
			
			TimeSlotWindowGokart dialog = new TimeSlotWindowGokart(bookingCtrl);
			startTimeGokart = dialog.showDialog();
			
			if(startTimeGokart != null) {
				
				finishTimeGokart = startTimeGokart.plusMinutes(EnumType.valueOfLabel(eventLabel).getLength());
				
				
				
				try {
					finishTimeGokart = bookingCtrl.addTimeslot(eventLabel, startTimeGokart, finishTimeGokart);
					
					textFieldTimeSlotGokart.setText(startTimeGokart.toString() + " /-/ " + finishTimeGokart.toString());
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Personer skal være tilføjet til ordren");
		}
		
		
		
		
	}


	private void handleCancelBookingEvent() {
		
		this.dispose();
		
	}

	private void handleAcceptBookingEvent() {
		// TODO Implement finishBooking();
		
		BackgroundWorkerFinishBooking backgroundWorker = new BackgroundWorkerFinishBooking(bookingCtrl, this);
		
		backgroundWorker.execute();
		
		JOptionPane.showMessageDialog(null, "Uploader til database");
		
		//this.dispose(); flyttet til tråden
	}

	private void handleResetCustomerEvent() {
		// TODO Sæt alle tekst felter til at være tomme
		
		textFieldPhoneNo.setText("");
		textFieldAddress.setText("");
		textFieldBirthDate.setText("");
		textFieldCity.setText("");
		textFieldCountry.setText("");
		textFieldEmail.setText("");
		textFieldName.setText("");
		textFieldZipcode.setText("");
		
		textFieldCustomerPhone.setText("Tlf.");
		
		btnAddCustomer.setVisible(true);
		
	}

	private void handleAddCustomerEvent() {
		// TODO Implement addCustomer() eller er i searchForCustomer;
		
		
		
	}

	private void handleSearchForCustomerEvent() {
		// TODO Få den til at returnere kunden så info kan indsættes;
		
		PatternCheck patternCheck = new PatternCheck();
		Customer foundCustomer = null;
		
		if(patternCheck.checkPhoneNo(textFieldCustomerPhone.getText())) {
			try {
				foundCustomer = bookingCtrl.addCustomer(textFieldCustomerPhone.getText());
				setCustomerInfo(foundCustomer);
				btnAddCustomer.setVisible(false);
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, textFieldCustomerPhone.getText() + " er ikke et validt telefon nummer");
		}
		
		
		
		
	}

	private void setCustomerInfo(Customer foundCustomer) {
		
		if(foundCustomer == null) {
			JOptionPane.showMessageDialog(null, "Kunde med telefon nummer(" + textFieldCustomerPhone.getText() + ") kunne ikke findes");
		}
		else if(foundCustomer.getDateOfBirth() != null) {
			textFieldPhoneNo.setText(foundCustomer.getPhoneNo());
			textFieldAddress.setText(foundCustomer.getAddress());
			textFieldBirthDate.setText(foundCustomer.getDateOfBirth().toString());
			textFieldCity.setText(foundCustomer.getCity());
			textFieldCountry.setText(foundCustomer.getCountry());
			textFieldEmail.setText(foundCustomer.getEmail());
			textFieldName.setText(foundCustomer.getName());
			textFieldZipcode.setText(String.valueOf(foundCustomer.getZipCode()));
		}
		else{
			textFieldPhoneNo.setText(foundCustomer.getPhoneNo());
			textFieldName.setText(foundCustomer.getName());
		}
		
		
		
	}

	private void handleExitEvent() {
		
		this.dispose();
		
	}

}
