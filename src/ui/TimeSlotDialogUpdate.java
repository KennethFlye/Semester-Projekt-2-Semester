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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.BookingCtrl;
import database.DataAccessException;
import model.Booking;
import model.PatternCheck;

public class TimeSlotDialogUpdate extends JDialog {
	private final JComponent contentPanel = new JPanel();
	private JTextField txtDialogDate;
	private LocalDate date;
	private BookingCtrl bookingCtrl;
	private JTable tableBookings;
	private DefaultTableModel dtmodel;
	private String[] columnNames = {"ID", "Kunde", "Eventtype", "Starttid", "Sluttid", "Total"};
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
		txtDialogDate.setColumns(20);
		
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
		tableBookings.getColumnModel().getColumn(3).setMinWidth(100);
		tableBookings.getColumnModel().getColumn(4).setMinWidth(100);
		
		this.bookingCtrl = bookingCtrl;
		this.date = date;
		txtDialogDate.setText(date.toString());
		
		dtmodel.addRow(new Object[] {"ID", "Kunde", "Eventtype", "Starttid", "Sluttid", "Total"});
		
		handleSearchClick();
	}


	private void handleSearchClick() {
		PatternCheck patternCheck = new PatternCheck();
		if(!patternCheck.checkDateString(txtDialogDate.getText())) {
			txtDialogDate.setText("dato format inkorrekt");
		}
		else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate searchDate = LocalDate.parse(txtDialogDate.getText(), formatter);
			
			List<Booking> bookings = null;
			
			try {
				bookings = bookingCtrl.findBookingsByDate(searchDate);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}

			//Loop over alle bookings der bliver returneret - TODO printer stadig dobbelt
			printBookingsToTable(bookings);
		}
			
		if(dtmodel.getRowCount()<=1) {
			//since we add a row with column names to the table, the tablecount should always be at least 1
			JOptionPane.showMessageDialog(null, "Der findes ingen bookings på denne dag.");
			txtDialogDate.setText("søg en ny dato");
		}
	}
	
	private void printBookingsToTable(List<Booking> bookings) {
		for (Booking element : bookings) { 
			String event = "";
			String startTime = "";
			String finishTime = "";
			if(element.getTimeslots().size()==2) {
				event = "Gokart + Hal";
				for(int i=0; i<element.getTimeslots().size(); i++) {
					//TODO få den til at printe en ting mellem de to tider?
					startTime += (element.getTimeslots().get(i).getStartTime().getHour() + ":" + element.getTimeslots().get(i).getStartTime().getMinute() + "  ");
					finishTime += (element.getTimeslots().get(i).getFinishTime().getHour() + ":" + element.getTimeslots().get(i).getFinishTime().getMinute() + "  ");
				}
			}
			else {
				event = element.getTimeslots().get(0).getEventType().getEnumType().getLabel();
				startTime = element.getTimeslots().get(0).getStartTime().toString();
				finishTime = element.getTimeslots().get(0).getFinishTime().toString();
			}
		dtmodel.addRow(new Object[] {element.getBookingId(), element.getCustomer().getName(), 
						event, startTime, finishTime, element.getTotal()});
		}
	}
	
	private void handleAcceptClick() {
		if(tableBookings.getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "Du skal først vælge en booking.");
		}
		else {
			int rowIndex = tableBookings.getSelectedRow();
			selectedID = (int) tableBookings.getValueAt(rowIndex, 0);
			this.dispose();
		}
	}
	
	private void handleCancelClick() {
		this.dispose();
	}
	
	public int showDialog() {
		setVisible(true);
		return selectedID;
	}

}
