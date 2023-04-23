import java.util.*;

public class LessonBookingSystem {
    private TimetableGenerator timetableGenerator;
    private ArrayList<Lesson> bookedLessons;

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
        System.out.println("Enter the lesson type:");
        System.out.println("1. Spin");
        System.out.println("2. Yoga");
        System.out.println("3. Bodysculpt");
        System.out.println("4. Zumba");
        System.out.println("5. Aquacise");
        System.out.println("6. Box Fit");
        System.out.print("Enter your choice (1-6): ");
        int lessonChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String lessonType;

        double price = 0.0;
        switch (lessonChoice) {
            case 1:
                lessonType = "Spin";
                price = 10.0;
                break;
            case 2:
                lessonType = "Yoga";
                price = 15.0;
                break;
            case 3:
                lessonType = "Bodysculpt";
                price = 12.0;
                break;
            case 4:
                lessonType = "Zumba";
                price = 8.0;
                break;
            case 5:
                lessonType = "Aquacise";
                price = 12.0;
                break;
            case 6:
                lessonType = "Box Fit";
                price = 10.0;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        timetableGenerator.viewTimetableByLessonType(lessonType);

        if (customer == null) {
            System.out.println("Please sign in first before booking a lesson by type.");
            signIn();
        }

        if (customer != null) {
            System.out.print("Enter the week number to book a lesson (1-8): ");
            int weekNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            // Check if week number is valid
            if (weekNumber < 1 || weekNumber > 8) {
                System.out.println("Invalid lesson number. Please try again.");
            } else {
                if (bookedLessons.size() >= 5) {
                    System.out.println("Lesson " + lessonType + " for week " + weekNumber + " is fully booked. Please choose another week or lesson.");
                } else {
                    Lesson lesson = new Lesson(lessonType, weekNumber, customer, null, price, 4);
                    bookedLessons.add(lesson);
                    System.out.println("Lesson booked for week " + weekNumber);
                }
            }
        } else {
            System.out.println("No customer is set. Please set a customer before booking a lesson.");
        }
    }

    public void bookLessonByDay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the day:");
        System.out.println("1. Saturday");
        System.out.println("2. Sunday");
        System.out.print("Enter your choice: ");
        int dayChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        String dayOfWeek;

        switch (dayChoice) {
            case 1:
                dayOfWeek = "Sat";
                break;
            case 2:
                dayOfWeek = "Sun";
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        timetableGenerator.viewTimetableByDay(dayOfWeek);

        if (customer == null) {
            System.out.println("Please sign in first before booking a lesson by type.");
            signIn();
        }

        System.out.print("Enter the week number to book a lesson (1-8): ");
        int weekNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (weekNumber < 1 || weekNumber > 8) {
            System.out.println("Invalid week number. Please try again.");
            return;
        }

        System.out.println("Select the lesson you want to book:");
        System.out.println("1. Spin");
        System.out.println("2. Yoga");
        System.out.println("3. Bodysculpt");
        System.out.println("4. Zumba");
        System.out.println("5. Aquacise");
        System.out.println("6. Box Fit");
        System.out.print("Enter your choice: ");
        int lessonChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        String lessonType;
        double price;

        switch (lessonChoice) {
            case 1:
                lessonType = "Spin";
                price = 10.0;
                break;
            case 2:
                lessonType = "Yoga";
                price = 15.0;
                break;
            case 3:
                lessonType = "Bodysculpt";
                price = 12.0;
                break;
            case 4:
                lessonType = "Zumba";
                price = 8.0;
                break;
            case 5:
                lessonType = "Aquacise";
                price = 12.0;
                break;
            case 6:
                lessonType = "Box Fit";
                price = 10.0;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        // Check if lesson has available capacity
        if (bookedLessons.size() >= 5) {
            System.out.println("Lesson " + lessonType + " for week " + weekNumber + " is fully booked. Please choose another week or lesson.");
        } else {
            Lesson lesson = new Lesson(lessonType, weekNumber, customer, null, price, 4);
            bookedLessons.add(lesson);
            System.out.println(lessonType + " lesson booked for week " + weekNumber );
        }
    }

    public void changeBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the week number for the booking you want to change (1-8): ");
        int weekNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (customer == null) {
            System.out.println("Please sign in first before changing a booking.");
            signIn();
        } else {
            for (Lesson lesson1 : bookedLessons) {
                if (lesson1.getCustomer().getEmail().equals(customer.getEmail())) {
                    System.out.println("Are you sure you want to change your booking for week " + weekNumber + "? (yes/no): ");
                    String choice = scanner.nextLine().toLowerCase();
                    if (choice.equals("yes")) {
                        bookedLessons.remove(lesson1);
                        System.out.print("lesson has been removed ");

                        System.out.println("Book lesson by lessontype or day");

                        String option = scanner.nextLine().toLowerCase();

                        switch (option) {
                            case "day":
                                bookLessonByDay();
                                break;
                            case "lessontype":
                                bookLessonByType();
                                break;
                            default:
                                System.out.println("Not a function");
                                break;
                        }
                    } else {
                        System.out.println("Booking not canceled.");
                    }
                } else {
                    System.out.println("No booking found for week " + weekNumber + ".");
                }
            }
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
            boolean bookingFound = false;
            for (Lesson lesson : bookedLessons) {
                if (lesson.getWeekNumber() == weekNumber && lesson.getCustomer().getEmail().equals(customer.getEmail())) {
                    bookingFound = true;
                    System.out.println("Are you sure you want to cancel your booking for week " + weekNumber + "? (yes/no): ");
                    String choice = scanner.nextLine().toLowerCase();
                    if (choice.equals("yes")) {
                        bookedLessons.remove(lesson);
                        System.out.println("Booking canceled for week " + weekNumber + " by customer " + customer.getFirstname() + " " + customer.getLastName());
                        break;
                    } else {
                        System.out.println("Booking not canceled.");
                        break;
                    }
                }
            }
            if (!bookingFound) {
                System.out.println("No booking found for week " + weekNumber + ".");
            }
        }
    }


    public void addReview(Lesson lesson) {
        Scanner scanner = new Scanner(System.in);
        if (customer == null) {
            System.out.println("Please sign in first before adding a review.");
            signIn();
        } else {
            System.out.print(" Add a review: ");
            String lessonTypeOrDay = scanner.nextLine();

            int rating = 0;
            boolean validRating = false;
            while (!validRating) {
                System.out.println("Enter your rating (1-5):");
                System.out.println("1: Very dissatisfied");
                System.out.println("2: Dissatisfied");
                System.out.println("3: Ok");
                System.out.println("4: Satisfied");
                System.out.println("5: Very Satisfied");
                String ratingInput = scanner.nextLine();

                try {
                    rating = Integer.parseInt(ratingInput);
                    if (rating >= 1 && rating <= 5) {
                        validRating = true;
                    } else {
                        System.out.println("Invalid rating. Please enter a rating between 1 and 5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid rating. Please enter a numeric value between 1 and 5.");
                }
            }

            // Create a new Review object and add it to the reviews list
            Review review = new Review(customer, lessonTypeOrDay, "", rating);

            reviews.add(review);

            System.out.println(" Thanks for the review.");


        }
    }

    public void viewBookedLessons() {
        if (customer == null) {
            System.out.println("Please sign in first to view your booked lessons.");
            signIn();
        }

        if (bookedLessons.isEmpty()) {
            System.out.println("You have not booked any lessons yet.");
        } else {
            System.out.println("List of booked lessons:");
            for (Lesson lesson : bookedLessons) {
                if (lesson != null && lesson.getCustomer().getEmail().equals(customer.getEmail())) {
                    System.out.println("- Week " + lesson.getWeekNumber() + ": " + lesson.getType() + " booked by " + customer.getFirstname() + " " + customer.getLastName() + "" + " for $" + lesson.getPrice());
                }
            }
        }
    }


    public void attendLesson() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the week number for the booking you want to attend (1-8): ");
        int weekNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (customer == null) {
            System.out.println("Please sign in first before attending a lesson.");
            signIn();
        } else {
            List<Lesson> lessonsToRemove = new ArrayList<>();
            boolean foundLesson = false;
            for (Lesson lesson : bookedLessons) {
                if (lesson.getWeekNumber() == weekNumber && lesson.getCustomer().getEmail().equals(customer.getEmail())) {
                    System.out.println("Lesson has been attended.");
                    addReview(lesson);
                    lesson.setNoOfCustomers(lesson.getNoOfCustomers() + 1);
                    lesson.setTotalPrice(lesson.getTotalPrice() + lesson.getPrice());
                    lessonsToRemove.add(lesson);
                    foundLesson = true;
                    break;
                }
            }
            if (!foundLesson) {
                System.out.println("No booking found for week " + weekNumber + ".");
            } else {
                bookedLessons.removeAll(lessonsToRemove);
            }
        }
    }


    public String getMonthName(int num){
        String name = "";

        switch (num){
            case 1:
                name = "January";
                break;
            case 2:
                name = "Febuary";
                break;
            case 3:
                name = "March";
                break;
            case 4:
                name = "April";
                break;
            case 5:
                name = "May";
                break;
            case 6:
                name = "June";
                break;
            case 7:
                name = "July";
                break;
            case 8:
                name = "August";
                break;
            case 9:
                name = "September";
                break;
            case 10:
                name = "October";
                break;
            case 11:
                name = "November";
                break;
            case 12:
                name = "December";
                break;
            default:
                name = "Unknown";
                break;
        }

        return name;
    }

    public void monthlyLessonReport(int month) {
        if (bookedLessons.isEmpty()) {
            System.out.println("There are no lessons booked.");
            return;
        }

        System.out.println("Monthly Lesson Report for " + getMonthName(month));

        for (Lesson lesson : bookedLessons) {
            if (lesson.getMonth() == month) {
                System.out.println( "Lesson " + lesson.getType() + ", Number of customers is " + lesson.getNoOfCustomers() +", rating - (" +  lesson.getReview());
            }
        }
    }




    public void monthlyChampionFitnessTypeReport(int month){
        if (bookedLessons.isEmpty()) {
            System.out.println("There are no lessons booked.");
            return;
        }

        System.out.println("Monthly Lesson Report for " + getMonthName(month));

        for (Lesson lesson : bookedLessons) {
            if (lesson.getMonth() == month) {
                System.out.println("Lesson " + lesson.getType() + ", Number of customers is $" + lesson.getTotalPrice());
            }
        }
    };

    // Method to view booked lessons
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
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
                System.out.println("4. Book a group fitness lesson");
                System.out.println("5. Modify Booking");
                System.out.println("6. Change booking");
                System.out.println("7. Monthly Lesson Report");
                System.out.println("8. Monthly Champion Fitness Type Report");
            }
            System.out.println("9. Exit");
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
                            System.out.println("1. Book by day");
                            System.out.println("2. Book by type");
                            System.out.print("Enter your choice: ");
                            String bookingChoice = scanner.nextLine();
                            if (bookingChoice.equals("1")) {
                                bookLessonByDay();
                            } else if (bookingChoice.equals("2")) {
                                bookLessonByType();
                            } else {
                                System.out.println("Invalid choice. Please try again.");
                            }
                            viewBookedLessons();
                        } else {
                            System.out.println("You need to sign in first.");
                        }
                        break;
                    case "5":
                        if (isSignedIn) {
                            viewBookedLessons();
                            System.out.println("1. Attend Lesson");
                            System.out.println("2. Change Booking");
                            System.out.println("3. Cancel Booking");
                            System.out.print("Enter your choice: ");
                            String bookingChoice = scanner.nextLine();
                            if (bookingChoice.equals("1")) {
                                attendLesson();
                            } else if (bookingChoice.equals("2")) {
                                changeBooking();
                            } else if (bookingChoice.equals("3")) {
                                cancelBooking();
                            } else {
                                System.out.println("Invalid choice. Please try again.");
                            }
                            viewBookedLessons();
                        } else {
                            System.out.println("You need to sign in first.");
                        }
                        break;
                    case "6":
                        if (isSignedIn) {
                            changeBooking();
                            viewBookedLessons();
                        } else {
                            System.out.println("You need to sign in first.");
                        }
                        break;
                    case "7":
                        if (isSignedIn) {
                            monthlyLessonReport(4);
                        } else {
                            System.out.println("You need to sign in first.");
                        }
                        break;
                    case "8":
                        if (isSignedIn) {
                            monthlyChampionFitnessTypeReport(4);
                        } else {
                            System.out.println("You need to sign in first.");
                        }
                        break;
                    case "9":
                        System.out.println("Exiting the system...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");

                }

        } while (!choice.equals("9"));
    }

    public static void main(String[] args) {
        LessonBookingSystem bookingSystem = new LessonBookingSystem();
        bookingSystem.run();
    }
}
