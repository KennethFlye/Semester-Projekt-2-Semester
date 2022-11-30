package database;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface GokartDBIF {
	boolean checkGokarts(int amount, LocalDateTime time, LocalDateTime end) throws SQLException;
}
