/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import hotel.booking.BookingCTL;
import hotel.checkin.CheckinCTL;
import hotel.checkout.CheckoutCTL;
import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;
import hotel.entities.ServiceCharge;
import hotel.entities.ServiceType;
import hotel.service.RecordServiceCTL;
import hotel.service.RecordServiceUI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Malik
 */
public class TestBug2 {
    
        
     @Test
      public void testBug2() { 
         
         // Date variables
         Hotel hotel = new Hotel();
         String name = "Waqas";
         String address = "2A Tucker St";
         int phoneNum = 45544;
         RoomType roomType = RoomType.DOUBLE;
         int roomID = 201;
         double cost = 54.0;
         int stayLength = 2;
         int occuptantNum = 2;
         Date arrivalDate = new Date();
         CreditCardType creditType = CreditCardType.VISA;
         
         Guest guest = new Guest(name, address, phoneNum);
         Room room = new Room(roomID, RoomType.SINGLE);
         CreditCard card = new CreditCard(CreditCardType.VISA, 4, 345);
    
         // Make booking
         BookingCTL booking = new BookingCTL(hotel);
         
         booking.phoneNumberEntered(phoneNum);
         booking.guestDetailsEntered(name, address);
         booking.roomTypeAndOccupantsEntered(roomType, occuptantNum);
         booking.bookingTimesEntered(arrivalDate, stayLength);
         booking.creditDetailsEntered(creditType, 4, 345);
         
         // confirmation num
         long confimationNum = hotel.book(room, guest, arrivalDate, stayLength, occuptantNum, card);
         
         // checkingIn
         CheckinCTL checkIn = new CheckinCTL(hotel);
         checkIn.confirmationNumberEntered(confimationNum);
         checkIn.checkInConfirmed(true);
         
         
         // checkedOut
         CheckoutCTL checkOut = new CheckoutCTL(hotel);
         checkOut.roomIdEntered(roomID);
         checkOut.chargesAccepted(true);
         checkOut.creditDetailsEntered(creditType, 4, 345);
                 
         // RecordService
         RecordServiceCTL recordService = new RecordServiceCTL(hotel);
         recordService.roomNumberEntered(roomID);
         recordService.serviceDetailsEntered(ServiceType.BAR_FRIDGE, cost);
         Booking expected = null; // Booking must be null 
         Booking book = hotel.findActiveBookingByRoomId(roomID);
         assertEquals(expected, book);
                           
        
          }
      }
      
   
     

