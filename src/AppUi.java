
import java.awt.print.Book;
import java.util.*;

public class AppUi {

    private Timetable timetable;
    private Customer signedInCustomer;
    LessonBookingSystem lessonBookingSystem;
    Scanner scanner = new Scanner(System.in);



    public AppUi() {
        this.lessonBookingSystem = new LessonBookingSystem();
        this.timetable = new Timetable();
        this.signedInCustomer = null;
    }

    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastname = scanner.nextLine();
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        Customer currentCustomer = lessonBookingSystem.signUp(firstname,lastname,email);
        signedInCustomer = currentCustomer;
        System.out.println("Sign up successful! Welcome, " + firstname + " " + lastname + ".");

    }

    public void signIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        // Validate email against customer's email
        if (lessonBookingSystem.getCustomers().containsKey(email)) {
            this.signedInCustomer = lessonBookingSystem.getCustomers().get(email);
            System.out.println("Sign in successful! Welcome back, " + signedInCustomer.getFirstname() + " " + signedInCustomer.getLastName() + ".");
        } else {
            System.out.println("Invalid email address. Please try again.");
            signIn();
        }
    }

    public void signOut() {
        this.signedInCustomer = null;
        System.out.println("Sign out successful. Thank you for using the Lesson Booking System!");
    }

    public Lesson selectLessons(ArrayList<Lesson> lessons){
        System.out.println("Choose a Lesson");
        for(int i = 0; i < lessons.size(); i++ ){
            System.out.println("Enter " + (int)(i + 1) + " for " + lessons.get(i).toString());
        }

        int choice = scanner.nextInt();
        Lesson lesson = lessons.get(choice - 1);
        if(signedInCustomer.getBookedLessons().contains(lesson)){
            System.out.println("You already have this lesson, please pick another lesson");
            return selectLessons(lessons);
        } else if(lesson.isFilled()){
            System.out.println("This Lesson is filled, please pick another lesson");
            return selectLessons(lessons);
        } else {
            return lesson;
        }

    }

    public void displayBookings(){
        for (Map.Entry<String, Booking> entry : signedInCustomer.getBookings().entrySet()) {
            System.out.println("Booking ID: " + entry.getKey() + ", Lesson: " + entry.getValue().getLesson().toString());
        }
    }


    public Lesson bookLessonByType() {
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

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        switch (lessonChoice) {
            case 1:
                lessons = timetable.getLessonByType("spin");
                break;
            case 2:
                lessons = timetable.getLessonByType("yoga");
                break;
            case 3:
                lessons = timetable.getLessonByType("bodysculpt");
                break;
            case 4:
                lessons = timetable.getLessonByType("zumba");
                break;
            case 5:
                lessons = timetable.getLessonByType("aquacise");
                break;
            case 6:
                lessons = timetable.getLessonByType("box fit");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                bookLessonByType();
        }

        return selectLessons(lessons);

    }

    public Lesson bookLessonByDay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the day:");
        System.out.println("1. Saturday");
        System.out.println("2. Sunday");
        System.out.print("Enter your choice: ");
        int dayChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();

        switch (dayChoice) {
            case 1:
                lessons = timetable.getLessonByDay("saturday");
                break;
            case 2:
                lessons = timetable.getLessonByDay("sunday");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                bookLessonByDay();
        }
        return selectLessons(lessons);



    }

    public Lesson chooseLesson(){
        System.out.println("Select how you want to view the timetable, Enter 1 for By Day or 2 for By Fitness Activity");
        int choice = scanner.nextInt();
        Lesson lesson = null;
        if(choice == 1){
            lesson = bookLessonByDay();
        } else if(choice == 2){
            lesson = bookLessonByType();
        } else{
            System.out.println("Please enter a valid option");
            return chooseLesson();
        }
        return lesson;
    }

    public void bookLesson(){
        Lesson lesson = chooseLesson();
        String bookingID = lessonBookingSystem.registerNewBooking(signedInCustomer, lesson);
        System.out.println("Booking successful. Your booking ID is " + bookingID + "\n");
    }

    public void changeBooking() {
         // Consume the newline character
        displayBookings();
        boolean continueLoop = true;
        Lesson newlesson = null;
        Booking booking = null;
        while(continueLoop){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the booking ID of the booking you want to change NB: ID is Case sensitive");

            String bookingID = scanner.next();
            scanner.nextLine();

            if(signedInCustomer.getBookings().containsKey(bookingID)){
                System.out.println("Pick the lesson you want to change the booking to");

                newlesson = chooseLesson();
                booking = signedInCustomer.getBookings().get(bookingID);
                continueLoop = false;
            } else {
                System.out.println("The ID you typed is incorrect");
            }
        }
        lessonBookingSystem.changeBooking(booking, newlesson);
        System.out.println("Booking changed successfully\n");
    }

    public void cancelBooking() {
        displayBookings();
        boolean continueLoop = true;
        Booking booking = null;
        while(continueLoop){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the booking ID of the booking you want to cancel NB: ID is Case sensitive");

            String bookingID = scanner.next();
            scanner.nextLine();

            if(signedInCustomer.getBookings().containsKey(bookingID)){
                booking = signedInCustomer.getBookings().get(bookingID);
                continueLoop = false;
            } else {
                System.out.println("The ID you typed is incorrect");
            }
        }
        lessonBookingSystem.cancelBooking(booking);
        System.out.println("Booking cancelled successfully\n");
    }


    public void attendLesson() {
        Scanner feedbackScanner = new Scanner(System.in);
        displayBookings();
        boolean continueLoop = true;
        Booking booking = null;
        while(continueLoop){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the booking ID of the booking you want to attend NB: ID is Case sensitive");

            String bookingID = scanner.next();
            scanner.nextLine();

            if(signedInCustomer.getBookings().containsKey(bookingID)){
                booking = signedInCustomer.getBookings().get(bookingID);
                continueLoop = false;
            } else {
                System.out.println("The ID you typed is incorrect");
            }
        }
        System.out.println("Thank you for attending the lesson, Please drop a review");

        boolean loop = true;
        Review review = null;

        while(loop){
            System.out.println("Enter 1 to drop both feedback and rating and 2 to drop just rating");
            int choice = scanner.nextInt();

            if(choice == 1){

                int rating = 0;

                while (rating > 5 || rating < 1){
                    System.out.println("Enter your rating (1-5):");
                    System.out.println("1: Very dissatisfied");
                    System.out.println("2: Dissatisfied");
                    System.out.println("3: Ok");
                    System.out.println("4: Satisfied");
                    System.out.println("5: Very Satisfied");
                    rating = scanner.nextInt();
                }
                System.out.println("Enter Feedback");
                String feedback = feedbackScanner.nextLine();

                review = new Review(feedback, rating);
                loop = false;
            } else if(choice == 2){
                int rating = 0;

                while (rating > 5 || rating < 1){
                    System.out.println("Enter your rating (1-5):");
                    System.out.println("1: Very dissatisfied");
                    System.out.println("2: Dissatisfied");
                    System.out.println("3: Ok");
                    System.out.println("4: Satisfied");
                    System.out.println("5: Very Satisfied");
                    rating = scanner.nextInt();
                }

                review = new Review(rating);
                loop = false;
            }
        }
        lessonBookingSystem.attendLesson(booking, review);

        System.out.println("Review Submitted\n");
    }


    public void displayMonthlyLessonReport() {
        System.out.print("Enter the Month");
        int month = scanner.nextInt();
        System.out.println("Here is the monthly report for Month " + month + "\n\n");
        System.out.println(timetable.getMonthlyReport(month));
    }




    public void monthlyChampionFitnessTypeReport(){
        System.out.print("Enter the Month");
        int month = scanner.nextInt();
        System.out.println("Here is the Champions report for Month " + month + "\n\n");
        System.out.println(timetable.getChampionsReport(month));
    }

    public void exit(){
        System.exit(0);
    }

    // Method to view booked lessons
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1. Sign up");
            System.out.println("2. Sign in");
            int choice = scanner.nextInt();
            if (choice == 1){
                signUp();
                break;
            } else if(choice == 2){
                signIn();
                break;
            }
        }

        while (true){
            System.out.println("1. Book a group fitness lesson");
            System.out.println("2. Modify Booking");
            System.out.println("3. Monthly Lesson Report");
            System.out.println("4. Monthly Champion Fitness Type Report");
            System.out.println("5 Sign out");
            System.out.println("0 Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bookLesson();
                    break;
                case 2:
                    while (true){
                        System.out.println("1. Attend Lesson");
                        System.out.println("2. Change Booking");
                        System.out.println("3. Cancel Booking");
                        System.out.print("Enter your choice: ");
                        int bookingChoice = scanner.nextInt();
                        if (bookingChoice == 1) {
                            attendLesson();
                            break;
                        } else if (bookingChoice == 2) {
                            changeBooking();
                            break;
                        } else if (bookingChoice == 3) {
                            cancelBooking();
                            break;
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                    } break;
                case 3:
                    displayMonthlyLessonReport();
                    break;
                case 4:
                    monthlyChampionFitnessTypeReport();
                    break;

                case 5:
                    signOut();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }


        }
    }

}
