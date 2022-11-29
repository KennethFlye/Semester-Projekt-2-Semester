package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BookingCtrl;
import database.BookingTimeDB;
import model.Booking;
import model.BookingTime;

/**GUI
 * Methods <ul>
 * <li>createBooking()			TODO
 * <li>findBookedTimeslots()	TODO
 * <li>addTime()				TODO
 * <li>addCustomer				TODO
 * <li>addAmountOfPeople()		TODO
 * <li>addCateringMenu()		TODO
 * <li>finishBooking()			TODO
 * */

public class GraphicalUserInterface extends JFrame {

	private JPanel contentPane;
	BookingCtrl bookingCtrl;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicalUserInterface frame = new GraphicalUserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
	 * 				GRAPHICS
	 * 								ebic
	 * */
	
	/**
	 * Create the frame.
	 * 
	 */
	public GraphicalUserInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	
	
	/* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
	 * 				METHODS
	 * 								0/7 completed
	 * */
	
	/**Creates a new booking object
	 * <p> Calls createNewBooking() in BookingCtrl
	 * @see {@link BookingCtrl} {@link Booking} {@link BookingDBIF} {@link BookingDB}
	 * */
	private void createBooking() {
		bookingCtrl.createBooking();
	}
	
	
	/**Finds the time slots the are booked
	 * <p> Calls findBookedTimeslots in BookingCtrl
	 * @see {@link BookingTime} {@link BookingTimeDB}
	 * */
	private void findBookedTimeslots() {
	bookingCtrl.findBookedTimeslots();	addTime();
	}
	
	
	/**<img src= "doc-files/documentation.gif", height=70%> //Illustration from DCD
	 * <br> Finds the time slots the are booked
	 * <p> Calls findBookedTimeslots in BookingCtrl
	 * @see {@link BookingTime} {@link BookingTimeDB}
	 * */
	private void addTime() {
		//BookingCtrl.addTime(null, null, null);
	}
	
	/**
	 *<img src= "doc-files/documentation.gif", height=70%> */
	private void addCustomer() {
		//BookingCtrl.addCustomer("ToDo");
	}
	
	private void addAmountOfPeople() {
		bookingCtrl.addAmountOfPeople(0);
	}
	private void addCateringMenu(){
		bookingCtrl.addCatering(null);
	}
	
	private void finishBooking() {
		bookingCtrl.finishBooking();
	}
	
	
}
