package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.BookingCtrl;
import database.DataAccessException;
import model.Booking;
import model.BookingTime;

public class TimeSlotDialogUpdate extends JDialog {
	private final JComponent contentPanel = new JPanel();
	private JTextField txtDialogDate;
	private LocalDate date;
	private BookingCtrl bookingCtrl;
	private JTable tableBookings;
	private DefaultTableModel dtmodel;
	private String[] columnNames = {"ID", "Kunde", "Eventtype", "Starttid", "Sluttid", "Menu", "Total"};
	private int selectedID;
	
	public TimeSlotDialogUpdate(BookingCtrl bookingCtrl, LocalDate date) {
		super((java.awt.Frame) null, true);
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 600, 300);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDate = new JLabel("Dato (yyyy-mm-dd)");
		panelTop.add(lblDate);
		
		txtDialogDate = new JTextField();
		panelTop.add(txtDialogDate);
		txtDialogDate.setColumns(10);
		
		JButton btnDialogSearch = new JButton("S\u00F8g");
		panelTop.add(btnDialogSearch);
		
		btnDialogSearch.addActionListener((e) -> handleSearchClick());
		
		JPanel panelBot = new JPanel();
		getContentPane().add(panelBot, BorderLayout.SOUTH);
		
		JButton btnDiaAccept = new JButton("Godkend");
		panelBot.add(btnDiaAccept);
		
		btnDiaAccept.addActionListener((e) -> handleAcceptClick());
		
		JButton btnDiaCancel = new JButton("Annuller");
		panelBot.add(btnDiaCancel);
		
		btnDiaCancel.addActionListener((e) -> handleCancelClick());
		
		JPanel panelMid = new JPanel();
		getContentPane().add(panelMid, BorderLayout.CENTER);
		
		tableBookings = new JTable();
		panelMid.add(tableBookings);
		
		dtmodel = new DefaultTableModel(0,0);
		dtmodel.setColumnIdentifiers(columnNames);
		
		tableBookings.setModel(dtmodel);
		
		this.bookingCtrl = bookingCtrl;
		this.date = date;
		txtDialogDate.setText(date.toString());
		
		dtmodel.addRow(new Object[] {"ID", "Kunde", "Eventtype", "Starttid", "Sluttid", "Menu", "Total"});
		
		handleSearchClick();
	}


	private void handleSearchClick() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate searchDate = LocalDate.parse(txtDialogDate.getText(), formatter);
		
		List<Booking> bookings = null;
		
		try {
			bookings = bookingCtrl.findBookingsByDate(searchDate);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		
		//Loop over alle tider der bliver returneret
		for (Booking element : bookings) { 
			dtmodel.addRow(new Object[] {element.getBookingId(), element.getCustomer().getName(), 
					element.getTimeslots().get(0).getEventType(), element.getTimeslots().get(0).getStartTime(), element.getTimeslots().get(0).getFinishTime(),
					element.getCatering().getEnumMenu().getLabel(), element.getTotal()});
		}
	}
	
	private void handleAcceptClick() {
		int rowIndex = tableBookings.getSelectedRow();
		selectedID = (int) tableBookings.getValueAt(rowIndex, 0);
		this.dispose();
	}
	
	private void handleCancelClick() {
		this.dispose();
	}
	
	public int showDialog() {
		setVisible(true);
		return selectedID;
	}

}
