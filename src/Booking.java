public class Booking {
    private Lesson lesson;
    private Customer customer;
    private Review review;
    private String status;
    private String bookingID;

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public Booking(Lesson lesson, Customer customer, String bookingID) {
        this.lesson = lesson;
        this.customer = customer;
        this.bookingID = bookingID;
        this.status = "unattended";
    }

    public String toString(){
        return "Booking ID: " + bookingID + " Lesson: " + lesson.toString();
    }
}
