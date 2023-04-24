
import java.util.*;

public class LessonBookingSystem {
    private HashMap<String, Booking> bookings;

    private HashMap<String, Customer> customers;

    public HashMap<String, Booking> getBookings() {
        return bookings;
    }

    public HashMap<String, Customer> getCustomers() {
        return customers;
    }

    public LessonBookingSystem() {
        this.bookings = new HashMap<String, Booking>();
        this.customers = new HashMap<String, Customer>();
    }

    public String generateID() {
        String uniqueID = "";
        do {
            UUID uuid = UUID.randomUUID();
            uniqueID = uuid.toString().replaceAll("-", "").substring(0, 6);
        } while (uniqueID.length() != 6);
        return uniqueID;
    }

    public String registerNewBooking(Customer customer, Lesson lesson){
        String bookingID = generateID();
        Booking newBooking = new Booking(lesson, customer, bookingID);
        this.bookings.put(bookingID, newBooking);
        customer.addBooking(newBooking);
        customer.addLesson(lesson);
        lesson.updateIsFilled();
        return bookingID;
    }

    public void cancelBooking(Booking booking){
        Lesson lesson = booking.getLesson();
        Customer customer = booking.getCustomer();
        lesson.decreaseNumberOfBooking();
        customer.removeLesson(lesson);
        booking.setStatus("cancelled");

    }

    public void changeBooking(Booking booking, Lesson newLesson){
        Lesson oldlesson = booking.getLesson();
        Customer customer = booking.getCustomer();
       customer.removeLesson(oldlesson);
       customer.addLesson(newLesson);
       oldlesson.decreaseNumberOfBooking();
       newLesson.increaseNumberOfBooking();

        booking.setStatus("changed");
    }

    public void attendLesson(Booking booking, Review review){
        Lesson lesson = booking.getLesson();
        Customer customer = booking.getCustomer();

        lesson.addReview(review);
        lesson.increaseNumberOfAttendance();
        booking.setReview(review);
        booking.setStatus("attended");
    }

    public Booking getBooking(String ID){
        return bookings.get(ID);
    }

    public Customer signUp(String firstName, String lastName, String email){
        Customer newCustomer = new Customer(firstName, lastName,email);
        customers.put(email, newCustomer);
        return newCustomer;

    }

    public Customer getCustomer(String email){
        return this.customers.get(email);
    }


}
