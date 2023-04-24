import java.time.LocalDate;
import java.util.ArrayList;

public class Lesson {
    private LessonType type;
    private int weekNumber;
    private int month;
    private String day; // saturday or sunday
    private int period; // 1 or 2


    private int numberOfBookings;
    private int numberOfAttendance;
    private final int CAPACITY = 5;
    private boolean isFilled;
    private ArrayList<Review> reviews;

    public Lesson(LessonType type, int month, int weekNumber, String day, int period) {
        this.type = type;
        this.weekNumber = weekNumber;
        this.month = month;
        this.day = day;
        this.period = period;
        this.numberOfAttendance = 0;
        this.numberOfBookings = 0;
        this.reviews = new ArrayList<Review>();
        this.isFilled = false;
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getNumberOfBookings() {
        return numberOfBookings;
    }

    public void setNumberOfBookings(int numberOfBookings) {
        this.numberOfBookings = numberOfBookings;
    }

    public int getNumberOfAttendance() {
        return numberOfAttendance;
    }

    public void setNumberOfAttendance(int numberOfAttendance) {
        this.numberOfAttendance = numberOfAttendance;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }

    public double getAverageRating(){
        int sum = 0;
        for(Review review : this.getReviews()){
            sum += review.getRating();
        }
        if (reviews.size() == 0){
            return 0;
        } else{
            return (double)sum/reviews.size();
        }

    }

    public void updateIsFilled(){
        if(numberOfBookings == CAPACITY){
            setFilled(true);
        } else{
            setFilled(false);
        }
    }

    public void increaseNumberOfBooking(){
        this.numberOfBookings += 1;
        updateIsFilled();
    }

    public void decreaseNumberOfBooking(){
        this.numberOfBookings -= 1;
        updateIsFilled();
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void increaseNumberOfAttendance(){
        this.numberOfAttendance += 1;
    }

    public String toString(){
        return "Month: " + month + " | Week: " + weekNumber + " | Day: " + day + " | Period: " + period + " | Type: " + type.getFitnessActivity() + " | Price: " + type.getPrice();
    }
}
