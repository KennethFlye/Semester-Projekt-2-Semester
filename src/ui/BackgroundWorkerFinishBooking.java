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
		}
	
	@Override
	protected Void doInBackground() throws DataAccessException {
		
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
		try {
			receipt = bookingCtrl.finishBooking();
			Receipt receiptDialog = new Receipt(receipt);
			receiptDialog.setVisible(true);
			ui.dispose();
		} catch (SQLException e) {
		this.ui = ui;
		this.receipt = new ArrayList<>();
			throw new DataAccessException(e, "Could not run in bg");
		}
=======
>>>>>>> Stashed changes
		receipt = bookingCtrl.finishBooking();
		Receipt receiptDialog = new Receipt(receipt);
		receiptDialog.setVisible(true);
		ui.dispose();
<<<<<<< Updated upstream
=======
>>>>>>> 2897065492618a052d0d1bb8bce016e42734491b
>>>>>>> Stashed changes
		
		return null;
	}
	
	

}
