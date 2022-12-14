package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DataAccessException;

import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.Font;

public class BookingMenu extends JFrame {

	private JPanel contentPane;
	private CreateBookingMenu createBookingMenu;
	private UpdateBookingMenu updateBookingMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingMenu frame = new BookingMenu();
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
	public BookingMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel1 = new JPanel();
		contentPane.add(northPanel1, BorderLayout.NORTH);
		northPanel1.setLayout(new BorderLayout(0, 0));
		
		JToggleButton tglbtnOverview = new JToggleButton("≡");
		northPanel1.add(tglbtnOverview, BorderLayout.WEST);
		
		JButton btnExit = new JButton("X");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		northPanel1.add(btnExit, BorderLayout.EAST);
		
		btnExit.addActionListener((e) -> handleExitEvent());
		
		JPanel centerPanel1 = new JPanel();
		contentPane.add(centerPanel1, BorderLayout.CENTER);
		
		JButton btnCreateBooking = new JButton("Opret Booking");
		centerPanel1.add(btnCreateBooking);

		btnCreateBooking.addActionListener((e) -> handleCreateBookingEvent());
		
		JButton btnUpdateBooking = new JButton("Opdater Booking");
		centerPanel1.add(btnUpdateBooking);
		
		btnUpdateBooking.addActionListener((e) -> {
			try {
				handleUpdateBookingEvent();
			} catch (DataAccessException e1) {
				e1.printStackTrace(); //TODO change?
			}
		});
		
		JButton btnShowCalendar = new JButton("Vis Kalender");
		centerPanel1.add(btnShowCalendar);
		
		btnShowCalendar.addActionListener((e) -> handleShowCalenderClick());
		
	}

	private void handleUpdateBookingEvent() throws DataAccessException {
		updateBookingMenu = new UpdateBookingMenu(); //throws exception because everything happens via the database
		updateBookingMenu.setVisible(true);
	}

	private void handleShowCalenderClick() {
		JOptionPane.showMessageDialog(null, "Endnu ikke implementeret");
	}

	private void handleExitEvent() {
		this.dispose();
	}

	private void handleCreateBookingEvent() {
		createBookingMenu = new CreateBookingMenu();
		createBookingMenu.setVisible(true);
	}

}
