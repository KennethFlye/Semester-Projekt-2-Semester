package ui;

import javax.swing.SwingWorker;

import controller.BookingCtrl;
import database.DataAccessException;

public class BackgroundWorkerFinishBooking extends SwingWorker<Void, Void>{

	private BookingCtrl bookingCtrl;
	
	private CreateBookingMenu ui;
	
	public BackgroundWorkerFinishBooking(BookingCtrl bookingCtrl, CreateBookingMenu ui) {
		this.bookingCtrl = bookingCtrl;
		this.ui = ui;
		}
	
	@Override
	protected Void doInBackground() {
		
		try {
			bookingCtrl.finishBooking();
			ui.dispose();
		} catch (DataAccessException e) {
			// TODO gør noget med den
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
