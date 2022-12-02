package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controller.BookingCtrl;
import database.DataAccessException;
import model.BookingTime;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimeSlotWindowGokart extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldStartTime;
	private JTextField textFieldEndTime;
	private JTable tableTimeslot;
	private DefaultTableModel dtm;
	private String[] columnNames = {"Event type", "Start Tid", "Slut Tid"};
	private JTextField textFieldChoosenDay;
	private JButton btnSearchDay, okButton, cancelButton;
	private LocalDateTime timeStart;
	private BookingCtrl bookingCtrl;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			TimeSlotWindowGokart dialog = new TimeSlotWindowGokart();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public TimeSlotWindowGokart(BookingCtrl bookingCtrl) {
		super((java.awt.Frame) null, true);
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		this.bookingCtrl = bookingCtrl;
		setBounds(100, 100, 550, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelNorth = new JPanel();
			contentPanel.add(panelNorth, BorderLayout.NORTH);
			panelNorth.setLayout(new BorderLayout(0, 0));
			{
				JPanel panelNorthWest = new JPanel();
				panelNorth.add(panelNorthWest, BorderLayout.WEST);
				{
					JLabel lblStartTime = new JLabel("Start Tid");
					panelNorthWest.add(lblStartTime);
				}
				{
					textFieldStartTime = new JTextField();
					panelNorthWest.add(textFieldStartTime);
					textFieldStartTime.setColumns(10);
				}
			}
			{
				JPanel panelNorthEast = new JPanel();
				panelNorth.add(panelNorthEast, BorderLayout.EAST);
				{
					JLabel lblEndTime = new JLabel("Slut Tid");
					panelNorthEast.add(lblEndTime);
				}
				{
					textFieldEndTime = new JTextField();
					panelNorthEast.add(textFieldEndTime);
					textFieldEndTime.setColumns(10);
				}
			}
			{
				JPanel panel = new JPanel();
				panelNorth.add(panel, BorderLayout.CENTER);
				{
					JLabel lblTimePattern = new JLabel("YYYY-MM-DD HH:mm");
					panel.add(lblTimePattern);
				}
			}
		}
		{
			JPanel panelCenter = new JPanel();
			contentPanel.add(panelCenter, BorderLayout.CENTER);
			{
				tableTimeslot = new JTable();
				
				dtm = new DefaultTableModel(0,0);
				dtm.setColumnIdentifiers(columnNames);
				panelCenter.setLayout(new BorderLayout(0, 0));
				
				tableTimeslot.setModel(dtm);
				// Her
				panelCenter.add(tableTimeslot, BorderLayout.EAST);
			}
			{
				JPanel panelCenterWest = new JPanel();
				panelCenter.add(panelCenterWest, BorderLayout.WEST);
				GridBagLayout gbl_panelCenterWest = new GridBagLayout();
				gbl_panelCenterWest.columnWidths = new int[]{49, 96, 0};
				gbl_panelCenterWest.rowHeights = new int[]{20, 0, 0, 0};
				gbl_panelCenterWest.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				gbl_panelCenterWest.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
				panelCenterWest.setLayout(gbl_panelCenterWest);
				{
					JLabel lblDayPattern = new JLabel("YYYY-MM-DD");
					GridBagConstraints gbc_lblDayPattern = new GridBagConstraints();
					gbc_lblDayPattern.gridwidth = 2;
					gbc_lblDayPattern.insets = new Insets(0, 0, 5, 5);
					gbc_lblDayPattern.gridx = 0;
					gbc_lblDayPattern.gridy = 0;
					panelCenterWest.add(lblDayPattern, gbc_lblDayPattern);
				}
				{
					JLabel lblChooseDay = new JLabel("Vælg Dag");
					GridBagConstraints gbc_lblChooseDay = new GridBagConstraints();
					gbc_lblChooseDay.anchor = GridBagConstraints.WEST;
					gbc_lblChooseDay.insets = new Insets(0, 0, 5, 5);
					gbc_lblChooseDay.gridx = 0;
					gbc_lblChooseDay.gridy = 1;
					panelCenterWest.add(lblChooseDay, gbc_lblChooseDay);
				}
				{
					textFieldChoosenDay = new JTextField();
					GridBagConstraints gbc_textFieldChoosenDay = new GridBagConstraints();
					gbc_textFieldChoosenDay.insets = new Insets(0, 0, 5, 0);
					gbc_textFieldChoosenDay.anchor = GridBagConstraints.NORTHWEST;
					gbc_textFieldChoosenDay.gridx = 1;
					gbc_textFieldChoosenDay.gridy = 1;
					panelCenterWest.add(textFieldChoosenDay, gbc_textFieldChoosenDay);
					textFieldChoosenDay.setColumns(10);
				}
				{
					btnSearchDay = new JButton("Søg Dag");
					GridBagConstraints gbc_btnSearchDay = new GridBagConstraints();
					gbc_btnSearchDay.gridwidth = 2;
					gbc_btnSearchDay.gridx = 0;
					gbc_btnSearchDay.gridy = 2;
					panelCenterWest.add(btnSearchDay, gbc_btnSearchDay);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//Buttons
		btnSearchDay.addActionListener((e) -> handleSearchDAyEvent());
		okButton.addActionListener((e) -> handleOkClickedEvent());
		cancelButton.addActionListener((e) -> handleCancelClckedEvent());
		
		
		dtm.addRow(new Object[] {"Event Type", "Start Time", "Finish Time"});
	}

	private void handleCancelClckedEvent() {
		this.dispose();
	}

	private void handleOkClickedEvent() {
		// TODO Implement Send Data Til Tidligere Vindue
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		timeStart = LocalDateTime.parse(textFieldStartTime.getText(), formatter);

		setVisible(false);
		this.dispose();
	}

	private void handleSearchDAyEvent() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate searchDate = LocalDate.parse(textFieldChoosenDay.getText(), formatter);
		
		System.out.println(searchDate.toString());
		
		List<BookingTime> bookedTimes = null;
		
		int year = searchDate.getYear();
		int month = searchDate.getMonthValue();
		int day = searchDate.getDayOfMonth();
		
		try {
			bookedTimes = bookingCtrl.findBookedTimeslots(year, month, day);
		} catch (DataAccessException e) {
			// TODO Vis hvad der gik galt
			e.printStackTrace();
		}
		
		//Loop over alle tider der bliver returneret
		for (BookingTime element : bookedTimes) {
			dtm.addRow(new Object[] {element.getEventType().getEnumType().getLabel(), element.getStartTime().toString(), element.getFinishTime().toString()});
		}
		
	}
	
	public LocalDateTime showDialog() {
		setVisible(true);
		return timeStart;
	}

}
