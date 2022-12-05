package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Customer;

public class CustomerDB implements CustomerDBIF{

	private static final String findAllQ = "SELECT * FROM Contact, Customer";
	private static final String findByPhoneNoQ = findAllQ + " WHERE Contact.phone = ? AND Contact.contactId = Customer.customerId";
	private static final String findAllCityQ = "SELECT * FROM ZipcodeCity";
	private static final String findCityByZipCodeQ = findAllCityQ + " WHERE ZipcodeCity.zipcode = ?";
	
	
	private PreparedStatement findAll, findByPhoneNo, findAllCity, findCityByZipCode;
	
	public CustomerDB() throws DataAccessException {
		
		try {
			findAll = DBConnection.getInstance().getConnection().prepareStatement(findAllQ);
			findByPhoneNo = DBConnection.getInstance().getConnection().prepareStatement(findByPhoneNoQ);
			findAllCity = DBConnection.getInstance().getConnection().prepareStatement(findAllCityQ);
			findCityByZipCode = DBConnection.getInstance().getConnection().prepareStatement(findCityByZipCodeQ);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Cannot prepare customer statements");
		}
		
		
	}
	
	@Override
	public Customer findCustomer(String phoneNo) throws DataAccessException {
		
		String firstName, lastName, fullName, phone, email, address, city, country;
		int zipCode;
		LocalDate dateOfBirth;
		ResultSet rs, rsCity;
		
		Customer foundCustomer = null;
		
		try {
			findByPhoneNo.setString(1, phoneNo);
			rs = findByPhoneNo.executeQuery();
			
			while(rs.next()) {
				if(rs.getDate("dateOfBirth") != null) {
					firstName = rs.getString("firstName");
					lastName = rs.getString("lastName");
					fullName = firstName + " " + lastName;
					phone = rs.getString("phone");
					email = rs.getString("email");
					address = rs.getString("address");
					zipCode = rs.getInt("zipCode");
					
					findCityByZipCode.setInt(1, zipCode);
					rsCity = findCityByZipCode.executeQuery();
					
					country = rs.getString("country");
					dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();
					
				
					
					while(rsCity.next()) {
						city = rsCity.getString("zipcodeCity");
						
						foundCustomer = new Customer(fullName, phone, email, address, zipCode, city, country, dateOfBirth);
					}
				}
				else {
					firstName = rs.getString("firstName");
					lastName = rs.getString("lastName");
					fullName = firstName + " " + lastName;
					
					phone = rs.getString("phone");
					
					foundCustomer = new Customer(fullName, phone);
				}
				
				
				
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could Not Find Customer In Database");
		}
		
		return foundCustomer;
	}
	
	

}
