package ui;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.BookingCtrl;
import database.DataAccessException;
import model.BookingTime;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

public class TimeSlotDialogUpdate extends JDialog {
	private JTextField txtDialogDate;
	private LocalDateTime date;
	private BookingCtrl bookingCtrl;
	private JTable tableGokarts;
	private JTable tableEvents;
	private DefaultTableModel dtmodel;
	private String[] columnNames = {"0"};
	
	public TimeSlotDialogUpdate(BookingCtrl bookingCtrl, LocalDateTime date) {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDate = new JLabel("Dato");
		panelTop.add(lblDate);
		
		txtDialogDate = new JTextField();
		panelTop.add(txtDialogDate);
		txtDialogDate.setColumns(10);
		
		JButton btnDialogSearch = new JButton("S\u00F8g");
		panelTop.add(btnDialogSearch);
		
		btnDialogSearch.addActionListener((e) -> handleSearchClick());
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnDiaAccept = new JButton("Godkend");
		panel.add(btnDiaAccept);
		
		btnDiaAccept.addActionListener((e) -> handleAcceptClick());
		
		JButton btnDiaCancel = new JButton("Annuller");
		panel.add(btnDiaCancel);
		
		btnDiaCancel.addActionListener((e) -> handleCancelClick());
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		tableGokarts = new JTable();
		splitPane.setLeftComponent(tableGokarts);
		
		tableEvents = new JTable();
		splitPane.setRightComponent(tableEvents);
		
		dtmodel = new DefaultTableModel(0,0);
		dtmodel.setColumnIdentifiers(columnNames);
		
		tableGokarts.setModel(dtmodel);
		tableEvents.setModel(dtmodel);
		
		this.bookingCtrl = bookingCtrl;
		this.date = date;
		txtDialogDate.setText(date.toString());
		
	}


	private void handleSearchClick() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate searchDate = LocalDate.parse(txtDialogDate.getText(), formatter);
		
		List<BookingTime> bookedTimes = null;
		
		int year = searchDate.getYear();
		int month = searchDate.getMonthValue();
		int day = searchDate.getDayOfMonth();
		
		try {
			bookedTimes = bookingCtrl.findBookedTimeslots(year, month, day);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		
		//Loop over alle tider der bliver returneret
		for (BookingTime element : bookedTimes) {
			if(element.getEventType().getEnumType().getLocation()==1) {
				dtmodel.addRow(new Object[] {element.getEventType().getEnumType().getLabel(), element.getStartTime().toString(), element.getFinishTime().toString()});
			}
			else if(element.getEventType().getEnumType().getLocation()==2) {
				dtmodel.addRow(new Object[] {element.getEventType().getEnumType().getLabel(), element.getStartTime().toString(), element.getFinishTime().toString()});
			}
		}
	}
	
	private void handleAcceptClick() {
		//TODO make it
	}
	
	private void handleCancelClick() {
		this.dispose();
	}

}
