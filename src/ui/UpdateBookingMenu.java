package ui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.BookingCtrl;
import database.DataAccessException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JList;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JEditorPane;

public class UpdateBookingMenu extends JFrame {
	private JTextField txtSearchDate;
	private BookingCtrl bookingCtrl;
	
	public UpdateBookingMenu() throws DataAccessException {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSearchDate = new JLabel("S\u00F8g dato");
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
		
		JEditorPane editorPane = new JEditorPane();
		getContentPane().add(editorPane, BorderLayout.CENTER);
		
		btnCancel.addActionListener((e) -> handleCancelClick());
		
		
		try {
			bookingCtrl = new BookingCtrl();
		}
		catch(DataAccessException e) {
			throw new DataAccessException(e, UpdateBookingMenu.class.getName() + " could not instantiate bookingctrl");
		}
	}
	
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
	
	private void handleSearchDateClick() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime date = LocalDateTime.parse(txtSearchDate.getText(), formatter);
		TimeSlotDialogUpdate dialog = new TimeSlotDialogUpdate(bookingCtrl, date);
		
		//pseudo
		//open dialog
		//add check timeslots to an array
		//for each in array, print to dialog tables
		//click one to add to updatebookingmenu table
		//make writeable
		//accept btn saves to db
		
		
	}
	
	private void handleAcceptClick() {
		//TODO implement - get the swingworker
	}
	
	private void handleCancelClick() {
		this.dispose();
	}

}
