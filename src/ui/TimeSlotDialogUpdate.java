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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.BookingCtrl;
import database.DataAccessException;
import model.BookingTime;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;

public class TimeSlotDialogUpdate extends JDialog {
	private final JComponent contentPanel = new JPanel();
	private JTextField txtDialogDate;
	private LocalDate date;
	private BookingCtrl bookingCtrl;
	private JTable tableGokarts;
	private JTable tableEvents;
	private DefaultTableModel dtmodel;
	private String[] columnNames = {"Event type", "Start Tid", "Slut Tid"};
	private LocalDateTime selectedStarttime;
	
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
		
		dtmodel = new DefaultTableModel(0,0); //TODO maybe make 2 of them
		dtmodel.setColumnIdentifiers(columnNames);
		
		tableGokarts.setModel(dtmodel);
		tableEvents.setModel(dtmodel);
		
		this.bookingCtrl = bookingCtrl;
		this.date = date;
		txtDialogDate.setText(date.toString());
		
		dtmodel.addRow(new Object[] {"Event Type", "Start Time", "Finish Time"});
		
	}


	private void handleSearchClick() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime searchDate = LocalDateTime.parse(txtDialogDate.getText(), formatter);
		
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
		int rowIndex = tableEvents.getSelectedRow(); //TODO make possible with two tables?
		selectedStarttime = (LocalDateTime) tableEvents.getValueAt(rowIndex, 1);
		this.dispose();
	}
	
	private void handleCancelClick() {
		this.dispose();
	}
	
	public LocalDateTime showDialog() {
		setVisible(true);
		return selectedStarttime;
	}

}
