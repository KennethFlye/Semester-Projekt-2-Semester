package database;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingTimeDBIF {
	public List<LocalDateTime> getBookedTimeslots();
}
