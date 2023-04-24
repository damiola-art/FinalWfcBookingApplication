import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LessonBookingSystemTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getBookings() {
    }

    @Test
    public void getCustomers() {
    }

    @Test
    public void generateID() {
    }

    @Test
    public void RegisterNewBooking() {
    }

    @Test
    public void cancelBooking() {
    }

    @Test
    public void changeBooking() {
    }

    @Test
    public void attendLesson() {
    }

    @Test
    public void getBooking() {
    }

    @Test
    public void SignUp() {
        LessonBookingSystem system = new LessonBookingSystem();
        Customer customer = system.signUp("John", "Doe", "john.doe@example.com");
        assertNotNull(customer);
        assertEquals("John", customer.getFirstname());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals(0, customer.getBookings().size());
        assertEquals(0, customer.getBookedLessons().size());
    }
    @Test
    public void getCustomer() {
    }
}