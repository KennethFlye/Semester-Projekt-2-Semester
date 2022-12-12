package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.BookingCtrl;
import database.DataAccessException;

public class UpdateBookingMenu extends JFrame {
	private JTextField txtSearchDate;
	private BookingCtrl bookingCtrl;
	private JPanel contentPane;
	private JEditorPane editorPane;
	
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
		
		editorPane = new JEditorPane();
		Middlepanel.add(editorPane);
		
		btnCancel.addActionListener((e) -> handleCancelClick());
		
		
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

}
