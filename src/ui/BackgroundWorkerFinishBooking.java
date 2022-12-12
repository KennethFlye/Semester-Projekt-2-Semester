package ui;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import controller.BookingCtrl;
import database.DataAccessException;

public class BackgroundWorkerFinishBooking extends SwingWorker<Void, Void>{

	private BookingCtrl bookingCtrl;
	
	private ArrayList<String> receipt;
	
	private CreateBookingMenu ui;
	
	public BackgroundWorkerFinishBooking(BookingCtrl bookingCtrl, CreateBookingMenu ui) {
		this.bookingCtrl = bookingCtrl;
		this.ui = ui;
		this.receipt = new ArrayList<>();
		}
	
	@Override
	protected Void doInBackground() throws DataAccessException {
		
		receipt = bookingCtrl.finishBooking();
		Receipt receiptDialog = new Receipt(receipt);
		receiptDialog.setVisible(true);
		ui.dispose();
		
		return null;
	}
	
	

}
