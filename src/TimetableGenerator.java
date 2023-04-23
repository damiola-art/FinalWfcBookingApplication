import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;


public class TimetableGenerator {
    private String[][] timetable; // Declare the timetable field






    // Constructor
    public TimetableGenerator() {
        this.setTimetable(generateTimetable()); // Call the generateTimetable() method to generate the timetable
      // this.viewTimetable();



    }

/*    public void viewTimetable() {
        System.out.println("Timetable for all lesson types:");
        System.out.format("%-5s%-10s%-12s%-8s%-25s%-25s%n", "Week", "Month", "Date", "Day", "Lesson 1", "Lesson 2");

        for (String[] row : getTimetable()) {
            System.out.format("%-5s%-10s%-12s%-8s%-25s%-25s%n", row[0], row[1], row[2], row[3], row[4], row[5]);
        }
    }
*/
    public void viewTimetableByLessonType(String lessonType) {
        System.out.println("Timetable for " + lessonType + ":");
        System.out.format("%-5s%-10s%-12s%-8s%-25s%n", "Week", "Month", "Date", "Day", "Lesson");

        boolean lessonFound = false; // added flag to track if lesson is found

        for (String[] row : getTimetable()) {

            if (row.length >= 5 && (row[4].toLowerCase().contains(lessonType.toLowerCase()) || row[5].toLowerCase().contains(lessonType.toLowerCase()))) {
                String lesson = row[5].toLowerCase().contains(lessonType.toLowerCase()) ? row[5] : row[4];
                System.out.format("%-5s%-10s%-12s%-8s%-25s%n", row[0], row[1], row[2], row[3], lesson);
                lessonFound = true; // set flag to true if lesson is found
            }
        }

        if (!lessonFound) { // display error message if lesson not found
            System.out.println("No lessons found for the given lesson type: " + lessonType);
        }
    }

   /*
        System.out.println("Timetable for " + lessonType + ":");
        System.out.format("%-5s%-10s%-12s%-8s%-25s%-25s%n", "Week", "Month", "Date", "Day", "Lesson 1", "Lesson 2");

        for (String[] row : getTimetable()) {
            if (row[4].toLowerCase().contains(lessonType.toLowerCase()) || row[5].toLowerCase().contains(lessonType.toLowerCase())) {
                System.out.format("%-5s%-10s%-12s%-8s%-25s%-25s%n", row[0], row[1], row[2], row[3], row[4], row[5]);
            }
        }
    }
*/
  /*public void viewTimetableByLessonType(String lessonType) {
      System.out.println("Timetable for " + lessonType + ":");
      System.out.format("%-5s%-10s%-12s%-8s%-25s%-15s%n", "Week", "Month", "Date", "Day", "Lesson", "Time");

      for (String[] row : getTimetable()) {
          if (row[4].toLowerCase().contains(lessonType.toLowerCase()) || row[5].toLowerCase().contains(lessonType.toLowerCase())) {
              System.out.format("%-5s%-10s%-12s%-8s%-25s%-15s%n", row[0], row[1], row[2], row[3], row[4], row[6]);
          }
      }
  }

   */



    // Method to view timetable by day
    public void viewTimetableByDay(String dayOfWeek) {
        System.out.println("Timetable for " + dayOfWeek + ":");
        System.out.format("%-5s%-10s%-12s%-8s%-25s%-25s%n", "Week", "Month", "Date", "Day", "Lesson 1", "Lesson 2");

        for (String[] row : getTimetable()) {
            if (row[3].equalsIgnoreCase(dayOfWeek)) {
                System.out.format("%-5s%-10s%-12s%-8s%-25s%-25s%n", row[0], row[1], row[2], row[3], row[4], row[5]);
            }
        }
    }

    // Method to generate the timetable
    String[][] generateTimetable() {
        // Define the available lessons and their time slots
        String[] lessons = {"Spin", "Yoga", "Bodysculpt", "Zumba", "Aquacise", "Box Fit"};
        String[] timeSlots = {"10:00 AM", "2:00 PM", "9:00 AM", "11:00 AM", "3:00 PM", "5:00 PM", "4:00 PM", "6:00 PM", "11:00 AM", "1:00 PM", "2:00 PM", "4:00 PM"};

        // Create a calendar for 8 weekends (16 days)
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = currentDate.plusWeeks(8);

        // Initialize the week number to 1
        int weekNumber = 1;

        // Initialize timetable ArrayList
        ArrayList<String[]> timetable = new ArrayList<>();

        // Iterate over the dates and check for Saturdays and Sundays
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        for (LocalDate date = currentDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                // Output the week number, month, date, and day if it's a Sunday
                String month = date.format(DateTimeFormatter.ofPattern("MMM"));
                String formattedDate = date.format(formatter);
                String dayOfWeek = date.getDayOfWeek().toString().substring(0, 3);
                String[] row = {String.valueOf(weekNumber), month, formattedDate, dayOfWeek, "", ""}; // Fix: Initialize with empty strings for lessons
                timetable.add(row);
                weekNumber++;
            } else if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                // Output the week number, month, date, and day if it's a Saturday
                String month = date.format(DateTimeFormatter.ofPattern("MMM"));
                String formattedDate = date.format(formatter);
                String dayOfWeek = date.getDayOfWeek().toString().substring(0, 3);
                String[] row = {String.valueOf(weekNumber), month, formattedDate, dayOfWeek, "", ""}; // Fix: Initialize with empty strings for lessons
                timetable.add(row);
            }
        }

        // Populate the timetable with available lessons
        // Populate the timetable with available lessons and time slots
        int lessonIndex = 0;
        for (String[] row : timetable) {
            row[4] = lessons[lessonIndex % lessons.length] + ": " + timeSlots[lessonIndex % timeSlots.length];
            row[5] = lessons[(lessonIndex + 1) % lessons.length] + ": " + timeSlots[(lessonIndex + 1) % timeSlots.length];
            lessonIndex += 2;
        }

        // Set the completed timetable to the timetable field
        String[][] timetableArray = new String[timetable.size()][];
        for (int i = 0; i < timetable.size(); i++) {
            timetableArray[i] = timetable.get(i);
        }

        this.setTimetable(timetableArray);

        return timetableArray;
    }

    public String[][] getTimetable() {
        return timetable;
    }

    public void setTimetable(String[][] timetable) {
        this.timetable = timetable;
    }
}