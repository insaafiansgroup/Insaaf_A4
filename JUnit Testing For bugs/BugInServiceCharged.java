package JUnit;

import static org.junit.Assert.*;


import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import src.hotel.credit.CreditCard;
import src.hotel.credit.CreditCardType;
import src.hotel.entities.Booking;
import src.hotel.entities.Guest;
import src.hotel.entities.Hotel;
import src.hotel.entities.Room;
import src.hotel.entities.RoomType;
import src.hotel.entities.ServiceCharge;
import src.hotel.entities.ServiceType;



/* @auhtor Harminder Kaur-11645213- Bug 2 Testing Code*/
public class BugInServiceCharged {
	Hotel hotel;
	Date date;
	int stayLength=4;
	Room room;
	Guest guest;
	CreditCard card;
	int occupants=2;

	@Before 
	

    public void setUp() throws Exception {

		// setting up all the required variables and objects in the hotel Application

		hotel = new Hotel();// new object for Hotel class
		hotel.addRoom(RoomType.DOUBLE, 112);// adding room to hotel

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");// setting date format

		date = format.parse("02-11-2018");// initializing arrival date

		room = new Room(112,RoomType.DOUBLE);// new object of Room class
        int phonenumber=0424424424;//initializing phone number
        String name ="Harry";// initializing name variable
        String address="Craigieburn";//initializing address variable
		guest = new Guest(name, address,phonenumber);// initializing object guest of Guest class

		card = new CreditCard(CreditCardType.VISA, 2, 123);// initializing card object of CreditCard class
		// generating confirmtionNumber by calling book method of hotel class passing all parameters
		long confirmationNumber =hotel.book(room, guest, date, stayLength, occupants, card); 
		hotel.checkin(confirmationNumber);// checkin by passing confirmationNumber
		

	}

	

	@After 

    public void tearDown() throws Exception {

		card = null;

		guest = null;

		room = null;

		hotel = null;

	}
	// Method to test the charges 120  for Restaurant service is 0 for checkedin customer in room number 112. 
	@Test
	public void testServiceChargedMethod() throws Exception { 
		   
		double charge=120;
		Booking booking= new Booking(guest,room,date,stayLength,occupants,card);//creating booking object with passing objects as parameters
		booking.addServiceCharge(ServiceType.RESTAURANT, charge);// calling addServiceCharge method of booking class by passing service type and cost to that service as parameters
		List<ServiceCharge> charges = booking.getCharges(); // creating list to add charges of type ServiceCharge class
        for(ServiceCharge servicecharge: charges)//for loop
        {
         assertEquals(charge, servicecharge.getCost(),0); // compare expected and actual value with eplison value by  calling getCost method of serviceCharge class in entities
        }
	}

}

	
	





   



