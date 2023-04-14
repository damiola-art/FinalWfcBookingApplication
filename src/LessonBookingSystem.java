import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class LessonBookingSystem {
    private TimetableGenerator timetableGenerator;
    private List<Lesson> bookedLessons;

    private Customer customer;
    private ArrayList<Review> reviews;
    private Lesson lesson;

    private List<Customer> customers; // List to store customer objects





    public LessonBookingSystem() {
        this.timetableGenerator = new TimetableGenerator();
        this.bookedLessons = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.reviews = new ArrayList<>();

    }


    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastname = scanner.nextLine();
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        customer = new Customer(firstname, lastname, email);
        System.out.println("Sign up successful! Welcome, " + firstname + " " + lastname + ".");
    }

    public void signIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        // Validate email against customer's email
        if (customer != null && customer.getEmail().equals(email)) {
            System.out.println("Sign in successful! Welcome back, " + customer.getFirstname() + " " + customer.getLastName() + ".");
        } else {
            System.out.println("Invalid email address. Please sign up or try again.");
            signUp();
        }
    }
    public void signOut() {
        customer = null;
        System.out.println("Sign out successful. Thank you for using the Lesson Booking System!");
    }




    public void bookLessonByType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the lesson type (Spin, Yoga, Bodysculpt, Zumba): ");
        String lessonType = scanner.nextLine();
        timetableGenerator.viewTimetableByLessonType(lessonType); // Pass the user input as the lesson type

        if (customer == null) {
            System.out.println("Please sign in first before booking a lesson by type.");
            signIn();
        }

        if (customer != null) {
            System.out.print("Enter your first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter the week number to book a lesson (1-8): ");
            int weekNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Check if week number is valid
            if (weekNumber < 1 || weekNumber > 8) {
                System.out.println("Invalid lesson number. Please try again.");
            } else {
                // Check if lesson has available capacity
                int bookedCount = 0;
                for (Lesson lesson : bookedLessons) {
                    if (lesson.getType().equalsIgnoreCase(lessonType) && lesson.getWeekNumber() == weekNumber) {
                        bookedCount++;
                    }
                }
                if (bookedCount >= 5) {
                    System.out.println("Lesson " + lessonType + " for week " + weekNumber + " is fully booked. Please choose another week or lesson.");
                } else {
                    // Get price for the lesson type
                    double price = 0.0; // Default price
                    if (lessonType.equalsIgnoreCase("Spin")) {
                        price = 10.0;
                    } else if (lessonType.equalsIgnoreCase("Yoga")) {
                        price = 15.0;
                    } else if (lessonType.equalsIgnoreCase("Bodysculpt")) {
                        price = 12.0;
                    } else if (lessonType.equalsIgnoreCase("Zumba")) {
                        price = 8.0;
                    }

                    Lesson lesson = new Lesson(lessonType, weekNumber, firstName + " " + lastName, price, bookedCount + 1);
                    bookedLessons.add(lesson);
                    System.out.println("Lesson booked for week " + weekNumber + " by customer " + firstName + " " + lastName);

                    // Prompt customer to add a review
                    System.out.print("Would you like to add a review for this lesson? (Y/N): ");
                    String addReview = scanner.nextLine();
                    if (addReview.equalsIgnoreCase("Y")) {
                        addReview();
                    }
                }
            }
        } else {
            System.out.println("No customer is set. Please set a customer before booking a lesson.");
        }
    }



    public void bookLessonByDay() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the day (Pick between Saturday and Sunday): ");
        String dayOfWeek = scanner.nextLine().toLowerCase(); // Convert to lowercase for case-insensitive comparison

        if (customer == null) {
            System.out.println("Please sign in first before booking a lesson by type.");
            signIn();
        }
        if (dayOfWeek.equals("saturday")) { // Use equals() method for String comparison
            timetableGenerator.viewTimetableByDay("sat");
        } else if (dayOfWeek.equals("sunday")) {
            timetableGenerator.viewTimetableByDay("sun");
        } else {
            System.out.println("Invalid day. Please try again.");
            return; // Exit the method if the day is invalid
        }

        if (customer != null) {
            System.out.print("Enter the week number to book a lesson (1-8): ");
            int weekNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            if (weekNumber < 1 || weekNumber > 8) {
                System.out.println("Invalid lesson number. Please try again.");
            } else {
                System.out.println("Lesson booked for week " + weekNumber + " by customer " + customer.getFirstname() + " " + customer.getLastName());

                // Prompt customer to add a review
                System.out.print("Would you like to add a review for this lesson? (Y/N): ");
                String addReview = scanner.nextLine();
                if (addReview.equalsIgnoreCase("Y")) {
                    addReview();
                }


            }
        } else {
            System.out.println("No customer is set. Please set a customer before booking a lesson.");
        }
    }





    public void cancelBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the week number for the booking you want to cancel (1-8): ");
        int weekNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (customer == null) {
            System.out.println("Please sign in first before canceling a booking.");
            signIn();
        } else {
            if (bookedLessons.contains(customer.getEmail() + "-" + weekNumber)) {
                System.out.println("Are you sure you want to cancel your booking for week " + weekNumber + "? (yes/no): ");
                String choice = scanner.nextLine().toLowerCase();
                if (choice.equals("yes")) {
                    bookedLessons.remove(customer.getEmail() + "-" + weekNumber);
                    System.out.println("Booking canceled for week " + weekNumber + " by customer " + customer.getFirstname() + " " + customer.getLastName());
                } else {
                    System.out.println("Booking not canceled.");
                }
            } else {
                System.out.println("No booking found for week " + weekNumber + ".");
            }
        }
    }








    // Method to view booked lessons
    public void viewBookedLessons() {
        if (customer == null) {
            System.out.println("Please sign in first to view your booked lessons.");
            signIn();
        } else {
            System.out.println("List of booked lessons for " + customer.getFirstname() + " " + customer.getLastName() + ":");
            boolean foundBookedLessons = false;
            for (Lesson lesson : bookedLessons) {
                if (lesson.getCustomer().equals(customer)) {
                    System.out.println(lesson.toString());
                    foundBookedLessons = true;
                }
            }
            if (!foundBookedLessons) {
                System.out.println("No booked lessons found.");
            }
        }
    }

    public void addReview() {
        Scanner scanner = new Scanner(System.in);
        if (customer == null) {
            System.out.println("Please sign in first before adding a review.");
            signIn();
        } else {
            System.out.print("Enter the lesson type or day for which you want to add a review: ");
            String lessonTypeOrDay = scanner.nextLine();

            System.out.print("Enter your review comment: ");
            String comment = scanner.nextLine();

            System.out.print("Enter your rating (1-5): ");
            int rating = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Create a new Review object and add it to the reviews list
            Review review = new Review(customer, lessonTypeOrDay, comment, rating);
            reviews.add(review);

            System.out.println("Review added successfully.");
        }
    }



    // Method to view booked lessons

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        boolean isSignedUp = false;
        boolean isSignedIn = false;

        do {
            System.out.println("========== Lesson Booking System ==========");
            if (!isSignedUp) {
                System.out.println("1. Sign up");
            } else if (!isSignedIn) {
                System.out.println("2. Sign in");
            } else {
                System.out.println("3. Sign out");
                System.out.println("4. Book lesson by type");
                System.out.println("5. Book lesson by day");
                System.out.println("6. Change booking");
                System.out.println("7. Cancel booking");
            }
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    signUp();
                    isSignedUp = true;
                    break;
                case "2":
                    if (isSignedUp) {
                        signIn();
                        isSignedIn = true;
                    } else {
                        System.out.println("You need to sign up first.");
                    }
                    break;
                case "3":
                    if (isSignedIn) {
                        signOut();
                        isSignedIn = false;
                    } else {
                        System.out.println("You need to sign in first.");
                    }
                    break;
                case "4":
                    if (isSignedIn) {
                        bookLessonByType();
                    } else {
                        System.out.println("You need to sign in first.");
                    }
                    break;
                case "5":
                    if (isSignedIn) {
                        bookLessonByDay();
                    } else {
                        System.out.println("You need to sign in first.");
                    }
                    break;
                case "6":
                    if (isSignedIn) {
                        viewBookedLessons();
                    } else {
                        System.out.println("You need to sign in first.");
                    }
                    break;
                case "7":
                    if (isSignedIn) {
                        cancelBooking();
                    } else {
                        System.out.println("You need to sign in first.");
                    }
                    break;
                case "8":
                    System.out.println("Thank you for using the Lesson Booking System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!choice.equals("8"));
    }


    public static void main(String[] args) {
        LessonBookingSystem bookingSystem = new LessonBookingSystem();
        bookingSystem.run();
    }
}
