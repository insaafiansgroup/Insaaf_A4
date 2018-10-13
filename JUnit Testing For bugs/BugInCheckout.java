package JUnit;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



/* @author Harminder Kaur -11645213 
Bug 2 Testing*/
public class BugInCheckout {
	Hotel hotel;
	Date date;
	int stayLength=4;
	Room room;
	Guest guest;
	CreditCard card;
	int occupants=3;

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

    
		// Method to test that the given room number customer has been checked out
        @Test
        public void testCheckout() {
            int roomNumber = 112;
    	hotel.checkout(roomNumber);// calling checkout method
    	}

//Method to check whether the booking for checkout room is till available in record.
       
        @Test
        public void testFindActiveBookingByRoomId() {
            
            int roomNumber = 112;// initializing room number
            hotel.checkout(roomNumber);// calling checkout method of hotel to confirmed checkout for given room number
            Booking expectedOutput = null;// booking has to be null after checkout
            Booking actualOutput = hotel.findActiveBookingByRoomId(roomNumber);// to check the booking still available in record 
            assertEquals(expectedOutput, actualOutput);// comparing the expected and actual output of booking for given room number
        }

	}
	

	
	





   



