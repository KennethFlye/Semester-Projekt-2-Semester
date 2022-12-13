package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.BookingCtrl;
import database.DataAccessException;
import model.Booking;
import model.BookingTime;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class UpdateBookingMenu extends JFrame {
	private JTextField txtSearchDate;
	private BookingCtrl bookingCtrl;
	private JPanel contentPane;
	private JTextField txtCustomerName, txtEmployeeName, txtGokartStartTime, txtGokartEndTime, 
	txtStartTimeEventHall, txtEndTimeEventHall, txtAmountOfPeople, txtTotalPrice;
	
	private JRadioButton rdbtnIsPaid;
	
	private JLabel lblIsPaid, lblTotalPrice, lblMenuType, lblAmountOfPeople, lblEndTimeEventHall, 
	lblEventHallType, lblStartTimeEventHall, lblEndTimeGokart, lblStartTimeGokart, lblGokartType, lblEmployeeName, lblCustomerName;
	
	private JComboBox comboBoxMenuType, comboBoxEventHallType, comboBoxGokartType;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateBookingMenu frame = new UpdateBookingMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public UpdateBookingMenu() throws DataAccessException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSearchDate = new JLabel("S\u00F8g dato (yyyy-mm-dd)");
		topPanel.add(lblSearchDate);
		
		txtSearchDate = new JTextField();
		topPanel.add(txtSearchDate);
		txtSearchDate.setColumns(10);
		
		JButton btnSearchDate = new JButton("S\u00F8g");
		topPanel.add(btnSearchDate);
		
		btnSearchDate.addActionListener((e) -> handleSearchDateClick());
		
		JPanel bottomPanel = new JPanel();
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		JButton btnAccept = new JButton("Godkend");
		bottomPanel.add(btnAccept);
		
		btnAccept.addActionListener((e) -> handleAcceptClick());
		
		JButton btnCancel = new JButton("Annuller");
		bottomPanel.add(btnCancel);
		
		JPanel Middlepanel = new JPanel();
		getContentPane().add(Middlepanel, BorderLayout.CENTER);
		GridBagLayout gbl_Middlepanel = new GridBagLayout();
		gbl_Middlepanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_Middlepanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_Middlepanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_Middlepanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		Middlepanel.setLayout(gbl_Middlepanel);
		
		lblCustomerName = new JLabel("Kunde Navn");
		GridBagConstraints gbc_lblCustomerName = new GridBagConstraints();
		gbc_lblCustomerName.anchor = GridBagConstraints.EAST;
		gbc_lblCustomerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerName.gridx = 1;
		gbc_lblCustomerName.gridy = 0;
		Middlepanel.add(lblCustomerName, gbc_lblCustomerName);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setEditable(false);
		GridBagConstraints gbc_txtCustomerName = new GridBagConstraints();
		gbc_txtCustomerName.insets = new Insets(0, 0, 5, 0);
		gbc_txtCustomerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCustomerName.gridx = 2;
		gbc_txtCustomerName.gridy = 0;
		Middlepanel.add(txtCustomerName, gbc_txtCustomerName);
		txtCustomerName.setColumns(10);
		
		lblEmployeeName = new JLabel("Medarbejder");
		GridBagConstraints gbc_lblEmployeeName = new GridBagConstraints();
		gbc_lblEmployeeName.anchor = GridBagConstraints.EAST;
		gbc_lblEmployeeName.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmployeeName.gridx = 1;
		gbc_lblEmployeeName.gridy = 1;
		Middlepanel.add(lblEmployeeName, gbc_lblEmployeeName);
		
		txtEmployeeName = new JTextField();
		txtEmployeeName.setEditable(false);
		GridBagConstraints gbc_txtEmployeeName = new GridBagConstraints();
		gbc_txtEmployeeName.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmployeeName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmployeeName.gridx = 2;
		gbc_txtEmployeeName.gridy = 1;
		Middlepanel.add(txtEmployeeName, gbc_txtEmployeeName);
		txtEmployeeName.setColumns(10);
		
		lblGokartType = new JLabel("Gokart");
		GridBagConstraints gbc_lblGokartType = new GridBagConstraints();
		gbc_lblGokartType.anchor = GridBagConstraints.EAST;
		gbc_lblGokartType.insets = new Insets(0, 0, 5, 5);
		gbc_lblGokartType.gridx = 1;
		gbc_lblGokartType.gridy = 2;
		Middlepanel.add(lblGokartType, gbc_lblGokartType);
		
		comboBoxGokartType = new JComboBox();
		GridBagConstraints gbc_comboBoxGokartType = new GridBagConstraints();
		gbc_comboBoxGokartType.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxGokartType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxGokartType.gridx = 2;
		gbc_comboBoxGokartType.gridy = 2;
		Middlepanel.add(comboBoxGokartType, gbc_comboBoxGokartType);
		
		lblStartTimeGokart = new JLabel("Starttid");
		GridBagConstraints gbc_lblStartTimeGokart = new GridBagConstraints();
		gbc_lblStartTimeGokart.anchor = GridBagConstraints.EAST;
		gbc_lblStartTimeGokart.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartTimeGokart.gridx = 1;
		gbc_lblStartTimeGokart.gridy = 3;
		Middlepanel.add(lblStartTimeGokart, gbc_lblStartTimeGokart);
		
		txtGokartStartTime = new JTextField();
		GridBagConstraints gbc_txtGokartStartTime = new GridBagConstraints();
		gbc_txtGokartStartTime.insets = new Insets(0, 0, 5, 0);
		gbc_txtGokartStartTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGokartStartTime.gridx = 2;
		gbc_txtGokartStartTime.gridy = 3;
		Middlepanel.add(txtGokartStartTime, gbc_txtGokartStartTime);
		txtGokartStartTime.setColumns(10);
		
		lblEndTimeGokart = new JLabel("Sluttid");
		GridBagConstraints gbc_lblEndTimeGokart = new GridBagConstraints();
		gbc_lblEndTimeGokart.anchor = GridBagConstraints.EAST;
		gbc_lblEndTimeGokart.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndTimeGokart.gridx = 1;
		gbc_lblEndTimeGokart.gridy = 4;
		Middlepanel.add(lblEndTimeGokart, gbc_lblEndTimeGokart);
		
		txtGokartEndTime = new JTextField();
		GridBagConstraints gbc_txtGokartEndTime = new GridBagConstraints();
		gbc_txtGokartEndTime.insets = new Insets(0, 0, 5, 0);
		gbc_txtGokartEndTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGokartEndTime.gridx = 2;
		gbc_txtGokartEndTime.gridy = 4;
		Middlepanel.add(txtGokartEndTime, gbc_txtGokartEndTime);
		txtGokartEndTime.setColumns(10);
		
		lblEventHallType = new JLabel("Eventhal");
		GridBagConstraints gbc_lblEventHallType = new GridBagConstraints();
		gbc_lblEventHallType.anchor = GridBagConstraints.EAST;
		gbc_lblEventHallType.insets = new Insets(0, 0, 5, 5);
		gbc_lblEventHallType.gridx = 1;
		gbc_lblEventHallType.gridy = 5;
		Middlepanel.add(lblEventHallType, gbc_lblEventHallType);
		
		comboBoxEventHallType = new JComboBox();
		GridBagConstraints gbc_comboBoxEventHallType = new GridBagConstraints();
		gbc_comboBoxEventHallType.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxEventHallType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEventHallType.gridx = 2;
		gbc_comboBoxEventHallType.gridy = 5;
		Middlepanel.add(comboBoxEventHallType, gbc_comboBoxEventHallType);
		
		lblStartTimeEventHall = new JLabel("Starttid");
		GridBagConstraints gbc_lblEventHallStartTime = new GridBagConstraints();
		gbc_lblEventHallStartTime.anchor = GridBagConstraints.EAST;
		gbc_lblEventHallStartTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblEventHallStartTime.gridx = 1;
		gbc_lblEventHallStartTime.gridy = 6;
		Middlepanel.add(lblStartTimeEventHall, gbc_lblEventHallStartTime);
		
		txtStartTimeEventHall = new JTextField();
		GridBagConstraints gbc_txtEventHallStartTime = new GridBagConstraints();
		gbc_txtEventHallStartTime.insets = new Insets(0, 0, 5, 0);
		gbc_txtEventHallStartTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEventHallStartTime.gridx = 2;
		gbc_txtEventHallStartTime.gridy = 6;
		Middlepanel.add(txtStartTimeEventHall, gbc_txtEventHallStartTime);
		txtStartTimeEventHall.setColumns(10);
		
		lblEndTimeEventHall = new JLabel("Sluttid");
		GridBagConstraints gbc_lblEventHallEndTime = new GridBagConstraints();
		gbc_lblEventHallEndTime.anchor = GridBagConstraints.EAST;
		gbc_lblEventHallEndTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblEventHallEndTime.gridx = 1;
		gbc_lblEventHallEndTime.gridy = 7;
		Middlepanel.add(lblEndTimeEventHall, gbc_lblEventHallEndTime);
		
		txtEndTimeEventHall = new JTextField();
		GridBagConstraints gbc_txtEventHallEndTime = new GridBagConstraints();
		gbc_txtEventHallEndTime.insets = new Insets(0, 0, 5, 0);
		gbc_txtEventHallEndTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEventHallEndTime.gridx = 2;
		gbc_txtEventHallEndTime.gridy = 7;
		Middlepanel.add(txtEndTimeEventHall, gbc_txtEventHallEndTime);
		txtEndTimeEventHall.setColumns(10);
		
		lblAmountOfPeople = new JLabel("Antal personer");
		GridBagConstraints gbc_lblAmountOfPeople = new GridBagConstraints();
		gbc_lblAmountOfPeople.anchor = GridBagConstraints.EAST;
		gbc_lblAmountOfPeople.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmountOfPeople.gridx = 1;
		gbc_lblAmountOfPeople.gridy = 8;
		Middlepanel.add(lblAmountOfPeople, gbc_lblAmountOfPeople);
		
		txtAmountOfPeople = new JTextField();
		GridBagConstraints gbc_txtAmountOfPeople = new GridBagConstraints();
		gbc_txtAmountOfPeople.insets = new Insets(0, 0, 5, 0);
		gbc_txtAmountOfPeople.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAmountOfPeople.gridx = 2;
		gbc_txtAmountOfPeople.gridy = 8;
		Middlepanel.add(txtAmountOfPeople, gbc_txtAmountOfPeople);
		txtAmountOfPeople.setColumns(10);
		
		lblMenuType = new JLabel("Menu");
		GridBagConstraints gbc_lblMenuType = new GridBagConstraints();
		gbc_lblMenuType.anchor = GridBagConstraints.EAST;
		gbc_lblMenuType.insets = new Insets(0, 0, 5, 5);
		gbc_lblMenuType.gridx = 1;
		gbc_lblMenuType.gridy = 9;
		Middlepanel.add(lblMenuType, gbc_lblMenuType);
		
		comboBoxMenuType = new JComboBox();
		GridBagConstraints gbc_comboBoxMenuType = new GridBagConstraints();
		gbc_comboBoxMenuType.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxMenuType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMenuType.gridx = 2;
		gbc_comboBoxMenuType.gridy = 9;
		Middlepanel.add(comboBoxMenuType, gbc_comboBoxMenuType);
		
		lblTotalPrice = new JLabel("Total pris");
		GridBagConstraints gbc_lblTotalPrice = new GridBagConstraints();
		gbc_lblTotalPrice.anchor = GridBagConstraints.EAST;
		gbc_lblTotalPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalPrice.gridx = 1;
		gbc_lblTotalPrice.gridy = 10;
		Middlepanel.add(lblTotalPrice, gbc_lblTotalPrice);
		
		txtTotalPrice = new JTextField();
		txtTotalPrice.setEditable(false);
		GridBagConstraints gbc_txtTotalPrice = new GridBagConstraints();
		gbc_txtTotalPrice.insets = new Insets(0, 0, 5, 0);
		gbc_txtTotalPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTotalPrice.gridx = 2;
		gbc_txtTotalPrice.gridy = 10;
		Middlepanel.add(txtTotalPrice, gbc_txtTotalPrice);
		txtTotalPrice.setColumns(10);
		
		lblIsPaid = new JLabel("Er betalt");
		GridBagConstraints gbc_lblIsPaid = new GridBagConstraints();
		gbc_lblIsPaid.insets = new Insets(0, 0, 0, 5);
		gbc_lblIsPaid.gridx = 1;
		gbc_lblIsPaid.gridy = 11;
		Middlepanel.add(lblIsPaid, gbc_lblIsPaid);
		
		rdbtnIsPaid = new JRadioButton("");
		GridBagConstraints gbc_rdbtnIsPaid = new GridBagConstraints();
		gbc_rdbtnIsPaid.gridx = 2;
		gbc_rdbtnIsPaid.gridy = 11;
		Middlepanel.add(rdbtnIsPaid, gbc_rdbtnIsPaid);
		
		btnCancel.addActionListener((e) -> handleCancelClick());
		
		setVisibilityAllBoxes(false);
		
		try {
			bookingCtrl = new BookingCtrl();
		}
		catch(DataAccessException e) {
			throw new DataAccessException(e, UpdateBookingMenu.class.getName() + " could not instantiate bookingctrl");
		}
	}
	
	private void handleSearchDateClick() {
		//TODO make date string foolproof
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(txtSearchDate.getText(), formatter);
		TimeSlotDialogUpdate dialog = new TimeSlotDialogUpdate(bookingCtrl, date);
		int bookingID = dialog.showDialog();
		
		try {
			//pseudo
//			editorPane.setHit = bookingCtrl.findBookingByDate(date);
//			bookingCtrl.findBookingTimeById(dialog.showDialog())
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		getBooking = dialog.showdialog();
		
		
		//pseudo
		//click one to add to updatebookingmenu table
		//make writeable
		//accept btn saves to db
		
		
	}
	
	private void handleAcceptClick() {
		try {
			//TODO implement - get the swingworker
			bookingCtrl.updateBooking(null);
			bookingCtrl.updateBookingTime(null);
		} catch (Exception e) {
		}
		//maybe make it more like the receipt with individual rows to edit = easier to translate to query
	}
	
	private void handleCancelClick() {
		this.dispose();
	}
	
	private void setVisibilityAllBoxes(boolean visibility) {
		lblCustomerName.setVisible(visibility);
		txtCustomerName.setVisible(visibility);
		
		lblEmployeeName.setVisible(visibility);
		txtEmployeeName.setVisible(visibility);
		
		lblAmountOfPeople.setVisible(visibility);
		txtAmountOfPeople.setVisible(visibility);
		
		lblMenuType.setVisible(visibility);
		comboBoxMenuType.setVisible(visibility);
		
		lblTotalPrice.setVisible(visibility);
		txtTotalPrice.setVisible(visibility);
		
		lblIsPaid.setVisible(visibility);
		rdbtnIsPaid.setVisible(visibility);
		
		setVisibilityEventBoxes(visibility);
		setVisibilityGokartBoxes(visibility);
	}
	
	private void setVisibilityEventBoxes(boolean visibility) {
		lblEventHallType.setVisible(visibility);
		comboBoxEventHallType.setVisible(visibility);
		
		lblStartTimeEventHall.setVisible(visibility);
		txtStartTimeEventHall.setVisible(visibility);
		
		lblEndTimeEventHall.setVisible(visibility);
		txtEndTimeEventHall.setVisible(visibility);
	}
	
	private void setVisibilityGokartBoxes(boolean visibility) {
		lblGokartType.setVisible(visibility);
		comboBoxGokartType.setVisible(visibility);
		
		lblStartTimeGokart.setVisible(visibility);
		txtGokartStartTime.setVisible(visibility);
		
		lblEndTimeGokart.setVisible(visibility);
		txtGokartEndTime.setVisible(visibility);
	}
	
	private void addBookingToWindow(Booking booking) {
		setVisibilityAllBoxes(true);
		
		List<BookingTime> bookingTimes = booking.getTimeslots();
		for(int i = 0; i < bookingTimes.size(); i++) {
			BookingTime currentBookingTime = bookingTimes.get(i);
			int location = currentBookingTime.getEventType().getEnumType().getLocation();
			if(location == 1) {
				
			}
			else if()
		}
	}

}
