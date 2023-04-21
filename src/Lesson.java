public class Lesson {
    private String type;
    private int weekNumber;
    private String customerName;
    private double price;
    private int capacity;

    private Customer customer;

    public Lesson(String type, int weekNumber, Customer customer,String customerName, double price, int capacity) {
        this.setType(type);
        this.setWeekNumber(weekNumber);
        this.setCustomerName(customerName);
        this.setCustomer(customer);
        this.setPrice(price);
        this.setCapacity(capacity);
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {

        return String.format("%s %s %s %s",weekNumber, type, price, customerName);
    }

    // Getters and Setters
    // ...
}
