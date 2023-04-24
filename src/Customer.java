import java.util.ArrayList;
import java.util.HashMap;

public class Customer {
    private String firstname;

    private String lastName;

    private String email;
    private ArrayList<Lesson> bookedLessons;
    private HashMap<String, Booking> bookings;

    public ArrayList<Lesson> getBookedLessons() {
        return bookedLessons;
    }

    public void setBookedLessons(ArrayList<Lesson> bookedLessons) {
        this.bookedLessons = bookedLessons;
    }

    public HashMap<String, Booking> getBookings() {
        return bookings;
    }

    public void setBookings(HashMap<String, Booking> bookings) {
        this.bookings = bookings;
    }

    public Customer(String firstname, String lastName, String email) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.bookedLessons = new ArrayList<Lesson>();
        this.bookings = new HashMap<String, Booking>();
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addLesson(Lesson lesson){
        this.bookedLessons.add(lesson);
    }

    public void removeLesson(Lesson lesson){
        this.bookedLessons.remove(lesson);
    }

    public void addBooking(Booking booking){
        this.bookings.put(booking.getBookingID(), booking);
    }
}












