package ui;

import javax.swing.SwingWorker;

import database.DataAccessException;

public class BackgroundWorker extends SwingWorker<Void, Void> {

	private CreateBookingMenu cbm;
	
	public BackgroundWorker(CreateBookingMenu cbm) {
		this.cbm = cbm;
	}

	@Override
	protected Void doInBackground() throws DataAccessException {
		
		return null;
	}
}
