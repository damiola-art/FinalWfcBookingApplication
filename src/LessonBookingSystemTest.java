import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class LessonBookingSystemTest {
    LessonBookingSystem system;
    Lesson lesson1;
    Customer customer1;
    LessonType fitnessType1;


    @BeforeEach
    public void setUp() {
        system = new LessonBookingSystem();
        customer1 = new Customer("John", "Doe", "john.doe1@example.com");
        fitnessType1 = new LessonType(10, "Bodysculpt");
        lesson1 = new Lesson(fitnessType1, 1,1,"saturday", 1);
    }

    @AfterEach
    public void tearDown() {
        system = null;
        fitnessType1 = null;
        lesson1 = null;
    }

    @Test
    public void testSignUp() {
        Customer customer = system.signUp("John", "Doe", "john.doe@example.com");
        Customer result = system.getCustomer("john.doe@example.com");
        assertEquals(result, customer);
    }
    @Test
    public void testRegisterNewBooking() {
        String bookingID = system.registerNewBooking(customer1, lesson1);
        Booking booking = system.getBooking(bookingID);
        assertEquals(customer1, booking.getCustomer());
        assertEquals(lesson1, booking.getLesson());
    }

    @Test
    public void testCancelBooking() {
        String bookingID = system.registerNewBooking(customer1, lesson1);
        Booking booking = system.getBooking(bookingID);
        system.cancelBooking(booking);
        assertEquals("cancelled", booking.getStatus());
        assertFalse(customer1.getBookedLessons().contains(lesson1));
    }

    @Test
    public void testChangeBooking() {
        Lesson lesson2 = new Lesson(fitnessType1,2,2, "saturday", 1);
        String bookingID = system.registerNewBooking(customer1, lesson1);
        Booking booking = system.getBooking(bookingID);
        system.changeBooking(booking, lesson2);
        assertEquals("changed", booking.getStatus());
        assertFalse(customer1.getBookedLessons().contains(lesson1));
        assertTrue(customer1.getBookedLessons().contains(lesson2));
        assertEquals(0, lesson1.getNumberOfBookings());
        assertEquals(1, lesson2.getNumberOfBookings());
    }

    @Test
    public void testAttendLesson() {
        Review review = new Review("Great lesson!", 5);
        String bookingID = system.registerNewBooking(customer1, lesson1);
        Booking booking = system.getBooking(bookingID);
        system.attendLesson(booking, review);
        assertEquals("attended", booking.getStatus());
        assertEquals(1, lesson1.getNumberOfAttendance());
        assertTrue(lesson1.getReviews().contains(review));
        assertEquals(review, booking.getReview());
    }
}