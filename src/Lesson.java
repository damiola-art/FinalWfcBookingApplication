import java.time.LocalDate;

public class Lesson {
    private String type;
    private int weekNumber;
    private String customerName;
    private double price;
    private int month; // new instance variable

    private int noOfCustomers = 0;
    private double totalPrice = 0;
    private Customer customer;
    private LocalDate startDate;

    private Review review;

    public Lesson(String type, int weekNumber, Customer customer, String customerName, double price, int month) {
        this.setType(type);
        this.setWeekNumber(weekNumber);
        this.setCustomer(customer);
        this.setCustomerName(customerName);
        this.setPrice(price);
        this.setMonth(month);

        this.startDate = startDate;
    }

    public int getNoOfCustomers() {
        return noOfCustomers;
    }

    public void setNoOfCustomers(int noOfCustomers) {
        this.noOfCustomers += noOfCustomers;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice += totalPrice;
    }

    public LocalDate getStartDate() { // new method to get start date
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) { // new method to set start date
        this.startDate = startDate;
    }
    // getters and setters for the new variables
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }




    // other existing getters and setters omitted for brevity...

    @Override
    public String toString() {
        return String.format("%s %s %s %s", getWeekNumber(), getType(), getPrice(), getCustomerName());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
