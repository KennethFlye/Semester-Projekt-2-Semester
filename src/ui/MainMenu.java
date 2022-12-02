package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private BookingMenu bookingMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel northFirstPanel = new JPanel();
		contentPane.add(northFirstPanel, BorderLayout.NORTH);
		northFirstPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLogo = new JLabel("Thy-Gokart & Event");
		lblLogo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		northFirstPanel.add(lblLogo, BorderLayout.CENTER);
		
		JButton btnExit = new JButton("X");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		northFirstPanel.add(btnExit, BorderLayout.EAST);
		
		btnExit.addActionListener((e) -> handleExitEvent());
		
		JPanel centerFirstPanel = new JPanel();
		contentPane.add(centerFirstPanel, BorderLayout.CENTER);
		centerFirstPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel northSecondPanel = new JPanel();
		centerFirstPanel.add(northSecondPanel, BorderLayout.NORTH);
		
		JButton btnBooking = new JButton("Booking");
		northSecondPanel.add(btnBooking);
		btnBooking.addActionListener((e) -> handleBookingClick());
		
		
		JButton btnCustomer = new JButton("Kunde");
		northSecondPanel.add(btnCustomer);
		
		JButton btnEmployee = new JButton("Medarbejder");
		northSecondPanel.add(btnEmployee);
		
		JPanel centerSecondPanel = new JPanel();
		centerFirstPanel.add(centerSecondPanel, BorderLayout.CENTER);
		centerSecondPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel northThirdPanel = new JPanel();
		centerSecondPanel.add(northThirdPanel, BorderLayout.NORTH);
		northThirdPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnRent = new JButton("Udlejning");
		northThirdPanel.add(btnRent);
		
		JButton btnPrices = new JButton("Priser");
		northThirdPanel.add(btnPrices);
		
		JButton btnGokarts = new JButton("Gokarts");
		northThirdPanel.add(btnGokarts);
	}

	private void handleExitEvent() {
		
		this.dispose();
		
	}

	private void handleBookingClick() {
		
		bookingMenu = new BookingMenu();
		bookingMenu.setVisible(true);
		
	}
	

}
